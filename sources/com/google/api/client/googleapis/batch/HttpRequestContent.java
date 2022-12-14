package com.google.api.client.googleapis.batch;

import com.google.api.client.http.AbstractHttpContent;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Logger;

class HttpRequestContent extends AbstractHttpContent {
    private static final String HTTP_VERSION = "HTTP/1.1";
    static final String NEWLINE = "\r\n";
    private final HttpRequest request;

    HttpRequestContent(HttpRequest request2) {
        super("application/http");
        this.request = request2;
    }

    public void writeTo(OutputStream out) throws IOException {
        Writer writer = new OutputStreamWriter(out, getCharset());
        writer.write(this.request.getRequestMethod());
        writer.write(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        writer.write(this.request.getUrl().build());
        writer.write(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        writer.write(HTTP_VERSION);
        writer.write(NEWLINE);
        HttpHeaders headers = new HttpHeaders();
        headers.fromHttpHeaders(this.request.getHeaders());
        headers.setAcceptEncoding((String) null).setUserAgent((String) null).setContentEncoding((String) null).setContentType((String) null).setContentLength((Long) null);
        HttpContent content = this.request.getContent();
        if (content != null) {
            headers.setContentType(content.getType());
            long contentLength = content.getLength();
            if (contentLength != -1) {
                headers.setContentLength(Long.valueOf(contentLength));
            }
        }
        HttpHeaders.serializeHeadersForMultipartRequests(headers, (StringBuilder) null, (Logger) null, writer);
        writer.write(NEWLINE);
        writer.flush();
        if (content != null) {
            content.writeTo(out);
        }
    }
}
