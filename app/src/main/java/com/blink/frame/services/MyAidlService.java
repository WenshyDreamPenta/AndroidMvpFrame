package com.blink.frame.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.blink.frame.IMyAidlInterface;
/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/27
 *     desc   : aidl service
 * </pre>
 */
public class MyAidlService extends Service {
    public MyAidlService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
       return mBinder;
    }

    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }
    };
}
