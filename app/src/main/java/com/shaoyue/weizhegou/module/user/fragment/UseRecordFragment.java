package com.shaoyue.weizhegou.module.user.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;


import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.SpeedApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.common.CommConfig;
import com.shaoyue.weizhegou.entity.UseRecordBean;
import com.shaoyue.weizhegou.entity.UseRecordList;
import com.shaoyue.weizhegou.module.user.adapter.UseRecordAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class UseRecordFragment extends BaseTitleFragment {

    @BindView(com.shaoyue.weizhegou.R.id.ll_list)
    LinearLayout mListLayout;

    @BindView(com.shaoyue.weizhegou.R.id.ll_empty)
    LinearLayout mEmptyLayout;


    @BindView(com.shaoyue.weizhegou.R.id.rv_record)
    RecyclerView mUseRecordView;

    @BindView(com.shaoyue.weizhegou.R.id.refresh_layout)
    SmartRefreshLayout mRereshLayout;

    private List<UseRecordBean> mUseRecordData = new ArrayList<>();

    private UseRecordAdapter mUseRecorderAdapter;

    private int mPageNum = 1;

    private boolean isFirstStart = true;

    public static UseRecordFragment newInstance() {

        Bundle args = new Bundle();

        UseRecordFragment fragment = new UseRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return com.shaoyue.weizhegou.R.layout.fragment_use_record;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle(com.shaoyue.weizhegou.R.string.title_use_records);
        initRefreshView();

        initListView();
    }

    @Override
    protected void loadData() {
        startProgressDialog(false);
        fetchNewListData();
    }

    private void initRefreshView() {
        mRereshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                fetchNewListData();
            }
        });
        mRereshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                fetchMoreListData();
            }
        });
    }

    private void initListView() {
        mUseRecorderAdapter = new UseRecordAdapter(mUseRecordData);
        mUseRecorderAdapter.setEnableLoadMore(false);
        mUseRecordView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mUseRecordView.setAdapter(mUseRecorderAdapter);
    }


    private void fetchNewListData() {
        mPageNum = 1;
        SpeedApi.fetchUseRecord(mPageNum, CommConfig.LIST_PAGE_SIZE, new BaseCallback<BaseResponse<UseRecordList>>() {
            @Override
            public void onSucc(BaseResponse<UseRecordList> result) {
                if (isFirstStart) {
                    stopProgressDialog();
                    isFirstStart = false;
                }
                mRereshLayout.finishRefresh(2000);
                UseRecordList lineList = result.data;
                if (lineList.getListData() == null || lineList.getListData().size() == 0) {
                    updateListView();
                    return;
                }
                mUseRecordData.clear();
                mUseRecordData.addAll(lineList.getListData());
                mUseRecorderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(ApiException apiError) {
                if (isFirstStart) {
                    stopProgressDialog();
                    isFirstStart = false;
                }
                mRereshLayout.finishRefresh(2000);
                updateListView();
            }
        }, this);
    }

    private void fetchMoreListData() {
        SpeedApi.fetchUseRecord(mPageNum + 1, CommConfig.LIST_PAGE_SIZE, new BaseCallback<BaseResponse<UseRecordList>>() {
            @Override
            public void onSucc(BaseResponse<UseRecordList> result) {
                mRereshLayout.finishLoadMore(2000);
                UseRecordList lineList = result.data;
                if (lineList.getListData() == null || lineList.getListData().size() == 0) {
                    updateListView();
                    return;
                }
                mPageNum += 1;
                mUseRecordData.addAll(lineList.getListData());
                mUseRecorderAdapter.notifyDataSetChanged();
                updateListView();
            }

            @Override
            public void onFail(ApiException apiError) {
                mRereshLayout.finishLoadMore(2000);
                updateListView();
            }
        }, this);
    }


    private void updateListView() {
        if (mUseRecordData.size() == 0) {
            mEmptyLayout.setVisibility(View.VISIBLE);
            mListLayout.setVisibility(View.GONE);
        } else {
            mEmptyLayout.setVisibility(View.GONE);
            mListLayout.setVisibility(View.VISIBLE);
        }

    }


}
