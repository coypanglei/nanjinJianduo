package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.ApplicationBean;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class CreditApplicationAdapter extends BaseQuickAdapter<ApplicationBean, BaseViewHolder> {


    public CreditApplicationAdapter() {
        super(R.layout.item_credit_application);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ApplicationBean item) {
        helper.setText(R.id.tv_id_card, item.getSfzh());
        helper.setText(R.id.tv_xuhao, helper.getAdapterPosition() + 1 + "");
        helper.setText(R.id.tv_time, item.getSlrq());
        helper.setText(R.id.tv_sxsq, item.getSqsx());
        helper.setText(R.id.tv_lczt, item.getLczt());
        helper.setText(R.id.tv_dqhj, item.getDqhj());
        helper.setText(R.id.tv_sxjg, item.getSxjg());
        helper.setText(R.id.tv_sxjl, item.getSxjl());
        helper.setText(R.id.tv_slr, item.getSqr());
        helper.setText(R.id.tv_csjg, item.getCsjl());
        helper.setText(R.id.tv_name, item.getKhmc());

        if (item.isClick()) {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
            helper.setTextColor(R.id.tv_id_card, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_time, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_sxsq, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_lczt, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dqhj, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_sxjg, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_sxjl, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_slr, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_csjg, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_name, mContext.getResources().getColor(R.color.color_2c4eb6));
        } else {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));
            helper.setTextColor(R.id.tv_id_card, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_time, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_sxsq, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_lczt, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dqhj, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_sxjg, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_sxjl, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_slr, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_csjg, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_name, mContext.getResources().getColor(R.color.color_fc1c1a1d));
        }
    }
}
