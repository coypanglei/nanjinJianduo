package com.shaoyue.weizhegou.entity.dhgl;

import com.shaoyue.weizhegou.base.BaseBean;

public class SelectDataBean extends BaseBean {

    private String key;

    private String name;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SelectDataBean(String key, String name) {
        this.key = key;
        this.name = name;
    }
}
