package com.wangmx.frame.feature.home;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/28
 *     desc   :
 * </pre>
 */
public interface HomeContract {
    interface View{
        void initAnimator();
        void updateViews(String data);
    }
    interface Presenter{
        void initDatas();
    }

}
