package com.google.api.client.auth.oauth2;

import com.google.api.client.http.HttpResponseException;

public class TokenResponseException extends HttpResponseException {
    private static final long serialVersionUID = 4020689092957439244L;
    private final transient TokenErrorResponse details;

    TokenResponseException(HttpResponseException.Builder builder, TokenErrorResponse details2) {
        super(builder);
        this.details = details2;
    }

    public final TokenErrorResponse getDetails() {
        return this.details;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: com.google.api.client.auth.oauth2.TokenErrorResponse} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.api.client.auth.oauth2.TokenResponseException from(com.google.api.client.json.JsonFactory r11, com.google.api.client.http.HttpResponse r12) {
        /*
            com.google.api.client.http.HttpResponseException$Builder r1 = new com.google.api.client.http.HttpResponseException$Builder
            int r7 = r12.getStatusCode()
            java.lang.String r8 = r12.getStatusMessage()
            com.google.api.client.http.HttpHeaders r9 = r12.getHeaders()
            r1.<init>(r7, r8, r9)
            com.google.api.client.util.Preconditions.checkNotNull(r11)
            r4 = 0
            r3 = 0
            java.lang.String r2 = r12.getContentType()
            boolean r7 = r12.isSuccessStatusCode()     // Catch:{ IOException -> 0x0073 }
            if (r7 != 0) goto L_0x006e
            if (r2 == 0) goto L_0x006e
            java.io.InputStream r7 = r12.getContent()     // Catch:{ IOException -> 0x0073 }
            if (r7 == 0) goto L_0x006e
            java.lang.String r7 = "application/json; charset=UTF-8"
            boolean r7 = com.google.api.client.http.HttpMediaType.equalsIgnoreParameters(r7, r2)     // Catch:{ IOException -> 0x0073 }
            if (r7 == 0) goto L_0x006e
            com.google.api.client.json.JsonObjectParser r7 = new com.google.api.client.json.JsonObjectParser     // Catch:{ IOException -> 0x0073 }
            r7.<init>((com.google.api.client.json.JsonFactory) r11)     // Catch:{ IOException -> 0x0073 }
            java.io.InputStream r8 = r12.getContent()     // Catch:{ IOException -> 0x0073 }
            java.nio.charset.Charset r9 = r12.getContentCharset()     // Catch:{ IOException -> 0x0073 }
            java.lang.Class<com.google.api.client.auth.oauth2.TokenErrorResponse> r10 = com.google.api.client.auth.oauth2.TokenErrorResponse.class
            java.lang.Object r7 = r7.parseAndClose((java.io.InputStream) r8, (java.nio.charset.Charset) r9, r10)     // Catch:{ IOException -> 0x0073 }
            r0 = r7
            com.google.api.client.auth.oauth2.TokenErrorResponse r0 = (com.google.api.client.auth.oauth2.TokenErrorResponse) r0     // Catch:{ IOException -> 0x0073 }
            r4 = r0
            java.lang.String r3 = r4.toPrettyString()     // Catch:{ IOException -> 0x0073 }
        L_0x004b:
            java.lang.StringBuilder r6 = com.google.api.client.http.HttpResponseException.computeMessageBuffer(r12)
            boolean r7 = com.google.api.client.util.Strings.isNullOrEmpty(r3)
            if (r7 != 0) goto L_0x0061
            java.lang.String r7 = com.google.api.client.util.StringUtils.LINE_SEPARATOR
            java.lang.StringBuilder r7 = r6.append(r7)
            r7.append(r3)
            r1.setContent(r3)
        L_0x0061:
            java.lang.String r7 = r6.toString()
            r1.setMessage(r7)
            com.google.api.client.auth.oauth2.TokenResponseException r7 = new com.google.api.client.auth.oauth2.TokenResponseException
            r7.<init>(r1, r4)
            return r7
        L_0x006e:
            java.lang.String r3 = r12.parseAsString()     // Catch:{ IOException -> 0x0073 }
            goto L_0x004b
        L_0x0073:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.auth.oauth2.TokenResponseException.from(com.google.api.client.json.JsonFactory, com.google.api.client.http.HttpResponse):com.google.api.client.auth.oauth2.TokenResponseException");
    }
}
