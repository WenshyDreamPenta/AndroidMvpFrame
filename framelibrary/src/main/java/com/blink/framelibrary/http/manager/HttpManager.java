package com.blink.framelibrary.http.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
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
public class HttpManager {

    private Retrofit mRetrofit;

    public HttpManager(Retrofit retrofit, List<Interceptor> interceptors) {
        mRetrofit = retrofit;
    }

    //获取Retrofit
    public Retrofit getRetrofit(){
        return mRetrofit;
    }

    //请求
    public <T> void request(Flowable<T> flowable, ResourceSubscriber<T> subscriber) {
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
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

        public HttpManager build() {

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

            return new HttpManager(retrofit, interceptors);

        }

    }

    public interface HttpCommon {
        int OKHTTP_CLIENT_CONNECT_TIMEOUT = 10;
        int OKHTTP_CLIENT_WRITE_TIMEOUT = 20;
        int OKHTTP_CLIENT_READ_TIMEOUT = 20;
    }

}
