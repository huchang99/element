package com.heyskill.element;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.hc.element_ec.main.EcBottomFragment;
import com.hc.element_ec.main.index.IndexFragment;
import com.hc.element_ec.sign.SignInFragment;
import com.hc.element_ec.sign.SignUpFragment;
import com.heyskill.element_core.activities.BaseActivity;
import com.heyskill.element_core.fragments.BaseFragment;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }

    @Override
    public BaseFragment setRootFragment() {
        //return new LauncherFragment();
       // return new SignInFragment();
        return new EcBottomFragment();
       // return  new IndexFragment();
    }
}
