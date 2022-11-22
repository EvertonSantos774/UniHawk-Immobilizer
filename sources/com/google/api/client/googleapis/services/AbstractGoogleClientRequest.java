package com.google.api.client.googleapis.services;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.MethodOverride;
import com.google.api.client.googleapis.batch.BatchCallback;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GZipEncoding;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpResponseInterceptor;
import com.google.api.client.http.UriTemplate;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.common.base.StandardSystemProperty;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractGoogleClientRequest<T> extends GenericData {
    private static final String API_VERSION_HEADER = "X-Goog-Api-Client";
    public static final String USER_AGENT_SUFFIX = "Google-API-Java-Client";
    private final AbstractGoogleClient abstractGoogleClient;
    private boolean disableGZipContent;
    private MediaHttpDownloader downloader;
    private final HttpContent httpContent;
    private HttpHeaders lastResponseHeaders;
    private int lastStatusCode = -1;
    private String lastStatusMessage;
    private HttpHeaders requestHeaders = new HttpHeaders();
    private final String requestMethod;
    private Class<T> responseClass;
    private boolean returnRawInputStream;
    private MediaHttpUploader uploader;
    private final String uriTemplate;

    protected AbstractGoogleClientRequest(AbstractGoogleClient abstractGoogleClient2, String requestMethod2, String uriTemplate2, HttpContent httpContent2, Class<T> responseClass2) {
        this.responseClass = (Class) Preconditions.checkNotNull(responseClass2);
        this.abstractGoogleClient = (AbstractGoogleClient) Preconditions.checkNotNull(abstractGoogleClient2);
        this.requestMethod = (String) Preconditions.checkNotNull(requestMethod2);
        this.uriTemplate = (String) Preconditions.checkNotNull(uriTemplate2);
        this.httpContent = httpContent2;
        String applicationName = abstractGoogleClient2.getApplicationName();
        if (applicationName != null) {
            this.requestHeaders.setUserAgent(applicationName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + USER_AGENT_SUFFIX);
        } else {
            this.requestHeaders.setUserAgent(USER_AGENT_SUFFIX);
        }
        this.requestHeaders.set(API_VERSION_HEADER, (Object) ApiClientVersion.getDefault().build(abstractGoogleClient2.getClass().getSimpleName()));
    }

    static class ApiClientVersion {
        private static final ApiClientVersion DEFAULT_VERSION = new ApiClientVersion();
        private final String headerTemplate;

        ApiClientVersion() {
            this(getJavaVersion(), StandardSystemProperty.OS_NAME.value(), StandardSystemProperty.OS_VERSION.value(), GoogleUtils.VERSION);
        }

        ApiClientVersion(String javaVersion, String osName, String osVersion, String clientVersion) {
            StringBuilder sb = new StringBuilder("java/");
            sb.append(formatSemver(javaVersion));
            sb.append(" http-google-%s/");
            sb.append(formatSemver(clientVersion));
            if (!(osName == null || osVersion == null)) {
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                sb.append(formatName(osName));
                sb.append("/");
                sb.append(formatSemver(osVersion));
            }
            this.headerTemplate = sb.toString();
        }

        /* access modifiers changed from: package-private */
        public String build(String clientName) {
            return String.format(this.headerTemplate, new Object[]{formatName(clientName)});
        }

        /* access modifiers changed from: private */
        public static ApiClientVersion getDefault() {
            return DEFAULT_VERSION;
        }

        private static String getJavaVersion() {
            String version = System.getProperty("java.version");
            if (version == null) {
                return null;
            }
            String formatted = formatSemver(version, (String) null);
            if (formatted != null) {
                return formatted;
            }
            Matcher m = Pattern.compile("^(\\d+)[^\\d]?").matcher(version);
            if (m.find()) {
                return m.group(1) + ".0.0";
            }
            return null;
        }

        private static String formatName(String name) {
            return name.toLowerCase().replaceAll("[^\\w\\d\\-]", "-");
        }

        private static String formatSemver(String version) {
            return formatSemver(version, version);
        }

        private static String formatSemver(String version, String defaultValue) {
            if (version == null) {
                return null;
            }
            Matcher m = Pattern.compile("(\\d+\\.\\d+\\.\\d+).*").matcher(version);
            if (m.find()) {
                return m.group(1);
            }
            return defaultValue;
        }
    }

    public final boolean getDisableGZipContent() {
        return this.disableGZipContent;
    }

    public final boolean getReturnRawInputSteam() {
        return this.returnRawInputStream;
    }

    public AbstractGoogleClientRequest<T> setDisableGZipContent(boolean disableGZipContent2) {
        this.disableGZipContent = disableGZipContent2;
        return this;
    }

    public AbstractGoogleClientRequest<T> setReturnRawInputStream(boolean returnRawInputStream2) {
        this.returnRawInputStream = returnRawInputStream2;
        return this;
    }

    public final String getRequestMethod() {
        return this.requestMethod;
    }

    public final String getUriTemplate() {
        return this.uriTemplate;
    }

    public final HttpContent getHttpContent() {
        return this.httpContent;
    }

    public AbstractGoogleClient getAbstractGoogleClient() {
        return this.abstractGoogleClient;
    }

    public final HttpHeaders getRequestHeaders() {
        return this.requestHeaders;
    }

    public AbstractGoogleClientRequest<T> setRequestHeaders(HttpHeaders headers) {
        this.requestHeaders = headers;
        return this;
    }

    public final HttpHeaders getLastResponseHeaders() {
        return this.lastResponseHeaders;
    }

    public final int getLastStatusCode() {
        return this.lastStatusCode;
    }

    public final String getLastStatusMessage() {
        return this.lastStatusMessage;
    }

    public final Class<T> getResponseClass() {
        return this.responseClass;
    }

    public final MediaHttpUploader getMediaHttpUploader() {
        return this.uploader;
    }

    /* access modifiers changed from: protected */
    public final void initializeMediaUpload(AbstractInputStreamContent mediaContent) {
        HttpRequestFactory requestFactory = this.abstractGoogleClient.getRequestFactory();
        this.uploader = new MediaHttpUploader(mediaContent, requestFactory.getTransport(), requestFactory.getInitializer());
        this.uploader.setInitiationRequestMethod(this.requestMethod);
        if (this.httpContent != null) {
            this.uploader.setMetadata(this.httpContent);
        }
    }

    public final MediaHttpDownloader getMediaHttpDownloader() {
        return this.downloader;
    }

    /* access modifiers changed from: protected */
    public final void initializeMediaDownload() {
        HttpRequestFactory requestFactory = this.abstractGoogleClient.getRequestFactory();
        this.downloader = new MediaHttpDownloader(requestFactory.getTransport(), requestFactory.getInitializer());
    }

    public GenericUrl buildHttpRequestUrl() {
        return new GenericUrl(UriTemplate.expand(this.abstractGoogleClient.getBaseUrl(), this.uriTemplate, this, true));
    }

    public HttpRequest buildHttpRequest() throws IOException {
        return buildHttpRequest(false);
    }

    /* access modifiers changed from: protected */
    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return buildHttpRequest(true);
    }

    private HttpRequest buildHttpRequest(boolean usingHead) throws IOException {
        boolean z;
        boolean z2 = false;
        if (this.uploader == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (!usingHead || this.requestMethod.equals("GET")) {
            z2 = true;
        }
        Preconditions.checkArgument(z2);
        final HttpRequest httpRequest = getAbstractGoogleClient().getRequestFactory().buildRequest(usingHead ? "HEAD" : this.requestMethod, buildHttpRequestUrl(), this.httpContent);
        new MethodOverride().intercept(httpRequest);
        httpRequest.setParser(getAbstractGoogleClient().getObjectParser());
        if (this.httpContent == null && (this.requestMethod.equals("POST") || this.requestMethod.equals("PUT") || this.requestMethod.equals(HttpMethods.PATCH))) {
            httpRequest.setContent(new EmptyContent());
        }
        httpRequest.getHeaders().putAll(this.requestHeaders);
        if (!this.disableGZipContent) {
            httpRequest.setEncoding(new GZipEncoding());
        }
        httpRequest.setResponseReturnRawInputStream(this.returnRawInputStream);
        final HttpResponseInterceptor responseInterceptor = httpRequest.getResponseInterceptor();
        httpRequest.setResponseInterceptor(new HttpResponseInterceptor() {
            public void interceptResponse(HttpResponse response) throws IOException {
                if (responseInterceptor != null) {
                    responseInterceptor.interceptResponse(response);
                }
                if (!response.isSuccessStatusCode() && httpRequest.getThrowExceptionOnExecuteError()) {
                    throw AbstractGoogleClientRequest.this.newExceptionOnError(response);
                }
            }
        });
        return httpRequest;
    }

    public HttpResponse executeUnparsed() throws IOException {
        return executeUnparsed(false);
    }

    /* access modifiers changed from: protected */
    public HttpResponse executeMedia() throws IOException {
        set("alt", (Object) "media");
        return executeUnparsed();
    }

    /* access modifiers changed from: protected */
    public HttpResponse executeUsingHead() throws IOException {
        Preconditions.checkArgument(this.uploader == null);
        HttpResponse response = executeUnparsed(true);
        response.ignore();
        return response;
    }

    private HttpResponse executeUnparsed(boolean usingHead) throws IOException {
        HttpResponse response;
        if (this.uploader == null) {
            response = buildHttpRequest(usingHead).execute();
        } else {
            GenericUrl httpRequestUrl = buildHttpRequestUrl();
            boolean throwExceptionOnExecuteError = getAbstractGoogleClient().getRequestFactory().buildRequest(this.requestMethod, httpRequestUrl, this.httpContent).getThrowExceptionOnExecuteError();
            response = this.uploader.setInitiationHeaders(this.requestHeaders).setDisableGZipContent(this.disableGZipContent).upload(httpRequestUrl);
            response.getRequest().setParser(getAbstractGoogleClient().getObjectParser());
            if (throwExceptionOnExecuteError && !response.isSuccessStatusCode()) {
                throw newExceptionOnError(response);
            }
        }
        this.lastResponseHeaders = response.getHeaders();
        this.lastStatusCode = response.getStatusCode();
        this.lastStatusMessage = response.getStatusMessage();
        return response;
    }

    /* access modifiers changed from: protected */
    public IOException newExceptionOnError(HttpResponse response) {
        return new HttpResponseException(response);
    }

    public T execute() throws IOException {
        return executeUnparsed().parseAs(this.responseClass);
    }

    public InputStream executeAsInputStream() throws IOException {
        return executeUnparsed().getContent();
    }

    /* access modifiers changed from: protected */
    public InputStream executeMediaAsInputStream() throws IOException {
        return executeMedia().getContent();
    }

    public void executeAndDownloadTo(OutputStream outputStream) throws IOException {
        executeUnparsed().download(outputStream);
    }

    /* access modifiers changed from: protected */
    public void executeMediaAndDownloadTo(OutputStream outputStream) throws IOException {
        if (this.downloader == null) {
            executeMedia().download(outputStream);
        } else {
            this.downloader.download(buildHttpRequestUrl(), this.requestHeaders, outputStream);
        }
    }

    public final <E> void queue(BatchRequest batchRequest, Class<E> errorClass, BatchCallback<T, E> callback) throws IOException {
        Preconditions.checkArgument(this.uploader == null, "Batching media requests is not supported");
        batchRequest.queue(buildHttpRequest(), getResponseClass(), errorClass, callback);
    }

    public AbstractGoogleClientRequest<T> set(String fieldName, Object value) {
        return (AbstractGoogleClientRequest) super.set(fieldName, value);
    }

    /* access modifiers changed from: protected */
    public final void checkRequiredParameter(Object value, String name) {
        boolean z;
        if (this.abstractGoogleClient.getSuppressRequiredParameterChecks() || value != null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Required parameter %s must be specified", name);
    }
}
