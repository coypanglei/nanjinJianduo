package com.shaoyue.weizhegou.entity.order;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;
import com.shaoyue.weizhegou.entity.goods.ShopCarBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/7/9 0009 14:24
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrderIdBean extends BaseBean {

    @SerializedName("order_id")
    private String order_id;

    @SerializedName("order_sn")
    private String order_sn;

    @SerializedName("create_time")
    private String create_time;

    @SerializedName("pay_status")
    private String pay_status;

    @SerializedName("shipping_status")
    private String shipping_status;

    @SerializedName("composite_status")
    private String composite_status;

    @SerializedName("composite_status_msg")
    private String composite_status_msg;

    @SerializedName("total_amount")
    private String total_amount;

    @SerializedName("products_price")
    private String products_price;

    @SerializedName("order_products")
    private List<ShopCarBean> order_products;

    //can_delete
    //[int]	是	是否能点击删除 1:是；0:否	展开
    //can_info
    //[int]	是	是否能查看详情 1:是；0:否	展开
    //can_pay
    //[int]	是	是否能点击支付 1:是；0:否	展开
    //can_refund
    //[int]	是	是否能点击退款 1:是；0:否	展开
    //can_return
    //[int]	是	是否能点击退货 1:是；0:否	展开
    //can_exchange
    //[int]	是	是否能点击换货 1:是；0:否	展开
    //can_receipt

    @SerializedName("can_delete")
    private int can_delete;

    @SerializedName("can_info")
    private int can_info;

    @SerializedName("can_pay")
    private int can_pay;

    @SerializedName("can_refund")
    private int can_refund;

    @SerializedName("can_return")
    private int can_return;

    @SerializedName("can_exchange")
    private int can_exchange;

    @SerializedName("can_receipt")
    private int can_receipt;

    public int getCan_delete() {
        return can_delete;
    }

    public void setCan_delete(int can_delete) {
        this.can_delete = can_delete;
    }

    public int getCan_info() {
        return can_info;
    }

    public void setCan_info(int can_info) {
        this.can_info = can_info;
    }

    public int getCan_pay() {
        return can_pay;
    }

    public void setCan_pay(int can_pay) {
        this.can_pay = can_pay;
    }

    public int getCan_refund() {
        return can_refund;
    }

    public void setCan_refund(int can_refund) {
        this.can_refund = can_refund;
    }

    public int getCan_return() {
        return can_return;
    }

    public void setCan_return(int can_return) {
        this.can_return = can_return;
    }

    public int getCan_exchange() {
        return can_exchange;
    }

    public void setCan_exchange(int can_exchange) {
        this.can_exchange = can_exchange;
    }

    public int getCan_receipt() {
        return can_receipt;
    }

    public void setCan_receipt(int can_receipt) {
        this.can_receipt = can_receipt;
    }

    public List<ShopCarBean> getOrder_products() {
        return order_products;
    }

    public void setOrder_products(List<ShopCarBean> order_products) {
        this.order_products = order_products;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getProducts_price() {
        return products_price;
    }

    public void setProducts_price(String products_price) {
        this.products_price = products_price;
    }

    public String getComposite_status_msg() {
        return composite_status_msg;
    }

    public void setComposite_status_msg(String composite_status_msg) {
        this.composite_status_msg = composite_status_msg;
    }

    public String getComposite_status() {
        return composite_status;
    }

    public void setComposite_status(String composite_status) {
        this.composite_status = composite_status;
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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getShipping_status() {
        return shipping_status;
    }

    public void setShipping_status(String shipping_status) {
        this.shipping_status = shipping_status;
    }

    @Override
    public String toString() {
        return "OrderIdBean{" +
                "order_id='" + order_id + '\'' +
                ", order_sn='" + order_sn + '\'' +
                ", create_time='" + create_time + '\'' +
                ", pay_status='" + pay_status + '\'' +
                ", shipping_status='" + shipping_status + '\'' +
                ", composite_status='" + composite_status + '\'' +
                ", composite_status_msg='" + composite_status_msg + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", products_price='" + products_price + '\'' +
                ", order_products=" + order_products +
                '}';
    }
}
