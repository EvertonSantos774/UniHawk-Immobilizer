package com.google.api.client.googleapis.services.json;

import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;

public abstract class AbstractGoogleJsonClient extends AbstractGoogleClient {
    protected AbstractGoogleJsonClient(Builder builder) {
        super(builder);
    }

    public JsonObjectParser getObjectParser() {
        return (JsonObjectParser) super.getObjectParser();
    }

    public final JsonFactory getJsonFactory() {
        return getObjectParser().getJsonFactory();
    }

    public static abstract class Builder extends AbstractGoogleClient.Builder {
        public abstract AbstractGoogleJsonClient build();

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected Builder(com.google.api.client.http.HttpTransport r7, com.google.api.client.json.JsonFactory r8, java.lang.String r9, java.lang.String r10, com.google.api.client.http.HttpRequestInitializer r11, boolean r12) {
            /*
                r6 = this;
                com.google.api.client.json.JsonObjectParser$Builder r1 = new com.google.api.client.json.JsonObjectParser$Builder
                r1.<init>(r8)
                if (r12 == 0) goto L_0x0029
                r0 = 2
                java.lang.String[] r0 = new java.lang.String[r0]
                r2 = 0
                java.lang.String r3 = "data"
                r0[r2] = r3
                r2 = 1
                java.lang.String r3 = "error"
                r0[r2] = r3
                java.util.List r0 = java.util.Arrays.asList(r0)
            L_0x0018:
                com.google.api.client.json.JsonObjectParser$Builder r0 = r1.setWrapperKeys(r0)
                com.google.api.client.json.JsonObjectParser r4 = r0.build()
                r0 = r6
                r1 = r7
                r2 = r9
                r3 = r10
                r5 = r11
                r0.<init>(r1, r2, r3, r4, r5)
                return
            L_0x0029:
                java.util.Set r0 = java.util.Collections.emptySet()
                goto L_0x0018
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder.<init>(com.google.api.client.http.HttpTransport, com.google.api.client.json.JsonFactory, java.lang.String, java.lang.String, com.google.api.client.http.HttpRequestInitializer, boolean):void");
        }

        public final JsonObjectParser getObjectParser() {
            return (JsonObjectParser) super.getObjectParser();
        }

        public final JsonFactory getJsonFactory() {
            return getObjectParser().getJsonFactory();
        }

        public Builder setRootUrl(String rootUrl) {
            return (Builder) super.setRootUrl(rootUrl);
        }

        public Builder setServicePath(String servicePath) {
            return (Builder) super.setServicePath(servicePath);
        }

        public Builder setGoogleClientRequestInitializer(GoogleClientRequestInitializer googleClientRequestInitializer) {
            return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
        }

        public Builder setHttpRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
            return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
        }

        public Builder setApplicationName(String applicationName) {
            return (Builder) super.setApplicationName(applicationName);
        }

        public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
            return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
        }

        public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
            return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
        }

        public Builder setSuppressAllChecks(boolean suppressAllChecks) {
            return (Builder) super.setSuppressAllChecks(suppressAllChecks);
        }
    }
}
