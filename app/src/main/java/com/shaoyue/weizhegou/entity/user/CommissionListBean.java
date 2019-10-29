package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/5/23 0023 09:57
 * <p>
 * 邮箱：434604925@qq.com
 */
public class CommissionListBean extends BaseBean {

    @SerializedName("total")
    private int total;

    @SerializedName("data")
    private List<CommissionDetailsBean> data;

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

    public List<CommissionDetailsBean> getData() {
        return data;
    }

    public void setData(List<CommissionDetailsBean> data) {
        this.data = data;
    }
}
