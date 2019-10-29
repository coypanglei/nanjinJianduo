package com.shaoyue.weizhegou.entity.cedit;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class ApplicationListBean extends BaseBean {

    @SerializedName("records")
    private List<ApplicationBean> mBaseBean;

    public List<ApplicationBean> getmBaseBean() {
        return mBaseBean;
    }

    public void setmBaseBean(List<ApplicationBean> mBaseBean) {
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
