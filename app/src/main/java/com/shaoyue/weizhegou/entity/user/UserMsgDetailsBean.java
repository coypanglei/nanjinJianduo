package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

public class UserMsgDetailsBean extends BaseBean {

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


    @SerializedName("depart_name")
    private String depart_name;

    @SerializedName("khmc")
    private String khmc;

    @SerializedName("dqhj")
    private String dqhj;

    @SerializedName("starttime")
    private String starttime;

    public String getStarttime() {
        return starttime;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getDqhj() {
        return dqhj;
    }

    public void setDqhj(String dqhj) {
        this.dqhj = dqhj;
    }
}
