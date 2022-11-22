package com.google.api.client.http;

import com.google.api.client.util.Charsets;
import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class HttpResponse {
    private static final String CONTENT_ENCODING_GZIP = "gzip";
    private static final String CONTENT_ENCODING_XGZIP = "x-gzip";
    private InputStream content;
    private final String contentEncoding;
    private int contentLoggingLimit;
    private boolean contentRead;
    private final String contentType;
    private boolean loggingEnabled;
    private final HttpMediaType mediaType;
    private final HttpRequest request;
    LowLevelHttpResponse response;
    private final boolean returnRawInputStream;
    private final int statusCode;
    private final String statusMessage;

    HttpResponse(HttpRequest request2, LowLevelHttpResponse response2) throws IOException {
        boolean loggable;
        this.request = request2;
        this.returnRawInputStream = request2.getResponseReturnRawInputStream();
        this.contentLoggingLimit = request2.getContentLoggingLimit();
        this.loggingEnabled = request2.isLoggingEnabled();
        this.response = response2;
        this.contentEncoding = response2.getContentEncoding();
        int code = response2.getStatusCode();
        this.statusCode = code < 0 ? 0 : code;
        String message = response2.getReasonPhrase();
        this.statusMessage = message;
        Logger logger = HttpTransport.LOGGER;
        if (!this.loggingEnabled || !logger.isLoggable(Level.CONFIG)) {
            loggable = false;
        } else {
            loggable = true;
        }
        StringBuilder logbuf = null;
        if (loggable) {
            logbuf = new StringBuilder();
            logbuf.append("-------------- RESPONSE --------------").append(StringUtils.LINE_SEPARATOR);
            String statusLine = response2.getStatusLine();
            if (statusLine != null) {
                logbuf.append(statusLine);
            } else {
                logbuf.append(this.statusCode);
                if (message != null) {
                    logbuf.append(' ').append(message);
                }
            }
            logbuf.append(StringUtils.LINE_SEPARATOR);
        }
        request2.getResponseHeaders().fromHttpResponse(response2, loggable ? logbuf : null);
        String contentType2 = response2.getContentType();
        contentType2 = contentType2 == null ? request2.getResponseHeaders().getContentType() : contentType2;
        this.contentType = contentType2;
        this.mediaType = parseMediaType(contentType2);
        if (loggable) {
            logger.config(logbuf.toString());
        }
    }

    private static HttpMediaType parseMediaType(String contentType2) {
        if (contentType2 == null) {
            return null;
        }
        try {
            return new HttpMediaType(contentType2);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public int getContentLoggingLimit() {
        return this.contentLoggingLimit;
    }

    public HttpResponse setContentLoggingLimit(int contentLoggingLimit2) {
        Preconditions.checkArgument(contentLoggingLimit2 >= 0, "The content logging limit must be non-negative.");
        this.contentLoggingLimit = contentLoggingLimit2;
        return this;
    }

    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }

    public HttpResponse setLoggingEnabled(boolean loggingEnabled2) {
        this.loggingEnabled = loggingEnabled2;
        return this;
    }

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public String getContentType() {
        return this.contentType;
    }

    public HttpMediaType getMediaType() {
        return this.mediaType;
    }

    public HttpHeaders getHeaders() {
        return this.request.getResponseHeaders();
    }

    public boolean isSuccessStatusCode() {
        return HttpStatusCodes.isSuccess(this.statusCode);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public HttpTransport getTransport() {
        return this.request.getTransport();
    }

    public HttpRequest getRequest() {
        return this.request;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0049 A[Catch:{ EOFException -> 0x0071, all -> 0x006e }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0074  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.InputStream getContent() throws java.io.IOException {
        /*
            r7 = this;
            boolean r5 = r7.contentRead
            if (r5 != 0) goto L_0x005d
            com.google.api.client.http.LowLevelHttpResponse r5 = r7.response
            java.io.InputStream r2 = r5.getContent()
            if (r2 == 0) goto L_0x005a
            r0 = 0
            boolean r5 = r7.returnRawInputStream     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
            if (r5 != 0) goto L_0x0076
            java.lang.String r5 = r7.contentEncoding     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
            if (r5 == 0) goto L_0x0076
            java.lang.String r5 = r7.contentEncoding     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
            java.lang.String r5 = r5.trim()     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
            java.util.Locale r6 = java.util.Locale.ENGLISH     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
            java.lang.String r4 = r5.toLowerCase(r6)     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
            java.lang.String r5 = "gzip"
            boolean r5 = r5.equals(r4)     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
            if (r5 != 0) goto L_0x0031
            java.lang.String r5 = "x-gzip"
            boolean r5 = r5.equals(r4)     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
            if (r5 == 0) goto L_0x0076
        L_0x0031:
            java.util.zip.GZIPInputStream r3 = new java.util.zip.GZIPInputStream     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
            com.google.api.client.http.ConsumingInputStream r5 = new com.google.api.client.http.ConsumingInputStream     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
            r5.<init>(r2)     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
            r3.<init>(r5)     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
        L_0x003b:
            java.util.logging.Logger r1 = com.google.api.client.http.HttpTransport.LOGGER     // Catch:{ EOFException -> 0x0071, all -> 0x006e }
            boolean r5 = r7.loggingEnabled     // Catch:{ EOFException -> 0x0071, all -> 0x006e }
            if (r5 == 0) goto L_0x0074
            java.util.logging.Level r5 = java.util.logging.Level.CONFIG     // Catch:{ EOFException -> 0x0071, all -> 0x006e }
            boolean r5 = r1.isLoggable(r5)     // Catch:{ EOFException -> 0x0071, all -> 0x006e }
            if (r5 == 0) goto L_0x0074
            com.google.api.client.util.LoggingInputStream r2 = new com.google.api.client.util.LoggingInputStream     // Catch:{ EOFException -> 0x0071, all -> 0x006e }
            java.util.logging.Level r5 = java.util.logging.Level.CONFIG     // Catch:{ EOFException -> 0x0071, all -> 0x006e }
            int r6 = r7.contentLoggingLimit     // Catch:{ EOFException -> 0x0071, all -> 0x006e }
            r2.<init>(r3, r1, r5, r6)     // Catch:{ EOFException -> 0x0071, all -> 0x006e }
        L_0x0052:
            r7.content = r2     // Catch:{ EOFException -> 0x0060, all -> 0x0067 }
            r0 = 1
            if (r0 != 0) goto L_0x005a
            r2.close()
        L_0x005a:
            r5 = 1
            r7.contentRead = r5
        L_0x005d:
            java.io.InputStream r5 = r7.content
            return r5
        L_0x0060:
            r5 = move-exception
        L_0x0061:
            if (r0 != 0) goto L_0x005a
            r2.close()
            goto L_0x005a
        L_0x0067:
            r5 = move-exception
        L_0x0068:
            if (r0 != 0) goto L_0x006d
            r2.close()
        L_0x006d:
            throw r5
        L_0x006e:
            r5 = move-exception
            r2 = r3
            goto L_0x0068
        L_0x0071:
            r5 = move-exception
            r2 = r3
            goto L_0x0061
        L_0x0074:
            r2 = r3
            goto L_0x0052
        L_0x0076:
            r3 = r2
            goto L_0x003b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.http.HttpResponse.getContent():java.io.InputStream");
    }

    public void download(OutputStream outputStream) throws IOException {
        IOUtils.copy(getContent(), outputStream);
    }

    public void ignore() throws IOException {
        InputStream content2 = getContent();
        if (content2 != null) {
            content2.close();
        }
    }

    public void disconnect() throws IOException {
        ignore();
        this.response.disconnect();
    }

    public <T> T parseAs(Class<T> dataClass) throws IOException {
        if (!hasMessageBody()) {
            return null;
        }
        return this.request.getParser().parseAndClose(getContent(), getContentCharset(), dataClass);
    }

    private boolean hasMessageBody() throws IOException {
        int statusCode2 = getStatusCode();
        if (!getRequest().getRequestMethod().equals("HEAD") && statusCode2 / 100 != 1 && statusCode2 != 204 && statusCode2 != 304) {
            return true;
        }
        ignore();
        return false;
    }

    public Object parseAs(Type dataType) throws IOException {
        if (!hasMessageBody()) {
            return null;
        }
        return this.request.getParser().parseAndClose(getContent(), getContentCharset(), dataType);
    }

    public String parseAsString() throws IOException {
        InputStream content2 = getContent();
        if (content2 == null) {
            return "";
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(content2, out);
        return out.toString(getContentCharset().name());
    }

    public Charset getContentCharset() {
        if (this.mediaType == null || this.mediaType.getCharsetParameter() == null) {
            return Charsets.ISO_8859_1;
        }
        return this.mediaType.getCharsetParameter();
    }
}
