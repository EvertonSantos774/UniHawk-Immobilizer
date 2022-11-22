package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

@GwtIncompatible
final class SequentialExecutor implements Executor {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(SequentialExecutor.class.getName());
    private final Executor executor;
    /* access modifiers changed from: private */
    @GuardedBy("queue")
    public final Deque<Runnable> queue = new ArrayDeque();
    private final QueueWorker worker = new QueueWorker();
    @GuardedBy("queue")
    private long workerRunCount = 0;
    /* access modifiers changed from: private */
    @GuardedBy("queue")
    public WorkerRunningState workerRunningState = WorkerRunningState.IDLE;

    enum WorkerRunningState {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    static /* synthetic */ long access$308(SequentialExecutor x0) {
        long j = x0.workerRunCount;
        x0.workerRunCount = 1 + j;
        return j;
    }

    SequentialExecutor(Executor executor2) {
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r10.executor.execute(r10.worker);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        if (r10.workerRunningState == com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        if (r0 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        r7 = r10.queue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003d, code lost:
        monitor-enter(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0042, code lost:
        if (r10.workerRunCount != r2) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0048, code lost:
        if (r10.workerRunningState != com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004a, code lost:
        r10.workerRunningState = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004e, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0056, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0059, code lost:
        monitor-enter(r10.queue);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005e, code lost:
        if (r10.workerRunningState == com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x006e, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0071, code lost:
        if ((r5 instanceof java.util.concurrent.RejectedExecutionException) == false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0075, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0079, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x007d, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(final java.lang.Runnable r11) {
        /*
            r10 = this;
            r0 = 1
            r6 = 0
            com.google.common.base.Preconditions.checkNotNull(r11)
            java.util.Deque<java.lang.Runnable> r7 = r10.queue
            monitor-enter(r7)
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r8 = r10.workerRunningState     // Catch:{ all -> 0x0053 }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r9 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch:{ all -> 0x0053 }
            if (r8 == r9) goto L_0x0014
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r8 = r10.workerRunningState     // Catch:{ all -> 0x0053 }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r9 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUED     // Catch:{ all -> 0x0053 }
            if (r8 != r9) goto L_0x001b
        L_0x0014:
            java.util.Deque<java.lang.Runnable> r6 = r10.queue     // Catch:{ all -> 0x0053 }
            r6.add(r11)     // Catch:{ all -> 0x0053 }
            monitor-exit(r7)     // Catch:{ all -> 0x0053 }
        L_0x001a:
            return
        L_0x001b:
            long r2 = r10.workerRunCount     // Catch:{ all -> 0x0053 }
            com.google.common.util.concurrent.SequentialExecutor$1 r4 = new com.google.common.util.concurrent.SequentialExecutor$1     // Catch:{ all -> 0x0053 }
            r4.<init>(r11)     // Catch:{ all -> 0x0053 }
            java.util.Deque<java.lang.Runnable> r8 = r10.queue     // Catch:{ all -> 0x0053 }
            r8.add(r4)     // Catch:{ all -> 0x0053 }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r8 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING     // Catch:{ all -> 0x0053 }
            r10.workerRunningState = r8     // Catch:{ all -> 0x0053 }
            monitor-exit(r7)     // Catch:{ all -> 0x0053 }
            java.util.concurrent.Executor r7 = r10.executor     // Catch:{ RuntimeException -> 0x0056, Error -> 0x007f }
            com.google.common.util.concurrent.SequentialExecutor$QueueWorker r8 = r10.worker     // Catch:{ RuntimeException -> 0x0056, Error -> 0x007f }
            r7.execute(r8)     // Catch:{ RuntimeException -> 0x0056, Error -> 0x007f }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r7 = r10.workerRunningState
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r8 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING
            if (r7 == r8) goto L_0x007d
        L_0x0039:
            if (r0 != 0) goto L_0x001a
            java.util.Deque<java.lang.Runnable> r7 = r10.queue
            monitor-enter(r7)
            long r8 = r10.workerRunCount     // Catch:{ all -> 0x0050 }
            int r6 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x004e
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r6 = r10.workerRunningState     // Catch:{ all -> 0x0050 }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r8 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING     // Catch:{ all -> 0x0050 }
            if (r6 != r8) goto L_0x004e
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r6 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUED     // Catch:{ all -> 0x0050 }
            r10.workerRunningState = r6     // Catch:{ all -> 0x0050 }
        L_0x004e:
            monitor-exit(r7)     // Catch:{ all -> 0x0050 }
            goto L_0x001a
        L_0x0050:
            r6 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0050 }
            throw r6
        L_0x0053:
            r6 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0053 }
            throw r6
        L_0x0056:
            r5 = move-exception
        L_0x0057:
            java.util.Deque<java.lang.Runnable> r7 = r10.queue
            monitor-enter(r7)
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r8 = r10.workerRunningState     // Catch:{ all -> 0x0076 }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r9 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE     // Catch:{ all -> 0x0076 }
            if (r8 == r9) goto L_0x0066
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r8 = r10.workerRunningState     // Catch:{ all -> 0x0076 }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r9 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING     // Catch:{ all -> 0x0076 }
            if (r8 != r9) goto L_0x0079
        L_0x0066:
            java.util.Deque<java.lang.Runnable> r8 = r10.queue     // Catch:{ all -> 0x0076 }
            boolean r8 = r8.removeLastOccurrence(r4)     // Catch:{ all -> 0x0076 }
            if (r8 == 0) goto L_0x0079
            r1 = r0
        L_0x006f:
            boolean r6 = r5 instanceof java.util.concurrent.RejectedExecutionException     // Catch:{ all -> 0x0076 }
            if (r6 == 0) goto L_0x0075
            if (r1 == 0) goto L_0x007b
        L_0x0075:
            throw r5     // Catch:{ all -> 0x0076 }
        L_0x0076:
            r6 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0076 }
            throw r6
        L_0x0079:
            r1 = r6
            goto L_0x006f
        L_0x007b:
            monitor-exit(r7)     // Catch:{ all -> 0x0076 }
            goto L_0x001a
        L_0x007d:
            r0 = r6
            goto L_0x0039
        L_0x007f:
            r5 = move-exception
            goto L_0x0057
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.execute(java.lang.Runnable):void");
    }

    private final class QueueWorker implements Runnable {
        private QueueWorker() {
        }

        public void run() {
            try {
                workOnQueue();
            } catch (Error e) {
                synchronized (SequentialExecutor.this.queue) {
                    WorkerRunningState unused = SequentialExecutor.this.workerRunningState = WorkerRunningState.IDLE;
                    throw e;
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
            if (r2 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0045, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
            r2 = r2 | java.lang.Thread.interrupted();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            r3.run();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
            if (r2 == false) goto L_?;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void workOnQueue() {
            /*
                r8 = this;
                r2 = 0
                r1 = 0
            L_0x0002:
                com.google.common.util.concurrent.SequentialExecutor r4 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0075 }
                java.util.Deque r5 = r4.queue     // Catch:{ all -> 0x0075 }
                monitor-enter(r5)     // Catch:{ all -> 0x0075 }
                if (r1 != 0) goto L_0x002d
                com.google.common.util.concurrent.SequentialExecutor r4 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0080 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r4 = r4.workerRunningState     // Catch:{ all -> 0x0080 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r6 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch:{ all -> 0x0080 }
                if (r4 != r6) goto L_0x0020
                monitor-exit(r5)     // Catch:{ all -> 0x0080 }
                if (r2 == 0) goto L_0x001f
                java.lang.Thread r4 = java.lang.Thread.currentThread()
                r4.interrupt()
            L_0x001f:
                return
            L_0x0020:
                com.google.common.util.concurrent.SequentialExecutor r4 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0080 }
                com.google.common.util.concurrent.SequentialExecutor.access$308(r4)     // Catch:{ all -> 0x0080 }
                com.google.common.util.concurrent.SequentialExecutor r4 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0080 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r6 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch:{ all -> 0x0080 }
                com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState unused = r4.workerRunningState = r6     // Catch:{ all -> 0x0080 }
                r1 = 1
            L_0x002d:
                com.google.common.util.concurrent.SequentialExecutor r4 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0080 }
                java.util.Deque r4 = r4.queue     // Catch:{ all -> 0x0080 }
                java.lang.Object r3 = r4.poll()     // Catch:{ all -> 0x0080 }
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch:{ all -> 0x0080 }
                if (r3 != 0) goto L_0x004d
                com.google.common.util.concurrent.SequentialExecutor r4 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0080 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r6 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE     // Catch:{ all -> 0x0080 }
                com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState unused = r4.workerRunningState = r6     // Catch:{ all -> 0x0080 }
                monitor-exit(r5)     // Catch:{ all -> 0x0080 }
                if (r2 == 0) goto L_0x001f
                java.lang.Thread r4 = java.lang.Thread.currentThread()
                r4.interrupt()
                goto L_0x001f
            L_0x004d:
                monitor-exit(r5)     // Catch:{ all -> 0x0080 }
                boolean r4 = java.lang.Thread.interrupted()     // Catch:{ all -> 0x0075 }
                r2 = r2 | r4
                r3.run()     // Catch:{ RuntimeException -> 0x0057 }
                goto L_0x0002
            L_0x0057:
                r0 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.SequentialExecutor.log     // Catch:{ all -> 0x0075 }
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x0075 }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0075 }
                r6.<init>()     // Catch:{ all -> 0x0075 }
                java.lang.String r7 = "Exception while executing runnable "
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0075 }
                java.lang.StringBuilder r6 = r6.append(r3)     // Catch:{ all -> 0x0075 }
                java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0075 }
                r4.log(r5, r6, r0)     // Catch:{ all -> 0x0075 }
                goto L_0x0002
            L_0x0075:
                r4 = move-exception
                if (r2 == 0) goto L_0x007f
                java.lang.Thread r5 = java.lang.Thread.currentThread()
                r5.interrupt()
            L_0x007f:
                throw r4
            L_0x0080:
                r4 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x0080 }
                throw r4     // Catch:{ all -> 0x0075 }
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.QueueWorker.workOnQueue():void");
        }
    }
}
