package com.shaoyue.weizhegou.entity.order;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/7/9 0009 14:37
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrderDetailsBean extends BaseBean {

    //order_id
    //[int]	是	订单ID	展开
    //order_sn
    //[int]	是	订单编码	展开
    //order_status
    //[int]	是	订单状态	展开
    //shipping_status
    //[int]	是	物流状态	展开
    //pay_status
    //[int]	是	支付状态	展开
    //consignee
    //[string]	是	收货人	展开
    //area
    //[string]	是	收货地址详情	展开
    //address
    //[string]	是	收货地址详情	展开
    //mobile
    //[string]	是	收货人手机号	展开
    //products_price
    //[double]	是	商品总价格	展开
    //total_amount
    //[double]	是	实际付款金额	展开
    //create_time
    //[string]	是	订单创建时间	展开
    //user_note
    //[string]	是	买家留言
    //composite_status
    //[int]	是	订单状态码	展开
    //composite_status_msg复制
    //[string]	是	订单状态信息
    //id_card
    //coupon_price=5.00
    //shipping_price=0.00

    //can_pay
    //[int]	是	是否能支付 0:不能；1:能	展开
    //can_refund
    //[int]	是	是否能退款 0:不能；1:能	展开
    //can_return
    //[int]	是	是否能退货 0:不能；1:能	展开
    //can_exchange
    //[int]	是	是否能换货 0:不能；1:能	展开
    //can_receipt
    //[int]	是	是否能确认收货 0:不能；1:能
//can_delete

    //order_icon

    @SerializedName("order_icon")
    private String order_icon;

    @SerializedName("can_delete")
    private String can_delete;


    @SerializedName("can_pay")
    private String can_pay;

    @SerializedName("can_refund")
    private String can_refund;

    @SerializedName("can_return")
    private String can_return;

    @SerializedName("can_exchange")
    private String can_exchange;

    @SerializedName("can_receipt")
    private String can_recepit;

    public String getOrder_icon() {
        return order_icon;
    }

    public void setOrder_icon(String order_icon) {
        this.order_icon = order_icon;
    }

    public String getCan_delete() {
        return can_delete;
    }

    public void setCan_delete(String can_delete) {
        this.can_delete = can_delete;
    }

    public String getCan_pay() {
        return can_pay;
    }

    public void setCan_pay(String can_pay) {
        this.can_pay = can_pay;
    }

    public String getCan_refund() {
        return can_refund;
    }

    public void setCan_refund(String can_refund) {
        this.can_refund = can_refund;
    }

    public String getCan_return() {
        return can_return;
    }

    public void setCan_return(String can_return) {
        this.can_return = can_return;
    }

    public String getCan_exchange() {
        return can_exchange;
    }

    public void setCan_exchange(String can_exchange) {
        this.can_exchange = can_exchange;
    }

    public String getCan_recepit() {
        return can_recepit;
    }

    public void setCan_recepit(String can_recepit) {
        this.can_recepit = can_recepit;
    }

    @SerializedName("shipping_price")
    private String shipping_price;

    public String getShipping_price() {
        return shipping_price;
    }

    public void setShipping_price(String shipping_price) {
        this.shipping_price = shipping_price;
    }

    @SerializedName("coupon_price")
    private String coupon_price;


    @SerializedName("id_card")
    private String id_card;

    @SerializedName("order_id")
    private String order_id;

    @SerializedName("order_sn")
    private String order_sn;


    @SerializedName("shipping_status")
    private String shipping_status;

    @SerializedName("order_status")
    private String order_status;

    @SerializedName("pay_status")
    private String pay_status;

    @SerializedName("consignee")
    private String consignee;

    @SerializedName("area")
    private String area;

    @SerializedName("address")
    private String address;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("products_price")
    private String products_price;

    @SerializedName("total_amount")
    private String total_amount;

    @SerializedName("create_time")
    private String create_time;

    @SerializedName("user_note")
    private String user_note;

    @SerializedName("composite_status")
    private String composite_status;

    @SerializedName("composite_status_msg")
    private String composite_status_msg;

    @SerializedName("products_list")
    private List<OrderDetailsGoodsBean> products_list;

    public List<OrderDetailsGoodsBean> getProducts_list() {
        return products_list;
    }

    public void setProducts_list(List<OrderDetailsGoodsBean> products_list) {
        this.products_list = products_list;
    }

    public String getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(String coupon_price) {
        this.coupon_price = coupon_price;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getShipping_status() {
        return shipping_status;
    }

    public void setShipping_status(String shipping_status) {
        this.shipping_status = shipping_status;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProducts_price() {
        return products_price;
    }

    public void setProducts_price(String products_price) {
        this.products_price = products_price;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUser_note() {
        return user_note;
    }

    public void setUser_note(String user_note) {
        this.user_note = user_note;
    }

    public String getComposite_status() {
        return composite_status;
    }

    public void setComposite_status(String composite_status) {
        this.composite_status = composite_status;
    }

    public String getComposite_status_msg() {
        return composite_status_msg;
    }

    public void setComposite_status_msg(String composite_status_msg) {
        this.composite_status_msg = composite_status_msg;
    }


    public boolean getVisable() {
        if (can_delete.equals("0") && can_exchange.equals("0") && can_pay.equals("0") && can_recepit.equals("0") && can_refund.equals("0") && can_return.equals("0")) {
            return true;
        }
        return false;
    }
}
