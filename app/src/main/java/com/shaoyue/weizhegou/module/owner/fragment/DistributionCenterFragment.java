package com.shaoyue.weizhegou.module.owner.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseCustomFragment;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.entity.user.DistributionNumBean;
import com.shaoyue.weizhegou.entity.user.UserInfoBean;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.XClick.SingleClick;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 作者：PangLei on 2019/5/20 0020 17:12
 * <p>
 * 邮箱：434604925@qq.com
 */
public class DistributionCenterFragment extends BaseCustomFragment {

    @BindView(R.id.tv_productCount_num)
    TextView mTvProductCountNum;
    @BindView(R.id.tv_sonSaleCount_num)
    TextView mTvSonSaleCountNum;
    @BindView(R.id.tv_sonUserCount_num)
    TextView mTvSonUserCountNum;
    @BindView(R.id.tv_orderCount_num)
    TextView mTvOrderCountNum;
    @BindView(R.id.tv_commission_amount)
    TextView mTvCommissionAmount;
    @BindView(R.id.tv_my_superior)
    TextView mTvMySuperior;
    @BindView(R.id.tv_revenue_price)
    TextView mTvRevenuePrice;
    @BindView(R.id.tv_withdraw)
    TextView mTvWithdraw;

    private String[] mDistributorTitles = {"所有分销商", "一级", "二级"};
    private String[] mMemberTitles = {"所有会员", "一级", "二级", "三级"};


    public static DistributionCenterFragment newInstance() {

        Bundle args = new Bundle();

        DistributionCenterFragment fragment = new DistributionCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_distribution_center;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshUser(UserInfoBean userInfoBean) {
        if (userInfoBean.getWithdraw_auth().equals("1")) {
            mTvWithdraw.setVisibility(View.VISIBLE);
        } else {
            mTvWithdraw.setVisibility(View.INVISIBLE);
        }

        if (!"0".equals(userInfoBean.getReferrer_id())) {
            mTvMySuperior.setVisibility(View.VISIBLE);
        }
        if (ObjectUtils.isNotEmpty(userInfoBean.getCommission_balance())) {
            mTvCommissionAmount.setText("¥ " + userInfoBean.getCommission_balance());
        }

        if (ObjectUtils.isNotEmpty(userInfoBean.getTo_sum_amount())) {
            mTvRevenuePrice.setText("待收益：¥" + userInfoBean.getTo_sum_amount());
        }

    }


    private void initView() {
        UserApi.getDistributionCenterNum(new BaseCallback<BaseResponse<DistributionNumBean>>() {
            @Override
            public void onSucc(BaseResponse<DistributionNumBean> result) {
                if (result.data.getProductCount() == 0) {
                    mTvProductCountNum.setVisibility(View.INVISIBLE);
                } else {
                    mTvProductCountNum.setVisibility(View.VISIBLE);
                    if (result.data.getProductCount() <= 99) {
                        mTvProductCountNum.setText(result.data.getProductCount() + "");
                    } else {
                        mTvProductCountNum.setText("99+");
                    }
                }
                if (result.data.getOrderCount().getAll() == 0) {
                    mTvOrderCountNum.setVisibility(View.INVISIBLE);
                } else {
                    mTvOrderCountNum.setVisibility(View.VISIBLE);
                    if (result.data.getOrderCount().getAll() <= 99) {
                        mTvOrderCountNum.setText(result.data.getOrderCount().getAll() + "");
                    } else {
                        mTvOrderCountNum.setText("99+");
                    }
                }

                if (result.data.getSonSaleCount().getAll() == 0) {
                    mTvSonSaleCountNum.setVisibility(View.INVISIBLE);
                } else {
                    mTvSonSaleCountNum.setVisibility(View.VISIBLE);
                    if (result.data.getSonSaleCount().getAll() <= 99) {
                        mTvSonSaleCountNum.setText(result.data.getSonSaleCount().getAll() + "");
                    } else {
                        mTvSonSaleCountNum.setText("99+");
                    }
                }
                if (result.data.getSonUserCount().getAll() == 0) {
                    mTvSonUserCountNum.setVisibility(View.INVISIBLE);
                } else {
                    mTvSonUserCountNum.setVisibility(View.VISIBLE);
                    if (result.data.getSonUserCount().getAll() <= 99) {
                        mTvSonUserCountNum.setText(result.data.getSonUserCount().getAll() + "");
                    } else {
                        mTvSonUserCountNum.setText("99+");
                    }
                }
                mDistributorTitles[0] = "所有分销商(" + result.data.getSonSaleCount().getAll() + ")";
                mDistributorTitles[1] = "一级(" + result.data.getSonSaleCount().getOne() + ")";
                mDistributorTitles[2] = "二级(" + result.data.getSonSaleCount().getTwo() + ")";
                mMemberTitles[0] = "所有会员(" + result.data.getSonUserCount().getAll() + ")";
                mMemberTitles[1] = "一级(" + result.data.getSonUserCount().getOne() + ")";
                mMemberTitles[2] = "二级(" + result.data.getSonUserCount().getTwo() + ")";
                mMemberTitles[3] = "三级(" + result.data.getSonUserCount().getThree() + ")";
            }

            @Override
            public void onFail(ApiException apiError) {

            }
        }, this);
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @SingleClick
    @OnClick({R.id.tv_shop_qc_code, R.id.tv_my_superior, R.id.rl_go_commission_details, R.id.tv_withdraw, R.id.tv_sonUserCount,
            R.id.tv_pending_waiting_for_distributor, R.id.tv_pending_waiting_for_distribution_products})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_shop_qc_code:
                UIHelper.showShareQcDialog(getActivity());
                break;
            case R.id.tv_my_superior:

                break;
            case R.id.rl_go_commission_details:
            case R.id.tv_withdraw:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.COMMISSION_DETAILS);
                break;
            case R.id.tv_sonUserCount:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.DISTRIBUTOR_MEMBER, new profileBean("0", mMemberTitles));
                break;
            case R.id.tv_pending_waiting_for_distributor:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.DISTRIBUTOR, new profileBean("0", mDistributorTitles));
                break;
            case R.id.tv_pending_waiting_for_distribution_products:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.DISTRIBUTOR_PRODUCTS);
                break;
        }
    }



}
