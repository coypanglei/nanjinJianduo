package com.shaoyue.weizhegou.module.user.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.entity.PayRecordBean;


import java.util.List;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/30 12:42
 */
public class PayRecordAdapter extends BaseQuickAdapter<PayRecordBean, BaseViewHolder> {

    public PayRecordAdapter(List<PayRecordBean> list) {
        super(com.shaoyue.weizhegou.R.layout.item_pay_record, list);
    }


    @Override
    protected void convert(BaseViewHolder helper, PayRecordBean item) {
//        helper.setText(R.id.tv_country_name, item.getCountry());
        helper.setText(com.shaoyue.weizhegou.R.id.tv_order_id, item.getOrderId());
        helper.setText(com.shaoyue.weizhegou.R.id.tv_pay_price, "-"+item.getPayPrice());
        helper.setText(com.shaoyue.weizhegou.R.id.tv_pay_time, item.getPayTime());

    }
}