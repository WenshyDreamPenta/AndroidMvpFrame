package com.blink.frame.feature.home;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blink.frame.IMyAidlInterface;
import com.blink.frame.R;
import com.blink.framelibrary.base.activity.BaseMvpActivity;
import com.blink.framelibrary.http.HttpApiRequest;
import com.blink.framelibrary.http.api.testapi.TestApi;
import com.blink.framelibrary.http.api.testapi.module.TestModule;
import com.blink.framelibrary.http.subscriber.ApiSubscriber;
import com.blink.framelibrary.utils.animator.FrameAnimator;
/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/28
 *     desc   :
 * </pre>
 */
public class HomeActivity extends BaseMvpActivity<HomeContract.View, HomePresenter> implements HomeContract.View {
    private ImageView imageView;
    private RelativeLayout rlRoot;
    private TextView tvApi;
    private TextView tvAnim;
    private IMyAidlInterface mIRemoteService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIRemoteService = IMyAidlInterface.Stub.asInterface(service);
            try {
                mIRemoteService.add(2,3);
            }
            catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIRemoteService = null;
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    public void init(){
        // getToolBar().setTitle("main activity");
        mPresenter.initDatas();
        Intent intent = new Intent();
        intent.setAction("com.blink.frame.services.MyAidlService");
        intent.setPackage("com.blink.frame");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void initEvents() {
        imageView.setOnClickListener(this);
        tvApi.setOnClickListener(this);
        tvAnim.setOnClickListener(this);
    }

    @Override
    public void initViews() {
        imageView = findViewById(R.id.iv_refresh);
        tvAnim = findViewById(R.id.tv_anim_test);
        tvApi = findViewById(R.id.tv_api_test);
        rlRoot = findViewById(R.id.rl_root);
    }

    @Override
    public void initAnimator() {
        FrameAnimator.getInstance().setParameters(this, R.array.refresh_anim, 24).createFramesAnim(imageView).setIsRecycle(true).start();
    }

    @Override
    public void updateViews(String data) {
    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_anim_test:
                initAnimator();
                break;
            case R.id.tv_api_test:
                HttpApiRequest.request(HttpApiRequest.getApi(TestApi.class).getTestApi(), new ApiSubscriber<TestModule>() {
                    @Override
                    public void onNext(TestModule testModule) {
                        Log.d("api", "onNext: " + testModule.getMsg());
                    }
                });
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
