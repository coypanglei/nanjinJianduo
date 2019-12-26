package com.shaoyue.weizhegou.entity;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class ZxcxListBean extends BaseBean {


    /**
     * records : [{"zxqkms":"11","xykljyqcs":11,"jyxwhye":11,"xykzs":11,"zxshjl":"11","dbsfyq":"是","dbwjfl":"正常","dbje":11,"id":"1","jyxthdkjgs":"11","sxsfzh":"320304198804012414","jyxthye":11,"fjyxyqcs":"11","jyxyqcs":"11","fjyxthye":11,"cs1":11,"cs3":11,"cs2":11,"jyxwjfl":"正常","jyxblye":11,"js":"本人","description":null,"delFlag":null,"xykdqyqcs":11,"jyxgrxyjlpj":"良好","fjyxgrxyjlpj":"一般","fjyxthdkjgs":"11","updateBy":null,"je2":11,"grxyjlpj":"良好","je1":11,"fjyxwhye":11,"je3":11,"xykzgyqje":11,"fjyxwjfl":"正常","xykyyje":11,"fjyxblye":11,"xyksxje":11,"updateTime":null,"fjyxyqje":11,"wjfl":"正常","xykzgyqcs":11,"createBy":null,"createTime":null,"sxid":"120","blye":11,"yqqj3":"11","yqqj1":"11","jyxyqje":11,"yqqj2":"11"},{"zxqkms":"12","xykljyqcs":12,"jyxwhye":12,"xykzs":12,"zxshjl":"12","dbsfyq":"否","dbwjfl":"不正常","dbje":12,"id":"2","jyxthdkjgs":"12","sxsfzh":"320304198804012414","jyxthye":12,"fjyxyqcs":"12","jyxyqcs":"12","fjyxthye":12,"cs1":12,"cs3":12,"cs2":12,"jyxwjfl":"不正常","jyxblye":12,"js":"配偶","description":null,"delFlag":null,"xykdqyqcs":12,"jyxgrxyjlpj":"良好","fjyxgrxyjlpj":"一般","fjyxthdkjgs":"12","updateBy":null,"je2":12,"grxyjlpj":"一般","je1":12,"fjyxwhye":12,"je3":12,"xykzgyqje":12,"fjyxwjfl":"不正常","xykyyje":12,"fjyxblye":12,"xyksxje":12,"updateTime":null,"fjyxyqje":12,"wjfl":"不正常","xykzgyqcs":12,"createBy":null,"createTime":null,"sxid":"120","blye":12,"yqqj3":"12","yqqj1":"12","jyxyqje":12,"yqqj2":"12"}]
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
         * zxqkms : 11
         * xykljyqcs : 11.0
         * jyxwhye : 11.0
         * xykzs : 11.0
         * zxshjl : 11
         * dbsfyq : 是
         * dbwjfl : 正常
         * dbje : 11.0
         * id : 1
         * jyxthdkjgs : 11
         * sxsfzh : 320304198804012414
         * jyxthye : 11.0
         * fjyxyqcs : 11
         * jyxyqcs : 11
         * fjyxthye : 11.0
         * cs1 : 11.0
         * cs3 : 11.0
         * cs2 : 11.0
         * jyxwjfl : 正常
         * jyxblye : 11.0
         * js : 本人
         * description : null
         * delFlag : null
         * xykdqyqcs : 11.0
         * jyxgrxyjlpj : 良好
         * fjyxgrxyjlpj : 一般
         * fjyxthdkjgs : 11
         * updateBy : null
         * je2 : 11.0
         * grxyjlpj : 良好
         * je1 : 11.0
         * fjyxwhye : 11.0
         * je3 : 11.0
         * xykzgyqje : 11.0
         * fjyxwjfl : 正常
         * xykyyje : 11.0
         * fjyxblye : 11.0
         * xyksxje : 11.0
         * updateTime : null
         * fjyxyqje : 11.0
         * wjfl : 正常
         * xykzgyqcs : 11.0
         * createBy : null
         * createTime : null
         * sxid : 120
         * blye : 11.0
         * yqqj3 : 11
         * yqqj1 : 11
         * jyxyqje : 11.0
         * yqqj2 : 11
         */

        private String zxqkms;
        private double xykljyqcs;
        private double jyxwhye;
        private double xykzs;
        private String zxshjl;
        private String dbsfyq;
        private String dbwjfl;
        private double dbje;
        private String id;
        private String jyxthdkjgs;
        private String sxsfzh;
        private double jyxthye;
        private String fjyxyqcs;
        private String jyxyqcs;
        private double fjyxthye;
        private double cs1;
        private double cs3;
        private double cs2;
        private String jyxwjfl;
        private double jyxblye;
        private String js;
        private String description;
        private String delFlag;
        private double xykdqyqcs;
        private String jyxgrxyjlpj;
        private String fjyxgrxyjlpj;
        private String fjyxthdkjgs;
        private String updateBy;
        private double je2;
        private String grxyjlpj;
        private double je1;
        private double fjyxwhye;
        private double je3;
        private double xykzgyqje;
        private String fjyxwjfl;
        private double xykyyje;
        private double fjyxblye;
        private double xyksxje;
        private String updateTime;
        private double fjyxyqje;
        private String wjfl;
        private double xykzgyqcs;
        private String createBy;
        private String createTime;
        private String sxid;
        private double blye;
        private String yqqj3;
        private String yqqj1;
        private double jyxyqje;
        private String yqqj2;

        public String getZxqkms() {
            return zxqkms;
        }

        public void setZxqkms(String zxqkms) {
            this.zxqkms = zxqkms;
        }

        public double getXykljyqcs() {
            return xykljyqcs;
        }

        public void setXykljyqcs(double xykljyqcs) {
            this.xykljyqcs = xykljyqcs;
        }

        public double getJyxwhye() {
            return jyxwhye;
        }

        public void setJyxwhye(double jyxwhye) {
            this.jyxwhye = jyxwhye;
        }

        public double getXykzs() {
            return xykzs;
        }

        public void setXykzs(double xykzs) {
            this.xykzs = xykzs;
        }

        public String getZxshjl() {
            return zxshjl;
        }

        public void setZxshjl(String zxshjl) {
            this.zxshjl = zxshjl;
        }

        public String getDbsfyq() {
            return dbsfyq;
        }

        public void setDbsfyq(String dbsfyq) {
            this.dbsfyq = dbsfyq;
        }

        public String getDbwjfl() {
            return dbwjfl;
        }

        public void setDbwjfl(String dbwjfl) {
            this.dbwjfl = dbwjfl;
        }

        public double getDbje() {
            return dbje;
        }

        public void setDbje(double dbje) {
            this.dbje = dbje;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJyxthdkjgs() {
            return jyxthdkjgs;
        }

        public void setJyxthdkjgs(String jyxthdkjgs) {
            this.jyxthdkjgs = jyxthdkjgs;
        }

        public String getSxsfzh() {
            return sxsfzh;
        }

        public void setSxsfzh(String sxsfzh) {
            this.sxsfzh = sxsfzh;
        }

        public double getJyxthye() {
            return jyxthye;
        }

        public void setJyxthye(double jyxthye) {
            this.jyxthye = jyxthye;
        }

        public String getFjyxyqcs() {
            return fjyxyqcs;
        }

        public void setFjyxyqcs(String fjyxyqcs) {
            this.fjyxyqcs = fjyxyqcs;
        }

        public String getJyxyqcs() {
            return jyxyqcs;
        }

        public void setJyxyqcs(String jyxyqcs) {
            this.jyxyqcs = jyxyqcs;
        }

        public double getFjyxthye() {
            return fjyxthye;
        }

        public void setFjyxthye(double fjyxthye) {
            this.fjyxthye = fjyxthye;
        }

        public double getCs1() {
            return cs1;
        }

        public void setCs1(double cs1) {
            this.cs1 = cs1;
        }

        public double getCs3() {
            return cs3;
        }

        public void setCs3(double cs3) {
            this.cs3 = cs3;
        }

        public double getCs2() {
            return cs2;
        }

        public void setCs2(double cs2) {
            this.cs2 = cs2;
        }

        public String getJyxwjfl() {
            return jyxwjfl;
        }

        public void setJyxwjfl(String jyxwjfl) {
            this.jyxwjfl = jyxwjfl;
        }

        public double getJyxblye() {
            return jyxblye;
        }

        public void setJyxblye(double jyxblye) {
            this.jyxblye = jyxblye;
        }

        public String getJs() {
            return js;
        }

        public void setJs(String js) {
            this.js = js;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public double getXykdqyqcs() {
            return xykdqyqcs;
        }

        public void setXykdqyqcs(double xykdqyqcs) {
            this.xykdqyqcs = xykdqyqcs;
        }

        public String getJyxgrxyjlpj() {
            return jyxgrxyjlpj;
        }

        public void setJyxgrxyjlpj(String jyxgrxyjlpj) {
            this.jyxgrxyjlpj = jyxgrxyjlpj;
        }

        public String getFjyxgrxyjlpj() {
            return fjyxgrxyjlpj;
        }

        public void setFjyxgrxyjlpj(String fjyxgrxyjlpj) {
            this.fjyxgrxyjlpj = fjyxgrxyjlpj;
        }

        public String getFjyxthdkjgs() {
            return fjyxthdkjgs;
        }

        public void setFjyxthdkjgs(String fjyxthdkjgs) {
            this.fjyxthdkjgs = fjyxthdkjgs;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public double getJe2() {
            return je2;
        }

        public void setJe2(double je2) {
            this.je2 = je2;
        }

        public String getGrxyjlpj() {
            return grxyjlpj;
        }

        public void setGrxyjlpj(String grxyjlpj) {
            this.grxyjlpj = grxyjlpj;
        }

        public double getJe1() {
            return je1;
        }

        public void setJe1(double je1) {
            this.je1 = je1;
        }

        public double getFjyxwhye() {
            return fjyxwhye;
        }

        public void setFjyxwhye(double fjyxwhye) {
            this.fjyxwhye = fjyxwhye;
        }

        public double getJe3() {
            return je3;
        }

        public void setJe3(double je3) {
            this.je3 = je3;
        }

        public double getXykzgyqje() {
            return xykzgyqje;
        }

        public void setXykzgyqje(double xykzgyqje) {
            this.xykzgyqje = xykzgyqje;
        }

        public String getFjyxwjfl() {
            return fjyxwjfl;
        }

        public void setFjyxwjfl(String fjyxwjfl) {
            this.fjyxwjfl = fjyxwjfl;
        }

        public double getXykyyje() {
            return xykyyje;
        }

        public void setXykyyje(double xykyyje) {
            this.xykyyje = xykyyje;
        }

        public double getFjyxblye() {
            return fjyxblye;
        }

        public void setFjyxblye(double fjyxblye) {
            this.fjyxblye = fjyxblye;
        }

        public double getXyksxje() {
            return xyksxje;
        }

        public void setXyksxje(double xyksxje) {
            this.xyksxje = xyksxje;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public double getFjyxyqje() {
            return fjyxyqje;
        }

        public void setFjyxyqje(double fjyxyqje) {
            this.fjyxyqje = fjyxyqje;
        }

        public String getWjfl() {
            return wjfl;
        }

        public void setWjfl(String wjfl) {
            this.wjfl = wjfl;
        }

        public double getXykzgyqcs() {
            return xykzgyqcs;
        }

        public void setXykzgyqcs(double xykzgyqcs) {
            this.xykzgyqcs = xykzgyqcs;
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

        public String getSxid() {
            return sxid;
        }

        public void setSxid(String sxid) {
            this.sxid = sxid;
        }

        public double getBlye() {
            return blye;
        }

        public void setBlye(double blye) {
            this.blye = blye;
        }

        public String getYqqj3() {
            return yqqj3;
        }

        public void setYqqj3(String yqqj3) {
            this.yqqj3 = yqqj3;
        }

        public String getYqqj1() {
            return yqqj1;
        }

        public void setYqqj1(String yqqj1) {
            this.yqqj1 = yqqj1;
        }

        public double getJyxyqje() {
            return jyxyqje;
        }

        public void setJyxyqje(double jyxyqje) {
            this.jyxyqje = jyxyqje;
        }

        public String getYqqj2() {
            return yqqj2;
        }

        public void setYqqj2(String yqqj2) {
            this.yqqj2 = yqqj2;
        }
    }
}
