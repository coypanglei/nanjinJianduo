package com.shaoyue.weizhegou.module.pay.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.PayApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.PayBean;
import com.shaoyue.weizhegou.entity.PayListBean;
import com.shaoyue.weizhegou.module.pay.adapter.PayListAdapter;
import com.shaoyue.weizhegou.router.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by USER on 2018/12/21.
 */

public class PayListFragment extends BaseTitleFragment {

    @BindView(R.id.iv_pay_text)
    ImageView mIvPayText;
    @BindView(R.id.tv_pay_price)
    TextView mTvPayPrice;
    @BindView(R.id.rl_bottom)
    RelativeLayout mRlBottom;


    private List<PayBean> mPayListData = new ArrayList<>();

    private PayListAdapter mPayListAdapter;

    private boolean isFirstStart = true;

    @BindView(R.id.rv_pay)
    RecyclerView mRvPay;

    private PayBean mPayBean;

    public static PayListFragment newInstance() {

        Bundle args = new Bundle();

        PayListFragment fragment = new PayListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_pay_list;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle(R.string.t_packag_purchase);
        initListView();
    }

    @Override
    protected void loadData() {
        startProgressDialog(true);
        getPayList();
    }

    private void initListView() {

        mPayListAdapter = new PayListAdapter(mPayListData);
        mPayListAdapter.setEnableLoadMore(false);
        mRvPay.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvPay.setAdapter(mPayListAdapter);
        mRvPay.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (PayBean mPayBean : mPayListData) {
                    mPayBean.setmDefault("");
                }
                mPayListData.get(position).setmDefault("true");
                mPayBean = mPayListData.get(position);
                mTvPayPrice.setText("￥ " + mPayListData.get(position).getPayPrice());
                mPayListAdapter.notifyDataSetChanged();

            }
        });


    }

    /**
     * 获取列表
     */
    public void getPayList() {
        PayApi.getPayPackageList(new BaseCallback<BaseResponse<PayListBean>>() {
            @Override
            public void onSucc(BaseResponse<PayListBean> result) {
                if (isFirstStart) {
                    stopProgressDialog();
                    isFirstStart = false;
                }
                mPayListData.clear();

                mPayListData.addAll(result.data.getmPayList());
                mPayListAdapter.notifyDataSetChanged();
                mRlBottom.setVisibility(View.VISIBLE);
                mIvPayText.setVisibility(View.VISIBLE);
                for (PayBean payListBean : mPayListData) {
                    if ("true".equals(payListBean.getmDefault())) {
                        mTvPayPrice.setText("￥ " + payListBean.getPayPrice());
                        mPayBean = payListBean;
                        break;
                    }
                }

            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                if (isFirstStart) {
                    stopProgressDialog();
                    isFirstStart = false;
                }
            }
        }, this);

    }


    @OnClick(R.id.tv_pay_btn)
    public void onViewClicked() {
        UIHelper.showpayPackage(getActivity(), mPayBean);
    }
}
