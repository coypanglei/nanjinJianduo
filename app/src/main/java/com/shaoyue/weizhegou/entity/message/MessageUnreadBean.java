package com.shaoyue.weizhegou.entity.message;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/7/16 0016 11:08
 * <p>
 * 邮箱：434604925@qq.com
 */
public class MessageUnreadBean extends BaseBean {

    @SerializedName("count")
    private int count;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
