package com.google.common.util.concurrent;

import androidx.core.app.NotificationCompat;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtIncompatible
final class ListenerCallQueue<L> {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final List<PerListenerQueue<L>> listeners = Collections.synchronizedList(new ArrayList());

    interface Event<L> {
        void call(L l);
    }

    ListenerCallQueue() {
    }

    public void addListener(L listener, Executor executor) {
        Preconditions.checkNotNull(listener, "listener");
        Preconditions.checkNotNull(executor, "executor");
        this.listeners.add(new PerListenerQueue(listener, executor));
    }

    public void enqueue(Event<L> event) {
        enqueueHelper(event, event);
    }

    public void enqueue(Event<L> event, String label) {
        enqueueHelper(event, label);
    }

    private void enqueueHelper(Event<L> event, Object label) {
        Preconditions.checkNotNull(event, NotificationCompat.CATEGORY_EVENT);
        Preconditions.checkNotNull(label, "label");
        synchronized (this.listeners) {
            for (PerListenerQueue<L> queue : this.listeners) {
                queue.add(event, label);
            }
        }
    }

    public void dispatch() {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).dispatch();
        }
    }

    private static final class PerListenerQueue<L> implements Runnable {
        final Executor executor;
        @GuardedBy("this")
        boolean isThreadScheduled;
        @GuardedBy("this")
        final Queue<Object> labelQueue = Queues.newArrayDeque();
        final L listener;
        @GuardedBy("this")
        final Queue<Event<L>> waitQueue = Queues.newArrayDeque();

        PerListenerQueue(L listener2, Executor executor2) {
            this.listener = Preconditions.checkNotNull(listener2);
            this.executor = (Executor) Preconditions.checkNotNull(executor2);
        }

        /* access modifiers changed from: package-private */
        public synchronized void add(Event<L> event, Object label) {
            this.waitQueue.add(event);
            this.labelQueue.add(label);
        }

        /* access modifiers changed from: package-private */
        public void dispatch() {
            boolean scheduleEventRunner = false;
            synchronized (this) {
                if (!this.isThreadScheduled) {
                    this.isThreadScheduled = true;
                    scheduleEventRunner = true;
                }
            }
            if (scheduleEventRunner) {
                try {
                    this.executor.execute(this);
                } catch (RuntimeException e) {
                    synchronized (this) {
                        this.isThreadScheduled = false;
                        ListenerCallQueue.logger.log(Level.SEVERE, "Exception while running callbacks for " + this.listener + " on " + this.executor, e);
                        throw e;
                    }
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
            r8.isThreadScheduled = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
            monitor-exit(r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
            r2.call(r8.listener);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
            if (0 == 0) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
            monitor-enter(r8);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r8 = this;
                r3 = 1
            L_0x0001:
                monitor-enter(r8)     // Catch:{ all -> 0x0055 }
                boolean r4 = r8.isThreadScheduled     // Catch:{ all -> 0x005e }
                com.google.common.base.Preconditions.checkState(r4)     // Catch:{ all -> 0x005e }
                java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Event<L>> r4 = r8.waitQueue     // Catch:{ all -> 0x005e }
                java.lang.Object r2 = r4.poll()     // Catch:{ all -> 0x005e }
                com.google.common.util.concurrent.ListenerCallQueue$Event r2 = (com.google.common.util.concurrent.ListenerCallQueue.Event) r2     // Catch:{ all -> 0x005e }
                java.util.Queue<java.lang.Object> r4 = r8.labelQueue     // Catch:{ all -> 0x005e }
                java.lang.Object r1 = r4.poll()     // Catch:{ all -> 0x005e }
                if (r2 != 0) goto L_0x0024
                r4 = 0
                r8.isThreadScheduled = r4     // Catch:{ all -> 0x005e }
                r3 = 0
                monitor-exit(r8)     // Catch:{ all -> 0x005e }
                if (r3 == 0) goto L_0x0023
                monitor-enter(r8)
                r4 = 0
                r8.isThreadScheduled = r4     // Catch:{ all -> 0x0061 }
                monitor-exit(r8)     // Catch:{ all -> 0x0061 }
            L_0x0023:
                return
            L_0x0024:
                monitor-exit(r8)     // Catch:{ all -> 0x005e }
                L r4 = r8.listener     // Catch:{ RuntimeException -> 0x002b }
                r2.call(r4)     // Catch:{ RuntimeException -> 0x002b }
                goto L_0x0001
            L_0x002b:
                r0 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.ListenerCallQueue.logger     // Catch:{ all -> 0x0055 }
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x0055 }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0055 }
                r6.<init>()     // Catch:{ all -> 0x0055 }
                java.lang.String r7 = "Exception while executing callback: "
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0055 }
                L r7 = r8.listener     // Catch:{ all -> 0x0055 }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0055 }
                java.lang.String r7 = " "
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0055 }
                java.lang.StringBuilder r6 = r6.append(r1)     // Catch:{ all -> 0x0055 }
                java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0055 }
                r4.log(r5, r6, r0)     // Catch:{ all -> 0x0055 }
                goto L_0x0001
            L_0x0055:
                r4 = move-exception
                if (r3 == 0) goto L_0x005d
                monitor-enter(r8)
                r5 = 0
                r8.isThreadScheduled = r5     // Catch:{ all -> 0x0064 }
                monitor-exit(r8)     // Catch:{ all -> 0x0064 }
            L_0x005d:
                throw r4
            L_0x005e:
                r4 = move-exception
                monitor-exit(r8)     // Catch:{ all -> 0x005e }
                throw r4     // Catch:{ all -> 0x0055 }
            L_0x0061:
                r4 = move-exception
                monitor-exit(r8)     // Catch:{ all -> 0x0061 }
                throw r4
            L_0x0064:
                r4 = move-exception
                monitor-exit(r8)     // Catch:{ all -> 0x0064 }
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.PerListenerQueue.run():void");
        }
    }
}
