package org.shaded.apache.http.client.methods;

import com.google.common.net.HttpHeaders;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.HeaderIterator;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpOptions extends HttpRequestBase {
    public static final String METHOD_NAME = "OPTIONS";

    public HttpOptions() {
    }

    public HttpOptions(URI uri) {
        setURI(uri);
    }

    public HttpOptions(String uri) {
        setURI(URI.create(uri));
    }

    public String getMethod() {
        return "OPTIONS";
    }

    public Set<String> getAllowedMethods(HttpResponse response) {
        if (response == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
        HeaderIterator it = response.headerIterator(HttpHeaders.ALLOW);
        Set<String> methods = new HashSet<>();
        while (it.hasNext()) {
            for (HeaderElement element : it.nextHeader().getElements()) {
                methods.add(element.getName());
            }
        }
        return methods;
    }
}
