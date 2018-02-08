package com.blink.framelibrary.network;

import com.blink.framelibrary.config.Config;
import com.blink.framelibrary.network.manager.RetrofitManager;
import com.blink.framelibrary.network.subscriber.ApiSubscriber;
import com.blink.framelibrary.network.api.user.UserApi;
import com.blink.framelibrary.network.interceptor.HttpInterceptor;

import io.reactivex.Flowable;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/30
 *     desc   :
 * </pre>
 */
public class ApiRequest {

    private static RetrofitManager retrofitManager = new RetrofitManager.Builder()
            .setBaseUrl(Config.BASE_URL)
            .addApiService(0, UserApi.class)
            .addInterceptor(new HttpInterceptor())
            .build();

    public static <T> void requestApi(Flowable<T> observable, ApiSubscriber<T> subscriber) {
        retrofitManager.requestApi(observable, subscriber);
    }

    public static <T> T getService(int id) {
        if (retrofitManager != null) {
            return (T) retrofitManager.getService(id);
        }
        return null;
    }

}
