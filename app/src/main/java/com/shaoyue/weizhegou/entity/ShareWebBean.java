package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;


/**
 * Created by USER on 2018/11/10.
 */

public class ShareWebBean extends BaseBean {
//"img_ico": "http:\/\/com-download.oss-cn-hangzhou.aliyuncs.com\/turtle\/%E9%A2%8615%E6%97%A5%E6%97%B6%E9%95%BF_icon_tpsVCm.png",
//        "pic_share": "http:\/\/com-download.oss-cn-hangzhou.aliyuncs.com\/turtle\/%E6%B5%B7%E9%BE%9F%E4%B8%8B%E8%BD%BD%E8%90%BD%E5%9C%B0%E9%A1%B5_Oqbufo.jpg",
//        "url_share": "http:\/\/www.haigui666.com\/share.html",
//        "title_share": "领15日时长"

    @SerializedName("img_ico")
    private String img_ico;



    @SerializedName("pic_share")

    private String pic_share;

    @SerializedName("url_share")
    private String url_share;

    @SerializedName("title_share")
    private String title_share;


    public String getImg_ico() {
        return img_ico;
    }

    public void setImg_ico(String img_ico) {
        this.img_ico = img_ico;
    }

    public String getPic_share() {
        return pic_share;
    }

    public void setPic_share(String pic_share) {
        this.pic_share = pic_share;
    }

    public String getUrl_share() {
        return url_share;
    }

    public void setUrl_share(String url_share) {
        this.url_share = url_share;
    }

    public String getTitle_share() {
        return title_share;
    }

    public void setTitle_share(String title_share) {
        this.title_share = title_share;
    }
}
