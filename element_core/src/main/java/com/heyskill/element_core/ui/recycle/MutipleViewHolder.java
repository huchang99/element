package com.heyskill.element_core.ui.recycle;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

public class MutipleViewHolder extends BaseViewHolder {

    public MutipleViewHolder(View view) {
        super(view);
    }

    public static MutipleViewHolder create(View view){
        return new MutipleViewHolder(view);
    }
}
