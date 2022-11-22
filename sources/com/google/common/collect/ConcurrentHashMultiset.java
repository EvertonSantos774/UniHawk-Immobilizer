package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Serialization;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
public final class ConcurrentHashMultiset<E> extends AbstractMultiset<E> implements Serializable {
    private static final long serialVersionUID = 1;
    /* access modifiers changed from: private */
    public final transient ConcurrentMap<E, AtomicInteger> countMap;

    public /* bridge */ /* synthetic */ boolean contains(@NullableDecl Object obj) {
        return super.contains(obj);
    }

    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    private static class FieldSettersHolder {
        static final Serialization.FieldSetter<ConcurrentHashMultiset> COUNT_MAP_FIELD_SETTER = Serialization.getFieldSetter(ConcurrentHashMultiset.class, "countMap");

        private FieldSettersHolder() {
        }
    }

    public static <E> ConcurrentHashMultiset<E> create() {
        return new ConcurrentHashMultiset<>(new ConcurrentHashMap());
    }

    public static <E> ConcurrentHashMultiset<E> create(Iterable<? extends E> elements) {
        ConcurrentHashMultiset<E> multiset = create();
        Iterables.addAll(multiset, elements);
        return multiset;
    }

    @Beta
    public static <E> ConcurrentHashMultiset<E> create(ConcurrentMap<E, AtomicInteger> countMap2) {
        return new ConcurrentHashMultiset<>(countMap2);
    }

    @VisibleForTesting
    ConcurrentHashMultiset(ConcurrentMap<E, AtomicInteger> countMap2) {
        Preconditions.checkArgument(countMap2.isEmpty(), "the backing map (%s) must be empty", (Object) countMap2);
        this.countMap = countMap2;
    }

    public int count(@NullableDecl Object element) {
        AtomicInteger existingCounter = (AtomicInteger) Maps.safeGet(this.countMap, element);
        if (existingCounter == null) {
            return 0;
        }
        return existingCounter.get();
    }

    public int size() {
        long sum = 0;
        for (AtomicInteger value : this.countMap.values()) {
            sum += (long) value.get();
        }
        return Ints.saturatedCast(sum);
    }

    public Object[] toArray() {
        return snapshot().toArray();
    }

    public <T> T[] toArray(T[] array) {
        return snapshot().toArray(array);
    }

