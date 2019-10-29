package com.shaoyue.weizhegou.module.pay.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.PayBean;

import java.util.List;


public class PayListAdapter extends BaseQuickAdapter<PayBean, BaseViewHolder> {


    public PayListAdapter(List<PayBean> list) {
        super(R.layout.item_new_pay_list, list);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final PayBean item) {
        if (item.getmDefault().equals("true")) {
            helper.getView(R.id.rl_pay_bg).setBackgroundResource(R.drawable.icon_pay_bg);
        } else {
            helper.getView(R.id.rl_pay_bg).setBackgroundResource(R.drawable.speed_button_bg);
        }
        int position = helper.getAdapterPosition();
        if (position == 0) {
            helper.getView(R.id.iv_pay_icon).setBackgroundResource(R.drawable.icon_pay_thirty_days);
        } else if (position == 1) {
            helper.getView(R.id.iv_pay_icon).setBackgroundResource(R.drawable.icon_pay_ninty_days);
        } else if (position == 2) {
            helper.getView(R.id.iv_pay_icon).setBackgroundResource(R.drawable.icon_pay_days_3);
        } else if (position == 3) {
            helper.getView(R.id.iv_pay_icon).setBackgroundResource(R.drawable.icon_pay_days_4);
        }
        helper.setText(R.id.tv_name, item.getName());
        if (!"".equals(item.getReducePrice())) {
            helper.setVisible(R.id.jian_price, true);
            helper.setText(R.id.jian_price, item.getReducePrice());
        }

        helper.setText(R.id.tv_sub_name, item.getSubName());
        helper.setText(R.id.tv_price_type, item.getPriceSymbol());
        helper.setText(R.id.tv_price, item.getPrice());
        if ("true".equals(item.getPopular())) {
            helper.setVisible(R.id.iv_tuijian, true);
        }
    }


}
