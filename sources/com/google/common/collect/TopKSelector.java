package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
final class TopKSelector<T> {
    private final T[] buffer;
    private int bufferSize;
    private final Comparator<? super T> comparator;

    /* renamed from: k */
    private final int f325k;
    @NullableDecl
    private T threshold;

    public static <T extends Comparable<? super T>> TopKSelector<T> least(int k) {
        return least(k, Ordering.natural());
    }

    public static <T> TopKSelector<T> least(int k, Comparator<? super T> comparator2) {
        return new TopKSelector<>(comparator2, k);
    }

    public static <T extends Comparable<? super T>> TopKSelector<T> greatest(int k) {
        return greatest(k, Ordering.natural());
    }

    public static <T> TopKSelector<T> greatest(int k, Comparator<? super T> comparator2) {
        return new TopKSelector<>(Ordering.from(comparator2).reverse(), k);
    }

    private TopKSelector(Comparator<? super T> comparator2, int k) {
        boolean z;
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator2, "comparator");
        this.f325k = k;
        if (k >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "k must be nonnegative, was %s", k);
        this.buffer = (Object[]) new Object[(k * 2)];
        this.bufferSize = 0;
        this.threshold = null;
    }

    public void offer(@NullableDecl T elem) {
        if (this.f325k != 0) {
            if (this.bufferSize == 0) {
                this.buffer[0] = elem;
                this.threshold = elem;
                this.bufferSize = 1;
            } else if (this.bufferSize < this.f325k) {
                T[] tArr = this.buffer;
                int i = this.bufferSize;
                this.bufferSize = i + 1;
                tArr[i] = elem;
                if (this.comparator.compare(elem, this.threshold) > 0) {
                    this.threshold = elem;
                }
            } else if (this.comparator.compare(elem, this.threshold) < 0) {
                T[] tArr2 = this.buffer;
                int i2 = this.bufferSize;
                this.bufferSize = i2 + 1;
                tArr2[i2] = elem;
                if (this.bufferSize == this.f325k * 2) {
                    trim();
                }
            }
        }
    }

    private void trim() {
        int left = 0;
        int right = (this.f325k * 2) - 1;
        int minThresholdPosition = 0;
        int iterations = 0;
        int maxIterations = IntMath.log2(right - 0, RoundingMode.CEILING) * 3;
        while (true) {
            if (left < right) {
                int pivotNewIndex = partition(left, right, ((left + right) + 1) >>> 1);
                if (pivotNewIndex <= this.f325k) {
                    if (pivotNewIndex >= this.f325k) {
                        break;
                    }
                    left = Math.max(pivotNewIndex, left + 1);
                    minThresholdPosition = pivotNewIndex;
                } else {
                    right = pivotNewIndex - 1;
                }
                iterations++;
                if (iterations >= maxIterations) {
                    Arrays.sort(this.buffer, left, right, this.comparator);
                    break;
                }
            } else {
                break;
            }
        }
        this.bufferSize = this.f325k;
        this.threshold = this.buffer[minThresholdPosition];
        for (int i = minThresholdPosition + 1; i < this.f325k; i++) {
            if (this.comparator.compare(this.buffer[i], this.threshold) > 0) {
                this.threshold = this.buffer[i];
            }
        }
    }

    private int partition(int left, int right, int pivotIndex) {
        T pivotValue = this.buffer[pivotIndex];
        this.buffer[pivotIndex] = this.buffer[right];
        int pivotNewIndex = left;
        for (int i = left; i < right; i++) {
            if (this.comparator.compare(this.buffer[i], pivotValue) < 0) {
                swap(pivotNewIndex, i);
                pivotNewIndex++;
            }
        }
        this.buffer[right] = this.buffer[pivotNewIndex];
        this.buffer[pivotNewIndex] = pivotValue;
        return pivotNewIndex;
    }

    private void swap(int i, int j) {
        T tmp = this.buffer[i];
        this.buffer[i] = this.buffer[j];
        this.buffer[j] = tmp;
    }

    public void offerAll(Iterable<? extends T> elements) {
        offerAll(elements.iterator());
    }

    public void offerAll(Iterator<? extends T> elements) {
        while (elements.hasNext()) {
            offer(elements.next());
        }
    }

    public List<T> topK() {
        Arrays.sort(this.buffer, 0, this.bufferSize, this.comparator);
        if (this.bufferSize > this.f325k) {
            Arrays.fill(this.buffer, this.f325k, this.buffer.length, (Object) null);
            this.bufferSize = this.f325k;
            this.threshold = this.buffer[this.f325k - 1];
        }
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(this.buffer, this.bufferSize)));
    }
}
