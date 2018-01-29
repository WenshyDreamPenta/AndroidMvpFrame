package com.wangmx.frame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangmx.framelibrary.utils.CommonUtil;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/24
 *     desc   : Fragment 基类
 * </pre>
 */
public abstract class BaseFragment extends Fragment implements  BaseInterface.BaseView{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        init();
        initViews();
        initEvents();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }


    @Override
    public void onClick(View v) {
        if(CommonUtil.isFastClick()){
            return;
        }
        onWidgetClick(v);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
