package com.shaoyue.weizhegou.entity.cedit;

import android.graphics.Bitmap;

import com.shaoyue.weizhegou.base.BaseBean;

public class QianziBean extends BaseBean {


    /**
     * createBy :
     * createTime :
     * cxlx :
     * delFlag :
     * description :
     * id :
     * js :
     * sqrjbkhjlqm :
     * sqrqm :
     * sxid :
     * sxrq :
     * sxsfzh :
     * txrq :
     * updateBy :
     * updateTime :
     * zjhm :
     * isQianMing：
     */

    private String uploadImg;

    @Override
    public String toString() {
        return "QianziBean{" +
                "uploadImg='" + uploadImg + '\'' +
                ", isQianMing='" + isQianMing + '\'' +
                ", cxlx='" + cxlx + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", js='" + js + '\'' +
                ", sqrjbkhjlqm='" + sqrjbkhjlqm + '\'' +
                ", sqrqm='" + sqrqm + '\'' +
                ", sxid='" + sxid + '\'' +
                ", sxrq='" + sxrq + '\'' +
                ", sxsfzh='" + sxsfzh + '\'' +
                ", txrq='" + txrq + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", zjhm='" + zjhm + '\'' +
                ", type=" + type +
                '}';
    }

    private Bitmap screenImg;

    private String screenshotImg;

    public Bitmap getScreenImg() {
        return screenImg;
    }

    public void setScreenImg(Bitmap screenImg) {
        this.screenImg = screenImg;
    }

    public String getScreenshotImg() {
        return screenshotImg;
    }

    public void setScreenshotImg(String screenshotImg) {
        this.screenshotImg = screenshotImg;
    }

    public String getUploadImg() {
        return uploadImg;
    }

    public void setUploadImg(String uploadImg) {
        this.uploadImg = uploadImg;
    }

    private String isQianMing;

    public String getIsQianMing() {
        return isQianMing;
    }

    public void setIsQianMing(String isQianMing) {
        this.isQianMing = isQianMing;
    }

    private String cxlx;
    private String delFlag;
    private String description;
    private String id;
    private String js;
    private String sqrjbkhjlqm;
    private String sqrqm;
    private String sxid;
    private String sxrq;
    private String sxsfzh;
    private String txrq;
    private String updateBy;
    private String updateTime;
    private String zjhm;
    //0 本人 1银行业务员
    private boolean type;

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getCxlx() {
        return cxlx;
    }

    public void setCxlx(String cxlx) {
        this.cxlx = cxlx;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }

    public String getSqrjbkhjlqm() {
        return sqrjbkhjlqm;
    }

    public void setSqrjbkhjlqm(String sqrjbkhjlqm) {
        this.sqrjbkhjlqm = sqrjbkhjlqm;
    }

    public String getSqrqm() {
        return sqrqm;
    }

    public void setSqrqm(String sqrqm) {
        this.sqrqm = sqrqm;
    }

    public String getSxid() {
        return sxid;
    }

    public void setSxid(String sxid) {
        this.sxid = sxid;
    }

    public String getSxrq() {
        return sxrq;
    }

    public void setSxrq(String sxrq) {
        this.sxrq = sxrq;
    }

    public String getSxsfzh() {
        return sxsfzh;
    }

    public void setSxsfzh(String sxsfzh) {
        this.sxsfzh = sxsfzh;
    }

    public String getTxrq() {
        return txrq;
    }

    public void setTxrq(String txrq) {
        this.txrq = txrq;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }
}
