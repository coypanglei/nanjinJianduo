package com.shaoyue.weizhegou.entity.cedit;

import android.graphics.Bitmap;

public class ScreenObject {

    private Bitmap bitmap;

    public ScreenObject(Bitmap bitmap, String js) {
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

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ScreenObject(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
