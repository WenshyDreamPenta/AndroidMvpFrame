package com.blink.framelibrary.http.api.uploadapi;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/9
 *     desc   :
 * </pre>
 */
public interface UploadFileApi {
    @POST
    Flowable<ResponseBody> uploadFile(@Url String url, @Body MultipartBody body);
}
