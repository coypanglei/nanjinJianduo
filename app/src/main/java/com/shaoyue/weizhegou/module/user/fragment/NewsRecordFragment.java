package com.shaoyue.weizhegou.module.user.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;


import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AccountApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.common.CommConfig;
import com.shaoyue.weizhegou.entity.NewsBean;
import com.shaoyue.weizhegou.entity.NewsList;
import com.shaoyue.weizhegou.module.user.adapter.NewsRecordAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class NewsRecordFragment extends BaseTitleFragment {

    @BindView(R.id.ll_list)
    LinearLayout mListLayout;

    @BindView(R.id.ll_empty)
    LinearLayout mEmptyLayout;


    @BindView(R.id.rv_record)
    RecyclerView mUseRecordView;

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRereshLayout;

    private List<NewsBean> mNewsRecordData = new ArrayList<>();

    private NewsRecordAdapter mNewsRecorderAdapter;

    private int mPageNum = 1;

    private boolean isFirstStart = true;

    public static NewsRecordFragment newInstance() {

        Bundle args = new Bundle();

        NewsRecordFragment fragment = new NewsRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_news_record;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle(R.string.title_news_records);
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
        mNewsRecorderAdapter = new NewsRecordAdapter(mNewsRecordData);
        mNewsRecorderAdapter.setEnableLoadMore(false);
        mUseRecordView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mUseRecordView.setAdapter(mNewsRecorderAdapter);
    }


    private void fetchNewListData() {
        mPageNum = 1;
        AccountApi.getNewsList(mPageNum, CommConfig.LIST_PAGE_SIZE, new BaseCallback<BaseResponse<NewsList>>() {
            @Override
            public void onSucc(BaseResponse<NewsList> result) {
                if (isFirstStart) {
                    stopProgressDialog();
                    isFirstStart = false;
                }
                mRereshLayout.finishRefresh(2000);
                NewsList lineList = result.data;
                if (lineList.getNewsBean() == null || lineList.getNewsBean().size() == 0) {
                    updateListView();
                    return;
                }
                mNewsRecordData.clear();
                mNewsRecordData.addAll(lineList.getNewsBean());
                mNewsRecorderAdapter.notifyDataSetChanged();
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
        AccountApi.getNewsList(mPageNum + 1, CommConfig.LIST_PAGE_SIZE, new BaseCallback<BaseResponse<NewsList>>() {
            @Override
            public void onSucc(BaseResponse<NewsList> result) {
                mRereshLayout.finishLoadMore(2000);
               NewsList lineList = result.data;
                if (lineList.getNewsBean() == null || lineList.getNewsBean().size() == 0) {
                    updateListView();
                    return;
                }
                mPageNum += 1;
                mNewsRecordData.addAll(lineList.getNewsBean());
                mNewsRecorderAdapter.notifyDataSetChanged();
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
        if (mNewsRecordData.size() == 0) {
            mEmptyLayout.setVisibility(View.VISIBLE);
            mListLayout.setVisibility(View.GONE);
        } else {
            mEmptyLayout.setVisibility(View.GONE);
            mListLayout.setVisibility(View.VISIBLE);
        }

    }


}
