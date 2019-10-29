package com.shaoyue.weizhegou.module.user.adapter;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.NewsBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/31 10:22
 */
public class NewsRecordAdapter extends BaseQuickAdapter<NewsBean, BaseViewHolder> {


    public NewsRecordAdapter(List<NewsBean> data) {
        super(R.layout.item_news_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean item) {
        LogUtils.e(item.getCreateTime());
        String startTime = TimeUtils.millis2String(Long.valueOf(item.getCreateTime()) * 1000,
                new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        LogUtils.e(startTime);
        helper.setText(R.id.tv_speed_news_time, startTime);

        helper.setText(R.id.tv_content, item.getText());
    }


}