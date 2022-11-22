package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

@GwtCompatible
final class TrieParser {
    private static final Joiner PREFIX_JOINER = Joiner.m50on("");

    TrieParser() {
    }

    static ImmutableMap<String, PublicSuffixType> parseTrie(CharSequence encoded) {
        ImmutableMap.Builder<String, PublicSuffixType> builder = ImmutableMap.builder();
        int encodedLen = encoded.length();
        int idx = 0;
        while (idx < encodedLen) {
            idx += doParseTrieToBuilder(Lists.newLinkedList(), encoded, idx, builder);
        }
        return builder.build();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0062 A[EDGE_INSN: B:35:0x0062->B:25:0x0062 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int doParseTrieToBuilder(java.util.List<java.lang.CharSequence> r10, java.lang.CharSequence r11, int r12, com.google.common.collect.ImmutableMap.Builder<java.lang.String, com.google.thirdparty.publicsuffix.PublicSuffixType> r13) {
        /*
            r9 = 58
            r8 = 33
            r7 = 0
            r6 = 63
            r5 = 44
            int r2 = r11.length()
            r3 = r12
            r0 = 0
        L_0x000f:
            if (r3 >= r2) goto L_0x0021
            char r0 = r11.charAt(r3)
            r4 = 38
            if (r0 == r4) goto L_0x0021
            if (r0 == r6) goto L_0x0021
            if (r0 == r8) goto L_0x0021
            if (r0 == r9) goto L_0x0021
            if (r0 != r5) goto L_0x0068
        L_0x0021:
            java.lang.CharSequence r4 = r11.subSequence(r12, r3)
            java.lang.CharSequence r4 = reverse(r4)
            r10.add(r7, r4)
            if (r0 == r8) goto L_0x0034
            if (r0 == r6) goto L_0x0034
            if (r0 == r9) goto L_0x0034
            if (r0 != r5) goto L_0x0047
        L_0x0034:
            com.google.common.base.Joiner r4 = PREFIX_JOINER
            java.lang.String r1 = r4.join((java.lang.Iterable<?>) r10)
            int r4 = r1.length()
            if (r4 <= 0) goto L_0x0047
            com.google.thirdparty.publicsuffix.PublicSuffixType r4 = com.google.thirdparty.publicsuffix.PublicSuffixType.fromCode(r0)
            r13.put(r1, r4)
        L_0x0047:
            int r3 = r3 + 1
            if (r0 == r6) goto L_0x0062
            if (r0 == r5) goto L_0x0062
        L_0x004d:
            if (r3 >= r2) goto L_0x0062
            int r4 = doParseTrieToBuilder(r10, r11, r3, r13)
            int r3 = r3 + r4
            char r4 = r11.charAt(r3)
            if (r4 == r6) goto L_0x0060
            char r4 = r11.charAt(r3)
            if (r4 != r5) goto L_0x004d
        L_0x0060:
            int r3 = r3 + 1
        L_0x0062:
            r10.remove(r7)
            int r4 = r3 - r12
            return r4
        L_0x0068:
            int r3 = r3 + 1
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.thirdparty.publicsuffix.TrieParser.doParseTrieToBuilder(java.util.List, java.lang.CharSequence, int, com.google.common.collect.ImmutableMap$Builder):int");
    }

    private static CharSequence reverse(CharSequence s) {
        return new StringBuilder(s).reverse();
    }
}
