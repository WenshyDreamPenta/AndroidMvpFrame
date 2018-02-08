package com.blink.framelibrary.network.api.testapi;

import com.blink.framelibrary.network.api.testapi.module.TestModule;
import com.blink.framelibrary.network.constant.HttpData;

import io.reactivex.Flowable;
import retrofit2.http.POST;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/8
 *     desc   :
 * </pre>
 */
public interface TestApi {
    @POST(HttpData.TEST_API)
    Flowable<TestModule> getTestApi();
}
