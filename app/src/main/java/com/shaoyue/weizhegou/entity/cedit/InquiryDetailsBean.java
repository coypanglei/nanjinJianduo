package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

public class InquiryDetailsBean extends BaseBean {

    String title;

    String content1;

    String content2;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public InquiryDetailsBean(String title, String content1) {
        this.title = title;
        this.content1 = content1;
    }

    public InquiryDetailsBean(String title, String content1, String content2) {
        this.title = title;
        this.content1 = content1;
        this.content2 = content2;
    }
}
