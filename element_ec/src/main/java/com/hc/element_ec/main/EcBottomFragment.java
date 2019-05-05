package com.hc.element_ec.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.hc.element_ec.main.index.IndexFragment;
import com.heyskill.element_core.fragments.BaseFragment;
import com.heyskill.element_core.fragments.bottom.BaseBottomFragment;
import com.heyskill.element_core.fragments.bottom.BottomItemFragment;
import com.heyskill.element_core.fragments.bottom.BottomTabBean;
import com.heyskill.element_core.fragments.bottom.ItemBuilder;

import java.util.LinkedHashMap;

public class EcBottomFragment extends BaseBottomFragment {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemFragment> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexFragment());
        items.put(new BottomTabBean("{fa-sort}","分类"),new IndexFragment());
        items.put(new BottomTabBean("{fa-compass}","发现"),new IndexFragment());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new IndexFragment());
        items.put(new BottomTabBean("{fa-user}","我的"),new IndexFragment());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexFragment() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }
}
