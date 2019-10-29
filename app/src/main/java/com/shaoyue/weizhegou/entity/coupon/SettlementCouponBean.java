package com.shaoyue.weizhegou.entity.coupon;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/5/17 0017 11:43
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SettlementCouponBean extends BaseBean {


    @SerializedName("coupon_name")
    private String couponName;

    @SerializedName("coupon_value")
    private String couponValue;

    @SerializedName("id")
    private int id;

    public SettlementCouponBean(String couponName, String couponValue, int id, boolean select) {
        this.couponName = couponName;
        this.couponValue = couponValue;
        this.id = id;
        this.select = select;
    }

    private boolean select;

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(String couponValue) {
        this.couponValue = couponValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
