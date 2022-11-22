package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import com.google.errorprone.annotations.ForOverride;
import java.lang.Throwable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
abstract class AbstractCatchingFuture<V, X extends Throwable, F, T> extends FluentFuture.TrustedFuture<V> implements Runnable {
    @NullableDecl
    Class<X> exceptionType;
    @NullableDecl
    F fallback;
    @NullableDecl
    ListenableFuture<? extends V> inputFuture;

    /* access modifiers changed from: package-private */
    @ForOverride
    @NullableDecl
    public abstract T doFallback(F f, X x) throws Exception;

    /* access modifiers changed from: package-private */
    @ForOverride
    public abstract void setResult(@NullableDecl T t);

    static <V, X extends Throwable> ListenableFuture<V> create(ListenableFuture<? extends V> input, Class<X> exceptionType2, Function<? super X, ? extends V> fallback2, Executor executor) {
        CatchingFuture<V, X> future = new CatchingFuture<>(input, exceptionType2, fallback2);
        input.addListener(future, MoreExecutors.rejectionPropagatingExecutor(executor, future));
        return future;
    }

    static <X extends Throwable, V> ListenableFuture<V> create(ListenableFuture<? extends V> input, Class<X> exceptionType2, AsyncFunction<? super X, ? extends V> fallback2, Executor executor) {
        AsyncCatchingFuture<V, X> future = new AsyncCatchingFuture<>(input, exceptionType2, fallback2);
        input.addListener(future, MoreExecutors.rejectionPropagatingExecutor(executor, future));
        return future;
    }

    AbstractCatchingFuture(ListenableFuture<? extends V> inputFuture2, Class<X> exceptionType2, F fallback2) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(inputFuture2);
        this.exceptionType = (Class) Preconditions.checkNotNull(exceptionType2);
        this.fallback = Preconditions.checkNotNull(fallback2);
    }

    public final void run() {
        boolean z = true;
        ListenableFuture<? extends V> localInputFuture = this.inputFuture;
        Class<X> localExceptionType = this.exceptionType;
        F localFallback = this.fallback;
        boolean z2 = (localExceptionType == null) | (localInputFuture == null);
        if (localFallback != null) {
            z = false;
        }
        if (!(z | z2) && !isCancelled()) {
            this.inputFuture = null;
            V sourceResult = null;
            X throwable = null;
            try {
                sourceResult = Futures.getDone(localInputFuture);
            } catch (ExecutionException e) {
                throwable = (Throwable) Preconditions.checkNotNull(e.getCause());
            } catch (Throwable e2) {
                throwable = e2;
            }
            if (throwable == null) {
                set(sourceResult);
            } else if (!Platform.isInstanceOfThrowableClass(throwable, localExceptionType)) {
                setFuture(localInputFuture);
            } else {
                try {
                    T fallbackResult = doFallback(localFallback, throwable);
                    this.exceptionType = null;
                    this.fallback = null;
                    setResult(fallbackResult);
                } catch (Throwable th) {
                    this.exceptionType = null;
                    this.fallback = null;
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String pendingToString() {
        ListenableFuture<? extends V> localInputFuture = this.inputFuture;
        Class<X> localExceptionType = this.exceptionType;
        F localFallback = this.fallback;
        String superString = super.pendingToString();
        String resultString = "";
        if (localInputFuture != null) {
            resultString = "inputFuture=[" + localInputFuture + "], ";
        }
        if (localExceptionType != null && localFallback != null) {
            return resultString + "exceptionType=[" + localExceptionType + "], fallback=[" + localFallback + "]";
        }
        if (superString != null) {
            return resultString + superString;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    private static final class AsyncCatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, AsyncFunction<? super X, ? extends V>, ListenableFuture<? extends V>> {
        AsyncCatchingFuture(ListenableFuture<? extends V> input, Class<X> exceptionType, AsyncFunction<? super X, ? extends V> fallback) {
            super(input, exceptionType, fallback);
        }

        /* access modifiers changed from: package-private */
        public ListenableFuture<? extends V> doFallback(AsyncFunction<? super X, ? extends V> fallback, X cause) throws Exception {
            ListenableFuture<? extends V> replacement = fallback.apply(cause);
            Preconditions.checkNotNull(replacement, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", (Object) fallback);
            return replacement;
        }

        /* access modifiers changed from: package-private */
        public void setResult(ListenableFuture<? extends V> result) {
            setFuture(result);
        }
    }

    private static final class CatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, Function<? super X, ? extends V>, V> {
        CatchingFuture(ListenableFuture<? extends V> input, Class<X> exceptionType, Function<? super X, ? extends V> fallback) {
            super(input, exceptionType, fallback);
        }

        /* access modifiers changed from: package-private */
        @NullableDecl
        public V doFallback(Function<? super X, ? extends V> fallback, X cause) throws Exception {
            return fallback.apply(cause);
        }

        /* access modifiers changed from: package-private */
        public void setResult(@NullableDecl V result) {
            set(result);
        }
    }
}
