package com.shaoyue.weizhegou.entity;

/**
 * 作者：HQY on 18/3/30 14:33
 * 邮箱：hqy_xz@126.com
 */

public class WxPayEvent {
    private String type;

    public WxPayEvent(String t) {
        this.type = t;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
