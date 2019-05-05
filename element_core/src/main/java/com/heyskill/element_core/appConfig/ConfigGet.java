package com.heyskill.element_core.appConfig;

import android.content.Context;
import android.os.Handler;

public final class ConfigGet {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getElementConfigs()
                .put(ConfigFlag.APPLICATION_CONTEXT,context);
        return Configurator.getInstance();
    }



    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
    }

    public static Handler getHandler(){
        return getConfiguration(ConfigFlag.HANDLER);
    }


}
