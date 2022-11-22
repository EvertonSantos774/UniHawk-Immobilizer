package com.google.common.p004io;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
/* renamed from: com.google.common.io.BaseEncoding */
public abstract class BaseEncoding {
    private static final BaseEncoding BASE16 = new Base16Encoding("base16()", "0123456789ABCDEF");
    private static final BaseEncoding BASE32 = new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    private static final BaseEncoding BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    private static final BaseEncoding BASE64 = new Base64Encoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final BaseEncoding BASE64_URL = new Base64Encoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');

    public abstract boolean canDecode(CharSequence charSequence);

    /* access modifiers changed from: package-private */
    public abstract int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException;

    @GwtIncompatible
    public abstract InputStream decodingStream(Reader reader);

    /* access modifiers changed from: package-private */
    public abstract void encodeTo(Appendable appendable, byte[] bArr, int i, int i2) throws IOException;

    @GwtIncompatible
    public abstract OutputStream encodingStream(Writer writer);

    public abstract BaseEncoding lowerCase();

    /* access modifiers changed from: package-private */
    public abstract int maxDecodedSize(int i);

    /* access modifiers changed from: package-private */
    public abstract int maxEncodedSize(int i);

    public abstract BaseEncoding omitPadding();

    public abstract BaseEncoding upperCase();

    public abstract BaseEncoding withPadChar(char c);

    public abstract BaseEncoding withSeparator(String str, int i);

    BaseEncoding() {
    }

    /* renamed from: com.google.common.io.BaseEncoding$DecodingException */
    public static final class DecodingException extends IOException {
        DecodingException(String message) {
            super(message);
        }

        DecodingException(Throwable cause) {
            super(cause);
        }
    }

    public String encode(byte[] bytes) {
        return encode(bytes, 0, bytes.length);
    }

    public final String encode(byte[] bytes, int off, int len) {
        Preconditions.checkPositionIndexes(off, off + len, bytes.length);
        StringBuilder result = new StringBuilder(maxEncodedSize(len));
        try {
            encodeTo(result, bytes, off, len);
            return result.toString();
        } catch (IOException impossible) {
            throw new AssertionError(impossible);
        }
    }

    @GwtIncompatible
    public final ByteSink encodingSink(final CharSink encodedSink) {
        Preconditions.checkNotNull(encodedSink);
        return new ByteSink() {
            public OutputStream openStream() throws IOException {
                return BaseEncoding.this.encodingStream(encodedSink.openStream());
            }
        };
    }

    private static byte[] extract(byte[] result, int length) {
        if (length == result.length) {
            return result;
        }
        byte[] trunc = new byte[length];
        System.arraycopy(result, 0, trunc, 0, length);
        return trunc;
    }

    public final byte[] decode(CharSequence chars) {
        try {
            return decodeChecked(chars);
        } catch (DecodingException badInput) {
            throw new IllegalArgumentException(badInput);
        }
    }

    /* access modifiers changed from: package-private */
    public final byte[] decodeChecked(CharSequence chars) throws DecodingException {
        CharSequence chars2 = trimTrailingPadding(chars);
        byte[] tmp = new byte[maxDecodedSize(chars2.length())];
        return extract(tmp, decodeTo(tmp, chars2));
    }

    @GwtIncompatible
    public final ByteSource decodingSource(final CharSource encodedSource) {
        Preconditions.checkNotNull(encodedSource);
        return new ByteSource() {
            public InputStream openStream() throws IOException {
                return BaseEncoding.this.decodingStream(encodedSource.openStream());
            }
        };
    }

    /* access modifiers changed from: package-private */
    public CharSequence trimTrailingPadding(CharSequence chars) {
        return (CharSequence) Preconditions.checkNotNull(chars);
    }

    public static BaseEncoding base64() {
        return BASE64;
    }

    public static BaseEncoding base64Url() {
        return BASE64_URL;
    }

    public static BaseEncoding base32() {
        return BASE32;
    }

