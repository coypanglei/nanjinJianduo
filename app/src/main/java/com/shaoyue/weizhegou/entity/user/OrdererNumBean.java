package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/5/28 0028 10:18
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrdererNumBean extends BaseBean {

//       "allUserCount":92,//所有用户
//               "buyUserCount":93,//订货商用户
//               "orderCount":0//订单数量

    @SerializedName("allUserCount")
    private int allUserCount;

    @SerializedName("buyUserCount")
    private int buyUserCount;

    @SerializedName("orderCount")
    private OrderCountBean orderCount;

    public int getAllUserCount() {
        return allUserCount;
    }

    public void setAllUserCount(int allUserCount) {
        this.allUserCount = allUserCount;
    }

    public int getBuyUserCount() {
        return buyUserCount;
    }

    public void setBuyUserCount(int buyUserCount) {
        this.buyUserCount = buyUserCount;
    }

    public OrderCountBean getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(OrderCountBean orderCount) {
        this.orderCount = orderCount;
    }

    @Override
    public String toString() {
        return "OrdererNumBean{" +
                "allUserCount=" + allUserCount +
                ", buyUserCount=" + buyUserCount +
                ", orderCount=" + orderCount +
                '}';
    }
}
