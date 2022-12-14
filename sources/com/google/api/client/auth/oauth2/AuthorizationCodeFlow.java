package com.google.api.client.auth.oauth2;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Joiner;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Strings;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class AuthorizationCodeFlow {
    private final String authorizationServerEncodedUrl;
    private final HttpExecuteInterceptor clientAuthentication;
    private final String clientId;
    private final Clock clock;
    private final CredentialCreatedListener credentialCreatedListener;
    @Beta
    private final DataStore<StoredCredential> credentialDataStore;
    @Beta
    @Deprecated
    private final CredentialStore credentialStore;
    private final JsonFactory jsonFactory;
    private final Credential.AccessMethod method;
    private final Collection<CredentialRefreshListener> refreshListeners;
    private final HttpRequestInitializer requestInitializer;
    private final Collection<String> scopes;
    private final String tokenServerEncodedUrl;
    private final HttpTransport transport;

    public interface CredentialCreatedListener {
        void onCredentialCreated(Credential credential, TokenResponse tokenResponse) throws IOException;
    }

    public AuthorizationCodeFlow(Credential.AccessMethod method2, HttpTransport transport2, JsonFactory jsonFactory2, GenericUrl tokenServerUrl, HttpExecuteInterceptor clientAuthentication2, String clientId2, String authorizationServerEncodedUrl2) {
        this(new Builder(method2, transport2, jsonFactory2, tokenServerUrl, clientAuthentication2, clientId2, authorizationServerEncodedUrl2));
    }

    protected AuthorizationCodeFlow(Builder builder) {
        this.method = (Credential.AccessMethod) Preconditions.checkNotNull(builder.method);
        this.transport = (HttpTransport) Preconditions.checkNotNull(builder.transport);
        this.jsonFactory = (JsonFactory) Preconditions.checkNotNull(builder.jsonFactory);
        this.tokenServerEncodedUrl = ((GenericUrl) Preconditions.checkNotNull(builder.tokenServerUrl)).build();
        this.clientAuthentication = builder.clientAuthentication;
        this.clientId = (String) Preconditions.checkNotNull(builder.clientId);
        this.authorizationServerEncodedUrl = (String) Preconditions.checkNotNull(builder.authorizationServerEncodedUrl);
        this.requestInitializer = builder.requestInitializer;
        this.credentialStore = builder.credentialStore;
        this.credentialDataStore = builder.credentialDataStore;
        this.scopes = Collections.unmodifiableCollection(builder.scopes);
        this.clock = (Clock) Preconditions.checkNotNull(builder.clock);
        this.credentialCreatedListener = builder.credentialCreatedListener;
        this.refreshListeners = Collections.unmodifiableCollection(builder.refreshListeners);
    }

    public AuthorizationCodeRequestUrl newAuthorizationUrl() {
        return new AuthorizationCodeRequestUrl(this.authorizationServerEncodedUrl, this.clientId).setScopes(this.scopes);
    }

    public AuthorizationCodeTokenRequest newTokenRequest(String authorizationCode) {
        return new AuthorizationCodeTokenRequest(this.transport, this.jsonFactory, new GenericUrl(this.tokenServerEncodedUrl), authorizationCode).setClientAuthentication(this.clientAuthentication).setRequestInitializer(this.requestInitializer).setScopes(this.scopes);
    }

    public Credential createAndStoreCredential(TokenResponse response, String userId) throws IOException {
        Credential credential = newCredential(userId).setFromTokenResponse(response);
        if (this.credentialStore != null) {
            this.credentialStore.store(userId, credential);
        }
        if (this.credentialDataStore != null) {
            this.credentialDataStore.set(userId, new StoredCredential(credential));
        }
        if (this.credentialCreatedListener != null) {
            this.credentialCreatedListener.onCredentialCreated(credential, response);
        }
        return credential;
    }

    public Credential loadCredential(String userId) throws IOException {
        if (Strings.isNullOrEmpty(userId)) {
            return null;
        }
        if (this.credentialDataStore == null && this.credentialStore == null) {
            return null;
        }
        Credential credential = newCredential(userId);
        if (this.credentialDataStore != null) {
            StoredCredential stored = this.credentialDataStore.get(userId);
            if (stored == null) {
                return null;
            }
            credential.setAccessToken(stored.getAccessToken());
            credential.setRefreshToken(stored.getRefreshToken());
            credential.setExpirationTimeMilliseconds(stored.getExpirationTimeMilliseconds());
            return credential;
        } else if (!this.credentialStore.load(userId, credential)) {
            return null;
        } else {
            return credential;
        }
    }

    private Credential newCredential(String userId) {
        Credential.Builder builder = new Credential.Builder(this.method).setTransport(this.transport).setJsonFactory(this.jsonFactory).setTokenServerEncodedUrl(this.tokenServerEncodedUrl).setClientAuthentication(this.clientAuthentication).setRequestInitializer(this.requestInitializer).setClock(this.clock);
        if (this.credentialDataStore != null) {
            builder.addRefreshListener(new DataStoreCredentialRefreshListener(userId, this.credentialDataStore));
        } else if (this.credentialStore != null) {
            builder.addRefreshListener(new CredentialStoreRefreshListener(userId, this.credentialStore));
        }
        builder.getRefreshListeners().addAll(this.refreshListeners);
        return builder.build();
    }

    public final Credential.AccessMethod getMethod() {
        return this.method;
    }

    public final HttpTransport getTransport() {
        return this.transport;
    }

    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }

    public final String getTokenServerEncodedUrl() {
        return this.tokenServerEncodedUrl;
    }

    public final HttpExecuteInterceptor getClientAuthentication() {
        return this.clientAuthentication;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getAuthorizationServerEncodedUrl() {
        return this.authorizationServerEncodedUrl;
    }

    @Beta
    @Deprecated
    public final CredentialStore getCredentialStore() {
        return this.credentialStore;
    }

    @Beta
    public final DataStore<StoredCredential> getCredentialDataStore() {
        return this.credentialDataStore;
    }

    public final HttpRequestInitializer getRequestInitializer() {
        return this.requestInitializer;
    }

    public final String getScopesAsString() {
        return Joiner.m38on(' ').join(this.scopes);
    }

    public final Collection<String> getScopes() {
        return this.scopes;
    }

    public final Clock getClock() {
        return this.clock;
    }

    public final Collection<CredentialRefreshListener> getRefreshListeners() {
        return this.refreshListeners;
    }

    public static class Builder {
        String authorizationServerEncodedUrl;
        HttpExecuteInterceptor clientAuthentication;
        String clientId;
        Clock clock = Clock.SYSTEM;
        CredentialCreatedListener credentialCreatedListener;
        @Beta
        DataStore<StoredCredential> credentialDataStore;
        @Beta
        @Deprecated
        CredentialStore credentialStore;
        JsonFactory jsonFactory;
        Credential.AccessMethod method;
        Collection<CredentialRefreshListener> refreshListeners = Lists.newArrayList();
        HttpRequestInitializer requestInitializer;
        Collection<String> scopes = Lists.newArrayList();
        GenericUrl tokenServerUrl;
        HttpTransport transport;

        public Builder(Credential.AccessMethod method2, HttpTransport transport2, JsonFactory jsonFactory2, GenericUrl tokenServerUrl2, HttpExecuteInterceptor clientAuthentication2, String clientId2, String authorizationServerEncodedUrl2) {
            setMethod(method2);
            setTransport(transport2);
            setJsonFactory(jsonFactory2);
            setTokenServerUrl(tokenServerUrl2);
            setClientAuthentication(clientAuthentication2);
            setClientId(clientId2);
            setAuthorizationServerEncodedUrl(authorizationServerEncodedUrl2);
        }

        public AuthorizationCodeFlow build() {
            return new AuthorizationCodeFlow(this);
        }

        public final Credential.AccessMethod getMethod() {
            return this.method;
        }

        public Builder setMethod(Credential.AccessMethod method2) {
            this.method = (Credential.AccessMethod) Preconditions.checkNotNull(method2);
            return this;
        }

        public final HttpTransport getTransport() {
            return this.transport;
        }

        public Builder setTransport(HttpTransport transport2) {
            this.transport = (HttpTransport) Preconditions.checkNotNull(transport2);
            return this;
        }

        public final JsonFactory getJsonFactory() {
            return this.jsonFactory;
        }

        public Builder setJsonFactory(JsonFactory jsonFactory2) {
            this.jsonFactory = (JsonFactory) Preconditions.checkNotNull(jsonFactory2);
            return this;
        }

        public final GenericUrl getTokenServerUrl() {
            return this.tokenServerUrl;
        }

        public Builder setTokenServerUrl(GenericUrl tokenServerUrl2) {
            this.tokenServerUrl = (GenericUrl) Preconditions.checkNotNull(tokenServerUrl2);
            return this;
        }

        public final HttpExecuteInterceptor getClientAuthentication() {
            return this.clientAuthentication;
        }

        public Builder setClientAuthentication(HttpExecuteInterceptor clientAuthentication2) {
            this.clientAuthentication = clientAuthentication2;
            return this;
        }

        public final String getClientId() {
            return this.clientId;
        }

        public Builder setClientId(String clientId2) {
            this.clientId = (String) Preconditions.checkNotNull(clientId2);
            return this;
        }

        public final String getAuthorizationServerEncodedUrl() {
            return this.authorizationServerEncodedUrl;
        }

        public Builder setAuthorizationServerEncodedUrl(String authorizationServerEncodedUrl2) {
            this.authorizationServerEncodedUrl = (String) Preconditions.checkNotNull(authorizationServerEncodedUrl2);
            return this;
        }

        @Beta
        @Deprecated
        public final CredentialStore getCredentialStore() {
            return this.credentialStore;
        }

        @Beta
        public final DataStore<StoredCredential> getCredentialDataStore() {
            return this.credentialDataStore;
        }

        public final Clock getClock() {
            return this.clock;
        }

        public Builder setClock(Clock clock2) {
            this.clock = (Clock) Preconditions.checkNotNull(clock2);
            return this;
        }

        @Beta
        @Deprecated
        public Builder setCredentialStore(CredentialStore credentialStore2) {
            Preconditions.checkArgument(this.credentialDataStore == null);
            this.credentialStore = credentialStore2;
            return this;
        }

        @Beta
        public Builder setDataStoreFactory(DataStoreFactory dataStoreFactory) throws IOException {
            return setCredentialDataStore(StoredCredential.getDefaultDataStore(dataStoreFactory));
        }

        @Beta
        public Builder setCredentialDataStore(DataStore<StoredCredential> credentialDataStore2) {
            Preconditions.checkArgument(this.credentialStore == null);
            this.credentialDataStore = credentialDataStore2;
            return this;
        }

        public final HttpRequestInitializer getRequestInitializer() {
            return this.requestInitializer;
        }

        public Builder setRequestInitializer(HttpRequestInitializer requestInitializer2) {
            this.requestInitializer = requestInitializer2;
            return this;
        }

        public Builder setScopes(Collection<String> scopes2) {
            this.scopes = (Collection) Preconditions.checkNotNull(scopes2);
            return this;
        }

        public final Collection<String> getScopes() {
            return this.scopes;
        }

        public Builder setCredentialCreatedListener(CredentialCreatedListener credentialCreatedListener2) {
            this.credentialCreatedListener = credentialCreatedListener2;
            return this;
        }

        public Builder addRefreshListener(CredentialRefreshListener refreshListener) {
            this.refreshListeners.add(Preconditions.checkNotNull(refreshListener));
            return this;
        }

        public final Collection<CredentialRefreshListener> getRefreshListeners() {
            return this.refreshListeners;
        }

        public Builder setRefreshListeners(Collection<CredentialRefreshListener> refreshListeners2) {
            this.refreshListeners = (Collection) Preconditions.checkNotNull(refreshListeners2);
            return this;
        }

        public final CredentialCreatedListener getCredentialCreatedListener() {
            return this.credentialCreatedListener;
        }
    }
}
