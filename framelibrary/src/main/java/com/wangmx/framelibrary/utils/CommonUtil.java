package com.wangmx.framelibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/28
 *     desc   : 通用工具类
 * </pre>
 */

public class CommonUtil {

    //防止多次点击
    private static long lastClick = 0;

    public static boolean isNotEmpty(String str) {
        return !(str == null || str.equals(""));
    }

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    //判断是否快速点击
    public static boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 500) {
            lastClick = now;
            return false;
        }
        return true;
    }

    //获取分辨率
    public static DisplayMetrics getDisplayMetrics(Context context) {

        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;

    }
}
