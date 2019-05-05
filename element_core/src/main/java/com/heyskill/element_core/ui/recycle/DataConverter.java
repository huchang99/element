package com.heyskill.element_core.ui.recycle;


import java.util.ArrayList;

/**
 * 数据转换约束
 */
public abstract class DataConverter {

    //存储每一条数据的信息
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    //解析JSON数据我们所需要的格式MultipleItemEntity，由子类来实现
    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String mJsonData) {
        this.mJsonData = mJsonData;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }
}
