package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.common.util.concurrent.internal.InternalFutures;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.j2objc.annotations.ReflectionSupport;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import sun.misc.Unsafe;

@GwtCompatible(emulated = true)
@ReflectionSupport(ReflectionSupport.Level.FULL)
public abstract class AbstractFuture<V> extends InternalFutureFailureAccess implements ListenableFuture<V> {
    /* access modifiers changed from: private */
    public static final AtomicHelper ATOMIC_HELPER;
    /* access modifiers changed from: private */
    public static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    private static final Object NULL = new Object();
    private static final long SPIN_THRESHOLD_NANOS = 1000;
    private static final Logger log = Logger.getLogger(AbstractFuture.class.getName());
    /* access modifiers changed from: private */
    @NullableDecl
    public volatile Listener listeners;
    /* access modifiers changed from: private */
    @NullableDecl
    public volatile Object value;
    /* access modifiers changed from: private */
    @NullableDecl
    public volatile Waiter waiters;

    interface Trusted<V> extends ListenableFuture<V> {
    }

    static {
        AtomicHelper helper;
        Throwable thrownUnsafeFailure = null;
        Throwable thrownAtomicReferenceFieldUpdaterFailure = null;
        try {
            helper = new UnsafeAtomicHelper();
        } catch (Throwable atomicReferenceFieldUpdaterFailure) {
            thrownAtomicReferenceFieldUpdaterFailure = atomicReferenceFieldUpdaterFailure;
            helper = new SynchronizedHelper();
        }
        ATOMIC_HELPER = helper;
        Class<LockSupport> cls = LockSupport.class;
        if (thrownAtomicReferenceFieldUpdaterFailure != null) {
            log.log(Level.SEVERE, "UnsafeAtomicHelper is broken!", thrownUnsafeFailure);
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", thrownAtomicReferenceFieldUpdaterFailure);
        }
    }

    static abstract class TrustedFuture<V> extends AbstractFuture<V> implements Trusted<V> {
        TrustedFuture() {
        }

        @CanIgnoreReturnValue
        public final V get() throws InterruptedException, ExecutionException {
            return AbstractFuture.super.get();
        }

        @CanIgnoreReturnValue
        public final V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return AbstractFuture.super.get(timeout, unit);
        }

        public final boolean isDone() {
            return AbstractFuture.super.isDone();
        }

        public final boolean isCancelled() {
            return AbstractFuture.super.isCancelled();
        }

        public final void addListener(Runnable listener, Executor executor) {
            AbstractFuture.super.addListener(listener, executor);
        }

