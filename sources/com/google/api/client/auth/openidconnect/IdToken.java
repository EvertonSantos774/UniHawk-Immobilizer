package com.google.api.client.auth.openidconnect;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.json.webtoken.JsonWebToken;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Beta
public class IdToken extends JsonWebSignature {
    public IdToken(JsonWebSignature.Header header, Payload payload, byte[] signatureBytes, byte[] signedContentBytes) {
        super(header, payload, signatureBytes, signedContentBytes);
    }

    public Payload getPayload() {
        return (Payload) super.getPayload();
    }

    public final boolean verifyIssuer(String expectedIssuer) {
        return verifyIssuer((Collection<String>) Collections.singleton(expectedIssuer));
    }

    public final boolean verifyIssuer(Collection<String> expectedIssuer) {
        return expectedIssuer.contains(getPayload().getIssuer());
    }

    public final boolean verifyAudience(Collection<String> trustedClientIds) {
        Collection<String> audience = getPayload().getAudienceAsList();
        if (audience.isEmpty()) {
            return false;
        }
        return trustedClientIds.containsAll(audience);
    }

    public final boolean verifyTime(long currentTimeMillis, long acceptableTimeSkewSeconds) {
        return verifyExpirationTime(currentTimeMillis, acceptableTimeSkewSeconds) && verifyIssuedAtTime(currentTimeMillis, acceptableTimeSkewSeconds);
    }

    public final boolean verifyExpirationTime(long currentTimeMillis, long acceptableTimeSkewSeconds) {
        return currentTimeMillis <= (getPayload().getExpirationTimeSeconds().longValue() + acceptableTimeSkewSeconds) * 1000;
    }

    public final boolean verifyIssuedAtTime(long currentTimeMillis, long acceptableTimeSkewSeconds) {
        return currentTimeMillis >= (getPayload().getIssuedAtTimeSeconds().longValue() - acceptableTimeSkewSeconds) * 1000;
    }

    public static IdToken parse(JsonFactory jsonFactory, String idTokenString) throws IOException {
        JsonWebSignature jws = JsonWebSignature.parser(jsonFactory).setPayloadClass(Payload.class).parse(idTokenString);
        return new IdToken(jws.getHeader(), (Payload) jws.getPayload(), jws.getSignatureBytes(), jws.getSignedContentBytes());
    }

    @Beta
    public static class Payload extends JsonWebToken.Payload {
        @Key("at_hash")
        private String accessTokenHash;
        @Key("auth_time")
        private Long authorizationTimeSeconds;
        @Key("azp")
        private String authorizedParty;
        @Key("acr")
        private String classReference;
        @Key("amr")
        private List<String> methodsReferences;
        @Key
        private String nonce;

        public final Long getAuthorizationTimeSeconds() {
            return this.authorizationTimeSeconds;
        }

        public Payload setAuthorizationTimeSeconds(Long authorizationTimeSeconds2) {
            this.authorizationTimeSeconds = authorizationTimeSeconds2;
            return this;
        }

        public final String getAuthorizedParty() {
            return this.authorizedParty;
        }

        public Payload setAuthorizedParty(String authorizedParty2) {
            this.authorizedParty = authorizedParty2;
            return this;
        }

        public final String getNonce() {
            return this.nonce;
        }

        public Payload setNonce(String nonce2) {
            this.nonce = nonce2;
            return this;
        }

        public final String getAccessTokenHash() {
            return this.accessTokenHash;
        }

        public Payload setAccessTokenHash(String accessTokenHash2) {
            this.accessTokenHash = accessTokenHash2;
            return this;
        }

        public final String getClassReference() {
            return this.classReference;
        }

        public Payload setClassReference(String classReference2) {
            this.classReference = classReference2;
            return this;
        }

        public final List<String> getMethodsReferences() {
            return this.methodsReferences;
        }

        public Payload setMethodsReferences(List<String> methodsReferences2) {
            this.methodsReferences = methodsReferences2;
            return this;
        }

        public Payload setExpirationTimeSeconds(Long expirationTimeSeconds) {
            return (Payload) super.setExpirationTimeSeconds(expirationTimeSeconds);
        }

        public Payload setNotBeforeTimeSeconds(Long notBeforeTimeSeconds) {
            return (Payload) super.setNotBeforeTimeSeconds(notBeforeTimeSeconds);
        }

        public Payload setIssuedAtTimeSeconds(Long issuedAtTimeSeconds) {
            return (Payload) super.setIssuedAtTimeSeconds(issuedAtTimeSeconds);
        }

        public Payload setIssuer(String issuer) {
            return (Payload) super.setIssuer(issuer);
        }

        public Payload setAudience(Object audience) {
            return (Payload) super.setAudience(audience);
        }

        public Payload setJwtId(String jwtId) {
            return (Payload) super.setJwtId(jwtId);
        }

        public Payload setType(String type) {
            return (Payload) super.setType(type);
        }

        public Payload setSubject(String subject) {
            return (Payload) super.setSubject(subject);
        }

        public Payload set(String fieldName, Object value) {
            return (Payload) super.set(fieldName, value);
        }

        public Payload clone() {
            return (Payload) super.clone();
        }
    }
}
