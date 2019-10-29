package com.shaoyue.weizhegou.entity.order;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/7/22 0022 14:48
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrderDetailsGoodsBean extends BaseBean {
//id
//[int]	是	订单商品ID	展开
//products_name
//[string]	是	商品名称	展开
//products_img
//[string]	是	商品图片	展开
//products_sn
//[string]	是	商品编码	展开
//products_num
//[number]	是	购买数量	展开
//is_on_sale
//[int]	是	是否下架	展开
//see_shipping
//[int]	是	是否能 查看物流0:不能；1:能	展开
//status_msg
//[string]	是	退换货状态	展开
//member_price
//[object]	是	会员价格
//see_shipping 是否能查看物流

    //spec_key_name

    @SerializedName("see_shipping")
    private String see_shipping;



    @SerializedName("spec_key_name")
    private String spec_key_name;

    public String getSpec_key_name() {
        return spec_key_name;
    }

    public void setSpec_key_name(String spec_key_name) {
        this.spec_key_name = spec_key_name;
    }

    @SerializedName("id")
    private String id;

    @SerializedName("products_name")
    private String products_name;

    @SerializedName("products_img")
    private String products_img;

    @SerializedName("products_sn")
    private String products_sn;

    @SerializedName("products_num")
    private String products_num;

    @SerializedName("is_on_sale")
    private String is_on_sale;




    @SerializedName("status_msg")
    private String status_msg;

    @SerializedName("member_price")
    private String member_price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProducts_sn() {
        return products_sn;
    }

    public void setProducts_sn(String products_sn) {
        this.products_sn = products_sn;
    }

    public String getProducts_num() {
        return products_num;
    }

    public void setProducts_num(String products_num) {
        this.products_num = products_num;
    }

    public String getIs_on_sale() {
        return is_on_sale;
    }

    public void setIs_on_sale(String is_on_sale) {
        this.is_on_sale = is_on_sale;
    }

    public String getSee_shipping() {
        return see_shipping;
    }

    public void setSee_shipping(String see_shipping) {
        this.see_shipping = see_shipping;
    }

    public String getStatus_msg() {
        return status_msg;
    }

    public void setStatus_msg(String status_msg) {
        this.status_msg = status_msg;
    }

    public String getMember_price() {
        return member_price;
    }

    public void setMember_price(String member_price) {
        this.member_price = member_price;
    }
}
