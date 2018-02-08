package com.blink.framelibrary.network;

import com.blink.framelibrary.config.Config;
import com.blink.framelibrary.network.interceptor.HttpInterceptor;
import com.blink.framelibrary.network.manager.HttpManager;
import com.blink.framelibrary.network.subscriber.ApiSubscriber;

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
    //构造函数私有化
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

    //http请求
    public static <T> void request(@NonNull Flowable<T> observable, @NonNull ApiSubscriber<T> subscriber){
        initHttpManager();
        mHttpManager.request(observable, subscriber);
    }

    //获取api接口类
    public static  <T>  T getApi(@NonNull Class<T> t){
        return  mHttpManager.getRetrofit().create(t);
    }
}
