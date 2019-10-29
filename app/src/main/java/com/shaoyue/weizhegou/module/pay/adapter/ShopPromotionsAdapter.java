package com.shaoyue.weizhegou.module.pay.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.PayBean;
import com.shaoyue.weizhegou.entity.coupon.CouponBean;
import com.shaoyue.weizhegou.entity.coupon.SettlementCouponBean;

import java.util.List;


public class ShopPromotionsAdapter extends BaseQuickAdapter<SettlementCouponBean, BaseViewHolder> {


    public ShopPromotionsAdapter(List<SettlementCouponBean> list) {
        super(R.layout.item_order_conpon, list);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final SettlementCouponBean item) {
        helper.setText(R.id.tv_name, item.getCouponName());
        if (item.isSelect()) {
            helper.setImageResource(R.id.iv_select, R.drawable.icon_goods_select);
        } else {
            helper.setImageResource(R.id.iv_select, R.drawable.icon_goods_not_select);
        }

    }


}
