package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

public class loginUserDepartsBean extends BaseBean {

    @SerializedName("departName")
    private String departName;

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
}
