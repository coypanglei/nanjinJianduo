package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.ProgressBean;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;


/**
 * 作者：PangLei on 2019/7/12 0012 14:36
 * <p>
 * 邮箱：434604925@qq.com
 */
public class ApplicationProgressAdapter extends BaseQuickAdapter<ProgressBean, BaseViewHolder> {


    public ApplicationProgressAdapter() {
        super(R.layout.item_progress_recycle);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final ProgressBean item) {
        try {
            if (item.isOne()) {
                holder.setVisible(R.id.iv_top_line, false);
            } else {
                holder.setVisible(R.id.iv_top_line, true);
            }

            holder.setVisible(R.id.iv_bottom_line, true);
            holder.setImageResource(R.id.iv_status, R.drawable.icon_blue_yuan);

            holder.setText(R.id.tv_name, item.getTaskname());
            holder.setText(R.id.tv_time, item.getApprovaltime());


            if (ObjectUtils.isNotEmpty(item.getId())) {
                holder.setText(R.id.tv_spr, "审批人:       " + item.getApprovaluser());


                holder.setText(R.id.tv_spjg, "审批结果:  " + item.getApprovaltype());


                holder.setText(R.id.tv_spyj, "审批意见:  " + item.getComment());
                holder.setText(R.id.tv_start_time, "开始时间:  " + item.getStarttime());
            } else {
                holder.setText(R.id.tv_start_time, "审批人:       " + item.getApprovaluser());
                holder.setVisible(R.id.tv_spyj, false);
                holder.setVisible(R.id.tv_spjg, false);
//                holder.setGone(R.id.tv_start_time, false);
                holder.setVisible(R.id.iv_bottom_line, false);
                holder.setImageResource(R.id.iv_status, R.drawable.icon_hui_yuan);
            }
            ImageView iv = holder.getView(R.id.iv_url);
            if (item.getList().size() > 0 && ObjectUtils.isNotEmpty(item.getList().get(0).getUrl())) {
                GlideNewImageLoader.displayImageNoCacheNoDefault(mContext, iv, DomainMgr.getInstance().getBaseUrlImg() + item.getList().get(0).getUrl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
