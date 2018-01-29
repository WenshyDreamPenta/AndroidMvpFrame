package com.wangmx.frame.main;

import android.os.Bundle;
import android.view.View;

import com.wangmx.frame.R;
import com.wangmx.frame.base.BaseBarActivity;

public class MainActivity extends BaseBarActivity {

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

    }

    @Override
    public void onWidgetClick(View view) {
    }

}
