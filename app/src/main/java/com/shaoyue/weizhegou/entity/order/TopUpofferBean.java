package com.shaoyue.weizhegou.entity.order;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/7/6 0006 13:46
 * <p>
 * 邮箱：434604925@qq.com
 */
public class TopUpofferBean extends BaseBean {

    //    "recharge_recharge":"15",//充值金额
    //            "give_coupon":"",//赠送优惠券
    //            "give_value":"赠送54元"//赠送金额

    @SerializedName("recharge_recharge")
    private String recharge_recharge;

    @SerializedName("give_coupon")
    private String give_coupon;

    @SerializedName("give_value")
    private String give_value;

    public String getRecharge_recharge() {
        return recharge_recharge;
    }

    public void setRecharge_recharge(String recharge_recharge) {
        this.recharge_recharge = recharge_recharge;
    }

    public String getGive_coupon() {
        return give_coupon;
    }

    public void setGive_coupon(String give_coupon) {
        this.give_coupon = give_coupon;
    }

    public String getGive_value() {
        return give_value;
    }

    public void setGive_value(String give_value) {
        this.give_value = give_value;
    }
}
