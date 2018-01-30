package com.wangmx.framelibrary.network;

import com.wangmx.framelibrary.network.api.base.ApiSubscriber;
import com.wangmx.framelibrary.network.api.user.UserApi;
import com.wangmx.framelibrary.network.interceptor.HttpInterceptor;

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
            .addService(0, UserApi.class)
            .addInterceptor(new HttpInterceptor())
            .build();

    public <T> int requestApi(Flowable<T> observable, ApiSubscriber<T> subscriber) {
        return retrofitManager.requestApi(observable, subscriber);
    }

    public <T> T getService(int id) {
        if (retrofitManager != null) {
            return (T) retrofitManager.getService(id);
        }
        return null;
    }

    ApiRequest getInstance() {
        if (mInstance == null) {
            mInstance = new ApiRequest();
        }
        return mInstance;
    }
}
