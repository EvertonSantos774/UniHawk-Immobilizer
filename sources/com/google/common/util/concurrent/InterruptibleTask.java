package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.j2objc.annotations.ReflectionSupport;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
@ReflectionSupport(ReflectionSupport.Level.FULL)
abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable DONE = new DoNothingRunnable();
    private static final Runnable INTERRUPTING = new DoNothingRunnable();
    private static final int MAX_BUSY_WAIT_SPINS = 1000;
    private static final Runnable PARKED = new DoNothingRunnable();

    /* access modifiers changed from: package-private */
    public abstract void afterRanInterruptibly(@NullableDecl T t, @NullableDecl Throwable th);

    /* access modifiers changed from: package-private */
    public abstract boolean isDone();

    /* access modifiers changed from: package-private */
    public abstract T runInterruptibly() throws Exception;

    /* access modifiers changed from: package-private */
    public abstract String toPendingString();

    InterruptibleTask() {
    }

    static {
        Class<LockSupport> cls = LockSupport.class;
    }

    private static final class DoNothingRunnable implements Runnable {
        private DoNothingRunnable() {
        }

        public void run() {
        }
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final void run() {
        /*
            r14 = this;
            r13 = 1000(0x3e8, float:1.401E-42)
            r8 = 1
            r9 = 0
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r10 = 0
            boolean r10 = r14.compareAndSet(r10, r0)
            if (r10 != 0) goto L_0x0010
        L_0x000f:
            return
        L_0x0010:
            boolean r10 = r14.isDone()
            if (r10 != 0) goto L_0x005c
            r4 = r8
        L_0x0017:
            r3 = 0
            r1 = 0
            if (r4 == 0) goto L_0x001f
            java.lang.Object r3 = r14.runInterruptibly()     // Catch:{ Throwable -> 0x006f, all -> 0x00c0 }
        L_0x001f:
            java.lang.Runnable r10 = DONE
            boolean r10 = r14.compareAndSet(r0, r10)
            if (r10 != 0) goto L_0x0069
            r2 = 0
            r5 = 0
            java.lang.Object r6 = r14.get()
            java.lang.Runnable r6 = (java.lang.Runnable) r6
        L_0x002f:
            java.lang.Runnable r10 = INTERRUPTING
            if (r6 == r10) goto L_0x0037
            java.lang.Runnable r10 = PARKED
            if (r6 != r10) goto L_0x0064
        L_0x0037:
            int r5 = r5 + 1
            if (r5 <= r13) goto L_0x0060
            java.lang.Runnable r10 = PARKED
            if (r6 == r10) goto L_0x0049
            java.lang.Runnable r10 = INTERRUPTING
            java.lang.Runnable r11 = PARKED
            boolean r10 = r14.compareAndSet(r10, r11)
            if (r10 == 0) goto L_0x0055
        L_0x0049:
            boolean r10 = java.lang.Thread.interrupted()
            if (r10 != 0) goto L_0x0051
            if (r2 == 0) goto L_0x005e
        L_0x0051:
            r2 = r8
        L_0x0052:
            java.util.concurrent.locks.LockSupport.park(r14)
        L_0x0055:
            java.lang.Object r6 = r14.get()
            java.lang.Runnable r6 = (java.lang.Runnable) r6
            goto L_0x002f
        L_0x005c:
            r4 = r9
            goto L_0x0017
        L_0x005e:
            r2 = r9
            goto L_0x0052
        L_0x0060:
            java.lang.Thread.yield()
            goto L_0x0055
        L_0x0064:
            if (r2 == 0) goto L_0x0069
            r0.interrupt()
        L_0x0069:
            if (r4 == 0) goto L_0x000f
            r14.afterRanInterruptibly(r3, r1)
            goto L_0x000f
        L_0x006f:
            r7 = move-exception
            r1 = r7
            java.lang.Runnable r10 = DONE
            boolean r10 = r14.compareAndSet(r0, r10)
            if (r10 != 0) goto L_0x00b9
            r2 = 0
            r5 = 0
            java.lang.Object r6 = r14.get()
            java.lang.Runnable r6 = (java.lang.Runnable) r6
        L_0x0081:
            java.lang.Runnable r10 = INTERRUPTING
            if (r6 == r10) goto L_0x0089
            java.lang.Runnable r10 = PARKED
            if (r6 != r10) goto L_0x00b4
        L_0x0089:
            int r5 = r5 + 1
            if (r5 <= r13) goto L_0x00b0
            java.lang.Runnable r10 = PARKED
            if (r6 == r10) goto L_0x009b
            java.lang.Runnable r10 = INTERRUPTING
            java.lang.Runnable r11 = PARKED
            boolean r10 = r14.compareAndSet(r10, r11)
            if (r10 == 0) goto L_0x00a7
        L_0x009b:
            boolean r10 = java.lang.Thread.interrupted()
            if (r10 != 0) goto L_0x00a3
            if (r2 == 0) goto L_0x00ae
        L_0x00a3:
            r2 = r8
        L_0x00a4:
            java.util.concurrent.locks.LockSupport.park(r14)
        L_0x00a7:
            java.lang.Object r6 = r14.get()
            java.lang.Runnable r6 = (java.lang.Runnable) r6
            goto L_0x0081
        L_0x00ae:
            r2 = r9
            goto L_0x00a4
        L_0x00b0:
            java.lang.Thread.yield()
            goto L_0x00a7
        L_0x00b4:
            if (r2 == 0) goto L_0x00b9
            r0.interrupt()
        L_0x00b9:
            if (r4 == 0) goto L_0x000f
            r14.afterRanInterruptibly(r3, r1)
            goto L_0x000f
        L_0x00c0:
            r10 = move-exception
            java.lang.Runnable r11 = DONE
            boolean r11 = r14.compareAndSet(r0, r11)
            if (r11 != 0) goto L_0x0109
            r2 = 0
            r5 = 0
            java.lang.Object r6 = r14.get()
            java.lang.Runnable r6 = (java.lang.Runnable) r6
        L_0x00d1:
            java.lang.Runnable r11 = INTERRUPTING
            if (r6 == r11) goto L_0x00d9
            java.lang.Runnable r11 = PARKED
            if (r6 != r11) goto L_0x0104
        L_0x00d9:
            int r5 = r5 + 1
            if (r5 <= r13) goto L_0x0100
            java.lang.Runnable r11 = PARKED
            if (r6 == r11) goto L_0x00eb
            java.lang.Runnable r11 = INTERRUPTING
            java.lang.Runnable r12 = PARKED
            boolean r11 = r14.compareAndSet(r11, r12)
            if (r11 == 0) goto L_0x00f7
        L_0x00eb:
            boolean r11 = java.lang.Thread.interrupted()
            if (r11 != 0) goto L_0x00f3
            if (r2 == 0) goto L_0x00fe
        L_0x00f3:
            r2 = r8
        L_0x00f4:
            java.util.concurrent.locks.LockSupport.park(r14)
        L_0x00f7:
            java.lang.Object r6 = r14.get()
            java.lang.Runnable r6 = (java.lang.Runnable) r6
            goto L_0x00d1
        L_0x00fe:
            r2 = r9
            goto L_0x00f4
        L_0x0100:
            java.lang.Thread.yield()
            goto L_0x00f7
        L_0x0104:
            if (r2 == 0) goto L_0x0109
            r0.interrupt()
        L_0x0109:
            if (r4 == 0) goto L_0x010e
            r14.afterRanInterruptibly(r3, r1)
        L_0x010e:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.InterruptibleTask.run():void");
    }

    /* access modifiers changed from: package-private */
    public final void interruptTask() {
        Runnable currentRunner = (Runnable) get();
        if ((currentRunner instanceof Thread) && compareAndSet(currentRunner, INTERRUPTING)) {
            try {
                ((Thread) currentRunner).interrupt();
            } finally {
                if (((Runnable) getAndSet(DONE)) == PARKED) {
                    LockSupport.unpark((Thread) currentRunner);
                }
            }
        }
    }

    public final String toString() {
        String result;
        Runnable state = (Runnable) get();
        if (state == DONE) {
            result = "running=[DONE]";
        } else if (state == INTERRUPTING) {
            result = "running=[INTERRUPTED]";
        } else if (state instanceof Thread) {
            result = "running=[RUNNING ON " + ((Thread) state).getName() + "]";
        } else {
            result = "running=[NOT STARTED YET]";
        }
        return result + ", " + toPendingString();
    }
}
