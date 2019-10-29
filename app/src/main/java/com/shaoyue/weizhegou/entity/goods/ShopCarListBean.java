package com.shaoyue.weizhegou.entity.goods;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;


import java.util.List;

/**
 * 作者：PangLei on 2019/6/6 0006 15:28
 * <p>
 * 邮箱：434604925@qq.com
 */
public class ShopCarListBean extends BaseBean {

    @SerializedName("is_all_selected")
    private int is_all_selected;


    @SerializedName("total")
    private int total;

    @SerializedName("is_invalid_count")
    private int is_invalid_count;

    @SerializedName("list")
    private List<ShopCarBean> data;

    @SerializedName("per_page")
    private int per_page;


    @SerializedName("products_count")
    private int products_count;

    @SerializedName("order_count")
    private int order_count;

    @SerializedName("max_offer")
    private String max_offer;

    @SerializedName("total_price")
    private String total_price;

    @SerializedName("is_all_invalid")
    private int isAllInvalid;

    public int getIs_all_selected() {
        return is_all_selected;
    }

    public void setIs_all_selected(int is_all_selected) {
        this.is_all_selected = is_all_selected;
    }

    public int getIsAllInvalid() {
        return isAllInvalid;
    }

    public void setIsAllInvalid(int isAllInvalid) {
        this.isAllInvalid = isAllInvalid;
    }

    public int getIs_invalid_count() {
        return is_invalid_count;
    }

    public void setIs_invalid_count(int is_invalid_count) {
        this.is_invalid_count = is_invalid_count;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getMax_offer() {
        return max_offer;
    }

    public void setMax_offer(String max_offer) {
        this.max_offer = max_offer;
    }

    public int getProducts_count() {
        return products_count;
    }

    public void setProducts_count(int products_count) {
        this.products_count = products_count;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ShopCarBean> getData() {
        return data;
    }

    public void setData(List<ShopCarBean> data) {
        this.data = data;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }
}
