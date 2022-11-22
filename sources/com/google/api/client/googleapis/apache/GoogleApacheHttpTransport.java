package com.google.api.client.googleapis.apache;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.util.SslUtils;
import java.io.IOException;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;

public final class GoogleApacheHttpTransport {
    public static ApacheHttpTransport newTrustedTransport() throws GeneralSecurityException, IOException {
        SocketConfig socketConfig = SocketConfig.custom().setRcvBufSize(8192).setSndBufSize(8192).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(-1, TimeUnit.MILLISECONDS);
        connectionManager.setValidateAfterInactivity(-1);
        KeyStore trustStore = GoogleUtils.getCertificateTrustStore();
        SSLContext sslContext = SslUtils.getTlsSslContext();
        SslUtils.initSslContext(sslContext, trustStore, SslUtils.getPkixTrustManagerFactory());
        return new ApacheHttpTransport(HttpClientBuilder.create().useSystemProperties().setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext)).setDefaultSocketConfig(socketConfig).setMaxConnTotal(200).setMaxConnPerRoute(20).setRoutePlanner(new SystemDefaultRoutePlanner(ProxySelector.getDefault())).setConnectionManager(connectionManager).disableRedirectHandling().disableAutomaticRetries().build());
    }

    private GoogleApacheHttpTransport() {
    }
}
