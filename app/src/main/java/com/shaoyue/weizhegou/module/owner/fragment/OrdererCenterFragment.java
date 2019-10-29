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
import com.shaoyue.weizhegou.entity.user.OrdererNumBean;
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
 * 作者：PangLei on 2019/5/21 0021 10:30
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrdererCenterFragment extends BaseCustomFragment {

    @BindView(R.id.tv_commission_amount)
    TextView mTvCommissionAmount;
    @BindView(R.id.tv_orderer_num)
    TextView mTvOrdererNum;
    @BindView(R.id.tv_member_num)
    TextView mTvMemberNum;
    @BindView(R.id.tv_order_num)
    TextView mTvOrderNum;
    @BindView(R.id.tv_revenue_price)
    TextView mTvRevenuePrice;
    @BindView(R.id.tv_withdraw)
    TextView mTvWithdraw;
    Unbinder unbinder;

    private String[] mTitles = {"下级订货商", "下级会员", "团队订单"};


    public static OrdererCenterFragment newInstance() {

        Bundle args = new Bundle();

        OrdererCenterFragment fragment = new OrdererCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_orderer_center;
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
        if (userInfoBean.getWithdraw_auth().equals("1")) {
            mTvWithdraw.setVisibility(View.VISIBLE);
        } else {
            mTvWithdraw.setVisibility(View.INVISIBLE);
        }
        if (ObjectUtils.isNotEmpty(userInfoBean.getCommission_balance())) {
            mTvCommissionAmount.setText("¥ " + userInfoBean.getCommission_balance());
        }

        if (ObjectUtils.isNotEmpty(userInfoBean.getTo_sum_amount())) {
            mTvRevenuePrice.setText("待收益：¥" + userInfoBean.getTo_sum_amount());
        }


    }

    private void initView() {
        UserApi.getOrdererCenterNum(new BaseCallback<BaseResponse<OrdererNumBean>>() {
            @Override
            public void onSucc(BaseResponse<OrdererNumBean> result) {

                if (result.data.getBuyUserCount() == 0) {
                    mTvOrdererNum.setVisibility(View.INVISIBLE);
                } else {

                    mTvOrdererNum.setVisibility(View.VISIBLE);
                    if (result.data.getBuyUserCount() <= 99) {
                        mTvOrdererNum.setText(result.data.getBuyUserCount() + "");
                    } else {
                        mTvOrdererNum.setText("99+");
                    }
                }
                if (result.data.getAllUserCount() == 0) {
                    mTvMemberNum.setVisibility(View.INVISIBLE);
                } else {
                    mTvMemberNum.setVisibility(View.VISIBLE);
                    if (result.data.getAllUserCount() <= 99) {
                        mTvMemberNum.setText(result.data.getAllUserCount() + "");
                    } else {
                        mTvMemberNum.setText("99+");
                    }
                }
                if (result.data.getOrderCount().getAll() == 0) {
                    mTvOrderNum.setVisibility(View.INVISIBLE);
                } else {
                    mTvOrderNum.setVisibility(View.VISIBLE);
                    if (result.data.getOrderCount().getAll() <= 99) {
                        mTvOrderNum.setText(result.data.getOrderCount().getAll() + "");
                    } else {
                        mTvOrderNum.setText("99+");
                    }
                }
                mTitles[0] = "下级订货商(" + result.data.getBuyUserCount() + ")";
                mTitles[1] = "下级会员(" + result.data.getAllUserCount() + ")";
                mTitles[2] = "团队订单(" + result.data.getOrderCount().getAll() + ")";
            }

            @Override
            public void onFail(ApiException apiError) {

            }
        }, this);


    }

    @SingleClick
    @OnClick({R.id.tv_subordinate_orderer, R.id.tv_pending_waiting_for_distributor, R.id.tv_team_order, R.id.rl_go_commission_details, R.id.tv_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_subordinate_orderer:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ORDERER_DETAILS, new profileBean("0", mTitles));
                break;
            case R.id.tv_pending_waiting_for_distributor:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ORDERER_DETAILS, new profileBean("1", mTitles));
                break;
            case R.id.tv_team_order:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ORDERER_DETAILS, new profileBean("2", mTitles));
                break;
            case R.id.rl_go_commission_details:
            case R.id.tv_withdraw:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.COMMISSION_DETAILS);
                break;
        }
    }



}
