package com.blink.framelibrary.network.api.user;

import com.blink.framelibrary.network.api.user.module.QcloudSignModule;
import com.blink.framelibrary.network.constant.HttpData;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/30
 *     desc   :
 * </pre>
 */
public interface UserApi {
    @GET(HttpData.PLUGIN_QCLOUD)
    Flowable<QcloudSignModule> getQcloundSignApi();
}
