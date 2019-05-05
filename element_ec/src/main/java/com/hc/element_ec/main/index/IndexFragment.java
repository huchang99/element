package com.hc.element_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hc.element_ec.R;
import com.hc.element_ec.main.EcBottomFragment;
import com.heyskill.element_core.fragments.bottom.BottomItemFragment;
import com.heyskill.element_core.ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;


public class IndexFragment extends BottomItemFragment {

    private RecyclerView mRecyclerView = null;
    private SwipeRefreshLayout mSwipeRefreshLayout = null;
    private Toolbar mToolbar = null;
    private IconTextView mIconScanView = null;
    private AppCompatEditText mSearchView = null;
    private RefreshHandler mRefreshHandler = null;



    @Override
    public Object setLayout() {
        return R.layout.index_fragment;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initViewId(rootView);
        mRefreshHandler = RefreshHandler.create(mSwipeRefreshLayout,mRecyclerView,new IndexDataConverter());
    }

    private void initViewId(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.rv_index);
        mSwipeRefreshLayout = rootView.findViewById(R.id.srl_index);
        mToolbar = rootView.findViewById(R.id.tb_index);
        mIconScanView = rootView.findViewById(R.id.icon_index_scan);
        mSearchView = rootView.findViewById(R.id.et_search_view);
    }


    private void initRecyclerView(){
        GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(manager);
        //设置点击事件,获取父布局
        EcBottomFragment ecBottomFragment = getCurrentParentFragment();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomFragment));

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("api");

    }

    //初始化下拉刷新控件,设置颜色
    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        //true球由小变大
        mSwipeRefreshLayout.setProgressViewOffset(true, 120, 300);
    }
}
