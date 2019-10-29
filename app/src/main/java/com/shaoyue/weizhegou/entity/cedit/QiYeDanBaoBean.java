package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class QiYeDanBaoBean extends BaseBean {


    /**
     * records : [{"createBy":"admin","createTime":"2019-05-19 10:05:15","updateBy":null,"sxid":"c20cde0c6057e6f3eb3f2ea6ff6a3527","zcdz":"啊啊","sxsfzh":null,"sjjydz":"阿斯顿","description":null,"updateTime":null,"id":"7cef46acbe59b2bdfb615925ae9f5ec4","qymc":"撒大大","delFlag":null}]
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
         * createTime : 2019-05-19 10:05:15
         * updateBy : null
         * sxid : c20cde0c6057e6f3eb3f2ea6ff6a3527
         * zcdz : 啊啊
         * sxsfzh : null
         * sjjydz : 阿斯顿
         * description : null
         * updateTime : null
         * id : 7cef46acbe59b2bdfb615925ae9f5ec4
         * qymc : 撒大大
         * delFlag : null
         */
        private boolean click;

        public boolean isClick() {
            return click;
        }

        public void setClick(boolean click) {
            this.click = click;
        }
        private String createBy;
        private String createTime;
        private Object updateBy;
        private String sxid;
        private String zcdz;
        private Object sxsfzh;
        private String sjjydz;
        private Object description;
        private Object updateTime;
        private String id;
        private String qymc;
        private Object delFlag;

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

        public String getZcdz() {
            return zcdz;
        }

        public void setZcdz(String zcdz) {
            this.zcdz = zcdz;
        }

        public Object getSxsfzh() {
            return sxsfzh;
        }

        public void setSxsfzh(Object sxsfzh) {
            this.sxsfzh = sxsfzh;
        }

        public String getSjjydz() {
            return sjjydz;
        }

        public void setSjjydz(String sjjydz) {
            this.sjjydz = sjjydz;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
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

        public String getQymc() {
            return qymc;
        }

        public void setQymc(String qymc) {
            this.qymc = qymc;
        }

        public Object getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Object delFlag) {
            this.delFlag = delFlag;
        }
    }
}
