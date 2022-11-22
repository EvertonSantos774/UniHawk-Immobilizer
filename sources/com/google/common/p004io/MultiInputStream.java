package com.google.common.p004io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
/* renamed from: com.google.common.io.MultiInputStream */
final class MultiInputStream extends InputStream {
    @NullableDecl

    /* renamed from: in */
    private InputStream f361in;

    /* renamed from: it */
    private Iterator<? extends ByteSource> f362it;

    public MultiInputStream(Iterator<? extends ByteSource> it) throws IOException {
        this.f362it = (Iterator) Preconditions.checkNotNull(it);
        advance();
    }

    public void close() throws IOException {
        if (this.f361in != null) {
            try {
                this.f361in.close();
            } finally {
                this.f361in = null;
            }
        }
    }

    private void advance() throws IOException {
        close();
        if (this.f362it.hasNext()) {
            this.f361in = ((ByteSource) this.f362it.next()).openStream();
        }
    }

    public int available() throws IOException {
        if (this.f361in == null) {
            return 0;
        }
        return this.f361in.available();
    }

    public boolean markSupported() {
        return false;
    }

    public int read() throws IOException {
        while (this.f361in != null) {
            int result = this.f361in.read();
            if (result != -1) {
                return result;
            }
            advance();
        }
        return -1;
    }

    public int read(@NullableDecl byte[] b, int off, int len) throws IOException {
        while (this.f361in != null) {
            int result = this.f361in.read(b, off, len);
            if (result != -1) {
                return result;
            }
            advance();
        }
        return -1;
    }

    public long skip(long n) throws IOException {
        if (this.f361in == null || n <= 0) {
            return 0;
        }
        long result = this.f361in.skip(n);
        if (result != 0) {
            return result;
        }
        if (read() == -1) {
            return 0;
        }
        return 1 + this.f361in.skip(n - 1);
    }
}
