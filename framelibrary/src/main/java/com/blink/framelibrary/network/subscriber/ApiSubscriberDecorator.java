package com.blink.framelibrary.network.subscriber;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/5
 *     desc   : api数据 订阅
 * </pre>
 */

public class ApiSubscriberDecorator<T> extends ApiSubscriber<T> {

    private int requestId;
    private ApiSubscriber apiSubscriber;

    public ApiSubscriberDecorator(int requestId, ApiSubscriber apiSubscriber) {
        this.requestId = requestId;
        this.apiSubscriber = apiSubscriber;
    }

    @Override
    public void onNext(T t) {
        apiSubscriber.onNext(t);
    }

    @Override
    public void onError(Throwable t) {
        apiSubscriber.onError(t);
    }

    @Override
    public void onComplete() {
        apiSubscriber.onComplete();
    }

}
