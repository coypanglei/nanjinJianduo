package com.shaoyue.weizhegou.module.owner.adapter;


import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.order.TopUpofferBean;
import com.shaoyue.weizhegou.entity.user.CommissionDetailsBean;


/**
 * 作者：PangLei on 2019/5/23 0023 09:53
 * <p>
 * 邮箱：434604925@qq.com
 */
public class RechargeMoneyAdapter extends BaseQuickAdapter<TopUpofferBean, BaseViewHolder> {

    public RechargeMoneyAdapter() {
        super(R.layout.item_top_up_offer);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopUpofferBean item) {

        if (ObjectUtils.isNotEmpty(item.getRecharge_recharge())) {
            helper.setText(R.id.tv_recharge,item.getRecharge_recharge());
            helper.setGone(R.id.tv_recharge, true);
        } else {
            helper.setGone(R.id.tv_recharge, false);
        }
        if (ObjectUtils.isNotEmpty(item.getGive_coupon())) {
            helper.setText(R.id.tv_give_coupon,item.getGive_coupon());
            helper.setGone(R.id.tv_give_coupon, true);
        } else {
            helper.setGone(R.id.tv_give_coupon, false);
        }
        if (ObjectUtils.isNotEmpty(item.getGive_value())) {
            helper.setText(R.id.tv_give_value,item.getGive_value());
            helper.setGone(R.id.tv_give_value, true);
        } else {
            helper.setGone(R.id.tv_give_value, false);
        }

    }
}
