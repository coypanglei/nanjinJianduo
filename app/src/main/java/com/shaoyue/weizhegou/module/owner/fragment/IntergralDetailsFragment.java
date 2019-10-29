package com.shaoyue.weizhegou.module.owner.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseNoStatusFragment;
import com.shaoyue.weizhegou.entity.user.CommissionListBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.owner.adapter.IntergralDetailAdapter;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 作者：PangLei on 2019/5/28 0028 15:17
 * <p>
 * 邮箱：434604925@qq.com
 */
public class IntergralDetailsFragment extends BaseNoStatusFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.tv_integral)
    TextView mTvIntegral;
    Unbinder unbinder;
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
    IntergralDetailAdapter mCouponAdapter;
    private String state = "3";
    private int per_page = 20;

    public static IntergralDetailsFragment newInstance() {

        Bundle args = new Bundle();

        IntergralDetailsFragment fragment = new IntergralDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_integral_details;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        initView();
        mEmptyRelative.setBackgroundResource(R.color.white);
        mTvIntegral.setText(UserMgr.getInstance().getSpScore());
    }

    /**
     * 初始化View
     */
    private void initView() {
        mCouponAdapter = new IntergralDetailAdapter();
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
                    mEmptyText.setText("暂无积分明细"+ToastUtil.getToastMsg());
                    mEmptyImg.setImageResource(R.drawable.icon_empty_integral);
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

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        removeFragment();
    }
}
