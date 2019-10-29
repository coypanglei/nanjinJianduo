package com.shaoyue.weizhegou.module.credit.fragment.apply.diyao.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.GongsiDanbao;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class ZiRanDanBaoThreeAdapter extends BaseQuickAdapter<GongsiDanbao.RecordsBean, BaseViewHolder> {


    public ZiRanDanBaoThreeAdapter() {
        super(R.layout.item_zhiya_gongsi);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final GongsiDanbao.RecordsBean item) {
        helper.setText(R.id.tv_dbgsmc, item.getDbgsmc());
        helper.setText(R.id.tv_xuhao, helper.getAdapterPosition() + 1 + "");
        helper.setText(R.id.tv_bcdbje, item.getBcdbje()+"");
        helper.setText(R.id.tv_fdbcs, item.getFdbcs());
        helper.setText(R.id.tv_ms, item.getDescription());

        if (item.isClick()) {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
            helper.setTextColor(R.id.tv_dbgsmc, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_bcdbje, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_fdbcs, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_ms, mContext.getResources().getColor(R.color.color_2c4eb6));

        } else {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));
            helper.setTextColor(R.id.tv_dbgsmc, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_bcdbje, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_fdbcs, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_ms, mContext.getResources().getColor(R.color.color_fc1c1a1d));



        }
    }
}
