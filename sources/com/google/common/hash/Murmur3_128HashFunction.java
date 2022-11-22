package com.google.common.hash;

import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable
final class Murmur3_128HashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction GOOD_FAST_HASH_128 = new Murmur3_128HashFunction(Hashing.GOOD_FAST_HASH_SEED);
    static final HashFunction MURMUR3_128 = new Murmur3_128HashFunction(0);
    private static final long serialVersionUID = 0;
    private final int seed;

    Murmur3_128HashFunction(int seed2) {
        this.seed = seed2;
    }

    public int bits() {
        return 128;
    }

    public Hasher newHasher() {
        return new Murmur3_128Hasher(this.seed);
    }

    public String toString() {
        return "Hashing.murmur3_128(" + this.seed + ")";
    }

    public boolean equals(@NullableDecl Object object) {
        if (!(object instanceof Murmur3_128HashFunction) || this.seed != ((Murmur3_128HashFunction) object).seed) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getClass().hashCode() ^ this.seed;
    }

    private static final class Murmur3_128Hasher extends AbstractStreamingHasher {

        /* renamed from: C1 */
        private static final long f329C1 = -8663945395140668459L;

        /* renamed from: C2 */
        private static final long f330C2 = 5545529020109919103L;
        private static final int CHUNK_SIZE = 16;

        /* renamed from: h1 */
        private long f331h1;

        /* renamed from: h2 */
        private long f332h2;
        private int length = 0;

        Murmur3_128Hasher(int seed) {
            super(16);
            this.f331h1 = (long) seed;
            this.f332h2 = (long) seed;
        }

        /* access modifiers changed from: protected */
        public void process(ByteBuffer bb) {
            bmix64(bb.getLong(), bb.getLong());
            this.length += 16;
        }

        private void bmix64(long k1, long k2) {
            this.f331h1 ^= mixK1(k1);
            this.f331h1 = Long.rotateLeft(this.f331h1, 27);
            this.f331h1 += this.f332h2;
            this.f331h1 = (this.f331h1 * 5) + 1390208809;
            this.f332h2 ^= mixK2(k2);
            this.f332h2 = Long.rotateLeft(this.f332h2, 31);
            this.f332h2 += this.f331h1;
            this.f332h2 = (this.f332h2 * 5) + 944331445;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0076, code lost:
            r2 = r2 ^ ((long) com.google.common.primitives.UnsignedBytes.toInt(r13.get(8)));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0080, code lost:
            r0 = 0 ^ r13.getLong();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0085, code lost:
            r12.f331h1 ^= mixK1(r0);
            r12.f332h2 ^= mixK2(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0097, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x00a6, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r13.get(5))) << 40);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x00b2, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r13.get(4))) << 32);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x00be, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r13.get(3))) << 24);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x00ca, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r13.get(2))) << 16);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x00d6, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r13.get(1))) << 8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x00e2, code lost:
            r0 = r0 ^ ((long) com.google.common.primitives.UnsignedBytes.toInt(r13.get(0)));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0035, code lost:
            r2 = r2 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r13.get(13))) << 40);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0042, code lost:
            r2 = r2 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r13.get(12))) << 32);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x004f, code lost:
            r2 = r2 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r13.get(11))) << 24);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x005c, code lost:
            r2 = r2 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r13.get(10))) << 16);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0069, code lost:
            r2 = r2 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r13.get(9))) << 8);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void processRemaining(java.nio.ByteBuffer r13) {
            /*
                r12 = this;
                r11 = 40
                r10 = 32
                r9 = 24
                r8 = 16
                r7 = 8
                r0 = 0
                r2 = 0
                int r4 = r12.length
                int r5 = r13.remaining()
                int r4 = r4 + r5
                r12.length = r4
                int r4 = r13.remaining()
                switch(r4) {
                    case 1: goto L_0x00e2;
                    case 2: goto L_0x00d6;
                    case 3: goto L_0x00ca;
                    case 4: goto L_0x00be;
                    case 5: goto L_0x00b2;
                    case 6: goto L_0x00a6;
                    case 7: goto L_0x0098;
                    case 8: goto L_0x0080;
                    case 9: goto L_0x0076;
                    case 10: goto L_0x0069;
                    case 11: goto L_0x005c;
                    case 12: goto L_0x004f;
                    case 13: goto L_0x0042;
                    case 14: goto L_0x0035;
                    case 15: goto L_0x0026;
                    default: goto L_0x001e;
                }
            L_0x001e:
                java.lang.AssertionError r4 = new java.lang.AssertionError
                java.lang.String r5 = "Should never get here."
                r4.<init>(r5)
                throw r4
            L_0x0026:
                r4 = 14
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                r6 = 48
                long r4 = r4 << r6
                long r2 = r2 ^ r4
            L_0x0035:
                r4 = 13
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r11
                long r2 = r2 ^ r4
            L_0x0042:
                r4 = 12
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r10
                long r2 = r2 ^ r4
            L_0x004f:
                r4 = 11
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r9
                long r2 = r2 ^ r4
            L_0x005c:
                r4 = 10
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r8
                long r2 = r2 ^ r4
            L_0x0069:
                r4 = 9
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r7
                long r2 = r2 ^ r4
            L_0x0076:
                byte r4 = r13.get(r7)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r2 = r2 ^ r4
            L_0x0080:
                long r4 = r13.getLong()
                long r0 = r0 ^ r4
            L_0x0085:
                long r4 = r12.f331h1
                long r6 = mixK1(r0)
                long r4 = r4 ^ r6
                r12.f331h1 = r4
                long r4 = r12.f332h2
                long r6 = mixK2(r2)
                long r4 = r4 ^ r6
                r12.f332h2 = r4
                return
            L_0x0098:
                r4 = 6
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                r6 = 48
                long r4 = r4 << r6
                long r0 = r0 ^ r4
            L_0x00a6:
                r4 = 5
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r11
                long r0 = r0 ^ r4
            L_0x00b2:
                r4 = 4
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r10
                long r0 = r0 ^ r4
            L_0x00be:
                r4 = 3
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r9
                long r0 = r0 ^ r4
            L_0x00ca:
                r4 = 2
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r8
                long r0 = r0 ^ r4
            L_0x00d6:
                r4 = 1
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r7
                long r0 = r0 ^ r4
            L_0x00e2:
                r4 = 0
                byte r4 = r13.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r0 = r0 ^ r4
                goto L_0x0085
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.hash.Murmur3_128HashFunction.Murmur3_128Hasher.processRemaining(java.nio.ByteBuffer):void");
        }

        public HashCode makeHash() {
            this.f331h1 ^= (long) this.length;
            this.f332h2 ^= (long) this.length;
            this.f331h1 += this.f332h2;
            this.f332h2 += this.f331h1;
            this.f331h1 = fmix64(this.f331h1);
            this.f332h2 = fmix64(this.f332h2);
            this.f331h1 += this.f332h2;
            this.f332h2 += this.f331h1;
            return HashCode.fromBytesNoCopy(ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.f331h1).putLong(this.f332h2).array());
        }

        private static long fmix64(long k) {
            long k2 = (k ^ (k >>> 33)) * -49064778989728563L;
            long k3 = (k2 ^ (k2 >>> 33)) * -4265267296055464877L;
            return k3 ^ (k3 >>> 33);
        }

        private static long mixK1(long k1) {
            return Long.rotateLeft(k1 * f329C1, 31) * f330C2;
        }

        private static long mixK2(long k2) {
            return Long.rotateLeft(k2 * f330C2, 33) * f329C1;
        }
    }
}
