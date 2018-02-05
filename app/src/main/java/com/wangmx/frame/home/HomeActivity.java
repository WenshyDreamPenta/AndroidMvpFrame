package com.wangmx.frame.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wangmx.frame.R;
import com.wangmx.framelibrary.base.activity.BaseMvpActivity;
import com.wangmx.framelibrary.utils.animator.FrameAnimator;

public class HomeActivity extends BaseMvpActivity<HomeContract.View, HomePresenter> implements HomeContract.View{
    private ImageView imageView;
    @Override
    public int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    public void init() {
       // getToolBar().setTitle("main activity");
        mPresenter.initDatas();
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void initEvents() {

    }

    @Override
    public void initViews() {
        imageView = findViewById(R.id.iv_refresh);
        initAnimator();
    }

    @Override
    public void initAnimator() {
        getLifecycle().addObserver(FrameAnimator.getInstance().setParameters(this, getLifecycle(),R.array.refresh_anim, 24));
        FrameAnimator.getInstance().createFramesAnim(imageView).setIsRecycle(true).start();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void updateViews(String data) {

    }

    @Override
    public void onWidgetClick(View view) {
    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected boolean isShouldHasBar() {
        return false;
    }

    @Override
    protected boolean isEventActivity() {
        return false;
    }
}
