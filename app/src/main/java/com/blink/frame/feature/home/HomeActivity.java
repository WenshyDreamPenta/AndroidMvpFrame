package com.blink.frame.feature.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blink.frame.R;
import com.blink.framelibrary.base.activity.BaseMvpActivity;
import com.blink.framelibrary.utils.CommonUtil;
import com.blink.framelibrary.utils.animator.FrameAnimator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class HomeActivity extends BaseMvpActivity<HomeContract.View, HomePresenter> implements HomeContract.View {
    private ImageView imageView;
    private TextView tvInfo;
    private RelativeLayout rlRoot;
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
        try{
            Class<?> textClass = Class.forName("android.widget.TextView");
            Constructor constructor = textClass.getConstructor(Context.class);
            Method settext = textClass.getMethod("setText", CharSequence.class);
            Object object = constructor.newInstance(this);
            rlRoot.addView((TextView)object);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ((TextView)object).getLayoutParams();
            layoutParams.topMargin = CommonUtil.dp2px(this, 100);
            ((TextView)object).setLayoutParams(layoutParams);
            settext.invoke(object, "invoke hello");

        }
        catch (Exception e){
            e.printStackTrace();
        }
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
        rlRoot = findViewById(R.id.rl_root);
    }

    @Override
    public void initAnimator() {
        FrameAnimator.getInstance().setParameters(this, R.array.refresh_anim, 24)
                .createFramesAnim(imageView).setIsRecycle(true).start();
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
