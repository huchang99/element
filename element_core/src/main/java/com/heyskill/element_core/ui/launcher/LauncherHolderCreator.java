package com.heyskill.element_core.ui.launcher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.heyskill.element_core.R;

public class LauncherHolderCreator implements CBViewHolderCreator{
    @Override
    public Holder createHolder(View itemView) {
        return new LauncherHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.launcher_item_image;
    }
}
