package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import gnu.kawa.lispexpr.LispReader;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
@Beta
public final class Escapers {
    private static final Escaper NULL_ESCAPER = new CharEscaper() {
        public String escape(String string) {
            return (String) Preconditions.checkNotNull(string);
        }

        /* access modifiers changed from: protected */
        public char[] escape(char c) {
            return null;
        }
    };

    private Escapers() {
    }

    public static Escaper nullEscaper() {
        return NULL_ESCAPER;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Beta
    public static final class Builder {
        private final Map<Character, String> replacementMap;
        private char safeMax;
        private char safeMin;
        /* access modifiers changed from: private */
        public String unsafeReplacement;

        private Builder() {
            this.replacementMap = new HashMap();
            this.safeMin = 0;
            this.safeMax = LispReader.TOKEN_ESCAPE_CHAR;
            this.unsafeReplacement = null;
        }

        @CanIgnoreReturnValue
        public Builder setSafeRange(char safeMin2, char safeMax2) {
            this.safeMin = safeMin2;
            this.safeMax = safeMax2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUnsafeReplacement(@NullableDecl String unsafeReplacement2) {
            this.unsafeReplacement = unsafeReplacement2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addEscape(char c, String replacement) {
            Preconditions.checkNotNull(replacement);
            this.replacementMap.put(Character.valueOf(c), replacement);
            return this;
        }

        public Escaper build() {
            return new ArrayBasedCharEscaper(this.replacementMap, this.safeMin, this.safeMax) {
                private final char[] replacementChars;

                {
                    this.replacementChars = Builder.this.unsafeReplacement != null ? Builder.this.unsafeReplacement.toCharArray() : null;
                }

                /* access modifiers changed from: protected */
                public char[] escapeUnsafe(char c) {
                    return this.replacementChars;
                }
            };
        }
    }

    static UnicodeEscaper asUnicodeEscaper(Escaper escaper) {
        Preconditions.checkNotNull(escaper);
        if (escaper instanceof UnicodeEscaper) {
            return (UnicodeEscaper) escaper;
        }
        if (escaper instanceof CharEscaper) {
            return wrap((CharEscaper) escaper);
        }
        throw new IllegalArgumentException("Cannot create a UnicodeEscaper from: " + escaper.getClass().getName());
    }

    public static String computeReplacement(CharEscaper escaper, char c) {
        return stringOrNull(escaper.escape(c));
    }

    public static String computeReplacement(UnicodeEscaper escaper, int cp) {
        return stringOrNull(escaper.escape(cp));
    }

    private static String stringOrNull(char[] in) {
        if (in == null) {
            return null;
        }
        return new String(in);
    }

    private static UnicodeEscaper wrap(final CharEscaper escaper) {
        return new UnicodeEscaper() {
            /* access modifiers changed from: protected */
            public char[] escape(int cp) {
                int hiCount;
                int loCount;
                if (cp < 65536) {
                    return escaper.escape((char) cp);
                }
                char[] surrogateChars = new char[2];
                Character.toChars(cp, surrogateChars, 0);
                char[] hiChars = escaper.escape(surrogateChars[0]);
                char[] loChars = escaper.escape(surrogateChars[1]);
                if (hiChars == null && loChars == null) {
                    return null;
                }
                if (hiChars != null) {
                    hiCount = hiChars.length;
                } else {
                    hiCount = 1;
                }
                if (loChars != null) {
                    loCount = loChars.length;
                } else {
                    loCount = 1;
                }
                char[] output = new char[(hiCount + loCount)];
                if (hiChars != null) {
                    for (int n = 0; n < hiChars.length; n++) {
                        output[n] = hiChars[n];
                    }
                } else {
                    output[0] = surrogateChars[0];
                }
                if (loChars != null) {
                    for (int n2 = 0; n2 < loChars.length; n2++) {
                        output[hiCount + n2] = loChars[n2];
                    }
                    return output;
                }
                output[hiCount] = surrogateChars[1];
                return output;
            }
        };
    }
}