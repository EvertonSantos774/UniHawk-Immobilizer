package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public enum CaseFormat {
    LOWER_HYPHEN(CharMatcher.m43is('-'), "-") {
        /* access modifiers changed from: package-private */
        public String normalizeWord(String word) {
            return Ascii.toLowerCase(word);
        }

        /* access modifiers changed from: package-private */
        public String convert(CaseFormat format, String s) {
            if (format == LOWER_UNDERSCORE) {
                return s.replace('-', '_');
            }
            if (format == UPPER_UNDERSCORE) {
                return Ascii.toUpperCase(s.replace('-', '_'));
            }
            return CaseFormat.super.convert(format, s);
        }
    },
    LOWER_UNDERSCORE(CharMatcher.m43is('_'), "_") {
        /* access modifiers changed from: package-private */
        public String normalizeWord(String word) {
            return Ascii.toLowerCase(word);
        }

        /* access modifiers changed from: package-private */
        public String convert(CaseFormat format, String s) {
            if (format == LOWER_HYPHEN) {
                return s.replace('_', '-');
            }
            if (format == UPPER_UNDERSCORE) {
                return Ascii.toUpperCase(s);
            }
            return CaseFormat.super.convert(format, s);
        }
    },
    LOWER_CAMEL(CharMatcher.inRange('A', 'Z'), "") {
        /* access modifiers changed from: package-private */
        public String normalizeWord(String word) {
            return CaseFormat.firstCharOnlyToUpper(word);
        }
    },
    UPPER_CAMEL(CharMatcher.inRange('A', 'Z'), "") {
        /* access modifiers changed from: package-private */
        public String normalizeWord(String word) {
            return CaseFormat.firstCharOnlyToUpper(word);
        }
    },
    UPPER_UNDERSCORE(CharMatcher.m43is('_'), "_") {
        /* access modifiers changed from: package-private */
        public String normalizeWord(String word) {
            return Ascii.toUpperCase(word);
        }

        /* access modifiers changed from: package-private */
        public String convert(CaseFormat format, String s) {
            if (format == LOWER_HYPHEN) {
                return Ascii.toLowerCase(s.replace('_', '-'));
            }
            if (format == LOWER_UNDERSCORE) {
                return Ascii.toLowerCase(s);
            }
            return CaseFormat.super.convert(format, s);
        }
    };
    
    private final CharMatcher wordBoundary;
    private final String wordSeparator;

    /* access modifiers changed from: package-private */
    public abstract String normalizeWord(String str);

    private CaseFormat(CharMatcher wordBoundary2, String wordSeparator2) {
        this.wordBoundary = wordBoundary2;
        this.wordSeparator = wordSeparator2;
    }

    /* renamed from: to */
    public final String mo15655to(CaseFormat format, String str) {
        Preconditions.checkNotNull(format);
        Preconditions.checkNotNull(str);
        return format == this ? str : convert(format, str);
    }

    /* access modifiers changed from: package-private */
    public String convert(CaseFormat format, String s) {
        StringBuilder out = null;
        int i = 0;
        int j = -1;
        while (true) {
            j = this.wordBoundary.indexIn(s, j + 1);
            if (j == -1) {
                break;
            }
            if (i == 0) {
                out = new StringBuilder(s.length() + (this.wordSeparator.length() * 4));
                out.append(format.normalizeFirstWord(s.substring(i, j)));
            } else {
                out.append(format.normalizeWord(s.substring(i, j)));
            }
            out.append(format.wordSeparator);
            i = j + this.wordSeparator.length();
        }
        if (i == 0) {
            return format.normalizeFirstWord(s);
        }
        return out.append(format.normalizeWord(s.substring(i))).toString();
    }

    public Converter<String, String> converterTo(CaseFormat targetFormat) {
        return new StringConverter(this, targetFormat);
    }

    private static final class StringConverter extends Converter<String, String> implements Serializable {
        private static final long serialVersionUID = 0;
        private final CaseFormat sourceFormat;
        private final CaseFormat targetFormat;

        StringConverter(CaseFormat sourceFormat2, CaseFormat targetFormat2) {
            this.sourceFormat = (CaseFormat) Preconditions.checkNotNull(sourceFormat2);
            this.targetFormat = (CaseFormat) Preconditions.checkNotNull(targetFormat2);
        }

        /* access modifiers changed from: protected */
        public String doForward(String s) {
            return this.sourceFormat.mo15655to(this.targetFormat, s);
        }

        /* access modifiers changed from: protected */
        public String doBackward(String s) {
            return this.targetFormat.mo15655to(this.sourceFormat, s);
        }

        public boolean equals(@NullableDecl Object object) {
            if (!(object instanceof StringConverter)) {
                return false;
            }
            StringConverter that = (StringConverter) object;
            if (!this.sourceFormat.equals(that.sourceFormat) || !this.targetFormat.equals(that.targetFormat)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.sourceFormat.hashCode() ^ this.targetFormat.hashCode();
        }

        public String toString() {
            return this.sourceFormat + ".converterTo(" + this.targetFormat + ")";
        }
    }

    private String normalizeFirstWord(String word) {
        return this == LOWER_CAMEL ? Ascii.toLowerCase(word) : normalizeWord(word);
    }

    /* access modifiers changed from: private */
    public static String firstCharOnlyToUpper(String word) {
        if (word.isEmpty()) {
            return word;
        }
        return Ascii.toUpperCase(word.charAt(0)) + Ascii.toLowerCase(word.substring(1));
    }
}
