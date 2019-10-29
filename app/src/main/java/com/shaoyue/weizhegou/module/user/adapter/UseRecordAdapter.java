package com.shaoyue.weizhegou.module.user.adapter;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.entity.UseRecordBean;


import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/31 10:22
 */
public class UseRecordAdapter extends BaseQuickAdapter<UseRecordBean, BaseViewHolder> {


    public UseRecordAdapter(List<UseRecordBean> data) {
        super(com.shaoyue.weizhegou.R.layout.item_use_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UseRecordBean item) {
        helper.setText(com.shaoyue.weizhegou.R.id.tv_speed_use_time, item.getUseTime());

        String startTime = TimeUtils.millis2String(item.getStartTime() * 1000,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        helper.setText(com.shaoyue.weizhegou.R.id.tv_speed_use_start_time, startTime);
    }


}