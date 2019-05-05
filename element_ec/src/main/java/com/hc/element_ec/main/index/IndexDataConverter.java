package com.hc.element_ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.heyskill.element_core.ui.recycle.DataConverter;
import com.heyskill.element_core.ui.recycle.ItemType;
import com.heyskill.element_core.ui.recycle.MultipleItemEntity;
import com.heyskill.element_core.ui.recycle.MultupleFields;

import java.util.ArrayList;

public class IndexDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = dataArray.size();
        for(int i = 0;i<size;i++){
            JSONObject data = dataArray.getJSONObject(i);

            String imageUrl = data.getString("imageUrl");
            String text = data.getString("text");
            int spanSize = data.getInteger("spanSize");
            int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            //存储Banner
            final ArrayList<String> bannerImages = new ArrayList<>();//存储Banner
            int type = 0;
            if (imageUrl == null && text != null) { //文字
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {//图片
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {//有图有文字
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                //Banner的初始化
                int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }
            //初始化MultipleItemEntity
            MultipleItemEntity entity = new MultipleItemEntity.Builder()
                    .setField(MultupleFields.ITEM_TYPE, type)
                    .setField(MultupleFields.SPAN_SIZE, spanSize)
                    .setField(MultupleFields.ID, id)
                    .setField(MultupleFields.TEXT, text)
                    .setField(MultupleFields.IMAGE_URL,imageUrl)
                    .setField(MultupleFields.BANNERS, bannerImages)
                    .build();
            ENTITIES.add(entity);

        }
        return ENTITIES;
    }
}
