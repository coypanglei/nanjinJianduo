package com.shaoyue.weizhegou.module.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AccountApi;
import com.shaoyue.weizhegou.api.remote.MessageApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.entity.message.MessageUnreadBean;
import com.shaoyue.weizhegou.entity.user.OrderStatisticsBean;
import com.shaoyue.weizhegou.entity.user.WebBean;
import com.shaoyue.weizhegou.interfac.CommCallBack;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.owner.fragment.DistributionCenterFragment;
import com.shaoyue.weizhegou.module.owner.fragment.MemberCentreFragment;
import com.shaoyue.weizhegou.module.owner.fragment.OrdererCenterFragment;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.ToastUtil;

import com.shaoyue.weizhegou.widget.ScrollViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.TextWidthColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyCenterFragment extends BaseTitleFragment {

    @BindView(R.id.iv_mohu)
    ImageView mIvMohu;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.ll_login)
    LinearLayout mLlLogin;
    @BindView(R.id.ll_center_info)
    LinearLayout mLlCenterInfo;
    @BindView(R.id.tv_go_login)
    TextView mTvGoLogin;
    @BindView(R.id.tv_sign_out)
    TextView mTvSignOut;
    @BindView(R.id.tv_profile_id)
    TextView mTvProfileId;
    @BindView(R.id.tv_register_time)
    TextView mTvRegisterTime;
    @BindView(R.id.tv_pending_pay)
    TextView mTvPendingPay;
    @BindView(R.id.tv_pending_waiting_for_goods)
    TextView mTvPendingWaitingForGoods;
    @BindView(R.id.tv_rank_name)
    TextView mTvRankName;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.tv_commission_balance)
    TextView mTvCommissionBalance;
    @BindView(R.id.tv_score)
    TextView mTvScore;
    @BindView(R.id.ll_order)
    LinearLayout mLlOrder;
    @BindView(R.id.ll_center_features)
    LinearLayout mLlCenterFeatures;
    @BindView(R.id.ll_commission_amount)
    LinearLayout mLlCommissionAmount;
    @BindView(R.id.moretab_indicator)
    ScrollIndicatorView scrollIndicatorView;
    @BindView(R.id.moretab_viewPager)
    ScrollViewPager viewPager;
    @BindView(R.id.tv_commission_amount)
    TextView mTvCommissionAmount;

    private final Fragment[] fragmentArray = {MemberCentreFragment.newInstance(), DistributionCenterFragment.newInstance(), OrdererCenterFragment.newInstance()};

    String[] mTextviewArray;
    @BindView(R.id.tv_unpaid_count)
    TextView mTvUnpaidCount;
    @BindView(R.id.tv_not_shipped_count)
    TextView mTvNotShippedCount;
    @BindView(R.id.tv_unreceived_count)
    TextView mTvUnreceivedCount;
    @BindView(R.id.tv_return_count)
    TextView mTvReturnCount;


    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;

    //类型 0未登录 1登录 2店主
    private int mType = 0;

    public static MyCenterFragment newInstance() {
        Bundle args = new Bundle();
        MyCenterFragment fragment = new MyCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("会员中心").hideLeftButtonV2().setRightBtnV3(R.drawable.icon_info, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mType != 0) {
                    UIHelper.showProfileCommonActivity(getActivity(), ContentType.ACCOUTN_INFO);
                }
            }
        });
        initView();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_center;
    }

    @Override
    public void onResume() {
        super.onResume();

        setType();
        //绑定弹窗
        UserApi.isBindPhone(new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
            }

            @Override
            public void onFail(ApiException apiError) {
                if (apiError.getErrCode() == 400) {
                    UIHelper.showPecrfectIndividualDialog(getActivity());
                }
            }
        }, this);
        //获取会员信息是否已读
        MessageApi.getMessageUnread(new BaseCallback<BaseResponse<MessageUnreadBean>>() {
            @Override
            public void onSucc(BaseResponse<MessageUnreadBean> result) {
                if (result.data.getCount() == 0) {
                    setRightBtnV3(R.drawable.icon_info, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mType != 0) {
                                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ACCOUTN_INFO);
                            }
                        }
                    });
                } else {

                    setRightBtnV3(R.drawable.icon_red_msg, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mType != 0) {
                                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ACCOUTN_INFO);
                            }
                        }
                    });

                }
            }

            @Override
            public void onFail(ApiException apiError) {
                setRightBtnV3(R.drawable.icon_info, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }, this);

    }

    /**
     * 初始化View
     */
    private void initView() {
        mTextviewArray = getResources().getStringArray(R.array.main_tab_center);
        scrollIndicatorView.setScrollBar(new TextWidthColorBar(getActivity(), scrollIndicatorView, getResources().getColor(R.color.color_cd0946), 4));
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(getResources().getColor(R.color.color_cd0946), getResources().getColor(R.color.color_4a4a4a)).setSize(15, 15));
        viewPager.setOffscreenPageLimit(3);
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        inflate = LayoutInflater.from(mActivity.getApplicationContext());
        indicatorViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        setType();
    }


    /**
     * 设置当页面状态
     */
    private void setType() {
        UserMgr.getInstance().refreshMyCenter(new CommCallBack() {
            @Override
            public void complete(int code, String msg) {
                if (code == 0) {
                    UserMgr.getInstance().setLogin(true);
                    mType = UserMgr.getInstance().getMemberShipLevel();
                    getOrderNum();
                    LogUtils.e(mType);
                } else if (code == 401) {
                    UserMgr.getInstance().setLogin(false);
                    UserMgr.getInstance().clearUserInfo();
                    mType = 0;
                } else {

                    ToastUtil.showErrorToast(msg);
                }
                setVisableView();
            }
        });
    }

    /**
     * 获取订单数量
     */
    private void getOrderNum() {
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

    /**
     * 切换状态类型
     * <p>
     * 0 未登录 不显示个人信息
     * <p>
     * 1 登录 Vip 显示个人信息等等
     * <p>
     * 2 登录 店主
     */
    private void setVisableView() {
        LogUtils.e(mType);
        switch (mType) {
            case 0:
                mLlOrder.setVisibility(View.VISIBLE);
                mLlCenterFeatures.setVisibility(View.VISIBLE);
                mLlCenterInfo.setVisibility(View.GONE);
                mTvSignOut.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                scrollIndicatorView.setVisibility(View.GONE);
                mTvGoLogin.setText("登录/注册");
                GlideNewImageLoader.displayImageHeadNoCache(getActivity(), mProfileImage, UserMgr.getInstance().getHeaderpic());
                mLlCommissionAmount.setVisibility(View.GONE);
                break;
            case 1:
                mTvGoLogin.setText(UserMgr.getInstance().getUsername());
                mLlOrder.setVisibility(View.VISIBLE);
                mLlCenterFeatures.setVisibility(View.VISIBLE);
                mLlCommissionAmount.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                scrollIndicatorView.setVisibility(View.GONE);
                //设置头像
                GlideNewImageLoader.displayImageHeadNoCache(getActivity(), mProfileImage, UserMgr.getInstance().getHeaderpic());
                //id
                mTvProfileId.setText("ID : " + UserMgr.getInstance().getUid());
                mTvRegisterTime.setText("注册日期 : " + UserMgr.getInstance().getHasRegDay());
                mTvRankName.setText(UserMgr.getInstance().getSpRankName());
                mTvCommissionBalance.setText(UserMgr.getInstance().getSP_BAlANCE());
                mTvBalance.setText(UserMgr.getInstance().getSpAmountOfConsumption());
                mTvScore.setText(UserMgr.getInstance().getSpScore());
                mTvCommissionAmount.setText(UserMgr.getInstance().getSP_COMMISSION_BAlANCE());
                mLlCenterInfo.setVisibility(View.VISIBLE);
                mTvSignOut.setVisibility(View.VISIBLE);

                break;
            //3合伙人
            //2店主
            case 2:
            case 3:
                viewPager.setVisibility(View.VISIBLE);
                scrollIndicatorView.setVisibility(View.VISIBLE);
                mTvGoLogin.setText(UserMgr.getInstance().getUsername());
                //设置头像
                GlideNewImageLoader.displayImageHeadNoCache(getActivity(), mProfileImage, UserMgr.getInstance().getHeaderpic());
                //id
                mTvProfileId.setText("ID : " + UserMgr.getInstance().getUid());
                mTvRegisterTime.setText("注册日期 : " + UserMgr.getInstance().getHasRegDay());
                mTvRankName.setText(UserMgr.getInstance().getSpRankName());
                mTvCommissionBalance.setText(UserMgr.getInstance().getSP_BAlANCE());
                mTvBalance.setText(UserMgr.getInstance().getSpAmountOfConsumption());
                mTvScore.setText(UserMgr.getInstance().getSpScore());
                mTvCommissionAmount.setText(UserMgr.getInstance().getSP_COMMISSION_BAlANCE());
                mLlCenterInfo.setVisibility(View.VISIBLE);
                mTvSignOut.setVisibility(View.VISIBLE);
                mLlOrder.setVisibility(View.GONE);
                mLlCenterFeatures.setVisibility(View.GONE);
                //佣金金额
                mLlCommissionAmount.setVisibility(View.VISIBLE);
                indicatorViewPager.getAdapter().notifyDataSetChanged();
                break;
            default:
                break;
        }

    }


    /**
     * 退出登录
     */
    private void doLogout() {
        startProgressDialog(false);
        UserMgr.getInstance().doLogout(new CommCallBack() {
            @Override
            public void complete(int code, String msg) {
                stopProgressDialog();
                UIHelper.goLoginPage(getActivity());
            }
        });

    }


    @OnClick({R.id.ll_login, R.id.tv_sign_out, R.id.tv_go_address_management, R.id.tv_account_security,
            R.id.tv_modify_data, R.id.tv_red_envelope, R.id.tv_wallet, R.id.tv_integral, R.id.rl_pending_pay,
            R.id.rl_pending_waiting_for_goods, R.id.rl_pending_receipt, R.id.rl_return, R.id.ll_all_order, R.id.tv_recharge,
            R.id.tv_member_benefits
    })
    public void onViewClicked(View view) {
        // 没登陆时登陆
        LogUtils.e(mType);
        if (mType == 0) {
            UIHelper.goLoginPage(getActivity());
            return;
        }
        switch (view.getId()) {
            case R.id.ll_login:
                //登录
                if (!UserMgr.getInstance().isLogin()) {
                    UIHelper.goLoginPage(getActivity());
                }
                break;
            case R.id.tv_sign_out:
                mTvReturnCount.setVisibility(View.INVISIBLE);
                mTvUnreceivedCount.setVisibility(View.INVISIBLE);
                mTvUnpaidCount.setVisibility(View.INVISIBLE);
                mTvNotShippedCount.setVisibility(View.INVISIBLE);
                //登出
                doLogout();
                break;
            case R.id.tv_go_address_management:
                //地址管理
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ADDRESS_MANAGEMENT);
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


    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return mTextviewArray.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(mTextviewArray[position]);
            int padding = 10;
            textView.setPadding(padding, 0, padding, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {

            return fragmentArray[position];
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_UNCHANGED;
        }

    }
}
