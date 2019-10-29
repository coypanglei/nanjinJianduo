package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by USER on 2018/9/20.
 */

public class HomeAd implements Serializable {
    //    {
//        "id":1,
//            "ad_link":"",
//            "ad_code":"https://weizhegou.oss-cn-shanghai.aliyuncs.com/index/banner1%20.png"
//    }
    @SerializedName("id")
    private String id;

    @SerializedName("ad_link")
    private String adLink;

    @SerializedName("ad_code")
    private String adCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdLink() {
        return adLink;
    }

    public void setAdLink(String adLink) {
        this.adLink = adLink;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }
}
