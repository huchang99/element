package com.heyskill.element_core.utils.timer;

import java.util.TimerTask;

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if(mITimerListener != null){
            mITimerListener.onTime();
        }
    }
}
