package com.shaoyue.weizhegou.module.owner.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.user.CommissionListBean;
import com.shaoyue.weizhegou.module.owner.adapter.CommissionDetailAdapter;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 作者：PangLei on 2019/5/28 0028 15:17
 * <p>
 * 邮箱：434604925@qq.com
 */
public class RechargeDetailsFragment extends BaseTitleFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {


    private int page = 1;
    private int total = 0;
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
    CommissionDetailAdapter mCouponAdapter;
    ;
    private String state = "1";
    private int per_page = 20;

    public static RechargeDetailsFragment newInstance() {

        Bundle args = new Bundle();

        RechargeDetailsFragment fragment = new RechargeDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_recharge_details;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("充值明细");
        initView();
        mEmptyRelative.setBackgroundResource(R.color.white);

    }

    /**
     * 初始化View
     */
    private void initView() {

        mCouponAdapter = new CommissionDetailAdapter();
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
    protected void loadData() {
        super.loadData();
        initCouponList();
    }

    private void initCouponList() {
        page = 1;
        UserApi.getAllDetail(state, page, "1,2", new BaseCallback<BaseResponse<CommissionListBean>>() {
            @Override
            public void onSucc(BaseResponse<CommissionListBean> result) {
                total = result.data.getTotal();
                per_page = result.data.getPer_page();
                mCouponAdapter.setNewData(result.data.getData());
                if (result.data.getData().size() <= 0 || ObjectUtils.isEmpty(result.data.getData())) {
                    mEmptyRelative.setVisibility(View.VISIBLE);
                    mEmptyText.setText("暂无充值明细" + ToastUtil.getToastMsg());
                    mEmptyImg.setImageResource(R.drawable.icon_empty_wallet);
                } else {
                    mEmptyRelative.setVisibility(View.GONE);
                }
            }
        }, this);
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
}
