package com.google.api.client.http;

import com.google.api.client.util.Beta;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public final class HttpRequest {
    public static final int DEFAULT_NUMBER_OF_RETRIES = 10;
    public static final String USER_AGENT_SUFFIX = ("Google-HTTP-Java-Client/" + VERSION + " (gzip)");
    public static final String VERSION = getVersion();
    @Beta
    @Deprecated
    private BackOffPolicy backOffPolicy;
    private int connectTimeout = 20000;
    private HttpContent content;
    private int contentLoggingLimit = 16384;
    private boolean curlLoggingEnabled = true;
    private HttpEncoding encoding;
    private HttpExecuteInterceptor executeInterceptor;
    private boolean followRedirects = true;
    private HttpHeaders headers = new HttpHeaders();
    @Beta
    private HttpIOExceptionHandler ioExceptionHandler;
    private boolean loggingEnabled = true;
    private int numRetries = 10;
    private ObjectParser objectParser;
    private int readTimeout = 20000;
    private String requestMethod;
    private HttpHeaders responseHeaders = new HttpHeaders();
    private HttpResponseInterceptor responseInterceptor;
    private boolean responseReturnRawInputStream = false;
    @Beta
    @Deprecated
    private boolean retryOnExecuteIOException = false;
    private Sleeper sleeper = Sleeper.DEFAULT;
    private boolean suppressUserAgentSuffix;
    private boolean throwExceptionOnExecuteError = true;
    private final Tracer tracer = OpenCensusUtils.getTracer();
    private final HttpTransport transport;
    private HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler;
    private GenericUrl url;
    private boolean useRawRedirectUrls = false;
    private int writeTimeout = 0;

    HttpRequest(HttpTransport transport2, String requestMethod2) {
        this.transport = transport2;
        setRequestMethod(requestMethod2);
    }

    public HttpTransport getTransport() {
        return this.transport;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public HttpRequest setRequestMethod(String requestMethod2) {
        Preconditions.checkArgument(requestMethod2 == null || HttpMediaType.matchesToken(requestMethod2));
        this.requestMethod = requestMethod2;
        return this;
    }

    public GenericUrl getUrl() {
        return this.url;
    }

    public HttpRequest setUrl(GenericUrl url2) {
        this.url = (GenericUrl) Preconditions.checkNotNull(url2);
        return this;
    }

    public HttpContent getContent() {
        return this.content;
    }

    public HttpRequest setContent(HttpContent content2) {
        this.content = content2;
        return this;
    }

    public HttpEncoding getEncoding() {
        return this.encoding;
    }

    public HttpRequest setEncoding(HttpEncoding encoding2) {
        this.encoding = encoding2;
        return this;
    }

    @Beta
    @Deprecated
    public BackOffPolicy getBackOffPolicy() {
        return this.backOffPolicy;
    }

    @Beta
    @Deprecated
    public HttpRequest setBackOffPolicy(BackOffPolicy backOffPolicy2) {
        this.backOffPolicy = backOffPolicy2;
        return this;
    }

    public int getContentLoggingLimit() {
        return this.contentLoggingLimit;
    }

    public HttpRequest setContentLoggingLimit(int contentLoggingLimit2) {
        Preconditions.checkArgument(contentLoggingLimit2 >= 0, "The content logging limit must be non-negative.");
        this.contentLoggingLimit = contentLoggingLimit2;
        return this;
    }

    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }

    public HttpRequest setLoggingEnabled(boolean loggingEnabled2) {
        this.loggingEnabled = loggingEnabled2;
        return this;
    }

    public boolean isCurlLoggingEnabled() {
        return this.curlLoggingEnabled;
    }

    public HttpRequest setCurlLoggingEnabled(boolean curlLoggingEnabled2) {
        this.curlLoggingEnabled = curlLoggingEnabled2;
        return this;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public HttpRequest setConnectTimeout(int connectTimeout2) {
        Preconditions.checkArgument(connectTimeout2 >= 0);
        this.connectTimeout = connectTimeout2;
        return this;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public HttpRequest setReadTimeout(int readTimeout2) {
        Preconditions.checkArgument(readTimeout2 >= 0);
        this.readTimeout = readTimeout2;
        return this;
    }

    public int getWriteTimeout() {
        return this.writeTimeout;
    }

    public HttpRequest setWriteTimeout(int writeTimeout2) {
        Preconditions.checkArgument(writeTimeout2 >= 0);
        this.writeTimeout = writeTimeout2;
        return this;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public HttpRequest setHeaders(HttpHeaders headers2) {
        this.headers = (HttpHeaders) Preconditions.checkNotNull(headers2);
        return this;
    }

    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }

    public HttpRequest setResponseHeaders(HttpHeaders responseHeaders2) {
        this.responseHeaders = (HttpHeaders) Preconditions.checkNotNull(responseHeaders2);
        return this;
    }

    public HttpExecuteInterceptor getInterceptor() {
        return this.executeInterceptor;
    }

    public HttpRequest setInterceptor(HttpExecuteInterceptor interceptor) {
        this.executeInterceptor = interceptor;
        return this;
    }

    public HttpUnsuccessfulResponseHandler getUnsuccessfulResponseHandler() {
        return this.unsuccessfulResponseHandler;
    }

    public HttpRequest setUnsuccessfulResponseHandler(HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler2) {
        this.unsuccessfulResponseHandler = unsuccessfulResponseHandler2;
        return this;
    }

    @Beta
    public HttpIOExceptionHandler getIOExceptionHandler() {
        return this.ioExceptionHandler;
    }

    @Beta
    public HttpRequest setIOExceptionHandler(HttpIOExceptionHandler ioExceptionHandler2) {
        this.ioExceptionHandler = ioExceptionHandler2;
        return this;
    }

    public HttpResponseInterceptor getResponseInterceptor() {
        return this.responseInterceptor;
    }

    public HttpRequest setResponseInterceptor(HttpResponseInterceptor responseInterceptor2) {
        this.responseInterceptor = responseInterceptor2;
        return this;
    }

    public int getNumberOfRetries() {
        return this.numRetries;
    }

    public HttpRequest setNumberOfRetries(int numRetries2) {
        Preconditions.checkArgument(numRetries2 >= 0);
        this.numRetries = numRetries2;
        return this;
    }

    public HttpRequest setParser(ObjectParser parser) {
        this.objectParser = parser;
        return this;
    }

    public final ObjectParser getParser() {
        return this.objectParser;
    }

    public boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public HttpRequest setFollowRedirects(boolean followRedirects2) {
        this.followRedirects = followRedirects2;
        return this;
    }

    public boolean getUseRawRedirectUrls() {
        return this.useRawRedirectUrls;
    }

    public HttpRequest setUseRawRedirectUrls(boolean useRawRedirectUrls2) {
        this.useRawRedirectUrls = useRawRedirectUrls2;
        return this;
    }

    public boolean getThrowExceptionOnExecuteError() {
        return this.throwExceptionOnExecuteError;
    }

    public HttpRequest setThrowExceptionOnExecuteError(boolean throwExceptionOnExecuteError2) {
        this.throwExceptionOnExecuteError = throwExceptionOnExecuteError2;
        return this;
    }

    @Beta
    @Deprecated
    public boolean getRetryOnExecuteIOException() {
        return this.retryOnExecuteIOException;
    }

    @Beta
    @Deprecated
    public HttpRequest setRetryOnExecuteIOException(boolean retryOnExecuteIOException2) {
        this.retryOnExecuteIOException = retryOnExecuteIOException2;
        return this;
    }

    public boolean getSuppressUserAgentSuffix() {
        return this.suppressUserAgentSuffix;
    }

    public HttpRequest setSuppressUserAgentSuffix(boolean suppressUserAgentSuffix2) {
        this.suppressUserAgentSuffix = suppressUserAgentSuffix2;
        return this;
    }

    public boolean getResponseReturnRawInputStream() {
        return this.responseReturnRawInputStream;
    }

    public HttpRequest setResponseReturnRawInputStream(boolean responseReturnRawInputStream2) {
        this.responseReturnRawInputStream = responseReturnRawInputStream2;
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v0, resolved type: com.google.api.client.http.HttpContent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v1, resolved type: com.google.api.client.http.HttpContent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v2, resolved type: com.google.api.client.http.HttpContent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v112, resolved type: com.google.api.client.http.HttpEncodingStreamingContent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v3, resolved type: com.google.api.client.http.HttpContent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v4, resolved type: com.google.api.client.http.HttpContent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v132, resolved type: com.google.api.client.util.LoggingStreamingContent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v5, resolved type: com.google.api.client.http.HttpContent} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x040c  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x041b  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x04b1 A[Catch:{ all -> 0x04bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x04c4  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x0514  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x051a  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0526  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0530  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x03be A[SYNTHETIC, Splitter:B:86:0x03be] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.api.client.http.HttpResponse execute() throws java.io.IOException {
        /*
            r40 = this;
            r31 = 0
            r0 = r40
            int r0 = r0.numRetries
            r37 = r0
            if (r37 < 0) goto L_0x041c
            r37 = 1
        L_0x000c:
            com.google.api.client.util.Preconditions.checkArgument(r37)
            r0 = r40
            int r0 = r0.numRetries
            r30 = r0
            r0 = r40
            com.google.api.client.http.BackOffPolicy r0 = r0.backOffPolicy
            r37 = r0
            if (r37 == 0) goto L_0x0026
            r0 = r40
            com.google.api.client.http.BackOffPolicy r0 = r0.backOffPolicy
            r37 = r0
            r37.reset()
        L_0x0026:
            r26 = 0
            r0 = r40
            java.lang.String r0 = r0.requestMethod
            r37 = r0
            com.google.api.client.util.Preconditions.checkNotNull(r37)
            r0 = r40
            com.google.api.client.http.GenericUrl r0 = r0.url
            r37 = r0
            com.google.api.client.util.Preconditions.checkNotNull(r37)
            r0 = r40
            io.opencensus.trace.Tracer r0 = r0.tracer
            r37 = r0
            java.lang.String r38 = com.google.api.client.http.OpenCensusUtils.SPAN_NAME_HTTP_REQUEST_EXECUTE
            io.opencensus.trace.SpanBuilder r37 = r37.spanBuilder(r38)
            boolean r38 = com.google.api.client.http.OpenCensusUtils.isRecordEvent()
            io.opencensus.trace.SpanBuilder r37 = r37.setRecordEvents(r38)
            io.opencensus.trace.Span r32 = r37.startSpan()
        L_0x0052:
            java.lang.StringBuilder r37 = new java.lang.StringBuilder
            r37.<init>()
            java.lang.String r38 = "retry #"
            java.lang.StringBuilder r37 = r37.append(r38)
            r0 = r40
            int r0 = r0.numRetries
            r38 = r0
            int r38 = r38 - r30
            java.lang.StringBuilder r37 = r37.append(r38)
            java.lang.String r37 = r37.toString()
            r0 = r32
            r1 = r37
            r0.addAnnotation(r1)
            if (r26 == 0) goto L_0x0079
            r26.ignore()
        L_0x0079:
            r26 = 0
            r16 = 0
            r0 = r40
            com.google.api.client.http.HttpExecuteInterceptor r0 = r0.executeInterceptor
            r37 = r0
            if (r37 == 0) goto L_0x0092
            r0 = r40
            com.google.api.client.http.HttpExecuteInterceptor r0 = r0.executeInterceptor
            r37 = r0
            r0 = r37
            r1 = r40
            r0.intercept(r1)
        L_0x0092:
            r0 = r40
            com.google.api.client.http.GenericUrl r0 = r0.url
            r37 = r0
            java.lang.String r35 = r37.build()
            java.lang.String r37 = "http.method"
            r0 = r40
            java.lang.String r0 = r0.requestMethod
            r38 = r0
            r0 = r32
            r1 = r37
            r2 = r38
            addSpanAttribute(r0, r1, r2)
            java.lang.String r37 = "http.host"
            r0 = r40
            com.google.api.client.http.GenericUrl r0 = r0.url
            r38 = r0
            java.lang.String r38 = r38.getHost()
            r0 = r32
            r1 = r37
            r2 = r38
            addSpanAttribute(r0, r1, r2)
            java.lang.String r37 = "http.path"
            r0 = r40
            com.google.api.client.http.GenericUrl r0 = r0.url
            r38 = r0
            java.lang.String r38 = r38.getRawPath()
            r0 = r32
            r1 = r37
            r2 = r38
            addSpanAttribute(r0, r1, r2)
            java.lang.String r37 = "http.url"
            r0 = r32
            r1 = r37
            r2 = r35
            addSpanAttribute(r0, r1, r2)
            r0 = r40
            com.google.api.client.http.HttpTransport r0 = r0.transport
            r37 = r0
            r0 = r40
            java.lang.String r0 = r0.requestMethod
            r38 = r0
            r0 = r37
            r1 = r38
            r2 = r35
            com.google.api.client.http.LowLevelHttpRequest r22 = r0.buildRequest(r1, r2)
            java.util.logging.Logger r20 = com.google.api.client.http.HttpTransport.LOGGER
            r0 = r40
            boolean r0 = r0.loggingEnabled
            r37 = r0
            if (r37 == 0) goto L_0x0420
            java.util.logging.Level r37 = java.util.logging.Level.CONFIG
            r0 = r20
            r1 = r37
            boolean r37 = r0.isLoggable(r1)
            if (r37 == 0) goto L_0x0420
            r19 = 1
        L_0x0110:
            r18 = 0
            r13 = 0
            if (r19 == 0) goto L_0x017a
            java.lang.StringBuilder r18 = new java.lang.StringBuilder
            r18.<init>()
            java.lang.String r37 = "-------------- REQUEST  --------------"
            r0 = r18
            r1 = r37
            java.lang.StringBuilder r37 = r0.append(r1)
            java.lang.String r38 = com.google.api.client.util.StringUtils.LINE_SEPARATOR
            r37.append(r38)
            r0 = r40
            java.lang.String r0 = r0.requestMethod
            r37 = r0
            r0 = r18
            r1 = r37
            java.lang.StringBuilder r37 = r0.append(r1)
            r38 = 32
            java.lang.StringBuilder r37 = r37.append(r38)
            r0 = r37
            r1 = r35
            java.lang.StringBuilder r37 = r0.append(r1)
            java.lang.String r38 = com.google.api.client.util.StringUtils.LINE_SEPARATOR
            r37.append(r38)
            r0 = r40
            boolean r0 = r0.curlLoggingEnabled
            r37 = r0
            if (r37 == 0) goto L_0x017a
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r37 = "curl -v --compressed"
            r0 = r37
            r13.<init>(r0)
            r0 = r40
            java.lang.String r0 = r0.requestMethod
            r37 = r0
            java.lang.String r38 = "GET"
            boolean r37 = r37.equals(r38)
            if (r37 != 0) goto L_0x017a
            java.lang.String r37 = " -X "
            r0 = r37
            java.lang.StringBuilder r37 = r13.append(r0)
            r0 = r40
            java.lang.String r0 = r0.requestMethod
            r38 = r0
            r37.append(r38)
        L_0x017a:
            r0 = r40
            com.google.api.client.http.HttpHeaders r0 = r0.headers
            r37 = r0
            java.lang.String r25 = r37.getUserAgent()
            r0 = r40
            boolean r0 = r0.suppressUserAgentSuffix
            r37 = r0
            if (r37 != 0) goto L_0x01a6
            if (r25 != 0) goto L_0x0424
            r0 = r40
            com.google.api.client.http.HttpHeaders r0 = r0.headers
            r37 = r0
            java.lang.String r38 = USER_AGENT_SUFFIX
            r37.setUserAgent(r38)
            java.lang.String r37 = "http.user_agent"
            java.lang.String r38 = USER_AGENT_SUFFIX
            r0 = r32
            r1 = r37
            r2 = r38
            addSpanAttribute(r0, r1, r2)
        L_0x01a6:
            r0 = r40
            com.google.api.client.http.HttpHeaders r0 = r0.headers
            r37 = r0
            r0 = r32
            r1 = r37
            com.google.api.client.http.OpenCensusUtils.propagateTracingContext(r0, r1)
            r0 = r40
            com.google.api.client.http.HttpHeaders r0 = r0.headers
            r37 = r0
            r0 = r37
            r1 = r18
            r2 = r20
            r3 = r22
            com.google.api.client.http.HttpHeaders.serializeHeaders(r0, r1, r13, r2, r3)
            r0 = r40
            boolean r0 = r0.suppressUserAgentSuffix
            r37 = r0
            if (r37 != 0) goto L_0x01d9
            r0 = r40
            com.google.api.client.http.HttpHeaders r0 = r0.headers
            r37 = r0
            r0 = r37
            r1 = r25
            r0.setUserAgent(r1)
        L_0x01d9:
            r0 = r40
            com.google.api.client.http.HttpContent r0 = r0.content
            r33 = r0
            if (r33 == 0) goto L_0x01ed
            r0 = r40
            com.google.api.client.http.HttpContent r0 = r0.content
            r37 = r0
            boolean r37 = r37.retrySupported()
            if (r37 == 0) goto L_0x045b
        L_0x01ed:
            r9 = 1
        L_0x01ee:
            if (r33 == 0) goto L_0x0305
            r10 = -1
            r0 = r40
            com.google.api.client.http.HttpContent r0 = r0.content
            r37 = r0
            java.lang.String r12 = r37.getType()
            if (r19 == 0) goto L_0x0219
            com.google.api.client.util.LoggingStreamingContent r34 = new com.google.api.client.util.LoggingStreamingContent
            java.util.logging.Logger r37 = com.google.api.client.http.HttpTransport.LOGGER
            java.util.logging.Level r38 = java.util.logging.Level.CONFIG
            r0 = r40
            int r0 = r0.contentLoggingLimit
            r39 = r0
            r0 = r34
            r1 = r33
            r2 = r37
            r3 = r38
            r4 = r39
            r0.<init>(r1, r2, r3, r4)
            r33 = r34
        L_0x0219:
            r0 = r40
            com.google.api.client.http.HttpEncoding r0 = r0.encoding
            r37 = r0
            if (r37 != 0) goto L_0x045e
            r8 = 0
            r0 = r40
            com.google.api.client.http.HttpContent r0 = r0.content
            r37 = r0
            long r10 = r37.getLength()
        L_0x022c:
            if (r19 == 0) goto L_0x02e6
            if (r12 == 0) goto L_0x0276
            java.lang.StringBuilder r37 = new java.lang.StringBuilder
            r37.<init>()
            java.lang.String r38 = "Content-Type: "
            java.lang.StringBuilder r37 = r37.append(r38)
            r0 = r37
            java.lang.StringBuilder r37 = r0.append(r12)
            java.lang.String r17 = r37.toString()
            r0 = r18
            r1 = r17
            java.lang.StringBuilder r37 = r0.append(r1)
            java.lang.String r38 = com.google.api.client.util.StringUtils.LINE_SEPARATOR
            r37.append(r38)
            if (r13 == 0) goto L_0x0276
            java.lang.StringBuilder r37 = new java.lang.StringBuilder
            r37.<init>()
            java.lang.String r38 = " -H '"
            java.lang.StringBuilder r37 = r37.append(r38)
            r0 = r37
            r1 = r17
            java.lang.StringBuilder r37 = r0.append(r1)
            java.lang.String r38 = "'"
            java.lang.StringBuilder r37 = r37.append(r38)
            java.lang.String r37 = r37.toString()
            r0 = r37
            r13.append(r0)
        L_0x0276:
            if (r8 == 0) goto L_0x02be
            java.lang.StringBuilder r37 = new java.lang.StringBuilder
            r37.<init>()
            java.lang.String r38 = "Content-Encoding: "
            java.lang.StringBuilder r37 = r37.append(r38)
            r0 = r37
            java.lang.StringBuilder r37 = r0.append(r8)
            java.lang.String r17 = r37.toString()
            r0 = r18
            r1 = r17
            java.lang.StringBuilder r37 = r0.append(r1)
            java.lang.String r38 = com.google.api.client.util.StringUtils.LINE_SEPARATOR
            r37.append(r38)
            if (r13 == 0) goto L_0x02be
            java.lang.StringBuilder r37 = new java.lang.StringBuilder
            r37.<init>()
            java.lang.String r38 = " -H '"
            java.lang.StringBuilder r37 = r37.append(r38)
            r0 = r37
            r1 = r17
            java.lang.StringBuilder r37 = r0.append(r1)
            java.lang.String r38 = "'"
            java.lang.StringBuilder r37 = r37.append(r38)
            java.lang.String r37 = r37.toString()
            r0 = r37
            r13.append(r0)
        L_0x02be:
            r38 = 0
            int r37 = (r10 > r38 ? 1 : (r10 == r38 ? 0 : -1))
            if (r37 < 0) goto L_0x02e6
            java.lang.StringBuilder r37 = new java.lang.StringBuilder
            r37.<init>()
            java.lang.String r38 = "Content-Length: "
            java.lang.StringBuilder r37 = r37.append(r38)
            r0 = r37
            java.lang.StringBuilder r37 = r0.append(r10)
            java.lang.String r17 = r37.toString()
            r0 = r18
            r1 = r17
            java.lang.StringBuilder r37 = r0.append(r1)
            java.lang.String r38 = com.google.api.client.util.StringUtils.LINE_SEPARATOR
            r37.append(r38)
        L_0x02e6:
            if (r13 == 0) goto L_0x02ef
            java.lang.String r37 = " -d '@-'"
            r0 = r37
            r13.append(r0)
        L_0x02ef:
            r0 = r22
            r0.setContentType(r12)
            r0 = r22
            r0.setContentEncoding(r8)
            r0 = r22
            r0.setContentLength(r10)
            r0 = r22
            r1 = r33
            r0.setStreamingContent(r1)
        L_0x0305:
            if (r19 == 0) goto L_0x0349
            java.lang.String r37 = r18.toString()
            r0 = r20
            r1 = r37
            r0.config(r1)
            if (r13 == 0) goto L_0x0349
            java.lang.String r37 = " -- '"
            r0 = r37
            r13.append(r0)
            java.lang.String r37 = "'"
            java.lang.String r38 = "'\"'\"'"
            r0 = r35
            r1 = r37
            r2 = r38
            java.lang.String r37 = r0.replaceAll(r1, r2)
            r0 = r37
            r13.append(r0)
            java.lang.String r37 = "'"
            r0 = r37
            r13.append(r0)
            if (r33 == 0) goto L_0x033e
            java.lang.String r37 = " << $$$"
            r0 = r37
            r13.append(r0)
        L_0x033e:
            java.lang.String r37 = r13.toString()
            r0 = r20
            r1 = r37
            r0.config(r1)
        L_0x0349:
            if (r9 == 0) goto L_0x047d
            if (r30 <= 0) goto L_0x047d
            r31 = 1
        L_0x034f:
            r0 = r40
            int r0 = r0.connectTimeout
            r37 = r0
            r0 = r40
            int r0 = r0.readTimeout
            r38 = r0
            r0 = r22
            r1 = r37
            r2 = r38
            r0.setTimeout(r1, r2)
            r0 = r40
            int r0 = r0.writeTimeout
            r37 = r0
            r0 = r22
            r1 = r37
            r0.setWriteTimeout(r1)
            r0 = r40
            io.opencensus.trace.Tracer r0 = r0.tracer
            r37 = r0
            r0 = r37
            r1 = r32
            io.opencensus.common.Scope r36 = r0.withSpan(r1)
            long r38 = r22.getContentLength()
            r0 = r32
            r1 = r38
            com.google.api.client.http.OpenCensusUtils.recordSentMessageEvent(r0, r1)
            com.google.api.client.http.LowLevelHttpResponse r23 = r22.execute()     // Catch:{ IOException -> 0x048e }
            if (r23 == 0) goto L_0x039b
            long r38 = r23.getContentLength()     // Catch:{ IOException -> 0x048e }
            r0 = r32
            r1 = r38
            com.google.api.client.http.OpenCensusUtils.recordReceivedMessageEvent(r0, r1)     // Catch:{ IOException -> 0x048e }
        L_0x039b:
            r28 = 0
            com.google.api.client.http.HttpResponse r27 = new com.google.api.client.http.HttpResponse     // Catch:{ all -> 0x0481 }
            r0 = r27
            r1 = r40
            r2 = r23
            r0.<init>(r1, r2)     // Catch:{ all -> 0x0481 }
            r28 = 1
            if (r28 != 0) goto L_0x03b5
            java.io.InputStream r21 = r23.getContent()     // Catch:{ IOException -> 0x056a, all -> 0x0565 }
            if (r21 == 0) goto L_0x03b5
            r21.close()     // Catch:{ IOException -> 0x056a, all -> 0x0565 }
        L_0x03b5:
            r36.close()
            r26 = r27
        L_0x03ba:
            r29 = 0
            if (r26 == 0) goto L_0x0512
            boolean r37 = r26.isSuccessStatusCode()     // Catch:{ all -> 0x051d }
            if (r37 != 0) goto L_0x0512
            r15 = 0
            r0 = r40
            com.google.api.client.http.HttpUnsuccessfulResponseHandler r0 = r0.unsuccessfulResponseHandler     // Catch:{ all -> 0x051d }
            r37 = r0
            if (r37 == 0) goto L_0x03df
            r0 = r40
            com.google.api.client.http.HttpUnsuccessfulResponseHandler r0 = r0.unsuccessfulResponseHandler     // Catch:{ all -> 0x051d }
            r37 = r0
            r0 = r37
            r1 = r40
            r2 = r26
            r3 = r31
            boolean r15 = r0.handleResponse(r1, r2, r3)     // Catch:{ all -> 0x051d }
        L_0x03df:
            if (r15 != 0) goto L_0x03f6
            int r37 = r26.getStatusCode()     // Catch:{ all -> 0x051d }
            com.google.api.client.http.HttpHeaders r38 = r26.getHeaders()     // Catch:{ all -> 0x051d }
            r0 = r40
            r1 = r37
            r2 = r38
            boolean r37 = r0.handleRedirect(r1, r2)     // Catch:{ all -> 0x051d }
            if (r37 == 0) goto L_0x04da
            r15 = 1
        L_0x03f6:
            r31 = r31 & r15
            if (r31 == 0) goto L_0x03fd
            r26.ignore()     // Catch:{ all -> 0x051d }
        L_0x03fd:
            int r30 = r30 + -1
            r29 = 1
            if (r26 == 0) goto L_0x0408
            if (r29 != 0) goto L_0x0408
            r26.disconnect()
        L_0x0408:
            if (r31 != 0) goto L_0x0052
            if (r26 != 0) goto L_0x0526
            r37 = 0
        L_0x040e:
            io.opencensus.trace.EndSpanOptions r37 = com.google.api.client.http.OpenCensusUtils.getEndSpanOptions(r37)
            r0 = r32
            r1 = r37
            r0.end(r1)
            if (r26 != 0) goto L_0x0530
            throw r16
        L_0x041c:
            r37 = 0
            goto L_0x000c
        L_0x0420:
            r19 = 0
            goto L_0x0110
        L_0x0424:
            java.lang.StringBuilder r37 = new java.lang.StringBuilder
            r37.<init>()
            r0 = r37
            r1 = r25
            java.lang.StringBuilder r37 = r0.append(r1)
            java.lang.String r38 = " "
            java.lang.StringBuilder r37 = r37.append(r38)
            java.lang.String r38 = USER_AGENT_SUFFIX
            java.lang.StringBuilder r37 = r37.append(r38)
            java.lang.String r24 = r37.toString()
            r0 = r40
            com.google.api.client.http.HttpHeaders r0 = r0.headers
            r37 = r0
            r0 = r37
            r1 = r24
            r0.setUserAgent(r1)
            java.lang.String r37 = "http.user_agent"
            r0 = r32
            r1 = r37
            r2 = r24
            addSpanAttribute(r0, r1, r2)
            goto L_0x01a6
        L_0x045b:
            r9 = 0
            goto L_0x01ee
        L_0x045e:
            r0 = r40
            com.google.api.client.http.HttpEncoding r0 = r0.encoding
            r37 = r0
            java.lang.String r8 = r37.getName()
            com.google.api.client.http.HttpEncodingStreamingContent r34 = new com.google.api.client.http.HttpEncodingStreamingContent
            r0 = r40
            com.google.api.client.http.HttpEncoding r0 = r0.encoding
            r37 = r0
            r0 = r34
            r1 = r33
            r2 = r37
            r0.<init>(r1, r2)
            r33 = r34
            goto L_0x022c
        L_0x047d:
            r31 = 0
            goto L_0x034f
        L_0x0481:
            r37 = move-exception
            if (r28 != 0) goto L_0x048d
            java.io.InputStream r21 = r23.getContent()     // Catch:{ IOException -> 0x048e }
            if (r21 == 0) goto L_0x048d
            r21.close()     // Catch:{ IOException -> 0x048e }
        L_0x048d:
            throw r37     // Catch:{ IOException -> 0x048e }
        L_0x048e:
            r14 = move-exception
        L_0x048f:
            r0 = r40
            boolean r0 = r0.retryOnExecuteIOException     // Catch:{ all -> 0x04bf }
            r37 = r0
            if (r37 != 0) goto L_0x04c4
            r0 = r40
            com.google.api.client.http.HttpIOExceptionHandler r0 = r0.ioExceptionHandler     // Catch:{ all -> 0x04bf }
            r37 = r0
            if (r37 == 0) goto L_0x04b1
            r0 = r40
            com.google.api.client.http.HttpIOExceptionHandler r0 = r0.ioExceptionHandler     // Catch:{ all -> 0x04bf }
            r37 = r0
            r0 = r37
            r1 = r40
            r2 = r31
            boolean r37 = r0.handleIOException(r1, r2)     // Catch:{ all -> 0x04bf }
            if (r37 != 0) goto L_0x04c4
        L_0x04b1:
            r37 = 0
            io.opencensus.trace.EndSpanOptions r37 = com.google.api.client.http.OpenCensusUtils.getEndSpanOptions(r37)     // Catch:{ all -> 0x04bf }
            r0 = r32
            r1 = r37
            r0.end(r1)     // Catch:{ all -> 0x04bf }
            throw r14     // Catch:{ all -> 0x04bf }
        L_0x04bf:
            r37 = move-exception
        L_0x04c0:
            r36.close()
            throw r37
        L_0x04c4:
            r16 = r14
            if (r19 == 0) goto L_0x04d5
            java.util.logging.Level r37 = java.util.logging.Level.WARNING     // Catch:{ all -> 0x04bf }
            java.lang.String r38 = "exception thrown while executing request"
            r0 = r20
            r1 = r37
            r2 = r38
            r0.log(r1, r2, r14)     // Catch:{ all -> 0x04bf }
        L_0x04d5:
            r36.close()
            goto L_0x03ba
        L_0x04da:
            if (r31 == 0) goto L_0x03f6
            r0 = r40
            com.google.api.client.http.BackOffPolicy r0 = r0.backOffPolicy     // Catch:{ all -> 0x051d }
            r37 = r0
            if (r37 == 0) goto L_0x03f6
            r0 = r40
            com.google.api.client.http.BackOffPolicy r0 = r0.backOffPolicy     // Catch:{ all -> 0x051d }
            r37 = r0
            int r38 = r26.getStatusCode()     // Catch:{ all -> 0x051d }
            boolean r37 = r37.isBackOffRequired(r38)     // Catch:{ all -> 0x051d }
            if (r37 == 0) goto L_0x03f6
            r0 = r40
            com.google.api.client.http.BackOffPolicy r0 = r0.backOffPolicy     // Catch:{ all -> 0x051d }
            r37 = r0
            long r6 = r37.getNextBackOffMillis()     // Catch:{ all -> 0x051d }
            r38 = -1
            int r37 = (r6 > r38 ? 1 : (r6 == r38 ? 0 : -1))
            if (r37 == 0) goto L_0x03f6
            r0 = r40
            com.google.api.client.util.Sleeper r0 = r0.sleeper     // Catch:{ InterruptedException -> 0x0563 }
            r37 = r0
            r0 = r37
            r0.sleep(r6)     // Catch:{ InterruptedException -> 0x0563 }
        L_0x050f:
            r15 = 1
            goto L_0x03f6
        L_0x0512:
            if (r26 != 0) goto L_0x051a
            r37 = 1
        L_0x0516:
            r31 = r31 & r37
            goto L_0x03fd
        L_0x051a:
            r37 = 0
            goto L_0x0516
        L_0x051d:
            r37 = move-exception
            if (r26 == 0) goto L_0x0525
            if (r29 != 0) goto L_0x0525
            r26.disconnect()
        L_0x0525:
            throw r37
        L_0x0526:
            int r37 = r26.getStatusCode()
            java.lang.Integer r37 = java.lang.Integer.valueOf(r37)
            goto L_0x040e
        L_0x0530:
            r0 = r40
            com.google.api.client.http.HttpResponseInterceptor r0 = r0.responseInterceptor
            r37 = r0
            if (r37 == 0) goto L_0x0545
            r0 = r40
            com.google.api.client.http.HttpResponseInterceptor r0 = r0.responseInterceptor
            r37 = r0
            r0 = r37
            r1 = r26
            r0.interceptResponse(r1)
        L_0x0545:
            r0 = r40
            boolean r0 = r0.throwExceptionOnExecuteError
            r37 = r0
            if (r37 == 0) goto L_0x0562
            boolean r37 = r26.isSuccessStatusCode()
            if (r37 != 0) goto L_0x0562
            com.google.api.client.http.HttpResponseException r37 = new com.google.api.client.http.HttpResponseException     // Catch:{ all -> 0x055d }
            r0 = r37
            r1 = r26
            r0.<init>((com.google.api.client.http.HttpResponse) r1)     // Catch:{ all -> 0x055d }
            throw r37     // Catch:{ all -> 0x055d }
        L_0x055d:
            r37 = move-exception
            r26.disconnect()
            throw r37
        L_0x0562:
            return r26
        L_0x0563:
            r37 = move-exception
            goto L_0x050f
        L_0x0565:
            r37 = move-exception
            r26 = r27
            goto L_0x04c0
        L_0x056a:
            r14 = move-exception
            r26 = r27
            goto L_0x048f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.http.HttpRequest.execute():com.google.api.client.http.HttpResponse");
    }

    @Beta
    public Future<HttpResponse> executeAsync(Executor executor) {
        FutureTask<HttpResponse> future = new FutureTask<>(new Callable<HttpResponse>() {
            public HttpResponse call() throws Exception {
                return HttpRequest.this.execute();
            }
        });
        executor.execute(future);
        return future;
    }

    @Beta
    public Future<HttpResponse> executeAsync() {
        return executeAsync(Executors.newFixedThreadPool(1, new ThreadFactoryBuilder().setDaemon(true).build()));
    }

    public boolean handleRedirect(int statusCode, HttpHeaders responseHeaders2) {
        String redirectLocation = responseHeaders2.getLocation();
        if (!getFollowRedirects() || !HttpStatusCodes.isRedirect(statusCode) || redirectLocation == null) {
            return false;
        }
        setUrl(new GenericUrl(this.url.toURL(redirectLocation), this.useRawRedirectUrls));
        if (statusCode == 303) {
            setRequestMethod("GET");
            setContent((HttpContent) null);
        }
        this.headers.setAuthorization((String) null);
        this.headers.setIfMatch((String) null);
        this.headers.setIfNoneMatch((String) null);
        this.headers.setIfModifiedSince((String) null);
        this.headers.setIfUnmodifiedSince((String) null);
        this.headers.setIfRange((String) null);
        return true;
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }

    public HttpRequest setSleeper(Sleeper sleeper2) {
        this.sleeper = (Sleeper) Preconditions.checkNotNull(sleeper2);
        return this;
    }

    private static void addSpanAttribute(Span span, String key, String value) {
        if (value != null) {
            span.putAttribute(key, AttributeValue.stringAttributeValue(value));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0030, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0031, code lost:
        r6 = r4;
        r4 = r3;
        r3 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0045, code lost:
        r3 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getVersion() {
        /*
            java.lang.String r2 = "unknown-version"
            java.lang.Class<com.google.api.client.http.HttpRequest> r3 = com.google.api.client.http.HttpRequest.class
            java.lang.String r4 = "/google-http-client.properties"
            java.io.InputStream r0 = r3.getResourceAsStream(r4)     // Catch:{ IOException -> 0x0028 }
            r4 = 0
            if (r0 == 0) goto L_0x001b
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Throwable -> 0x002e, all -> 0x0045 }
            r1.<init>()     // Catch:{ Throwable -> 0x002e, all -> 0x0045 }
            r1.load(r0)     // Catch:{ Throwable -> 0x002e, all -> 0x0045 }
            java.lang.String r3 = "google-http-client.version"
            java.lang.String r2 = r1.getProperty(r3)     // Catch:{ Throwable -> 0x002e, all -> 0x0045 }
        L_0x001b:
            if (r0 == 0) goto L_0x0022
            if (r4 == 0) goto L_0x002a
            r0.close()     // Catch:{ Throwable -> 0x0023 }
        L_0x0022:
            return r2
        L_0x0023:
            r3 = move-exception
            r4.addSuppressed(r3)     // Catch:{ IOException -> 0x0028 }
            goto L_0x0022
        L_0x0028:
            r3 = move-exception
            goto L_0x0022
        L_0x002a:
            r0.close()     // Catch:{ IOException -> 0x0028 }
            goto L_0x0022
        L_0x002e:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r4 = move-exception
            r6 = r4
            r4 = r3
            r3 = r6
        L_0x0034:
            if (r0 == 0) goto L_0x003b
            if (r4 == 0) goto L_0x0041
            r0.close()     // Catch:{ Throwable -> 0x003c }
        L_0x003b:
            throw r3     // Catch:{ IOException -> 0x0028 }
        L_0x003c:
            r5 = move-exception
            r4.addSuppressed(r5)     // Catch:{ IOException -> 0x0028 }
            goto L_0x003b
        L_0x0041:
            r0.close()     // Catch:{ IOException -> 0x0028 }
            goto L_0x003b
        L_0x0045:
            r3 = move-exception
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.http.HttpRequest.getVersion():java.lang.String");
    }
}
