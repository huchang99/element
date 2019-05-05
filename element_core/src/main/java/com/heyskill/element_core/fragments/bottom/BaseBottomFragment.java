package com.heyskill.element_core.fragments.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.heyskill.element_core.R;
import com.heyskill.element_core.fragments.BaseFragment;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 组装页面
 */

public abstract class BaseBottomFragment extends BaseFragment implements View.OnClickListener {

    //控件
    private LinearLayoutCompat mBotterBar;
    //保存TAB的内容
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    //保存每个Tab对应的Fragment
    private final ArrayList<BottomItemFragment> ITEMS_FRAGMENT = new ArrayList<>();

    //定义一个LinkHashMap接受传过来的页面数据
    private final LinkedHashMap<BottomTabBean, BottomItemFragment> ITEMS = new LinkedHashMap<>();
    //记录当前的Fragment
    private int mCurrentFragment = 0;
    //item默认的颜色
    private int mIndexFragment = 0;
    //item默认的颜色
    private int mClickColor = Color.RED;

    //对子类提供的设置的抽象方法
    //让子类传入布局所需要的按钮和布局
    public abstract LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(ItemBuilder builder);

    //让子类传入设置首次加载的主页
    public abstract int setIndexFragment();

    //让子类传入设置点击之后按钮的颜色
    public abstract int setClickColor();

    @Override
    public Object setLayout() {
        return R.layout.bottom_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexFragment = setIndexFragment();
        if (setClickColor() != 0) {
            mClickColor = setClickColor();
        }
        ItemBuilder builder = ItemBuilder.builder();
        LinkedHashMap<BottomTabBean, BottomItemFragment> items = setItems(builder);
        ITEMS.putAll(items);
        //for循环取出ITEMS中键值对的值
        for (Map.Entry<BottomTabBean, BottomItemFragment> item : ITEMS.entrySet()) {
            BottomTabBean key = item.getKey();
            BottomItemFragment value = item.getValue();
            TAB_BEANS.add(key);
            ITEMS_FRAGMENT.add(value);
        }
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        int size = ITEMS.size();
        initViewId(rootView);
        //循环填充底部的布局，比较核心的内容
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBotterBar);
            final RelativeLayout item = (RelativeLayout) mBotterBar.getChildAt(i);
            //设置每个Item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            if (i == mIndexFragment) {
                itemIcon.setTextColor(mClickColor);
                itemTitle.setTextColor(mClickColor);
            }
        }
        //初始化每个Tab对应的界面
        //将fragments的ArrayList转化为SupportsFragment的数组，框架需要
        final SupportFragment[] FragmentArray = ITEMS_FRAGMENT.toArray(new SupportFragment[size]);
        //Fragmentation提供的设置方法
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexFragment, FragmentArray);

    }

    //重置按钮的颜色
    private void resetColor() {
        final int count = mBotterBar.getChildCount();
        for (int i = 0; i < count; i++) {
            RelativeLayout item = (RelativeLayout) mBotterBar.getChildAt(i);
            IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);
            AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }


    private void initViewId(View rootView) {
        mBotterBar = rootView.findViewById(R.id.bottom_bar);
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        resetColor();
        RelativeLayout item = (RelativeLayout) v;
        IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickColor);
        AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickColor);
        //展示和隐藏Fragment 参数一位要展示的，参数二为要隐藏的
        showHideFragment(ITEMS_FRAGMENT.get(tag),ITEMS_FRAGMENT.get(mCurrentFragment));
        mCurrentFragment = tag;
    }
}
