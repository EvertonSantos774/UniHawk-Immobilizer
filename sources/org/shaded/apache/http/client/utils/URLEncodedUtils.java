package org.shaded.apache.http.client.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.NameValuePair;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.message.BasicNameValuePair;
import org.shaded.apache.http.protocol.HTTP;
import org.shaded.apache.http.util.EntityUtils;

@Immutable
public class URLEncodedUtils {
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PARAMETER_SEPARATOR = "&";

    public static List<NameValuePair> parse(URI uri, String encoding) {
        List<NameValuePair> result = Collections.emptyList();
        String query = uri.getRawQuery();
        if (query == null || query.length() <= 0) {
            return result;
        }
        List<NameValuePair> result2 = new ArrayList<>();
        parse(result2, new Scanner(query), encoding);
        return result2;
    }

    public static List<NameValuePair> parse(HttpEntity entity) throws IOException {
        String content;
        List<NameValuePair> result = Collections.emptyList();
        String contentType = null;
        String charset = null;
        Header h = entity.getContentType();
        if (h != null) {
            HeaderElement[] elems = h.getElements();
            if (elems.length > 0) {
                HeaderElement elem = elems[0];
                contentType = elem.getName();
                NameValuePair param = elem.getParameterByName("charset");
                if (param != null) {
                    charset = param.getValue();
                }
            }
        }
        if (contentType == null || !contentType.equalsIgnoreCase("application/x-www-form-urlencoded") || (content = EntityUtils.toString(entity, HTTP.ASCII)) == null || content.length() <= 0) {
            return result;
        }
        List<NameValuePair> result2 = new ArrayList<>();
        parse(result2, new Scanner(content), charset);
        return result2;
    }

    public static boolean isEncoded(HttpEntity entity) {
        Header h = entity.getContentType();
        if (h == null) {
            return false;
        }
        HeaderElement[] elems = h.getElements();
        if (elems.length > 0) {
            return elems[0].getName().equalsIgnoreCase("application/x-www-form-urlencoded");
        }
        return false;
    }

    public static void parse(List<NameValuePair> parameters, Scanner scanner, String encoding) {
        scanner.useDelimiter(PARAMETER_SEPARATOR);
        while (scanner.hasNext()) {
            String[] nameValue = scanner.next().split(NAME_VALUE_SEPARATOR);
            if (nameValue.length == 0 || nameValue.length > 2) {
                throw new IllegalArgumentException("bad parameter");
            }
            String name = decode(nameValue[0], encoding);
            String value = null;
            if (nameValue.length == 2) {
                value = decode(nameValue[1], encoding);
            }
            parameters.add(new BasicNameValuePair(name, value));
        }
    }

    public static String format(List<? extends NameValuePair> parameters, String encoding) {
        StringBuilder result = new StringBuilder();
        for (NameValuePair parameter : parameters) {
            String encodedName = encode(parameter.getName(), encoding);
            String value = parameter.getValue();
            String encodedValue = value != null ? encode(value, encoding) : "";
            if (result.length() > 0) {
                result.append(PARAMETER_SEPARATOR);
            }
            result.append(encodedName);
            result.append(NAME_VALUE_SEPARATOR);
            result.append(encodedValue);
        }
        return result.toString();
    }

    private static String decode(String content, String encoding) {
        if (encoding == null) {
            encoding = "ISO-8859-1";
        }
        try {
            return URLDecoder.decode(content, encoding);
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }

    private static String encode(String content, String encoding) {
        if (encoding == null) {
            encoding = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(content, encoding);
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }
}
