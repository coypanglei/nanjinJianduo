package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

public class ScreenObject extends BaseBean {

    private String bitmap;

    public ScreenObject(String bitmap, String js) {
        this.bitmap = bitmap;
        this.js = js;
    }

    private String js;

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }
}
