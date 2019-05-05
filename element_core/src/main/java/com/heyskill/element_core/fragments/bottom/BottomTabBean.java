package com.heyskill.element_core.fragments.bottom;

/**
 * 底部导航栏按钮的封装
 */

public class BottomTabBean {

    private final CharSequence ICON; //字体图标
    private final CharSequence TITLE;


    public BottomTabBean(CharSequence icon, CharSequence title) {
        ICON = icon;
        TITLE = title;
    }

    public CharSequence getIcon(){
        return ICON;
    }

    public CharSequence getTitle(){
        return TITLE;
    }
}
