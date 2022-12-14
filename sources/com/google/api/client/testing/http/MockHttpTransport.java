package com.google.api.client.testing.http;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Beta
public class MockHttpTransport extends HttpTransport {
    private MockLowLevelHttpRequest lowLevelHttpRequest;
    private MockLowLevelHttpResponse lowLevelHttpResponse;
    private Set<String> supportedMethods;

    public MockHttpTransport() {
    }

    protected MockHttpTransport(Builder builder) {
        this.supportedMethods = builder.supportedMethods;
        this.lowLevelHttpRequest = builder.lowLevelHttpRequest;
        this.lowLevelHttpResponse = builder.lowLevelHttpResponse;
    }

    public boolean supportsMethod(String method) throws IOException {
        return this.supportedMethods == null || this.supportedMethods.contains(method);
    }

    public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        Preconditions.checkArgument(supportsMethod(method), "HTTP method %s not supported", method);
        if (this.lowLevelHttpRequest != null) {
            return this.lowLevelHttpRequest;
        }
        this.lowLevelHttpRequest = new MockLowLevelHttpRequest(url);
        if (this.lowLevelHttpResponse != null) {
            this.lowLevelHttpRequest.setResponse(this.lowLevelHttpResponse);
        }
        return this.lowLevelHttpRequest;
    }

    public final Set<String> getSupportedMethods() {
        if (this.supportedMethods == null) {
            return null;
        }
        return Collections.unmodifiableSet(this.supportedMethods);
    }

    public final MockLowLevelHttpRequest getLowLevelHttpRequest() {
        return this.lowLevelHttpRequest;
    }

    @Beta
    public static class Builder {
        MockLowLevelHttpRequest lowLevelHttpRequest;
        MockLowLevelHttpResponse lowLevelHttpResponse;
        Set<String> supportedMethods;

        public MockHttpTransport build() {
            return new MockHttpTransport(this);
        }

        public final Set<String> getSupportedMethods() {
            return this.supportedMethods;
        }

        public final Builder setSupportedMethods(Set<String> supportedMethods2) {
            this.supportedMethods = supportedMethods2;
            return this;
        }

        public final Builder setLowLevelHttpRequest(MockLowLevelHttpRequest lowLevelHttpRequest2) {
            Preconditions.checkState(this.lowLevelHttpResponse == null, "Cannnot set a low level HTTP request when a low level HTTP response has been set.");
            this.lowLevelHttpRequest = lowLevelHttpRequest2;
            return this;
        }

        public final MockLowLevelHttpRequest getLowLevelHttpRequest() {
            return this.lowLevelHttpRequest;
        }

        public final Builder setLowLevelHttpResponse(MockLowLevelHttpResponse lowLevelHttpResponse2) {
            Preconditions.checkState(this.lowLevelHttpRequest == null, "Cannot set a low level HTTP response when a low level HTTP request has been set.");
            this.lowLevelHttpResponse = lowLevelHttpResponse2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public MockLowLevelHttpResponse getLowLevelHttpResponse() {
            return this.lowLevelHttpResponse;
        }
    }
}
