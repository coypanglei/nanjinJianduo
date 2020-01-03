package com.shaoyue.weizhegou.entity.user;

import com.shaoyue.weizhegou.base.BaseBean;

public class QianzibanDanBean extends BaseBean {

    String title;

    String content;

    public QianzibanDanBean(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
