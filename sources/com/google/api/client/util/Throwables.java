package com.google.api.client.util;

public final class Throwables {
    public static RuntimeException propagate(Throwable throwable) {
        return com.google.common.base.Throwables.propagate(throwable);
    }

    public static void propagateIfPossible(Throwable throwable) {
        if (throwable != null) {
            com.google.common.base.Throwables.throwIfUnchecked(throwable);
        }
    }

    public static <X extends Throwable> void propagateIfPossible(Throwable throwable, Class<X> declaredType) throws Throwable {
        com.google.common.base.Throwables.propagateIfPossible(throwable, declaredType);
    }

    private Throwables() {
    }
}
