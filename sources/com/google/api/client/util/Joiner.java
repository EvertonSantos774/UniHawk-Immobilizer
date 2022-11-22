package com.google.api.client.util;

public final class Joiner {
    private final com.google.common.base.Joiner wrapped;

    /* renamed from: on */
    public static Joiner m38on(char separator) {
        return new Joiner(com.google.common.base.Joiner.m49on(separator));
    }

    private Joiner(com.google.common.base.Joiner wrapped2) {
        this.wrapped = wrapped2;
    }

    public final String join(Iterable<?> parts) {
        return this.wrapped.join(parts);
    }
}
