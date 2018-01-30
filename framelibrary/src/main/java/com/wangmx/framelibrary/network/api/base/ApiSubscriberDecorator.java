package com.wangmx.framelibrary.network.api.base;

/**
 * Created by supertramp on 17/12/26.
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
