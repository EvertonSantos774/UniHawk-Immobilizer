package com.google.api.client.http.javanet;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;
import com.google.common.annotations.VisibleForTesting;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class NetHttpRequest extends LowLevelHttpRequest {
    private static final OutputWriter DEFAULT_CONNECTION_WRITER = new DefaultOutputWriter();
    private final HttpURLConnection connection;
    private int writeTimeout = 0;

    interface OutputWriter {
        void write(OutputStream outputStream, StreamingContent streamingContent) throws IOException;
    }

    NetHttpRequest(HttpURLConnection connection2) {
        this.connection = connection2;
        connection2.setInstanceFollowRedirects(false);
    }

    public void addHeader(String name, String value) {
        this.connection.addRequestProperty(name, value);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public String getRequestProperty(String name) {
        return this.connection.getRequestProperty(name);
    }

    public void setTimeout(int connectTimeout, int readTimeout) {
        this.connection.setReadTimeout(readTimeout);
        this.connection.setConnectTimeout(connectTimeout);
    }

    public void setWriteTimeout(int writeTimeout2) throws IOException {
        this.writeTimeout = writeTimeout2;
    }

    static class DefaultOutputWriter implements OutputWriter {
        DefaultOutputWriter() {
        }

        public void write(OutputStream outputStream, StreamingContent content) throws IOException {
            content.writeTo(outputStream);
        }
    }

    public LowLevelHttpResponse execute() throws IOException {
        return execute(DEFAULT_CONNECTION_WRITER);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public LowLevelHttpResponse execute(OutputWriter outputWriter) throws IOException {
        HttpURLConnection connection2 = this.connection;
        if (getStreamingContent() != null) {
            String contentType = getContentType();
            if (contentType != null) {
                addHeader("Content-Type", contentType);
            }
            String contentEncoding = getContentEncoding();
            if (contentEncoding != null) {
                addHeader("Content-Encoding", contentEncoding);
            }
            long contentLength = getContentLength();
            if (contentLength >= 0) {
                connection2.setRequestProperty("Content-Length", Long.toString(contentLength));
            }
            String requestMethod = connection2.getRequestMethod();
            if ("POST".equals(requestMethod) || "PUT".equals(requestMethod)) {
                connection2.setDoOutput(true);
                if (contentLength < 0 || contentLength > 2147483647L) {
                    connection2.setChunkedStreamingMode(0);
                } else {
                    connection2.setFixedLengthStreamingMode((int) contentLength);
                }
                OutputStream out = connection2.getOutputStream();
                try {
                    writeContentToOutputStream(outputWriter, out);
                    try {
                        out.close();
                    } catch (IOException exception) {
                        if (0 == 0) {
                            throw exception;
                        }
                    }
                } catch (IOException e) {
                    if (!hasResponse(connection2)) {
                        throw e;
                    }
                    try {
                        out.close();
                    } catch (IOException exception2) {
                        if (1 == 0) {
                            throw exception2;
                        }
                    }
                } catch (Throwable th) {
                    try {
                        out.close();
                    } catch (IOException exception3) {
                        if (1 == 0) {
                            throw exception3;
                        }
                    }
                    throw th;
                }
            } else {
                Preconditions.checkArgument(contentLength == 0, "%s with non-zero content length is not supported", requestMethod);
            }
        }
        boolean successfulConnection = false;
        try {
            connection2.connect();
            successfulConnection = true;
            return new NetHttpResponse(connection2);
        } finally {
            if (!successfulConnection) {
                connection2.disconnect();
            }
        }
    }

    private boolean hasResponse(HttpURLConnection connection2) {
        try {
            return connection2.getResponseCode() > 0;
        } catch (IOException e) {
            return false;
        }
    }

    private void writeContentToOutputStream(final OutputWriter outputWriter, final OutputStream out) throws IOException {
        if (this.writeTimeout == 0) {
            outputWriter.write(out, getStreamingContent());
            return;
        }
        final StreamingContent content = getStreamingContent();
        Callable<Boolean> writeContent = new Callable<Boolean>() {
            public Boolean call() throws IOException {
                outputWriter.write(out, content);
                return Boolean.TRUE;
            }
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(new FutureTask(writeContent), (Object) null);
        executor.shutdown();
        try {
            future.get((long) this.writeTimeout, TimeUnit.MILLISECONDS);
            if (!executor.isTerminated()) {
                executor.shutdown();
            }
        } catch (InterruptedException e) {
            throw new IOException("Socket write interrupted", e);
        } catch (ExecutionException e2) {
            throw new IOException("Exception in socket write", e2);
        } catch (TimeoutException e3) {
            throw new IOException("Socket write timed out", e3);
        }
    }
}
