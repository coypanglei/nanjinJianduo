package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/7/12 0012 11:21
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrderStatisticsBean extends BaseBean {

    //unpaid_count
    //[number]	是	待付款数量	展开
    //not_shipped复制
    //[number]	是	待发货数量	展开
    //unreceived_count
    //[number]	是	待收货数量	展开
    //return_count
    @SerializedName("unpaid_count")
    private int unpaid_count;

    @SerializedName("not_shipped")
    private int not_shipped;

    @SerializedName("unreceived_count")
    private int unreceived_count;


    @SerializedName("return_count")
    private int return_count;

    public int getUnpaid_count() {
        return unpaid_count;
    }

    public void setUnpaid_count(int unpaid_count) {
        this.unpaid_count = unpaid_count;
    }

    public int getNot_shipped() {
        return not_shipped;
    }

    public void setNot_shipped(int not_shipped) {
        this.not_shipped = not_shipped;
    }

    public int getUnreceived_count() {
        return unreceived_count;
    }

    public void setUnreceived_count(int unreceived_count) {
        this.unreceived_count = unreceived_count;
    }

    public int getReturn_count() {
        return return_count;
    }

    public void setReturn_count(int return_count) {
        this.return_count = return_count;
    }
}