    private List<E> snapshot() {
        List<E> list = Lists.newArrayListWithExpectedSize(size());
        for (Multiset.Entry<E> entry : entrySet()) {
            E element = entry.getElement();
            for (int i = entry.getCount(); i > 0; i--) {
                list.add(element);
            }
        }
        return list;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0060, code lost:
        r1 = new java.util.concurrent.atomic.AtomicInteger(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006b, code lost:
        if (r8.countMap.putIfAbsent(r9, r1) == null) goto L_0x0075;
     */
    @com.google.errorprone.annotations.CanIgnoreReturnValue
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int add(E r9, int r10) {
        /*
            r8 = this;
            r5 = 0
            com.google.common.base.Preconditions.checkNotNull(r9)
            if (r10 != 0) goto L_0x000b
            int r3 = r8.count(r9)
        L_0x000a:
            return r3
        L_0x000b:
            java.lang.String r6 = "occurences"
            com.google.common.collect.CollectPreconditions.checkPositive(r10, r6)
        L_0x0010:
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r6 = r8.countMap
            java.lang.Object r0 = com.google.common.collect.Maps.safeGet(r6, r9)
            java.util.concurrent.atomic.AtomicInteger r0 = (java.util.concurrent.atomic.AtomicInteger) r0
            if (r0 != 0) goto L_0x002b
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r6 = r8.countMap
            java.util.concurrent.atomic.AtomicInteger r7 = new java.util.concurrent.atomic.AtomicInteger
            r7.<init>(r10)
            java.lang.Object r0 = r6.putIfAbsent(r9, r7)
            java.util.concurrent.atomic.AtomicInteger r0 = (java.util.concurrent.atomic.AtomicInteger) r0
            if (r0 != 0) goto L_0x002b
            r3 = r5
            goto L_0x000a
        L_0x002b:
            int r3 = r0.get()
            if (r3 == 0) goto L_0x0060
            int r2 = com.google.common.math.IntMath.checkedAdd(r3, r10)     // Catch:{ ArithmeticException -> 0x003c }
            boolean r6 = r0.compareAndSet(r3, r2)     // Catch:{ ArithmeticException -> 0x003c }
            if (r6 == 0) goto L_0x002b
            goto L_0x000a
        L_0x003c:
            r4 = move-exception
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Overflow adding "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r10)
            java.lang.String r7 = " occurrences to a count of "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r3)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L_0x0060:
            java.util.concurrent.atomic.AtomicInteger r1 = new java.util.concurrent.atomic.AtomicInteger
            r1.<init>(r10)
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r6 = r8.countMap
            java.lang.Object r6 = r6.putIfAbsent(r9, r1)
            if (r6 == 0) goto L_0x0075
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r6 = r8.countMap
            boolean r6 = r6.replace(r9, r0, r1)
            if (r6 == 0) goto L_0x0010
        L_0x0075:
            r3 = r5
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ConcurrentHashMultiset.add(java.lang.Object, int):int");
    }

    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object element, int occurrences) {
        int oldValue;
        int newValue;
        if (occurrences == 0) {
            return count(element);
        }
        CollectPreconditions.checkPositive(occurrences, "occurences");
        AtomicInteger existingCounter = (AtomicInteger) Maps.safeGet(this.countMap, element);
        if (existingCounter == null) {
            return 0;
        }
        do {
            oldValue = existingCounter.get();
            if (oldValue == 0) {
                return 0;
            }
            newValue = Math.max(0, oldValue - occurrences);
        } while (!existingCounter.compareAndSet(oldValue, newValue));
        if (newValue != 0) {
            return oldValue;
        }
        this.countMap.remove(element, existingCounter);
        return oldValue;
    }

    @CanIgnoreReturnValue
    public boolean removeExactly(@NullableDecl Object element, int occurrences) {
        int oldValue;
        int newValue;
        if (occurrences == 0) {
            return true;
        }
        CollectPreconditions.checkPositive(occurrences, "occurences");
        AtomicInteger existingCounter = (AtomicInteger) Maps.safeGet(this.countMap, element);
        if (existingCounter == null) {
            return false;
        }
        do {
            oldValue = existingCounter.get();
            if (oldValue < occurrences) {
                return false;
            }
            newValue = oldValue - occurrences;
        } while (!existingCounter.compareAndSet(oldValue, newValue));
        if (newValue != 0) {
            return true;
        }
        this.countMap.remove(element, existingCounter);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        if (r8 != 0) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
        r1 = new java.util.concurrent.atomic.AtomicInteger(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003d, code lost:
        if (r6.countMap.putIfAbsent(r7, r1) == null) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return 0;
     */
    @com.google.errorprone.annotations.CanIgnoreReturnValue
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int setCount(E r7, int r8) {
        /*
            r6 = this;
            r3 = 0
            com.google.common.base.Preconditions.checkNotNull(r7)
            java.lang.String r4 = "count"
            com.google.common.collect.CollectPreconditions.checkNonnegative((int) r8, (java.lang.String) r4)
        L_0x0009:
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r4 = r6.countMap
            java.lang.Object r0 = com.google.common.collect.Maps.safeGet(r4, r7)
            java.util.concurrent.atomic.AtomicInteger r0 = (java.util.concurrent.atomic.AtomicInteger) r0
            if (r0 != 0) goto L_0x0028
            if (r8 != 0) goto L_0x0017
            r2 = r3
        L_0x0016:
            return r2
        L_0x0017:
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r4 = r6.countMap
            java.util.concurrent.atomic.AtomicInteger r5 = new java.util.concurrent.atomic.AtomicInteger
            r5.<init>(r8)
            java.lang.Object r0 = r4.putIfAbsent(r7, r5)
            java.util.concurrent.atomic.AtomicInteger r0 = (java.util.concurrent.atomic.AtomicInteger) r0
            if (r0 != 0) goto L_0x0028
            r2 = r3
            goto L_0x0016
        L_0x0028:
            int r2 = r0.get()
            if (r2 != 0) goto L_0x0049
            if (r8 != 0) goto L_0x0032
            r2 = r3
            goto L_0x0016
        L_0x0032:
            java.util.concurrent.atomic.AtomicInteger r1 = new java.util.concurrent.atomic.AtomicInteger
            r1.<init>(r8)
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r4 = r6.countMap
            java.lang.Object r4 = r4.putIfAbsent(r7, r1)
            if (r4 == 0) goto L_0x0047
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r4 = r6.countMap
            boolean r4 = r4.replace(r7, r0, r1)
            if (r4 == 0) goto L_0x0009
        L_0x0047:
            r2 = r3
            goto L_0x0016
        L_0x0049:
            boolean r4 = r0.compareAndSet(r2, r8)
            if (r4 == 0) goto L_0x0028
            if (r8 != 0) goto L_0x0016
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r3 = r6.countMap
            r3.remove(r7, r0)
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ConcurrentHashMultiset.setCount(java.lang.Object, int):int");
    }

    @CanIgnoreReturnValue
    public boolean setCount(E element, int expectedOldCount, int newCount) {
        boolean z = false;
        Preconditions.checkNotNull(element);
        CollectPreconditions.checkNonnegative(expectedOldCount, "oldCount");
        CollectPreconditions.checkNonnegative(newCount, "newCount");
        AtomicInteger existingCounter = (AtomicInteger) Maps.safeGet(this.countMap, element);
        if (existingCounter != null) {
            int oldValue = existingCounter.get();
            if (oldValue == expectedOldCount) {
                if (oldValue == 0) {
                    if (newCount == 0) {
                        this.countMap.remove(element, existingCounter);
                        return true;
                    }
                    AtomicInteger newCounter = new AtomicInteger(newCount);
                    if (this.countMap.putIfAbsent(element, newCounter) == null || this.countMap.replace(element, existingCounter, newCounter)) {
                        z = true;
                    }
                    return z;
                } else if (existingCounter.compareAndSet(oldValue, newCount)) {
                    if (newCount != 0) {
                        return true;
                    }
                    this.countMap.remove(element, existingCounter);
                    return true;
                }
            }
            return false;
        } else if (expectedOldCount != 0) {
            return false;
        } else {
            if (newCount == 0 || this.countMap.putIfAbsent(element, new AtomicInteger(newCount)) == null) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public Set<E> createElementSet() {
        final Set<E> delegate = this.countMap.keySet();
        return new ForwardingSet<E>() {
            /* access modifiers changed from: protected */
            public Set<E> delegate() {
                return delegate;
            }

            public boolean contains(@NullableDecl Object object) {
                return object != null && Collections2.safeContains(delegate, object);
            }

            public boolean containsAll(Collection<?> collection) {
                return standardContainsAll(collection);
            }

            public boolean remove(Object object) {
                return object != null && Collections2.safeRemove(delegate, object);
            }

            public boolean removeAll(Collection<?> c) {
                return standardRemoveAll(c);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public Iterator<E> elementIterator() {
        throw new AssertionError("should never be called");
    }

    @Deprecated
    public Set<Multiset.Entry<E>> createEntrySet() {
        return new EntrySet();
    }

    /* access modifiers changed from: package-private */
    public int distinctElements() {
        return this.countMap.size();
    }

    public boolean isEmpty() {
        return this.countMap.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public Iterator<Multiset.Entry<E>> entryIterator() {
        final Iterator<Multiset.Entry<E>> readOnlyIterator = new AbstractIterator<Multiset.Entry<E>>() {
            private final Iterator<Map.Entry<E, AtomicInteger>> mapEntries = ConcurrentHashMultiset.this.countMap.entrySet().iterator();

            /* access modifiers changed from: protected */
            public Multiset.Entry<E> computeNext() {
                while (this.mapEntries.hasNext()) {
                    Map.Entry<E, AtomicInteger> mapEntry = this.mapEntries.next();
                    int count = mapEntry.getValue().get();
                    if (count != 0) {
                        return Multisets.immutableEntry(mapEntry.getKey(), count);
                    }
                }
                return (Multiset.Entry) endOfData();
            }
        };
        return new ForwardingIterator<Multiset.Entry<E>>() {
            @NullableDecl
            private Multiset.Entry<E> last;

            /* access modifiers changed from: protected */
            public Iterator<Multiset.Entry<E>> delegate() {
                return readOnlyIterator;
            }

            public Multiset.Entry<E> next() {
                this.last = (Multiset.Entry) super.next();
                return this.last;
            }

            public void remove() {
                boolean z;
                if (this.last != null) {
                    z = true;
                } else {
                    z = false;
                }
                CollectPreconditions.checkRemove(z);
                ConcurrentHashMultiset.this.setCount(this.last.getElement(), 0);
                this.last = null;
            }
        };
    }

    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    public void clear() {
        this.countMap.clear();
    }

    private class EntrySet extends AbstractMultiset<E>.EntrySet {
        private EntrySet() {
            super();
        }

        /* access modifiers changed from: package-private */
        public ConcurrentHashMultiset<E> multiset() {
            return ConcurrentHashMultiset.this;
        }

        public Object[] toArray() {
            return snapshot().toArray();
        }

        public <T> T[] toArray(T[] array) {
            return snapshot().toArray(array);
        }

        private List<Multiset.Entry<E>> snapshot() {
            List<Multiset.Entry<E>> list = Lists.newArrayListWithExpectedSize(size());
            Iterators.addAll(list, iterator());
            return list;
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.countMap);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        FieldSettersHolder.COUNT_MAP_FIELD_SETTER.set(this, (Object) (ConcurrentMap) stream.readObject());
    }
}
