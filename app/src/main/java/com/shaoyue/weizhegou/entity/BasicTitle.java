package com.shaoyue.weizhegou.entity;

import com.shaoyue.weizhegou.base.BaseBean;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.sxdc.DbBean;

import java.util.List;

public class BasicTitle extends BaseBean {

    String title;

    private List<BasicInformationBean.RecordsBean> mlist;

    private List<DbBean> mdbBeanList;

    public List<DbBean> getMdbBeanList() {
        return mdbBeanList;
    }

    public void setMdbBeanList(List<DbBean> mdbBeanList) {
        this.mdbBeanList = mdbBeanList;
    }

    public BasicTitle(String title, List<BasicInformationBean.RecordsBean> mlist) {
        this.title = title;
        this.mlist = mlist;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BasicInformationBean.RecordsBean> getMlist() {
        return mlist;
    }

    public void setMlist(List<BasicInformationBean.RecordsBean> mlist) {
        this.mlist = mlist;
    }

    @Override
    public String toString() {
        return "BasicTitle{" +
                "title='" + title + '\'' +
                ", mlist=" + mlist +
                '}';
    }
}
