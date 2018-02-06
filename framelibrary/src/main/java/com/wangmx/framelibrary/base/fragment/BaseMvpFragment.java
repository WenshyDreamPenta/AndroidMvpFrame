package com.wangmx.framelibrary.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangmx.framelibrary.base.BasePresenter;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/6
 *     desc   : Mvp Fragment基类
 * </pre>
 */
public abstract class BaseMvpFragment<V, P extends BasePresenter<V>> extends BaseFragment {
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        mPresenter.attach((V) this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        mPresenter.detach();
        super.onDestroyView();
    }

    public abstract P initPresenter();
}
