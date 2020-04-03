package com.shaoyue.weizhegou.entity.dhgl;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class SdInfoListBean extends BaseBean {


    /**
     * current : 0
     * pages : 0
     * records : [{"dbfs":"","dkje":"","dkye":"","dkyt":"","id":"","khmc":"","qsrq":"","zffs":"","zjhm":""}]
     * searchCount : true
     * size : 0
     * total : 0
     */

    private int current;
    private int pages;
    private boolean searchCount;
    private int size;
    private int total;
    private List<RecordsBean> records;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * dbfs :
         * dkje :
         * dkye :
         * dkyt :
         * id :
         * khmc :
         * qsrq :
         * zffs :
         * zjhm :
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

        private String dbfs;
        private String dkje;
        private String dkye;
        private String dkyt;
        private String id;
        private String khmc;
        private String qsrq;
        private String zffs;
        private String zjhm;
        private boolean click;

        public boolean isClick() {
            return click;
        }

        public void setClick(boolean click) {
            this.click = click;
        }

        public String getDbfs() {
            return dbfs;
        }

        public void setDbfs(String dbfs) {
            this.dbfs = dbfs;
        }

        public String getDkje() {
            return dkje;
        }

        public void setDkje(String dkje) {
            this.dkje = dkje;
        }

        public String getDkye() {
            return dkye;
        }

        public void setDkye(String dkye) {
            this.dkye = dkye;
        }

        public String getDkyt() {
            return dkyt;
        }

        public void setDkyt(String dkyt) {
            this.dkyt = dkyt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKhmc() {
            return khmc;
        }

        public void setKhmc(String khmc) {
            this.khmc = khmc;
        }

        public String getQsrq() {
            return qsrq;
        }

        public void setQsrq(String qsrq) {
            this.qsrq = qsrq;
        }

        public String getZffs() {
            return zffs;
        }

        public void setZffs(String zffs) {
            this.zffs = zffs;
        }

        public String getZjhm() {
            return zjhm;
        }

        public void setZjhm(String zjhm) {
            this.zjhm = zjhm;
        }
    }
}
