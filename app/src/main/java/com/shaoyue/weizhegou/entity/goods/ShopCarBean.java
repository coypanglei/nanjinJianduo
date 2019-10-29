package com.shaoyue.weizhegou.entity.goods;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/6/6 0006 14:41
 * <p>
 * 邮箱：434604925@qq.com
 */
public class ShopCarBean extends BaseBean {

    //id复制
    //[number]	是		收缩
    //参数示例：81
    //
    //user_id
    //[number]	是	会员ID	展开
    //device_id
    //[string]	是		展开
    //products_id
    //[number]	是	商品ID	展开
    //products_sn
    //[string]	是	商品编码	收缩
    //参数示例：2019053102
    //
    //products_name
    //[string]	是	商品名称	展开
    //products_img
    //[string]	是	商品图片	展开
    //market_price
    //[string]	是	原价	展开
    //shop_price
    //[string]	是	现价	展开
    //member_price
    //[string]	是	会员价格	展开
    //products_num
    //[number]	是	数量	展开
    //item_id
    //[number]	是	规格id	展开
    //spec_key
    //[string]	是
    //spec_key_name
    //[string]	是	规格名称
    //selected
    //[number]	是	是否选中	展开
    //add_time
    //[number]	是	加入时间	展开
    //need_id
    //[number]	是	是否需要身份证	展开
    //store_count
    //[number]	是	库存	展开
    //is_on_sale
    //[number]	是	是否上架	展开
    //purchase_limit
    //[number]	是	限购数量	展开
    //orderer_sales_quantity
    //[string]	是	订货商起买数量	展开
    //is_invalid
    //[number]	是	是否失效 1:失效 0:不失效

    public void setIs_on_sale(int is_on_sale) {
        this.is_on_sale = is_on_sale;
    }

    public int getIs_on_sale() {
        return is_on_sale;
    }

    private int positon;

    public int getPositon() {
        return positon;
    }

    public void setPositon(int positon) {
        this.positon = positon;
    }


    @SerializedName("cart_status")
    private int cart_status;

    @SerializedName("purchase_limit")
    private int purchase_limit;

    @SerializedName("starting_quantity")
    private int starting_quantity;

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

    @SerializedName("id")
    private String id;

    @SerializedName("user_id")
    private String user_id;


    @SerializedName("products_id")
    private String products_id;

    @SerializedName("products_sn")
    private String products_sn;

    @SerializedName("products_name")
    private String products_name;

    @SerializedName("products_img")
    private String products_img;

    @SerializedName("market_price")
    private String market_price;

    @SerializedName("shop_price")
    private String shop_price;

    @SerializedName("member_price")
    private String member_price;

    @SerializedName("products_num")
    private int products_num;

    @SerializedName("item_id")
    private String item_id;

    @SerializedName("spec_key")
    private String spec_key;

    @SerializedName("spec_key_name")
    private String spec_key_name;

    @SerializedName("selected")
    private int selected;

    @SerializedName("add_time")
    private String add_time;

    @SerializedName("need_id")
    private String need_id;

    @SerializedName("store_count")
    private String store_count;

    @SerializedName("is_on_sale")
    private int is_on_sale;


    @SerializedName("orderer_sales_quantity")
    private String orderer_sales_quantity;

    @SerializedName("is_invalid")
    private int is_invalid;



    public int getCart_status() {
        return cart_status;
    }

    public void setCart_status(int cart_status) {
        this.cart_status = cart_status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProducts_id() {
        return products_id;
    }

    public void setProducts_id(String products_id) {
        this.products_id = products_id;
    }

    public String getProducts_sn() {
        return products_sn;
    }

    public void setProducts_sn(String products_sn) {
        this.products_sn = products_sn;
    }

    public String getProducts_name() {
        return products_name;
    }

    public void setProducts_name(String products_name) {
        this.products_name = products_name;
    }

    public String getProducts_img() {
        return products_img;
    }

    public void setProducts_img(String products_img) {
        this.products_img = products_img;
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

    public String getMember_price() {
        return member_price;
    }

    public void setMember_price(String member_price) {
        this.member_price = member_price;
    }

    public int getProducts_num() {
        return products_num;
    }

    public void setProducts_num(int products_num) {
        this.products_num = products_num;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getSpec_key() {
        return spec_key;
    }

    public void setSpec_key(String spec_key) {
        this.spec_key = spec_key;
    }

    public String getSpec_key_name() {
        return spec_key_name;
    }

    public void setSpec_key_name(String spec_key_name) {
        this.spec_key_name = spec_key_name;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getNeed_id() {
        return need_id;
    }

    public void setNeed_id(String need_id) {
        this.need_id = need_id;
    }

    public String getStore_count() {
        return store_count;
    }

    public void setStore_count(String store_count) {
        this.store_count = store_count;
    }




    public String getOrderer_sales_quantity() {
        return orderer_sales_quantity;
    }

    public void setOrderer_sales_quantity(String orderer_sales_quantity) {
        this.orderer_sales_quantity = orderer_sales_quantity;
    }

    public int getIs_invalid() {
        return is_invalid;
    }

    public void setIs_invalid(int is_invalid) {
        this.is_invalid = is_invalid;
    }
}
