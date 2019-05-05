package com.hc.element_ec.launcher;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hc.element_ec.R;
import com.heyskill.element_core.fragments.BaseFragment;
import com.heyskill.element_core.ui.launcher.ScrollLauncherTag;
import com.heyskill.element_core.utils.storage.sharePreferenceUtil;
import com.heyskill.element_core.utils.timer.BaseTimerTask;
import com.heyskill.element_core.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import kotlin.jvm.internal.PropertyReference0Impl;


/**
 * 启动页
 */
public class LauncherFragment extends BaseFragment implements ITimerListener {

    private TextView tv_launcher_timer;
    private Timer mTimer = null;
    //倒计时的计数
    private int mCount = 5;

    @Override
    public Object setLayout() {
        return R.layout.element_launcher;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initViewId(rootView);
        initTimer();
    }

    private void initViewId(View rootView) {
        tv_launcher_timer = rootView.findViewById(R.id.tv_launcher_timer);
        tv_launcher_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                    checkIsShowScroll();
                }
            }
        });

    }
    //判断是否显示滑动启动页
    private void checkIsShowScroll(){
        if(!sharePreferenceUtil.getAppFlag(ScrollLauncherTag.HAS_FISRT_LAUNCHER_APP.name())){
            //如果之前没有启动过APP，就直接进入启动页。Fragmentation的用法
            start(new LauncherScrollFragment(),SINGLETASK);
        }
        else {
            //检查用户是否登录app
        }
    }

    //初始化计时器
    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onTime() {
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tv_launcher_timer != null) {
                    tv_launcher_timer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });

    }
}
