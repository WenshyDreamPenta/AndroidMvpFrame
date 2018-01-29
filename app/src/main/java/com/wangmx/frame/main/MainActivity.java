package com.wangmx.frame.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wangmx.frame.R;
import com.wangmx.frame.base.BaseBarActivity;
import com.wangmx.framelibrary.utils.BarUtils;

public class MainActivity extends BaseBarActivity {

    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    public void init() {
        int staheight = BarUtils.getActionBarHeight(this);
        Log.d("MainActivity", "init: " + staheight) ;
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
