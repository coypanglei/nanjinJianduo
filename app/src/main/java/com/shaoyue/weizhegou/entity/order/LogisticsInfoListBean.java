package com.shaoyue.weizhegou.entity.order;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/7/12 0012 15:36
 * <p>
 * 邮箱：434604925@qq.com
 */
public class LogisticsInfoListBean extends BaseBean {

    // "products_name":"斐乐FILA 2019夏季新品 女款潮流百搭休闲宽松舒适连衣裙 ",
    //        "products_img":"https://weizhegou.oss-cn-shanghai.aliyuncs.com/products/20190708/1562548689420547.jpg",
    //        "shipping_name":"圆通",
    //        "invoice_no":"75159940418906",
    //        "shipping_note":"已发货",
    //   "express_info":[
    //            {
    //                "time":"2019-07-07 18:37:26",
    //                "context":"【徐州市】 已签收, 签收人凭取货码签收, 如有疑问请电联: 15262187421 / 4000633333,15262187421, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】",
    //                "ftime":"2019-07-07 18:37:26",
    //                "areaCode":null,
    //                "areaName":null,
    //                "status":"签收"
    //            },

    @SerializedName("products_name")
    private String products_name;


    @SerializedName("products_img")
    private String products_img;

    @SerializedName("shipping_name")
    private String shipping_name;

    @SerializedName("invoice_no")
    private String invoice_no;

    @SerializedName("shipping_note")
    private String shipping_note;

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

    public String getShipping_name() {
        return shipping_name;
    }

    public void setShipping_name(String shipping_name) {
        this.shipping_name = shipping_name;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getShipping_note() {
        return shipping_note;
    }

    public void setShipping_note(String shipping_note) {
        this.shipping_note = shipping_note;
    }

    @SerializedName("express_info")
    private List<LogisticsInfoBean> express_info;

    public List<LogisticsInfoBean> getExpress_info() {
        return express_info;
    }

    public void setExpress_info(List<LogisticsInfoBean> express_info) {
        this.express_info = express_info;
    }
}
