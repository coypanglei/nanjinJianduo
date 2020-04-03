package com.shaoyue.weizhegou.entity.dhgl;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class SdxjlBean extends BaseBean {


    /**
     * records : [{"khmc":"移动客户1","sjrq":"2020-03-31","jysj":"09:54:23","jyje":110.1,"dfxm":"对方123","bz":"123","jylx":"0","id":"19","zjhm":"320304198804012414","jyrq":"2020-04-22","zh":"1112223334444411","dfzh":"1112223334444411"},{"khmc":"移动客户1","sjrq":"2020-03-31","jysj":"09:54:23","jyje":110.1,"dfxm":"对方123","bz":"123","jylx":"0","id":"18","zjhm":"320304198804012414","jyrq":"2020-04-22","zh":"1112223334444411","dfzh":"1112223334444411"}]
     * total : 18
     * size : 2
     * current : 1
     * searchCount : true
     * pages : 9
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
         * khmc : 移动客户1
         * sjrq : 2020-03-31
         * jysj : 09:54:23
         * jyje : 110.1
         * dfxm : 对方123
         * bz : 123
         * jylx : 0
         * id : 19
         * zjhm : 320304198804012414
         * jyrq : 2020-04-22
         * zh : 1112223334444411
         * dfzh : 1112223334444411
         */

        private String khmc;
        private String sjrq;
        private String jysj;
        private double jyje;
        private String dfxm;
        private String bz;
        private String jylx;
        private String id;
        private String zjhm;
        private String jyrq;
        private String zh;
        private String dfzh;

        private boolean click;

        public boolean isClick() {
            return click;
        }

        public void setClick(boolean click) {
            this.click = click;
        }

        public String getKhmc() {
            return khmc;
        }

        public void setKhmc(String khmc) {
            this.khmc = khmc;
        }

        public String getSjrq() {
            return sjrq;
        }

        public void setSjrq(String sjrq) {
            this.sjrq = sjrq;
        }

        public String getJysj() {
            return jysj;
        }

        public void setJysj(String jysj) {
            this.jysj = jysj;
        }

        public double getJyje() {
            return jyje;
        }

        public void setJyje(double jyje) {
            this.jyje = jyje;
        }

        public String getDfxm() {
            return dfxm;
        }

        public void setDfxm(String dfxm) {
            this.dfxm = dfxm;
        }

        public String getBz() {
            return bz;
        }

        public void setBz(String bz) {
            this.bz = bz;
        }

        public String getJylx() {
            return jylx;
        }

        public void setJylx(String jylx) {
            this.jylx = jylx;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getZjhm() {
            return zjhm;
        }

        public void setZjhm(String zjhm) {
            this.zjhm = zjhm;
        }

        public String getJyrq() {
            return jyrq;
        }

        public void setJyrq(String jyrq) {
            this.jyrq = jyrq;
        }

        public String getZh() {
            return zh;
        }

        public void setZh(String zh) {
            this.zh = zh;
        }

        public String getDfzh() {
            return dfzh;
        }

        public void setDfzh(String dfzh) {
            this.dfzh = dfzh;
        }
    }
}
