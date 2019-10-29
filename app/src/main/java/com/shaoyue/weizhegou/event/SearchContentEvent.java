package com.shaoyue.weizhegou.event;

import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/4/8 0008 10:16
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SearchContentEvent extends BaseBean {

    //搜索内容
    private String content;
    //0 主页 1 分销产品
    private int type;

    public SearchContentEvent(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
