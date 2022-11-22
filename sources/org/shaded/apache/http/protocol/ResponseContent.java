package org.shaded.apache.http.protocol;

import java.io.IOException;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseInterceptor;
import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.ProtocolVersion;

public class ResponseContent implements HttpResponseInterceptor {
    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        if (response == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if (response.containsHeader("Transfer-Encoding")) {
            throw new ProtocolException("Transfer-encoding header already present");
        } else if (response.containsHeader("Content-Length")) {
            throw new ProtocolException("Content-Length header already present");
        } else {
            ProtocolVersion ver = response.getStatusLine().getProtocolVersion();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                long len = entity.getContentLength();
                if (entity.isChunked() && !ver.lessEquals(HttpVersion.HTTP_1_0)) {
                    response.addHeader("Transfer-Encoding", HTTP.CHUNK_CODING);
                } else if (len >= 0) {
                    response.addHeader("Content-Length", Long.toString(entity.getContentLength()));
                }
                if (entity.getContentType() != null && !response.containsHeader("Content-Type")) {
                    response.addHeader(entity.getContentType());
                }
                if (entity.getContentEncoding() != null && !response.containsHeader("Content-Encoding")) {
                    response.addHeader(entity.getContentEncoding());
                    return;
                }
                return;
            }
            int status = response.getStatusLine().getStatusCode();
            if (status != 204 && status != 304 && status != 205) {
                response.addHeader("Content-Length", "0");
            }
        }
    }
}
