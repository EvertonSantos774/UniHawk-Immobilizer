package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
@Beta
public final class Monitor {
    @GuardedBy("lock")
    private Guard activeGuards;
    private final boolean fair;
    /* access modifiers changed from: private */
    public final ReentrantLock lock;

    @Beta
    public static abstract class Guard {
        final Condition condition;
        @Weak
        final Monitor monitor;
        @NullableDecl
        @GuardedBy("monitor.lock")
        Guard next;
        @GuardedBy("monitor.lock")
        int waiterCount = 0;

        public abstract boolean isSatisfied();

        protected Guard(Monitor monitor2) {
            this.monitor = (Monitor) Preconditions.checkNotNull(monitor2, "monitor");
            this.condition = monitor2.lock.newCondition();
        }
    }

    public Monitor() {
        this(false);
    }

    public Monitor(boolean fair2) {
        this.activeGuards = null;
        this.fair = fair2;
        this.lock = new ReentrantLock(fair2);
    }

    public void enter() {
        this.lock.lock();
    }

    public boolean enter(long time, TimeUnit unit) {
        long startTime;
        long remainingNanos;
        boolean tryLock;
        long timeoutNanos = toSafeNanos(time, unit);
        ReentrantLock lock2 = this.lock;
        if (!this.fair && lock2.tryLock()) {
            return true;
        }
        boolean interrupted = Thread.interrupted();
        try {
            startTime = System.nanoTime();
            remainingNanos = timeoutNanos;
            while (true) {
                tryLock = lock2.tryLock(remainingNanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (!interrupted) {
                return tryLock;
            }
            Thread.currentThread().interrupt();
            return tryLock;
        } catch (InterruptedException e) {
            interrupted = true;
            remainingNanos = remainingNanos(startTime, timeoutNanos);
        } catch (Throwable th) {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    public boolean enterInterruptibly(long time, TimeUnit unit) throws InterruptedException {
        return this.lock.tryLock(time, unit);
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock2 = this.lock;
        boolean signalBeforeWaiting = lock2.isHeldByCurrentThread();
        lock2.lockInterruptibly();
        boolean satisfied = false;
        try {
            if (!guard.isSatisfied()) {
                await(guard, signalBeforeWaiting);
            }
            satisfied = true;
        } finally {
            if (!satisfied) {
                leave();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        if (r0.tryLock() != false) goto L_0x002c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0044 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enterWhen(com.google.common.util.concurrent.Monitor.Guard r11, long r12, java.util.concurrent.TimeUnit r14) throws java.lang.InterruptedException {
        /*
            r10 = this;
            long r6 = toSafeNanos(r12, r14)
            com.google.common.util.concurrent.Monitor r8 = r11.monitor
            if (r8 == r10) goto L_0x000e
            java.lang.IllegalMonitorStateException r8 = new java.lang.IllegalMonitorStateException
            r8.<init>()
            throw r8
        L_0x000e:
            java.util.concurrent.locks.ReentrantLock r0 = r10.lock
            boolean r1 = r0.isHeldByCurrentThread()
            r4 = 0
            boolean r8 = r10.fair
            if (r8 != 0) goto L_0x004f
            boolean r8 = java.lang.Thread.interrupted()
            if (r8 == 0) goto L_0x0026
            java.lang.InterruptedException r8 = new java.lang.InterruptedException
            r8.<init>()
            throw r8
        L_0x0026:
            boolean r8 = r0.tryLock()
            if (r8 == 0) goto L_0x004f
        L_0x002c:
            r2 = 0
            r3 = 1
            boolean r8 = r11.isSatisfied()     // Catch:{ all -> 0x0067 }
            if (r8 != 0) goto L_0x0040
            r8 = 0
            int r8 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r8 != 0) goto L_0x005b
        L_0x003a:
            boolean r8 = r10.awaitNanos(r11, r6, r1)     // Catch:{ all -> 0x0067 }
            if (r8 == 0) goto L_0x0060
        L_0x0040:
            r2 = 1
        L_0x0041:
            r3 = 0
            if (r2 != 0) goto L_0x004e
            if (r3 == 0) goto L_0x004b
            if (r1 != 0) goto L_0x004b
            r10.signalNextWaiter()     // Catch:{ all -> 0x0062 }
        L_0x004b:
            r0.unlock()
        L_0x004e:
            return r2
        L_0x004f:
            long r4 = initNanoTime(r6)
            boolean r8 = r0.tryLock(r12, r14)
            if (r8 != 0) goto L_0x002c
            r2 = 0
            goto L_0x004e
        L_0x005b:
            long r6 = remainingNanos(r4, r6)     // Catch:{ all -> 0x0067 }
            goto L_0x003a
        L_0x0060:
            r2 = 0
            goto L_0x0041
        L_0x0062:
            r8 = move-exception
            r0.unlock()
            throw r8
        L_0x0067:
            r8 = move-exception
            if (r2 != 0) goto L_0x0074
            if (r3 == 0) goto L_0x0071
            if (r1 != 0) goto L_0x0071
            r10.signalNextWaiter()     // Catch:{ all -> 0x0075 }
        L_0x0071:
            r0.unlock()
        L_0x0074:
            throw r8
        L_0x0075:
            r8 = move-exception
            r0.unlock()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterWhen(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock2 = this.lock;
        boolean signalBeforeWaiting = lock2.isHeldByCurrentThread();
        lock2.lock();
        boolean satisfied = false;
        try {
            if (!guard.isSatisfied()) {
                awaitUninterruptibly(guard, signalBeforeWaiting);
            }
            satisfied = true;
        } finally {
            if (!satisfied) {
                leave();
            }
        }
    }

    public boolean enterWhenUninterruptibly(Guard guard, long time, TimeUnit unit) {
        boolean satisfied;
        long remainingNanos;
        long remainingNanos2;
        long timeoutNanos = toSafeNanos(time, unit);
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock2 = this.lock;
        long startTime = 0;
        boolean signalBeforeWaiting = lock2.isHeldByCurrentThread();
        boolean interrupted = Thread.interrupted();
        try {
            if (this.fair || !lock2.tryLock()) {
                startTime = initNanoTime(timeoutNanos);
                remainingNanos2 = timeoutNanos;
                while (true) {
                    break;
                }
                if (!lock2.tryLock(remainingNanos2, TimeUnit.NANOSECONDS)) {
                    satisfied = false;
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    return satisfied;
                }
            }
            while (!guard.isSatisfied()) {
                if (startTime == 0) {
                    long startTime2 = initNanoTime(timeoutNanos);
                    remainingNanos = timeoutNanos;
                } else {
                    remainingNanos = remainingNanos(startTime, timeoutNanos);
                }
                satisfied = awaitNanos(guard, remainingNanos, signalBeforeWaiting);
            }
            satisfied = true;
            if (!satisfied) {
                lock2.unlock();
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            return satisfied;
        } catch (InterruptedException e) {
            interrupted = true;
            signalBeforeWaiting = false;
        } catch (InterruptedException e2) {
            interrupted = true;
            remainingNanos2 = remainingNanos(startTime, timeoutNanos);
        } catch (Throwable th) {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }

    public boolean enterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        boolean satisfied = false;
        try {
            satisfied = guard.isSatisfied();
            return satisfied;
        } finally {
            if (!satisfied) {
                lock2.unlock();
            }
        }
    }

    public boolean enterIf(Guard guard, long time, TimeUnit unit) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        } else if (!enter(time, unit)) {
            return false;
        } else {
            boolean satisfied = false;
            try {
                satisfied = guard.isSatisfied();
            } finally {
                if (!satisfied) {
                    this.lock.unlock();
                }
            }
        }
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock2 = this.lock;
        lock2.lockInterruptibly();
        boolean satisfied = false;
        try {
            satisfied = guard.isSatisfied();
            return satisfied;
        } finally {
            if (!satisfied) {
                lock2.unlock();
            }
        }
    }

    public boolean enterIfInterruptibly(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock2 = this.lock;
        if (!lock2.tryLock(time, unit)) {
            return false;
        }
        boolean satisfied = false;
        try {
            satisfied = guard.isSatisfied();
        } finally {
            if (!satisfied) {
                lock2.unlock();
            }
        }
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock2 = this.lock;
        if (!lock2.tryLock()) {
            return false;
        }
        boolean satisfied = false;
        try {
            satisfied = guard.isSatisfied();
        } finally {
            if (!satisfied) {
                lock2.unlock();
            }
        }
    }

    public void waitFor(Guard guard) throws InterruptedException {
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            await(guard, true);
        }
    }

    public boolean waitFor(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        long timeoutNanos = toSafeNanos(time, unit);
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (guard.isSatisfied()) {
            return true;
        } else {
            if (!Thread.interrupted()) {
                return awaitNanos(guard, timeoutNanos, true);
            }
            throw new InterruptedException();
        }
    }

    public void waitForUninterruptibly(Guard guard) {
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            awaitUninterruptibly(guard, true);
        }
    }

    public boolean waitForUninterruptibly(Guard guard, long time, TimeUnit unit) {
        long timeoutNanos = toSafeNanos(time, unit);
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (guard.isSatisfied()) {
            return true;
        } else {
            boolean signalBeforeWaiting = true;
            long startTime = initNanoTime(timeoutNanos);
            boolean interrupted = Thread.interrupted();
            long remainingNanos = timeoutNanos;
            while (true) {
                try {
                    boolean awaitNanos = awaitNanos(guard, remainingNanos, signalBeforeWaiting);
                    if (!interrupted) {
                        return awaitNanos;
                    }
                    Thread.currentThread().interrupt();
                    return awaitNanos;
                } catch (InterruptedException e) {
                    interrupted = true;
                    if (!guard.isSatisfied()) {
                        signalBeforeWaiting = false;
                        remainingNanos = remainingNanos(startTime, timeoutNanos);
                    } else if (1 == 0) {
                        return true;
                    } else {
                        Thread.currentThread().interrupt();
                        return true;
                    }
                } catch (Throwable th) {
                    if (1 != 0) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
        }
    }

    public void leave() {
        ReentrantLock lock2 = this.lock;
        try {
            if (lock2.getHoldCount() == 1) {
                signalNextWaiter();
            }
        } finally {
            lock2.unlock();
        }
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        this.lock.lock();
        try {
            return guard.waiterCount;
        } finally {
            this.lock.unlock();
        }
    }

    private static long toSafeNanos(long time, TimeUnit unit) {
        long timeoutNanos = unit.toNanos(time);
        if (timeoutNanos <= 0) {
            return 0;
        }
        if (timeoutNanos > 6917529027641081853L) {
            return 6917529027641081853L;
        }
        return timeoutNanos;
    }

    private static long initNanoTime(long timeoutNanos) {
        if (timeoutNanos <= 0) {
            return 0;
        }
        long startTime = System.nanoTime();
        if (startTime == 0) {
            return 1;
        }
        return startTime;
    }

    private static long remainingNanos(long startTime, long timeoutNanos) {
        if (timeoutNanos <= 0) {
            return 0;
        }
        return timeoutNanos - (System.nanoTime() - startTime);
    }

    @GuardedBy("lock")
    private void signalNextWaiter() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            if (isSatisfied(guard)) {
                guard.condition.signal();
                return;
            }
        }
    }

    @GuardedBy("lock")
    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable throwable) {
            signalAllWaiters();
            throw throwable;
        }
    }

    @GuardedBy("lock")
    private void signalAllWaiters() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            guard.condition.signalAll();
        }
    }

    @GuardedBy("lock")
    private void beginWaitingFor(Guard guard) {
        int waiters = guard.waiterCount;
        guard.waiterCount = waiters + 1;
        if (waiters == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    @GuardedBy("lock")
    private void endWaitingFor(Guard guard) {
        int waiters = guard.waiterCount - 1;
        guard.waiterCount = waiters;
        if (waiters == 0) {
            Guard p = this.activeGuards;
            Guard pred = null;
            while (p != guard) {
                pred = p;
                p = p.next;
            }
            if (pred == null) {
                this.activeGuards = p.next;
            } else {
                pred.next = p.next;
            }
            p.next = null;
        }
    }

    @GuardedBy("lock")
    private void await(Guard guard, boolean signalBeforeWaiting) throws InterruptedException {
        if (signalBeforeWaiting) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.await();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    @GuardedBy("lock")
    private void awaitUninterruptibly(Guard guard, boolean signalBeforeWaiting) {
        if (signalBeforeWaiting) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.awaitUninterruptibly();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    @GuardedBy("lock")
    private boolean awaitNanos(Guard guard, long nanos, boolean signalBeforeWaiting) throws InterruptedException {
        boolean z;
        boolean firstTime = true;
        while (true) {
            if (nanos <= 0) {
                z = false;
                if (!firstTime) {
                    endWaitingFor(guard);
                }
            } else {
                if (firstTime) {
                    if (signalBeforeWaiting) {
                        try {
                            signalNextWaiter();
                        } catch (Throwable th) {
                            if (!firstTime) {
                                endWaitingFor(guard);
                            }
                            throw th;
                        }
                    }
                    beginWaitingFor(guard);
                    firstTime = false;
                }
                nanos = guard.condition.awaitNanos(nanos);
                if (guard.isSatisfied()) {
                    z = true;
                    if (!firstTime) {
                        endWaitingFor(guard);
                    }
                }
            }
        }
        return z;
    }
}
