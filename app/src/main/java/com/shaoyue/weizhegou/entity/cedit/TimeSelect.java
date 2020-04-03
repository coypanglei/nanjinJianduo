package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

public class TimeSelect extends BaseBean {


    private String time;

    private String title;

    @Override
    public String toString() {
        return "TimeSelect{" +
                "time='" + time + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public TimeSelect(String time, String title) {
        this.time = time;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
