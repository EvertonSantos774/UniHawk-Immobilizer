package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

abstract class Dispatcher {
    /* access modifiers changed from: package-private */
    public abstract void dispatch(Object obj, Iterator<Subscriber> it);

    Dispatcher() {
    }

    static Dispatcher perThreadDispatchQueue() {
        return new PerThreadQueuedDispatcher();
    }

    static Dispatcher legacyAsync() {
        return new LegacyAsyncDispatcher();
    }

    static Dispatcher immediate() {
        return ImmediateDispatcher.INSTANCE;
    }

    private static final class PerThreadQueuedDispatcher extends Dispatcher {
        private final ThreadLocal<Boolean> dispatching;
        private final ThreadLocal<Queue<Event>> queue;

        private PerThreadQueuedDispatcher() {
            this.queue = new ThreadLocal<Queue<Event>>() {
                /* access modifiers changed from: protected */
                public Queue<Event> initialValue() {
                    return Queues.newArrayDeque();
                }
            };
            this.dispatching = new ThreadLocal<Boolean>() {
                /* access modifiers changed from: protected */
                public Boolean initialValue() {
                    return false;
                }
            };
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x005f A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:6:0x0037 A[Catch:{ all -> 0x0053 }, LOOP:1: B:6:0x0037->B:8:0x0041, LOOP_START] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void dispatch(java.lang.Object r5, java.util.Iterator<com.google.common.eventbus.Subscriber> r6) {
            /*
                r4 = this;
                com.google.common.base.Preconditions.checkNotNull(r5)
                com.google.common.base.Preconditions.checkNotNull(r6)
                java.lang.ThreadLocal<java.util.Queue<com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event>> r2 = r4.queue
                java.lang.Object r1 = r2.get()
                java.util.Queue r1 = (java.util.Queue) r1
                com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event r2 = new com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event
                r3 = 0
                r2.<init>(r5, r6)
                r1.offer(r2)
                java.lang.ThreadLocal<java.lang.Boolean> r2 = r4.dispatching
                java.lang.Object r2 = r2.get()
                java.lang.Boolean r2 = (java.lang.Boolean) r2
                boolean r2 = r2.booleanValue()
                if (r2 != 0) goto L_0x0069
                java.lang.ThreadLocal<java.lang.Boolean> r2 = r4.dispatching
                r3 = 1
                java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
                r2.set(r3)
            L_0x002f:
                java.lang.Object r0 = r1.poll()     // Catch:{ all -> 0x0053 }
                com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event r0 = (com.google.common.eventbus.Dispatcher.PerThreadQueuedDispatcher.Event) r0     // Catch:{ all -> 0x0053 }
                if (r0 == 0) goto L_0x005f
            L_0x0037:
                java.util.Iterator r2 = r0.subscribers     // Catch:{ all -> 0x0053 }
                boolean r2 = r2.hasNext()     // Catch:{ all -> 0x0053 }
                if (r2 == 0) goto L_0x002f
                java.util.Iterator r2 = r0.subscribers     // Catch:{ all -> 0x0053 }
                java.lang.Object r2 = r2.next()     // Catch:{ all -> 0x0053 }
                com.google.common.eventbus.Subscriber r2 = (com.google.common.eventbus.Subscriber) r2     // Catch:{ all -> 0x0053 }
                java.lang.Object r3 = r0.event     // Catch:{ all -> 0x0053 }
                r2.dispatchEvent(r3)     // Catch:{ all -> 0x0053 }
                goto L_0x0037
            L_0x0053:
                r2 = move-exception
                java.lang.ThreadLocal<java.lang.Boolean> r3 = r4.dispatching
                r3.remove()
                java.lang.ThreadLocal<java.util.Queue<com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event>> r3 = r4.queue
                r3.remove()
                throw r2
            L_0x005f:
                java.lang.ThreadLocal<java.lang.Boolean> r2 = r4.dispatching
                r2.remove()
                java.lang.ThreadLocal<java.util.Queue<com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event>> r2 = r4.queue
                r2.remove()
            L_0x0069:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.eventbus.Dispatcher.PerThreadQueuedDispatcher.dispatch(java.lang.Object, java.util.Iterator):void");
        }

        private static final class Event {
            /* access modifiers changed from: private */
            public final Object event;
            /* access modifiers changed from: private */
            public final Iterator<Subscriber> subscribers;

            private Event(Object event2, Iterator<Subscriber> subscribers2) {
                this.event = event2;
                this.subscribers = subscribers2;
            }
        }
    }

    private static final class LegacyAsyncDispatcher extends Dispatcher {
        private final ConcurrentLinkedQueue<EventWithSubscriber> queue;

        private LegacyAsyncDispatcher() {
            this.queue = Queues.newConcurrentLinkedQueue();
        }

        /* access modifiers changed from: package-private */
        public void dispatch(Object event, Iterator<Subscriber> subscribers) {
            Preconditions.checkNotNull(event);
            while (subscribers.hasNext()) {
                this.queue.add(new EventWithSubscriber(event, subscribers.next()));
            }
            while (true) {
                EventWithSubscriber e = this.queue.poll();
                if (e != null) {
                    e.subscriber.dispatchEvent(e.event);
                } else {
                    return;
                }
            }
        }

        private static final class EventWithSubscriber {
            /* access modifiers changed from: private */
            public final Object event;
            /* access modifiers changed from: private */
            public final Subscriber subscriber;

            private EventWithSubscriber(Object event2, Subscriber subscriber2) {
                this.event = event2;
                this.subscriber = subscriber2;
            }
        }
    }

    private static final class ImmediateDispatcher extends Dispatcher {
        /* access modifiers changed from: private */
        public static final ImmediateDispatcher INSTANCE = new ImmediateDispatcher();

        private ImmediateDispatcher() {
        }

        /* access modifiers changed from: package-private */
        public void dispatch(Object event, Iterator<Subscriber> subscribers) {
            Preconditions.checkNotNull(event);
            while (subscribers.hasNext()) {
                subscribers.next().dispatchEvent(event);
            }
        }
    }
}
