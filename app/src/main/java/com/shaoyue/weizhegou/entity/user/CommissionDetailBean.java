package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/7/11 0011 11:11
 * <p>
 * 邮箱：434604925@qq.com
 */
public class CommissionDetailBean extends BaseBean {
    // "commission_income":"10000000.00",//佣金总收入
    //        "commission_balance":"9995975.00",//可提现佣金
    //        "to_sum_amount":1550,//待收益
    //        "buy_amount":1550,//订货商总计
    //        "sale_amount":0,//分销商总总计
    //        "to_buy_amount":1550,//订货商待收益
    //        "to_sale_amount":0,//分销商待收益
    //        "finish_buy_order":0,//订货商完成订单数
    //        "finish_sale_order":0,//分销商完成订单数
    //        "trading_buy_order":2,//订货商交易中订单
    //        "trading_sale_order":0//分销商交易中订单

    @SerializedName("commission_income")
    private String commission_income;

    @SerializedName("commission_balance")
    private String commission_balance;

    @SerializedName("to_sum_amount")
    private String to_sum_amount;

    @SerializedName("buy_amount")
    private String buy_amount;


    @SerializedName("sale_amount")
    private String sale_amount;

    @SerializedName("to_buy_amount")
    private String to_buy_amount;

    @SerializedName("to_sale_amount")
    private String to_sale_amount;

    @SerializedName("finish_buy_order")
    private String finish_buy_order;

    @SerializedName("finish_sale_order")
    private String finish_sale_order;

    @SerializedName("trading_buy_order")
    private String trading_buy_order;

    @SerializedName("trading_sale_order")
    private String trading_sale_order;

    public String getCommission_income() {
        return commission_income;
    }

    public void setCommission_income(String commission_income) {
        this.commission_income = commission_income;
    }

    public String getCommission_balance() {
        return commission_balance;
    }

    public void setCommission_balance(String commission_balance) {
        this.commission_balance = commission_balance;
    }

    public String getTo_sum_amount() {
        return to_sum_amount;
    }

    public void setTo_sum_amount(String to_sum_amount) {
        this.to_sum_amount = to_sum_amount;
    }

    public String getBuy_amount() {
        return buy_amount;
    }

    public void setBuy_amount(String buy_amount) {
        this.buy_amount = buy_amount;
    }

    public String getSale_amount() {
        return sale_amount;
    }

    public void setSale_amount(String sale_amount) {
        this.sale_amount = sale_amount;
    }

    public String getTo_buy_amount() {
        return to_buy_amount;
    }

    public void setTo_buy_amount(String to_buy_amount) {
        this.to_buy_amount = to_buy_amount;
    }

    public String getTo_sale_amount() {
        return to_sale_amount;
    }

    public void setTo_sale_amount(String to_sale_amount) {
        this.to_sale_amount = to_sale_amount;
    }

    public String getFinish_buy_order() {
        return finish_buy_order;
    }

    public void setFinish_buy_order(String finish_buy_order) {
        this.finish_buy_order = finish_buy_order;
    }

    public String getFinish_sale_order() {
        return finish_sale_order;
    }

    public void setFinish_sale_order(String finish_sale_order) {
        this.finish_sale_order = finish_sale_order;
    }

    public String getTrading_buy_order() {
        return trading_buy_order;
    }

    public void setTrading_buy_order(String trading_buy_order) {
        this.trading_buy_order = trading_buy_order;
    }

    public String getTrading_sale_order() {
        return trading_sale_order;
    }

    public void setTrading_sale_order(String trading_sale_order) {
        this.trading_sale_order = trading_sale_order;
    }
}
