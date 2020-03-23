package com.shaoyue.weizhegou.entity.dhgl;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class DgInfoListBean extends BaseBean {


    /**
     * records : [{"khdz":"xxx村","dbfs":"抵押","sshy":"批发零售业","qyzjhm":"110101199003078670","fxfl":"F类","qymc":"批发零售业","lxfs":"15966663545","frzjhm":"320302198712104714","dkje":34,"dkyt":"批发零售业","qsrq":"2020-02-20","frdb":"齐全","id":"6","dkye":34},{"khdz":"xxx村","dbfs":"抵押","sshy":"制造业","qyzjhm":"110101199003078670","fxfl":"G类","qymc":"制造业","lxfs":"15966663545","frzjhm":"320302198712104714","dkje":44,"dkyt":"制造业","qsrq":"2020-02-20","frdb":"大","id":"7","dkye":3},{"khdz":"xxxx村","dbfs":"抵押","sshy":"对公贷款","qyzjhm":"62010219900307369X","fxfl":"B类","qymc":"对公贷款","lxfs":"15966663545","frzjhm":"320302198712104714","dkje":23,"dkyt":"做生意","qsrq":"2020-02-20","frdb":"李四","id":"2","dkye":11},{"khdz":"xxx小区","dbfs":"抵押","sshy":"农林牧渔业","qyzjhm":"110101199003078670","fxfl":"A类","qymc":"农林牧渔业","lxfs":"15966663545","frzjhm":"320302198712104714","dkje":23,"dkyt":"买房","qsrq":"2020-02-20","frdb":"张三","id":"1","dkye":12},{"khdz":"xxx村","dbfs":"抵押","sshy":"其他","qyzjhm":"110101199003078670","fxfl":"C类","qymc":"其他","lxfs":"15966663545","frzjhm":"320302198712104714","dkje":23,"dkyt":"其他","qsrq":"2020-02-20","frdb":"王五","id":"3","dkye":11},{"khdz":"xxx村","dbfs":"抵押","sshy":"房地产","qyzjhm":"110101199003078670","fxfl":"D类","qymc":"房地产","lxfs":"15966663545","frzjhm":"320302198712104714","dkje":23,"dkyt":"房地产","qsrq":"2020-02-20","frdb":"孙八","id":"4","dkye":22},{"khdz":"xxx村","dbfs":"抵押","sshy":"建筑业","qyzjhm":"110101199003078670","fxfl":"E类","qymc":"建筑业","lxfs":"15966663545","frzjhm":"320302198712104714","dkje":23,"dkyt":"建筑业","qsrq":"2020-02-20","frdb":"周七","id":"5","dkye":12}]
     * total : 7
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
         * khdz : xxx村
         * dbfs : 抵押
         * sshy : 批发零售业
         * qyzjhm : 110101199003078670
         * fxfl : F类
         * qymc : 批发零售业
         * lxfs : 15966663545
         * frzjhm : 320302198712104714
         * dkje : 34
         * dkyt : 批发零售业
         * qsrq : 2020-02-20
         * frdb : 齐全
         * id : 6
         * dkye : 34
         */
       private  boolean click;

        private String zt;

        private String zzm;

        public String getZzm() {
            return zzm;
        }

        public void setZzm(String zzm) {
            this.zzm = zzm;
        }

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
        public boolean isClick() {
            return click;
        }

        public void setClick(boolean click) {
            this.click = click;
        }

        private String khdz;
        private String dbfs;
        private String sshy;
        private String qyzjhm;
        private String fxfl;
        private String qymc;
        private String lxfs;
        private String frzjhm;
        private String dkje;
        private String dkyt;
        private String qsrq;
        private String frdb;
        private String id;
        private String dkye;

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

        public String getSshy() {
            return sshy;
        }

        public void setSshy(String sshy) {
            this.sshy = sshy;
        }

        public String getQyzjhm() {
            return qyzjhm;
        }

        public void setQyzjhm(String qyzjhm) {
            this.qyzjhm = qyzjhm;
        }

        public String getFxfl() {
            return fxfl;
        }

        public void setFxfl(String fxfl) {
            this.fxfl = fxfl;
        }

        public String getQymc() {
            return qymc;
        }

        public void setQymc(String qymc) {
            this.qymc = qymc;
        }

        public String getLxfs() {
            return lxfs;
        }

        public void setLxfs(String lxfs) {
            this.lxfs = lxfs;
        }

        public String getFrzjhm() {
            return frzjhm;
        }

        public void setFrzjhm(String frzjhm) {
            this.frzjhm = frzjhm;
        }



        public String getDkyt() {
            return dkyt;
        }

        public void setDkyt(String dkyt) {
            this.dkyt = dkyt;
        }

        public String getQsrq() {
            return qsrq;
        }

        public void setQsrq(String qsrq) {
            this.qsrq = qsrq;
        }

        public String getFrdb() {
            return frdb;
        }

        public void setFrdb(String frdb) {
            this.frdb = frdb;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


    }
}
