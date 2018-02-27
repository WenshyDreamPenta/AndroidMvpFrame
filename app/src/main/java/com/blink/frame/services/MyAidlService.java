package com.blink.frame.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import com.blink.frame.IMyAidlInterface;
/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/27
 *     desc   : aidl service
 * </pre>
 */
public class MyAidlService extends Service {

    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            Log.d("MyAidlService", "add: " + (a + b));
            return a + b;
        }

        public int getPid(){
            return Process.myPid();
        }
    };

    public MyAidlService() {}

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
       return mBinder;
    }




}
