package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.FamilyBean;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class FamilyInfoAdapter extends BaseQuickAdapter<FamilyBean, BaseViewHolder> {


    public FamilyInfoAdapter() {
        super(R.layout.item_family_info);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final FamilyBean item) {
        helper.setText(R.id.tv_sfzh, item.getSfzh());
        helper.setText(R.id.tv_xuhao, helper.getAdapterPosition() + 1 + "");
        helper.setText(R.id.tv_js, item.getJs());
        helper.setText(R.id.tv_name, item.getXm());
        helper.setText(R.id.tv_xb, item.getXb());
        helper.setText(R.id.tv_nl, item.getNl());
        helper.setText(R.id.tv_hyzk, item.getHyzk());
        helper.setText(R.id.tv_jkzk, item.getJkzk());
        helper.setText(R.id.tv_lxdh, item.getLxdh());
        helper.setText(R.id.tv_zy, item.getZy());

        if (item.isClick()) {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
            helper.setTextColor(R.id.tv_sfzh, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_js,  mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_name, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_xb, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_nl, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_hyzk, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_jkzk, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_lxdh, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_zy, mContext.getResources().getColor(R.color.color_2c4eb6));
        } else {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));
            helper.setTextColor(R.id.tv_sfzh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_js,  mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_name, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_xb, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_nl, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_hyzk, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_jkzk, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_lxdh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_zy, mContext.getResources().getColor(R.color.color_fc1c1a1d));

        }
    }
}
