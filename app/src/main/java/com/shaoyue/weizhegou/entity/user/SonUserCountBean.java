package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/7/2 0002 11:45
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SonUserCountBean extends BaseBean {
    @SerializedName("all")
    private int all;

    @SerializedName("one")
    private int one;

    @SerializedName("two")
    private int two;

    @SerializedName("three")
    private int three;

    public int getThree() {
        return three;
    }

    public void setThree(int three) {
        this.three = three;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }
}
