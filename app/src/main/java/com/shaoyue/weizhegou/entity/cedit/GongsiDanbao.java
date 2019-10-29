package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class GongsiDanbao extends BaseBean {


    /**
     * records : [{"createBy":"admin","createTime":"2019-36-18 18:36:37","updateBy":null,"sxid":"c20cde0c6057e6f3eb3f2ea6ff6a3527","bcdbje":1,"sxsfzh":null,"fdbcs":"asd","description":"asd","dbgsmc":"ada","updateTime":null,"id":"fee14dcc48120f3d52651b0bcc1e1066","delFlag":null}]
     * total : 1
     * size : 15
     * current : 1
     * searchCount : true
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private boolean searchCount;
    private int pages;
    private List<RecordsBean> records;

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

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * createBy : admin
         * createTime : 2019-36-18 18:36:37
         * updateBy : null
         * sxid : c20cde0c6057e6f3eb3f2ea6ff6a3527
         * bcdbje : 1.0
         * sxsfzh : null
         * fdbcs : asd
         * description : asd
         * dbgsmc : ada
         * updateTime : null
         * id : fee14dcc48120f3d52651b0bcc1e1066
         * delFlag : null
         */

        private String createBy;
        private String createTime;
        private Object updateBy;
        private String sxid;
        private double bcdbje;
        private Object sxsfzh;
        private String fdbcs;
        private String description;
        private String dbgsmc;
        private Object updateTime;
        private String id;
        private Object delFlag;
        private boolean click;

        public boolean isClick() {
            return click;
        }

        public void setClick(boolean click) {
            this.click = click;
        }
        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getSxid() {
            return sxid;
        }

        public void setSxid(String sxid) {
            this.sxid = sxid;
        }

        public double getBcdbje() {
            return bcdbje;
        }

        public void setBcdbje(double bcdbje) {
            this.bcdbje = bcdbje;
        }

        public Object getSxsfzh() {
            return sxsfzh;
        }

        public void setSxsfzh(Object sxsfzh) {
            this.sxsfzh = sxsfzh;
        }

        public String getFdbcs() {
            return fdbcs;
        }

        public void setFdbcs(String fdbcs) {
            this.fdbcs = fdbcs;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDbgsmc() {
            return dbgsmc;
        }

        public void setDbgsmc(String dbgsmc) {
            this.dbgsmc = dbgsmc;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Object delFlag) {
            this.delFlag = delFlag;
        }
    }
}
