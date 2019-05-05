package com.heyskill.element_core.ui.recycle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;

public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    //处理加入的键值对，保证顺序
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();

    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE = new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUEUE);

    public MultipleItemEntity(LinkedHashMap<Object,Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    /**
     * 控制RecycleView的样式和特征
     *
     * @return
     */
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultupleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key) {
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    //返回整个数据
    public final LinkedHashMap<?, ?> getFields() {
        return FIELDS_REFERENCE.get();
    }

    //    public final MultipleItemEntity setField(Object key,Object value){
//        FIELDS_REFERENCE.get().put(key,value);
//        return this;
//    }
    public static class Builder {

        private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

        public Builder() {
            //清除之前的数据
            FIELDS.clear();
        }


        public final Builder setItemType(int itemType) {
            FIELDS.put(MultupleFields.ITEM_TYPE, itemType);
            return this;
        }

        //存储
        public final Builder setField(Object key, Object value) {
            FIELDS.put(key, value);
            return this;
        }

        public final Builder setFields(WeakHashMap<?, ?> map) {
            FIELDS.putAll(map);
            return this;
        }

        public final MultipleItemEntity build() {
            return new MultipleItemEntity(FIELDS);
        }

    }

}