        @CanIgnoreReturnValue
        public final boolean cancel(boolean mayInterruptIfRunning) {
            return AbstractFuture.super.cancel(mayInterruptIfRunning);
        }
    }

    private static final class Waiter {
        static final Waiter TOMBSTONE = new Waiter(AbstractFuture.GENERATE_CANCELLATION_CAUSES);
        @NullableDecl
        volatile Waiter next;
        @NullableDecl
        volatile Thread thread;

        Waiter(boolean unused) {
        }

        Waiter() {
            AbstractFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
        }

        /* access modifiers changed from: package-private */
        public void setNext(Waiter next2) {
            AbstractFuture.ATOMIC_HELPER.putNext(this, next2);
        }

        /* access modifiers changed from: package-private */
        public void unpark() {
            Thread w = this.thread;
            if (w != null) {
                this.thread = null;
                LockSupport.unpark(w);
            }
        }
    }

    private void removeWaiter(Waiter node) {
        node.thread = null;
        while (true) {
            Waiter pred = null;
            Waiter curr = this.waiters;
            if (curr != Waiter.TOMBSTONE) {
                while (curr != null) {
                    Waiter succ = curr.next;
                    if (curr.thread != null) {
                        pred = curr;
                    } else if (pred != null) {
                        pred.next = succ;
                        if (pred.thread == null) {
                        }
                    } else if (!ATOMIC_HELPER.casWaiters(this, curr, succ)) {
                    }
                    curr = succ;
                }
                return;
            }
            return;
        }
    }

    private static final class Listener {
        static final Listener TOMBSTONE = new Listener((Runnable) null, (Executor) null);
        final Executor executor;
        @NullableDecl
        Listener next;
        final Runnable task;

        Listener(Runnable task2, Executor executor2) {
            this.task = task2;
            this.executor = executor2;
        }
    }

    private static final class Failure {
        static final Failure FALLBACK_INSTANCE = new Failure(new Throwable("Failure occurred while trying to finish a future.") {
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable exception;

        Failure(Throwable exception2) {
            this.exception = (Throwable) Preconditions.checkNotNull(exception2);
        }
    }

    private static final class Cancellation {
        static final Cancellation CAUSELESS_CANCELLED;
        static final Cancellation CAUSELESS_INTERRUPTED;
        @NullableDecl
        final Throwable cause;
        final boolean wasInterrupted;

        static {
            if (AbstractFuture.GENERATE_CANCELLATION_CAUSES) {
                CAUSELESS_CANCELLED = null;
                CAUSELESS_INTERRUPTED = null;
                return;
            }
            CAUSELESS_CANCELLED = new Cancellation(AbstractFuture.GENERATE_CANCELLATION_CAUSES, (Throwable) null);
            CAUSELESS_INTERRUPTED = new Cancellation(true, (Throwable) null);
        }

        Cancellation(boolean wasInterrupted2, @NullableDecl Throwable cause2) {
            this.wasInterrupted = wasInterrupted2;
            this.cause = cause2;
        }
    }

    private static final class SetFuture<V> implements Runnable {
        final ListenableFuture<? extends V> future;
        final AbstractFuture<V> owner;

        SetFuture(AbstractFuture<V> owner2, ListenableFuture<? extends V> future2) {
            this.owner = owner2;
            this.future = future2;
        }

        public void run() {
            if (this.owner.value == this) {
                if (AbstractFuture.ATOMIC_HELPER.casValue(this.owner, this, AbstractFuture.getFutureValue(this.future))) {
                    AbstractFuture.complete(this.owner);
                }
            }
        }
    }

    protected AbstractFuture() {
    }

    @CanIgnoreReturnValue
    public V get(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException, ExecutionException {
        long remainingNanos = unit.toNanos(timeout);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object localValue = this.value;
        if ((localValue != null ? true : GENERATE_CANCELLATION_CAUSES) && (!(localValue instanceof SetFuture) ? true : GENERATE_CANCELLATION_CAUSES)) {
            return getDoneValue(localValue);
        }
        long endNanos = remainingNanos > 0 ? System.nanoTime() + remainingNanos : 0;
        if (remainingNanos >= 1000) {
            Waiter oldHead = this.waiters;
            if (oldHead != Waiter.TOMBSTONE) {
                Waiter node = new Waiter();
                do {
                    node.setNext(oldHead);
                    if (ATOMIC_HELPER.casWaiters(this, oldHead, node)) {
                        do {
                            LockSupport.parkNanos(this, remainingNanos);
                            if (Thread.interrupted()) {
                                removeWaiter(node);
                                throw new InterruptedException();
                            }
                            Object localValue2 = this.value;
                            if ((localValue2 != null ? true : GENERATE_CANCELLATION_CAUSES) && (!(localValue2 instanceof SetFuture) ? true : GENERATE_CANCELLATION_CAUSES)) {
                                return getDoneValue(localValue2);
                            }
                            remainingNanos = endNanos - System.nanoTime();
                        } while (remainingNanos >= 1000);
                        removeWaiter(node);
                    } else {
                        oldHead = this.waiters;
                    }
                } while (oldHead != Waiter.TOMBSTONE);
            }
            return getDoneValue(this.value);
        }
        while (remainingNanos > 0) {
            Object localValue3 = this.value;
            if ((localValue3 != null ? true : GENERATE_CANCELLATION_CAUSES) && (!(localValue3 instanceof SetFuture) ? true : GENERATE_CANCELLATION_CAUSES)) {
                return getDoneValue(localValue3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            remainingNanos = endNanos - System.nanoTime();
        }
        String futureToString = toString();
        String unitString = unit.toString().toLowerCase(Locale.ROOT);
        String message = "Waited " + timeout + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + unit.toString().toLowerCase(Locale.ROOT);
        if (1000 + remainingNanos < 0) {
            String message2 = message + " (plus ";
            long overWaitNanos = -remainingNanos;
            long overWaitUnits = unit.convert(overWaitNanos, TimeUnit.NANOSECONDS);
            long overWaitLeftoverNanos = overWaitNanos - unit.toNanos(overWaitUnits);
            boolean shouldShowExtraNanos = (overWaitUnits == 0 || overWaitLeftoverNanos > 1000) ? true : GENERATE_CANCELLATION_CAUSES;
            if (overWaitUnits > 0) {
                String message3 = message2 + overWaitUnits + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + unitString;
                if (shouldShowExtraNanos) {
                    message3 = message3 + ",";
                }
                message2 = message3 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            }
            if (shouldShowExtraNanos) {
                message2 = message2 + overWaitLeftoverNanos + " nanoseconds ";
            }
            message = message2 + "delay)";
        }
        if (isDone()) {
            throw new TimeoutException(message + " but future completed as timeout expired");
        }
        throw new TimeoutException(message + " for " + futureToString);
    }

    @CanIgnoreReturnValue
    public V get() throws InterruptedException, ExecutionException {
        Object localValue;
        boolean z;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object localValue2 = this.value;
        if ((localValue2 != null) && (!(localValue2 instanceof SetFuture))) {
            return getDoneValue(localValue2);
        }
        Waiter oldHead = this.waiters;
        if (oldHead != Waiter.TOMBSTONE) {
            Waiter node = new Waiter();
            do {
                node.setNext(oldHead);
                if (ATOMIC_HELPER.casWaiters(this, oldHead, node)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            removeWaiter(node);
                            throw new InterruptedException();
                        }
                        localValue = this.value;
                        if (localValue != null) {
                            z = true;
                        } else {
                            z = false;
                        }
                    } while (!(z & (!(localValue instanceof SetFuture))));
                    return getDoneValue(localValue);
                }
                oldHead = this.waiters;
            } while (oldHead != Waiter.TOMBSTONE);
        }
        return getDoneValue(this.value);
    }

    private V getDoneValue(Object obj) throws ExecutionException {
        if (obj instanceof Cancellation) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((Cancellation) obj).cause);
        } else if (obj instanceof Failure) {
            throw new ExecutionException(((Failure) obj).exception);
        } else if (obj == NULL) {
            return null;
        } else {
            return obj;
        }
    }

    public boolean isDone() {
        boolean z = true;
        Object localValue = this.value;
        boolean z2 = localValue != null;
        if (localValue instanceof SetFuture) {
            z = false;
        }
        return z2 & z;
    }

    public boolean isCancelled() {
        return this.value instanceof Cancellation;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.common.util.concurrent.ListenableFuture, com.google.common.util.concurrent.ListenableFuture<? extends V>] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    @com.google.errorprone.annotations.CanIgnoreReturnValue
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean cancel(boolean r11) {
        /*
            r10 = this;
            r7 = 1
            r8 = 0
            java.lang.Object r2 = r10.value
            r3 = 0
            if (r2 != 0) goto L_0x004b
            r6 = r7
        L_0x0008:
            boolean r9 = r2 instanceof com.google.common.util.concurrent.AbstractFuture.SetFuture
            r6 = r6 | r9
            if (r6 == 0) goto L_0x005a
            boolean r6 = GENERATE_CANCELLATION_CAUSES
            if (r6 == 0) goto L_0x004d
            com.google.common.util.concurrent.AbstractFuture$Cancellation r5 = new com.google.common.util.concurrent.AbstractFuture$Cancellation
            java.util.concurrent.CancellationException r6 = new java.util.concurrent.CancellationException
            java.lang.String r9 = "Future.cancel() was called."
            r6.<init>(r9)
            r5.<init>(r11, r6)
        L_0x001d:
            r0 = r10
        L_0x001e:
            com.google.common.util.concurrent.AbstractFuture$AtomicHelper r6 = ATOMIC_HELPER
            boolean r6 = r6.casValue(r0, r2, r5)
            if (r6 == 0) goto L_0x005b
            r3 = 1
            if (r11 == 0) goto L_0x002c
            r0.interruptTask()
        L_0x002c:
            complete(r0)
            boolean r6 = r2 instanceof com.google.common.util.concurrent.AbstractFuture.SetFuture
            if (r6 == 0) goto L_0x005a
            r6 = r2
            com.google.common.util.concurrent.AbstractFuture$SetFuture r6 = (com.google.common.util.concurrent.AbstractFuture.SetFuture) r6
            com.google.common.util.concurrent.ListenableFuture<? extends V> r1 = r6.future
            boolean r6 = r1 instanceof com.google.common.util.concurrent.AbstractFuture.Trusted
            if (r6 == 0) goto L_0x0057
            r4 = r1
            com.google.common.util.concurrent.AbstractFuture r4 = (com.google.common.util.concurrent.AbstractFuture) r4
            java.lang.Object r2 = r4.value
            if (r2 != 0) goto L_0x0055
            r6 = r7
        L_0x0044:
            boolean r9 = r2 instanceof com.google.common.util.concurrent.AbstractFuture.SetFuture
            r6 = r6 | r9
            if (r6 == 0) goto L_0x005a
            r0 = r4
            goto L_0x001e
        L_0x004b:
            r6 = r8
            goto L_0x0008
        L_0x004d:
            if (r11 == 0) goto L_0x0052
            com.google.common.util.concurrent.AbstractFuture$Cancellation r5 = com.google.common.util.concurrent.AbstractFuture.Cancellation.CAUSELESS_INTERRUPTED
            goto L_0x001d
        L_0x0052:
            com.google.common.util.concurrent.AbstractFuture$Cancellation r5 = com.google.common.util.concurrent.AbstractFuture.Cancellation.CAUSELESS_CANCELLED
            goto L_0x001d
        L_0x0055:
            r6 = r8
            goto L_0x0044
        L_0x0057:
            r1.cancel(r11)
        L_0x005a:
            return r3
        L_0x005b:
            java.lang.Object r2 = r0.value
            boolean r6 = r2 instanceof com.google.common.util.concurrent.AbstractFuture.SetFuture
            if (r6 != 0) goto L_0x001e
            goto L_0x005a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AbstractFuture.cancel(boolean):boolean");
    }

    /* access modifiers changed from: protected */
    public void interruptTask() {
    }

    /* access modifiers changed from: protected */
    public final boolean wasInterrupted() {
        Object localValue = this.value;
        if (!(localValue instanceof Cancellation) || !((Cancellation) localValue).wasInterrupted) {
            return GENERATE_CANCELLATION_CAUSES;
        }
        return true;
    }

    public void addListener(Runnable listener, Executor executor) {
        Listener oldHead;
        Preconditions.checkNotNull(listener, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        if (isDone() || (oldHead = this.listeners) == Listener.TOMBSTONE) {
            executeListener(listener, executor);
        }
        Listener newNode = new Listener(listener, executor);
        do {
            newNode.next = oldHead;
            if (!ATOMIC_HELPER.casListeners(this, oldHead, newNode)) {
                oldHead = this.listeners;
            } else {
                return;
            }
        } while (oldHead != Listener.TOMBSTONE);
        executeListener(listener, executor);
    }

    /* access modifiers changed from: protected */
    @CanIgnoreReturnValue
    public boolean set(@NullableDecl V value2) {
        Object valueToSet;
        if (value2 == null) {
            valueToSet = NULL;
        } else {
            valueToSet = value2;
        }
        if (!ATOMIC_HELPER.casValue(this, (Object) null, valueToSet)) {
            return GENERATE_CANCELLATION_CAUSES;
        }
        complete(this);
        return true;
    }

    /* access modifiers changed from: protected */
    @CanIgnoreReturnValue
    public boolean setException(Throwable throwable) {
        if (!ATOMIC_HELPER.casValue(this, (Object) null, new Failure((Throwable) Preconditions.checkNotNull(throwable)))) {
            return GENERATE_CANCELLATION_CAUSES;
        }
        complete(this);
        return true;
    }

    /* access modifiers changed from: protected */
    @CanIgnoreReturnValue
    @Beta
    public boolean setFuture(ListenableFuture<? extends V> future) {
        SetFuture valueToSet;
        Failure failure;
        Preconditions.checkNotNull(future);
        Object localValue = this.value;
        if (localValue == null) {
            if (future.isDone()) {
                if (!ATOMIC_HELPER.casValue(this, (Object) null, getFutureValue(future))) {
                    return GENERATE_CANCELLATION_CAUSES;
                }
                complete(this);
                return true;
            }
            valueToSet = new SetFuture(this, future);
            if (ATOMIC_HELPER.casValue(this, (Object) null, valueToSet)) {
                try {
                    future.addListener(valueToSet, DirectExecutor.INSTANCE);
                    return true;
                } catch (Throwable th) {
                    failure = Failure.FALLBACK_INSTANCE;
                }
            } else {
                localValue = this.value;
            }
        }
        if (localValue instanceof Cancellation) {
            future.cancel(((Cancellation) localValue).wasInterrupted);
        }
        return GENERATE_CANCELLATION_CAUSES;
        ATOMIC_HELPER.casValue(this, valueToSet, failure);
        return true;
    }

    /* access modifiers changed from: private */
    public static Object getFutureValue(ListenableFuture<?> future) {
        boolean z;
        Throwable throwable;
        if (future instanceof Trusted) {
            Object v = ((AbstractFuture) future).value;
            if (!(v instanceof Cancellation)) {
                return v;
            }
            Cancellation c = (Cancellation) v;
            if (!c.wasInterrupted) {
                return v;
            }
            if (c.cause != null) {
                return new Cancellation(GENERATE_CANCELLATION_CAUSES, c.cause);
            }
            return Cancellation.CAUSELESS_CANCELLED;
        } else if ((future instanceof InternalFutureFailureAccess) && (throwable = InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess) future)) != null) {
            return new Failure(throwable);
        } else {
            boolean wasCancelled = future.isCancelled();
            if (!GENERATE_CANCELLATION_CAUSES) {
                z = true;
            } else {
                z = false;
            }
            if (z && wasCancelled) {
                return Cancellation.CAUSELESS_CANCELLED;
            }
            try {
                Object v2 = getUninterruptibly(future);
                if (wasCancelled) {
                    return new Cancellation(GENERATE_CANCELLATION_CAUSES, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + future));
                }
                if (v2 == null) {
                    return NULL;
                }
                return v2;
            } catch (ExecutionException exception) {
                if (wasCancelled) {
                    return new Cancellation(GENERATE_CANCELLATION_CAUSES, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + future, exception));
                }
                return new Failure(exception.getCause());
            } catch (CancellationException cancellation) {
                if (!wasCancelled) {
                    return new Failure(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + future, cancellation));
                }
                return new Cancellation(GENERATE_CANCELLATION_CAUSES, cancellation);
            } catch (Throwable t) {
                return new Failure(t);
            }
        }
    }

    private static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
        V v;
        boolean interrupted = GENERATE_CANCELLATION_CAUSES;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException e) {
                interrupted = true;
            } catch (Throwable th) {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    /* access modifiers changed from: private */
    public static void complete(AbstractFuture<?> abstractFuture) {
        Listener next = null;
        AbstractFuture<V> future = abstractFuture;
        while (true) {
            future.releaseWaiters();
            future.afterDone();
            next = future.clearListeners(next);
            while (true) {
                if (next != null) {
                    Listener curr = next;
                    next = next.next;
                    Runnable task = curr.task;
                    if (task instanceof SetFuture) {
                        SetFuture<?> setFuture = (SetFuture) task;
                        AbstractFuture<V> abstractFuture2 = setFuture.owner;
                        if (abstractFuture2.value == setFuture) {
                            if (ATOMIC_HELPER.casValue(abstractFuture2, setFuture, getFutureValue(setFuture.future))) {
                                future = abstractFuture2;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        executeListener(task, curr.executor);
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @ForOverride
    @Beta
    public void afterDone() {
    }

    /* access modifiers changed from: protected */
    @NullableDecl
    public final Throwable tryInternalFastPathGetFailure() {
        if (this instanceof Trusted) {
            Object obj = this.value;
            if (obj instanceof Failure) {
                return ((Failure) obj).exception;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void maybePropagateCancellationTo(@NullableDecl Future<?> related) {
        if ((related != null ? true : GENERATE_CANCELLATION_CAUSES) && isCancelled()) {
            related.cancel(wasInterrupted());
        }
    }

    private void releaseWaiters() {
        Waiter head;
        do {
            head = this.waiters;
        } while (!ATOMIC_HELPER.casWaiters(this, head, Waiter.TOMBSTONE));
        for (Waiter currentWaiter = head; currentWaiter != null; currentWaiter = currentWaiter.next) {
            currentWaiter.unpark();
        }
    }

    private Listener clearListeners(Listener onto) {
        Listener head;
        do {
            head = this.listeners;
        } while (!ATOMIC_HELPER.casListeners(this, head, Listener.TOMBSTONE));
        Listener reversedList = onto;
        while (head != null) {
            Listener tmp = head;
            head = head.next;
            tmp.next = reversedList;
            reversedList = tmp;
        }
        return reversedList;
    }

    public String toString() {
        String pendingDescription;
        StringBuilder builder = new StringBuilder().append(super.toString()).append("[status=");
        if (isCancelled()) {
            builder.append("CANCELLED");
        } else if (isDone()) {
            addDoneString(builder);
        } else {
            try {
                pendingDescription = pendingToString();
            } catch (RuntimeException e) {
                pendingDescription = "Exception thrown from implementation: " + e.getClass();
            }
            if (pendingDescription != null && !pendingDescription.isEmpty()) {
                builder.append("PENDING, info=[").append(pendingDescription).append("]");
            } else if (isDone()) {
                addDoneString(builder);
            } else {
                builder.append("PENDING");
            }
        }
        return builder.append("]").toString();
    }

    /* access modifiers changed from: protected */
    @NullableDecl
    public String pendingToString() {
        Object localValue = this.value;
        if (localValue instanceof SetFuture) {
            return "setFuture=[" + userObjectToString(((SetFuture) localValue).future) + "]";
        }
        if (this instanceof ScheduledFuture) {
            return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
        }
        return null;
    }

    private void addDoneString(StringBuilder builder) {
        try {
            builder.append("SUCCESS, result=[").append(userObjectToString(getUninterruptibly(this))).append("]");
        } catch (ExecutionException e) {
            builder.append("FAILURE, cause=[").append(e.getCause()).append("]");
        } catch (CancellationException e2) {
            builder.append("CANCELLED");
        } catch (RuntimeException e3) {
            builder.append("UNKNOWN, cause=[").append(e3.getClass()).append(" thrown from get()]");
        }
    }

    private String userObjectToString(Object o) {
        if (o == this) {
            return "this future";
        }
        return String.valueOf(o);
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
        }
    }

    private static abstract class AtomicHelper {
        /* access modifiers changed from: package-private */
        public abstract boolean casListeners(AbstractFuture<?> abstractFuture, Listener listener, Listener listener2);

        /* access modifiers changed from: package-private */
        public abstract boolean casValue(AbstractFuture<?> abstractFuture, Object obj, Object obj2);

        /* access modifiers changed from: package-private */
        public abstract boolean casWaiters(AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2);

        /* access modifiers changed from: package-private */
        public abstract void putNext(Waiter waiter, Waiter waiter2);

        /* access modifiers changed from: package-private */
        public abstract void putThread(Waiter waiter, Thread thread);

        private AtomicHelper() {
        }
    }

    private static final class UnsafeAtomicHelper extends AtomicHelper {
        static final long LISTENERS_OFFSET;
        static final Unsafe UNSAFE;
        static final long VALUE_OFFSET;
        static final long WAITERS_OFFSET;
        static final long WAITER_NEXT_OFFSET;
        static final long WAITER_THREAD_OFFSET;

        private UnsafeAtomicHelper() {
            super();
        }

        static {
            Unsafe unsafe;
            try {
                unsafe = Unsafe.getUnsafe();
            } catch (SecurityException e) {
                try {
                    unsafe = (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
                        public Unsafe run() throws Exception {
                            Class<Unsafe> k = Unsafe.class;
                            for (Field f : k.getDeclaredFields()) {
                                f.setAccessible(true);
                                Object x = f.get((Object) null);
                                if (k.isInstance(x)) {
                                    return k.cast(x);
                                }
                            }
                            throw new NoSuchFieldError("the Unsafe");
                        }
                    });
                } catch (PrivilegedActionException e2) {
                    throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
                }
            }
            Class<AbstractFuture> cls = AbstractFuture.class;
            try {
                WAITERS_OFFSET = unsafe.objectFieldOffset(cls.getDeclaredField("waiters"));
                LISTENERS_OFFSET = unsafe.objectFieldOffset(cls.getDeclaredField("listeners"));
                VALUE_OFFSET = unsafe.objectFieldOffset(cls.getDeclaredField("value"));
                WAITER_THREAD_OFFSET = unsafe.objectFieldOffset(Waiter.class.getDeclaredField("thread"));
                WAITER_NEXT_OFFSET = unsafe.objectFieldOffset(Waiter.class.getDeclaredField("next"));
                UNSAFE = unsafe;
            } catch (Exception e3) {
                Throwables.throwIfUnchecked(e3);
                throw new RuntimeException(e3);
            }
        }

        /* access modifiers changed from: package-private */
        public void putThread(Waiter waiter, Thread newValue) {
            UNSAFE.putObject(waiter, WAITER_THREAD_OFFSET, newValue);
        }

        /* access modifiers changed from: package-private */
        public void putNext(Waiter waiter, Waiter newValue) {
            UNSAFE.putObject(waiter, WAITER_NEXT_OFFSET, newValue);
        }

        /* access modifiers changed from: package-private */
        public boolean casWaiters(AbstractFuture<?> future, Waiter expect, Waiter update) {
            return UNSAFE.compareAndSwapObject(future, WAITERS_OFFSET, expect, update);
        }

        /* access modifiers changed from: package-private */
        public boolean casListeners(AbstractFuture<?> future, Listener expect, Listener update) {
            return UNSAFE.compareAndSwapObject(future, LISTENERS_OFFSET, expect, update);
        }

        /* access modifiers changed from: package-private */
        public boolean casValue(AbstractFuture<?> future, Object expect, Object update) {
            return UNSAFE.compareAndSwapObject(future, VALUE_OFFSET, expect, update);
        }
    }

    private static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicReferenceFieldUpdater<AbstractFuture, Listener> listenersUpdater;
        final AtomicReferenceFieldUpdater<AbstractFuture, Object> valueUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Waiter> waiterNextUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Thread> waiterThreadUpdater;
        final AtomicReferenceFieldUpdater<AbstractFuture, Waiter> waitersUpdater;

        SafeAtomicHelper(AtomicReferenceFieldUpdater<Waiter, Thread> waiterThreadUpdater2, AtomicReferenceFieldUpdater<Waiter, Waiter> waiterNextUpdater2, AtomicReferenceFieldUpdater<AbstractFuture, Waiter> waitersUpdater2, AtomicReferenceFieldUpdater<AbstractFuture, Listener> listenersUpdater2, AtomicReferenceFieldUpdater<AbstractFuture, Object> valueUpdater2) {
            super();
            this.waiterThreadUpdater = waiterThreadUpdater2;
            this.waiterNextUpdater = waiterNextUpdater2;
            this.waitersUpdater = waitersUpdater2;
            this.listenersUpdater = listenersUpdater2;
            this.valueUpdater = valueUpdater2;
        }

        /* access modifiers changed from: package-private */
        public void putThread(Waiter waiter, Thread newValue) {
            this.waiterThreadUpdater.lazySet(waiter, newValue);
        }

        /* access modifiers changed from: package-private */
        public void putNext(Waiter waiter, Waiter newValue) {
            this.waiterNextUpdater.lazySet(waiter, newValue);
        }

        /* access modifiers changed from: package-private */
        public boolean casWaiters(AbstractFuture<?> future, Waiter expect, Waiter update) {
            return this.waitersUpdater.compareAndSet(future, expect, update);
        }

        /* access modifiers changed from: package-private */
        public boolean casListeners(AbstractFuture<?> future, Listener expect, Listener update) {
            return this.listenersUpdater.compareAndSet(future, expect, update);
        }

        /* access modifiers changed from: package-private */
        public boolean casValue(AbstractFuture<?> future, Object expect, Object update) {
            return this.valueUpdater.compareAndSet(future, expect, update);
        }
    }

    private static final class SynchronizedHelper extends AtomicHelper {
        private SynchronizedHelper() {
            super();
        }

        /* access modifiers changed from: package-private */
        public void putThread(Waiter waiter, Thread newValue) {
            waiter.thread = newValue;
        }

        /* access modifiers changed from: package-private */
        public void putNext(Waiter waiter, Waiter newValue) {
            waiter.next = newValue;
        }

        /* access modifiers changed from: package-private */
        public boolean casWaiters(AbstractFuture<?> future, Waiter expect, Waiter update) {
            boolean z;
            synchronized (future) {
                if (future.waiters == expect) {
                    Waiter unused = future.waiters = update;
                    z = true;
                } else {
                    z = AbstractFuture.GENERATE_CANCELLATION_CAUSES;
                }
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        public boolean casListeners(AbstractFuture<?> future, Listener expect, Listener update) {
            boolean z;
            synchronized (future) {
                if (future.listeners == expect) {
                    Listener unused = future.listeners = update;
                    z = true;
                } else {
                    z = AbstractFuture.GENERATE_CANCELLATION_CAUSES;
                }
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        public boolean casValue(AbstractFuture<?> future, Object expect, Object update) {
            boolean z;
            synchronized (future) {
                if (future.value == expect) {
                    Object unused = future.value = update;
                    z = true;
                } else {
                    z = AbstractFuture.GENERATE_CANCELLATION_CAUSES;
                }
            }
            return z;
        }
    }

    private static CancellationException cancellationExceptionWithCause(@NullableDecl String message, @NullableDecl Throwable cause) {
        CancellationException exception = new CancellationException(message);
        exception.initCause(cause);
        return exception;
    }
}
