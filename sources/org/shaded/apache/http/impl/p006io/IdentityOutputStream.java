package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import java.io.OutputStream;
import org.shaded.apache.http.p007io.SessionOutputBuffer;

/* renamed from: org.shaded.apache.http.impl.io.IdentityOutputStream */
public class IdentityOutputStream extends OutputStream {
    private boolean closed = false;
    private final SessionOutputBuffer out;

    public IdentityOutputStream(SessionOutputBuffer out2) {
        if (out2 == null) {
            throw new IllegalArgumentException("Session output buffer may not be null");
        }
        this.out = out2;
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.out.flush();
        }
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void write(byte[] b, int off, int len) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.out.write(b, off, len);
    }

    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    public void write(int b) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.out.write(b);
    }
}
