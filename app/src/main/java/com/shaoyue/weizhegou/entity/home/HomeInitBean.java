package com.shaoyue.weizhegou.entity.home;


import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.entity.HomeAd;

import java.io.Serializable;

import java.util.List;

/**
 * Created by USER on 2018/9/20.
 */

public class HomeInitBean implements Serializable {
    //{
    //    "code":200,
    //    "msg":"获取成功",
    //    "time":"1554695679",
    //    "data":{
    //        "top":[
    //            {
    //                "id":1,
    //                "name":"首页",
    //                "img_url":null,
    //                "url":""
    //            },
    //            {
    //                "id":2,
    //                "name":"全部商品",
    //                "img_url":null,
    //                "url":""
    //            },
    //            {
    //                "id":12,
    //                "name":"明日预告",
    //                "img_url":null,
    //                "url":""
    //            },
    //            {
    //                "id":13,
    //                "name":"店主门槛",
    //                "img_url":null,
    //                "url":""
    //            },
    //            {
    //                "id":15,
    //                "name":"今日团购",
    //                "img_url":null,
    //                "url":""
    //            }
    //        ],
    //        "middle":[
    //            {
    //                "id":4,
    //                "name":"整点秒杀",
    //                "img_url":"https://weizhegou.oss-cn-shanghai.aliyuncs.com/index/%E6%95%B4%E7%82%B9%E7%A7%92%E6%9D%80.png",
    //                "url":""
    //            },
    //            {
    //                "id":5,
    //                "name":"今日团购",
    //                "img_url":"https://weizhegou.oss-cn-shanghai.aliyuncs.com/index/%E4%BB%8A%E6%97%A5%E5%9B%A2%E8%B4%AD.png",
    //                "url":""
    //            },
    //            {
    //                "id":6,
    //                "name":"店主门槛",
    //                "img_url":"https://weizhegou.oss-cn-shanghai.aliyuncs.com/index/%E5%BA%97%E4%B8%BB%E9%97%A8%E6%A7%9B.png",
    //                "url":""
    //            },
    //            {
    //                "id":7,
    //                "name":"申请供应商",
    //                "img_url":"https://weizhegou.oss-cn-shanghai.aliyuncs.com/index/%E4%BE%9B%E5%BA%94%E5%95%86.png",
    //                "url":""
    //            }
    //        ],
    //        "ad":[
    //            {
    //                "id":1,
    //                "ad_link":"",
    //                "ad_code":"https://weizhegou.oss-cn-shanghai.aliyuncs.com/index/banner1%20.png"
    //            },
    //            {
    //                "id":2,
    //                "ad_link":"",
    //                "ad_code":"https://weizhegou.oss-cn-shanghai.aliyuncs.com/index/banner2%20.png"
    //            },
    //            {
    //                "id":3,
    //                "ad_link":"",
    //                "ad_code":"https://weizhegou.oss-cn-shanghai.aliyuncs.com/index/banner3%20.png"
    //            }
    //        ]
    //    }
    //}

    @SerializedName("broadcast_notice")
    private List<String> mBroadcastNotice;


    public List<String> getmBroadcastNotice() {
        return mBroadcastNotice;
    }

    public void setmBroadcastNotice(List<String> mBroadcastNotice) {
        this.mBroadcastNotice = mBroadcastNotice;
    }

    @SerializedName("shopping_notes")
    private String shoppingNotes;

    public String getShoppingNotes() {
        return shoppingNotes;
    }

    public void setShoppingNotes(String shoppingNotes) {
        this.shoppingNotes = shoppingNotes;
    }

    @SerializedName("top")
    private List<TitlePictureBean> mTopTitlePicture;


    @SerializedName("middle")
    private List<TitlePictureBean> mMiddleTitlePicture;

    @SerializedName("ad")
    private List<HomeAd> homeAdList;

    @SerializedName("categories")
    private List<categoriesBean> categoriesBeanList;

    public List<categoriesBean> getCategoriesBeanList() {
        return categoriesBeanList;
    }

    public void setCategoriesBeanList(List<categoriesBean> categoriesBeanList) {
        this.categoriesBeanList = categoriesBeanList;
    }

    public List<TitlePictureBean> getmTopTitlePicture() {
        return mTopTitlePicture;
    }

    public void setmTopTitlePicture(List<TitlePictureBean> mTopTitlePicture) {
        this.mTopTitlePicture = mTopTitlePicture;
    }

    public List<TitlePictureBean> getmMiddleTitlePicture() {
        return mMiddleTitlePicture;
    }

    public void setmMiddleTitlePicture(List<TitlePictureBean> mMiddleTitlePicture) {
        this.mMiddleTitlePicture = mMiddleTitlePicture;
    }

    public List<HomeAd> getHomeAdList() {
        return homeAdList;
    }

    public void setHomeAdList(List<HomeAd> homeAdList) {
        this.homeAdList = homeAdList;
    }


}
