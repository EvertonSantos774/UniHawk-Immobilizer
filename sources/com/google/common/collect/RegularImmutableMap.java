package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.AbstractMap;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    private static final int ABSENT = -1;
    static final ImmutableMap<Object, Object> EMPTY = new RegularImmutableMap((int[]) null, new Object[0], 0);
    private static final long serialVersionUID = 0;
    @VisibleForTesting
    final transient Object[] alternatingKeysAndValues;
    private final transient int[] hashTable;
    private final transient int size;

    static <K, V> RegularImmutableMap<K, V> create(int n, Object[] alternatingKeysAndValues2) {
        if (n == 0) {
            return (RegularImmutableMap) EMPTY;
        }
        if (n == 1) {
            CollectPreconditions.checkEntryNotNull(alternatingKeysAndValues2[0], alternatingKeysAndValues2[1]);
            return new RegularImmutableMap<>((int[]) null, alternatingKeysAndValues2, 1);
        }
        Preconditions.checkPositionIndex(n, alternatingKeysAndValues2.length >> 1);
        return new RegularImmutableMap<>(createHashTable(alternatingKeysAndValues2, n, ImmutableSet.chooseTableSize(n), 0), alternatingKeysAndValues2, n);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0035, code lost:
        r1[r0] = (r2 * 2) + r13;
        r2 = r2 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int[] createHashTable(java.lang.Object[] r10, int r11, int r12, int r13) {
        /*
            r9 = -1
            r7 = 1
            if (r11 != r7) goto L_0x000f
            r7 = r10[r13]
            r8 = r13 ^ 1
            r8 = r10[r8]
            com.google.common.collect.CollectPreconditions.checkEntryNotNull(r7, r8)
            r1 = 0
        L_0x000e:
            return r1
        L_0x000f:
            int r4 = r12 + -1
            int[] r1 = new int[r12]
            java.util.Arrays.fill(r1, r9)
            r2 = 0
        L_0x0017:
            if (r2 >= r11) goto L_0x000e
            int r7 = r2 * 2
            int r7 = r7 + r13
            r3 = r10[r7]
            int r7 = r2 * 2
            r8 = r13 ^ 1
            int r7 = r7 + r8
            r6 = r10[r7]
            com.google.common.collect.CollectPreconditions.checkEntryNotNull(r3, r6)
            int r7 = r3.hashCode()
            int r0 = com.google.common.collect.Hashing.smear(r7)
        L_0x0030:
            r0 = r0 & r4
            r5 = r1[r0]
            if (r5 != r9) goto L_0x003d
            int r7 = r2 * 2
            int r7 = r7 + r13
            r1[r0] = r7
            int r2 = r2 + 1
            goto L_0x0017
        L_0x003d:
            r7 = r10[r5]
            boolean r7 = r7.equals(r3)
            if (r7 == 0) goto L_0x0082
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Multiple entries with same key: "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r3)
            java.lang.String r9 = "="
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r6)
            java.lang.String r9 = " and "
            java.lang.StringBuilder r8 = r8.append(r9)
            r9 = r10[r5]
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = "="
            java.lang.StringBuilder r8 = r8.append(r9)
            r9 = r5 ^ 1
            r9 = r10[r9]
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x0082:
            int r0 = r0 + 1
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableMap.createHashTable(java.lang.Object[], int, int, int):int[]");
    }

    private RegularImmutableMap(int[] hashTable2, Object[] alternatingKeysAndValues2, int size2) {
        this.hashTable = hashTable2;
        this.alternatingKeysAndValues = alternatingKeysAndValues2;
        this.size = size2;
    }

    public int size() {
        return this.size;
    }

    @NullableDecl
    public V get(@NullableDecl Object key) {
        return get(this.hashTable, this.alternatingKeysAndValues, this.size, 0, key);
    }

    static Object get(@NullableDecl int[] hashTable2, @NullableDecl Object[] alternatingKeysAndValues2, int size2, int keyOffset, @NullableDecl Object key) {
        if (key == null) {
            return null;
        }
        if (size2 == 1) {
            if (alternatingKeysAndValues2[keyOffset].equals(key)) {
                return alternatingKeysAndValues2[keyOffset ^ 1];
            }
            return null;
        } else if (hashTable2 == null) {
            return null;
        } else {
            int mask = hashTable2.length - 1;
            int h = Hashing.smear(key.hashCode());
            while (true) {
                int h2 = h & mask;
                int index = hashTable2[h2];
                if (index == -1) {
                    return null;
                }
                if (alternatingKeysAndValues2[index].equals(key)) {
                    return alternatingKeysAndValues2[index ^ 1];
                }
                h = h2 + 1;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new EntrySet(this, this.alternatingKeysAndValues, 0, this.size);
    }

    static class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        /* access modifiers changed from: private */
        public final transient Object[] alternatingKeysAndValues;
        /* access modifiers changed from: private */
        public final transient int keyOffset;
        private final transient ImmutableMap<K, V> map;
        /* access modifiers changed from: private */
        public final transient int size;

        EntrySet(ImmutableMap<K, V> map2, Object[] alternatingKeysAndValues2, int keyOffset2, int size2) {
            this.map = map2;
            this.alternatingKeysAndValues = alternatingKeysAndValues2;
            this.keyOffset = keyOffset2;
            this.size = size2;
        }

        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: package-private */
        public int copyIntoArray(Object[] dst, int offset) {
            return asList().copyIntoArray(dst, offset);
        }

        /* access modifiers changed from: package-private */
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new ImmutableList<Map.Entry<K, V>>() {
                public Map.Entry<K, V> get(int index) {
                    Preconditions.checkElementIndex(index, EntrySet.this.size);
                    return new AbstractMap.SimpleImmutableEntry(EntrySet.this.alternatingKeysAndValues[(index * 2) + EntrySet.this.keyOffset], EntrySet.this.alternatingKeysAndValues[(index * 2) + (EntrySet.this.keyOffset ^ 1)]);
                }

                public int size() {
                    return EntrySet.this.size;
                }

                public boolean isPartialView() {
                    return true;
                }
            };
        }

        public boolean contains(Object object) {
            if (!(object instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) object;
            Object k = entry.getKey();
            Object v = entry.getValue();
            if (v == null || !v.equals(this.map.get(k))) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return true;
        }

        public int size() {
            return this.size;
        }
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<K> createKeySet() {
        return new KeySet(this, new KeysOrValuesAsList(this.alternatingKeysAndValues, 0, this.size));
    }

    static final class KeysOrValuesAsList extends ImmutableList<Object> {
        private final transient Object[] alternatingKeysAndValues;
        private final transient int offset;
        private final transient int size;

        KeysOrValuesAsList(Object[] alternatingKeysAndValues2, int offset2, int size2) {
            this.alternatingKeysAndValues = alternatingKeysAndValues2;
            this.offset = offset2;
            this.size = size2;
        }

        public Object get(int index) {
            Preconditions.checkElementIndex(index, this.size);
            return this.alternatingKeysAndValues[(index * 2) + this.offset];
        }

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return true;
        }

        public int size() {
            return this.size;
        }
    }

    static final class KeySet<K> extends ImmutableSet<K> {
        private final transient ImmutableList<K> list;
        private final transient ImmutableMap<K, ?> map;

        KeySet(ImmutableMap<K, ?> map2, ImmutableList<K> list2) {
            this.map = map2;
            this.list = list2;
        }

        public UnmodifiableIterator<K> iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: package-private */
        public int copyIntoArray(Object[] dst, int offset) {
            return asList().copyIntoArray(dst, offset);
        }

        public ImmutableList<K> asList() {
            return this.list;
        }

        public boolean contains(@NullableDecl Object object) {
            return this.map.get(object) != null;
        }

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return true;
        }

        public int size() {
            return this.map.size();
        }
    }

    /* access modifiers changed from: package-private */
    public ImmutableCollection<V> createValues() {
        return new KeysOrValuesAsList(this.alternatingKeysAndValues, 1, this.size);
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }
}
