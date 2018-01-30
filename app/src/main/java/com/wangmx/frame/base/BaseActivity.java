package com.wangmx.frame.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.wangmx.framelibrary.utils.ActivityManageUtil;
import com.wangmx.framelibrary.utils.CommonUtil;
import com.wangmx.framelibrary.utils.animator.FrameAnimator;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/24
 *     desc   : Activity基类
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseInterface.BaseView {

    protected View contentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        initData(bundle);
        setBaseView(getLayoutId());
        init();
        initViews();
        initEvents();
        ActivityManageUtil.getManager().addActivity(this);//加入acvivity管理栈

    }

    protected void setBaseView(@LayoutRes int layoutId) {
        setContentView(contentView = LayoutInflater.from(this).inflate(layoutId, null));
    }

    @Override
    public void onClick(View v) {
        if (CommonUtil.isFastClick()) {
            return;
        }
        onWidgetClick(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManageUtil.getManager().removeActivity(this);
    }
}
