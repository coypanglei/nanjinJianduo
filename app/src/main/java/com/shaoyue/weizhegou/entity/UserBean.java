package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;


public class UserBean extends BaseBean {

    @SerializedName("vip_time")
    private long vipTime;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    @SerializedName("is_read")
    private String isRead;


    public long getVipTime() {
        return vipTime;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "vipTime=" + vipTime +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
