package com.hc.element_ec.launcher;

import android.os.Bundle;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.hc.element_ec.R;
import com.heyskill.element_core.fragments.BaseFragment;
import com.heyskill.element_core.ui.launcher.LauncherHolderCreator;
import com.heyskill.element_core.ui.launcher.ScrollLauncherTag;
import com.heyskill.element_core.utils.storage.sharePreferenceUtil;

import java.util.ArrayList;

public class LauncherScrollFragment extends BaseFragment implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    //将banner的图片加入Arraylist
    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner.setPages(new LauncherHolderCreator(), INTEGERS)  //设置显示图
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})//设置显示的点
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//设置显示点的位置
                .setOnItemClickListener(this)
                .setCanLoop(false);//设置可循环

    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            //设置用户是否第一次登录的标志位
            sharePreferenceUtil.setAppFlag(ScrollLauncherTag.HAS_FISRT_LAUNCHER_APP.name(),true);


        }
    }
}
