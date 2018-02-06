package com.wangmx.framelibrary.widget.loading;

import android.app.Dialog;
import android.content.Context;

import com.wangmx.framelibrary.R;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/6
 *     desc   : Loading 布局可自定义
 * </pre>
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.NormalDialogStyle);
        init();
    }

    private void init() {
        setContentView(R.layout.loading_dialog);
        this.setCancelable(false);
    }

    @Override
    public void dismiss() {
        if (isShowing()) {
            super.dismiss();
        }
    }

}
