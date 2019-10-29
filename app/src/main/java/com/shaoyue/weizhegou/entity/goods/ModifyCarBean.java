package com.shaoyue.weizhegou.entity.goods;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/6/17 0017 16:51
 * <p>
 * 邮箱：434604925@qq.com
 */
public class ModifyCarBean extends BaseBean {

    @SerializedName("cart")
    private ShopCarBean mShopCarBean;

    @SerializedName("is_refresh")
    private int isRefresh;

    @SerializedName("order_count")
    private int order_count;

    @SerializedName("max_offer")
    private String max_offer;

    @SerializedName("total_price")
    private String total_price;

    private int positon;

    public int getPositon() {
        return positon;
    }

    public void setPositon(int positon) {
        this.positon = positon;
    }

    public ShopCarBean getmShopCarBean() {
        return mShopCarBean;
    }

    public void setmShopCarBean(ShopCarBean mShopCarBean) {
        this.mShopCarBean = mShopCarBean;
    }

    public int getIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(int isRefresh) {
        this.isRefresh = isRefresh;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public String getMax_offer() {
        return max_offer;
    }

    public void setMax_offer(String max_offer) {
        this.max_offer = max_offer;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
}
