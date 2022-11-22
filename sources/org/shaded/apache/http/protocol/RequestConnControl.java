package org.shaded.apache.http.protocol;

import com.google.api.client.http.HttpMethods;
import java.io.IOException;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestInterceptor;

public class RequestConnControl implements HttpRequestInterceptor {
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if (!request.getRequestLine().getMethod().equalsIgnoreCase(HttpMethods.CONNECT) && !request.containsHeader("Connection")) {
            request.addHeader("Connection", HTTP.CONN_KEEP_ALIVE);
        }
    }
}
