package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.util.Collections;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
final class Present<T> extends Optional<T> {
    private static final long serialVersionUID = 0;
    private final T reference;

    Present(T reference2) {
        this.reference = reference2;
    }

    public boolean isPresent() {
        return true;
    }

    public T get() {
        return this.reference;
    }

    /* renamed from: or */
    public T mo15643or(T defaultValue) {
        Preconditions.checkNotNull(defaultValue, "use Optional.orNull() instead of Optional.or(null)");
        return this.reference;
    }

    /* renamed from: or */
    public Optional<T> mo15641or(Optional<? extends T> secondChoice) {
        Preconditions.checkNotNull(secondChoice);
        return this;
    }

    /* renamed from: or */
    public T mo15642or(Supplier<? extends T> supplier) {
        Preconditions.checkNotNull(supplier);
        return this.reference;
    }

    public T orNull() {
        return this.reference;
    }

    public Set<T> asSet() {
        return Collections.singleton(this.reference);
    }

    public <V> Optional<V> transform(Function<? super T, V> function) {
        return new Present(Preconditions.checkNotNull(function.apply(this.reference), "the Function passed to Optional.transform() must not return null."));
    }

    public boolean equals(@NullableDecl Object object) {
        if (object instanceof Present) {
            return this.reference.equals(((Present) object).reference);
        }
        return false;
    }

    public int hashCode() {
        return 1502476572 + this.reference.hashCode();
    }

    public String toString() {
        return "Optional.of(" + this.reference + ")";
    }
}
