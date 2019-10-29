package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/5/21 0021 14:41
 * <p>
 * 邮箱：434604925@qq.com
 */
public class DistributionNumBean extends BaseBean {
//    productCount":28,"sonSaleCount":11,"sonUserCount":18,"orderCount":0

    @SerializedName("productCount")
    private int productCount;

    @SerializedName("sonSaleCount")
    private SonUserCountBean sonSaleCount;

    @SerializedName("sonUserCount")
    private SonUserCountBean sonUserCount;

    @SerializedName("orderCount")
    private OrderCountBean orderCount;



    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public SonUserCountBean getSonSaleCount() {
        return sonSaleCount;
    }

    public void setSonSaleCount(SonUserCountBean sonSaleCount) {
        this.sonSaleCount = sonSaleCount;
    }

    public SonUserCountBean getSonUserCount() {
        return sonUserCount;
    }

    public void setSonUserCount(SonUserCountBean sonUserCount) {
        this.sonUserCount = sonUserCount;
    }

    public OrderCountBean getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(OrderCountBean orderCount) {
        this.orderCount = orderCount;
    }

    @Override
    public String toString() {
        return "DistributionNumBean{" +
                "productCount=" + productCount +
                ", sonSaleCount=" + sonSaleCount +
                ", sonUserCount=" + sonUserCount +
                ", orderCount=" + orderCount +
                '}';
    }
}
