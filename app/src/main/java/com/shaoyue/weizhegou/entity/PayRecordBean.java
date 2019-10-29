package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

import com.shaoyue.weizhegou.base.BaseBean;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/30 12:45
 */
public class PayRecordBean extends BaseBean {

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("true_money")
    private String payPrice;

    @SerializedName("create_time_show")
    private String payTime;

    public String getOrderId() {
        return orderId;
    }

    public String getPayPrice() {
        return payPrice;
    }

    public String getPayTime() {
        return payTime;
    }

    @Override
    public String toString() {
        return "PayRecordBean{" +
                "orderId='" + orderId + '\'' +
                ", payPrice='" + payPrice + '\'' +
                ", payTime='" + payTime + '\'' +
                '}';
    }
}
