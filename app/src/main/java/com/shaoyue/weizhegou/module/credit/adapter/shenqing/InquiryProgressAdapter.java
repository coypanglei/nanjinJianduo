package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.dhgl.XcjyZxcxBean;


/**
 * 作者：PangLei on 2019/7/12 0012 14:36
 * <p>
 * 邮箱：434604925@qq.com
 */
public class InquiryProgressAdapter extends BaseQuickAdapter<XcjyZxcxBean.CxjlAndroidBean, BaseViewHolder> {


    public InquiryProgressAdapter() {
        super(R.layout.item_progress_inquiry);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final XcjyZxcxBean.CxjlAndroidBean item) {
        holder.setText(R.id.tv_time, item.get查询日期());
        holder.setText(R.id.tv_cxyy, item.get查询原因());
        holder.setText(R.id.tv_renyuan, item.get查询操作员());
        try {
            if (0 == holder.getAdapterPosition()) {
                holder.setVisible(R.id.iv_top_line, false);
            } else {
                holder.setVisible(R.id.iv_top_line, true);
            }

            holder.setVisible(R.id.iv_bottom_line, true);
            holder.setImageResource(R.id.iv_status, R.drawable.icon_blue_yuan);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
