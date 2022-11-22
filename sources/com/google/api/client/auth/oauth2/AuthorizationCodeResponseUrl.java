package com.google.api.client.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;

public class AuthorizationCodeResponseUrl extends GenericUrl {
    @Key
    private String code;
    @Key
    private String error;
    @Key("error_description")
    private String errorDescription;
    @Key("error_uri")
    private String errorUri;
    @Key
    private String state;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AuthorizationCodeResponseUrl(String encodedResponseUrl) {
        super(encodedResponseUrl);
        boolean z;
        boolean z2 = true;
        if (this.code == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z == (this.error == null) ? false : z2);
    }

    public final String getCode() {
        return this.code;
    }

    public AuthorizationCodeResponseUrl setCode(String code2) {
        this.code = code2;
        return this;
    }

    public final String getState() {
        return this.state;
    }

    public AuthorizationCodeResponseUrl setState(String state2) {
        this.state = state2;
        return this;
    }

    public final String getError() {
        return this.error;
    }

    public AuthorizationCodeResponseUrl setError(String error2) {
        this.error = error2;
        return this;
    }

    public final String getErrorDescription() {
        return this.errorDescription;
    }

    public AuthorizationCodeResponseUrl setErrorDescription(String errorDescription2) {
        this.errorDescription = errorDescription2;
        return this;
    }

    public final String getErrorUri() {
        return this.errorUri;
    }

    public AuthorizationCodeResponseUrl setErrorUri(String errorUri2) {
        this.errorUri = errorUri2;
        return this;
    }

    public AuthorizationCodeResponseUrl set(String fieldName, Object value) {
        return (AuthorizationCodeResponseUrl) super.set(fieldName, value);
    }

    public AuthorizationCodeResponseUrl clone() {
        return (AuthorizationCodeResponseUrl) super.clone();
    }
}
