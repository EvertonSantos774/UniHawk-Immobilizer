package com.google.api.client.http.apache;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

final class ApacheHttpRequest extends LowLevelHttpRequest {
    private final HttpClient httpClient;
    private final HttpRequestBase request;

    ApacheHttpRequest(HttpClient httpClient2, HttpRequestBase request2) {
        this.httpClient = httpClient2;
        this.request = request2;
    }

    public void addHeader(String name, String value) {
        this.request.addHeader(name, value);
    }

    public void setTimeout(int connectTimeout, int readTimeout) throws IOException {
        HttpParams params = this.request.getParams();
        ConnManagerParams.setTimeout(params, (long) connectTimeout);
        HttpConnectionParams.setConnectionTimeout(params, connectTimeout);
        HttpConnectionParams.setSoTimeout(params, readTimeout);
    }

    public LowLevelHttpResponse execute() throws IOException {
        if (getStreamingContent() != null) {
            Preconditions.checkState(this.request instanceof HttpEntityEnclosingRequest, "Apache HTTP client does not support %s requests with content.", this.request.getRequestLine().getMethod());
            ContentEntity entity = new ContentEntity(getContentLength(), getStreamingContent());
            entity.setContentEncoding(getContentEncoding());
            entity.setContentType(getContentType());
            if (getContentLength() == -1) {
                entity.setChunked(true);
            }
            this.request.setEntity(entity);
        }
        return new ApacheHttpResponse(this.request, this.httpClient.execute(this.request));
    }
}
