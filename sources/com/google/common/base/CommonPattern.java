package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class CommonPattern {
    public abstract int flags();

    public abstract CommonMatcher matcher(CharSequence charSequence);

    public abstract String pattern();

    public abstract String toString();

    CommonPattern() {
    }

    public static CommonPattern compile(String pattern) {
        return Platform.compilePattern(pattern);
    }

    public static boolean isPcreLike() {
        return Platform.patternCompilerIsPcreLike();
    }
}
