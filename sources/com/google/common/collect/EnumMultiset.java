package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Enum;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
public final class EnumMultiset<E extends Enum<E>> extends AbstractMultiset<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public transient int[] counts = new int[this.enumConstants.length];
    private transient int distinctElements;
    /* access modifiers changed from: private */
    public transient E[] enumConstants;
    /* access modifiers changed from: private */
    public transient long size;
    private transient Class<E> type;

    public /* bridge */ /* synthetic */ boolean contains(@NullableDecl Object obj) {
        return super.contains(obj);
    }

    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    static /* synthetic */ int access$210(EnumMultiset x0) {
        int i = x0.distinctElements;
        x0.distinctElements = i - 1;
        return i;
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Class<E> type2) {
        return new EnumMultiset<>(type2);
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> elements) {
        Iterator<E> iterator = elements.iterator();
        Preconditions.checkArgument(iterator.hasNext(), "EnumMultiset constructor passed empty Iterable");
        EnumMultiset<E> multiset = new EnumMultiset<>(((Enum) iterator.next()).getDeclaringClass());
        Iterables.addAll(multiset, elements);
        return multiset;
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> elements, Class<E> type2) {
        EnumMultiset<E> result = create(type2);
        Iterables.addAll(result, elements);
        return result;
    }

    private EnumMultiset(Class<E> type2) {
        this.type = type2;
        Preconditions.checkArgument(type2.isEnum());
        this.enumConstants = (Enum[]) type2.getEnumConstants();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = (java.lang.Enum) r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isActuallyE(@org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object r5) {
        /*
            r4 = this;
            r2 = 0
            boolean r3 = r5 instanceof java.lang.Enum
            if (r3 == 0) goto L_0x0018
            r0 = r5
            java.lang.Enum r0 = (java.lang.Enum) r0
            int r1 = r0.ordinal()
            E[] r3 = r4.enumConstants
            int r3 = r3.length
            if (r1 >= r3) goto L_0x0018
            E[] r3 = r4.enumConstants
            r3 = r3[r1]
            if (r3 != r0) goto L_0x0018
            r2 = 1
        L_0x0018:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.EnumMultiset.isActuallyE(java.lang.Object):boolean");
    }

    /* access modifiers changed from: package-private */
    public void checkIsE(@NullableDecl Object element) {
        Preconditions.checkNotNull(element);
        if (!isActuallyE(element)) {
            throw new ClassCastException("Expected an " + this.type + " but got " + element);
        }
    }

    /* access modifiers changed from: package-private */
    public int distinctElements() {
        return this.distinctElements;
    }

    public int size() {
        return Ints.saturatedCast(this.size);
    }

    public int count(@NullableDecl Object element) {
        if (element == null || !isActuallyE(element)) {
            return 0;
        }
        return this.counts[((Enum) element).ordinal()];
    }

    @CanIgnoreReturnValue
    public int add(E element, int occurrences) {
        checkIsE(element);
        CollectPreconditions.checkNonnegative(occurrences, "occurrences");
        if (occurrences == 0) {
            return count(element);
        }
        int index = element.ordinal();
        int oldCount = this.counts[index];
        long newCount = ((long) oldCount) + ((long) occurrences);
        Preconditions.checkArgument(newCount <= 2147483647L, "too many occurrences: %s", newCount);
        this.counts[index] = (int) newCount;
        if (oldCount == 0) {
            this.distinctElements++;
        }
        this.size += (long) occurrences;
        return oldCount;
    }

    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object element, int occurrences) {
        if (element == null || !isActuallyE(element)) {
            return 0;
        }
        Enum<?> e = (Enum) element;
        CollectPreconditions.checkNonnegative(occurrences, "occurrences");
        if (occurrences == 0) {
            return count(element);
        }
        int index = e.ordinal();
        int oldCount = this.counts[index];
        if (oldCount == 0) {
            return 0;
        }
        if (oldCount <= occurrences) {
            this.counts[index] = 0;
            this.distinctElements--;
            this.size -= (long) oldCount;
            return oldCount;
        }
        this.counts[index] = oldCount - occurrences;
        this.size -= (long) occurrences;
        return oldCount;
    }

    @CanIgnoreReturnValue
    public int setCount(E element, int count) {
        checkIsE(element);
        CollectPreconditions.checkNonnegative(count, "count");
        int index = element.ordinal();
        int oldCount = this.counts[index];
        this.counts[index] = count;
        this.size += (long) (count - oldCount);
        if (oldCount == 0 && count > 0) {
            this.distinctElements++;
        } else if (oldCount > 0 && count == 0) {
            this.distinctElements--;
        }
        return oldCount;
    }

    public void clear() {
        Arrays.fill(this.counts, 0);
        this.size = 0;
        this.distinctElements = 0;
    }

    abstract class Itr<T> implements Iterator<T> {
        int index = 0;
        int toRemove = -1;

        /* access modifiers changed from: package-private */
        public abstract T output(int i);

        Itr() {
        }

        public boolean hasNext() {
            while (this.index < EnumMultiset.this.enumConstants.length) {
                if (EnumMultiset.this.counts[this.index] > 0) {
                    return true;
                }
                this.index++;
            }
            return false;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T result = output(this.index);
            this.toRemove = this.index;
            this.index++;
            return result;
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.toRemove >= 0);
            if (EnumMultiset.this.counts[this.toRemove] > 0) {
                EnumMultiset.access$210(EnumMultiset.this);
                long unused = EnumMultiset.this.size = EnumMultiset.this.size - ((long) EnumMultiset.this.counts[this.toRemove]);
                EnumMultiset.this.counts[this.toRemove] = 0;
            }
            this.toRemove = -1;
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<E> elementIterator() {
        return new EnumMultiset<E>.Itr<E>() {
            /* access modifiers changed from: package-private */
            public E output(int index) {
                return EnumMultiset.this.enumConstants[index];
            }
        };
    }

    /* access modifiers changed from: package-private */
    public Iterator<Multiset.Entry<E>> entryIterator() {
        return new EnumMultiset<E>.Itr<Multiset.Entry<E>>() {
            /* access modifiers changed from: package-private */
            public Multiset.Entry<E> output(final int index) {
                return new Multisets.AbstractEntry<E>() {
                    public E getElement() {
                        return EnumMultiset.this.enumConstants[index];
                    }

                    public int getCount() {
                        return EnumMultiset.this.counts[index];
                    }
                };
            }
        };
    }

    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.type);
        Serialization.writeMultiset(this, stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.type = (Class) stream.readObject();
        this.enumConstants = (Enum[]) this.type.getEnumConstants();
        this.counts = new int[this.enumConstants.length];
        Serialization.populateMultiset(this, stream);
    }
}
