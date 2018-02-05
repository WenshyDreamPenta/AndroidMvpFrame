package com.wangmx.framelibrary.base;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/31
 *     desc   : Presenter基类
 * </pre>
 */
public abstract class BasePresenter<T> implements BaseInterface.BasePresenter{
    private T view;

    public void attach(T view){
        this.view = view;
    }

    public void detach(){
        this.view = null;
    }
}
