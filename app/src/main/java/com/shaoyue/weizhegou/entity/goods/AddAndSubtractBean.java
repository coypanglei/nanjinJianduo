package com.shaoyue.weizhegou.entity.goods;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：PangLei on 2019/6/18 0018 15:16
 * <p>
 * 邮箱：434604925@qq.com
 */
public class AddAndSubtractBean {

    //    "store_count":5,
    //        "starting_quantity":3,
    //        "purchase_limit":3,
    //        "inc_msg":"商品已到达限购数量",
    //        "dec_msg":"",
    //        "default_num":3

    @SerializedName("inc_msg")
    private String incMsg;

    @SerializedName("dec_msg")
    private String decMsg;


    public String getIncMsg() {
        return incMsg;
    }

    public void setIncMsg(String incMsg) {
        this.incMsg = incMsg;
    }

    public String getDecMsg() {
        return decMsg;
    }

    public void setDecMsg(String decMsg) {
        this.decMsg = decMsg;
    }
}
