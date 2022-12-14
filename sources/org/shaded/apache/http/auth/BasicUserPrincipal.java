package org.shaded.apache.http.auth;

import java.security.Principal;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.util.LangUtils;

@Immutable
public final class BasicUserPrincipal implements Principal {
    private final String username;

    public BasicUserPrincipal(String username2) {
        if (username2 == null) {
            throw new IllegalArgumentException("User name may not be null");
        }
        this.username = username2;
    }

    public String getName() {
        return this.username;
    }

    public int hashCode() {
        return LangUtils.hashCode(17, (Object) this.username);
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof BasicUserPrincipal) || !LangUtils.equals((Object) this.username, (Object) ((BasicUserPrincipal) o).username)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "[principal: " + this.username + "]";
    }
}
