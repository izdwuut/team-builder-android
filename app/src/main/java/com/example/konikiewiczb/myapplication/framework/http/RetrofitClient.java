package com.example.konikiewiczb.myapplication.framework.http;

import com.example.konikiewiczb.myapplication.BuildConfig;
import com.example.konikiewiczb.myapplication.Config;

import java.security.cert.CertificateFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class RetrofitClient {
    static RetrofitClient instance;
    static Api api;
    static OkHttpClient client;

    private RetrofitClient (Class<Api> api, InputStream cert) {
        Retrofit.Builder builder = new retrofit2.Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        if(BuildConfig.USE_SSL_CERT) {
            OkHttpClient client = getHttpClient(cert);
            builder.client(client);
        }
        this.api = builder.build().
                create(api);
    }

    public static Api get(Class<Api> api) {
        if(instance == null) {
            instance = new RetrofitClient(api, null);
        }
        return instance.getApi();
    }
    public static Api get(Class<Api> api, InputStream cert) {
        if(instance == null) {
            instance = new RetrofitClient(api, cert);
        }
        return instance.getApi();
    }

    Api getApi() {
        return api;
    }

    OkHttpClient getHttpClient(InputStream cert) {
        if(client != null) {
            return client;
        }
        try {
            Certificate ca = getCert(cert);
            KeyStore keyStore = getKeyStore(ca);

            TrustManagerFactory tmf = getTrustManagerFactory();
            tmf.init(keyStore);

            SSLContext sslContext = getSSLContext();
            sslContext.init(null, tmf.getTrustManagers(), null);

            X509TrustManager x509TrustManager = getTrustManager(tmf);

            client = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    Certificate getCert(InputStream cert) throws CertificateException, IOException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream caInput = cert;
        Certificate ca;
        try {
            ca = cf.generateCertificate(caInput);
        } finally {
            caInput.close();
        }
        return ca;
    }

    KeyStore getKeyStore(Certificate cert) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, Config.KEYSTORE_PASS.toCharArray());
        keyStore.setCertificateEntry("ca", cert);
        return keyStore;
    }

    TrustManagerFactory getTrustManagerFactory() throws NoSuchAlgorithmException {
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        return TrustManagerFactory.getInstance(tmfAlgorithm);
    }

    SSLContext getSSLContext() throws NoSuchAlgorithmException {
        return SSLContext.getInstance("TLS");
    }

    X509TrustManager getTrustManager(TrustManagerFactory tmf) {
        TrustManager[] trustManagers = tmf.getTrustManagers();
        return (X509TrustManager) trustManagers[0];
    }
}
