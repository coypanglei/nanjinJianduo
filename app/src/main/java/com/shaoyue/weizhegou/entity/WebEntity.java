package com.shaoyue.weizhegou.entity;

import java.io.Serializable;

/**
 * 作者：HQY on 17/5/29 11:07
 * 邮箱：hqy_xz@126.com
 */

public class WebEntity implements Serializable {

    private String title;

    private String url;
    private String money;

    private String orderId;

    private String time;


    public WebEntity(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public WebEntity(String title, String url, String money, String orderId, String time) {
        this.title = title;
        this.url = url;
        this.money = money;
        this.orderId = orderId;
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
