package com.wangmx.frame.base;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.wangmx.frame.R;
import com.wangmx.framelibrary.utils.BarUtils;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/29
 *     desc   : 有bar的activity基类
 * </pre>
 */
public abstract class BaseBarActivity extends BaseActivity {
    protected CoordinatorLayout rootLayout;
    protected Toolbar mToolbar;
    protected AppBarLayout appBarLayout;
    protected FrameLayout flContainer;

    @Override
    protected void setBaseView(int layoutId) {
        contentView = LayoutInflater.from(this).inflate(R.layout.base_activity, null);
        setContentView(contentView);
        rootLayout = findViewById(R.id.root_layout);
        appBarLayout = findViewById(R.id.appbarlayout);
        mToolbar = findViewById(R.id.toolbar);
        flContainer = findViewById(R.id.activity_container);
        flContainer.addView(LayoutInflater.from(this).inflate(layoutId, flContainer, false));
        setSupportActionBar(mToolbar);
        getToolBar().setDisplayHomeAsUpEnabled(true);

        BarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), 0);
        BarUtils.addMarginTopEqualStatusBarHeight(rootLayout);
    }

    protected ActionBar getToolBar() {
        return getSupportActionBar();
    }
}
