package com.shaoyue.weizhegou.event;

import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/5/27 0027 10:35
 * <p>
 * 邮箱：434604925@qq.com
 */
public class CarNumEvent extends BaseBean {

    private int type;

    private String num;

    public CarNumEvent(int type, String num) {
        this.type = type;
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
