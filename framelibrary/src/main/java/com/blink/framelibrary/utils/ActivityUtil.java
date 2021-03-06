package com.blink.framelibrary.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/28
 *     desc   : 全局Activity管理类
 * </pre>
 */
public class ActivityUtil {
    private static Stack<Activity> activityStack;

    private static ActivityUtil instance;

    private ActivityUtil() {}

    //单例
    public static ActivityUtil getManager() {
        if (instance == null) {
            instance = new ActivityUtil();
        }
        return instance;
    }


    //添加Activity到堆栈
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    //remove Activity
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity = null;
        }
    }

    //获取当前Activity（堆栈中最后一个压入的）
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    //结束当前Activity（堆栈中最后一个压入的）
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    //结束指定的Activity
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    //结束指定类名的Activity
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    //结束所有Activity
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

}
