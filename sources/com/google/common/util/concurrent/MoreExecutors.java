package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ForwardingListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@GwtCompatible(emulated = true)
public final class MoreExecutors {
    private MoreExecutors() {
    }

    @GwtIncompatible
    @Beta
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
        return new Application().getExitingExecutorService(executor, terminationTimeout, timeUnit);
    }

    @GwtIncompatible
    @Beta
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor) {
        return new Application().getExitingExecutorService(executor);
    }

    @GwtIncompatible
    @Beta
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
        return new Application().getExitingScheduledExecutorService(executor, terminationTimeout, timeUnit);
    }

    @GwtIncompatible
    @Beta
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor) {
        return new Application().getExitingScheduledExecutorService(executor);
    }

    @GwtIncompatible
    @Beta
    public static void addDelayedShutdownHook(ExecutorService service, long terminationTimeout, TimeUnit timeUnit) {
        new Application().addDelayedShutdownHook(service, terminationTimeout, timeUnit);
    }

    @GwtIncompatible
    @VisibleForTesting
    static class Application {
        Application() {
        }

        /* access modifiers changed from: package-private */
        public final ExecutorService getExitingExecutorService(ThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
            MoreExecutors.useDaemonThreadFactory(executor);
            ExecutorService service = Executors.unconfigurableExecutorService(executor);
            addDelayedShutdownHook(executor, terminationTimeout, timeUnit);
            return service;
        }

        /* access modifiers changed from: package-private */
        public final ExecutorService getExitingExecutorService(ThreadPoolExecutor executor) {
            return getExitingExecutorService(executor, 120, TimeUnit.SECONDS);
        }

        /* access modifiers changed from: package-private */
        public final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
            MoreExecutors.useDaemonThreadFactory(executor);
            ScheduledExecutorService service = Executors.unconfigurableScheduledExecutorService(executor);
            addDelayedShutdownHook(executor, terminationTimeout, timeUnit);
            return service;
        }

        /* access modifiers changed from: package-private */
        public final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor) {
            return getExitingScheduledExecutorService(executor, 120, TimeUnit.SECONDS);
        }

        /* access modifiers changed from: package-private */
        public final void addDelayedShutdownHook(ExecutorService service, long terminationTimeout, TimeUnit timeUnit) {
            Preconditions.checkNotNull(service);
            Preconditions.checkNotNull(timeUnit);
            final ExecutorService executorService = service;
            final long j = terminationTimeout;
            final TimeUnit timeUnit2 = timeUnit;
            addShutdownHook(MoreExecutors.newThread("DelayedShutdownHook-for-" + service, new Runnable() {
                public void run() {
                    try {
                        executorService.shutdown();
                        executorService.awaitTermination(j, timeUnit2);
                    } catch (InterruptedException e) {
                    }
                }
            }));
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public void addShutdownHook(Thread hook) {
            Runtime.getRuntime().addShutdownHook(hook);
        }
    }

    /* access modifiers changed from: private */
    @GwtIncompatible
    public static void useDaemonThreadFactory(ThreadPoolExecutor executor) {
        executor.setThreadFactory(new ThreadFactoryBuilder().setDaemon(true).setThreadFactory(executor.getThreadFactory()).build());
    }

    @GwtIncompatible
    private static final class DirectExecutorService extends AbstractListeningExecutorService {
        private final Object lock;
        @GuardedBy("lock")
        private int runningTasks;
        @GuardedBy("lock")
        private boolean shutdown;

        private DirectExecutorService() {
            this.lock = new Object();
            this.runningTasks = 0;
            this.shutdown = false;
        }

        public void execute(Runnable command) {
            startTask();
            try {
                command.run();
            } finally {
                endTask();
            }
        }

        public boolean isShutdown() {
            boolean z;
            synchronized (this.lock) {
                z = this.shutdown;
            }
            return z;
        }

        public void shutdown() {
            synchronized (this.lock) {
                this.shutdown = true;
                if (this.runningTasks == 0) {
                    this.lock.notifyAll();
                }
            }
        }

        public List<Runnable> shutdownNow() {
            shutdown();
            return Collections.emptyList();
        }

        public boolean isTerminated() {
            boolean z;
            synchronized (this.lock) {
                z = this.shutdown && this.runningTasks == 0;
            }
            return z;
        }

        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            boolean z;
            long nanos = unit.toNanos(timeout);
            synchronized (this.lock) {
                while (true) {
                    if (this.shutdown && this.runningTasks == 0) {
                        z = true;
                        break;
                    } else if (nanos <= 0) {
                        z = false;
                        break;
                    } else {
                        long now = System.nanoTime();
                        TimeUnit.NANOSECONDS.timedWait(this.lock, nanos);
                        nanos -= System.nanoTime() - now;
                    }
                }
            }
            return z;
        }

        private void startTask() {
            synchronized (this.lock) {
                if (this.shutdown) {
                    throw new RejectedExecutionException("Executor already shutdown");
                }
                this.runningTasks++;
            }
        }

        private void endTask() {
            synchronized (this.lock) {
                int numRunning = this.runningTasks - 1;
                this.runningTasks = numRunning;
                if (numRunning == 0) {
                    this.lock.notifyAll();
                }
            }
        }
    }

    @GwtIncompatible
    public static ListeningExecutorService newDirectExecutorService() {
        return new DirectExecutorService();
    }

    public static Executor directExecutor() {
        return DirectExecutor.INSTANCE;
    }

    @GwtIncompatible
    @Beta
    public static Executor newSequentialExecutor(Executor delegate) {
        return new SequentialExecutor(delegate);
    }

    @GwtIncompatible
    public static ListeningExecutorService listeningDecorator(ExecutorService delegate) {
        if (delegate instanceof ListeningExecutorService) {
            return (ListeningExecutorService) delegate;
        }
        return delegate instanceof ScheduledExecutorService ? new ScheduledListeningDecorator((ScheduledExecutorService) delegate) : new ListeningDecorator(delegate);
    }

    @GwtIncompatible
    public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService delegate) {
        return delegate instanceof ListeningScheduledExecutorService ? (ListeningScheduledExecutorService) delegate : new ScheduledListeningDecorator(delegate);
    }

    @GwtIncompatible
    private static class ListeningDecorator extends AbstractListeningExecutorService {
        private final ExecutorService delegate;

        ListeningDecorator(ExecutorService delegate2) {
            this.delegate = (ExecutorService) Preconditions.checkNotNull(delegate2);
        }

        public final boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return this.delegate.awaitTermination(timeout, unit);
        }

        public final boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        public final boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        public final void shutdown() {
            this.delegate.shutdown();
        }

        public final List<Runnable> shutdownNow() {
            return this.delegate.shutdownNow();
        }

        public final void execute(Runnable command) {
            this.delegate.execute(command);
        }
    }

    @GwtIncompatible
    private static final class ScheduledListeningDecorator extends ListeningDecorator implements ListeningScheduledExecutorService {
        final ScheduledExecutorService delegate;

        ScheduledListeningDecorator(ScheduledExecutorService delegate2) {
            super(delegate2);
            this.delegate = (ScheduledExecutorService) Preconditions.checkNotNull(delegate2);
        }

        public ListenableScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
            TrustedListenableFutureTask<Void> task = TrustedListenableFutureTask.create(command, null);
            return new ListenableScheduledTask(task, this.delegate.schedule(task, delay, unit));
        }

        public <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
            TrustedListenableFutureTask<V> task = TrustedListenableFutureTask.create(callable);
            return new ListenableScheduledTask(task, this.delegate.schedule(task, delay, unit));
        }

        public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
            NeverSuccessfulListenableFutureTask task = new NeverSuccessfulListenableFutureTask(command);
            return new ListenableScheduledTask(task, this.delegate.scheduleAtFixedRate(task, initialDelay, period, unit));
        }

        public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
            NeverSuccessfulListenableFutureTask task = new NeverSuccessfulListenableFutureTask(command);
            return new ListenableScheduledTask(task, this.delegate.scheduleWithFixedDelay(task, initialDelay, delay, unit));
        }

        private static final class ListenableScheduledTask<V> extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V> implements ListenableScheduledFuture<V> {
            private final ScheduledFuture<?> scheduledDelegate;

            public ListenableScheduledTask(ListenableFuture<V> listenableDelegate, ScheduledFuture<?> scheduledDelegate2) {
                super(listenableDelegate);
                this.scheduledDelegate = scheduledDelegate2;
            }

            public boolean cancel(boolean mayInterruptIfRunning) {
                boolean cancelled = super.cancel(mayInterruptIfRunning);
                if (cancelled) {
                    this.scheduledDelegate.cancel(mayInterruptIfRunning);
                }
                return cancelled;
            }

            public long getDelay(TimeUnit unit) {
                return this.scheduledDelegate.getDelay(unit);
            }

            public int compareTo(Delayed other) {
                return this.scheduledDelegate.compareTo(other);
            }
        }

        @GwtIncompatible
        private static final class NeverSuccessfulListenableFutureTask extends AbstractFuture.TrustedFuture<Void> implements Runnable {
            private final Runnable delegate;

            public NeverSuccessfulListenableFutureTask(Runnable delegate2) {
                this.delegate = (Runnable) Preconditions.checkNotNull(delegate2);
            }

            public void run() {
                try {
                    this.delegate.run();
                } catch (Throwable t) {
                    setException(t);
                    throw Throwables.propagate(t);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008c, code lost:
        if (r6 != null) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r5 = new java.util.concurrent.ExecutionException((java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00eb, code lost:
        r5 = r6;
     */
    @com.google.common.annotations.GwtIncompatible
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> T invokeAnyImpl(com.google.common.util.concurrent.ListeningExecutorService r23, java.util.Collection<? extends java.util.concurrent.Callable<T>> r24, boolean r25, long r26, java.util.concurrent.TimeUnit r28) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException {
        /*
            com.google.common.base.Preconditions.checkNotNull(r23)
            com.google.common.base.Preconditions.checkNotNull(r28)
            int r16 = r24.size()
            if (r16 <= 0) goto L_0x0084
            r20 = 1
        L_0x000e:
            com.google.common.base.Preconditions.checkArgument(r20)
            java.util.ArrayList r10 = com.google.common.collect.Lists.newArrayListWithCapacity(r16)
            java.util.concurrent.LinkedBlockingQueue r9 = com.google.common.collect.Queues.newLinkedBlockingQueue()
            r0 = r28
            r1 = r26
            long r18 = r0.toNanos(r1)
            r5 = 0
            if (r25 == 0) goto L_0x0087
            long r12 = java.lang.System.nanoTime()     // Catch:{ all -> 0x0098 }
        L_0x0028:
            java.util.Iterator r11 = r24.iterator()     // Catch:{ all -> 0x0098 }
            java.lang.Object r20 = r11.next()     // Catch:{ all -> 0x0098 }
            java.util.concurrent.Callable r20 = (java.util.concurrent.Callable) r20     // Catch:{ all -> 0x0098 }
            r0 = r23
            r1 = r20
            com.google.common.util.concurrent.ListenableFuture r20 = submitAndAddQueueListener(r0, r1, r9)     // Catch:{ all -> 0x0098 }
            r0 = r20
            r10.add(r0)     // Catch:{ all -> 0x0098 }
            int r16 = r16 + -1
            r4 = 1
            r6 = r5
        L_0x0043:
            java.lang.Object r8 = r9.poll()     // Catch:{ all -> 0x00c7 }
            java.util.concurrent.Future r8 = (java.util.concurrent.Future) r8     // Catch:{ all -> 0x00c7 }
            if (r8 != 0) goto L_0x0064
            if (r16 <= 0) goto L_0x008a
            int r16 = r16 + -1
            java.lang.Object r20 = r11.next()     // Catch:{ all -> 0x00c7 }
            java.util.concurrent.Callable r20 = (java.util.concurrent.Callable) r20     // Catch:{ all -> 0x00c7 }
            r0 = r23
            r1 = r20
            com.google.common.util.concurrent.ListenableFuture r20 = submitAndAddQueueListener(r0, r1, r9)     // Catch:{ all -> 0x00c7 }
            r0 = r20
            r10.add(r0)     // Catch:{ all -> 0x00c7 }
            int r4 = r4 + 1
        L_0x0064:
            if (r8 == 0) goto L_0x00ed
            int r4 = r4 + -1
            java.lang.Object r20 = r8.get()     // Catch:{ ExecutionException -> 0x00db, RuntimeException -> 0x00e0 }
            java.util.Iterator r21 = r10.iterator()
        L_0x0070:
            boolean r22 = r21.hasNext()
            if (r22 == 0) goto L_0x00ea
            java.lang.Object r8 = r21.next()
            java.util.concurrent.Future r8 = (java.util.concurrent.Future) r8
            r22 = 1
            r0 = r22
            r8.cancel(r0)
            goto L_0x0070
        L_0x0084:
            r20 = 0
            goto L_0x000e
        L_0x0087:
            r12 = 0
            goto L_0x0028
        L_0x008a:
            if (r4 != 0) goto L_0x00b1
            if (r6 != 0) goto L_0x00eb
            java.util.concurrent.ExecutionException r5 = new java.util.concurrent.ExecutionException     // Catch:{ all -> 0x00c7 }
            r20 = 0
            r0 = r20
            r5.<init>(r0)     // Catch:{ all -> 0x00c7 }
        L_0x0097:
            throw r5     // Catch:{ all -> 0x0098 }
        L_0x0098:
            r20 = move-exception
        L_0x0099:
            java.util.Iterator r21 = r10.iterator()
        L_0x009d:
            boolean r22 = r21.hasNext()
            if (r22 == 0) goto L_0x00e9
            java.lang.Object r8 = r21.next()
            java.util.concurrent.Future r8 = (java.util.concurrent.Future) r8
            r22 = 1
            r0 = r22
            r8.cancel(r0)
            goto L_0x009d
        L_0x00b1:
            if (r25 == 0) goto L_0x00d4
            java.util.concurrent.TimeUnit r20 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ all -> 0x00c7 }
            r0 = r18
            r2 = r20
            java.lang.Object r8 = r9.poll(r0, r2)     // Catch:{ all -> 0x00c7 }
            java.util.concurrent.Future r8 = (java.util.concurrent.Future) r8     // Catch:{ all -> 0x00c7 }
            if (r8 != 0) goto L_0x00ca
            java.util.concurrent.TimeoutException r20 = new java.util.concurrent.TimeoutException     // Catch:{ all -> 0x00c7 }
            r20.<init>()     // Catch:{ all -> 0x00c7 }
            throw r20     // Catch:{ all -> 0x00c7 }
        L_0x00c7:
            r20 = move-exception
            r5 = r6
            goto L_0x0099
        L_0x00ca:
            long r14 = java.lang.System.nanoTime()     // Catch:{ all -> 0x00c7 }
            long r20 = r14 - r12
            long r18 = r18 - r20
            r12 = r14
            goto L_0x0064
        L_0x00d4:
            java.lang.Object r8 = r9.take()     // Catch:{ all -> 0x00c7 }
            java.util.concurrent.Future r8 = (java.util.concurrent.Future) r8     // Catch:{ all -> 0x00c7 }
            goto L_0x0064
        L_0x00db:
            r7 = move-exception
            r5 = r7
        L_0x00dd:
            r6 = r5
            goto L_0x0043
        L_0x00e0:
            r17 = move-exception
            java.util.concurrent.ExecutionException r5 = new java.util.concurrent.ExecutionException     // Catch:{ all -> 0x00c7 }
            r0 = r17
            r5.<init>(r0)     // Catch:{ all -> 0x00c7 }
            goto L_0x00dd
        L_0x00e9:
            throw r20
        L_0x00ea:
            return r20
        L_0x00eb:
            r5 = r6
            goto L_0x0097
        L_0x00ed:
            r5 = r6
            goto L_0x00dd
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.MoreExecutors.invokeAnyImpl(com.google.common.util.concurrent.ListeningExecutorService, java.util.Collection, boolean, long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    @GwtIncompatible
    private static <T> ListenableFuture<T> submitAndAddQueueListener(ListeningExecutorService executorService, Callable<T> task, final BlockingQueue<Future<T>> queue) {
        final ListenableFuture<T> future = executorService.submit(task);
        future.addListener(new Runnable() {
            public void run() {
                queue.add(future);
            }
        }, directExecutor());
        return future;
    }

    @GwtIncompatible
    @Beta
    public static ThreadFactory platformThreadFactory() {
        if (!isAppEngine()) {
            return Executors.defaultThreadFactory();
        }
        try {
            return (ThreadFactory) Class.forName("com.google.appengine.api.ThreadManager").getMethod("currentRequestThreadFactory", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e);
        } catch (InvocationTargetException e2) {
            throw Throwables.propagate(e2.getCause());
        }
    }

    @GwtIncompatible
    private static boolean isAppEngine() {
        if (System.getProperty("com.google.appengine.runtime.environment") == null) {
            return false;
        }
        try {
            if (Class.forName("com.google.apphosting.api.ApiProxy").getMethod("getCurrentEnvironment", new Class[0]).invoke((Object) null, new Object[0]) != null) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            return false;
        }
    }

    @GwtIncompatible
    static Thread newThread(String name, Runnable runnable) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(runnable);
        Thread result = platformThreadFactory().newThread(runnable);
        try {
            result.setName(name);
        } catch (SecurityException e) {
        }
        return result;
    }

    @GwtIncompatible
    static Executor renamingDecorator(final Executor executor, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(nameSupplier);
        return isAppEngine() ? executor : new Executor() {
            public void execute(Runnable command) {
                executor.execute(Callables.threadRenaming(command, (Supplier<String>) nameSupplier));
            }
        };
    }

    @GwtIncompatible
    static ExecutorService renamingDecorator(ExecutorService service, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(service);
        Preconditions.checkNotNull(nameSupplier);
        return isAppEngine() ? service : new WrappingExecutorService(service) {
            /* access modifiers changed from: protected */
            public <T> Callable<T> wrapTask(Callable<T> callable) {
                return Callables.threadRenaming(callable, (Supplier<String>) nameSupplier);
            }

            /* access modifiers changed from: protected */
            public Runnable wrapTask(Runnable command) {
                return Callables.threadRenaming(command, (Supplier<String>) nameSupplier);
            }
        };
    }

    @GwtIncompatible
    static ScheduledExecutorService renamingDecorator(ScheduledExecutorService service, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(service);
        Preconditions.checkNotNull(nameSupplier);
        return isAppEngine() ? service : new WrappingScheduledExecutorService(service) {
            /* access modifiers changed from: protected */
            public <T> Callable<T> wrapTask(Callable<T> callable) {
                return Callables.threadRenaming(callable, (Supplier<String>) nameSupplier);
            }

            /* access modifiers changed from: protected */
            public Runnable wrapTask(Runnable command) {
                return Callables.threadRenaming(command, (Supplier<String>) nameSupplier);
            }
        };
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @Beta
    public static boolean shutdownAndAwaitTermination(ExecutorService service, long timeout, TimeUnit unit) {
        long halfTimeoutNanos = unit.toNanos(timeout) / 2;
        service.shutdown();
        try {
            if (!service.awaitTermination(halfTimeoutNanos, TimeUnit.NANOSECONDS)) {
                service.shutdownNow();
                service.awaitTermination(halfTimeoutNanos, TimeUnit.NANOSECONDS);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            service.shutdownNow();
        }
        return service.isTerminated();
    }

    static Executor rejectionPropagatingExecutor(final Executor delegate, final AbstractFuture<?> future) {
        Preconditions.checkNotNull(delegate);
        Preconditions.checkNotNull(future);
        return delegate == directExecutor() ? delegate : new Executor() {
            boolean thrownFromDelegate = true;

            public void execute(final Runnable command) {
                try {
                    delegate.execute(new Runnable() {
                        public void run() {
                            C14175.this.thrownFromDelegate = false;
                            command.run();
                        }
                    });
                } catch (RejectedExecutionException e) {
                    if (this.thrownFromDelegate) {
                        future.setException(e);
                    }
                }
            }
        };
    }
}
