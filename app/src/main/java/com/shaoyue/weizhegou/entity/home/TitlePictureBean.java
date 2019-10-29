package com.shaoyue.weizhegou.entity.home;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/4/8 0008 13:58
 * <p>
 * 邮箱：434604925@qq.com
 */
public class TitlePictureBean extends BaseBean {


    //  "id":1,
    //                "name":"首页",
    //                "img_url":null,
    //                "url":""
    @SerializedName("cat_id")
    private int cat_id;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("img_url")
    private String imgUrl;

    public int getId() {
        return id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    @Override
    public String toString() {
        return "TitlePictureBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