    public static BaseEncoding base32Hex() {
        return BASE32_HEX;
    }

    public static BaseEncoding base16() {
        return BASE16;
    }

    /* renamed from: com.google.common.io.BaseEncoding$Alphabet */
    private static final class Alphabet {
        final int bitsPerChar;
        final int bytesPerChunk;
        /* access modifiers changed from: private */
        public final char[] chars;
        final int charsPerChunk;
        private final byte[] decodabet;
        final int mask;
        private final String name;
        private final boolean[] validPadding;

        Alphabet(String name2, char[] chars2) {
            boolean z;
            boolean z2;
            this.name = (String) Preconditions.checkNotNull(name2);
            this.chars = (char[]) Preconditions.checkNotNull(chars2);
            try {
                this.bitsPerChar = IntMath.log2(chars2.length, RoundingMode.UNNECESSARY);
                int gcd = Math.min(8, Integer.lowestOneBit(this.bitsPerChar));
                try {
                    this.charsPerChunk = 8 / gcd;
                    this.bytesPerChunk = this.bitsPerChar / gcd;
                    this.mask = chars2.length - 1;
                    byte[] decodabet2 = new byte[128];
                    Arrays.fill(decodabet2, (byte) -1);
                    for (int i = 0; i < chars2.length; i++) {
                        char c = chars2[i];
                        if (c < decodabet2.length) {
                            z = true;
                        } else {
                            z = false;
                        }
                        Preconditions.checkArgument(z, "Non-ASCII character: %s", c);
                        if (decodabet2[c] == -1) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        Preconditions.checkArgument(z2, "Duplicate character: %s", c);
                        decodabet2[c] = (byte) i;
                    }
                    this.decodabet = decodabet2;
                    boolean[] validPadding2 = new boolean[this.charsPerChunk];
                    for (int i2 = 0; i2 < this.bytesPerChunk; i2++) {
                        validPadding2[IntMath.divide(i2 * 8, this.bitsPerChar, RoundingMode.CEILING)] = true;
                    }
                    this.validPadding = validPadding2;
                } catch (ArithmeticException e) {
                    throw new IllegalArgumentException("Illegal alphabet " + new String(chars2), e);
                }
            } catch (ArithmeticException e2) {
                throw new IllegalArgumentException("Illegal alphabet length " + chars2.length, e2);
            }
        }

        /* access modifiers changed from: package-private */
        public char encode(int bits) {
            return this.chars[bits];
        }

        /* access modifiers changed from: package-private */
        public boolean isValidPaddingStartPosition(int index) {
            return this.validPadding[index % this.charsPerChunk];
        }

        /* access modifiers changed from: package-private */
        public boolean canDecode(char ch) {
            return ch <= 127 && this.decodabet[ch] != -1;
        }

        /* access modifiers changed from: package-private */
        public int decode(char ch) throws DecodingException {
            if (ch > 127) {
                throw new DecodingException("Unrecognized character: 0x" + Integer.toHexString(ch));
            }
            byte result = this.decodabet[ch];
            if (result != -1) {
                return result;
            }
            if (ch <= ' ' || ch == 127) {
                throw new DecodingException("Unrecognized character: 0x" + Integer.toHexString(ch));
            }
            throw new DecodingException("Unrecognized character: " + ch);
        }

