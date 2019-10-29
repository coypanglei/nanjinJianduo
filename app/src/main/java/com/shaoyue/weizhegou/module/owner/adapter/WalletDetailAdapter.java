package com.shaoyue.weizhegou.module.owner.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.user.CommissionDetailsBean;


/**
 * 作者：PangLei on 2019/5/23 0023 09:53
 * <p>
 * 邮箱：434604925@qq.com
 */
public class WalletDetailAdapter extends BaseQuickAdapter<CommissionDetailsBean, BaseViewHolder> {


    public WalletDetailAdapter() {
        super(R.layout.item_wallet_details);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommissionDetailsBean item) {
        helper.setText(R.id.tv_balance, item.getBalance());
        helper.setText(R.id.tv_time, item.getCreateTime());
        if (item.getInorOut() == 1) {
            helper.setTextColor(R.id.tv_price, mContext.getResources().getColor(R.color.color_ff4a0b));
            helper.setText(R.id.tv_price, "+" + item.getAmount());
        } else if (item.getInorOut() == 2) {
            helper.setTextColor(R.id.tv_price, mContext.getResources().getColor(R.color.color_17c200));
            helper.setText(R.id.tv_price, "-" + item.getAmount());
        }


    }
}
