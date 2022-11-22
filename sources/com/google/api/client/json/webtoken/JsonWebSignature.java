package com.google.api.client.json.webtoken;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.webtoken.JsonWebToken;
import com.google.api.client.util.Base64;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import com.google.api.client.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class JsonWebSignature extends JsonWebToken {
    private final byte[] signatureBytes;
    private final byte[] signedContentBytes;

    public JsonWebSignature(Header header, JsonWebToken.Payload payload, byte[] signatureBytes2, byte[] signedContentBytes2) {
        super(header, payload);
        this.signatureBytes = (byte[]) Preconditions.checkNotNull(signatureBytes2);
        this.signedContentBytes = (byte[]) Preconditions.checkNotNull(signedContentBytes2);
    }

    public static class Header extends JsonWebToken.Header {
        @Key("alg")
        private String algorithm;
        @Key("crit")
        private List<String> critical;
        @Key("jwk")
        private String jwk;
        @Key("jku")
        private String jwkUrl;
        @Key("kid")
        private String keyId;
        @Key("x5c")
        private ArrayList<String> x509Certificates;
        @Key("x5t")
        private String x509Thumbprint;
        @Key("x5u")
        private String x509Url;

        public Header setType(String type) {
            super.setType(type);
            return this;
        }

        public final String getAlgorithm() {
            return this.algorithm;
        }

        public Header setAlgorithm(String algorithm2) {
            this.algorithm = algorithm2;
            return this;
        }

        public final String getJwkUrl() {
            return this.jwkUrl;
        }

        public Header setJwkUrl(String jwkUrl2) {
            this.jwkUrl = jwkUrl2;
            return this;
        }

        public final String getJwk() {
            return this.jwk;
        }

        public Header setJwk(String jwk2) {
            this.jwk = jwk2;
            return this;
        }

        public final String getKeyId() {
            return this.keyId;
        }

        public Header setKeyId(String keyId2) {
            this.keyId = keyId2;
            return this;
        }

        public final String getX509Url() {
            return this.x509Url;
        }

        public Header setX509Url(String x509Url2) {
            this.x509Url = x509Url2;
            return this;
        }

        public final String getX509Thumbprint() {
            return this.x509Thumbprint;
        }

        public Header setX509Thumbprint(String x509Thumbprint2) {
            this.x509Thumbprint = x509Thumbprint2;
            return this;
        }

        public final List<String> getX509Certificates() {
            return new ArrayList(this.x509Certificates);
        }

        public Header setX509Certificates(List<String> x509Certificates2) {
            this.x509Certificates = new ArrayList<>(x509Certificates2);
            return this;
        }

        public final List<String> getCritical() {
            if (this.critical == null || this.critical.isEmpty()) {
                return null;
            }
            return new ArrayList(this.critical);
        }

        public Header setCritical(List<String> critical2) {
            this.critical = new ArrayList(critical2);
            return this;
        }

        public Header set(String fieldName, Object value) {
            return (Header) super.set(fieldName, value);
        }

        public Header clone() {
            return (Header) super.clone();
        }
    }

    public Header getHeader() {
        return (Header) super.getHeader();
    }

    public final boolean verifySignature(PublicKey publicKey) throws GeneralSecurityException {
        String algorithm = getHeader().getAlgorithm();
        if ("RS256".equals(algorithm)) {
            return SecurityUtils.verify(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), publicKey, this.signatureBytes, this.signedContentBytes);
        }
        if ("ES256".equals(algorithm)) {
            return SecurityUtils.verify(SecurityUtils.getEs256SignatureAlgorithm(), publicKey, DerEncoder.encode(this.signatureBytes), this.signedContentBytes);
        }
        return false;
    }

    @Beta
    public final X509Certificate verifySignature(X509TrustManager trustManager) throws GeneralSecurityException {
        List<String> x509Certificates = getHeader().getX509Certificates();
        if (x509Certificates == null || x509Certificates.isEmpty()) {
            return null;
        }
        String algorithm = getHeader().getAlgorithm();
        if ("RS256".equals(algorithm)) {
            return SecurityUtils.verify(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), trustManager, x509Certificates, this.signatureBytes, this.signedContentBytes);
        }
        if ("ES256".equals(algorithm)) {
            return SecurityUtils.verify(SecurityUtils.getEs256SignatureAlgorithm(), trustManager, x509Certificates, DerEncoder.encode(this.signatureBytes), this.signedContentBytes);
        }
        return null;
    }

    @Beta
    public final X509Certificate verifySignature() throws GeneralSecurityException {
        X509TrustManager trustManager = getDefaultX509TrustManager();
        if (trustManager == null) {
            return null;
        }
        return verifySignature(trustManager);
    }

    private static X509TrustManager getDefaultX509TrustManager() {
        try {
            TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            factory.init((KeyStore) null);
            for (TrustManager manager : factory.getTrustManagers()) {
                if (manager instanceof X509TrustManager) {
                    return (X509TrustManager) manager;
                }
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (KeyStoreException e2) {
            return null;
        }
    }

    public final byte[] getSignatureBytes() {
        return Arrays.copyOf(this.signatureBytes, this.signatureBytes.length);
    }

    public final byte[] getSignedContentBytes() {
        return Arrays.copyOf(this.signedContentBytes, this.signedContentBytes.length);
    }

    public static JsonWebSignature parse(JsonFactory jsonFactory, String tokenString) throws IOException {
        return parser(jsonFactory).parse(tokenString);
    }

    public static Parser parser(JsonFactory jsonFactory) {
        return new Parser(jsonFactory);
    }

    public static final class Parser {
        private Class<? extends Header> headerClass = Header.class;
        private final JsonFactory jsonFactory;
        private Class<? extends JsonWebToken.Payload> payloadClass = JsonWebToken.Payload.class;

        public Parser(JsonFactory jsonFactory2) {
            this.jsonFactory = (JsonFactory) Preconditions.checkNotNull(jsonFactory2);
        }

        public Class<? extends Header> getHeaderClass() {
            return this.headerClass;
        }

        public Parser setHeaderClass(Class<? extends Header> headerClass2) {
            this.headerClass = headerClass2;
            return this;
        }

        public Class<? extends JsonWebToken.Payload> getPayloadClass() {
            return this.payloadClass;
        }

        public Parser setPayloadClass(Class<? extends JsonWebToken.Payload> payloadClass2) {
            this.payloadClass = payloadClass2;
            return this;
        }

        public JsonFactory getJsonFactory() {
            return this.jsonFactory;
        }

        public JsonWebSignature parse(String tokenString) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4 = true;
            int firstDot = tokenString.indexOf(46);
            if (firstDot != -1) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z);
            byte[] headerBytes = Base64.decodeBase64(tokenString.substring(0, firstDot));
            int secondDot = tokenString.indexOf(46, firstDot + 1);
            if (secondDot != -1) {
                z2 = true;
            } else {
                z2 = false;
            }
            Preconditions.checkArgument(z2);
            if (tokenString.indexOf(46, secondDot + 1) == -1) {
                z3 = true;
            } else {
                z3 = false;
            }
            Preconditions.checkArgument(z3);
            byte[] payloadBytes = Base64.decodeBase64(tokenString.substring(firstDot + 1, secondDot));
            byte[] signatureBytes = Base64.decodeBase64(tokenString.substring(secondDot + 1));
            byte[] signedContentBytes = StringUtils.getBytesUtf8(tokenString.substring(0, secondDot));
            Header header = (Header) this.jsonFactory.fromInputStream(new ByteArrayInputStream(headerBytes), this.headerClass);
            if (header.getAlgorithm() == null) {
                z4 = false;
            }
            Preconditions.checkArgument(z4);
            return new JsonWebSignature(header, (JsonWebToken.Payload) this.jsonFactory.fromInputStream(new ByteArrayInputStream(payloadBytes), this.payloadClass), signatureBytes, signedContentBytes);
        }
    }

    public static String signUsingRsaSha256(PrivateKey privateKey, JsonFactory jsonFactory, Header header, JsonWebToken.Payload payload) throws GeneralSecurityException, IOException {
        String content = Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(header)) + "." + Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(payload));
        return content + "." + Base64.encodeBase64URLSafeString(SecurityUtils.sign(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), privateKey, StringUtils.getBytesUtf8(content)));
    }
}
