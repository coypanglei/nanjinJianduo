package com.shaoyue.weizhegou.entity.goods;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/4/25 0025 11:31
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SpecInfoBean extends BaseBean {
    //id
    //[number]	是	自增id	展开
    //key
    //[string]	是	规格选项id ,例如，红色的id	展开
    //market_price
    //[string]	是	原价	展开
    //shop_price
    //[string]	是	现价	展开
    //store_count
    //[number]	是	库存	展开
    //sales_count

    @SerializedName("id")
    private int id;

    @SerializedName("key")
    private List<String> key;

    @SerializedName("key_name")
    private String keyName;

    @SerializedName("market_price")
    private String market_price;


    @SerializedName("shop_price")
    private String shopPrice;

    @SerializedName("store_count")
    private int storeCount;

    @SerializedName("sales_count")
    private String salesCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public int getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(int storeCount) {
        this.storeCount = storeCount;
    }

    public String getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(String salesCount) {
        this.salesCount = salesCount;
    }


}
