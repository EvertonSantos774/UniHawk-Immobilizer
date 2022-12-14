package com.google.api.client.testing.http;

import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@Beta
public class MockLowLevelHttpRequest extends LowLevelHttpRequest {
    private final Map<String, List<String>> headersMap = new HashMap();
    private MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
    private String url;

    public MockLowLevelHttpRequest() {
    }

    public MockLowLevelHttpRequest(String url2) {
        this.url = url2;
    }

    public void addHeader(String name, String value) throws IOException {
        String name2 = name.toLowerCase(Locale.US);
        List<String> values = this.headersMap.get(name2);
        if (values == null) {
            values = new ArrayList<>();
            this.headersMap.put(name2, values);
        }
        values.add(value);
    }

    public LowLevelHttpResponse execute() throws IOException {
        return this.response;
    }

    public String getUrl() {
        return this.url;
    }

    public Map<String, List<String>> getHeaders() {
        return Collections.unmodifiableMap(this.headersMap);
    }

    public String getFirstHeaderValue(String name) {
        List<String> values = this.headersMap.get(name.toLowerCase(Locale.US));
        if (values == null) {
            return null;
        }
        return values.get(0);
    }

    public List<String> getHeaderValues(String name) {
        List<String> values = this.headersMap.get(name.toLowerCase(Locale.US));
        return values == null ? Collections.emptyList() : Collections.unmodifiableList(values);
    }

    public MockLowLevelHttpRequest setUrl(String url2) {
        this.url = url2;
        return this;
    }

    public String getContentAsString() throws IOException {
        Charset charset;
        if (getStreamingContent() == null) {
            return "";
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        getStreamingContent().writeTo(out);
        String contentEncoding = getContentEncoding();
        if (contentEncoding != null && contentEncoding.contains("gzip")) {
            InputStream contentInputStream = new GZIPInputStream(new ByteArrayInputStream(out.toByteArray()));
            out = new ByteArrayOutputStream();
            IOUtils.copy(contentInputStream, out);
        }
        String contentType = getContentType();
        HttpMediaType mediaType = contentType != null ? new HttpMediaType(contentType) : null;
        if (mediaType == null || mediaType.getCharsetParameter() == null) {
            charset = Charsets.ISO_8859_1;
        } else {
            charset = mediaType.getCharsetParameter();
        }
        return out.toString(charset.name());
    }

    public MockLowLevelHttpResponse getResponse() {
        return this.response;
    }

    public MockLowLevelHttpRequest setResponse(MockLowLevelHttpResponse response2) {
        this.response = response2;
        return this;
    }
}
