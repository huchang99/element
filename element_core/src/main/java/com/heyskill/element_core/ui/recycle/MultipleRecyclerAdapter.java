package com.heyskill.element_core.ui.recycle;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heyskill.element_core.R;
import com.heyskill.element_core.ui.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity,MutipleViewHolder> implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {

    //确保初始化一次banner,f防止重复的Item加载
    private boolean mIsInitBanner = false;

    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data){
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter){
        return new MultipleRecyclerAdapter(converter.convert());
    }

    private void init(){
        //设置不同的布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_mutilple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_mutiple_banner);

        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected void convert(MutipleViewHolder holder, MultipleItemEntity entity) {
        String text;
        String imgerUrl;
        ArrayList<String> bannersImage;
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entity.getField(MultupleFields.TEXT);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imgerUrl = entity.getField(MultupleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imgerUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.img_single));
                break;
            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultupleFields.TEXT);
                imgerUrl = entity.getField(MultupleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imgerUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.img_multiple));
                holder.setText(R.id.tv_multiple, text);
                break;
            case ItemType.BANNER:
                if(!mIsInitBanner){
                    bannersImage = entity.getField(MultupleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner,bannersImage,this);
                    //初始化成功
                    mIsInitBanner = true;
                }
                break;

            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultupleFields.SPAN_SIZE);
    }

    @Override
    protected MutipleViewHolder createBaseViewHolder(View view) {
        return MutipleViewHolder.create(view);
    }

    @Override
    public void onItemClick(int position) {

    }
}
