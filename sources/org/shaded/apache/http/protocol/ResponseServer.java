package org.shaded.apache.http.protocol;

import java.io.IOException;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseInterceptor;
import org.shaded.apache.http.params.CoreProtocolPNames;

public class ResponseServer implements HttpResponseInterceptor {
    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        String s;
        if (response == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if (!response.containsHeader("Server") && (s = (String) response.getParams().getParameter(CoreProtocolPNames.ORIGIN_SERVER)) != null) {
            response.addHeader("Server", s);
        }
    }
}
