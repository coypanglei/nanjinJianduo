package com.shaoyue.weizhegou.module.order.adapter;


import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.coupon.CouponBean;
import com.shaoyue.weizhegou.entity.goods.ShopCarBean;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.entity.order.OrderDetailsBean;
import com.shaoyue.weizhegou.entity.order.OrderIdBean;
import com.shaoyue.weizhegou.entity.order.OrderListBean;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.XClick.SingleClick;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：PangLei on 2019/4/12 0012 13:55
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrderAdapter extends BaseQuickAdapter<OrderIdBean, BaseViewHolder> {
    private Activity activity;


    public OrderAdapter(Activity activity) {
        super(R.layout.item_order_all);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderIdBean item) {
        helper.addOnClickListener(R.id.tv_btn_return);
        helper.addOnClickListener(R.id.tv_btn_confirm_receipt);
        helper.addOnClickListener(R.id.tv_btn_delete_order);
        helper.addOnClickListener(R.id.tv_btn_details);
        helper.addOnClickListener(R.id.tv_btn_refund);
        helper.addOnClickListener(R.id.tv_btn_exchange);
        helper.addOnClickListener(R.id.tv_btn_immediate_payment);
        //订单编号
        helper.setText(R.id.tv_order_numbering, "订单编号：" + item.getOrder_sn());
        helper.setText(R.id.tv_time, "日期" + item.getCreate_time());
        // 已完成
        if (item.getComposite_status().equals("103")) {
            helper.setText(R.id.tv_title, "");
            helper.setBackgroundRes(R.id.tv_title, R.drawable.icon_completed);
            //已关闭
        } else if (item.getComposite_status().equals("104")) {
            helper.setText(R.id.tv_title, "");
            helper.setBackgroundRes(R.id.tv_title, R.drawable.icon_closed);
            //已取消
        } else if (item.getComposite_status().equals("110")) {
            helper.setText(R.id.tv_title, "");
            helper.setBackgroundRes(R.id.tv_title, R.drawable.icon_cancelled);
        } else {
            helper.setText(R.id.tv_title, item.getComposite_status_msg());
            helper.setBackgroundRes(R.id.tv_title, R.drawable.transparent_bg);

        }
        helper.setText(R.id.tv_products_price, item.getProducts_price());
        helper.setText(R.id.tv_total_amount, item.getTotal_amount());

        if (item.getCan_delete() == 1) {
            helper.setGone(R.id.tv_btn_delete_order, true);
        } else {
            helper.setGone(R.id.tv_btn_delete_order, false);
        }
        if (item.getCan_exchange() == 1) {
            helper.setGone(R.id.tv_btn_exchange, true);
        } else {
            helper.setGone(R.id.tv_btn_exchange, false);
        }

        if (item.getCan_info() == 1) {
            helper.setGone(R.id.tv_btn_details, true);
        } else {
            helper.setGone(R.id.tv_btn_details, false);
        }
        if (item.getCan_pay() == 1) {
            helper.setGone(R.id.tv_btn_immediate_payment, true);
        } else {
            helper.setGone(R.id.tv_btn_immediate_payment, false);
        }

        if (item.getCan_receipt() == 1) {
            helper.setGone(R.id.tv_btn_confirm_receipt, true);
        } else {
            helper.setGone(R.id.tv_btn_confirm_receipt, false);
        }
        if (item.getCan_return() == 1) {
            helper.setGone(R.id.tv_btn_return, true);
        } else {
            helper.setGone(R.id.tv_btn_return, false);
        }
        if (item.getCan_refund() == 1) {
            helper.setGone(R.id.tv_btn_refund, true);
        } else {
            helper.setGone(R.id.tv_btn_refund, false);
        }
        final OrderContentAdapter mCouponAdapter = new OrderContentAdapter();
        RecyclerView mRvContent = helper.getView(R.id.id_order_inside);
        mRvContent.setLayoutManager(new LinearLayoutManager(activity));
        mRvContent.setAdapter(mCouponAdapter);
        mCouponAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @SingleClick(1500)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ShopCarBean mShopCarBean = (ShopCarBean) adapter.getData().get(position);
                if (mShopCarBean.getIs_on_sale() == 1) {
                    UIHelper.showGoodsDetailsActivity(activity, ContentType.GOODS_DETAILS, new profileBean(mShopCarBean.getProducts_id() + ""));
                } else {
                    UIHelper.showProfileCommonActivity(activity, ContentType.EMPTY_GOODS);
                }
            }
        });

        mCouponAdapter.setNewData(item.getOrder_products());
    }
}
