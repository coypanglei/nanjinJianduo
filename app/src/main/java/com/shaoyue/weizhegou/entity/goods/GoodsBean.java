package com.shaoyue.weizhegou.entity.goods;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/4/3 0003 11:29
 * <p>
 * 邮箱：434604925@qq.com
 */
public class GoodsBean extends BaseBean {
    //id复制
//[number]	是	id	展开
//products_name
//[string]	是	商品名称	展开
//market_price
//[string]	是	原价	展开
//shop_price
//[string]	是	现价	展开
//images
//[string]	是	商品图片

    //标签
    @SerializedName("activity_name")
    private String activity_name;
    //折扣
    @SerializedName("discount")
    private String discount;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    @SerializedName("can_buy")
    private int canBuy;

    @SerializedName("id")
    private int id;

    @SerializedName("products_name")
    private String products_name;

    @SerializedName("market_price")
    private String market_price;

    @SerializedName("shop_price")
    private String shop_price;

    @SerializedName("images")
    private String images;

    public int getCanBuy() {
        return canBuy;
    }

    public void setCanBuy(int canBuy) {
        this.canBuy = canBuy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducts_name() {
        return products_name;
    }

    public void setProducts_name(String products_name) {
        this.products_name = products_name;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
