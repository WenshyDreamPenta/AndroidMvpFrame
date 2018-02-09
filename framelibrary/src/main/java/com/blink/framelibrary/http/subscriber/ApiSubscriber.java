package com.blink.framelibrary.http.subscriber;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/30
 *     desc   : api callback subscriber
 * </pre>
 */

public abstract class ApiSubscriber<T> extends ResourceSubscriber<T> {

    @Override
    public void onNext(T t) {}

    @Override
    public void onError(Throwable t) {
    }

    @Override
    public void onComplete() {}

}
