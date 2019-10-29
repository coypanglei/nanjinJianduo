package com.shaoyue.weizhegou.entity.goods;

import com.shaoyue.weizhegou.base.BaseBean;

import com.shaoyue.weizhegou.entity.coupon.SettlementCouponBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/7/1 0001 14:37
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SettlementCoupon extends BaseBean {

    private List<SettlementCouponBean> mCouponList;

    private String id;

    public List<SettlementCouponBean> getmCouponList() {
        return mCouponList;
    }

    public void setmCouponList(List<SettlementCouponBean> mCouponList) {
        this.mCouponList = mCouponList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SettlementCoupon(List<SettlementCouponBean> mCouponList, String id) {
        this.mCouponList = mCouponList;
        this.id = id;
    }
}
