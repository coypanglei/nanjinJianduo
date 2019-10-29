package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

public class MainClickBean extends BaseBean {

    @SerializedName("name")
    String title;

    @SerializedName("picPath")
    String picPath;

    public String getPicPath() {
        return picPath;
    }

    boolean select;

    public MainClickBean(String title, boolean select) {
        this.title = title;
        this.select = select;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    int img;

    public MainClickBean(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
