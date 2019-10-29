package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;


/**
 * Created by USER on 2018/12/3.
 */

public class NewsBean extends BaseBean {

//    {
//        "id": 15,
//            "sn": "4effad8517c4816f12a66e670148b5ed",
//            "name": "222",
//            "sub_name": "22",
//            "main_img": "2",
//            "read_times": 0,
//            "create_time": 1540611248,

//    }


    private String id;

    private String sn;

    private String ticker;

    private String title;

    @SerializedName("create_time")
    private long createTime;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    private String text;


    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }


}
