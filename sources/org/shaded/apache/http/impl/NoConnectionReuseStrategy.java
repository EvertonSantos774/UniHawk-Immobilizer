package org.shaded.apache.http.impl;

import org.shaded.apache.http.ConnectionReuseStrategy;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.protocol.HttpContext;

public class NoConnectionReuseStrategy implements ConnectionReuseStrategy {
    public boolean keepAlive(HttpResponse response, HttpContext context) {
        if (response == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        } else if (context != null) {
            return false;
        } else {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
    }
}
