package com.blink.framelibrary.http;

import com.blink.framelibrary.config.Config;
import com.blink.framelibrary.http.interceptor.HttpInterceptor;
import com.blink.framelibrary.http.manager.HttpManager;
import com.blink.framelibrary.http.subscriber.ApiSubscriber;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/30
 *     desc   : Http Api Request
 * </pre>
 */
public class HttpApiRequest {

    private volatile static HttpManager mHttpManager;
    //constructor private
    private HttpApiRequest(){}

    //init HttpManager
    private static void initHttpManager(){
        synchronized (HttpManager.class){
            if (mHttpManager == null) {
                mHttpManager = new HttpManager.Builder().setBaseUrl(Config.BASE_URL)
                        .addInterceptor(new HttpInterceptor())
                        .build();
            }
        }
    }

    //update baseUrl
    private static void updateHttpManager(String baseUrl) {
        synchronized (HttpManager.class) {
            mHttpManager = null;
            if (mHttpManager == null) {
                mHttpManager = new HttpManager.Builder().setBaseUrl(baseUrl)
                        .addInterceptor(new HttpInterceptor())
                        .build();
            }
        }
    }

    //http request method
    public static <T> void request(@NonNull Flowable<T> observable, @NonNull ApiSubscriber<T> subscriber){
        initHttpManager();
        mHttpManager.request(observable, subscriber);
    }

    //get api class
    public static  <T>  T getApi(@NonNull Class<T> t){
        return  mHttpManager.getRetrofit().create(t);
    }
}