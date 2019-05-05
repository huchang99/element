package com.heyskill.element_core.ui.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.heyskill.element_core.R;

public class ImageHolder extends Holder<String> {

    private ImageView mImageView;
    private Context mContext;

    public ImageHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
    }

    @Override
    protected void initView(View itemView) {
        mImageView = itemView.findViewById(R.id.item_imageholder);
    }

    @Override
    public void updateUI(String data) {
        Glide.with(mContext)
                .load(data)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .centerCrop()
                .into(mImageView);
    }
}
