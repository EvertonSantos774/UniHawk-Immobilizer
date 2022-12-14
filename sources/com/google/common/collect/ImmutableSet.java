package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private static final int CUTOFF = 751619276;
    private static final double DESIRED_LOAD_FACTOR = 0.7d;
    static final int MAX_TABLE_SIZE = 1073741824;
    @NullableDecl
    @RetainedWith
    @LazyInit
    private transient ImmutableList<E> asList;

    public abstract UnmodifiableIterator<E> iterator();

    /* renamed from: of */
    public static <E> ImmutableSet<E> m122of() {
        return RegularImmutableSet.EMPTY;
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m123of(E element) {
        return new SingletonImmutableSet(element);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m124of(E e1, E e2) {
        return construct(2, e1, e2);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m125of(E e1, E e2, E e3) {
        return construct(3, e1, e2, e3);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m126of(E e1, E e2, E e3, E e4) {
        return construct(4, e1, e2, e3, e4);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m127of(E e1, E e2, E e3, E e4, E e5) {
        return construct(5, e1, e2, e3, e4, e5);
    }

    @SafeVarargs
    /* renamed from: of */
    public static <E> ImmutableSet<E> m128of(E e1, E e2, E e3, E e4, E e5, E e6, E... others) {
        boolean z;
        if (others.length <= 2147483641) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "the total number of elements must fit in an int");
        Object[] elements = new Object[(others.length + 6)];
        elements[0] = e1;
        elements[1] = e2;
        elements[2] = e3;
        elements[3] = e4;
        elements[4] = e5;
        elements[5] = e6;
        System.arraycopy(others, 0, elements, 6, others.length);
        return construct(elements.length, elements);
    }

    /* access modifiers changed from: private */
    public static <E> ImmutableSet<E> construct(int n, Object... elements) {
        Object[] uniqueElements;
        switch (n) {
            case 0:
                return m122of();
            case 1:
                return m123of(elements[0]);
            default:
                int tableSize = chooseTableSize(n);
                Object[] table = new Object[tableSize];
                int mask = tableSize - 1;
                int hashCode = 0;
                int uniques = 0;
                for (int i = 0; i < n; i++) {
                    Object element = ObjectArrays.checkElementNotNull(elements[i], i);
                    int hash = element.hashCode();
                    int j = Hashing.smear(hash);
                    while (true) {
                        int index = j & mask;
                        Object value = table[index];
                        if (value == null) {
                            elements[uniques] = element;
                            table[index] = element;
                            hashCode += hash;
                            uniques++;
                        } else if (!value.equals(element)) {
                            j++;
                        }
                    }
                }
                Arrays.fill(elements, uniques, n, (Object) null);
                if (uniques == 1) {
                    return new SingletonImmutableSet(elements[0], hashCode);
                }
                if (chooseTableSize(uniques) < tableSize / 2) {
                    return construct(uniques, elements);
                }
                if (shouldTrim(uniques, elements.length)) {
                    uniqueElements = Arrays.copyOf(elements, uniques);
                } else {
                    uniqueElements = elements;
                }
                return new RegularImmutableSet(uniqueElements, hashCode, table, mask, uniques);
        }
    }

    /* access modifiers changed from: private */
    public static boolean shouldTrim(int actualUnique, int expectedUnique) {
        return actualUnique < (expectedUnique >> 1) + (expectedUnique >> 2);
    }

    @VisibleForTesting
    static int chooseTableSize(int setSize) {
        int tableSize = 1073741824;
        int setSize2 = Math.max(setSize, 2);
        if (setSize2 < CUTOFF) {
            tableSize = Integer.highestOneBit(setSize2 - 1) << 1;
            while (((double) tableSize) * DESIRED_LOAD_FACTOR < ((double) setSize2)) {
                tableSize <<= 1;
            }
        } else {
            Preconditions.checkArgument(setSize2 < 1073741824, "collection too large");
        }
        return tableSize;
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> elements) {
        if ((elements instanceof ImmutableSet) && !(elements instanceof SortedSet)) {
            ImmutableSet<E> set = (ImmutableSet) elements;
            if (!set.isPartialView()) {
                return set;
            }
        }
        Object[] array = elements.toArray();
        return construct(array.length, array);
    }

    public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return copyOf((Collection) elements);
        }
        return copyOf(elements.iterator());
    }

    public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> elements) {
        if (!elements.hasNext()) {
            return m122of();
        }
        E first = elements.next();
        if (!elements.hasNext()) {
            return m123of(first);
        }
        return new Builder().add((Object) first).addAll((Iterator) elements).build();
    }

    public static <E> ImmutableSet<E> copyOf(E[] elements) {
        switch (elements.length) {
            case 0:
                return m122of();
            case 1:
                return m123of(elements[0]);
            default:
                return construct(elements.length, (Object[]) elements.clone());
        }
    }

    ImmutableSet() {
    }

    /* access modifiers changed from: package-private */
    public boolean isHashCodeFast() {
        return false;
    }

    public boolean equals(@NullableDecl Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof ImmutableSet) || !isHashCodeFast() || !((ImmutableSet) object).isHashCodeFast() || hashCode() == object.hashCode()) {
            return Sets.equalsImpl(this, object);
        }
        return false;
    }

    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    public ImmutableList<E> asList() {
        ImmutableList<E> result = this.asList;
        if (result != null) {
            return result;
        }
        ImmutableList<E> result2 = createAsList();
        this.asList = result2;
        return result2;
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> createAsList() {
        return ImmutableList.asImmutableList(toArray());
    }

    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] elements2) {
            this.elements = elements2;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return ImmutableSet.copyOf((E[]) this.elements);
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    @Beta
    public static <E> Builder<E> builderWithExpectedSize(int expectedSize) {
        CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
        return new Builder<>(expectedSize);
    }

    public static class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        private int hashCode;
        @NullableDecl
        @VisibleForTesting
        Object[] hashTable;

        public Builder() {
            super(4);
        }

        Builder(int capacity) {
            super(capacity);
            this.hashTable = new Object[ImmutableSet.chooseTableSize(capacity)];
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E element) {
            Preconditions.checkNotNull(element);
            if (this.hashTable == null || ImmutableSet.chooseTableSize(this.size) > this.hashTable.length) {
                this.hashTable = null;
                super.add(element);
            } else {
                addDeduping(element);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E... elements) {
            if (this.hashTable != null) {
                for (E e : elements) {
                    add((Object) e);
                }
            } else {
                super.add(elements);
            }
            return this;
        }

        private void addDeduping(E element) {
            int mask = this.hashTable.length - 1;
            int hash = element.hashCode();
            int i = Hashing.smear(hash);
            while (true) {
                int i2 = i & mask;
                Object previous = this.hashTable[i2];
                if (previous == null) {
                    this.hashTable[i2] = element;
                    this.hashCode += hash;
                    super.add(element);
                    return;
                } else if (!previous.equals(element)) {
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> elements) {
            Preconditions.checkNotNull(elements);
            if (this.hashTable != null) {
                for (E e : elements) {
                    add((Object) e);
                }
            } else {
                super.addAll(elements);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> elements) {
            Preconditions.checkNotNull(elements);
            while (elements.hasNext()) {
                add((Object) elements.next());
            }
            return this;
        }

        public ImmutableSet<E> build() {
            ImmutableSet<E> result;
            switch (this.size) {
                case 0:
                    return ImmutableSet.m122of();
                case 1:
                    return ImmutableSet.m123of(this.contents[0]);
                default:
                    if (this.hashTable == null || ImmutableSet.chooseTableSize(this.size) != this.hashTable.length) {
                        result = ImmutableSet.construct(this.size, this.contents);
                        this.size = result.size();
                    } else {
                        result = new RegularImmutableSet<>(ImmutableSet.shouldTrim(this.size, this.contents.length) ? Arrays.copyOf(this.contents, this.size) : this.contents, this.hashCode, this.hashTable, this.hashTable.length - 1, this.size);
                    }
                    this.forceCopy = true;
                    this.hashTable = null;
                    return result;
            }
        }
    }
}
