package com.shaoyue.weizhegou.event;

import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/4/4 0004 17:54
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OkOrCancelEvent extends BaseBean {
    //清空数据 1
    private String mType = "";

    private String id;

    private String url;

    @Override
    public String toString() {
        return "OkOrCancelEvent{" +
                "mType='" + mType + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", mBind=" + mBind +
                '}';
    }

    public OkOrCancelEvent(String mType, String id, String url) {
        this.mType = mType;
        this.id = id;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OkOrCancelEvent(String mType, String id) {
        this.mType = mType;
        this.id = id;
    }

    private int mBind;

    public int getmBind() {
        return mBind;
    }

    public void setmBind(int mBind) {
        this.mBind = mBind;
    }

    public OkOrCancelEvent(int mBind) {
        this.mBind = mBind;
    }

    public String getmType() {
        return mType;
    }

    public OkOrCancelEvent(String mType) {
        this.mType = mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }
}
