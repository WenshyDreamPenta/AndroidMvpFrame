package com.blink.framelibrary.base.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.r0adkll.slidr.Slidr;
import com.blink.framelibrary.R;
import com.blink.framelibrary.base.BaseInterface;
import com.blink.framelibrary.utils.ActivityUtil;
import com.blink.framelibrary.utils.CommonUtil;
import com.blink.framelibrary.widget.loading.LoadingDialog;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/24
 *     desc   : Activity基类
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseInterface.BaseView, BaseInterface.BaseUtilView {

    private static final String ACTIVITY_EXTRA = "ACTIVITY_EXTRA";
    protected View contentView;
    protected LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slidr.attach(this);
        Bundle bundle = getIntent().getExtras();
        initData(bundle);
        setBaseView(getLayoutId());
        initViews();
        initEvents();
        init();
        ActivityUtil.getManager().addActivity(this);
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
    public void showLoading() {
        if(mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(this, R.layout.loading_dialog);
        }
        mLoadingDialog.show();
    }

    @Override
    public void disLoading() {
        if(mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        CommonUtil.showCustomToast(this, msg, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.getManager().removeActivity(this);
    }

    public static void start(Context context, Bundle bundle) {
        Intent starter = new Intent(context, BaseActivity.class);
        starter.putExtra(ACTIVITY_EXTRA, bundle);
        context.startActivity(starter);
    }
}
