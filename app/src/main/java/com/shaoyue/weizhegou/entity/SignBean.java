package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;


/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/30 22:boot_page
 */
public class SignBean extends BaseBean {

    @SerializedName("award_sec")
    private String awardSec;

    public String getAwardSec() {
        return awardSec;
    }

    public void setAwardSec(String awardSec) {
        this.awardSec = awardSec;
    }

    @Override
    public String toString() {
        return "SignBean{" +
                "awardSec='" + awardSec + '\'' +
                '}';
    }
}
