package com.shaoyue.weizhegou.module.credit.fragment.apply.diyao.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.DiyaDanBaoListBean;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class ZiRanDanBaoTwoAdapter extends BaseQuickAdapter<DiyaDanBaoListBean.RecordsBean, BaseViewHolder> {


    public ZiRanDanBaoTwoAdapter() {
        super(R.layout.item_zhiya);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final DiyaDanBaoListBean.RecordsBean item) {
        helper.setText(R.id.tv_dysyr, item.getDywsyr());
        helper.setText(R.id.tv_xuhao, helper.getAdapterPosition() + 1 + "");
        helper.setText(R.id.tv_syrqzh, item.getSyrqzh());
        helper.setText(R.id.tv_yjkrgx, item.getYjkrgx());
        helper.setText(R.id.tv_lxdh, item.getLxdh());
        helper.setText(R.id.tv_fwlz, item.getFwzl());
        helper.setText(R.id.tv_dywqzh, item.getDywqzh());

        if (item.isClick()) {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
            helper.setTextColor(R.id.tv_dysyr, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_yjkrgx, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_lxdh, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_fwlz, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dywqzh, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_syrqzh, mContext.getResources().getColor(R.color.color_2c4eb6));
        } else {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));
            helper.setTextColor(R.id.tv_dysyr, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_yjkrgx, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_lxdh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_fwlz, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dywqzh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_syrqzh, mContext.getResources().getColor(R.color.color_fc1c1a1d));


        }
    }
}
