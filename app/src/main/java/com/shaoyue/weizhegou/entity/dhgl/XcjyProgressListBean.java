package com.shaoyue.weizhegou.entity.dhgl;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class XcjyProgressListBean extends BaseBean {


    /**
     * records : [{"dqhj":"-1","jcjd":"第5季度","fj":null,"description":null,"updateTime":null,"zjhm":"320304198804012414","clsj":"2019-10-25 15:07:46","clrGh":"admin","createBy":"admin","shyj":"生成现场校验对象","createTime":"2019-10-25 15:07:46","updateBy":null,"clrMc":"管理","shzt":"生成现场校验对象","id":"f9a245edd66495a0b53f28af48ec2224"},{"dqhj":"0","jcjd":"第5季度","fj":null,"description":null,"updateTime":null,"zjhm":"320304198804012414","clsj":"2019-10-25 15:15:09","clrGh":"admin","createBy":"admin","shyj":"小组认领校验对象","createTime":"2019-10-25 15:15:10","updateBy":null,"clrMc":"管理","shzt":"小组认领校验对象","id":"bb9f95b11f5d24423c9764616ef8c691"}]
     * total : 2
     * size : 10
     * current : 1
     * searchCount : true
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private boolean searchCount;
    private int pages;
    private List<XcjyProgressBean> records;

    public List<XcjyProgressBean> getRecords() {
        return records;
    }

    public void setRecords(List<XcjyProgressBean> records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }




}
