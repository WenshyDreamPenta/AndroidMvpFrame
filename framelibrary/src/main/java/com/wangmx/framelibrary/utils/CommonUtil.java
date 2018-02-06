package com.wangmx.framelibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.wangmx.framelibrary.R;
import com.wangmx.framelibrary.widget.text.HandyTextView;

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

    //Toast弹出
    public static void showCustomToast(Context context, String msg, int type){
        if (((Activity) context).isFinishing()){
            return;
        }
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.common_toast, null);
        ((HandyTextView) toastRoot.findViewById(R.id.toast_text)).setText(msg);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        if(type == 0){
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        else if(type == 1){
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.setView(toastRoot);
        toast.show();
    }

    //获取分辨率
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);

        return metric;
    }
}
