package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class SxykhListBean extends BaseBean {


    /**
     * records : [{"cgbl":"阿斯顿","lxdh":"啊实打实的","sxsfzh":null,"sfzhm":"620102199003070077","description":null,"updateTime":null,"delFlag":null,"jsqd":"阿斯顿","jszq":"阿斯顿","jzc":"阿斯顿","createBy":"admin","sfwhkh":null,"createTime":"2019-11-11 15:11:39","updateBy":null,"sxid":"8e273eb655a482818ff7e3a1c7d4b1a2","mc":"阿斯顿","jyxm":null,"jydz":null,"id":"1358715efca0d91132988aec1a17a148","wlsj":"阿斯顿"}]
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
         * cgbl : 阿斯顿
         * lxdh : 啊实打实的
         * sxsfzh : null
         * sfzhm : 620102199003070077
         * description : null
         * updateTime : null
         * delFlag : null
         * jsqd : 阿斯顿
         * jszq : 阿斯顿
         * jzc : 阿斯顿
         * createBy : admin
         * sfwhkh : null
         * createTime : 2019-11-11 15:11:39
         * updateBy : null
         * sxid : 8e273eb655a482818ff7e3a1c7d4b1a2
         * mc : 阿斯顿
         * jyxm : null
         * jydz : null
         * id : 1358715efca0d91132988aec1a17a148
         * wlsj : 阿斯顿
         */

        private String cgbl;
        private String lxdh;
        private String sxsfzh;
        private String sfzhm;
        private String description;
        private String updateTime;
        private String delFlag;
        private String jsqd;
        private String jszq;
        private String jzc;
        private String createBy;
        private String sfwhkh;
        private String createTime;
        private String updateBy;
        private String sxid;
        private String mc;
        private String jyxm;
        private String jydz;
        private String id;
        private String wlsj;
        private boolean isClick;

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }

        public String getCgbl() {
            return cgbl;
        }

        public void setCgbl(String cgbl) {
            this.cgbl = cgbl;
        }

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public String getSxsfzh() {
            return sxsfzh;
        }

        public void setSxsfzh(String sxsfzh) {
            this.sxsfzh = sxsfzh;
        }

        public String getSfzhm() {
            return sfzhm;
        }

        public void setSfzhm(String sfzhm) {
            this.sfzhm = sfzhm;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getJsqd() {
            return jsqd;
        }

        public void setJsqd(String jsqd) {
            this.jsqd = jsqd;
        }

        public String getJszq() {
            return jszq;
        }

        public void setJszq(String jszq) {
            this.jszq = jszq;
        }

        public String getJzc() {
            return jzc;
        }

        public void setJzc(String jzc) {
            this.jzc = jzc;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getSfwhkh() {
            return sfwhkh;
        }

        public void setSfwhkh(String sfwhkh) {
            this.sfwhkh = sfwhkh;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getSxid() {
            return sxid;
        }

        public void setSxid(String sxid) {
            this.sxid = sxid;
        }

        public String getMc() {
            return mc;
        }

        public void setMc(String mc) {
            this.mc = mc;
        }

        public String getJyxm() {
            return jyxm;
        }

        public void setJyxm(String jyxm) {
            this.jyxm = jyxm;
        }

        public String getJydz() {
            return jydz;
        }

        public void setJydz(String jydz) {
            this.jydz = jydz;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWlsj() {
            return wlsj;
        }

        public void setWlsj(String wlsj) {
            this.wlsj = wlsj;
        }
    }
}
