package com.google.api.client.http.apache;

import com.google.api.client.http.LowLevelHttpResponse;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpRequestBase;

final class ApacheHttpResponse extends LowLevelHttpResponse {
    private final Header[] allHeaders;
    private final HttpRequestBase request;
    private final HttpResponse response;

    ApacheHttpResponse(HttpRequestBase request2, HttpResponse response2) {
        this.request = request2;
        this.response = response2;
        this.allHeaders = response2.getAllHeaders();
    }

    public int getStatusCode() {
        StatusLine statusLine = this.response.getStatusLine();
        if (statusLine == null) {
            return 0;
        }
        return statusLine.getStatusCode();
    }

    public InputStream getContent() throws IOException {
        HttpEntity entity = this.response.getEntity();
        if (entity == null) {
            return null;
        }
        return entity.getContent();
    }

    public String getContentEncoding() {
        Header contentEncodingHeader;
        HttpEntity entity = this.response.getEntity();
        if (entity == null || (contentEncodingHeader = entity.getContentEncoding()) == null) {
            return null;
        }
        return contentEncodingHeader.getValue();
    }

    public long getContentLength() {
        HttpEntity entity = this.response.getEntity();
        if (entity == null) {
            return -1;
        }
        return entity.getContentLength();
    }

    public String getContentType() {
        Header contentTypeHeader;
        HttpEntity entity = this.response.getEntity();
        if (entity == null || (contentTypeHeader = entity.getContentType()) == null) {
            return null;
        }
        return contentTypeHeader.getValue();
    }

    public String getReasonPhrase() {
        StatusLine statusLine = this.response.getStatusLine();
        if (statusLine == null) {
            return null;
        }
        return statusLine.getReasonPhrase();
    }

    public String getStatusLine() {
        StatusLine statusLine = this.response.getStatusLine();
        if (statusLine == null) {
            return null;
        }
        return statusLine.toString();
    }

    public String getHeaderValue(String name) {
        return this.response.getLastHeader(name).getValue();
    }

    public int getHeaderCount() {
        return this.allHeaders.length;
    }

    public String getHeaderName(int index) {
        return this.allHeaders[index].getName();
    }

    public String getHeaderValue(int index) {
        return this.allHeaders[index].getValue();
    }

    public void disconnect() {
        this.request.abort();
    }
}
