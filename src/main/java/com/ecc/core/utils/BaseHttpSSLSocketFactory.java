/**
 *
 */
package com.ecc.core.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author LIUAOZ
 * @version 1.0
 * @since 2016年12月29日 下午2:05:48
 */
public class BaseHttpSSLSocketFactory extends SSLSocketFactory {

    private SSLContext getSSLContext() {
        return createEasySSLContext();
    }

    @Override
    public Socket createSocket(InetAddress arg0, int arg1, InetAddress arg2, int arg3)
            throws IOException {
        return getSSLContext().getSocketFactory().createSocket(arg0, arg1, arg2, arg3);
    }

    @Override
    public Socket createSocket(String arg0, int arg1, InetAddress arg2, int arg3)
            throws IOException {
        return getSSLContext().getSocketFactory().createSocket(arg0, arg1, arg2, arg3);
    }

    @Override
    public Socket createSocket(InetAddress arg0, int arg1) throws IOException {
        return getSSLContext().getSocketFactory().createSocket(arg0, arg1);
    }

    @Override
    public Socket createSocket(String arg0, int arg1) throws IOException {
        return getSSLContext().getSocketFactory().createSocket(arg0, arg1);
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return null;
    }

    public String[] getDefaultCipherSuites() {
        return null;
    }

    @Override
    public Socket createSocket(Socket arg0, String arg1, int arg2, boolean arg3)
            throws IOException {
        return getSSLContext().getSocketFactory().createSocket(arg0, arg1, arg2, arg3);
    }

    private SSLContext createEasySSLContext() {
        try {
            SSLContext context = SSLContext.getInstance("SSL");
            context.init(null, new TrustManager[] {MyX509TrustManager.manger}, null);

            return context;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    public static class MyX509TrustManager implements X509TrustManager {
        static MyX509TrustManager manger = new MyX509TrustManager();

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {}
    }

}
