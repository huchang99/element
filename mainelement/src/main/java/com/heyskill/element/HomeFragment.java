package com.heyskill.element;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Toast;

import com.heyskill.element_core.fragments.BaseFragment;
import com.heyskill.element_core.net.RuestClient;
import com.heyskill.element_core.net.callback.IError;
import com.heyskill.element_core.net.callback.IFailure;
import com.heyskill.element_core.net.callback.ISuccess;

/**
 * 测试页
 */

public class HomeFragment extends BaseFragment {

    private SwipeRefreshLayout refreshBt;

    @Override
    public Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initViewId(rootView);
        initRefreshLayout();
        //testRestClient();
    }

    private void testRestClient(){
        RuestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/api")
                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String reponse) {
                     //   Toast.makeText(getContext(),reponse,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                })
                .build()
                .get();
    }

    private void initViewId(View rootView){
        refreshBt = rootView.findViewById(R.id.refreshLayout);
    }

    private void initRefreshLayout(){
        refreshBt.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        refreshBt.setProgressViewOffset(true,120,300);
        refreshBt.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshBt.setRefreshing(false);
                    }
                },2000);
            }
        });
    }



}
