package com.google.api.client.googleapis.batch;

import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.util.ByteStreams;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

final class BatchUnparsedResponse {
    private final String boundary;
    private int contentId = 0;
    boolean hasNext = true;
    private final InputStream inputStream;
    private final List<BatchRequest.RequestInfo<?, ?>> requestInfos;
    private final boolean retryAllowed;
    List<BatchRequest.RequestInfo<?, ?>> unsuccessfulRequestInfos = new ArrayList();

    BatchUnparsedResponse(InputStream inputStream2, String boundary2, List<BatchRequest.RequestInfo<?, ?>> requestInfos2, boolean retryAllowed2) throws IOException {
        this.boundary = boundary2;
        this.requestInfos = requestInfos2;
        this.retryAllowed = retryAllowed2;
        this.inputStream = inputStream2;
        checkForFinalBoundary(readLine());
    }

    /* access modifiers changed from: package-private */
    public void parseNextResponse() throws IOException {
        String line;
        String line2;
        InputStream body;
        String line3;
        this.contentId++;
        do {
            line = readLine();
            if (line == null || line.equals("")) {
                int statusCode = Integer.parseInt(readLine().split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)[1]);
                List<String> headerNames = new ArrayList<>();
                List<String> headerValues = new ArrayList<>();
                long contentLength = -1;
            }
            line = readLine();
            break;
        } while (line.equals(""));
        int statusCode2 = Integer.parseInt(readLine().split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)[1]);
        List<String> headerNames2 = new ArrayList<>();
        List<String> headerValues2 = new ArrayList<>();
        long contentLength2 = -1;
        while (true) {
            line2 = readLine();
            if (line2 != null && !line2.equals("")) {
                String[] headerParts = line2.split(": ", 2);
                String headerName = headerParts[0];
                String headerValue = headerParts[1];
                headerNames2.add(headerName);
                headerValues2.add(headerValue);
                if ("Content-Length".equalsIgnoreCase(headerName.trim())) {
                    contentLength2 = Long.parseLong(headerValue);
                }
            }
        }
        if (contentLength2 == -1) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            while (true) {
                line3 = readRawLine();
                if (line3 == null || line3.startsWith(this.boundary)) {
                    body = trimCrlf(buffer.toByteArray());
                    line2 = trimCrlf(line3);
                } else {
                    buffer.write(line3.getBytes("ISO-8859-1"));
                }
            }
            body = trimCrlf(buffer.toByteArray());
            line2 = trimCrlf(line3);
        } else {
            body = new FilterInputStream(ByteStreams.limit(this.inputStream, contentLength2)) {
                public void close() {
                }
            };
        }
        parseAndCallback(this.requestInfos.get(this.contentId - 1), statusCode2, getFakeResponse(statusCode2, body, headerNames2, headerValues2));
        while (true) {
            if (body.skip(contentLength2) <= 0 && body.read() == -1) {
                break;
            }
        }
        if (contentLength2 != -1) {
            line2 = readLine();
        }
        while (line2 != null && line2.length() == 0) {
            line2 = readLine();
        }
        checkForFinalBoundary(line2);
    }

    private <T, E> void parseAndCallback(BatchRequest.RequestInfo<T, E> requestInfo, int statusCode, HttpResponse response) throws IOException {
        BatchCallback<T, E> callback = requestInfo.callback;
        HttpHeaders responseHeaders = response.getHeaders();
        HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler = requestInfo.request.getUnsuccessfulResponseHandler();
        if (!HttpStatusCodes.isSuccess(statusCode)) {
            HttpContent content = requestInfo.request.getContent();
            boolean retrySupported = this.retryAllowed && (content == null || content.retrySupported());
            boolean errorHandled = false;
            boolean redirectRequest = false;
            if (unsuccessfulResponseHandler != null) {
                errorHandled = unsuccessfulResponseHandler.handleResponse(requestInfo.request, response, retrySupported);
            }
            if (!errorHandled && requestInfo.request.handleRedirect(response.getStatusCode(), response.getHeaders())) {
                redirectRequest = true;
            }
            if (retrySupported && (errorHandled || redirectRequest)) {
                this.unsuccessfulRequestInfos.add(requestInfo);
            } else if (callback != null) {
                callback.onFailure(getParsedDataClass(requestInfo.errorClass, response, requestInfo), responseHeaders);
            }
        } else if (callback != null) {
            callback.onSuccess(getParsedDataClass(requestInfo.dataClass, response, requestInfo), responseHeaders);
        }
    }

    private <A, T, E> A getParsedDataClass(Class<A> dataClass, HttpResponse response, BatchRequest.RequestInfo<T, E> requestInfo) throws IOException {
        if (dataClass == Void.class) {
            return null;
        }
        return requestInfo.request.getParser().parseAndClose(response.getContent(), response.getContentCharset(), dataClass);
    }

    private HttpResponse getFakeResponse(int statusCode, InputStream partContent, List<String> headerNames, List<String> headerValues) throws IOException {
        HttpRequest request = new FakeResponseHttpTransport(statusCode, partContent, headerNames, headerValues).createRequestFactory().buildPostRequest(new GenericUrl(HttpTesting.SIMPLE_URL), (HttpContent) null);
        request.setLoggingEnabled(false);
        request.setThrowExceptionOnExecuteError(false);
        return request.execute();
    }

    private String readRawLine() throws IOException {
        int b = this.inputStream.read();
        if (b == -1) {
            return null;
        }
        StringBuilder buffer = new StringBuilder();
        while (b != -1) {
            buffer.append((char) b);
            if (b == 10) {
                break;
            }
            b = this.inputStream.read();
        }
        return buffer.toString();
    }

    private String readLine() throws IOException {
        return trimCrlf(readRawLine());
    }

    private static String trimCrlf(String input) {
        if (input.endsWith("\r\n")) {
            return input.substring(0, input.length() - 2);
        }
        if (input.endsWith("\n")) {
            return input.substring(0, input.length() - 1);
        }
        return input;
    }

    private static InputStream trimCrlf(byte[] bytes) {
        int length = bytes.length;
        if (length > 0 && bytes[length - 1] == 10) {
            length--;
        }
        if (length > 0 && bytes[length - 1] == 13) {
            length--;
        }
        return new ByteArrayInputStream(bytes, 0, length);
    }

    private void checkForFinalBoundary(String boundaryLine) throws IOException {
        if (boundaryLine.equals(this.boundary + "--")) {
            this.hasNext = false;
            this.inputStream.close();
        }
    }

    private static class FakeResponseHttpTransport extends HttpTransport {
        private List<String> headerNames;
        private List<String> headerValues;
        private InputStream partContent;
        private int statusCode;

        FakeResponseHttpTransport(int statusCode2, InputStream partContent2, List<String> headerNames2, List<String> headerValues2) {
            this.statusCode = statusCode2;
            this.partContent = partContent2;
            this.headerNames = headerNames2;
            this.headerValues = headerValues2;
        }

        /* access modifiers changed from: protected */
        public LowLevelHttpRequest buildRequest(String method, String url) {
            return new FakeLowLevelHttpRequest(this.partContent, this.statusCode, this.headerNames, this.headerValues);
        }
    }

    private static class FakeLowLevelHttpRequest extends LowLevelHttpRequest {
        private List<String> headerNames;
        private List<String> headerValues;
        private InputStream partContent;
        private int statusCode;

        FakeLowLevelHttpRequest(InputStream partContent2, int statusCode2, List<String> headerNames2, List<String> headerValues2) {
            this.partContent = partContent2;
            this.statusCode = statusCode2;
            this.headerNames = headerNames2;
            this.headerValues = headerValues2;
        }

        public void addHeader(String name, String value) {
        }

        public LowLevelHttpResponse execute() {
            return new FakeLowLevelHttpResponse(this.partContent, this.statusCode, this.headerNames, this.headerValues);
        }
    }

    private static class FakeLowLevelHttpResponse extends LowLevelHttpResponse {
        private List<String> headerNames = new ArrayList();
        private List<String> headerValues = new ArrayList();
        private InputStream partContent;
        private int statusCode;

        FakeLowLevelHttpResponse(InputStream partContent2, int statusCode2, List<String> headerNames2, List<String> headerValues2) {
            this.partContent = partContent2;
            this.statusCode = statusCode2;
            this.headerNames = headerNames2;
            this.headerValues = headerValues2;
        }

        public InputStream getContent() {
            return this.partContent;
        }

        public int getStatusCode() {
            return this.statusCode;
        }

        public String getContentEncoding() {
            return null;
        }

        public long getContentLength() {
            return 0;
        }

        public String getContentType() {
            return null;
        }

        public String getStatusLine() {
            return null;
        }

        public String getReasonPhrase() {
            return null;
        }

        public int getHeaderCount() {
            return this.headerNames.size();
        }

        public String getHeaderName(int index) {
            return this.headerNames.get(index);
        }

        public String getHeaderValue(int index) {
            return this.headerValues.get(index);
        }
    }
}
