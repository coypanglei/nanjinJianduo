package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LineList {

    private int total;

    @SerializedName("pre_num")
    private int preNum;

    @SerializedName("now_page")
    private int nowPage;

    @SerializedName("lines")
    private List<NewLineBean> lineList;


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

    public List<NewLineBean> getLineList() {
        return lineList;
    }

    public void setLineList(List<NewLineBean> lineList) {
        this.lineList = lineList;
    }

    @Override
    public String toString() {
        return "LineList{" +
                "total=" + total +
                ", preNum=" + preNum +
                ", nowPage=" + nowPage +
                ", lineList=" + lineList +
                '}';
    }
}
