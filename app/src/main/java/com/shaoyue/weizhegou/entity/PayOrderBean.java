package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;


/**
 * Created by USER on 2018/12/24.
 */

public class PayOrderBean extends BaseBean {

    @SerializedName("alipay_sign")
    private String alipaySign;

    @SerializedName("pay_url")
    private String payUrl;

    @SerializedName("order_id")
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getAlipaySign() {

        return alipaySign;
    }

    public void setAlipaySign(String alipaySign) {
        this.alipaySign = alipaySign;
    }
}
