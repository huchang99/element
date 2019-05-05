package com.heyskill.element_core.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heyskill.element_core.activities.BaseActivity;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

public abstract class BaseFragment extends SwipeBackFragment {

    //传入一个Layout
    public abstract Object setLayout();

    public abstract void onBindView(Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }
        if (rootView != null) {
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    public final BaseActivity getBaseActivity() {
        return (BaseActivity) _mActivity;
    }

    //得到当前Fragment的父容器
    public <T extends BaseFragment> T getCurrentParentFragment() {
        return (T) getParentFragment();
    }
}
