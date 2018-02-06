package com.blink.framelibrary.network;

import com.blink.framelibrary.network.api.base.ApiSubscriber;
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

    private static ApiRequest mInstance;
    private static RetrofitManager retrofitManager = new RetrofitManager.Builder().setBaseUrl("")
            .addApiService(0, UserApi.class)
            .addInterceptor(new HttpInterceptor())
            .build();

    ApiRequest getInstance() {
        if (mInstance == null) {
            mInstance = new ApiRequest();
        }
        return mInstance;
    }

    public <T> int requestApi(Flowable<T> observable, ApiSubscriber<T> subscriber) {
        return retrofitManager.requestApi(observable, subscriber);
    }

    public <T> T getService(int id) {
        if (retrofitManager != null) {
            return (T) retrofitManager.getService(id);
        }
        return null;
    }

}
