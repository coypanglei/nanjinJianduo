package com.shaoyue.weizhegou.module.goods.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.GoodsApi;
import com.shaoyue.weizhegou.api.remote.OrderApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.center.AddressListBean;
import com.shaoyue.weizhegou.entity.coupon.SettlementCouponBean;
import com.shaoyue.weizhegou.entity.goods.SettlementCenterBean;
import com.shaoyue.weizhegou.entity.goods.SettlementCoupon;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.entity.order.OrderDetailsBean;
import com.shaoyue.weizhegou.entity.order.OrderIdBean;
import com.shaoyue.weizhegou.event.PayPasswordEvent;
import com.shaoyue.weizhegou.module.goods.adapter.SettlementOrderAdapter;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;


/**
 * 作者：PangLei on 2019/6/24 0024 15:42
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SettlementCenterFragment extends BaseTitleFragment {
    @BindView(R.id.tv_courier)
    TextView mTvCourier;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.tv_recipient)
    TextView mTvRecipient;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_courier_fees)
    TextView mTvCourierFees;
    @BindView(R.id.rv_goods)
    RecyclerView mRvGoods;
    @BindView(R.id.ll_identity_number)
    LinearLayout mLlIdentityNumber;
    @BindView(R.id.tv_id_card_description)
    TextView mTvIdCardDescription;
    @BindView(R.id.tv_account_balance)
    TextView mTvAccountBalance;
    @BindView(R.id.rl_balance)
    RelativeLayout mRlBalance;
    @BindView(R.id.iv_balance)
    ImageView mIvBalance;
    @BindView(R.id.iv_wechat)
    ImageView mIvWechat;
    @BindView(R.id.iv_alipay)
    ImageView mIvAlipay;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;

    @BindView(R.id.tv_coupon_price)
    TextView mTvCouponPrice;
    @BindView(R.id.rl_coupon)
    RelativeLayout mRlCoupon;
    @BindView(R.id.tv_settlement)
    TextView mTvSettlement;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    @BindView(R.id.et_provincial_certificate_number)
    EditText mEtProvincialCertificateNumber;


    private SettlementOrderAdapter mSettlementOrderAdapter;

    //支付方式
    private String paymentMethod;

    private int mCouponId = 0;

    private String mAddressId;

    private SettlementCenterBean mSettlementCenterBean;
    //购物车id
    private String ids;

    private String productsId;

    private String itemId;

    private String productsNum;


    public static SettlementCenterFragment newInstance(SettlementCenterBean settlementCenterBean) {
        Bundle args = new Bundle();
        args.putSerializable("SettlementCenter", settlementCenterBean);
        SettlementCenterFragment fragment = new SettlementCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            mSettlementCenterBean = (SettlementCenterBean) getArguments().getSerializable("SettlementCenter");
            ids = mSettlementCenterBean.getIds();
            productsId = mSettlementCenterBean.getProducts_id();
            itemId = mSettlementCenterBean.getItem_id();
            productsNum = mSettlementCenterBean.getProducts_num();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_settlement_center;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mSettlementOrderAdapter = new SettlementOrderAdapter(getActivity());
        mRvGoods.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvGoods.setAdapter(mSettlementOrderAdapter);
        mRvGoods.setNestedScrollingEnabled(false);
        mSettlementOrderAdapter.setNewData(mSettlementCenterBean.getmProductsList());
        mSettlementOrderAdapter.notifyDataSetChanged();
        mTvCourier.setText(mSettlementCenterBean.getDelivery());
        setmCarNum(mSettlementCenterBean.getOrder_count());
        if (ObjectUtils.isNotEmpty(mSettlementCenterBean.getUser_address().getId())) {
            mLlAddress.setVisibility(View.VISIBLE);
            mAddressId = mSettlementCenterBean.getUser_address().getId();
            mTvRecipient.setText("收件人：" + mSettlementCenterBean.getUser_address().getConsignee() + " " + mSettlementCenterBean.getUser_address().getMobile());
            mTvAddress.setText("地址：" + mSettlementCenterBean.getUser_address().getArea().replace(",", "") + mSettlementCenterBean.getUser_address().getAddress());
            mTvCourierFees.setText("￥" + mSettlementCenterBean.getFare());
        } else {
            mLlAddress.setVisibility(View.GONE);
        }

        //省份证号显示
        if (mSettlementCenterBean.getNeed_id().equals("1")) {
            mLlIdentityNumber.setVisibility(View.VISIBLE);
            mTvIdCardDescription.setText(mSettlementCenterBean.getId_card_notice());
        } else {
            mLlIdentityNumber.setVisibility(View.GONE);
        }
        //余额显示
        if (!mSettlementCenterBean.getBalance().equals("0.00")) {
            mRlBalance.setVisibility(View.VISIBLE);
            mTvAccountBalance.setText("账户余额:" + mSettlementCenterBean.getBalance());
        }
        //有可选优惠券显示
        if (mSettlementCenterBean.getCoupon_id() != 0) {
            mRlCoupon.setVisibility(View.VISIBLE);
            mTvCouponPrice.setText("- " + mSettlementCenterBean.getCoupon_price() + "元");
        }
        //余额不足时，默认选中微信
        if (mSettlementCenterBean.getBalance_bigger_total_price() == 0) {
            mIvWechat.setTag("select");
            mIvWechat.setImageResource(R.drawable.icon_goods_select);
        }
        if (mSettlementCenterBean.getAll_unusual() == 0) {
            mLlBottom.setVisibility(View.VISIBLE);
            mTvSettlement.setTag("click");
            mTvSettlement.setBackgroundResource(R.drawable.icon_red_jbshape_20);
        } else {
            mLlBottom.setVisibility(View.GONE);
            mTvSettlement.setTag("not_click");
            mTvSettlement.setBackgroundResource(R.drawable.icon_d4d4d4_20dp);
        }
        mTvTotalPrice.setText(mSettlementCenterBean.getTotal_price() + "");
        mCouponId = mSettlementCenterBean.getCoupon_id();

    }

    /**
     * 清除历史记录
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PayPasswordEvent event) {
        sumbitOrder(event);
    }

    /**
     * 提交订单
     */
    public void sumbitOrder(PayPasswordEvent event) {
        if (ObjectUtils.isEmpty(mAddressId)) {
            ToastUtil.showBlackToastSucess("请选择收货地址");
            return;
        }

        String mPayName = "";
        if (mIvWechat.getTag().equals("select")) {
            mPayName = "wechat";
        }
        if (mIvAlipay.getTag().equals("select")) {
            mPayName = "alipay";
        }
        int mSelectBlance = 0;

        if (mIvBalance.getTag().equals("select") && mIvBalance.getVisibility() == View.VISIBLE) {
            mSelectBlance = 1;
        }
        String mEtProvincialCertificateNum = mEtProvincialCertificateNumber.getText().toString().trim();

        OrderApi.cartSubmit(mSettlementCenterBean.getAction(), mAddressId, event.getPayPassword(), mCouponId + "", mPayName, ids, mSelectBlance + "", mEtProvincialCertificateNum, new BaseCallback<BaseResponse<OrderIdBean>>() {
            @Override
            public void onSucc(BaseResponse<OrderIdBean> result) {
                if (ObjectUtils.isNotEmpty(result.data.getOrder_id())) {
                    OrderApi.getOrderInfo(result.data.getOrder_id(), new BaseCallback<BaseResponse<OrderDetailsBean>>() {
                        @Override
                        public void onSucc(BaseResponse<OrderDetailsBean> result) {
                            getActivity().finish();
                            if (ObjectUtils.isNotEmpty(result.data)) {
                                UIHelper.showOrderDetailsActicity(getActivity(), ContentType.ORDER_DETAILS, result.data);
                            }
                        }
                    }, this);
                }

            }

            @Override
            public void onFail(ApiException apiError) {
                ToastUtil.showBlackToastSucess(apiError.getMessage());
            }
        }, this);
    }


    /**
     * 更换地址
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AddressListBean event) {
        mAddressId = event.getId();
        mLlAddress.setVisibility(View.VISIBLE);
        mTvRecipient.setText("收件人：" + event.getConsignee() + " " + event.getMobile());
        mTvAddress.setText("地址：" + event.getArea().replace(",", "") + event.getAddress());

        GoodsApi.carConfirm(mCouponId + "", mAddressId, ids, productsId, itemId, productsNum, mSettlementCenterBean.getAction(), "1", new BaseCallback<BaseResponse<SettlementCenterBean>>() {
            @Override
            public void onSucc(BaseResponse<SettlementCenterBean> result) {
                mSettlementCenterBean = result.data;
                mSettlementOrderAdapter.setNewData(mSettlementCenterBean.getmProductsList());
                mSettlementOrderAdapter.notifyDataSetChanged();

                mTvCourier.setText(mSettlementCenterBean.getDelivery());
                setmCarNum(mSettlementCenterBean.getOrder_count());
                mTvCourierFees.setText("￥" + mSettlementCenterBean.getFare());
                //余额显示
                if (!mSettlementCenterBean.getBalance().equals("0.00")) {
                    mRlBalance.setVisibility(View.VISIBLE);
                    mTvAccountBalance.setText("账户余额:" + mSettlementCenterBean.getBalance());
                }

                if (mSettlementCenterBean.getCoupon_id() != 0) {
                    mRlCoupon.setVisibility(View.VISIBLE);
                    mTvCouponPrice.setText("- " + mSettlementCenterBean.getCoupon_price() + "元");
                }
                if (mSettlementCenterBean.getAll_unusual() == 0) {
                    mLlBottom.setVisibility(View.VISIBLE);
                    mTvSettlement.setTag("click");
                    mTvSettlement.setBackgroundResource(R.drawable.icon_red_jbshape_20);
                } else {
                    mLlBottom.setVisibility(View.GONE);
                    mTvSettlement.setTag("not_click");
                    mTvSettlement.setBackgroundResource(R.drawable.icon_d4d4d4_20dp);
                }
                mTvTotalPrice.setText(mSettlementCenterBean.getTotal_price() + "");
                mCouponId = mSettlementCenterBean.getCoupon_id();
                if (mSettlementCenterBean.getCoupon_id() != 0) {
                    if (mCouponId == -1) {
                        mTvCouponPrice.setText("不使用优惠券");
                    } else {
                        mRlCoupon.setVisibility(View.VISIBLE);
                        mTvCouponPrice.setText("- " + mSettlementCenterBean.getCoupon_price() + "元");
                    }

                }
            }

            @Override
            public void onFail(ApiException apiError) {
                ToastUtil.showBlackToastSucess(apiError.getMessage());
            }
        }, this);
    }


    /**
     * 选择优惠券刷新数据
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSettlementCouponBeanEvent(SettlementCouponBean event) {
        mCouponId = event.getId();

        GoodsApi.carConfirm(mCouponId + "", mAddressId, ids, productsId, itemId, productsNum, mSettlementCenterBean.getAction(), "1", new BaseCallback<BaseResponse<SettlementCenterBean>>() {
            @Override
            public void onSucc(BaseResponse<SettlementCenterBean> result) {
                mSettlementCenterBean = result.data;
                mSettlementOrderAdapter.setNewData(mSettlementCenterBean.getmProductsList());
                mSettlementOrderAdapter.notifyDataSetChanged();

                mTvCourier.setText(mSettlementCenterBean.getDelivery());
                setmCarNum(mSettlementCenterBean.getOrder_count());

                //余额显示
                if (!mSettlementCenterBean.getBalance().equals("0.00")) {
                    mRlBalance.setVisibility(View.VISIBLE);
                    mTvAccountBalance.setText("账户余额:" + mSettlementCenterBean.getBalance());
                }

                if (mSettlementCenterBean.getCoupon_id() != 0) {
                    if (mCouponId == -1) {
                        mTvCouponPrice.setText("不使用优惠券");
                    } else {
                        mRlCoupon.setVisibility(View.VISIBLE);
                        mTvCouponPrice.setText("- " + mSettlementCenterBean.getCoupon_price() + "元");
                    }

                }
                if (mSettlementCenterBean.getAll_unusual() == 0) {
                    mLlBottom.setVisibility(View.VISIBLE);
                    mTvSettlement.setTag("click");
                    mTvSettlement.setBackgroundResource(R.drawable.icon_red_jbshape_20);
                } else {
                    mLlBottom.setVisibility(View.GONE);
                    mTvSettlement.setTag("not_click");
                    mTvSettlement.setBackgroundResource(R.drawable.icon_d4d4d4_20dp);
                }
                mTvTotalPrice.setText(mSettlementCenterBean.getTotal_price() + "");
                mCouponId = mSettlementCenterBean.getCoupon_id();
                if (mSettlementCenterBean.getBalance_bigger_total_price() == 0) {
                    if (mIvWechat.getTag().equals("no_select")) {
                        mIvWechat.setTag("select");
                        mIvWechat.setImageResource(R.drawable.icon_goods_select);
                        mIvAlipay.setTag("no_select");
                        mIvAlipay.setImageResource(R.drawable.icon_goods_not_select);
                    }
                } else {
                    mIvBalance.setTag("select");
                    mIvBalance.setImageResource(R.drawable.icon_goods_select);
                    mIvWechat.setTag("no_select");
                    mIvWechat.setImageResource(R.drawable.icon_goods_not_select);
                    mIvAlipay.setTag("no_select");
                    mIvAlipay.setImageResource(R.drawable.icon_goods_not_select);
                }
            }

            @Override
            public void onFail(ApiException apiError) {
                ToastUtil.showBlackToastSucess(apiError.getMessage());
            }
        }, this);
    }

    /**
     * 标题数字变化
     *
     * @param num
     */
    private void setmCarNum(int num) {
        if (num > 99) {
            setCommonTitle("结算中心 (" + "99+" + ")");
        } else if (num <= 0) {
            setCommonTitle("结算中心");
        } else {
            setCommonTitle("结算中心 (" + num + ")");
        }
    }

    /**
     * 打开支付框
     */
    private void openPayPasswordDialog() {
        UIHelper.showPayPasswordDialog(getActivity(), mSettlementCenterBean.getTotal_price());
    }

    @OnClick({R.id.rl_add_address, R.id.rl_wechat, R.id.rl_alipay, R.id.iv_balance, R.id.tv_settlement, R.id.rl_coupon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_add_address:
                //地址
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.ADDRESS_MANAGEMENT, new profileBean("1"));
                break;
            case R.id.rl_wechat:
                //订单大于余额 可取消
                if (mSettlementCenterBean.getBalance_bigger_total_price() == 0) {
                    if (mIvWechat.getTag().equals("no_select")) {
                        mIvWechat.setTag("select");
                        mIvWechat.setImageResource(R.drawable.icon_goods_select);
                        mIvAlipay.setTag("no_select");
                        mIvAlipay.setImageResource(R.drawable.icon_goods_not_select);
                    }
                } else {
                    if (mIvWechat.getTag().equals("no_select")) {
                        mIvWechat.setTag("select");
                        mIvWechat.setImageResource(R.drawable.icon_goods_select);
                        mIvBalance.setTag("no_select");
                        mIvBalance.setImageResource(R.drawable.icon_goods_not_select);
                        mIvAlipay.setTag("no_select");
                        mIvAlipay.setImageResource(R.drawable.icon_goods_not_select);
                    }
                }

                break;
            case R.id.rl_alipay:
                //订单大于余额
                if (mSettlementCenterBean.getBalance_bigger_total_price() == 0) {
                    if (mIvAlipay.getTag().equals("no_select")) {
                        mIvAlipay.setTag("select");
                        mIvAlipay.setImageResource(R.drawable.icon_goods_select);
                        mIvWechat.setTag("no_select");
                        mIvWechat.setImageResource(R.drawable.icon_goods_not_select);
                    }
                } else {
                    if (mIvAlipay.getTag().equals("no_select")) {
                        mIvAlipay.setTag("select");
                        mIvAlipay.setImageResource(R.drawable.icon_goods_select);
                        mIvBalance.setTag("no_select");
                        mIvBalance.setImageResource(R.drawable.icon_goods_not_select);
                        mIvWechat.setTag("no_select");
                        mIvWechat.setImageResource(R.drawable.icon_goods_not_select);
                    }
                }
                break;
            case R.id.iv_balance:
                //订单大于余额 可取消
                if (mSettlementCenterBean.getBalance_bigger_total_price() == 0) {
                    if (mIvBalance.getTag().equals("select")) {
                        mIvBalance.setTag("no_select");
                        mIvBalance.setImageResource(R.drawable.icon_goods_not_select);
                    } else {
                        mIvBalance.setTag("select");
                        mIvBalance.setImageResource(R.drawable.icon_goods_select);
                    }
                } else {
                    if (mIvBalance.getTag().equals("no_select")) {
                        mIvBalance.setTag("select");
                        mIvBalance.setImageResource(R.drawable.icon_goods_select);
                        mIvWechat.setTag("no_select");
                        mIvWechat.setImageResource(R.drawable.icon_goods_not_select);
                        mIvAlipay.setTag("no_select");
                        mIvAlipay.setImageResource(R.drawable.icon_goods_not_select);
                    }
                }
                break;
            //提交订单
            case R.id.tv_settlement:
                if (mTvSettlement.getTag().equals("click")) {
                    String mEtProvincialCertificateNum = mEtProvincialCertificateNumber.getText().toString().trim();
                    //省份证号显示
                    if (mSettlementCenterBean.getNeed_id().equals("1")) {
                        if (ObjectUtils.isEmpty(mEtProvincialCertificateNum)) {
                            ToastUtil.showBlackToastSucess("还未填写身份证号");
                            return;
                        }
                        //验证sheng份证号码
                        OrderApi.checkId(mAddressId, mEtProvincialCertificateNum, new BaseCallback<BaseResponse<Void>>() {
                            @Override
                            public void onSucc(BaseResponse<Void> result) {
                                if (mSettlementCenterBean.isPay_password() && mIvBalance.getTag().equals("select") && mIvWechat.getTag().equals("no_select") && mIvAlipay.getTag().equals("no_select")) {
                                    openPayPasswordDialog();
                                } else {
                                    sumbitOrder(new PayPasswordEvent(""));
                                }
                            }

                            @Override
                            public void onFail(ApiException apiError) {
                                ToastUtil.showBlackToastSucess(apiError.getMessage());
                            }
                        }, this);
                    } else {
                        if (mSettlementCenterBean.isPay_password() && mIvBalance.getTag().equals("select") && mIvWechat.getTag().equals("no_select") && mIvAlipay.getTag().equals("no_select")) {
                            openPayPasswordDialog();
                        } else {
                            sumbitOrder(new PayPasswordEvent(""));
                        }
                    }


                }
                break;
            case R.id.rl_coupon:
                //优惠券列表
                GoodsApi.getCartConponList(productsId, mSettlementCenterBean.getAction(), new BaseCallback<BaseResponse<List<SettlementCouponBean>>>() {
                    @Override
                    public void onSucc(BaseResponse<List<SettlementCouponBean>> result) {
                        //包含id 和显示的集合
                        List<SettlementCouponBean> mCouponBeanList = result.data;
                        if (ObjectUtils.isNotEmpty(result.data) && mCouponBeanList.size() > 0) {
                            //添加不使用优惠券选项
                            if (mCouponId == -1) {
                                mCouponBeanList.add(new SettlementCouponBean("不使用优惠券", "", -1, true));
                            } else {
                                mCouponBeanList.add(new SettlementCouponBean("不使用优惠券", "", -1, false));
                            }
                            //比较选中的优惠券
                            for (SettlementCouponBean mCouponBean : mCouponBeanList) {
                                if (mCouponBean.getId() == mCouponId) {
                                    mCouponBean.setSelect(true);
                                }
                            }

                            UIHelper.showCouponListDialog(getActivity(), new SettlementCoupon(mCouponBeanList, "-1"));
                        }
                    }
                }, this);
                break;
        }
    }


}
