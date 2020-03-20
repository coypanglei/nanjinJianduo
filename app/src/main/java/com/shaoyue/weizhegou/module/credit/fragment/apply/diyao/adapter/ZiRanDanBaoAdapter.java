package com.shaoyue.weizhegou.module.credit.fragment.apply.diyao.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.ZiRanDanBaoListBean;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class ZiRanDanBaoAdapter extends BaseQuickAdapter<ZiRanDanBaoListBean.RecordsBean, BaseViewHolder> {


    public ZiRanDanBaoAdapter() {
        super(R.layout.item_danbao_ziran);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ZiRanDanBaoListBean.RecordsBean item) {
        helper.setText(R.id.tv_sfzh, item.getSfzh());
        helper.setText(R.id.tv_xuhao, helper.getAdapterPosition() + 1 + "");
        helper.setText(R.id.tv_ms, item.getDescription());
        helper.setText(R.id.tv_xm, item.getXm());
        helper.setText(R.id.tv_hyzk, item.getHyzk());
        helper.setText(R.id.tv_dbrpo, item.getDbrpo());
        helper.setText(R.id.tv_dbrposfz,item.getDbrposfzh());
        helper.setText(R.id.tv_yjkrgx,item.getGx());

        if (item.isClick()) {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
            helper.setTextColor(R.id.tv_sfzh, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_ms,  mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_xm, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dbrpo, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dbrposfz, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_hyzk, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_yjkrgx, mContext.getResources().getColor(R.color.color_2c4eb6));
        } else {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));
            helper.setTextColor(R.id.tv_sfzh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_ms,  mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_xm, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dbrpo, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dbrposfz, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_hyzk, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_yjkrgx, mContext.getResources().getColor(R.color.color_fc1c1a1d));

        }
    }
}
