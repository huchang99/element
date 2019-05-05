package com.heyskill.element_core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.heyskill.element_core.R;
import com.heyskill.element_core.fragments.BaseFragment;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivity extends SupportActivity {

    public abstract BaseFragment setRootFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    @SuppressLint("RestrictedApi")
    private void initContainer(@Nullable  Bundle savedInstanceState){
       ContentFrameLayout container = new ContentFrameLayout(this);
       container.setId(R.id.fragment_container);
       setContentView(container);
       if(savedInstanceState == null){
           loadRootFragment(R.id.fragment_container,setRootFragment());
       }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
