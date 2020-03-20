package com.shaoyue.weizhegou.entity.user;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.base.BaseBean;

public class SxsqDanBean extends BaseBean {


    /**
     * id : null
     * sxid : null
     * sxsfzh : null
     * sqrxm : 测试2
     * sfzh : 110101199003072877
     * hyzk : 未婚
     * lxfs : 11111111
     * czdz : null
     * ed : null
     * qx : null
     * yt : null
     * sqrqm : null
     * jsqm : null
     * delFlag : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * description : null
     * zh : null
     * zydbfs : null
     * khjl : null
     * sqsj : 2019-12-22
     */

    private String id;
    private String sxid;
    private String sxsfzh;
    private String sqrxm;
    private String sfzh;
    private String hyzk;
    private String lxfs;
    private String czdz;
    private String ed;
    private String qx;
    private String yt;
    private String sqrqm;
    private String jsqm;
    private String delFlag;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String description;
    private String zh;
    private String zydbfs;
    private String khjl;
    private String sqsj;

    private String sqrpoqm;

    public String getSqrpoqm() {
        return sqrpoqm;
    }

    public void setSqrpoqm(String sqrpoqm) {
        this.sqrpoqm = sqrpoqm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSxid() {
        return sxid;
    }

    public void setSxid(String sxid) {
        this.sxid = sxid;
    }

    public String getSxsfzh() {
        return sxsfzh;
    }

    public void setSxsfzh(String sxsfzh) {
        this.sxsfzh = sxsfzh;
    }

    public String getSqrxm() {
        return sqrxm;
    }

    public void setSqrxm(String sqrxm) {
        this.sqrxm = sqrxm;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getHyzk() {
        return hyzk;
    }

    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public String getCzdz() {
        return czdz;
    }

    public void setCzdz(String czdz) {
        this.czdz = czdz;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }

    public String getYt() {
        return yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
    }

    public String getSqrqm() {
        return sqrqm;
    }

    public void setSqrqm(String sqrqm) {
        this.sqrqm = sqrqm;
    }

    public String getJsqm() {
        return jsqm;
    }

    public void setJsqm(String jsqm) {
        this.jsqm = jsqm;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getZydbfs() {
        return zydbfs;
    }

    public void setZydbfs(String zydbfs) {
        this.zydbfs = zydbfs;
    }

    public String getKhjl() {
        if (ObjectUtils.isEmpty(khjl)) {
            return "";
        }
        return khjl;
    }

    public void setKhjl(String khjl) {
        this.khjl = khjl;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }
}
