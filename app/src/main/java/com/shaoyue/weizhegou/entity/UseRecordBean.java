package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

import com.shaoyue.weizhegou.base.BaseBean;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/30 07:25
 */
public class UseRecordBean extends BaseBean {

    @SerializedName("start_time")
    private long startTime;
    @SerializedName("end_time")
    private long endTime;

    @SerializedName("use_time")
    private String useTime;


    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getUseTime() {
        return useTime;
    }

    @Override
    public String toString() {
        return "UseRecordBean{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", useTime='" + useTime + '\'' +
                '}';
    }
}
