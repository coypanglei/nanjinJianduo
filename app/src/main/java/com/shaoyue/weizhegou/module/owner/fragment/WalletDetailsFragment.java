package com.shaoyue.weizhegou.module.owner.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseNoStatusFragment;
import com.shaoyue.weizhegou.entity.user.CommissionListBean;
import com.shaoyue.weizhegou.entity.user.WalletDetailsBean;
import com.shaoyue.weizhegou.module.owner.adapter.WalletDetailAdapter;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 作者：PangLei on 2019/5/29 0029 09:16
 * <p>
 * 邮箱：434604925@qq.com
 */
public class WalletDetailsFragment extends BaseNoStatusFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.tv_wallet_balance)
    TextView mTvWalletBalance;
    @BindView(R.id.tv_total_income)
    TextView mTvTotalIncome;
    @BindView(R.id.tv_shopping_consumption)
    TextView mTvShoppingConsumption;

    private int page = 1;
    private int total = 0;
    private String state = "1";

    @BindView(R.id.empty_img)
    ImageView mEmptyImg;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;
    WalletDetailAdapter mCouponAdapter;
    private int per_page = 20;

    public static WalletDetailsFragment newInstance() {

        Bundle args = new Bundle();

        WalletDetailsFragment fragment = new WalletDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wallet_details;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        initView();
    }

    @Override
    protected void loadData() {
        super.loadData();
        initCouponList();
    }

    /**
     * 初始化View
     */
    private void initView() {
        UserApi.getGetWalletDetails(new BaseCallback<BaseResponse<WalletDetailsBean>>() {
            @Override
            public void onSucc(BaseResponse<WalletDetailsBean> result) {
                mTvWalletBalance.setText(result.data.getBalance());
                mTvTotalIncome.setText("全部收入：" + result.data.getIn_amount());
                mTvShoppingConsumption.setText("购物消费：" + result.data.getOut_amount());
            }
        }, this);

        mCouponAdapter = new WalletDetailAdapter();
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContent.setAdapter(mCouponAdapter);
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        mRefreshLayout.setIsShowLoadingMoreView(true);

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
                initCouponList();
            }
        }, 1000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        int size = (total / per_page) + 1;
        if (page == size) {
            mRefreshLayout.endLoadingMore();
            ToastUtil.showErrorToast("没有更多的数据了");
            return false;
        }

        UserApi.getAllDetail(state, page + 1, new BaseCallback<BaseResponse<CommissionListBean>>() {
            @Override
            public void onSucc(final BaseResponse<CommissionListBean> result) {
                total = result.data.getTotal();
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mRefreshLayout == null) {
                            return;
                        }
                        mRefreshLayout.endLoadingMore();
                        mCouponAdapter.addData(result.data.getData());
                        page++;
                    }
                }, 1000);
            }
        }, this);

        return true;
    }

    private void initCouponList() {
        page = 1;

        UserApi.getAllDetail(state, page, new BaseCallback<BaseResponse<CommissionListBean>>() {
            @Override
            public void onSucc(BaseResponse<CommissionListBean> result) {
                total = result.data.getTotal();
                per_page = result.data.getPer_page();
                mCouponAdapter.setNewData(result.data.getData());
                if (result.data.getData().size() > 0) {
                    mEmptyRelative.setVisibility(View.GONE);
                } else {
                    mEmptyRelative.setVisibility(View.VISIBLE);
                    mEmptyText.setText("暂无余额变动" + ToastUtil.getToastMsg());
                    mEmptyImg.setImageResource(R.drawable.icon_empty_wallet);
                }
            }
        }, this);
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        removeFragment();
    }


}
