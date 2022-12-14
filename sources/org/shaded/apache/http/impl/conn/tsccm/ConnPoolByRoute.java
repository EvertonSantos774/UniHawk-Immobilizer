package org.shaded.apache.http.impl.conn.tsccm;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.conn.ClientConnectionOperator;
import org.shaded.apache.http.conn.ConnectionPoolTimeoutException;
import org.shaded.apache.http.conn.params.ConnManagerParams;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.params.HttpParams;

public class ConnPoolByRoute extends AbstractConnPool {
    protected final Queue<BasicPoolEntry> freeConnections;
    private final Log log = LogFactory.getLog((Class) getClass());
    protected final ClientConnectionOperator operator;
    private final HttpParams params;
    protected final Map<HttpRoute, RouteSpecificPool> routeToPool;
    protected final Queue<WaitingThread> waitingThreads;

    public ConnPoolByRoute(ClientConnectionOperator operator2, HttpParams params2) {
        if (operator2 == null) {
            throw new IllegalArgumentException("Connection operator may not be null");
        }
        this.operator = operator2;
        this.params = params2;
        this.freeConnections = createFreeConnQueue();
        this.waitingThreads = createWaitingThreadQueue();
        this.routeToPool = createRouteToPoolMap();
    }

    /* access modifiers changed from: protected */
    public Queue<BasicPoolEntry> createFreeConnQueue() {
        return new LinkedList();
    }

    /* access modifiers changed from: protected */
    public Queue<WaitingThread> createWaitingThreadQueue() {
        return new LinkedList();
    }

    /* access modifiers changed from: protected */
    public Map<HttpRoute, RouteSpecificPool> createRouteToPoolMap() {
        return new HashMap();
    }

    /* access modifiers changed from: protected */
    public RouteSpecificPool newRouteSpecificPool(HttpRoute route) {
        return new RouteSpecificPool(route, ConnManagerParams.getMaxConnectionsPerRoute(this.params).getMaxForRoute(route));
    }

    /* access modifiers changed from: protected */
    public WaitingThread newWaitingThread(Condition cond, RouteSpecificPool rospl) {
        return new WaitingThread(cond, rospl);
    }

    /* access modifiers changed from: protected */
    public RouteSpecificPool getRoutePool(HttpRoute route, boolean create) {
        this.poolLock.lock();
        try {
            RouteSpecificPool rospl = this.routeToPool.get(route);
            if (rospl == null && create) {
                rospl = newRouteSpecificPool(route);
                this.routeToPool.put(route, rospl);
            }
            return rospl;
        } finally {
            this.poolLock.unlock();
        }
    }

    public int getConnectionsInPool(HttpRoute route) {
        int i = 0;
        this.poolLock.lock();
        try {
            RouteSpecificPool rospl = getRoutePool(route, false);
            if (rospl != null) {
                i = rospl.getEntryCount();
            }
            return i;
        } finally {
            this.poolLock.unlock();
        }
    }

