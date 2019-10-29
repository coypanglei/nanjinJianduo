package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

public class loginUserBean extends BaseBean {

    //"id": "e9ca23d68d884d4ebb19d07889727dae",
    //			"username": "admin",
    //			"realname": "管理员",

    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
