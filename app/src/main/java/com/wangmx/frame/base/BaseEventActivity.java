package com.wangmx.frame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wangmx.framelibrary.eventbus.EventMap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/5
 *     desc   : 事件总线基类
 * </pre>
 */
public abstract class BaseEventActivity extends BaseActivity {
    protected boolean isEvent = true;//事件开关
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isEvent){
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 事件观察者,事件总线处理
     * @param event 事件类型
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventMap.BaseEvent event){
            if(event instanceof EventMap.HExceptionEvent){

            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isEvent){
            EventBus.getDefault().unregister(this);
        }
    }
}
