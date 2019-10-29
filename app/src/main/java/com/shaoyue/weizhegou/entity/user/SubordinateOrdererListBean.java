package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/5/24 0024 15:43
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SubordinateOrdererListBean extends BaseBean {
    @SerializedName("total")
    private int total;

    @SerializedName("data")
    private List<SubordinateOrdererBean> data;

    @SerializedName("per_page")
    private int per_page;

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<SubordinateOrdererBean> getData() {
        return data;
    }

    public void setData(List<SubordinateOrdererBean> data) {
        this.data = data;
    }
}
