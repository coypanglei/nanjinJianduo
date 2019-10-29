package com.shaoyue.weizhegou.entity.cedit;

import java.util.List;

public class ZiRanDanBaoListBean {


    /**
     * records : [{"sxsfzh":null,"description":null,"updateTime":null,"delFlag":null,"dbrposfzh":null,"createBy":"admin","hyzk":"未婚","sfzh":"320303199303270819","xm":"阿萨","createTime":"2019-19-18 14:19:33","updateBy":null,"sxid":"d807acf39b08539e79c602a01962a42d","id":"50d236ed31ce81c90cf02f427dcf1eb3","dbrpo":null}]
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
         * sxsfzh : null
         * description : null
         * updateTime : null
         * delFlag : null
         * dbrposfzh : null
         * createBy : admin
         * hyzk : 未婚
         * sfzh : 320303199303270819
         * xm : 阿萨
         * createTime : 2019-19-18 14:19:33
         * updateBy : null
         * sxid : d807acf39b08539e79c602a01962a42d
         * id : 50d236ed31ce81c90cf02f427dcf1eb3
         * dbrpo : null
         */
        private boolean click;

        public boolean isClick() {
            return click;
        }

        public void setClick(boolean click) {
            this.click = click;
        }
        private Object sxsfzh;
        private String description;
        private Object updateTime;
        private Object delFlag;
        private String dbrposfzh;
        private String createBy;
        private String hyzk;
        private String sfzh;
        private String xm;
        private String createTime;
        private Object updateBy;
        private String sxid;
        private String id;
        private String dbrpo;

        public Object getSxsfzh() {
            return sxsfzh;
        }

        public void setSxsfzh(Object sxsfzh) {
            this.sxsfzh = sxsfzh;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Object delFlag) {
            this.delFlag = delFlag;
        }

        public String getDbrposfzh() {
            return dbrposfzh;
        }

        public void setDbrposfzh(String dbrposfzh) {
            this.dbrposfzh = dbrposfzh;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getHyzk() {
            return hyzk;
        }

        public void setHyzk(String hyzk) {
            this.hyzk = hyzk;
        }

        public String getSfzh() {
            return sfzh;
        }

        public void setSfzh(String sfzh) {
            this.sfzh = sfzh;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDbrpo() {
            return dbrpo;
        }

        public void setDbrpo(String dbrpo) {
            this.dbrpo = dbrpo;
        }
    }
}
