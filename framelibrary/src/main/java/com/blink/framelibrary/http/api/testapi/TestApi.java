package com.blink.framelibrary.http.api.testapi;

import com.blink.framelibrary.http.api.testapi.module.TestModule;
import com.blink.framelibrary.http.HttpData;

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
