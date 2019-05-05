package com.heyskill.element;

import android.app.Application;

import com.hc.element_ec.icon.FontEcModel;
import com.heyskill.element_core.appConfig.ConfigFlag;
import com.heyskill.element_core.appConfig.ConfigGet;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class elementApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ConfigGet.init(this)
                .withHostApi("http://mock.fulingjie.com/mock-android/")
                .withIcons(new FontAwesomeModule())
                .withIcons(new FontEcModel())
                .configure();
    }
}
