package com.blink.framelibrary.network.interceptor;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/30
 *     desc   :
 * </pre>
 */
public class HttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request != null) {
            URL requestURL = request.url().url();
            String baseURL = requestURL.getProtocol() + "://" + requestURL.getHost()  + requestURL.getPath();
            long ts = System.currentTimeMillis() / 1000L;
            Map<String, String> queryMap = splitQuery(requestURL);
            String paraUri = getParaUriNoSigned(queryMap, ts);
            Request.Builder signedRequestBuilder = request.newBuilder();
            signedRequestBuilder.addHeader("Connection", "close");
            signedRequestBuilder.url(baseURL + "?" + paraUri);
            request = signedRequestBuilder.build();
            Log.d("api", baseURL + "?" + paraUri);
        }
        return chain.proceed(request);
    }

    /**
     * 从 URL 获取参数
     */
    public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {

        Map<String, String> query_pairs = new TreeMap<>();
        if (url.getQuery() == null)
            return query_pairs;
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs)
        {
            int idx = pair.indexOf("=");
            query_pairs.put(pair.substring(0, idx), pair.substring(idx + 1));
        }
        return query_pairs;

    }

    private static String getParaUriNoSigned(Map<String, String> para, long ts) {

        para.put("timestamp", String.valueOf(ts));
        para.put("publickey", "3.9");
        para.put("system", "android");
        String paraUri = "";
        for (Map.Entry<String, String> entry : para.entrySet())
        {
            if (!TextUtils.isEmpty(entry.getValue()))
                paraUri += entry.getKey() + "=" + entry.getValue() + "&";
        }
        paraUri = paraUri.substring(0, paraUri.length() - 1);
        return paraUri;

    }
}
