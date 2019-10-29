package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

import com.shaoyue.weizhegou.base.BaseBean;


/**
 * Created by USER on 2018/11/8.
 */

public class ActivityBean extends BaseBean {

    @SerializedName("name")
    private String name;


    @SerializedName("content")

    private String content;

    @SerializedName("jump_title")
    private String jumpTitle;

    @SerializedName("jump_url")
    private String jumpUrl;

    @SerializedName("btn")
    private String btn;

    @SerializedName("is_closed")
    private String isClosed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJumpTitle() {
        return jumpTitle;
    }

    public void setJumpTitle(String jumpTitle) {
        this.jumpTitle = jumpTitle;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    public String getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(String isClosed) {
        this.isClosed = isClosed;
    }

    @Override
    public String toString() {
        return "ActivityBean{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", jumpTitle='" + jumpTitle + '\'' +
                ", jumpUrl='" + jumpUrl + '\'' +
                ", btn='" + btn + '\'' +
                ", isClosed='" + isClosed + '\'' +
                '}';
    }


}
