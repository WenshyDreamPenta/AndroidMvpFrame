package com.wangmx.frame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wangmx.framelibrary.utils.ActivityManageUtil;
import com.wangmx.framelibrary.utils.CommonUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/24
 *     desc   : Activity基类
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseInterface.BaseEvent{
    private Unbinder mUnbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        ActivityManageUtil.getManager().addActivity(this);
        init();
        initViews();
        initEvents();
    }

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initEvents();

    protected abstract void init();

    @Override
    public void doClick(View view) {
        if(CommonUtil.isFastClick()){
            return;
        }
        onWidgetClick(view);
    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManageUtil.getManager().removeActivity(this);
        mUnbinder.unbind();
    }
}
