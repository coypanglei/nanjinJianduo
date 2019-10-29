package com.shaoyue.weizhegou.module.credit.adapter.diaocha;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.diaocha.sxDiaoChaBean;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class sqDiaochaAdapter extends BaseQuickAdapter<sxDiaoChaBean.RecordsBean, BaseViewHolder> {


    public sqDiaochaAdapter() {
        super(R.layout.item_sxdiaocha);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final sxDiaoChaBean.RecordsBean item) {
        helper.setText(R.id.tv_id_card, item.getSqrsfzh());
        helper.setText(R.id.khxm,item.getSqrxm());
        helper.setText(R.id.tv_xuhao, helper.getAdapterPosition() + 1 + "");
        helper.setText(R.id.sqsx, item.getSqje());
        helper.setText(R.id.tv_dc_status, item.getDczt());
        helper.setText(R.id.tv_dcmx, item.getSxlx());
        helper.setText(R.id.tv_lc_status, item.getLckz());
        helper.setText(R.id.tv_slr, item.getSlr());
        helper.setText(R.id.tv_ygxd, item.getSfygxd());
        helper.setText(R.id.tv_sxjg, item.getSqjg());
        if (item.isClick()) {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
            helper.setTextColor(R.id.tv_id_card, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.sqsx, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dc_status, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dcmx, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_lc_status, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_ygxd, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_sxjg, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_slr, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.khxm, mContext.getResources().getColor(R.color.color_2c4eb6));
        } else {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));
            helper.setTextColor(R.id.tv_id_card, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.sqsx, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dc_status, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dcmx, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_lc_status, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_sxjg, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_ygxd, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_slr, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.khxm, mContext.getResources().getColor(R.color.color_fc1c1a1d));
        }
    }
}
