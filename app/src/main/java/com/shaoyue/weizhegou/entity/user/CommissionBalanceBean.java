package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：PangLei on 2019/6/27 0027 11:43
 * <p>
 * 邮箱：434604925@qq.com
 */
public class CommissionBalanceBean {

    @SerializedName("commission_balance")
    private String commission_balance;

    public String getCommission_balance() {
        return commission_balance;
    }

    public void setCommission_balance(String commission_balance) {
        this.commission_balance = commission_balance;
    }
}
