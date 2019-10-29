package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class DiyaDanBaoListBean extends BaseBean {


    /**
     * records : [{"lxdh":"15996937413","sxsfzh":null,"syrqzh":"撒旦","description":null,"updateTime":null,"yjkrgx":"阿斯顿","delFlag":null,"dywsyr":"阿斯顿","createBy":"admin","fwzl":"啊实打实的","createTime":"2019-35-18 15:35:37","updateBy":null,"sxid":"9e0c6b3e5f857c0ed957b89676886235","dywqzh":"3454","id":"5a50996c6704da61b4e0960e95083cb0"}]
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
         * lxdh : 15996937413
         * sxsfzh : null
         * syrqzh : 撒旦
         * description : null
         * updateTime : null
         * yjkrgx : 阿斯顿
         * delFlag : null
         * dywsyr : 阿斯顿
         * createBy : admin
         * fwzl : 啊实打实的
         * createTime : 2019-35-18 15:35:37
         * updateBy : null
         * sxid : 9e0c6b3e5f857c0ed957b89676886235
         * dywqzh : 3454
         * id : 5a50996c6704da61b4e0960e95083cb0
         */
        private boolean click;

        public boolean isClick() {
            return click;
        }

        public void setClick(boolean click) {
            this.click = click;
        }
        private String lxdh;
        private Object sxsfzh;
        private String syrqzh;
        private Object description;
        private Object updateTime;
        private String yjkrgx;
        private Object delFlag;
        private String dywsyr;
        private String createBy;
        private String fwzl;
        private String createTime;
        private Object updateBy;
        private String sxid;
        private String dywqzh;
        private String id;

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public Object getSxsfzh() {
            return sxsfzh;
        }

        public void setSxsfzh(Object sxsfzh) {
            this.sxsfzh = sxsfzh;
        }

        public String getSyrqzh() {
            return syrqzh;
        }

        public void setSyrqzh(String syrqzh) {
            this.syrqzh = syrqzh;
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

        public String getYjkrgx() {
            return yjkrgx;
        }

        public void setYjkrgx(String yjkrgx) {
            this.yjkrgx = yjkrgx;
        }

        public Object getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Object delFlag) {
            this.delFlag = delFlag;
        }

        public String getDywsyr() {
            return dywsyr;
        }

        public void setDywsyr(String dywsyr) {
            this.dywsyr = dywsyr;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getFwzl() {
            return fwzl;
        }

        public void setFwzl(String fwzl) {
            this.fwzl = fwzl;
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

        public String getDywqzh() {
            return dywqzh;
        }

        public void setDywqzh(String dywqzh) {
            this.dywqzh = dywqzh;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
