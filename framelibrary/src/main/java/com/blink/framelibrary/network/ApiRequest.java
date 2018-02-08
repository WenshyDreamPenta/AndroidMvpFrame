package com.blink.framelibrary.network;

import com.blink.framelibrary.config.Config;
import com.blink.framelibrary.network.interceptor.HttpInterceptor;
import com.blink.framelibrary.network.manager.RetrofitManager;
import com.blink.framelibrary.network.subscriber.ApiSubscriber;

import io.reactivex.Flowable;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/30
 *     desc   :
 * </pre>
 */
public class ApiRequest {

    private volatile static RetrofitManager INSTANCE;
    //构造函数私有化
    private ApiRequest(){}

    //获取单例
    public static RetrofitManager getInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitManager.Builder().setBaseUrl(Config.BASE_URL)
                            .addInterceptor(new HttpInterceptor())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public <T> void requestApi(Flowable<T> observable, ApiSubscriber<T> subscriber) {
        if(INSTANCE == null){
            return;
        }
        INSTANCE.requestApi(observable, subscriber);
    }

    public static Object getApi(Class<?> t){
        if(INSTANCE != null){
            return INSTANCE.getRetrofit().create(t);
        }
        return null;
    }
}
