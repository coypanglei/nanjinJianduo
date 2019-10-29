package com.shaoyue.weizhegou.entity;

/**
 * 作者：HQY on 18/1/29 13:58
 * 邮箱：hqy_xz@126.com
 */

public class PaySuccessEvent {
    private String type;

    public PaySuccessEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
