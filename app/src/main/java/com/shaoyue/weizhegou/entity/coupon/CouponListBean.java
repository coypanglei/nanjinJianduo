package com.shaoyue.weizhegou.entity.coupon;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;
import com.shaoyue.weizhegou.entity.NewsBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/5/17 0017 14:31
 * <p>
 * 邮箱：434604925@qq.com
 */
public class CouponListBean extends BaseBean {

    @SerializedName("total")
    private int total;

    @SerializedName("per_page")
    private int preNum;

    @SerializedName("current_page")
    private int nowPage;

    @SerializedName("data")
    private List<CouponBean> newsBean;

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

    public List<CouponBean> getNewsBean() {
        return newsBean;
    }

    public void setNewsBean(List<CouponBean> newsBean) {
        this.newsBean = newsBean;
    }
}
