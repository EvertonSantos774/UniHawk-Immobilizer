package com.google.api.client.http;

import java.io.IOException;

public final class HttpRequestFactory {
    private final HttpRequestInitializer initializer;
    private final HttpTransport transport;

    HttpRequestFactory(HttpTransport transport2, HttpRequestInitializer initializer2) {
        this.transport = transport2;
        this.initializer = initializer2;
    }

    public HttpTransport getTransport() {
        return this.transport;
    }

    public HttpRequestInitializer getInitializer() {
        return this.initializer;
    }

    public HttpRequest buildRequest(String requestMethod, GenericUrl url, HttpContent content) throws IOException {
        HttpRequest request = this.transport.buildRequest();
        if (this.initializer != null) {
            this.initializer.initialize(request);
        }
        request.setRequestMethod(requestMethod);
        if (url != null) {
            request.setUrl(url);
        }
        if (content != null) {
            request.setContent(content);
        }
        return request;
    }

    public HttpRequest buildDeleteRequest(GenericUrl url) throws IOException {
        return buildRequest("DELETE", url, (HttpContent) null);
    }

    public HttpRequest buildGetRequest(GenericUrl url) throws IOException {
        return buildRequest("GET", url, (HttpContent) null);
    }

    public HttpRequest buildPostRequest(GenericUrl url, HttpContent content) throws IOException {
        return buildRequest("POST", url, content);
    }

    public HttpRequest buildPutRequest(GenericUrl url, HttpContent content) throws IOException {
        return buildRequest("PUT", url, content);
    }

    public HttpRequest buildPatchRequest(GenericUrl url, HttpContent content) throws IOException {
        return buildRequest(HttpMethods.PATCH, url, content);
    }

    public HttpRequest buildHeadRequest(GenericUrl url) throws IOException {
        return buildRequest("HEAD", url, (HttpContent) null);
    }
}
