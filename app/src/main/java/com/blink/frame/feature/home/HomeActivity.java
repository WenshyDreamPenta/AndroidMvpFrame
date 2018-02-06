package com.blink.frame.feature.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blink.frame.R;
import com.blink.framelibrary.base.activity.BaseMvpActivity;
import com.blink.framelibrary.utils.animator.FrameAnimator;

public class HomeActivity extends BaseMvpActivity<HomeContract.View, HomePresenter> implements HomeContract.View {
    private ImageView imageView;
    private TextView tvInfo;
    private Handler mHandler = new Handler();

    @Override
    public int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    public void init() {
        // getToolBar().setTitle("main activity");
        mPresenter.initDatas();
        initAnimator();
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void initEvents() {
        imageView.setOnClickListener(this);
    }

    @Override
    public void initViews() {
        imageView = findViewById(R.id.iv_refresh);
        tvInfo = findViewById(R.id.tv_info);
    }

    @Override
    public void initAnimator() {
        getLifecycle().addObserver(FrameAnimator.getInstance().setParameters(this, getLifecycle(), R.array.refresh_anim, 24));
        FrameAnimator.getInstance().createFramesAnim(imageView).setIsRecycle(true).start();
    }

    @Override
    public void updateViews(String data) {
        tvInfo.setText(data);
    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.iv_refresh:
                showToast("Loading...");
                showLoading();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        disLoading();
                    }
                }, 2 * 1000);
                break;
        }
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
