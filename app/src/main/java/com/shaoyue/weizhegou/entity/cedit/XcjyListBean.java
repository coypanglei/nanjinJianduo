package com.shaoyue.weizhegou.entity.cedit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class XcjyListBean {
    @SerializedName("records")
    private List<XcjyBean> mBaseBean;

    public List<XcjyBean> getmBaseBean() {
        return mBaseBean;
    }

    public void setmBaseBean(List<XcjyBean> mBaseBean) {
        this.mBaseBean = mBaseBean;
    }

    @SerializedName("pages")
    private int pages;


    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }


}
