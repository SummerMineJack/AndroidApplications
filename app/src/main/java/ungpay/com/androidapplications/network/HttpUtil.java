package ungpay.com.androidapplications.network;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

import cz.msebera.android.httpclient.HttpEntity;

public class HttpUtil {
    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        client.setConnectTimeout(10000);
    }

    //get请求
    public static void get(String urlString, RequestParams params, AsyncHttpResponseHandler res) {
        client.get(urlString, params, res);
    }

    //post提交json请求
    public static void post4json(Context context, String urlString, HttpEntity entity, String headerType, JsonHttpResponseHandler jsonHttpResponseHandler) {
        client.post(context, urlString, entity, headerType, jsonHttpResponseHandler);
    }

    //post提交url拼接字符串
    public static void post4Str(String urlString, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        client.post(urlString, requestParams, asyncHttpResponseHandler);
    }

    public static AsyncHttpClient getClient() {
        return client;
    }

    private static class SafeTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws
                CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws
                CertificateException {
            try {
                for (X509Certificate certificate : chain) {
                    certificate.checkValidity(); //检查证书是否过期，签名是否通过等
                }
            } catch (Exception e) {
                throw new CertificateException(e);
            }
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}