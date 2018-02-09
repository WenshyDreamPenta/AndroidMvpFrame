package com.blink.framelibrary.http;

import com.blink.framelibrary.http.api.uploadapi.UploadFileApi;
import com.blink.framelibrary.http.interceptor.HttpInterceptor;
import com.blink.framelibrary.http.manager.HttpManager;
import com.blink.framelibrary.http.requestbody.MultipartBuilder;
import com.blink.framelibrary.http.requestbody.UploadFileRequestBody;
import com.blink.framelibrary.http.subscriber.FileUploadSubscriber;

import java.io.File;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;

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
                mHttpManager = new HttpManager.Builder().setBaseUrl(HttpData.BASE_URL)
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
    public static <T> void request(@NonNull Flowable<T> observable, @NonNull ResourceSubscriber<T> subscriber){
        mHttpManager.request(observable, subscriber);
    }

    //get api class
    public static  <T>  T getApi(@NonNull Class<T> t){
        //init
        initHttpManager();
        return  mHttpManager.getRetrofit().create(t);
    }

    /**
     * 单上传文件的封装.
     * @param url 完整的接口地址
     * @param file 需要上传的文件
     * @param fileUploadSubscriber 上传回调
     */
    public static void upLoadFile(String url, File file, FileUploadSubscriber<ResponseBody> fileUploadSubscriber) {
        UploadFileRequestBody uploadFileRequestBody = new UploadFileRequestBody(file, fileUploadSubscriber);
        request(getApi(UploadFileApi.class).uploadFile(url, MultipartBuilder.fileToMultipartBody(file, uploadFileRequestBody)), fileUploadSubscriber);
    }
}
