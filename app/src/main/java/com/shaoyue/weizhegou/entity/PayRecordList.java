package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/30 12:53
 */
public class PayRecordList {
    @SerializedName("total")
    private int total;

    @SerializedName("per_page")
    private int preNum;

    @SerializedName("current_page")
    private int nowPage;

    @SerializedName("list")
    private List<PayRecordBean> listData;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPreNum() {
        return preNum;
    }

    public void setPreNum(int preNum) {
        this.preNum = preNum;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public List<PayRecordBean> getListData() {
        return listData;
    }

    public void setListData(List<PayRecordBean> listData) {
        this.listData = listData;
    }


    @Override
    public String toString() {
        return "PayRecordList{" +
                "total=" + total +
                ", preNum=" + preNum +
                ", nowPage=" + nowPage +
                ", listData=" + listData +
                '}';
    }
}
