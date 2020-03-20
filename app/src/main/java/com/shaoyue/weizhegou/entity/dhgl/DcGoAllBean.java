package com.shaoyue.weizhegou.entity.dhgl;

import com.shaoyue.weizhegou.base.BaseBean;

import org.json.JSONObject;

public class DcGoAllBean extends BaseBean {
    private String id;

    private String title;

    private JSONObject jsonObject;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }



}
