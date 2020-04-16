package com.shaoyue.weizhegou.entity.cedit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FamilyListBean {
    private int total;
    private int size;
    private int current;
    private boolean searchCount;
    private int pages;

     private int ts;

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @SerializedName("records")
    List<FamilyBean> data;

    public List<FamilyBean> getData() {
        return data;
    }

    public void setData(List<FamilyBean> data) {
        this.data = data;
    }
}
