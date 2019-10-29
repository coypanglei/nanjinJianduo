package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 2018/11/28.
 */

public class NewLineBean {

    @SerializedName("title")
    private String title;

    @SerializedName("lineCounts")
    private String lineCounts;

    @SerializedName("isVip")
    private String isVip;

    @SerializedName("subTitle")
    private String subTitle;

    @SerializedName("lines")
    List<LineBean> lineBeans;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getLineCounts() {
        return lineCounts;
    }

    public void setLineCounts(String lineCounts) {
        this.lineCounts = lineCounts;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<LineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<LineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    @Override
    public String toString() {
        return "NewLineBean{" +
                "title='" + title + '\'' +
                ", lineCounts='" + lineCounts + '\'' +
                ", isVip='" + isVip + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", lineBeans=" + lineBeans +
                '}';
    }
}
