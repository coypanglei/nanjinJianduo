package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 2018/11/30.
 */

public class NewsList {
    @SerializedName("total")
    private int total;

    @SerializedName("per_page")
    private int preNum;

    @SerializedName("current_page")
    private int nowPage;


    @SerializedName("last_page")

    private String lastPage;


    @SerializedName("data")
    private List<NewsBean> newsBean;


    public List<NewsBean> getNewsBean() {
        return newsBean;
    }

    public void setNewsBean(List<NewsBean> newsBean) {
        this.newsBean = newsBean;
    }

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

    public String getLastPage() {
        return lastPage;
    }

    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }

    @Override
    public String toString() {
        return "NewsList{" +
                "total=" + total +
                ", preNum=" + preNum +
                ", nowPage=" + nowPage +
                ", lastPage='" + lastPage + '\'' +
                ", newsBean=" + newsBean +
                '}';
    }

}
