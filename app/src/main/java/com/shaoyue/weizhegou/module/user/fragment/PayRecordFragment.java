package com.shaoyue.weizhegou.module.user.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.libracore.lib.widget.StateButton;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.PayApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.common.CommConfig;
import com.shaoyue.weizhegou.entity.PayRecordBean;
import com.shaoyue.weizhegou.entity.PayRecordList;
import com.shaoyue.weizhegou.module.user.adapter.PayRecordAdapter;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/30 12:04
 */
public class PayRecordFragment extends BaseTitleFragment {

    @BindView(R.id.ll_list)
    LinearLayout mListLayout;

    @BindView(R.id.ll_empty)
    LinearLayout mEmptyLayout;

    @BindView(R.id.sb_go_pay)
    StateButton mGoPayButton;

    @BindView(R.id.rv_record)
    RecyclerView mPayRecordView;

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRereshLayout;

    private List<PayRecordBean> mPayRecordData = new ArrayList<>();

    private PayRecordAdapter mPayRecordAdapter;

    private int mPageNum = 1;

    private boolean isFirstStart = true;

    public static PayRecordFragment newInstance() {
        return new PayRecordFragment();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_pay_record;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle(R.string.title_purchase_record);

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
        mPayRecordAdapter = new PayRecordAdapter(mPayRecordData);
        mPayRecordAdapter.setEnableLoadMore(false);
        mPayRecordView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPayRecordView.setAdapter(mPayRecordAdapter);
    }


    private void fetchNewListData() {
        mPageNum = 1;
        PayApi.fetchPayRecord(mPageNum, CommConfig.LIST_PAGE_SIZE, new BaseCallback<BaseResponse<PayRecordList>>() {
            @Override
            public void onSucc(BaseResponse<PayRecordList> result) {
                if (isFirstStart) {
                    stopProgressDialog();
                    isFirstStart = false;
                }
                mRereshLayout.finishRefresh(2000);
                PayRecordList lineList = result.data;
                if (lineList.getListData() == null || lineList.getListData().size() == 0) {
                    updateListView();
                    return;
                }
                mPayRecordData.clear();
                mPayRecordData.addAll(lineList.getListData());
                mPayRecordAdapter.notifyDataSetChanged();
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
        PayApi.fetchPayRecord(mPageNum + 1, CommConfig.LIST_PAGE_SIZE, new BaseCallback<BaseResponse<PayRecordList>>() {
            @Override
            public void onSucc(BaseResponse<PayRecordList> result) {
                mRereshLayout.finishLoadMore(2000);
                PayRecordList lineList = result.data;
                if (lineList.getListData() == null || lineList.getListData().size() == 0) {
                    updateListView();
                    return;
                }
                mPageNum += 1;
                mPayRecordData.addAll(lineList.getListData());
                mPayRecordAdapter.notifyDataSetChanged();
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
        if (mPayRecordData.size() == 0) {
            mEmptyLayout.setVisibility(View.VISIBLE);
            mListLayout.setVisibility(View.GONE);
        } else {
            mEmptyLayout.setVisibility(View.GONE);
            mListLayout.setVisibility(View.VISIBLE);
        }

    }


    @OnClick({R.id.sb_go_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_go_pay:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.PAY_PACKAGE);
//                String packageUrl = AppMgr.getInstance().getH5PayUrl() + "?session=" +
//                        UserMgr.getInstance().getSessionId() + "&username=" + UserMgr.getInstance().getUsername();
//                addFragment(WebFragment.newInstance("套餐购买", packageUrl));
                break;
        }
    }


}
