package com.wangmx.framelibrary.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wangmx.framelibrary.base.BasePresenter;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/5
 *     desc   : mvp Activity 基类
 * </pre>
 */
public abstract class BaseMvpActivity<V, P extends BasePresenter<V>> extends BaseBarActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attach((V) this);
    }

    public abstract P initPresenter();
}