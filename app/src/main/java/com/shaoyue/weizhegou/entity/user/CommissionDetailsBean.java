package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/5/23 0023 09:21
 * <p>
 * 邮箱：434604925@qq.com
 */
public class CommissionDetailsBean extends BaseBean {


    @SerializedName("InorOut")
    private int inorOut;

    @SerializedName("amount")
    private String amount;

    @SerializedName("balance")
    private String balance;

    @SerializedName("create_time")
    private String createTime;

    @SerializedName("remark")
    private String remark;

    public int getInorOut() {
        return inorOut;
    }

    public void setInorOut(int inorOut) {
        this.inorOut = inorOut;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
