package com.google.api.client.http.javanet;

import com.google.api.client.http.LowLevelHttpResponse;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class NetHttpResponse extends LowLevelHttpResponse {
    private final HttpURLConnection connection;
    private final ArrayList<String> headerNames = new ArrayList<>();
    private final ArrayList<String> headerValues = new ArrayList<>();
    private final int responseCode;
    private final String responseMessage;

    NetHttpResponse(HttpURLConnection connection2) throws IOException {
        this.connection = connection2;
        int responseCode2 = connection2.getResponseCode();
        this.responseCode = responseCode2 == -1 ? 0 : responseCode2;
        this.responseMessage = connection2.getResponseMessage();
        List<String> headerNames2 = this.headerNames;
        List<String> headerValues2 = this.headerValues;
        for (Map.Entry<String, List<String>> entry : connection2.getHeaderFields().entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                for (String value : entry.getValue()) {
                    if (value != null) {
                        headerNames2.add(key);
                        headerValues2.add(value);
                    }
                }
            }
        }
    }

    public int getStatusCode() {
        return this.responseCode;
    }

    public InputStream getContent() throws IOException {
        InputStream in;
        try {
            in = this.connection.getInputStream();
        } catch (IOException e) {
            in = this.connection.getErrorStream();
        }
        if (in == null) {
            return null;
        }
        return new SizeValidatingInputStream(in);
    }

    public String getContentEncoding() {
        return this.connection.getContentEncoding();
    }

    public long getContentLength() {
        String string = this.connection.getHeaderField("Content-Length");
        if (string == null) {
            return -1;
        }
        return Long.parseLong(string);
    }

    public String getContentType() {
        return this.connection.getHeaderField("Content-Type");
    }

    public String getReasonPhrase() {
        return this.responseMessage;
    }

    public String getStatusLine() {
        String result = this.connection.getHeaderField(0);
        if (result == null || !result.startsWith("HTTP/1.")) {
            return null;
        }
        return result;
    }

    public int getHeaderCount() {
        return this.headerNames.size();
    }

    public String getHeaderName(int index) {
        return this.headerNames.get(index);
    }

    public String getHeaderValue(int index) {
        return this.headerValues.get(index);
    }

    public void disconnect() {
        this.connection.disconnect();
    }

    private final class SizeValidatingInputStream extends FilterInputStream {
        private long bytesRead = 0;

        public SizeValidatingInputStream(InputStream in) {
            super(in);
        }

        public int read(byte[] b, int off, int len) throws IOException {
            int n = this.in.read(b, off, len);
            if (n == -1) {
                throwIfFalseEOF();
            } else {
                this.bytesRead += (long) n;
            }
            return n;
        }

        public int read() throws IOException {
            int n = this.in.read();
            if (n == -1) {
                throwIfFalseEOF();
            } else {
                this.bytesRead++;
            }
            return n;
        }

        public long skip(long len) throws IOException {
            long n = this.in.skip(len);
            this.bytesRead += n;
            return n;
        }

        private void throwIfFalseEOF() throws IOException {
            long contentLength = NetHttpResponse.this.getContentLength();
            if (contentLength != -1 && this.bytesRead != 0 && this.bytesRead < contentLength) {
                throw new IOException("Connection closed prematurely: bytesRead = " + this.bytesRead + ", Content-Length = " + contentLength);
            }
        }
    }
}
