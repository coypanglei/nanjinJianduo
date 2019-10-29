package com.shaoyue.weizhegou.entity.cedit;

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
     */

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
