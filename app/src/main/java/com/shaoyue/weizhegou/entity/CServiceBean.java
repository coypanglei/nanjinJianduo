package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;


public class CServiceBean extends BaseBean {

    @SerializedName("service_phone")
    private String phone;

    @SerializedName("service_weibo")
    private String weibo;

    @SerializedName("service_qq")
    private String qq;

    @SerializedName("service_weixin")
    private String weixin;


    public String getPhone() {
        return phone;
    }

    public String getWeibo() {
        return weibo;
    }

    public String getQq() {
        return qq;
    }

    public String getWeixin() {
        return weixin;
    }


    @Override
    public String toString() {
        return "CServiceBean{" +
                "phone='" + phone + '\'' +
                ", weibo='" + weibo + '\'' +
                ", qq='" + qq + '\'' +
                ", weixin='" + weixin + '\'' +
                '}';
    }
}
