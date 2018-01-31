package com.wangmx.frame.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wangmx.frame.R;
import com.wangmx.frame.base.BaseBarActivity;
import com.wangmx.framelibrary.utils.animator.FrameAnimator;

public class MainActivity extends BaseBarActivity implements MainContract.View{
    private ImageView imageView;
    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    public void init() {
        getToolBar().setTitle("main activity");

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
    public void onWidgetClick(View view) {
    }

}
