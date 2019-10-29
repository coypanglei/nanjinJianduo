package com.shaoyue.weizhegou.module.order.adapter;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liaoinstan.springview.utils.DensityUtil;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.order.LogisticsInfoBean;


/**
 * 作者：PangLei on 2019/7/12 0012 14:36
 * <p>
 * 邮箱：434604925@qq.com
 */
public class LogisticsInfoAdapter extends BaseQuickAdapter<LogisticsInfoBean, BaseViewHolder> {


    public LogisticsInfoAdapter() {
        super(R.layout.item_logistics_recycle);
    }

    @Override
    protected void convert(final BaseViewHolder holder, LogisticsInfoBean item) {
        try {

            holder.setText(R.id.tv_status, item.getStatus());
            holder.setText(R.id.tv_time, item.getTime());//时间
            final TextView textView = holder.getView(R.id.tv_content);


            if (holder.getLayoutPosition() == 0) {
                holder.setTextColor(R.id.tv_time, mContext.getResources().getColor(R.color.color_1d95a4));//时间
                holder.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.color_1d95a4));
                textView.setTextColor(mContext.getResources().getColor(R.color.color_1d95a4));//时间
                textView.setText(item.getContext());
                //红色的圆点
                ImageView iv = holder.getView(R.id.iv_status);
//                holder.setImageResource(R.id.iv_status, R.drawable.shape_circle_blue);
                RelativeLayout.LayoutParams pointParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(10), DensityUtil.dp2px(10));
                pointParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                iv.setLayoutParams(pointParams);
//
//                holder.tv_time.setTextColor(context.getResources().getColor(R.color.newPrimary));
//                holder.tv_status.setTextColor(context.getResources().getColor(R.color.newPrimary));

                //灰色的竖线
                RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(1), ViewGroup.LayoutParams.MATCH_PARENT);
                lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status);//让直线置于圆点下面
                lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                ImageView mIvLine = holder.getView(R.id.iv_line);
                mIvLine.setLayoutParams(lineParams);

            } else {
                holder.setTextColor(R.id.tv_time, mContext.getResources().getColor(R.color.color_a4a4a4));//时间
                holder.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.color_a4a4a4));
                textView.setTextColor(mContext.getResources().getColor(R.color.color_a4a4a4));//时间
                textView.setText(item.getContext());
//                holder.iv_status.setBackgroundResource(R.mipmap.ic_logistics_bottom);
                holder.setImageResource(R.id.iv_status, R.drawable.shape_circle_logistics_gray);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DensityUtil.dp2px(10), DensityUtil.dp2px(10));
                lp.addRule(RelativeLayout.CENTER_IN_PARENT);
                ImageView iv = holder.getView(R.id.iv_status);
                iv.setLayoutParams(lp);
//
//                holder.tv_time.setTextColor(context.getResources().getColor(R.color.textColor_9b));
//                holder.tv_status.setTextColor(context.getResources().getColor(R.color.textColor_9b));

                //灰色的竖线
                RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(1), ViewGroup.LayoutParams.MATCH_PARENT);
//                lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status);//让直线置于圆点下面
                lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                ImageView mIvLine = holder.getView(R.id.iv_line);
                mIvLine.setLayoutParams(lineParams);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}
