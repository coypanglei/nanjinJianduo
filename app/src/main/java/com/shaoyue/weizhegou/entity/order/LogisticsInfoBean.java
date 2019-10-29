package com.shaoyue.weizhegou.entity.order;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/7/12 0012 14:39
 * <p>
 * 邮箱：434604925@qq.com
 */
public class LogisticsInfoBean extends BaseBean {

    // "time":"2019-07-07 18:37:26",
    //            "context":"【徐州市】 已签收, 签收人凭取货码签收, 如有疑问请电联: 15262187421 / 4000633333,15262187421, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】",
    //            "ftime":"2019-07-07 18:37:26",
    //            "areaCode":null,
    //            "areaName":null,
    //            "status":"签收"
    @SerializedName("ftime")
    private String time;

    @SerializedName("context")
    private String context;

    @SerializedName("status")
    private String status;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
