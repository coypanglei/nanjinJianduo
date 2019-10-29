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
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AccountApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseCustomFragment;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.entity.user.OrderStatisticsBean;
import com.shaoyue.weizhegou.entity.user.UserInfoBean;
import com.shaoyue.weizhegou.entity.user.WebBean;
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
 * 作者：PangLei on 2019/5/20 0020 11:28
 * <p>
 * 邮箱：434604925@qq.com
 */
public class MemberCentreFragment extends BaseCustomFragment {

    @BindView(R.id.tv_commission_amount)
    TextView mTvCommissionAmount;
    @BindView(R.id.tv_integral)
    TextView mTvIntegral;
    @BindView(R.id.tv_unpaid_count)
    TextView mTvUnpaidCount;
    @BindView(R.id.tv_not_shipped_count)
    TextView mTvNotShippedCount;
    @BindView(R.id.tv_unreceived_count)
    TextView mTvUnreceivedCount;
    @BindView(R.id.tv_return_count)
    TextView mTvReturnCount;
    @BindView(R.id.tv_revenue_price)
    TextView mTvRevenuePrice;
    @BindView(R.id.tv_withdraw)
    TextView mTvWithdraw;



    public static MemberCentreFragment newInstance() {
        Bundle args = new Bundle();
        MemberCentreFragment fragment = new MemberCentreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_member_centre;
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshUser(UserInfoBean userInfoBean) {
        if(userInfoBean.getWithdraw_auth().equals("1")){
            mTvWithdraw.setVisibility(View.VISIBLE);
        }else {
            mTvWithdraw.setVisibility(View.INVISIBLE);
        }
        if (ObjectUtils.isNotEmpty(userInfoBean.getCommission_balance())) {
            mTvCommissionAmount.setText("¥ " + userInfoBean.getCommission_balance());
        }

        if (ObjectUtils.isNotEmpty(userInfoBean.getTo_sum_amount())) {
            mTvRevenuePrice.setText("待收益：¥" + userInfoBean.getTo_sum_amount());
        }

        AccountApi.getOrderStatistics(new BaseCallback<BaseResponse<OrderStatisticsBean>>() {
            @Override
            public void onSucc(BaseResponse<OrderStatisticsBean> result) {
                if (result.data.getUnpaid_count() == 0) {
                    mTvUnpaidCount.setVisibility(View.INVISIBLE);
                } else {
                    mTvUnpaidCount.setVisibility(View.VISIBLE);
                    if (result.data.getUnpaid_count() <= 99) {
                        mTvUnpaidCount.setText(result.data.getUnpaid_count() + "");
                    } else {
                        mTvUnpaidCount.setText("99+");
                    }
                }
                if (result.data.getNot_shipped() == 0) {
                    mTvNotShippedCount.setVisibility(View.INVISIBLE);
                } else {
                    mTvNotShippedCount.setVisibility(View.VISIBLE);
                    if (result.data.getNot_shipped() <= 99) {
                        mTvNotShippedCount.setText(result.data.getNot_shipped() + "");
                    } else {
                        mTvNotShippedCount.setText("99+");
                    }
                }
                if (result.data.getUnreceived_count() == 0) {
                    mTvUnreceivedCount.setVisibility(View.INVISIBLE);
                } else {
                    mTvUnreceivedCount.setVisibility(View.VISIBLE);
                    if (result.data.getUnreceived_count() <= 99) {
                        mTvUnreceivedCount.setText(result.data.getUnreceived_count() + "");
                    } else {
                        mTvUnreceivedCount.setText("99+");
                    }
                }
                if (result.data.getReturn_count() == 0) {
                    mTvReturnCount.setVisibility(View.INVISIBLE);
                } else {
                    mTvReturnCount.setVisibility(View.VISIBLE);
                    if (result.data.getReturn_count() <= 99) {
                        mTvReturnCount.setText(result.data.getReturn_count() + "");
                    } else {
                        mTvReturnCount.setText("99+");
                    }
                }
            }
        }, this);
    }

    @SingleClick
    @OnClick({R.id.tv_red_envelope, R.id.tv_account_security, R.id.tv_modify_data, R.id.tv_go_address_management,
            R.id.rl_go_commission_details, R.id.tv_withdraw, R.id.tv_integral, R.id.tv_wallet, R.id.rl_pending_pay,
            R.id.rl_pending_waiting_for_goods, R.id.rl_pending_receipt, R.id.rl_return, R.id.ll_all_order, R.id.tv_recharge,R.id.tv_member_benefits})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_address_management:
                //地址管理
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ADDRESS_MANAGEMENT, new profileBean("0"));
                break;
            case R.id.tv_account_security:
                //账户安全
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ACCOUNT_SECRITY);
                break;
            case R.id.tv_modify_data:
                //个人信息
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.CHANGE_MY_INFO);
                break;
            case R.id.tv_red_envelope:

                //红包礼卷
                break;
            case R.id.rl_go_commission_details:
            case R.id.tv_withdraw:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.COMMISSION_DETAILS);
                break;
            //积分
            case R.id.tv_integral:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.INTERGRAL_DETAILS);
                break;
            //钱包
            case R.id.tv_wallet:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.WALLET_DETAILS);
                break;
            case R.id.ll_all_order:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ALL_ORDERS, new profileBean("0"));
                break;
            case R.id.rl_pending_pay:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ALL_ORDERS, new profileBean("1"));
                break;
            case R.id.rl_pending_waiting_for_goods:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ALL_ORDERS, new profileBean("2"));
                break;
            case R.id.rl_pending_receipt:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ALL_ORDERS, new profileBean("3"));
                break;
            case R.id.rl_return:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ALL_ORDERS, new profileBean("4"));
                break;
            case R.id.tv_recharge:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ACCOUNT_RECHARGE);
                break;
            case R.id.tv_member_benefits:
                UserApi.getWebview("member_benefits", new BaseCallback<BaseResponse<WebBean>>() {
                    @Override
                    public void onSucc(BaseResponse<WebBean> result) {
                        if (ObjectUtils.isNotEmpty(result.data)) {
                            UIHelper.showWebPage(getActivity(), result.data.getTitle(), result.data.getUrl());
                        }
                    }
                }, this);
                break;
        }
    }


}
