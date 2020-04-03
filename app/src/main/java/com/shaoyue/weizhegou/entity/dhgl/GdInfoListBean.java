package com.shaoyue.weizhegou.entity.dhgl;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class GdInfoListBean extends BaseBean {


    /**
     * records : [{"khmc":"张三","khdz":"XXXX小区","dbfs":"抵押","fxfl":"抵挡","lxfs":"15632565665","zjhm":"62010219900307369X","dkje":34,"dkyt":"买房","bqsfcxyqqx":"是","qsrq":"2020-02-17","id":"1","dkye":12,"sfyqhbl":"否"}]
     * total : 1
     * size : 15
     * current : 1
     * searchCount : true
     * pages : 1
     */

    private int  total;
    private int  size;
    private  int current;
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
         * khmc : 张三
         * khdz : XXXX小区
         * dbfs : 抵押
         * fxfl : 抵挡
         * lxfs : 15632565665
         * zjhm : 62010219900307369X
         * dkje : 34
         * dkyt : 买房
         * bqsfcxyqqx : 是
         * qsrq : 2020-02-17
         * id : 1
         * dkye : 12
         * sfyqhbl : 否
         */

        private String ghjlmc;

        private String jgmc;

        public String getJgmc() {
            return jgmc;
        }

        public void setJgmc(String jgmc) {
            this.jgmc = jgmc;
        }

        public String getGhjlmc() {
            return ghjlmc;
        }

        public void setGhjlmc(String ghjlmc) {
            this.ghjlmc = ghjlmc;
        }
        private String zt;
        private String spnr;

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public String getSpnr() {
            return spnr;
        }

        public void setSpnr(String spnr) {
            this.spnr = spnr;
        }

        private String khmc;
        private String khdz;
        private String dbfs;
        private String fxfl;
        private String lxfs;
        private String zjhm;
        private String dkje;
        private String dkyt;
        private String bqsfcxyqqx;
        private String qsrq;
        private String id;
        private String dkye;
        private String sfyqhbl;
        private boolean isClick;

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }

        public String getKhmc() {
            return khmc;
        }

        public void setKhmc(String khmc) {
            this.khmc = khmc;
        }

        public String getKhdz() {
            return khdz;
        }

        public void setKhdz(String khdz) {
            this.khdz = khdz;
        }

        public String getDbfs() {
            return dbfs;
        }

        public void setDbfs(String dbfs) {
            this.dbfs = dbfs;
        }

        public String getFxfl() {
            return fxfl;
        }

        public void setFxfl(String fxfl) {
            this.fxfl = fxfl;
        }

        public String getLxfs() {
            return lxfs;
        }

        public void setLxfs(String lxfs) {
            this.lxfs = lxfs;
        }

        public String getZjhm() {
            return zjhm;
        }

        public void setZjhm(String zjhm) {
            this.zjhm = zjhm;
        }

        public String getDkje() {
            return dkje;
        }

        public void setDkje(String dkje) {
            this.dkje = dkje;
        }

        public String getDkyt() {
            return dkyt;
        }

        public void setDkyt(String dkyt) {
            this.dkyt = dkyt;
        }

        public String getBqsfcxyqqx() {
            return bqsfcxyqqx;
        }

        public void setBqsfcxyqqx(String bqsfcxyqqx) {
            this.bqsfcxyqqx = bqsfcxyqqx;
        }

        public String getQsrq() {
            return qsrq;
        }

        public void setQsrq(String qsrq) {
            this.qsrq = qsrq;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDkye() {
            return dkye;
        }

        public void setDkye(String dkye) {
            this.dkye = dkye;
        }

        public String getSfyqhbl() {
            return sfyqhbl;
        }

        public void setSfyqhbl(String sfyqhbl) {
            this.sfyqhbl = sfyqhbl;
        }
    }
}
