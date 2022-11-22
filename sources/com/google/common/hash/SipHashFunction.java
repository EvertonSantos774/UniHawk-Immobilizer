package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;

    /* renamed from: c */
    private final int f336c;

    /* renamed from: d */
    private final int f337d;

    /* renamed from: k0 */
    private final long f338k0;

    /* renamed from: k1 */
    private final long f339k1;

    SipHashFunction(int c, int d, long k0, long k1) {
        boolean z = true;
        Preconditions.checkArgument(c > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", c);
        Preconditions.checkArgument(d <= 0 ? false : z, "The number of SipRound iterations (d=%s) during Finalization must be positive.", d);
        this.f336c = c;
        this.f337d = d;
        this.f338k0 = k0;
        this.f339k1 = k1;
    }

    public int bits() {
        return 64;
    }

    public Hasher newHasher() {
        return new SipHasher(this.f336c, this.f337d, this.f338k0, this.f339k1);
    }

    public String toString() {
        return "Hashing.sipHash" + this.f336c + "" + this.f337d + "(" + this.f338k0 + ", " + this.f339k1 + ")";
    }

    public boolean equals(@NullableDecl Object object) {
        if (!(object instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction other = (SipHashFunction) object;
        if (this.f336c == other.f336c && this.f337d == other.f337d && this.f338k0 == other.f338k0 && this.f339k1 == other.f339k1) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((long) ((getClass().hashCode() ^ this.f336c) ^ this.f337d)) ^ this.f338k0) ^ this.f339k1);
    }

    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b */
        private long f340b = 0;

        /* renamed from: c */
        private final int f341c;

        /* renamed from: d */
        private final int f342d;
        private long finalM = 0;

        /* renamed from: v0 */
        private long f343v0 = 8317987319222330741L;

        /* renamed from: v1 */
        private long f344v1 = 7237128888997146477L;

        /* renamed from: v2 */
        private long f345v2 = 7816392313619706465L;

        /* renamed from: v3 */
        private long f346v3 = 8387220255154660723L;

        SipHasher(int c, int d, long k0, long k1) {
            super(8);
            this.f341c = c;
            this.f342d = d;
            this.f343v0 ^= k0;
            this.f344v1 ^= k1;
            this.f345v2 ^= k0;
            this.f346v3 ^= k1;
        }

        /* access modifiers changed from: protected */
        public void process(ByteBuffer buffer) {
            this.f340b += 8;
            processM(buffer.getLong());
        }

        /* access modifiers changed from: protected */
        public void processRemaining(ByteBuffer buffer) {
            this.f340b += (long) buffer.remaining();
            int i = 0;
            while (buffer.hasRemaining()) {
                this.finalM ^= (((long) buffer.get()) & 255) << i;
                i += 8;
            }
        }

        public HashCode makeHash() {
            this.finalM ^= this.f340b << 56;
            processM(this.finalM);
            this.f345v2 ^= 255;
            sipRound(this.f342d);
            return HashCode.fromLong(((this.f343v0 ^ this.f344v1) ^ this.f345v2) ^ this.f346v3);
        }

        private void processM(long m) {
            this.f346v3 ^= m;
            sipRound(this.f341c);
            this.f343v0 ^= m;
        }

        private void sipRound(int iterations) {
            for (int i = 0; i < iterations; i++) {
                this.f343v0 += this.f344v1;
                this.f345v2 += this.f346v3;
                this.f344v1 = Long.rotateLeft(this.f344v1, 13);
                this.f346v3 = Long.rotateLeft(this.f346v3, 16);
                this.f344v1 ^= this.f343v0;
                this.f346v3 ^= this.f345v2;
                this.f343v0 = Long.rotateLeft(this.f343v0, 32);
                this.f345v2 += this.f344v1;
                this.f343v0 += this.f346v3;
                this.f344v1 = Long.rotateLeft(this.f344v1, 17);
                this.f346v3 = Long.rotateLeft(this.f346v3, 21);
                this.f344v1 ^= this.f345v2;
                this.f346v3 ^= this.f343v0;
                this.f345v2 = Long.rotateLeft(this.f345v2, 32);
            }
        }
    }
}
