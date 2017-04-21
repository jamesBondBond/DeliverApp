package com.example.anshul.deliverapp.application;

import android.app.Application;
import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.util.Log;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ResourceBundle;

public class ClientSSLSocketFactory  {

    private TrustManager[] getWrappedTrustManagers(TrustManager[] trustManagers) {
        final X509TrustManager originalTrustManager = (X509TrustManager) trustManagers[0];
        return new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return originalTrustManager.getAcceptedIssuers();
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        try {
                            if (certs != null && certs.length > 0){
                                certs[0].checkValidity();
                            } else {
                                originalTrustManager.checkClientTrusted(certs, authType);
                            }
                        } catch (CertificateExpiredException e) {
                            e.printStackTrace();
                        } catch (CertificateNotYetValidException e) {
                            e.printStackTrace();
                        } catch (java.security.cert.CertificateException e) {
                            e.printStackTrace();
                        }
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        try {
                            if (certs != null && certs.length > 0){
                                certs[0].checkValidity();
                            } else {
                                originalTrustManager.checkServerTrusted(certs, authType);
                            }
                        } catch (CertificateExpiredException e) {
                            e.printStackTrace();
                        } catch (CertificateNotYetValidException e) {
                            e.printStackTrace();
                        } catch (java.security.cert.CertificateException e) {
                            e.printStackTrace();
                        }
                    }
                }
        };
    }

    private SSLSocketFactory getSSLSocketFactory_Certificate(String keyStoreType, int keystoreResId)
            throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException, java.security.cert.CertificateException {

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream caInput = AppController.getInstance().getResources().openRawResource(keystoreResId);

        Certificate ca = cf.generateCertificate(caInput);
        caInput.close();

        if (keyStoreType == null || keyStoreType.length() == 0) {
            keyStoreType = KeyStore.getDefaultType();
        }
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        TrustManager[] wrappedTrustManagers = getWrappedTrustManagers(tmf.getTrustManagers());

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, wrappedTrustManagers, null);

        return sslContext.getSocketFactory();
    }

    private SSLSocketFactory getSSLSocketFactory_KeyStore(String keyStoreType, int keystoreResId, String keyPassword)
            throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException, java.security.cert.CertificateException {

        InputStream caInput = AppController.getInstance().getResources().openRawResource(keystoreResId);

        // creating a KeyStore containing trusted CAs

        if (keyStoreType == null || keyStoreType.length() == 0) {
            keyStoreType = KeyStore.getDefaultType();
        }
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);

        keyStore.load(caInput, keyPassword.toCharArray());

        // creating a TrustManager that trusts the CAs in the KeyStore

        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        TrustManager[] wrappedTrustManagers = getWrappedTrustManagers(tmf.getTrustManagers());

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, wrappedTrustManagers, null);

        return sslContext.getSocketFactory();
    }

}