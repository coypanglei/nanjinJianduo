package com.shaoyue.weizhegou.entity.message;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/7/16 0016 10:05
 * <p>
 * 邮箱：434604925@qq.com
 */
public class MessageInfoBean extends BaseBean {

    @SerializedName("type")
    private String type;

    @SerializedName("param")
    private String param;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
