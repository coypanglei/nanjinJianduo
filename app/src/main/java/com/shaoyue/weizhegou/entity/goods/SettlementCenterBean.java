package com.shaoyue.weizhegou.entity.goods;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;
import com.shaoyue.weizhegou.entity.center.AddressListBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/6/24 0024 15:18
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SettlementCenterBean extends BaseBean {
//     "delivery":"快递",快递信息
//        "user_address":{
//            "id":3,
//            "consignee":"朱琳",
//            "mobile":"18000111553",
//            "area":"河北省,石家庄市,新华区",
//            "address":"矿大软件园c6",
//            "is_default":0
//        },
//        "need_id":1,
//        "id_card":"",
//        "products_list":[
//            {
//                "id":21,
//                "user_id":8,
//                "session_id":"h3rtmpm55b3d400sn3j3njmsh6",
//                "products_id":272,
//                "products_sn":"2019051502",
//                "products_name":"测试规格商品加入购物车，勿操作",
//                "products_img":"https://weizhegou.oss-cn-shanghai.aliyuncs.com//products/20190501/1556676263312679.png",
//                "market_price":"100.00",
//                "shop_price":"79.00",
//                "member_price":"66.00",
//                "products_num":1,
//                "item_id":3647,
//                "spec_key":"7;288",
//                "spec_key_name":"白色; 女",
//                "selected":1,
//                "add_time":1558504410,
//                "sku":"",
//                "need_id":1,
//                "cat_id":0,
//                "store_count":400,
//                "is_on_sale":1
//            }
//        ],
//        "fare":0,
//        "coupon_price":0,
//        "cart_price":66,
//        "total_price":66
    //all_unusual


    @SerializedName("is_refresh")
    private int is_refresh;

    public int getIs_refresh() {
        return is_refresh;
    }

    public void setIs_refresh(int is_refresh) {
        this.is_refresh = is_refresh;
    }

    @SerializedName("all_unusual")
    private int all_unusual;

    public int getAll_unusual() {
        return all_unusual;
    }

    public void setAll_unusual(int all_unusual) {
        this.all_unusual = all_unusual;
    }

    @SerializedName("pay_password")
    private boolean pay_password;

    @SerializedName("products_list")
    private List<ShopCarBean> mProductsList;

    public List<ShopCarBean> getmProductsList() {
        return mProductsList;
    }

    public void setmProductsList(List<ShopCarBean> mProductsList) {
        this.mProductsList = mProductsList;
    }

    public boolean isPay_password() {
        return pay_password;
    }

    public void setPay_password(boolean pay_password) {
        this.pay_password = pay_password;
    }

    @SerializedName("balance_bigger_total_price")
    private int balance_bigger_total_price;

    public int getBalance_bigger_total_price() {
        return balance_bigger_total_price;
    }

    public void setBalance_bigger_total_price(int balance_bigger_total_price) {
        this.balance_bigger_total_price = balance_bigger_total_price;
    }

    @SerializedName("coupon_price")
    private String coupon_price;

    @SerializedName("user_coupon_id")
    private int coupon_id;

    public String getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(String coupon_price) {
        this.coupon_price = coupon_price;
    }


    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    @SerializedName("total_price")
    private String total_price;

    private String ids;
    //商品 id
    private String products_id;
    //商品数量
    private String products_num;
    //商品规格
    private String item_id;
    //是否立即购买
    @SerializedName("action")
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getProducts_num() {
        return products_num;
    }

    public void setProducts_num(String products_num) {
        this.products_num = products_num;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getProducts_id() {
        return products_id;
    }

    public void setProducts_id(String products_id) {
        this.products_id = products_id;
    }

    @SerializedName("order_count")
    private int order_count;

    @SerializedName("delivery")
    private String delivery;

    @SerializedName("user_address")
    private AddressListBean user_address;

    @SerializedName("fare")
    private String fare;

    @SerializedName("need_id")
    private String need_id;

    @SerializedName("id_card_notice")
    private String id_card_notice;

    @SerializedName("balance")
    private String balance;


    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getId_card_notice() {
        return id_card_notice;
    }

    public void setId_card_notice(String id_card_notice) {
        this.id_card_notice = id_card_notice;
    }

    public String getNeed_id() {
        return need_id;
    }

    public void setNeed_id(String need_id) {
        this.need_id = need_id;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public AddressListBean getUser_address() {
        return user_address;
    }

    public void setUser_address(AddressListBean user_address) {
        this.user_address = user_address;
    }

    @Override
    public String toString() {
        return "SettlementCenterBean{" +
                "ids='" + ids + '\'' +
                ", order_count=" + order_count +
                ", delivery='" + delivery + '\'' +
                ", user_address=" + user_address +
                ", fare='" + fare + '\'' +
                ", need_id='" + need_id + '\'' +
                ", id_card_notice='" + id_card_notice + '\'' +
                '}';
    }
}
