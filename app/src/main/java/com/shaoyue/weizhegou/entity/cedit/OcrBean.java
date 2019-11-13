package com.shaoyue.weizhegou.entity.cedit;

import android.content.Intent;

public class OcrBean {

    Intent data;

    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    int resultCode;

    public OcrBean(Intent data, int resultCode) {
        this.data = data;
        this.resultCode = resultCode;
    }

    public Intent getData() {
        return data;
    }

    public void setData(Intent data) {
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "OcrBean{" +
                "data=" + data +
                ", title='" + title + '\'' +
                ", resultCode=" + resultCode +
                '}';
    }
}
