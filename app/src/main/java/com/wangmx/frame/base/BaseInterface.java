package com.wangmx.frame.base;

import android.view.View;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/24
 *     desc   : 基类接口
 * </pre>
 */
public interface BaseInterface {
    interface BaseView{
    }
    interface BasePresenter{

    }
    interface BaseEvent{
        void doClick(View view);
        void onWidgetClick(View view);

    }
}
