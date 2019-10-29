package com.shaoyue.weizhegou.module.order.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.order.OrderDetailsBean;
import com.shaoyue.weizhegou.entity.order.OrderDetailsGoodsBean;
import com.shaoyue.weizhegou.module.order.adapter.OrderContentDetailsAdapter;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：PangLei on 2019/7/10 0010 09:39
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrderDetailsFragment extends BaseTitleFragment {

    @BindView(R.id.tv_order_msg)
    TextView mTvOrderMsg;
    @BindView(R.id.tv_order_sn)
    TextView mTvOrderSn;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_recipient)
    TextView mTvRecipient;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_id_card)
    TextView mTvIdCard;
    @BindView(R.id.tv_leave_a_message)
    TextView mTvLeaveAMessage;
    @BindView(R.id.rl_leave_a_msg)
    LinearLayout mRlLeaveAMsg;
    @BindView(R.id.tv_coupon_price)
    TextView mTvCouponPrice;
    @BindView(R.id.rl_coupon)
    RelativeLayout mRlCoupon;
    @BindView(R.id.tv_freight)
    TextView mTvFreight;
    @BindView(R.id.tv_btn_delete_order)
    TextView mTvBtnDeleteOrder;
    @BindView(R.id.tv_btn_refund)
    TextView mTvBtnRefund;
    @BindView(R.id.tv_btn_return)
    TextView mTvBtnReturn;
    @BindView(R.id.tv_btn_exchange)
    TextView mTvBtnExchange;
    @BindView(R.id.tv_btn_immediate_payment)
    TextView mTvBtnImmediatePayment;
    @BindView(R.id.tv_btn_confirm_receipt)
    TextView mTvBtnConfirmReceipt;
    @BindView(R.id.rl_bottom)
    RelativeLayout mRlBottom;
    @BindView(R.id.rv_goods)
    RecyclerView mRvGoods;
    @BindView(R.id.iv_order_status)
    ImageView mIvOrderStatus;


    private OrderContentDetailsAdapter mOrderContentDetailsAdapter;
    private OrderDetailsBean orderDetailsBean;

    public static OrderDetailsFragment newInstance(OrderDetailsBean orderDetailsBean) {
        Bundle args = new Bundle();
        args.putSerializable("order", orderDetailsBean);
        OrderDetailsFragment fragment = new OrderDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isEmpty(getArguments())) {
            removeFragment();
        }
        orderDetailsBean = (OrderDetailsBean) getArguments().getSerializable("order");
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_order_details;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("订单详情页");
        mTvOrderMsg.setText(orderDetailsBean.getComposite_status_msg());
        mTvOrderSn.setText("订单编号：" + orderDetailsBean.getOrder_sn());
        mTvTime.setText("创建时间：" + orderDetailsBean.getCreate_time());
        mTvRecipient.setText("收件人：" + orderDetailsBean.getConsignee());
        mTvRecipient.setText("收件人：" + orderDetailsBean.getConsignee() + " " + orderDetailsBean.getMobile());
        mTvAddress.setText("地址：" + orderDetailsBean.getArea().replace(",", "") + orderDetailsBean.getAddress());
        if (ObjectUtils.isNotEmpty(orderDetailsBean.getOrder_icon())) {
            GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), mIvOrderStatus, orderDetailsBean.getOrder_icon());
        }
        if (orderDetailsBean.getVisable()) {
            mRlBottom.setVisibility(View.GONE);
        } else {
            mRlBottom.setVisibility(View.VISIBLE);
        }

        if (orderDetailsBean.getCan_pay().equals("1")) {
            mTvBtnImmediatePayment.setVisibility(View.VISIBLE);
        }
        if (orderDetailsBean.getCan_exchange().equals("1")) {
            mTvBtnExchange.setVisibility(View.VISIBLE);
        }
        if (orderDetailsBean.getCan_return().equals("1")) {
            mTvBtnReturn.setVisibility(View.VISIBLE);
        }
        if (orderDetailsBean.getCan_recepit().equals("1")) {
            mTvBtnConfirmReceipt.setVisibility(View.VISIBLE);
        }
        if (orderDetailsBean.getCan_delete().equals("1")) {
            mTvBtnDeleteOrder.setVisibility(View.VISIBLE);
        }
        if (orderDetailsBean.getCan_refund().equals("1")) {
            mTvBtnRefund.setVisibility(View.VISIBLE);
        }

        if (ObjectUtils.isNotEmpty(orderDetailsBean.getId_card())) {
            mTvIdCard.setVisibility(View.VISIBLE);
            mTvIdCard.setText("(身份证号：" + orderDetailsBean.getId_card() + ")");
        }
        if (ObjectUtils.isNotEmpty(orderDetailsBean.getUser_note())) {
            mRlLeaveAMsg.setVisibility(View.VISIBLE);
            mTvLeaveAMessage.setText(orderDetailsBean.getUser_note());

        }

        if (ObjectUtils.isNotEmpty(orderDetailsBean.getCoupon_price())) {
            mRlCoupon.setVisibility(View.VISIBLE);
            mTvCouponPrice.setText("-￥" + orderDetailsBean.getCoupon_price());
        }
        mTvFreight.setText("￥" + orderDetailsBean.getShipping_price());
        mOrderContentDetailsAdapter = new OrderContentDetailsAdapter();
        mRvGoods.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvGoods.setAdapter(mOrderContentDetailsAdapter);
        mRvGoods.setNestedScrollingEnabled(false);
        mOrderContentDetailsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderDetailsGoodsBean mdata = (OrderDetailsGoodsBean) adapter.getData().get(position);

                switch (view.getId()) {
                    //物流信息
                    case R.id.tv_btn_logistics:
                        if (ObjectUtils.isNotEmpty(mdata.getId())) {
                            addFragment(LogisticsInfoFragment.newInstance(mdata.getId()));
                        }
                        break;
                }
            }
        });
        mOrderContentDetailsAdapter.setNewData(orderDetailsBean.getProducts_list());
    }


    @OnClick({R.id.tv_btn_delete_order, R.id.tv_btn_refund, R.id.tv_btn_return, R.id.tv_btn_exchange, R.id.tv_btn_immediate_payment, R.id.tv_btn_confirm_receipt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_delete_order:
                break;
            case R.id.tv_btn_refund:
                break;
            case R.id.tv_btn_return:
                break;
            case R.id.tv_btn_exchange:
                break;
            case R.id.tv_btn_immediate_payment:
                break;
            case R.id.tv_btn_confirm_receipt:
                break;
        }
    }


}
