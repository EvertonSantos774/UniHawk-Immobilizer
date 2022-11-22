package com.google.api.client.googleapis.testing.auth.oauth2;

import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.testing.TestUtils;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Beta
public class MockTokenServerTransport extends MockHttpTransport {
    static final String EXPECTED_GRANT_TYPE = "urn:ietf:params:oauth:grant-type:jwt-bearer";
    static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final String LEGACY_TOKEN_SERVER_URL = "https://accounts.google.com/o/oauth2/token";
    private static final Logger LOGGER = Logger.getLogger(MockTokenServerTransport.class.getName());
    Map<String, String> clients;
    Map<String, String> refreshTokens;
    Map<String, String> serviceAccounts;
    final String tokenServerUrl;

    public MockTokenServerTransport() {
        this(GoogleOAuthConstants.TOKEN_SERVER_URL);
    }

    public MockTokenServerTransport(String tokenServerUrl2) {
        this.serviceAccounts = new HashMap();
        this.clients = new HashMap();
        this.refreshTokens = new HashMap();
        this.tokenServerUrl = tokenServerUrl2;
    }

    public void addServiceAccount(String email, String accessToken) {
        this.serviceAccounts.put(email, accessToken);
    }

    public void addClient(String clientId, String clientSecret) {
        this.clients.put(clientId, clientSecret);
    }

    public void addRefreshToken(String refreshToken, String accessTokenToReturn) {
        this.refreshTokens.put(refreshToken, accessTokenToReturn);
    }

    public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        if (url.equals(this.tokenServerUrl)) {
            return buildTokenRequest(url);
        }
        if (!url.equals(LEGACY_TOKEN_SERVER_URL)) {
            return super.buildRequest(method, url);
        }
        LOGGER.warning("Your configured token_uri is using a legacy endpoint. You may want to redownload your credentials.");
        return buildTokenRequest(url);
    }

    private MockLowLevelHttpRequest buildTokenRequest(String url) {
        return new MockLowLevelHttpRequest(url) {
            public LowLevelHttpResponse execute() throws IOException {
                String accessToken;
                Map<String, String> query = TestUtils.parseQuery(getContentAsString());
                String foundId = query.get("client_id");
                if (foundId != null) {
                    if (!MockTokenServerTransport.this.clients.containsKey(foundId)) {
                        throw new IOException("Client ID not found.");
                    }
                    String foundSecret = query.get("client_secret");
                    String expectedSecret = MockTokenServerTransport.this.clients.get(foundId);
                    if (foundSecret == null || !foundSecret.equals(expectedSecret)) {
                        throw new IOException("Client secret not found.");
                    }
                    String foundRefresh = query.get("refresh_token");
                    if (!MockTokenServerTransport.this.refreshTokens.containsKey(foundRefresh)) {
                        throw new IOException("Refresh Token not found.");
                    }
                    accessToken = MockTokenServerTransport.this.refreshTokens.get(foundRefresh);
                } else if (query.containsKey("grant_type")) {
                    if (!MockTokenServerTransport.EXPECTED_GRANT_TYPE.equals(query.get("grant_type"))) {
                        throw new IOException("Unexpected Grant Type.");
                    }
                    JsonWebSignature signature = JsonWebSignature.parse(MockTokenServerTransport.JSON_FACTORY, query.get("assertion"));
                    String foundEmail = signature.getPayload().getIssuer();
                    if (!MockTokenServerTransport.this.serviceAccounts.containsKey(foundEmail)) {
                        throw new IOException("Service Account Email not found as issuer.");
                    }
                    accessToken = MockTokenServerTransport.this.serviceAccounts.get(foundEmail);
                    String foundScopes = (String) signature.getPayload().get("scope");
                    if (foundScopes == null || foundScopes.length() == 0) {
                        throw new IOException("Scopes not found.");
                    }
                } else {
                    throw new IOException("Unknown token type.");
                }
                GenericJson refreshContents = new GenericJson();
                refreshContents.setFactory(MockTokenServerTransport.JSON_FACTORY);
                refreshContents.put("access_token", (Object) accessToken);
                refreshContents.put("expires_in", (Object) 3600);
                refreshContents.put("token_type", (Object) "Bearer");
                return new MockLowLevelHttpResponse().setContentType(Json.MEDIA_TYPE).setContent(refreshContents.toPrettyString());
            }
        };
    }
}