    public PoolEntryRequest requestPoolEntry(final HttpRoute route, final Object state) {
        final WaitingThreadAborter aborter = new WaitingThreadAborter();
        return new PoolEntryRequest() {
            public void abortRequest() {
                ConnPoolByRoute.this.poolLock.lock();
                try {
                    aborter.abort();
                } finally {
                    ConnPoolByRoute.this.poolLock.unlock();
                }
            }

            public BasicPoolEntry getPoolEntry(long timeout, TimeUnit tunit) throws InterruptedException, ConnectionPoolTimeoutException {
                return ConnPoolByRoute.this.getEntryBlocking(route, state, timeout, tunit, aborter);
            }
        };
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry getEntryBlocking(HttpRoute route, Object state, long timeout, TimeUnit tunit, WaitingThreadAborter aborter) throws ConnectionPoolTimeoutException, InterruptedException {
        RouteSpecificPool rospl;
        WaitingThread waitingThread;
        int maxTotalConnections = ConnManagerParams.getMaxTotalConnections(this.params);
        Date deadline = null;
        if (timeout > 0) {
            deadline = new Date(System.currentTimeMillis() + tunit.toMillis(timeout));
        }
        BasicPoolEntry entry = null;
        this.poolLock.lock();
        try {
            rospl = getRoutePool(route, true);
            waitingThread = null;
            while (entry == null) {
                if (this.isShutDown) {
                    throw new IllegalStateException("Connection pool shut down.");
                }
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Total connections kept alive: " + this.freeConnections.size());
                    this.log.debug("Total issued connections: " + this.leasedConnections.size());
                    this.log.debug("Total allocated connection: " + this.numConnections + " out of " + maxTotalConnections);
                }
                entry = getFreeEntry(rospl, state);
                if (entry != null) {
                    break;
                }
                boolean hasCapacity = rospl.getCapacity() > 0;
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Available capacity: " + rospl.getCapacity() + " out of " + rospl.getMaxEntries() + " [" + route + "][" + state + "]");
                }
                if (hasCapacity && this.numConnections < maxTotalConnections) {
                    entry = createEntry(rospl, this.operator);
                } else if (!hasCapacity || this.freeConnections.isEmpty()) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Need to wait for connection [" + route + "][" + state + "]");
                    }
                    if (waitingThread == null) {
                        waitingThread = newWaitingThread(this.poolLock.newCondition(), rospl);
                        aborter.setWaitingThread(waitingThread);
                    }
                    rospl.queueThread(waitingThread);
                    this.waitingThreads.add(waitingThread);
                    boolean success = waitingThread.await(deadline);
                    rospl.removeThread(waitingThread);
                    this.waitingThreads.remove(waitingThread);
                    if (!success && deadline != null && deadline.getTime() <= System.currentTimeMillis()) {
                        throw new ConnectionPoolTimeoutException("Timeout waiting for connection");
                    }
                } else {
                    deleteLeastUsedEntry();
                    entry = createEntry(rospl, this.operator);
                }
            }
            this.poolLock.unlock();
            return entry;
        } catch (Throwable th) {
            this.poolLock.unlock();
            throw th;
        }
    }

    public void freeEntry(BasicPoolEntry entry, boolean reusable, long validDuration, TimeUnit timeUnit) {
        HttpRoute route = entry.getPlannedRoute();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Releasing connection [" + route + "][" + entry.getState() + "]");
        }
        this.poolLock.lock();
        try {
            if (this.isShutDown) {
                closeConnection(entry.getConnection());
                return;
            }
            this.leasedConnections.remove(entry);
            RouteSpecificPool rospl = getRoutePool(route, true);
            if (reusable) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Pooling connection [" + route + "][" + entry.getState() + "]" + "; keep alive for " + validDuration + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + timeUnit.toString());
                }
                rospl.freeEntry(entry);
                this.freeConnections.add(entry);
                this.idleConnHandler.add(entry.getConnection(), validDuration, timeUnit);
            } else {
                rospl.dropEntry();
                this.numConnections--;
            }
            notifyWaitingThread(rospl);
            this.poolLock.unlock();
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry getFreeEntry(RouteSpecificPool rospl, Object state) {
        BasicPoolEntry entry = null;
        this.poolLock.lock();
        boolean done = false;
        while (!done) {
            try {
                entry = rospl.allocEntry(state);
                if (entry != null) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Getting free connection [" + rospl.getRoute() + "][" + state + "]");
                    }
                    this.freeConnections.remove(entry);
                    if (!this.idleConnHandler.remove(entry.getConnection())) {
                        if (this.log.isDebugEnabled()) {
                            this.log.debug("Closing expired free connection [" + rospl.getRoute() + "][" + state + "]");
                        }
                        closeConnection(entry.getConnection());
                        rospl.dropEntry();
                        this.numConnections--;
                    } else {
                        this.leasedConnections.add(entry);
                        done = true;
                    }
                } else {
                    done = true;
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("No free connections [" + rospl.getRoute() + "][" + state + "]");
                    }
                }
            } catch (Throwable th) {
                this.poolLock.unlock();
                throw th;
            }
        }
        this.poolLock.unlock();
        return entry;
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry createEntry(RouteSpecificPool rospl, ClientConnectionOperator op) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Creating new connection [" + rospl.getRoute() + "]");
        }
        BasicPoolEntry entry = new BasicPoolEntry(op, rospl.getRoute());
        this.poolLock.lock();
        try {
            rospl.createdEntry(entry);
            this.numConnections++;
            this.leasedConnections.add(entry);
            return entry;
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void deleteEntry(BasicPoolEntry entry) {
        HttpRoute route = entry.getPlannedRoute();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Deleting connection [" + route + "][" + entry.getState() + "]");
        }
        this.poolLock.lock();
        try {
            closeConnection(entry.getConnection());
            RouteSpecificPool rospl = getRoutePool(route, true);
            rospl.deleteEntry(entry);
            this.numConnections--;
            if (rospl.isUnused()) {
                this.routeToPool.remove(route);
            }
            this.idleConnHandler.remove(entry.getConnection());
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void deleteLeastUsedEntry() {
        try {
            this.poolLock.lock();
            BasicPoolEntry entry = this.freeConnections.remove();
            if (entry != null) {
                deleteEntry(entry);
            } else if (this.log.isDebugEnabled()) {
                this.log.debug("No free connection to delete.");
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void handleLostEntry(HttpRoute route) {
        this.poolLock.lock();
        try {
            RouteSpecificPool rospl = getRoutePool(route, true);
            rospl.dropEntry();
            if (rospl.isUnused()) {
                this.routeToPool.remove(route);
            }
            this.numConnections--;
            notifyWaitingThread(rospl);
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x003e A[Catch:{ all -> 0x0079 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyWaitingThread(org.shaded.apache.http.impl.conn.tsccm.RouteSpecificPool r6) {
        /*
            r5 = this;
            r1 = 0
            java.util.concurrent.locks.Lock r2 = r5.poolLock
            r2.lock()
            if (r6 == 0) goto L_0x0047
            boolean r2 = r6.hasThread()     // Catch:{ all -> 0x0079 }
            if (r2 == 0) goto L_0x0047
            org.shaded.apache.commons.logging.Log r2 = r5.log     // Catch:{ all -> 0x0079 }
            boolean r2 = r2.isDebugEnabled()     // Catch:{ all -> 0x0079 }
            if (r2 == 0) goto L_0x0038
            org.shaded.apache.commons.logging.Log r2 = r5.log     // Catch:{ all -> 0x0079 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0079 }
            r3.<init>()     // Catch:{ all -> 0x0079 }
            java.lang.String r4 = "Notifying thread waiting on pool ["
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0079 }
            org.shaded.apache.http.conn.routing.HttpRoute r4 = r6.getRoute()     // Catch:{ all -> 0x0079 }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0079 }
            java.lang.String r4 = "]"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0079 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0079 }
            r2.debug(r3)     // Catch:{ all -> 0x0079 }
        L_0x0038:
            org.shaded.apache.http.impl.conn.tsccm.WaitingThread r1 = r6.nextThread()     // Catch:{ all -> 0x0079 }
        L_0x003c:
            if (r1 == 0) goto L_0x0041
            r1.wakeup()     // Catch:{ all -> 0x0079 }
        L_0x0041:
            java.util.concurrent.locks.Lock r2 = r5.poolLock
            r2.unlock()
            return
        L_0x0047:
            java.util.Queue<org.shaded.apache.http.impl.conn.tsccm.WaitingThread> r2 = r5.waitingThreads     // Catch:{ all -> 0x0079 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0079 }
            if (r2 != 0) goto L_0x0069
            org.shaded.apache.commons.logging.Log r2 = r5.log     // Catch:{ all -> 0x0079 }
            boolean r2 = r2.isDebugEnabled()     // Catch:{ all -> 0x0079 }
            if (r2 == 0) goto L_0x005e
            org.shaded.apache.commons.logging.Log r2 = r5.log     // Catch:{ all -> 0x0079 }
            java.lang.String r3 = "Notifying thread waiting on any pool"
            r2.debug(r3)     // Catch:{ all -> 0x0079 }
        L_0x005e:
            java.util.Queue<org.shaded.apache.http.impl.conn.tsccm.WaitingThread> r2 = r5.waitingThreads     // Catch:{ all -> 0x0079 }
            java.lang.Object r2 = r2.remove()     // Catch:{ all -> 0x0079 }
            r0 = r2
            org.shaded.apache.http.impl.conn.tsccm.WaitingThread r0 = (org.shaded.apache.http.impl.conn.tsccm.WaitingThread) r0     // Catch:{ all -> 0x0079 }
            r1 = r0
            goto L_0x003c
        L_0x0069:
            org.shaded.apache.commons.logging.Log r2 = r5.log     // Catch:{ all -> 0x0079 }
            boolean r2 = r2.isDebugEnabled()     // Catch:{ all -> 0x0079 }
            if (r2 == 0) goto L_0x003c
            org.shaded.apache.commons.logging.Log r2 = r5.log     // Catch:{ all -> 0x0079 }
            java.lang.String r3 = "Notifying no-one, there are no waiting threads"
            r2.debug(r3)     // Catch:{ all -> 0x0079 }
            goto L_0x003c
        L_0x0079:
            r2 = move-exception
            java.util.concurrent.locks.Lock r3 = r5.poolLock
            r3.unlock()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.conn.tsccm.ConnPoolByRoute.notifyWaitingThread(org.shaded.apache.http.impl.conn.tsccm.RouteSpecificPool):void");
    }

    public void deleteClosedConnections() {
        this.poolLock.lock();
        try {
            Iterator<BasicPoolEntry> iter = this.freeConnections.iterator();
            while (iter.hasNext()) {
                BasicPoolEntry entry = iter.next();
                if (!entry.getConnection().isOpen()) {
                    iter.remove();
                    deleteEntry(entry);
                }
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    public void shutdown() {
        this.poolLock.lock();
        try {
            super.shutdown();
            Iterator<BasicPoolEntry> ibpe = this.freeConnections.iterator();
            while (ibpe.hasNext()) {
                BasicPoolEntry entry = ibpe.next();
                ibpe.remove();
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Closing connection [" + entry.getPlannedRoute() + "][" + entry.getState() + "]");
                }
                closeConnection(entry.getConnection());
            }
            Iterator<WaitingThread> iwth = this.waitingThreads.iterator();
            while (iwth.hasNext()) {
                iwth.remove();
                iwth.next().wakeup();
            }
            this.routeToPool.clear();
        } finally {
            this.poolLock.unlock();
        }
    }
}
