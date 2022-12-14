package com.google.api.client.util;

public final class Preconditions {
    public static void checkArgument(boolean expression) {
        com.google.common.base.Preconditions.checkArgument(expression);
    }

    public static void checkArgument(boolean expression, Object errorMessage) {
        com.google.common.base.Preconditions.checkArgument(expression, errorMessage);
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        com.google.common.base.Preconditions.checkArgument(expression, errorMessageTemplate, errorMessageArgs);
    }

    public static void checkState(boolean expression) {
        com.google.common.base.Preconditions.checkState(expression);
    }

    public static void checkState(boolean expression, Object errorMessage) {
        com.google.common.base.Preconditions.checkState(expression, errorMessage);
    }

    public static void checkState(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        com.google.common.base.Preconditions.checkState(expression, errorMessageTemplate, errorMessageArgs);
    }

    public static <T> T checkNotNull(T reference) {
        return com.google.common.base.Preconditions.checkNotNull(reference);
    }

    public static <T> T checkNotNull(T reference, Object errorMessage) {
        return com.google.common.base.Preconditions.checkNotNull(reference, errorMessage);
    }

    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object... errorMessageArgs) {
        return com.google.common.base.Preconditions.checkNotNull(reference, errorMessageTemplate, errorMessageArgs);
    }

    private Preconditions() {
    }
}
