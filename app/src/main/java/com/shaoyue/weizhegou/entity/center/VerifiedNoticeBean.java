package com.shaoyue.weizhegou.entity.center;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/5/13 0013 11:08
 * <p>
 * 邮箱：434604925@qq.com
 */
public class VerifiedNoticeBean extends BaseBean {

    @SerializedName("content")
    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
