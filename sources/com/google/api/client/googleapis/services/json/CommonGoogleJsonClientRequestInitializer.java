package com.google.api.client.googleapis.services.json;

import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer;
import java.io.IOException;

public class CommonGoogleJsonClientRequestInitializer extends CommonGoogleClientRequestInitializer {
    @Deprecated
    public CommonGoogleJsonClientRequestInitializer() {
    }

    @Deprecated
    public CommonGoogleJsonClientRequestInitializer(String key) {
        super(key);
    }

    @Deprecated
    public CommonGoogleJsonClientRequestInitializer(String key, String userIp) {
        super(key, userIp);
    }

    public final void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
        super.initialize(request);
        initializeJsonRequest((AbstractGoogleJsonClientRequest) request);
    }

    /* access modifiers changed from: protected */
    public void initializeJsonRequest(AbstractGoogleJsonClientRequest<?> abstractGoogleJsonClientRequest) throws IOException {
    }

    public static class Builder extends CommonGoogleClientRequestInitializer.Builder {
        /* access modifiers changed from: protected */
        public Builder self() {
            return this;
        }
    }
}
