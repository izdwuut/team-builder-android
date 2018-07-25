package com.example.konikiewiczb.myapplication.framework;

import com.example.konikiewiczb.myapplication.BuildConfig;
import com.example.konikiewiczb.myapplication.Config;
import com.example.konikiewiczb.myapplication.R;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.cert.CertificateFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import android.content.Context;

public class RetrofitClient<E> {
    static retrofit2.Retrofit retrofit;
    static Api api;
    static OkHttpClient client;

    public static retrofit2.Retrofit getRetrofit(InputStream keystore) {
        if(retrofit != null) {
            return retrofit;
        }
        Retrofit.Builder builder = new retrofit2.Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        if(BuildConfig.USE_DEV_API) {
            OkHttpClient client = getHttpClient(keystore);
            builder.client(client);
        }
        retrofit = builder.build();
        return retrofit;
    }

    public static Api getApi(InputStream cert) {
        if(api != null) {
            return api;
        }
        return getRetrofit(cert).create(Api.class);
    }

    static OkHttpClient getHttpClient(InputStream cert) {
        if(client != null) {
            return client;
        }
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = cert;
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
            } finally {
                caInput.close();
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, Config.KEYSTORE_PASS.toCharArray());
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            TrustManager[] trustManagers = tmf.getTrustManagers();
            X509TrustManager x509TrustManager = (X509TrustManager) trustManagers[0];

            //create Okhttp client
            OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
            RetrofitClient.client = client;
            return client;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
