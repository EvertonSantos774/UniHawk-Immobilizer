package com.google.api.client.auth.oauth;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.Beta;
import com.google.api.client.util.escape.PercentEscaper;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map;
import org.shaded.apache.http.HttpHost;

@Beta
public final class OAuthParameters implements HttpExecuteInterceptor, HttpRequestInitializer {
    private static final PercentEscaper ESCAPER = new PercentEscaper("-_.~", false);
    private static final SecureRandom RANDOM = new SecureRandom();
    public String callback;
    public String consumerKey;
    public String nonce;
    public String realm;
    public String signature;
    public String signatureMethod;
    public OAuthSigner signer;
    public String timestamp;
    public String token;
    public String verifier;
    public String version;

    public void computeNonce() {
        this.nonce = Long.toHexString(Math.abs(RANDOM.nextLong()));
    }

    public void computeTimestamp() {
        this.timestamp = Long.toString(System.currentTimeMillis() / 1000);
    }

    private static class Parameter implements Comparable<Parameter> {
        private final String key;
        private final String value;

        public Parameter(String key2, String value2) {
            this.key = key2;
            this.value = value2;
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

        public int compareTo(Parameter p) {
            int result = this.key.compareTo(p.key);
            return result == 0 ? this.value.compareTo(p.value) : result;
        }
    }

    public void computeSignature(String requestMethod, GenericUrl requestUrl) throws GeneralSecurityException {
        OAuthSigner signer2 = this.signer;
        String signatureMethod2 = signer2.getSignatureMethod();
        this.signatureMethod = signatureMethod2;
        SortedMultiset<Parameter> parameters = TreeMultiset.create();
        putParameterIfValueNotNull(parameters, "oauth_callback", this.callback);
        putParameterIfValueNotNull(parameters, "oauth_consumer_key", this.consumerKey);
        putParameterIfValueNotNull(parameters, "oauth_nonce", this.nonce);
        putParameterIfValueNotNull(parameters, "oauth_signature_method", signatureMethod2);
        putParameterIfValueNotNull(parameters, "oauth_timestamp", this.timestamp);
        putParameterIfValueNotNull(parameters, "oauth_token", this.token);
        putParameterIfValueNotNull(parameters, "oauth_verifier", this.verifier);
        putParameterIfValueNotNull(parameters, "oauth_version", this.version);
        for (Map.Entry<String, Object> fieldEntry : requestUrl.entrySet()) {
            Object value = fieldEntry.getValue();
            if (value != null) {
                String name = fieldEntry.getKey();
                if (value instanceof Collection) {
                    for (Object repeatedValue : (Collection) value) {
                        putParameter(parameters, name, repeatedValue);
                    }
                } else {
                    putParameter(parameters, name, value);
                }
            }
        }
        StringBuilder parametersBuf = new StringBuilder();
        boolean first = true;
        for (Parameter parameter : parameters.elementSet()) {
            if (first) {
                first = false;
            } else {
                parametersBuf.append('&');
            }
            parametersBuf.append(parameter.getKey());
            String value2 = parameter.getValue();
            if (value2 != null) {
                parametersBuf.append('=').append(value2);
            }
        }
        String normalizedParameters = parametersBuf.toString();
        GenericUrl normalized = new GenericUrl();
        String scheme = requestUrl.getScheme();
        normalized.setScheme(scheme);
        normalized.setHost(requestUrl.getHost());
        normalized.setPathParts(requestUrl.getPathParts());
        int port = requestUrl.getPort();
        if ((HttpHost.DEFAULT_SCHEME_NAME.equals(scheme) && port == 80) || ("https".equals(scheme) && port == 443)) {
            port = -1;
        }
        normalized.setPort(port);
        String normalizedPath = normalized.build();
        StringBuilder buf = new StringBuilder();
        buf.append(escape(requestMethod)).append('&');
        buf.append(escape(normalizedPath)).append('&');
        buf.append(escape(normalizedParameters));
        this.signature = signer2.computeSignature(buf.toString());
    }

    public String getAuthorizationHeader() {
        StringBuilder buf = new StringBuilder("OAuth");
        appendParameter(buf, "realm", this.realm);
        appendParameter(buf, "oauth_callback", this.callback);
        appendParameter(buf, "oauth_consumer_key", this.consumerKey);
        appendParameter(buf, "oauth_nonce", this.nonce);
        appendParameter(buf, "oauth_signature", this.signature);
        appendParameter(buf, "oauth_signature_method", this.signatureMethod);
        appendParameter(buf, "oauth_timestamp", this.timestamp);
        appendParameter(buf, "oauth_token", this.token);
        appendParameter(buf, "oauth_verifier", this.verifier);
        appendParameter(buf, "oauth_version", this.version);
        return buf.substring(0, buf.length() - 1);
    }

    private void appendParameter(StringBuilder buf, String name, String value) {
        if (value != null) {
            buf.append(' ').append(escape(name)).append("=\"").append(escape(value)).append("\",");
        }
    }

    private void putParameterIfValueNotNull(Multiset<Parameter> parameters, String key, String value) {
        if (value != null) {
            putParameter(parameters, key, value);
        }
    }

    private void putParameter(Multiset<Parameter> parameters, String key, Object value) {
        parameters.add(new Parameter(escape(key), value == null ? null : escape(value.toString())));
    }

    public static String escape(String value) {
        return ESCAPER.escape(value);
    }

    public void initialize(HttpRequest request) throws IOException {
        request.setInterceptor(this);
    }

    public void intercept(HttpRequest request) throws IOException {
        computeNonce();
        computeTimestamp();
        try {
            computeSignature(request.getRequestMethod(), request.getUrl());
            request.getHeaders().setAuthorization(getAuthorizationHeader());
        } catch (GeneralSecurityException e) {
            IOException io = new IOException();
            io.initCause(e);
            throw io;
        }
    }
}
