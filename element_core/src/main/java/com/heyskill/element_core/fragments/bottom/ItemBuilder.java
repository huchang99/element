package com.heyskill.element_core.fragments.bottom;

import java.util.LinkedHashMap;

/**
 * 把每一个Fragment和底部的按钮对象进行绑定，组装成一个LinkedHashMap的集合
 */

public final class ItemBuilder {

    private  final LinkedHashMap<BottomTabBean,BottomItemFragment> ITEMS = new LinkedHashMap<>();

    //简单工厂模式
    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public  final  ItemBuilder addItem(BottomTabBean bean,BottomItemFragment fragment){
        ITEMS.put(bean,fragment);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemFragment> items){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean,BottomItemFragment> build(){
        return ITEMS;
    }


}
