package com.shaoyue.weizhegou.module.account.adapter;


import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.coupon.CouponBean;
import com.shaoyue.weizhegou.entity.message.MessageBean;

/**
 * 作者：PangLei on 2019/4/12 0012 13:55
 * <p>
 * 邮箱：434604925@qq.com
 */
public class MessageCenterAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {


    public MessageCenterAdapter() {
        super(R.layout.item_message_center);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        helper.setText(R.id.tv_time, item.getCreate_time());
        helper.setText(R.id.tv_content, item.getContent());
        helper.addOnClickListener(R.id.iv_select);
        helper.addOnClickListener(R.id.ll_click);
        if (item.getSelect() == 0) {
            helper.setImageResource(R.id.iv_select, R.drawable.icon_goods_not_select);
        } else {
            helper.setImageResource(R.id.iv_select, R.drawable.icon_goods_select);
        }

        if (item.getState() == 0) {
            helper.setTextColor(R.id.tv_time, mContext.getResources().getColor(R.color.color_4a4a4a));
            helper.setTextColor(R.id.tv_content, mContext.getResources().getColor(R.color.color_4a4a4a));
        } else {
            helper.setTextColor(R.id.tv_time, mContext.getResources().getColor(R.color.color_a4a4a4));
            helper.setTextColor(R.id.tv_content, mContext.getResources().getColor(R.color.color_a4a4a4));
        }


    }
}
