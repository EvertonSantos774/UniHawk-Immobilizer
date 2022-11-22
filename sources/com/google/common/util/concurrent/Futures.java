package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.CollectionFuture;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.Partially;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
public final class Futures extends GwtFuturesCatchingSpecialization {
    private Futures() {
    }

    @GwtIncompatible
    @Deprecated
    @Beta
    public static <V, X extends Exception> CheckedFuture<V, X> makeChecked(ListenableFuture<V> future, Function<? super Exception, X> mapper) {
        return new MappingCheckedFuture((ListenableFuture) Preconditions.checkNotNull(future), mapper);
    }

    public static <V> ListenableFuture<V> immediateFuture(@NullableDecl V value) {
        if (value == null) {
            return ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        }
        return new ImmediateFuture.ImmediateSuccessfulFuture(value);
    }

    @GwtIncompatible
    @Deprecated
    @Beta
    public static <V, X extends Exception> CheckedFuture<V, X> immediateCheckedFuture(@NullableDecl V value) {
        return new ImmediateFuture.ImmediateSuccessfulCheckedFuture(value);
    }

    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable throwable) {
        Preconditions.checkNotNull(throwable);
        return new ImmediateFuture.ImmediateFailedFuture(throwable);
    }

    public static <V> ListenableFuture<V> immediateCancelledFuture() {
        return new ImmediateFuture.ImmediateCancelledFuture();
    }

    @GwtIncompatible
    @Deprecated
    @Beta
    public static <V, X extends Exception> CheckedFuture<V, X> immediateFailedCheckedFuture(X exception) {
        Preconditions.checkNotNull(exception);
        return new ImmediateFuture.ImmediateFailedCheckedFuture(exception);
    }

    @Beta
    public static <O> ListenableFuture<O> submitAsync(AsyncCallable<O> callable, Executor executor) {
        TrustedListenableFutureTask<O> task = TrustedListenableFutureTask.create(callable);
        executor.execute(task);
        return task;
    }

    @GwtIncompatible
    @Beta
    public static <O> ListenableFuture<O> scheduleAsync(AsyncCallable<O> callable, long delay, TimeUnit timeUnit, ScheduledExecutorService executorService) {
        TrustedListenableFutureTask<O> task = TrustedListenableFutureTask.create(callable);
        final Future<?> scheduled = executorService.schedule(task, delay, timeUnit);
        task.addListener(new Runnable() {
            public void run() {
                scheduled.cancel(false);
            }
        }, MoreExecutors.directExecutor());
        return task;
    }

    @Beta
    @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public static <V, X extends Throwable> ListenableFuture<V> catching(ListenableFuture<? extends V> input, Class<X> exceptionType, Function<? super X, ? extends V> fallback, Executor executor) {
        return AbstractCatchingFuture.create(input, exceptionType, fallback, executor);
    }

    @Beta
    @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public static <V, X extends Throwable> ListenableFuture<V> catchingAsync(ListenableFuture<? extends V> input, Class<X> exceptionType, AsyncFunction<? super X, ? extends V> fallback, Executor executor) {
        return AbstractCatchingFuture.create(input, exceptionType, fallback, executor);
    }

    @GwtIncompatible
    @Beta
    public static <V> ListenableFuture<V> withTimeout(ListenableFuture<V> delegate, long time, TimeUnit unit, ScheduledExecutorService scheduledExecutor) {
        return delegate.isDone() ? delegate : TimeoutFuture.create(delegate, time, unit, scheduledExecutor);
    }

    @Beta
    public static <I, O> ListenableFuture<O> transformAsync(ListenableFuture<I> input, AsyncFunction<? super I, ? extends O> function, Executor executor) {
        return AbstractTransformFuture.create(input, function, executor);
    }

    @Beta
    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> input, Function<? super I, ? extends O> function, Executor executor) {
        return AbstractTransformFuture.create(input, function, executor);
    }

    @GwtIncompatible
    @Beta
    public static <I, O> Future<O> lazyTransform(final Future<I> input, final Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(input);
        Preconditions.checkNotNull(function);
        return new Future<O>() {
            public boolean cancel(boolean mayInterruptIfRunning) {
                return input.cancel(mayInterruptIfRunning);
            }

            public boolean isCancelled() {
                return input.isCancelled();
            }

            public boolean isDone() {
                return input.isDone();
            }

            public O get() throws InterruptedException, ExecutionException {
                return applyTransformation(input.get());
            }

            public O get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return applyTransformation(input.get(timeout, unit));
            }

            private O applyTransformation(I input) throws ExecutionException {
                try {
                    return function.apply(input);
                } catch (Throwable t) {
                    throw new ExecutionException(t);
                }
            }
        };
    }

    @SafeVarargs
    @Beta
    public static <V> ListenableFuture<List<V>> allAsList(ListenableFuture<? extends V>... futures) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf((E[]) futures), true);
    }

    @Beta
    public static <V> ListenableFuture<List<V>> allAsList(Iterable<? extends ListenableFuture<? extends V>> futures) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf(futures), true);
    }

    @SafeVarargs
    @Beta
    public static <V> FutureCombiner<V> whenAllComplete(ListenableFuture<? extends V>... futures) {
        return new FutureCombiner<>(false, ImmutableList.copyOf((E[]) futures));
    }

    @Beta
    public static <V> FutureCombiner<V> whenAllComplete(Iterable<? extends ListenableFuture<? extends V>> futures) {
        return new FutureCombiner<>(false, ImmutableList.copyOf(futures));
    }

    @SafeVarargs
    @Beta
    public static <V> FutureCombiner<V> whenAllSucceed(ListenableFuture<? extends V>... futures) {
        return new FutureCombiner<>(true, ImmutableList.copyOf((E[]) futures));
    }

    @Beta
    public static <V> FutureCombiner<V> whenAllSucceed(Iterable<? extends ListenableFuture<? extends V>> futures) {
        return new FutureCombiner<>(true, ImmutableList.copyOf(futures));
    }

    @GwtCompatible
    @CanIgnoreReturnValue
    @Beta
    public static final class FutureCombiner<V> {
        private final boolean allMustSucceed;
        private final ImmutableList<ListenableFuture<? extends V>> futures;

        private FutureCombiner(boolean allMustSucceed2, ImmutableList<ListenableFuture<? extends V>> futures2) {
            this.allMustSucceed = allMustSucceed2;
            this.futures = futures2;
        }

        public <C> ListenableFuture<C> callAsync(AsyncCallable<C> combiner, Executor executor) {
            return new CombinedFuture((ImmutableCollection<? extends ListenableFuture<?>>) this.futures, this.allMustSucceed, executor, combiner);
        }

        @CanIgnoreReturnValue
        public <C> ListenableFuture<C> call(Callable<C> combiner, Executor executor) {
            return new CombinedFuture((ImmutableCollection<? extends ListenableFuture<?>>) this.futures, this.allMustSucceed, executor, combiner);
        }

        public ListenableFuture<?> run(final Runnable combiner, Executor executor) {
            return call(new Callable<Void>() {
                public Void call() throws Exception {
                    combiner.run();
                    return null;
                }
            }, executor);
        }
    }

    @Beta
    public static <V> ListenableFuture<V> nonCancellationPropagating(ListenableFuture<V> future) {
        if (future.isDone()) {
            return future;
        }
        NonCancellationPropagatingFuture<V> output = new NonCancellationPropagatingFuture<>(future);
        future.addListener(output, MoreExecutors.directExecutor());
        return output;
    }

    private static final class NonCancellationPropagatingFuture<V> extends AbstractFuture.TrustedFuture<V> implements Runnable {
        private ListenableFuture<V> delegate;

        NonCancellationPropagatingFuture(ListenableFuture<V> delegate2) {
            this.delegate = delegate2;
        }

        public void run() {
            ListenableFuture<V> localDelegate = this.delegate;
            if (localDelegate != null) {
                setFuture(localDelegate);
            }
        }

        /* access modifiers changed from: protected */
        public String pendingToString() {
            ListenableFuture<V> localDelegate = this.delegate;
            if (localDelegate != null) {
                return "delegate=[" + localDelegate + "]";
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void afterDone() {
            this.delegate = null;
        }
    }

    @SafeVarargs
    @Beta
    public static <V> ListenableFuture<List<V>> successfulAsList(ListenableFuture<? extends V>... futures) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf((E[]) futures), false);
    }

    @Beta
    public static <V> ListenableFuture<List<V>> successfulAsList(Iterable<? extends ListenableFuture<? extends V>> futures) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf(futures), false);
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [java.lang.Iterable<? extends com.google.common.util.concurrent.ListenableFuture<? extends T>>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @com.google.common.annotations.Beta
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> com.google.common.collect.ImmutableList<com.google.common.util.concurrent.ListenableFuture<T>> inCompletionOrder(java.lang.Iterable<? extends com.google.common.util.concurrent.ListenableFuture<? extends T>> r11) {
        /*
            r9 = 0
            boolean r8 = r11 instanceof java.util.Collection
            if (r8 == 0) goto L_0x002f
            r0 = r11
            java.util.Collection r0 = (java.util.Collection) r0
        L_0x0008:
            int r8 = r0.size()
            com.google.common.util.concurrent.ListenableFuture[] r8 = new com.google.common.util.concurrent.ListenableFuture[r8]
            java.lang.Object[] r8 = r0.toArray(r8)
            com.google.common.util.concurrent.ListenableFuture[] r8 = (com.google.common.util.concurrent.ListenableFuture[]) r8
            r1 = r8
            com.google.common.util.concurrent.ListenableFuture[] r1 = (com.google.common.util.concurrent.ListenableFuture[]) r1
            com.google.common.util.concurrent.Futures$InCompletionOrderState r7 = new com.google.common.util.concurrent.Futures$InCompletionOrderState
            r7.<init>(r1)
            com.google.common.collect.ImmutableList$Builder r3 = com.google.common.collect.ImmutableList.builder()
            r5 = 0
        L_0x0021:
            int r8 = r1.length
            if (r5 >= r8) goto L_0x0034
            com.google.common.util.concurrent.Futures$InCompletionOrderFuture r8 = new com.google.common.util.concurrent.Futures$InCompletionOrderFuture
            r8.<init>(r7)
            r3.add((java.lang.Object) r8)
            int r5 = r5 + 1
            goto L_0x0021
        L_0x002f:
            com.google.common.collect.ImmutableList r0 = com.google.common.collect.ImmutableList.copyOf(r11)
            goto L_0x0008
        L_0x0034:
            com.google.common.collect.ImmutableList r2 = r3.build()
            r5 = 0
        L_0x0039:
            int r8 = r1.length
            if (r5 >= r8) goto L_0x004e
            r6 = r5
            r8 = r1[r5]
            com.google.common.util.concurrent.Futures$3 r9 = new com.google.common.util.concurrent.Futures$3
            r9.<init>(r7, r2, r6)
            java.util.concurrent.Executor r10 = com.google.common.util.concurrent.MoreExecutors.directExecutor()
            r8.addListener(r9, r10)
            int r5 = r5 + 1
            goto L_0x0039
        L_0x004e:
            r4 = r2
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Futures.inCompletionOrder(java.lang.Iterable):com.google.common.collect.ImmutableList");
    }

    private static final class InCompletionOrderFuture<T> extends AbstractFuture<T> {
        private InCompletionOrderState<T> state;

        private InCompletionOrderFuture(InCompletionOrderState<T> state2) {
            this.state = state2;
        }

        public boolean cancel(boolean interruptIfRunning) {
            InCompletionOrderState<T> localState = this.state;
            if (!super.cancel(interruptIfRunning)) {
                return false;
            }
            localState.recordOutputCancellation(interruptIfRunning);
            return true;
        }

        /* access modifiers changed from: protected */
        public void afterDone() {
            this.state = null;
        }

        /* access modifiers changed from: protected */
        public String pendingToString() {
            InCompletionOrderState<T> localState = this.state;
            if (localState != null) {
                return "inputCount=[" + localState.inputFutures.length + "], remaining=[" + localState.incompleteOutputCount.get() + "]";
            }
            return null;
        }
    }

    private static final class InCompletionOrderState<T> {
        private volatile int delegateIndex;
        /* access modifiers changed from: private */
        public final AtomicInteger incompleteOutputCount;
        /* access modifiers changed from: private */
        public final ListenableFuture<? extends T>[] inputFutures;
        private boolean shouldInterrupt;
        private boolean wasCancelled;

        private InCompletionOrderState(ListenableFuture<? extends T>[] inputFutures2) {
            this.wasCancelled = false;
            this.shouldInterrupt = true;
            this.delegateIndex = 0;
            this.inputFutures = inputFutures2;
            this.incompleteOutputCount = new AtomicInteger(inputFutures2.length);
        }

        /* access modifiers changed from: private */
        public void recordOutputCancellation(boolean interruptIfRunning) {
            this.wasCancelled = true;
            if (!interruptIfRunning) {
                this.shouldInterrupt = false;
            }
            recordCompletion();
        }

        /* access modifiers changed from: private */
        public void recordInputCompletion(ImmutableList<AbstractFuture<T>> delegates, int inputFutureIndex) {
            ListenableFuture<? extends T> inputFuture = this.inputFutures[inputFutureIndex];
            this.inputFutures[inputFutureIndex] = null;
            for (int i = this.delegateIndex; i < delegates.size(); i++) {
                if (((AbstractFuture) delegates.get(i)).setFuture(inputFuture)) {
                    recordCompletion();
                    this.delegateIndex = i + 1;
                    return;
                }
            }
            this.delegateIndex = delegates.size();
        }

        private void recordCompletion() {
            if (this.incompleteOutputCount.decrementAndGet() == 0 && this.wasCancelled) {
                for (ListenableFuture<? extends T> listenableFuture : this.inputFutures) {
                    if (listenableFuture != null) {
                        listenableFuture.cancel(this.shouldInterrupt);
                    }
                }
            }
        }
    }

    public static <V> void addCallback(ListenableFuture<V> future, FutureCallback<? super V> callback, Executor executor) {
        Preconditions.checkNotNull(callback);
        future.addListener(new CallbackListener(future, callback), executor);
    }

    private static final class CallbackListener<V> implements Runnable {
        final FutureCallback<? super V> callback;
        final Future<V> future;

        CallbackListener(Future<V> future2, FutureCallback<? super V> callback2) {
            this.future = future2;
            this.callback = callback2;
        }

        public void run() {
            try {
                this.callback.onSuccess(Futures.getDone(this.future));
            } catch (ExecutionException e) {
                this.callback.onFailure(e.getCause());
            } catch (Error | RuntimeException e2) {
                this.callback.onFailure(e2);
            }
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).addValue((Object) this.callback).toString();
        }
    }

    @CanIgnoreReturnValue
    public static <V> V getDone(Future<V> future) throws ExecutionException {
        Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", (Object) future);
        return Uninterruptibles.getUninterruptibly(future);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @Beta
    public static <V, X extends Exception> V getChecked(Future<V> future, Class<X> exceptionClass) throws Exception {
        return FuturesGetChecked.getChecked(future, exceptionClass);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @Beta
    public static <V, X extends Exception> V getChecked(Future<V> future, Class<X> exceptionClass, long timeout, TimeUnit unit) throws Exception {
        return FuturesGetChecked.getChecked(future, exceptionClass, timeout, unit);
    }

    @CanIgnoreReturnValue
    public static <V> V getUnchecked(Future<V> future) {
        Preconditions.checkNotNull(future);
        try {
            return Uninterruptibles.getUninterruptibly(future);
        } catch (ExecutionException e) {
            wrapAndThrowUnchecked(e.getCause());
            throw new AssertionError();
        }
    }

    private static void wrapAndThrowUnchecked(Throwable cause) {
        if (cause instanceof Error) {
            throw new ExecutionError((Error) cause);
        }
        throw new UncheckedExecutionException(cause);
    }

    @GwtIncompatible
    private static class MappingCheckedFuture<V, X extends Exception> extends AbstractCheckedFuture<V, X> {
        final Function<? super Exception, X> mapper;

        MappingCheckedFuture(ListenableFuture<V> delegate, Function<? super Exception, X> mapper2) {
            super(delegate);
            this.mapper = (Function) Preconditions.checkNotNull(mapper2);
        }

        /* access modifiers changed from: protected */
        public X mapException(Exception e) {
            return (Exception) this.mapper.apply(e);
        }
    }
}
