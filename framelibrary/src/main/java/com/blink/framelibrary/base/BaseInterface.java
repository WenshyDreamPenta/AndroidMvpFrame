package com.blink.framelibrary.base;

import android.os.Bundle;
import android.view.View;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/24
 *     desc   : 基类接口
 * </pre>
 */
public interface BaseInterface {
    interface BaseView extends View.OnClickListener {
        void initData(final Bundle bundle);

        int getLayoutId();

        void onWidgetClick(final View view);

        void init();

        void initEvents();

        void initViews();
    }

    interface BaseUtilView{
        void showLoading();
        void disLoading();
        void showToast(String msg);
    }

    interface BasePresenter {

    }
}
