package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class UserMsgBean extends BaseBean {

    //"result": {
    //		"records": [{
    //			"dqhj": "填写授信申请",
    //			"khmc": "巩崇龙",
    //			"lczt": "待提交",
    //			"sxid": "ff4d31a1a7c661f5188c76a3fadf7000",
    //			"starttime": "2019-08-21 08:51:16",
    //			"depart_name": "总行"
    //		}, {
    //			"dqhj": "填写授信申请",
    //			"lczt": "待提交",
    //			"sxid": "e427f7ab226f0c0f51e1571f533ecd6d",
    //			"starttime": "2019-08-21 11:22:14",
    //			"depart_name": "总行"
    //		}, {
    //			"dqhj": "填写授信申请",
    //			"lczt": "待提交",
    //			"sxid": "a4335f9cc563fceeab461f14c04763cc",
    //			"starttime": "2019-08-21 17:46:23",
    //			"depart_name": "总行"
    //		}, {
    //			"dqhj": "填写授信申请",
    //			"lczt": "待提交",
    //			"sxid": "018358d3b96a52d72b10db5e2d50a6a3",
    //			"starttime": "2019-08-22 09:51:47",
    //			"depart_name": "总行"
    //		}, {
    //			"dqhj": "填写授信申请",
    //			"lczt": "待提交",
    //			"sxid": "7404b4e416d856ca6e93eb60581f976b",
    //			"starttime": "2019-08-23 11:04:59",
    //			"depart_name": "总行"
    //		}],
    //		"total": 5,
    //		"size": 10,
    //		"current": 1,
    //		"searchCount": true,
    //		"pages": 1

    @SerializedName("total")
    private String total;

    @SerializedName("records")
    private List<UserMsgDetailsBean> record;

    @SerializedName("pages")
    private int pages;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<UserMsgDetailsBean> getRecord() {
        return record;
    }

    public void setRecord(List<UserMsgDetailsBean> record) {
        this.record = record;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
