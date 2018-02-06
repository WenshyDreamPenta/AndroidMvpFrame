package com.blink.framelibrary.base.application;

import android.support.multidex.MultiDexApplication;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/24
 *     desc   : application 继承MutiDex,解决63535问题
 * </pre>
 */
public class BaseAplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
