package com.shaoyue.weizhegou.entity.message;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/7/16 0016 15:28
 * <p>
 * 邮箱：434604925@qq.com
 */
public class MessageListBean extends BaseBean {
    //"total":1,
    //        "per_page":15,
    //        "current_page":1,
    //        "last_page":1,

    @SerializedName("total")
    private int total;

    @SerializedName("per_page")
    private int pre_page;


    @SerializedName("last_page")
    private String last_page;

    @SerializedName("current_page")
    private String current_page;


    @SerializedName("data")
    private List<MessageBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPre_page() {
        return pre_page;
    }

    public void setPre_page(int pre_page) {
        this.pre_page = pre_page;
    }

    public String getLast_page() {
        return last_page;
    }

    public void setLast_page(String last_page) {
        this.last_page = last_page;
    }

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public List<MessageBean> getData() {
        return data;
    }

    public void setData(List<MessageBean> data) {
        this.data = data;
    }
}
