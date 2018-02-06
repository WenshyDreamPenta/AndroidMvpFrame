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
        mPresenter = initPresenter();
        mPresenter.attach((V) this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public abstract P initPresenter();
}
