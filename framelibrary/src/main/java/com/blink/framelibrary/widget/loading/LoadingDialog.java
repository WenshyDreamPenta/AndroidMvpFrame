package com.blink.framelibrary.widget.loading;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;

import com.blink.framelibrary.R;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/6
 *     desc   : Loading 布局可自定义
 * </pre>
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context, @LayoutRes int layoutId) {
        super(context, R.style.NormalDialogStyle);
        init(layoutId);
    }

    private void init(@LayoutRes int layoutId) {
        setContentView(layoutId);
        this.setCancelable(false);
    }

    @Override
    public void dismiss() {
        if (isShowing()) {
            super.dismiss();
        }
    }



}
