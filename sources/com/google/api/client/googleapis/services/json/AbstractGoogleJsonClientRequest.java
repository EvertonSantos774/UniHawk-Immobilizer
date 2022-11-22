package com.google.api.client.googleapis.services.json;

import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonErrorContainer;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpResponse;
import java.io.IOException;

public abstract class AbstractGoogleJsonClientRequest<T> extends AbstractGoogleClientRequest<T> {
    private final Object jsonContent;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected AbstractGoogleJsonClientRequest(com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient r7, java.lang.String r8, java.lang.String r9, java.lang.Object r10, java.lang.Class<T> r11) {
        /*
            r6 = this;
            r0 = 0
            if (r10 != 0) goto L_0x000f
            r4 = r0
        L_0x0004:
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r5 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            r6.jsonContent = r10
            return
        L_0x000f:
            com.google.api.client.http.json.JsonHttpContent r1 = new com.google.api.client.http.json.JsonHttpContent
            com.google.api.client.json.JsonFactory r2 = r7.getJsonFactory()
            r1.<init>(r2, r10)
            com.google.api.client.json.JsonObjectParser r2 = r7.getObjectParser()
            java.util.Set r2 = r2.getWrapperKeys()
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x002b
        L_0x0026:
            com.google.api.client.http.json.JsonHttpContent r4 = r1.setWrapperKey(r0)
            goto L_0x0004
        L_0x002b:
            java.lang.String r0 = "data"
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest.<init>(com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient, java.lang.String, java.lang.String, java.lang.Object, java.lang.Class):void");
    }

    public AbstractGoogleJsonClient getAbstractGoogleClient() {
        return (AbstractGoogleJsonClient) super.getAbstractGoogleClient();
    }

    public AbstractGoogleJsonClientRequest<T> setDisableGZipContent(boolean disableGZipContent) {
        return (AbstractGoogleJsonClientRequest) super.setDisableGZipContent(disableGZipContent);
    }

    public AbstractGoogleJsonClientRequest<T> setRequestHeaders(HttpHeaders headers) {
        return (AbstractGoogleJsonClientRequest) super.setRequestHeaders(headers);
    }

    public final void queue(BatchRequest batchRequest, JsonBatchCallback<T> callback) throws IOException {
        super.queue(batchRequest, GoogleJsonErrorContainer.class, callback);
    }

    /* access modifiers changed from: protected */
    public GoogleJsonResponseException newExceptionOnError(HttpResponse response) {
        return GoogleJsonResponseException.from(getAbstractGoogleClient().getJsonFactory(), response);
    }

    public Object getJsonContent() {
        return this.jsonContent;
    }

    public AbstractGoogleJsonClientRequest<T> set(String fieldName, Object value) {
        return (AbstractGoogleJsonClientRequest) super.set(fieldName, value);
    }
}
