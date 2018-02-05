package com.wangmx.framelibrary.base;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/31
 *     desc   : Presenter基类
 * </pre>
 */
public abstract class BasePresenter<T> implements BaseInterface.BasePresenter{
    protected T mView;

    public void attach(T view){
        this.mView = view;
    }

    public void detach(){
        this.mView = null;
    }
}
