package com.blink.frame.feature.home;

import com.blink.framelibrary.base.BasePresenter;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/28
 *     desc   :
 * </pre>
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Override
    public void initDatas() {
        String s = "hello";
        mView.updateViews(s);
    }
}
