package com.shaoyue.weizhegou.entity;

import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/3/29 0029 15:23
 * <p>
 * 邮箱：434604925@qq.com
 */
public class FeaturesBean extends BaseBean {

    private String url;

    private String title;

    public String getUrl() {
        return url;
    }

    public FeaturesBean(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
