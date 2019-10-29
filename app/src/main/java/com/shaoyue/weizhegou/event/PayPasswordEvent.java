package com.shaoyue.weizhegou.event;

/**
 * 作者：PangLei on 2019/7/3 0003 10:40
 * <p>
 * 邮箱：434604925@qq.com
 */
public class PayPasswordEvent {

    private String payPassword;

    public PayPasswordEvent(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }
}
