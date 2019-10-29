package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/7/2 0002 11:35
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrderCountBean extends BaseBean {

    //all=0
    //finish=0
    //notPay=0
    //pay=0

    @SerializedName("all")
    private int all;

    @SerializedName("finish")
    private int finish;

    @SerializedName("noPay")
    private int noPay;

    @SerializedName("pay")
    private int pay;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getNoPay() {
        return noPay;
    }

    public void setNoPay(int noPay) {
        this.noPay = noPay;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }
}
