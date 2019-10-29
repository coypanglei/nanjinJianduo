package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：PangLei on 2019/7/8 0008 09:28
 * <p>
 * 邮箱：434604925@qq.com
 */
public class PayTypeInfoBean {

    // "wecaht":"1",//1开启 0关闭
    //        "alipay":"1",
    //        "is_default":"alipay"//默认


    @SerializedName("wechat_pay")
    private String wechat;

    @SerializedName("alipay")
    private String alipay;

    @SerializedName("is_default")
    private String is_default;

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }
}
