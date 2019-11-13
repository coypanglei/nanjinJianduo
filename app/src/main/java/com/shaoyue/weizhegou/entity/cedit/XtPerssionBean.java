package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

public class XtPerssionBean extends BaseBean {


    /**
     * img : ../../assets/imgs/target.png
     * mobileImg : mobieImage/znyx.png
     * sysName : znyx
     * title : 智能营销
     * nums : 0
     * status : false
     */

    private String img;
    private String mobileImg;
    private String sysName;
    private String title;
    private int nums;
    private boolean status;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMobileImg() {
        return mobileImg;
    }

    public void setMobileImg(String mobileImg) {
        this.mobileImg = mobileImg;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
