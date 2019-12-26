package com.shaoyue.weizhegou.entity.cedit;

import com.blankj.utilcode.util.ObjectUtils;
import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

public class ApplicationBean extends BaseBean {

    //"records": [{
    //   "khmc": null,
    //   "lczt": "待提交",
    //   "flag": "0",
    //   "description": null,
    //   "zzFlag": "1",
    //   "delFlag": null,
    //   "sxjl": null,
    //   "instid": null,
    //   "cswtgyy": null,
    //   "sxjg": null,
    //   "updateBy": null,
    //   "id": "bc080673bdcf8ee04d1e678ec0461e2a",
    //   "approvalFlag": null,
    //   "csjl": null,
    //   "sxsqly": null,
    //   "sfygxd": null,
    //   "taskid": null,
    //   "dqhj": null,
    //   "khh": null,
    //   "updateTime": null,
    //   "list": null,
    //   "slrq": null,
    //   "createBy": "test1",
    //   "sfzh": "320323199305051010",
    //   "createTime": "2019-08-16 15:08:59",
    //   "sqsx": null,
    //   "slr": null
    //    "sqr":sqr
    //  }],

    private String taskid;

    public String getTaskid() {
        if (ObjectUtils.isEmpty(taskid)) {
            return "";
        }
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    private String sqr;

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    private String slrq;

    public String getSlrq() {
        return slrq;
    }

    public void setSlrq(String slrq) {
        this.slrq = slrq;
    }

    @SerializedName("instid")
    private String instid;

    public String getInstid() {

        if (ObjectUtils.isEmpty(instid)) {
            return "";
        }
        return instid;
    }

    public void setInstid(String instid) {
        this.instid = instid;
    }

    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("createTime")
    private String createTime;

    @SerializedName("sfzh")
    private String sfzh;

    @SerializedName("khmc")
    private String khmc;

    @SerializedName("sqsx")
    private String sqsx;

    @SerializedName("sxjg")
    private String sxjg;

    @SerializedName("lczt")
    private String lczt;

    @SerializedName("dqhj")
    private String dqhj;

    @SerializedName("sxjl")
    private String sxjl;

    @SerializedName("slr")
    private String slr;

    @SerializedName("csjl")
    private String csjl;

    private boolean click;

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getSqsx() {
        return sqsx;
    }

    public void setSqsx(String sqsx) {
        this.sqsx = sqsx;
    }

    public String getSxjg() {
        return sxjg;
    }

    public void setSxjg(String sxjg) {
        this.sxjg = sxjg;
    }

    public String getLczt() {
        return lczt;
    }

    public void setLczt(String lczt) {
        this.lczt = lczt;
    }

    public String getDqhj() {
        return dqhj;
    }

    public void setDqhj(String dqhj) {
        this.dqhj = dqhj;
    }

    public String getSxjl() {
        return sxjl;
    }

    public void setSxjl(String sxjl) {
        this.sxjl = sxjl;
    }

    public String getSlr() {
        return slr;
    }

    public void setSlr(String slr) {
        this.slr = slr;
    }

    public String getCsjl() {
        return csjl;
    }

    public void setCsjl(String csjl) {
        this.csjl = csjl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }
}
