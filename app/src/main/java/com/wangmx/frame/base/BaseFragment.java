package com.wangmx.frame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/24
 *     desc   : Fragment 基类
 * </pre>
 */
public abstract class BaseFragment extends Fragment implements  BaseInterface.BaseEvent {
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, view);
        init();
        initViews();
        initEvents();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initEvents();

    protected abstract void init();

    @Override
    public void doClick(View view) {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
