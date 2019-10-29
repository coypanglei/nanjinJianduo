package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/7/16 0016 10:40
 * <p>
 * 邮箱：434604925@qq.com
 */
public class WalletDetailsBean extends BaseBean {

    // "balance":"0.00",//账户余额
    //        "in_amount":"0.00",//总收入
    //        "out_amount":"0.00"//总支出
    @SerializedName("balance")
    private String balance;

    @SerializedName("in_amount")
    private String in_amount;


    @SerializedName("out_amount")
    private String out_amount;


    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getIn_amount() {
        return in_amount;
    }

    public void setIn_amount(String in_amount) {
        this.in_amount = in_amount;
    }

    public String getOut_amount() {
        return out_amount;
    }

    public void setOut_amount(String out_amount) {
        this.out_amount = out_amount;
    }
}
