package com.shaoyue.weizhegou.entity.goods;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/4/24 0024 08:58
 * <p>
 * 邮箱：434604925@qq.com
 */
public class GoodsSpecificationBean extends BaseBean {

    // id   规格选项id
//
//	text 规格选项
    @SerializedName("id")
    private long id;

    @SerializedName("text")
    private String text;

    @SerializedName("src")
    private String img;

    @SerializedName("store_count")
    private long storeCount;

    //0 默认状态 //1 选中状态 //2 不可点击状态
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(long storeCount) {
        this.storeCount = storeCount;
    }

    public String getImg() {
        return img;
    }


    public void setImg(String img) {
        this.img = img;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "GoodsSpecificationBean{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", img='" + img + '\'' +
                ", storeCount=" + storeCount +
                ", type=" + type +
                '}';
    }
}
