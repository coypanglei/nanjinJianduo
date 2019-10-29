package com.shaoyue.weizhegou.entity.goods;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/4/19 0019 10:11
 * <p>
 * 邮箱：434604925@qq.com
 */
public class GoodsDetialBean extends BaseBean {

    //id
    //[number]	是	商品id	展开
    //cat_id
    //[number]	是	分类id	展开
    //products_name复制
    //[string]	是	商品名称	展开
    //store_count
    //[number]	是	库存	展开
    //market_price
    //[string]	是	原价	展开
    //shop_price
    //[string]	是	现价
    //promote_price
    //[string]	是	价格区间

    //give_integral
    //[string]	是 赠送积分
    //规格列表
    //spec

    //purchase_limit 	限购数量

    //starting_quantity 	起卖数量

    //is_time_purchase 是否是现实购商品

    //跳转类型 0 规格跳转  1加入购物车跳转 2 结算跳转
    private int type;

    @SerializedName("is_time_purchase")
    private int is_time_purchase;

    @SerializedName("purchase_limit")
    private int purchase_limit;

    @SerializedName("starting_quantity")
    private int starting_quantity;

    @SerializedName("give_integral")
    private String giveIntegral;


    @SerializedName("store_count")
    private int storeCount;


    @SerializedName("products_name")
    private String productsName;


    @SerializedName("promote_price")
    private String promotePrice;


    @SerializedName("shop_price")
    private String shopPrice;

    @SerializedName("market_price")
    private String marketPrice;


    @SerializedName("images")
    private List<String> images;


    @SerializedName("purchase_start_time")
    private long purchase_start_time;

    @SerializedName("purchase_end_time")
    private long purchase_end_time;

    @SerializedName("goods_content")
    private String goodsContent;

    //规格列表
    @SerializedName("spec")
    private List<GoodsSpecNameBean> goodsSpecNameBeanList;
    //选中规格的查询
    @SerializedName("sku_spec")
    private List<SpecInfoBean> mSpecInfoBeanList;


    @SerializedName("id")
    private String products_id;


    @SerializedName("activity_name")
    private String activityName;

    @SerializedName("discount")
    private String discount;

    public int getIs_time_purchase() {
        return is_time_purchase;
    }

    public void setIs_time_purchase(int is_time_purchase) {
        this.is_time_purchase = is_time_purchase;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    private int buyNum;


    public int getPurchase_limit() {
        return purchase_limit;
    }

    public void setPurchase_limit(int purchase_limit) {
        this.purchase_limit = purchase_limit;
    }

    public int getStarting_quantity() {
        if (starting_quantity == 0) {
            return 1;
        }
        return starting_quantity;
    }

    public void setStarting_quantity(int starting_quantity) {
        this.starting_quantity = starting_quantity;
    }

    public String getProducts_id() {
        return products_id;
    }

    public void setProducts_id(String products_id) {
        this.products_id = products_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    private List<String> selectSpecItems;

    public List<String> getSelectSpecItems() {
        return selectSpecItems;
    }

    public void setSelectSpecItems(List<String> selectSpecItems) {
        this.selectSpecItems = selectSpecItems;
    }

    public List<SpecInfoBean> getmSpecInfoBeanList() {
        return mSpecInfoBeanList;
    }

    public void setmSpecInfoBeanList(List<SpecInfoBean> mSpecInfoBeanList) {
        this.mSpecInfoBeanList = mSpecInfoBeanList;
    }

    public List<GoodsSpecNameBean> getGoodsSpecNameBeanList() {
        return goodsSpecNameBeanList;
    }

    public void setGoodsSpecNameBeanList(List<GoodsSpecNameBean> goodsSpecNameBeanList) {
        this.goodsSpecNameBeanList = goodsSpecNameBeanList;
    }

    public String getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(String giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public String getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(String promotePrice) {
        this.promotePrice = promotePrice;
    }


    public int getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(int storeCount) {
        this.storeCount = storeCount;
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }


    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public long getPurchase_start_time() {
        return purchase_start_time;
    }

    public void setPurchase_start_time(long purchase_start_time) {
        this.purchase_start_time = purchase_start_time;
    }

    public long getPurchase_end_time() {
        return purchase_end_time;
    }

    public void setPurchase_end_time(long purchase_end_time) {
        this.purchase_end_time = purchase_end_time;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }


}
