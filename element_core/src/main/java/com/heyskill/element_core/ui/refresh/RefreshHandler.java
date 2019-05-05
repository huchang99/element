package com.heyskill.element_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heyskill.element_core.R;
import com.heyskill.element_core.appConfig.ConfigGet;
import com.heyskill.element_core.net.RuestClient;
import com.heyskill.element_core.net.callback.ISuccess;
import com.heyskill.element_core.ui.recycle.DataConverter;
import com.heyskill.element_core.ui.recycle.MultipleRecyclerAdapter;

import java.util.logging.Handler;

import static com.heyskill.element_core.appConfig.ConfigGet.getHandler;

/**
 * 监听刷新事件的操作
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLEVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;


    public RefreshHandler(SwipeRefreshLayout refresh_layout,
                          RecyclerView recyclerView,
                          DataConverter converter,
                          PagingBean bean) {
        REFRESH_LAYOUT = refresh_layout;
        RECYCLEVIEW = recyclerView;
        CONVERTER = converter;
        BEAN = bean;
        //在构造方法中监听滑动事件
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout refresh_layout,
                                        RecyclerView recyclerView,
                                        DataConverter converter){
        return new RefreshHandler(refresh_layout,recyclerView,converter,new PagingBean());
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        ConfigGet.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    //第一次加载网络页面的网络请求
    public  void firstPage(String url){
        BEAN.setDelayed(1000);
        RuestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLEVIEW);
                        RECYCLEVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                        // Toast.makeText(Latte.getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }


    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}