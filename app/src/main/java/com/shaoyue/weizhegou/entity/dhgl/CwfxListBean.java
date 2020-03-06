package com.shaoyue.weizhegou.entity.dhgl;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class CwfxListBean extends BaseBean {


    /**
     * records : [{"ldbl":null,"xsmll":null,"ldzc":1,"lrze":null,"yysr":null,"zcfzl":"1","pid":"6","id":"ea137f657a3653b111950e7d305155d5","zcze":1,"ldfz":1,"fzze":1,"rq":"2020-02-26"},{"ldbl":"1","xsmll":null,"ldzc":1,"lrze":null,"yysr":null,"zcfzl":"1","pid":"6","id":"8c434f1160dc3c55b43ce69a03b17ac1","zcze":1,"ldfz":1,"fzze":1,"rq":"2020-02-24"}]
     * total : 2
     * size : 3
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
         * ldbl : null
         * xsmll : null
         * ldzc : 1
         * lrze : null
         * yysr : null
         * zcfzl : 1
         * pid : 6
         * id : ea137f657a3653b111950e7d305155d5
         * zcze : 1
         * ldfz : 1
         * fzze : 1
         * rq : 2020-02-26
         */

        private String ldbl;
        private String xsmll;
        private String ldzc;
        private String lrze;
        private String yysr;
        private String zcfzl;
        private String pid;
        private String id;
        private String zcze;
        private String ldfz;
        private String fzze;
        private String rq;
        //1标题类型  2不可填写类型 3 可填写类型
        private String type;

        public RecordsBean(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLdbl() {
            return ldbl;
        }

        public void setLdbl(String ldbl) {
            this.ldbl = ldbl;
        }

        public String getXsmll() {
            return xsmll;
        }

        public void setXsmll(String xsmll) {
            this.xsmll = xsmll;
        }



        public String getLrze() {
            return lrze;
        }

        public void setLrze(String lrze) {
            this.lrze = lrze;
        }

        public String getYysr() {
            return yysr;
        }

        public void setYysr(String yysr) {
            this.yysr = yysr;
        }

        public String getZcfzl() {
            return zcfzl;
        }

        public void setZcfzl(String zcfzl) {
            this.zcfzl = zcfzl;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLdzc() {
            return ldzc;
        }

        public void setLdzc(String ldzc) {
            this.ldzc = ldzc;
        }

        public String getZcze() {
            return zcze;
        }

        public void setZcze(String zcze) {
            this.zcze = zcze;
        }

        public String getLdfz() {
            return ldfz;
        }

        public void setLdfz(String ldfz) {
            this.ldfz = ldfz;
        }

        public String getFzze() {
            return fzze;
        }

        public void setFzze(String fzze) {
            this.fzze = fzze;
        }

        public String getRq() {
            return rq;
        }

        public void setRq(String rq) {
            this.rq = rq;
        }
    }
}
