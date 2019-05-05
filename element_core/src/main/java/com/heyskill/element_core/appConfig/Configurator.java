package com.heyskill.element_core.appConfig;


import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

public class Configurator {

    private static final HashMap<Object, Object> ELEMENT_CONFIGS = new HashMap<>();
    //存储字体
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    //全局的Handler
    private  static final Handler HANDLER = new Handler();


    private Configurator() {
        //初始化配置为flase
        ELEMENT_CONFIGS.put(ConfigFlag.CONFIG_READY, false);
        //配置Handler
        ELEMENT_CONFIGS.put(ConfigFlag.HANDLER,HANDLER);
    }

    public final void configure() {
        ELEMENT_CONFIGS.put(ConfigFlag.CONFIG_READY, true);
        initIcons();
    }

    public final HashMap<Object, Object> getElementConfigs() {
        return ELEMENT_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public Configurator withHostApi(String hostApi) {
        ELEMENT_CONFIGS.put(ConfigFlag.HOST_API, hostApi);
        return this;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer iconifyInitializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                iconifyInitializer.with(ICONS.get(i));
            }
        }
    }

    public Configurator withIcons(IconFontDescriptor iconFontDescriptor) {
        ICONS.add(iconFontDescriptor);
        return this;
    }

    private void checkConfiguration() {
        boolean isReady = (boolean) ELEMENT_CONFIGS.get(ConfigFlag.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = ELEMENT_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + "IS NULL");
        }
        return (T) ELEMENT_CONFIGS.get(key);
    }


}
