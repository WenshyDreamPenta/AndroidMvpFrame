package com.blink.framelibrary.network.manager;

import com.blink.framelibrary.network.subscriber.ApiSubscriber;
import com.blink.framelibrary.network.subscriber.ApiSubscriberDecorator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/30
 *     desc   : 网络Api管理类
 * </pre>
 */
public class RetrofitManager {

    private Retrofit mRetrofit;

    private AtomicInteger requestCounter = new AtomicInteger(0);
    public RetrofitManager(Retrofit retrofit, List<Interceptor> interceptors) {
        mRetrofit = retrofit;
    }
    public Retrofit getRetrofit(){
        return mRetrofit;
    }
    //请求
    public <T> int requestApi(Flowable<T> flowable, ApiSubscriber<T> subscriber) {
        int requstId = requestCounter.addAndGet(1);
        ApiSubscriberDecorator subscriberDecorator = new ApiSubscriberDecorator(requstId, subscriber);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriberDecorator);
        return requstId;
    }

    public static class Builder {

        private String baseUrl;
        private List<Interceptor> interceptors = new ArrayList<>();

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            interceptors.add(interceptor);
            return this;
        }

        public RetrofitManager build() {

            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().connectTimeout(HttpCommon.OKHTTP_CLIENT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(HttpCommon.OKHTTP_CLIENT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(HttpCommon.OKHTTP_CLIENT_READ_TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false);
            if (interceptors != null && interceptors.size() > 0) {
                for (Interceptor interceptor: interceptors) {
                    okHttpClientBuilder.addInterceptor(interceptor);

                }
            }

            Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .client(okHttpClientBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return new RetrofitManager(retrofit, interceptors);

        }

    }

    public interface HttpCommon {
        int OKHTTP_CLIENT_CONNECT_TIMEOUT = 10;
        int OKHTTP_CLIENT_WRITE_TIMEOUT = 20;
        int OKHTTP_CLIENT_READ_TIMEOUT = 20;
    }

}
