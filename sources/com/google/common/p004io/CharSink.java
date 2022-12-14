package com.google.common.p004io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

@GwtIncompatible
/* renamed from: com.google.common.io.CharSink */
public abstract class CharSink {
    public abstract Writer openStream() throws IOException;

    protected CharSink() {
    }

    public Writer openBufferedStream() throws IOException {
        Writer writer = openStream();
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer);
    }

    public void write(CharSequence charSequence) throws IOException {
        Preconditions.checkNotNull(charSequence);
        Closer closer = Closer.create();
        try {
            Writer out = (Writer) closer.register(openStream());
            out.append(charSequence);
            out.flush();
            closer.close();
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public void writeLines(Iterable<? extends CharSequence> lines) throws IOException {
        writeLines(lines, System.getProperty("line.separator"));
    }

    public void writeLines(Iterable<? extends CharSequence> lines, String lineSeparator) throws IOException {
        Preconditions.checkNotNull(lines);
        Preconditions.checkNotNull(lineSeparator);
        Closer closer = Closer.create();
        try {
            Writer out = (Writer) closer.register(openBufferedStream());
            for (CharSequence line : lines) {
                out.append(line).append(lineSeparator);
            }
            out.flush();
            closer.close();
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    @CanIgnoreReturnValue
    public long writeFrom(Readable readable) throws IOException {
        Preconditions.checkNotNull(readable);
        Closer closer = Closer.create();
        try {
            Writer out = (Writer) closer.register(openStream());
            long written = CharStreams.copy(readable, out);
            out.flush();
            closer.close();
            return written;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }
}
