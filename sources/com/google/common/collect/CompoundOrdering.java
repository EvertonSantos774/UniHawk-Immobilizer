package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

@GwtCompatible(serializable = true)
final class CompoundOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final Comparator<? super T>[] comparators;

    CompoundOrdering(Comparator<? super T> primary, Comparator<? super T> secondary) {
        this.comparators = (Comparator[]) new Comparator[]{primary, secondary};
    }

    CompoundOrdering(Iterable<? extends Comparator<? super T>> comparators2) {
        this.comparators = (Comparator[]) Iterables.toArray(comparators2, (T[]) new Comparator[0]);
    }

    public int compare(T left, T right) {
        for (Comparator<? super T> compare : this.comparators) {
            int result = compare.compare(left, right);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof CompoundOrdering) {
            return Arrays.equals(this.comparators, ((CompoundOrdering) object).comparators);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.comparators);
    }

    public String toString() {
        return "Ordering.compound(" + Arrays.toString(this.comparators) + ")";
    }
}
