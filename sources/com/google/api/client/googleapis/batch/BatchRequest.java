package com.google.api.client.googleapis.batch;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.MultipartContent;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class BatchRequest {
    private static final String GLOBAL_BATCH_ENDPOINT = "https://www.googleapis.com/batch";
    private static final String GLOBAL_BATCH_ENDPOINT_WARNING = "You are using the global batch endpoint which will soon be shut down. Please instantiate your BatchRequest via your service client's `batch(HttpRequestInitializer)` method. For an example, please see https://github.com/googleapis/google-api-java-client#batching.";
    private static final Logger LOGGER = Logger.getLogger(BatchRequest.class.getName());
    private GenericUrl batchUrl = new GenericUrl(GLOBAL_BATCH_ENDPOINT);
    private final HttpRequestFactory requestFactory;
    List<RequestInfo<?, ?>> requestInfos = new ArrayList();
    private Sleeper sleeper = Sleeper.DEFAULT;

    static class RequestInfo<T, E> {
        final BatchCallback<T, E> callback;
        final Class<T> dataClass;
        final Class<E> errorClass;
        final HttpRequest request;

        RequestInfo(BatchCallback<T, E> callback2, Class<T> dataClass2, Class<E> errorClass2, HttpRequest request2) {
            this.callback = callback2;
            this.dataClass = dataClass2;
            this.errorClass = errorClass2;
            this.request = request2;
        }
    }

    @Deprecated
    public BatchRequest(HttpTransport transport, HttpRequestInitializer httpRequestInitializer) {
        this.requestFactory = httpRequestInitializer == null ? transport.createRequestFactory() : transport.createRequestFactory(httpRequestInitializer);
    }

    public BatchRequest setBatchUrl(GenericUrl batchUrl2) {
        this.batchUrl = batchUrl2;
        return this;
    }

    public GenericUrl getBatchUrl() {
        return this.batchUrl;
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }

    public BatchRequest setSleeper(Sleeper sleeper2) {
        this.sleeper = (Sleeper) Preconditions.checkNotNull(sleeper2);
        return this;
    }

    public <T, E> BatchRequest queue(HttpRequest httpRequest, Class<T> dataClass, Class<E> errorClass, BatchCallback<T, E> callback) throws IOException {
        Preconditions.checkNotNull(httpRequest);
        Preconditions.checkNotNull(callback);
        Preconditions.checkNotNull(dataClass);
        Preconditions.checkNotNull(errorClass);
        this.requestInfos.add(new RequestInfo(callback, dataClass, errorClass, httpRequest));
        return this;
    }

    public int size() {
        return this.requestInfos.size();
    }

    /* JADX INFO: finally extract failed */
    public void execute() throws IOException {
        boolean retryAllowed;
        Preconditions.checkState(!this.requestInfos.isEmpty());
        if (GLOBAL_BATCH_ENDPOINT.equals(this.batchUrl.toString())) {
            LOGGER.log(Level.WARNING, GLOBAL_BATCH_ENDPOINT_WARNING);
        }
        HttpRequest batchRequest = this.requestFactory.buildPostRequest(this.batchUrl, (HttpContent) null);
        batchRequest.setInterceptor(new BatchInterceptor(batchRequest.getInterceptor()));
        int retriesRemaining = batchRequest.getNumberOfRetries();
        do {
            retryAllowed = retriesRemaining > 0;
            MultipartContent batchContent = new MultipartContent();
            batchContent.getMediaType().setSubType("mixed");
            int contentId = 1;
            for (RequestInfo<?, ?> requestInfo : this.requestInfos) {
                batchContent.addPart(new MultipartContent.Part(new HttpHeaders().setAcceptEncoding((String) null).set("Content-ID", (Object) Integer.valueOf(contentId)), new HttpRequestContent(requestInfo.request)));
                contentId++;
            }
            batchRequest.setContent(batchContent);
            HttpResponse response = batchRequest.execute();
            try {
                BatchUnparsedResponse batchResponse = new BatchUnparsedResponse(response.getContent(), "--" + response.getMediaType().getParameter("boundary"), this.requestInfos, retryAllowed);
                while (batchResponse.hasNext) {
                    batchResponse.parseNextResponse();
                }
                response.disconnect();
                List<RequestInfo<?, ?>> unsuccessfulRequestInfos = batchResponse.unsuccessfulRequestInfos;
                if (unsuccessfulRequestInfos.isEmpty()) {
                    break;
                }
                this.requestInfos = unsuccessfulRequestInfos;
                retriesRemaining--;
            } catch (Throwable th) {
                response.disconnect();
                throw th;
            }
        } while (retryAllowed);
        this.requestInfos.clear();
    }

    class BatchInterceptor implements HttpExecuteInterceptor {
        private HttpExecuteInterceptor originalInterceptor;

        BatchInterceptor(HttpExecuteInterceptor originalInterceptor2) {
            this.originalInterceptor = originalInterceptor2;
        }

        public void intercept(HttpRequest batchRequest) throws IOException {
            if (this.originalInterceptor != null) {
                this.originalInterceptor.intercept(batchRequest);
            }
            for (RequestInfo<?, ?> requestInfo : BatchRequest.this.requestInfos) {
                HttpExecuteInterceptor interceptor = requestInfo.request.getInterceptor();
                if (interceptor != null) {
                    interceptor.intercept(requestInfo.request);
                }
            }
        }
    }
}
