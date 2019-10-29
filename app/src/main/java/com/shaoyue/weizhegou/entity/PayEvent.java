package com.shaoyue.weizhegou.entity;

/**
 * 作者：HQY on 17/5/23 16:51
 * 邮箱：hqy_xz@126.com
 */

public class PayEvent {

    public String type;
    public PayEvent(String t){
        this.type=t;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