        private boolean hasLowerCase() {
            for (char c : this.chars) {
                if (Ascii.isLowerCase(c)) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasUpperCase() {
            for (char c : this.chars) {
                if (Ascii.isUpperCase(c)) {
                    return true;
                }
            }
            return false;
        }

        /* Debug info: failed to restart local var, previous not found, register: 5 */
        /* access modifiers changed from: package-private */
        public Alphabet upperCase() {
            if (!hasLowerCase()) {
                return this;
            }
            Preconditions.checkState(!hasUpperCase(), "Cannot call upperCase() on a mixed-case alphabet");
            char[] upperCased = new char[this.chars.length];
            for (int i = 0; i < this.chars.length; i++) {
                upperCased[i] = Ascii.toUpperCase(this.chars[i]);
            }
            return new Alphabet(this.name + ".upperCase()", upperCased);
        }

        /* Debug info: failed to restart local var, previous not found, register: 5 */
        /* access modifiers changed from: package-private */
        public Alphabet lowerCase() {
            if (!hasUpperCase()) {
                return this;
            }
            Preconditions.checkState(!hasLowerCase(), "Cannot call lowerCase() on a mixed-case alphabet");
            char[] lowerCased = new char[this.chars.length];
            for (int i = 0; i < this.chars.length; i++) {
                lowerCased[i] = Ascii.toLowerCase(this.chars[i]);
            }
            return new Alphabet(this.name + ".lowerCase()", lowerCased);
        }

        public boolean matches(char c) {
            return c < this.decodabet.length && this.decodabet[c] != -1;
        }

        public String toString() {
            return this.name;
        }

        public boolean equals(@NullableDecl Object other) {
            if (other instanceof Alphabet) {
                return Arrays.equals(this.chars, ((Alphabet) other).chars);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.chars);
        }
    }

    /* renamed from: com.google.common.io.BaseEncoding$StandardBaseEncoding */
    static class StandardBaseEncoding extends BaseEncoding {
        final Alphabet alphabet;
        @MonotonicNonNullDecl
        private transient BaseEncoding lowerCase;
        @NullableDecl
        final Character paddingChar;
        @MonotonicNonNullDecl
        private transient BaseEncoding upperCase;

        StandardBaseEncoding(String name, String alphabetChars, @NullableDecl Character paddingChar2) {
            this(new Alphabet(name, alphabetChars.toCharArray()), paddingChar2);
        }

        StandardBaseEncoding(Alphabet alphabet2, @NullableDecl Character paddingChar2) {
            this.alphabet = (Alphabet) Preconditions.checkNotNull(alphabet2);
            Preconditions.checkArgument(paddingChar2 == null || !alphabet2.matches(paddingChar2.charValue()), "Padding character %s was already in alphabet", (Object) paddingChar2);
            this.paddingChar = paddingChar2;
        }

        /* access modifiers changed from: package-private */
        public int maxEncodedSize(int bytes) {
            return this.alphabet.charsPerChunk * IntMath.divide(bytes, this.alphabet.bytesPerChunk, RoundingMode.CEILING);
        }

        @GwtIncompatible
        public OutputStream encodingStream(final Writer out) {
            Preconditions.checkNotNull(out);
            return new OutputStream() {
                int bitBuffer = 0;
                int bitBufferLength = 0;
                int writtenChars = 0;

                public void write(int b) throws IOException {
                    this.bitBuffer <<= 8;
                    this.bitBuffer |= b & 255;
                    this.bitBufferLength += 8;
                    while (this.bitBufferLength >= StandardBaseEncoding.this.alphabet.bitsPerChar) {
                        out.write(StandardBaseEncoding.this.alphabet.encode((this.bitBuffer >> (this.bitBufferLength - StandardBaseEncoding.this.alphabet.bitsPerChar)) & StandardBaseEncoding.this.alphabet.mask));
                        this.writtenChars++;
                        this.bitBufferLength -= StandardBaseEncoding.this.alphabet.bitsPerChar;
                    }
                }

                public void flush() throws IOException {
                    out.flush();
                }

                public void close() throws IOException {
                    if (this.bitBufferLength > 0) {
                        out.write(StandardBaseEncoding.this.alphabet.encode((this.bitBuffer << (StandardBaseEncoding.this.alphabet.bitsPerChar - this.bitBufferLength)) & StandardBaseEncoding.this.alphabet.mask));
                        this.writtenChars++;
                        if (StandardBaseEncoding.this.paddingChar != null) {
                            while (this.writtenChars % StandardBaseEncoding.this.alphabet.charsPerChunk != 0) {
                                out.write(StandardBaseEncoding.this.paddingChar.charValue());
                                this.writtenChars++;
                            }
                        }
                    }
                    out.close();
                }
            };
        }

        /* access modifiers changed from: package-private */
        public void encodeTo(Appendable target, byte[] bytes, int off, int len) throws IOException {
            Preconditions.checkNotNull(target);
            Preconditions.checkPositionIndexes(off, off + len, bytes.length);
            int i = 0;
            while (i < len) {
                encodeChunkTo(target, bytes, off + i, Math.min(this.alphabet.bytesPerChunk, len - i));
                i += this.alphabet.bytesPerChunk;
            }
        }

        /* access modifiers changed from: package-private */
        public void encodeChunkTo(Appendable target, byte[] bytes, int off, int len) throws IOException {
            Preconditions.checkNotNull(target);
            Preconditions.checkPositionIndexes(off, off + len, bytes.length);
            Preconditions.checkArgument(len <= this.alphabet.bytesPerChunk);
            long bitBuffer = 0;
            for (int i = 0; i < len; i++) {
                bitBuffer = (bitBuffer | ((long) (bytes[off + i] & 255))) << 8;
            }
            int bitOffset = ((len + 1) * 8) - this.alphabet.bitsPerChar;
            int bitsProcessed = 0;
            while (bitsProcessed < len * 8) {
                target.append(this.alphabet.encode(((int) (bitBuffer >>> (bitOffset - bitsProcessed))) & this.alphabet.mask));
                bitsProcessed += this.alphabet.bitsPerChar;
            }
            if (this.paddingChar != null) {
                while (bitsProcessed < this.alphabet.bytesPerChunk * 8) {
                    target.append(this.paddingChar.charValue());
                    bitsProcessed += this.alphabet.bitsPerChar;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public int maxDecodedSize(int chars) {
            return (int) (((((long) this.alphabet.bitsPerChar) * ((long) chars)) + 7) / 8);
        }

        /* access modifiers changed from: package-private */
        public CharSequence trimTrailingPadding(CharSequence chars) {
            Preconditions.checkNotNull(chars);
            if (this.paddingChar == null) {
                return chars;
            }
            char padChar = this.paddingChar.charValue();
            int l = chars.length() - 1;
            while (l >= 0 && chars.charAt(l) == padChar) {
                l--;
            }
            return chars.subSequence(0, l + 1);
        }

        public boolean canDecode(CharSequence chars) {
            Preconditions.checkNotNull(chars);
            CharSequence chars2 = trimTrailingPadding(chars);
            if (!this.alphabet.isValidPaddingStartPosition(chars2.length())) {
                return false;
            }
            for (int i = 0; i < chars2.length(); i++) {
                if (!this.alphabet.canDecode(chars2.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public int decodeTo(byte[] target, CharSequence chars) throws DecodingException {
            Preconditions.checkNotNull(target);
            CharSequence chars2 = trimTrailingPadding(chars);
            if (!this.alphabet.isValidPaddingStartPosition(chars2.length())) {
                throw new DecodingException("Invalid input length " + chars2.length());
            }
            int bytesWritten = 0;
            int charIdx = 0;
            while (charIdx < chars2.length()) {
                long chunk = 0;
                int charsProcessed = 0;
                for (int i = 0; i < this.alphabet.charsPerChunk; i++) {
                    chunk <<= this.alphabet.bitsPerChar;
                    if (charIdx + i < chars2.length()) {
                        chunk |= (long) this.alphabet.decode(chars2.charAt(charIdx + charsProcessed));
                        charsProcessed++;
                    }
                }
                int minOffset = (this.alphabet.bytesPerChunk * 8) - (this.alphabet.bitsPerChar * charsProcessed);
                int offset = (this.alphabet.bytesPerChunk - 1) * 8;
                int bytesWritten2 = bytesWritten;
                while (offset >= minOffset) {
                    target[bytesWritten2] = (byte) ((int) ((chunk >>> offset) & 255));
                    offset -= 8;
                    bytesWritten2++;
                }
                charIdx += this.alphabet.charsPerChunk;
                bytesWritten = bytesWritten2;
            }
            return bytesWritten;
        }

        @GwtIncompatible
        public InputStream decodingStream(final Reader reader) {
            Preconditions.checkNotNull(reader);
            return new InputStream() {
                int bitBuffer = 0;
                int bitBufferLength = 0;
                boolean hitPadding = false;
                int readChars = 0;

                public int read() throws IOException {
                    while (true) {
                        int readChar = reader.read();
                        if (readChar != -1) {
                            this.readChars++;
                            char ch = (char) readChar;
                            if (StandardBaseEncoding.this.paddingChar == null || StandardBaseEncoding.this.paddingChar.charValue() != ch) {
                                if (this.hitPadding) {
                                    throw new DecodingException("Expected padding character but found '" + ch + "' at index " + this.readChars);
                                }
                                this.bitBuffer <<= StandardBaseEncoding.this.alphabet.bitsPerChar;
                                this.bitBuffer |= StandardBaseEncoding.this.alphabet.decode(ch);
                                this.bitBufferLength += StandardBaseEncoding.this.alphabet.bitsPerChar;
                                if (this.bitBufferLength >= 8) {
                                    this.bitBufferLength -= 8;
                                    return (this.bitBuffer >> this.bitBufferLength) & 255;
                                }
                            } else if (this.hitPadding || (this.readChars != 1 && StandardBaseEncoding.this.alphabet.isValidPaddingStartPosition(this.readChars - 1))) {
                                this.hitPadding = true;
                            }
                        } else if (this.hitPadding || StandardBaseEncoding.this.alphabet.isValidPaddingStartPosition(this.readChars)) {
                            return -1;
                        } else {
                            throw new DecodingException("Invalid input length " + this.readChars);
                        }
                    }
                    throw new DecodingException("Padding cannot start at index " + this.readChars);
                }

                public void close() throws IOException {
                    reader.close();
                }
            };
        }

        /* Debug info: failed to restart local var, previous not found, register: 2 */
        public BaseEncoding omitPadding() {
            return this.paddingChar == null ? this : newInstance(this.alphabet, (Character) null);
        }

        /* Debug info: failed to restart local var, previous not found, register: 2 */
        public BaseEncoding withPadChar(char padChar) {
            if (8 % this.alphabet.bitsPerChar != 0) {
                return (this.paddingChar == null || this.paddingChar.charValue() != padChar) ? newInstance(this.alphabet, Character.valueOf(padChar)) : this;
            }
            return this;
        }

        public BaseEncoding withSeparator(String separator, int afterEveryChars) {
            boolean z;
            boolean z2 = true;
            for (int i = 0; i < separator.length(); i++) {
                if (!this.alphabet.matches(separator.charAt(i))) {
                    z = true;
                } else {
                    z = false;
                }
                Preconditions.checkArgument(z, "Separator (%s) cannot contain alphabet characters", (Object) separator);
            }
            if (this.paddingChar != null) {
                if (separator.indexOf(this.paddingChar.charValue()) >= 0) {
                    z2 = false;
                }
                Preconditions.checkArgument(z2, "Separator (%s) cannot contain padding character", (Object) separator);
            }
            return new SeparatedBaseEncoding(this, separator, afterEveryChars);
        }

        public BaseEncoding upperCase() {
            BaseEncoding result = this.upperCase;
            if (result == null) {
                Alphabet upper = this.alphabet.upperCase();
                result = upper == this.alphabet ? this : newInstance(upper, this.paddingChar);
                this.upperCase = result;
            }
            return result;
        }

        public BaseEncoding lowerCase() {
            BaseEncoding result = this.lowerCase;
            if (result == null) {
                Alphabet lower = this.alphabet.lowerCase();
                result = lower == this.alphabet ? this : newInstance(lower, this.paddingChar);
                this.lowerCase = result;
            }
            return result;
        }

        /* access modifiers changed from: package-private */
        public BaseEncoding newInstance(Alphabet alphabet2, @NullableDecl Character paddingChar2) {
            return new StandardBaseEncoding(alphabet2, paddingChar2);
        }

        public String toString() {
            StringBuilder builder = new StringBuilder("BaseEncoding.");
            builder.append(this.alphabet.toString());
            if (8 % this.alphabet.bitsPerChar != 0) {
                if (this.paddingChar == null) {
                    builder.append(".omitPadding()");
                } else {
                    builder.append(".withPadChar('").append(this.paddingChar).append("')");
                }
            }
            return builder.toString();
        }

        public boolean equals(@NullableDecl Object other) {
            if (!(other instanceof StandardBaseEncoding)) {
                return false;
            }
            StandardBaseEncoding that = (StandardBaseEncoding) other;
            if (!this.alphabet.equals(that.alphabet) || !Objects.equal(this.paddingChar, that.paddingChar)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.alphabet.hashCode() ^ Objects.hashCode(this.paddingChar);
        }
    }

    /* renamed from: com.google.common.io.BaseEncoding$Base16Encoding */
    static final class Base16Encoding extends StandardBaseEncoding {
        final char[] encoding;

        Base16Encoding(String name, String alphabetChars) {
            this(new Alphabet(name, alphabetChars.toCharArray()));
        }

        private Base16Encoding(Alphabet alphabet) {
            super(alphabet, (Character) null);
            this.encoding = new char[512];
            Preconditions.checkArgument(alphabet.chars.length == 16);
            for (int i = 0; i < 256; i++) {
                this.encoding[i] = alphabet.encode(i >>> 4);
                this.encoding[i | 256] = alphabet.encode(i & 15);
            }
        }

        /* access modifiers changed from: package-private */
        public void encodeTo(Appendable target, byte[] bytes, int off, int len) throws IOException {
            Preconditions.checkNotNull(target);
            Preconditions.checkPositionIndexes(off, off + len, bytes.length);
            for (int i = 0; i < len; i++) {
                int b = bytes[off + i] & 255;
                target.append(this.encoding[b]);
                target.append(this.encoding[b | 256]);
            }
        }

        /* access modifiers changed from: package-private */
        public int decodeTo(byte[] target, CharSequence chars) throws DecodingException {
            Preconditions.checkNotNull(target);
            if (chars.length() % 2 == 1) {
                throw new DecodingException("Invalid input length " + chars.length());
            }
            int bytesWritten = 0;
            int i = 0;
            while (i < chars.length()) {
                target[bytesWritten] = (byte) ((this.alphabet.decode(chars.charAt(i)) << 4) | this.alphabet.decode(chars.charAt(i + 1)));
                i += 2;
                bytesWritten++;
            }
            return bytesWritten;
        }

        /* access modifiers changed from: package-private */
        public BaseEncoding newInstance(Alphabet alphabet, @NullableDecl Character paddingChar) {
            return new Base16Encoding(alphabet);
        }
    }

    /* renamed from: com.google.common.io.BaseEncoding$Base64Encoding */
    static final class Base64Encoding extends StandardBaseEncoding {
        Base64Encoding(String name, String alphabetChars, @NullableDecl Character paddingChar) {
            this(new Alphabet(name, alphabetChars.toCharArray()), paddingChar);
        }

        private Base64Encoding(Alphabet alphabet, @NullableDecl Character paddingChar) {
            super(alphabet, paddingChar);
            Preconditions.checkArgument(alphabet.chars.length == 64);
        }

        /* access modifiers changed from: package-private */
        public void encodeTo(Appendable target, byte[] bytes, int off, int len) throws IOException {
            int i;
            Preconditions.checkNotNull(target);
            Preconditions.checkPositionIndexes(off, off + len, bytes.length);
            int i2 = off;
            int remaining = len;
            while (true) {
                i = i2;
                if (remaining < 3) {
                    break;
                }
                int i3 = i + 1;
                int i4 = i3 + 1;
                i2 = i4 + 1;
                int chunk = ((bytes[i] & 255) << 16) | ((bytes[i3] & 255) << 8) | (bytes[i4] & 255);
                target.append(this.alphabet.encode(chunk >>> 18));
                target.append(this.alphabet.encode((chunk >>> 12) & 63));
                target.append(this.alphabet.encode((chunk >>> 6) & 63));
                target.append(this.alphabet.encode(chunk & 63));
                remaining -= 3;
            }
            if (i < off + len) {
                encodeChunkTo(target, bytes, i, (off + len) - i);
            }
        }

        /* access modifiers changed from: package-private */
        public int decodeTo(byte[] target, CharSequence chars) throws DecodingException {
            Preconditions.checkNotNull(target);
            CharSequence chars2 = trimTrailingPadding(chars);
            if (!this.alphabet.isValidPaddingStartPosition(chars2.length())) {
                throw new DecodingException("Invalid input length " + chars2.length());
            }
            int bytesWritten = 0;
            int i = 0;
            while (i < chars2.length()) {
                int i2 = i + 1;
                i = i2 + 1;
                int chunk = (this.alphabet.decode(chars2.charAt(i)) << 18) | (this.alphabet.decode(chars2.charAt(i2)) << 12);
                int bytesWritten2 = bytesWritten + 1;
                target[bytesWritten] = (byte) (chunk >>> 16);
                if (i < chars2.length()) {
                    int i3 = i + 1;
                    int chunk2 = chunk | (this.alphabet.decode(chars2.charAt(i)) << 6);
                    bytesWritten = bytesWritten2 + 1;
                    target[bytesWritten2] = (byte) ((chunk2 >>> 8) & 255);
                    if (i3 < chars2.length()) {
                        i = i3 + 1;
                        bytesWritten2 = bytesWritten + 1;
                        target[bytesWritten] = (byte) ((chunk2 | this.alphabet.decode(chars2.charAt(i3))) & 255);
                    } else {
                        i = i3;
                    }
                }
                bytesWritten = bytesWritten2;
            }
            return bytesWritten;
        }

        /* access modifiers changed from: package-private */
        public BaseEncoding newInstance(Alphabet alphabet, @NullableDecl Character paddingChar) {
            return new Base64Encoding(alphabet, paddingChar);
        }
    }

    @GwtIncompatible
    static Reader ignoringReader(final Reader delegate, final String toIgnore) {
        Preconditions.checkNotNull(delegate);
        Preconditions.checkNotNull(toIgnore);
        return new Reader() {
            /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: 
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
                	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
                */
            public int read() throws java.io.IOException {
                /*
                    r3 = this;
                L_0x0000:
                    java.io.Reader r1 = r1
                    int r0 = r1.read()
                    r1 = -1
                    if (r0 == r1) goto L_0x0012
                    java.lang.String r1 = r2
                    char r2 = (char) r0
                    int r1 = r1.indexOf(r2)
                    if (r1 >= 0) goto L_0x0000
                L_0x0012:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.p004io.BaseEncoding.C13073.read():int");
            }

            public int read(char[] cbuf, int off, int len) throws IOException {
                throw new UnsupportedOperationException();
            }

            public void close() throws IOException {
                delegate.close();
            }
        };
    }

    static Appendable separatingAppendable(final Appendable delegate, final String separator, final int afterEveryChars) {
        Preconditions.checkNotNull(delegate);
        Preconditions.checkNotNull(separator);
        Preconditions.checkArgument(afterEveryChars > 0);
        return new Appendable() {
            int charsUntilSeparator = afterEveryChars;

            public Appendable append(char c) throws IOException {
                if (this.charsUntilSeparator == 0) {
                    delegate.append(separator);
                    this.charsUntilSeparator = afterEveryChars;
                }
                delegate.append(c);
                this.charsUntilSeparator--;
                return this;
            }

            public Appendable append(@NullableDecl CharSequence chars, int off, int len) throws IOException {
                throw new UnsupportedOperationException();
            }

            public Appendable append(@NullableDecl CharSequence chars) throws IOException {
                throw new UnsupportedOperationException();
            }
        };
    }

    @GwtIncompatible
    static Writer separatingWriter(final Writer delegate, String separator, int afterEveryChars) {
        final Appendable seperatingAppendable = separatingAppendable(delegate, separator, afterEveryChars);
        return new Writer() {
            public void write(int c) throws IOException {
                seperatingAppendable.append((char) c);
            }

            public void write(char[] chars, int off, int len) throws IOException {
                throw new UnsupportedOperationException();
            }

            public void flush() throws IOException {
                delegate.flush();
            }

            public void close() throws IOException {
                delegate.close();
            }
        };
    }

    /* renamed from: com.google.common.io.BaseEncoding$SeparatedBaseEncoding */
    static final class SeparatedBaseEncoding extends BaseEncoding {
        private final int afterEveryChars;
        private final BaseEncoding delegate;
        private final String separator;

        SeparatedBaseEncoding(BaseEncoding delegate2, String separator2, int afterEveryChars2) {
            this.delegate = (BaseEncoding) Preconditions.checkNotNull(delegate2);
            this.separator = (String) Preconditions.checkNotNull(separator2);
            this.afterEveryChars = afterEveryChars2;
            Preconditions.checkArgument(afterEveryChars2 > 0, "Cannot add a separator after every %s chars", afterEveryChars2);
        }

        /* access modifiers changed from: package-private */
        public CharSequence trimTrailingPadding(CharSequence chars) {
            return this.delegate.trimTrailingPadding(chars);
        }

        /* access modifiers changed from: package-private */
        public int maxEncodedSize(int bytes) {
            int unseparatedSize = this.delegate.maxEncodedSize(bytes);
            return (this.separator.length() * IntMath.divide(Math.max(0, unseparatedSize - 1), this.afterEveryChars, RoundingMode.FLOOR)) + unseparatedSize;
        }

        @GwtIncompatible
        public OutputStream encodingStream(Writer output) {
            return this.delegate.encodingStream(separatingWriter(output, this.separator, this.afterEveryChars));
        }

        /* access modifiers changed from: package-private */
        public void encodeTo(Appendable target, byte[] bytes, int off, int len) throws IOException {
            this.delegate.encodeTo(separatingAppendable(target, this.separator, this.afterEveryChars), bytes, off, len);
        }

        /* access modifiers changed from: package-private */
        public int maxDecodedSize(int chars) {
            return this.delegate.maxDecodedSize(chars);
        }

        public boolean canDecode(CharSequence chars) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < chars.length(); i++) {
                char c = chars.charAt(i);
                if (this.separator.indexOf(c) < 0) {
                    builder.append(c);
                }
            }
            return this.delegate.canDecode(builder);
        }

        /* access modifiers changed from: package-private */
        public int decodeTo(byte[] target, CharSequence chars) throws DecodingException {
            StringBuilder stripped = new StringBuilder(chars.length());
            for (int i = 0; i < chars.length(); i++) {
                char c = chars.charAt(i);
                if (this.separator.indexOf(c) < 0) {
                    stripped.append(c);
                }
            }
            return this.delegate.decodeTo(target, stripped);
        }

        @GwtIncompatible
        public InputStream decodingStream(Reader reader) {
            return this.delegate.decodingStream(ignoringReader(reader, this.separator));
        }

        public BaseEncoding omitPadding() {
            return this.delegate.omitPadding().withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding withPadChar(char padChar) {
            return this.delegate.withPadChar(padChar).withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding withSeparator(String separator2, int afterEveryChars2) {
            throw new UnsupportedOperationException("Already have a separator");
        }

        public BaseEncoding upperCase() {
            return this.delegate.upperCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding lowerCase() {
            return this.delegate.lowerCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public String toString() {
            return this.delegate + ".withSeparator(\"" + this.separator + "\", " + this.afterEveryChars + ")";
        }
    }
}
