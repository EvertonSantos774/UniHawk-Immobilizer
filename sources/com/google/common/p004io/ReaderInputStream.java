package com.google.common.p004io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;

@GwtIncompatible
/* renamed from: com.google.common.io.ReaderInputStream */
final class ReaderInputStream extends InputStream {
    private ByteBuffer byteBuffer;
    private CharBuffer charBuffer;
    private boolean doneFlushing;
    private boolean draining;
    private final CharsetEncoder encoder;
    private boolean endOfInput;
    private final Reader reader;
    private final byte[] singleByte;

    ReaderInputStream(Reader reader2, Charset charset, int bufferSize) {
        this(reader2, charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE), bufferSize);
    }

    ReaderInputStream(Reader reader2, CharsetEncoder encoder2, int bufferSize) {
        this.singleByte = new byte[1];
        this.reader = (Reader) Preconditions.checkNotNull(reader2);
        this.encoder = (CharsetEncoder) Preconditions.checkNotNull(encoder2);
        Preconditions.checkArgument(bufferSize > 0, "bufferSize must be positive: %s", bufferSize);
        encoder2.reset();
        this.charBuffer = CharBuffer.allocate(bufferSize);
        this.charBuffer.flip();
        this.byteBuffer = ByteBuffer.allocate(bufferSize);
    }

    public void close() throws IOException {
        this.reader.close();
    }

    public int read() throws IOException {
        if (read(this.singleByte) == 1) {
            return UnsignedBytes.toInt(this.singleByte[0]);
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0024 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(byte[] r10, int r11, int r12) throws java.io.IOException {
        /*
            r9 = this;
            r8 = 1
            r3 = 0
            int r4 = r11 + r12
            int r5 = r10.length
            com.google.common.base.Preconditions.checkPositionIndexes(r11, r4, r5)
            if (r12 != 0) goto L_0x000c
            r2 = r3
        L_0x000b:
            return r2
        L_0x000c:
            r2 = 0
            boolean r0 = r9.endOfInput
        L_0x000f:
            boolean r4 = r9.draining
            if (r4 == 0) goto L_0x002d
            int r4 = r11 + r2
            int r5 = r12 - r2
            int r4 = r9.drain(r10, r4, r5)
            int r2 = r2 + r4
            if (r2 == r12) goto L_0x0022
            boolean r4 = r9.doneFlushing
            if (r4 == 0) goto L_0x0026
        L_0x0022:
            if (r2 > 0) goto L_0x000b
            r2 = -1
            goto L_0x000b
        L_0x0026:
            r9.draining = r3
            java.nio.ByteBuffer r4 = r9.byteBuffer
            r4.clear()
        L_0x002d:
            boolean r4 = r9.doneFlushing
            if (r4 == 0) goto L_0x003d
            java.nio.charset.CoderResult r1 = java.nio.charset.CoderResult.UNDERFLOW
        L_0x0033:
            boolean r4 = r1.isOverflow()
            if (r4 == 0) goto L_0x0055
            r9.startDraining(r8)
            goto L_0x000f
        L_0x003d:
            if (r0 == 0) goto L_0x0048
            java.nio.charset.CharsetEncoder r4 = r9.encoder
            java.nio.ByteBuffer r5 = r9.byteBuffer
            java.nio.charset.CoderResult r1 = r4.flush(r5)
            goto L_0x0033
        L_0x0048:
            java.nio.charset.CharsetEncoder r4 = r9.encoder
            java.nio.CharBuffer r5 = r9.charBuffer
            java.nio.ByteBuffer r6 = r9.byteBuffer
            boolean r7 = r9.endOfInput
            java.nio.charset.CoderResult r1 = r4.encode(r5, r6, r7)
            goto L_0x0033
        L_0x0055:
            boolean r4 = r1.isUnderflow()
            if (r4 == 0) goto L_0x006d
            if (r0 == 0) goto L_0x0063
            r9.doneFlushing = r8
            r9.startDraining(r3)
            goto L_0x000f
        L_0x0063:
            boolean r4 = r9.endOfInput
            if (r4 == 0) goto L_0x0069
            r0 = 1
            goto L_0x002d
        L_0x0069:
            r9.readMoreChars()
            goto L_0x002d
        L_0x006d:
            boolean r4 = r1.isError()
            if (r4 == 0) goto L_0x002d
            r1.throwException()
            r2 = r3
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.p004io.ReaderInputStream.read(byte[], int, int):int");
    }

    private static CharBuffer grow(CharBuffer buf) {
        CharBuffer bigger = CharBuffer.wrap(Arrays.copyOf(buf.array(), buf.capacity() * 2));
        bigger.position(buf.position());
        bigger.limit(buf.limit());
        return bigger;
    }

    private void readMoreChars() throws IOException {
        if (availableCapacity(this.charBuffer) == 0) {
            if (this.charBuffer.position() > 0) {
                this.charBuffer.compact().flip();
            } else {
                this.charBuffer = grow(this.charBuffer);
            }
        }
        int limit = this.charBuffer.limit();
        int numChars = this.reader.read(this.charBuffer.array(), limit, availableCapacity(this.charBuffer));
        if (numChars == -1) {
            this.endOfInput = true;
        } else {
            this.charBuffer.limit(limit + numChars);
        }
    }

    private static int availableCapacity(Buffer buffer) {
        return buffer.capacity() - buffer.limit();
    }

    private void startDraining(boolean overflow) {
        this.byteBuffer.flip();
        if (!overflow || this.byteBuffer.remaining() != 0) {
            this.draining = true;
        } else {
            this.byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() * 2);
        }
    }

    private int drain(byte[] b, int off, int len) {
        int remaining = Math.min(len, this.byteBuffer.remaining());
        this.byteBuffer.get(b, off, remaining);
        return remaining;
    }
}
