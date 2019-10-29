package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

import com.shaoyue.weizhegou.base.BaseBean;


/**
 * Created by USER on 2018/12/21.
 */

public class PayBean extends BaseBean {

    //    {
//        "sn": "pak_1_month_apple",
//            "value": "2592000",
//            "price": "5.99",
//            "key": "time",
//            "name": "1 month plan",
//            "sub_name": "",
//            "price_symbol": "$",
//            "_apple_pay_sn": "apple_pak_1_month",
//            "_per_mon_price": "9.99",
//            "_origin_price": "",
//            "_save_percent": "67%",
//            "_save_money": "20.boot_page",
//            "_pak_tips": " FYI: The 1-year plan includes maximum savings and a full refund within 7 days. ",
//            "_default": "false",
//            "_popular": "false",
//            "_sum_price_round": "$9.99 Every 1 months",
//            "_how_long_day": "30 Day",
//            "_how_long_simp": "1 Month",
//            "_how_long": "1 months plan"
//            reduce_price
//    }
    @SerializedName("sn")
    private String sn;

    @SerializedName("value")
    private String value;

    @SerializedName("price")
    private String price;

    @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String name;

    @SerializedName("sub_name")
    private String subName;

    @SerializedName("price_symbol")
    private String priceSymbol;


    @SerializedName("_apple_pay_sn")
    private String applePaySn;

    @SerializedName("_per_mon_price")
    private String perMonPrice;

    @SerializedName("_origin_price")
    private String originPrice;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getPriceSymbol() {
        return priceSymbol;
    }

    public void setPriceSymbol(String priceSymbol) {
        this.priceSymbol = priceSymbol;
    }

    public String getApplePaySn() {
        return applePaySn;
    }

    public void setApplePaySn(String applePaySn) {
        this.applePaySn = applePaySn;
    }

    public String getPerMonPrice() {
        return perMonPrice;
    }

    public void setPerMonPrice(String perMonPrice) {
        this.perMonPrice = perMonPrice;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getSavePercent() {
        return savePercent;
    }

    public void setSavePercent(String savePercent) {
        this.savePercent = savePercent;
    }

    public String getSaveMoney() {
        return saveMoney;
    }

    public void setSaveMoney(String saveMoney) {
        this.saveMoney = saveMoney;
    }

    public String getPakTips() {
        return pakTips;
    }

    public void setPakTips(String pakTips) {
        this.pakTips = pakTips;
    }

    public String getmDefault() {
        return mDefault;
    }

    public void setmDefault(String mDefault) {
        this.mDefault = mDefault;
    }

    public String getPopular() {
        return popular;
    }

    public void setPopular(String popular) {
        this.popular = popular;
    }

    public String getSumPriceRound() {
        return sumPriceRound;
    }

    public void setSumPriceRound(String sumPriceRound) {
        this.sumPriceRound = sumPriceRound;
    }

    public String getHowLongDay() {
        return howLongDay;
    }

    public void setHowLongDay(String howLongDay) {
        this.howLongDay = howLongDay;
    }

    public String getHowLongSimp() {
        return howLongSimp;
    }

    public void setHowLongSimp(String howLongSimp) {
        this.howLongSimp = howLongSimp;
    }

    public String getHowLong() {
        return howLong;
    }

    public void setHowLong(String howLong) {
        this.howLong = howLong;
    }

    @SerializedName("_save_percent")
    private String savePercent;

    @SerializedName("_save_money")
    private String saveMoney;

    @SerializedName("_pak_tips")
    private String pakTips;

    @SerializedName("_default")
    private String mDefault;

    @SerializedName("_popular")
    private String popular;

    @SerializedName("_sum_price_round")
    private String sumPriceRound;

    @SerializedName("_how_long_day")
    private String howLongDay;

    @SerializedName("_how_long_simp")
    private String howLongSimp;

    @SerializedName("_how_long")
    private String howLong;

    public String getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(String reducePrice) {
        this.reducePrice = reducePrice;
    }

    @SerializedName("reduce_price")
    private String reducePrice;

    public String getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(String payPrice) {
        this.payPrice = payPrice;
    }

    @SerializedName("pay_price")
    private String payPrice;


    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
