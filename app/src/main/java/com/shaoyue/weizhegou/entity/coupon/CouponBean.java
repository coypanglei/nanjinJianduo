package com.shaoyue.weizhegou.entity.coupon;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/5/17 0017 11:43
 * <p>
 * 邮箱：434604925@qq.com
 */
public class CouponBean extends BaseBean {
//    "coupon_id":1,//优惠券ID
//            "indate":"2019.05.09 ~ 1970.01.01",//优惠券有效期
//            "doorsill":"满5.01使用"//优惠券门槛
//            "skip_type":"0"
    //   "coupon_name":"5元优惠券",//优惠券名称
    //                "coupon_value":"5.00"//优惠券面值
    //                "state":"1" 状态{"0":"全部","1":"正常","2":"已使用","9":"已过期"}

    @SerializedName("remark")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @SerializedName("coupon_name")
    private String couponName;

    @SerializedName("coupon_value")
    private String couponValue;

    @SerializedName("state")
    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @SerializedName("coupon_id")
    private String couponId;

    @SerializedName("indate")
    private String indate;

    @SerializedName("doorsill")
    private String doorsill;

    @SerializedName("skip_type")
    private String skipType;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getDoorsill() {
        return doorsill;
    }

    public void setDoorsill(String doorsill) {
        this.doorsill = doorsill;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }
}
