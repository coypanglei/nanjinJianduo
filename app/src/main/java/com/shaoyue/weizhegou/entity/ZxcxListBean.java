package com.shaoyue.weizhegou.entity;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.ArrayList;
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
         * zxqkms : null
         * zfdkye : 82251
         * dbze : 32665
         * mspjxxbs : 3
         * ssqkbs : --
         * nhdkye : 30
         * qcdkye : 151500
         * qsxxbs : 3
         * zxshjl : 未通过
         * xykzhs : 33
         * id : af52b4c24931e489c93f2342171cb553
         * jydkye : 4015
         * yzxshjl : null
         * mspjxxje : 332
         * lxyqzdqs_Android : ["7","7","7","7","7"]
         * zdkye : 19516383
         * xykjgs : 2
         * zxdkbs : 9
         * lxyqzdqs5 : 7
         * lxyqzdqs4 : 7
         * lxyqzdqs3 : 7
         * lxyqzdqs2 : 7
         * lxyqzdqs1 : 7
         * dbqk : [{"担保五级分类":"未分类","担保总额":"700","担保余额":"100","币种":"人民币","对外担保机构数":"1"},{"担保五级分类":"损失","担保总额":"9003","担保余额":"60003","币种":"人民币","对外担保机构数":"3"},{"担保五级分类":"关注","担保总额":"4004","担保余额":"40004","币种":"美元","对外担保机构数":"1"},{"担保五级分类":"损失","担保总额":"--","担保余额":"10001","币种":"美元","对外担保机构数":"1"},{"担保五级分类":"正常","担保总额":"9012","担保余额":"70012","币种":"日元","对外担保机构数":"3"},{"担保五级分类":"正常","担保总额":"5002","担保余额":"2","币种":"美元","对外担保机构数":"2"}]
         * fyhdkzbs : 10
         * qsxxje : 3000
         * zfdkbs : 40
         * js : 配偶
         * description : 系统审核结论为通过，人工修改为未通过
         * xzcfje : 15000
         * delFlag : null
         * xyklxyq_Android : ["7","7","7","7","7"]
         * dbqk_Android : [{"担保五级分类":"未分类","担保总额":"700","担保余额":"100","币种":"人民币","对外担保机构数":"1"},{"担保五级分类":"损失","担保总额":"9003","担保余额":"60003","币种":"人民币","对外担保机构数":"3"},{"担保五级分类":"关注","担保总额":"4004","担保余额":"40004","币种":"美元","对外担保机构数":"1"},{"担保五级分类":"损失","担保总额":"--","担保余额":"10001","币种":"美元","对外担保机构数":"1"},{"担保五级分类":"正常","担保总额":"9012","担保余额":"70012","币种":"日元","对外担保机构数":"3"},{"担保五级分类":"正常","担保总额":"5002","担保余额":"2","币种":"美元","对外担保机构数":"2"}]
         * nhdkbs : 3
         * dkyxze : 19544883
         * yqbs : 是
         * updateBy : admin
         * fyhdkye : 1303
         * xyklxyq5 : 7
         * xyklxyq4 : 7
         * dqyyed : 11606
         * xyklxyq3 : 7
         * xyklxyq2 : 7
         * xyklxyq1 : 7
         * sxjgs : 9
         * xzcfbs : 8
         * jydkbs : 4
         * qcdkbs : 6
         * zgsxed : 100002
         * zxdkye : 6303
         * dksxze : 640156
         * updateTime : 2020-20-13 21:20:01
         * sfwsxzxr : 是
         * cs : sss
         * createBy : admin
         * ssqkje : --
         * sfzh : 110101199003071807
         * zdkbs : 123
         * createTime : 2020-07-13 21:07:57
         * blyyms : null
         * sxid : 55efda762309212566c250b3fe30517b
         */

        private String zxqkms;
        private String zfdkye;
        private String dbze;
        private String mspjxxbs;
        private String ssqkbs;
        private String nhdkye;
        private String qcdkye;
        private String qsxxbs;
        private String zxshjl;
        private String xykzhs;
        private String id;
        private String jydkye;
        private String yzxshjl;
        private String mspjxxje;
        private String zdkye;
        private String xykjgs;
        private String zxdkbs;
        private String lxyqzdqs5;
        private String lxyqzdqs4;
        private String lxyqzdqs3;
        private String lxyqzdqs2;
        private String lxyqzdqs1;
        private String dbqk;
        private String fyhdkzbs;
        private String qsxxje;
        private String zfdkbs;
        private String js;
        private String description;
        private String xzcfje;
        private String delFlag;
        private String nhdkbs;
        private String dkyxze;
        private String yqbs;
        private String updateBy;
        private String fyhdkye;
        private String xyklxyq5;
        private String xyklxyq4;
        private String dqyyed;
        private String xyklxyq3;
        private String xyklxyq2;
        private String xyklxyq1;
        private String sxjgs;
        private String xzcfbs;
        private String jydkbs;
        private String qcdkbs;
        private String zgsxed;
        private String zxdkye;
        private String dksxze;
        private String updateTime;
        private String sfwsxzxr;
        private String cs;
        private String createBy;
        private String ssqkje;
        private String sfzh;
        private String zdkbs;
        private String createTime;
        private String blyyms;
        private String sxid;
        private List<String> lxyqzdqs_Android=new ArrayList<>();
        private List<String> xyklxyq_Android =new ArrayList<>();
        private List<DbqkAndroidBean> dbqk_Android;

        public String getZxqkms() {
            return zxqkms;
        }

        public void setZxqkms(String zxqkms) {
            this.zxqkms = zxqkms;
        }

        public String getZfdkye() {
            return zfdkye;
        }

        public void setZfdkye(String zfdkye) {
            this.zfdkye = zfdkye;
        }

        public String getDbze() {
            return dbze;
        }

        public void setDbze(String dbze) {
            this.dbze = dbze;
        }

        public String getMspjxxbs() {
            return mspjxxbs;
        }

        public void setMspjxxbs(String mspjxxbs) {
            this.mspjxxbs = mspjxxbs;
        }

        public String getSsqkbs() {
            return ssqkbs;
        }

        public void setSsqkbs(String ssqkbs) {
            this.ssqkbs = ssqkbs;
        }

        public String getNhdkye() {
            return nhdkye;
        }

        public void setNhdkye(String nhdkye) {
            this.nhdkye = nhdkye;
        }

        public String getQcdkye() {
            return qcdkye;
        }

        public void setQcdkye(String qcdkye) {
            this.qcdkye = qcdkye;
        }

        public String getQsxxbs() {
            return qsxxbs;
        }

        public void setQsxxbs(String qsxxbs) {
            this.qsxxbs = qsxxbs;
        }

        public String getZxshjl() {
            return zxshjl;
        }

        public void setZxshjl(String zxshjl) {
            this.zxshjl = zxshjl;
        }

        public String getXykzhs() {
            return xykzhs;
        }

        public void setXykzhs(String xykzhs) {
            this.xykzhs = xykzhs;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJydkye() {
            return jydkye;
        }

        public void setJydkye(String jydkye) {
            this.jydkye = jydkye;
        }

        public String getYzxshjl() {
            return yzxshjl;
        }

        public void setYzxshjl(String yzxshjl) {
            this.yzxshjl = yzxshjl;
        }

        public String getMspjxxje() {
            return mspjxxje;
        }

        public void setMspjxxje(String mspjxxje) {
            this.mspjxxje = mspjxxje;
        }

        public String getZdkye() {
            return zdkye;
        }

        public void setZdkye(String zdkye) {
            this.zdkye = zdkye;
        }

        public String getXykjgs() {
            return xykjgs;
        }

        public void setXykjgs(String xykjgs) {
            this.xykjgs = xykjgs;
        }

        public String getZxdkbs() {
            return zxdkbs;
        }

        public void setZxdkbs(String zxdkbs) {
            this.zxdkbs = zxdkbs;
        }

        public String getLxyqzdqs5() {
            return lxyqzdqs5;
        }

        public void setLxyqzdqs5(String lxyqzdqs5) {
            this.lxyqzdqs5 = lxyqzdqs5;
        }

        public String getLxyqzdqs4() {
            return lxyqzdqs4;
        }

        public void setLxyqzdqs4(String lxyqzdqs4) {
            this.lxyqzdqs4 = lxyqzdqs4;
        }

        public String getLxyqzdqs3() {
            return lxyqzdqs3;
        }

        public void setLxyqzdqs3(String lxyqzdqs3) {
            this.lxyqzdqs3 = lxyqzdqs3;
        }

        public String getLxyqzdqs2() {
            return lxyqzdqs2;
        }

        public void setLxyqzdqs2(String lxyqzdqs2) {
            this.lxyqzdqs2 = lxyqzdqs2;
        }

        public String getLxyqzdqs1() {
            return lxyqzdqs1;
        }

        public void setLxyqzdqs1(String lxyqzdqs1) {
            this.lxyqzdqs1 = lxyqzdqs1;
        }

        public String getDbqk() {
            return dbqk;
        }

        public void setDbqk(String dbqk) {
            this.dbqk = dbqk;
        }

        public String getFyhdkzbs() {
            return fyhdkzbs;
        }

        public void setFyhdkzbs(String fyhdkzbs) {
            this.fyhdkzbs = fyhdkzbs;
        }

        public String getQsxxje() {
            return qsxxje;
        }

        public void setQsxxje(String qsxxje) {
            this.qsxxje = qsxxje;
        }

        public String getZfdkbs() {
            return zfdkbs;
        }

        public void setZfdkbs(String zfdkbs) {
            this.zfdkbs = zfdkbs;
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

        public String getXzcfje() {
            return xzcfje;
        }

        public void setXzcfje(String xzcfje) {
            this.xzcfje = xzcfje;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getNhdkbs() {
            return nhdkbs;
        }

        public void setNhdkbs(String nhdkbs) {
            this.nhdkbs = nhdkbs;
        }

        public String getDkyxze() {
            return dkyxze;
        }

        public void setDkyxze(String dkyxze) {
            this.dkyxze = dkyxze;
        }

        public String getYqbs() {
            return yqbs;
        }

        public void setYqbs(String yqbs) {
            this.yqbs = yqbs;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getFyhdkye() {
            return fyhdkye;
        }

        public void setFyhdkye(String fyhdkye) {
            this.fyhdkye = fyhdkye;
        }

        public String getXyklxyq5() {
            return xyklxyq5;
        }

        public void setXyklxyq5(String xyklxyq5) {
            this.xyklxyq5 = xyklxyq5;
        }

        public String getXyklxyq4() {
            return xyklxyq4;
        }

        public void setXyklxyq4(String xyklxyq4) {
            this.xyklxyq4 = xyklxyq4;
        }

        public String getDqyyed() {
            return dqyyed;
        }

        public void setDqyyed(String dqyyed) {
            this.dqyyed = dqyyed;
        }

        public String getXyklxyq3() {
            return xyklxyq3;
        }

        public void setXyklxyq3(String xyklxyq3) {
            this.xyklxyq3 = xyklxyq3;
        }

        public String getXyklxyq2() {
            return xyklxyq2;
        }

        public void setXyklxyq2(String xyklxyq2) {
            this.xyklxyq2 = xyklxyq2;
        }

        public String getXyklxyq1() {
            return xyklxyq1;
        }

        public void setXyklxyq1(String xyklxyq1) {
            this.xyklxyq1 = xyklxyq1;
        }

        public String getSxjgs() {
            return sxjgs;
        }

        public void setSxjgs(String sxjgs) {
            this.sxjgs = sxjgs;
        }

        public String getXzcfbs() {
            return xzcfbs;
        }

        public void setXzcfbs(String xzcfbs) {
            this.xzcfbs = xzcfbs;
        }

        public String getJydkbs() {
            return jydkbs;
        }

        public void setJydkbs(String jydkbs) {
            this.jydkbs = jydkbs;
        }

        public String getQcdkbs() {
            return qcdkbs;
        }

        public void setQcdkbs(String qcdkbs) {
            this.qcdkbs = qcdkbs;
        }

        public String getZgsxed() {
            return zgsxed;
        }

        public void setZgsxed(String zgsxed) {
            this.zgsxed = zgsxed;
        }

        public String getZxdkye() {
            return zxdkye;
        }

        public void setZxdkye(String zxdkye) {
            this.zxdkye = zxdkye;
        }

        public String getDksxze() {
            return dksxze;
        }

        public void setDksxze(String dksxze) {
            this.dksxze = dksxze;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getSfwsxzxr() {
            return sfwsxzxr;
        }

        public void setSfwsxzxr(String sfwsxzxr) {
            this.sfwsxzxr = sfwsxzxr;
        }

        public String getCs() {
            return cs;
        }

        public void setCs(String cs) {
            this.cs = cs;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getSsqkje() {
            return ssqkje;
        }

        public void setSsqkje(String ssqkje) {
            this.ssqkje = ssqkje;
        }

        public String getSfzh() {
            return sfzh;
        }

        public void setSfzh(String sfzh) {
            this.sfzh = sfzh;
        }

        public String getZdkbs() {
            return zdkbs;
        }

        public void setZdkbs(String zdkbs) {
            this.zdkbs = zdkbs;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getBlyyms() {
            return blyyms;
        }

        public void setBlyyms(String blyyms) {
            this.blyyms = blyyms;
        }

        public String getSxid() {
            return sxid;
        }

        public void setSxid(String sxid) {
            this.sxid = sxid;
        }

        public List<String> getLxyqzdqs_Android() {
            return lxyqzdqs_Android;
        }

        public void setLxyqzdqs_Android(List<String> lxyqzdqs_Android) {
            this.lxyqzdqs_Android = lxyqzdqs_Android;
        }

        public List<String> getXyklxyq_Android() {
            return xyklxyq_Android;
        }

        public void setXyklxyq_Android(List<String> xyklxyq_Android) {
            this.xyklxyq_Android = xyklxyq_Android;
        }

        public List<DbqkAndroidBean> getDbqk_Android() {
            return dbqk_Android;
        }

        public void setDbqk_Android(List<DbqkAndroidBean> dbqk_Android) {
            this.dbqk_Android = dbqk_Android;
        }

        public static class DbqkAndroidBean {
            /**
             * 担保五级分类 : 未分类
             * 担保总额 : 700
             * 担保余额 : 100
             * 币种 : 人民币
             * 对外担保机构数 : 1
             */

            private String 担保五级分类;
            private String 担保总额;
            private String 担保余额;
            private String 币种;
            private String 对外担保机构数;

            public String get担保五级分类() {
                return 担保五级分类;
            }

            public void set担保五级分类(String 担保五级分类) {
                this.担保五级分类 = 担保五级分类;
            }

            public String get担保总额() {
                return 担保总额;
            }

            public void set担保总额(String 担保总额) {
                this.担保总额 = 担保总额;
            }

            public String get担保余额() {
                return 担保余额;
            }

            public void set担保余额(String 担保余额) {
                this.担保余额 = 担保余额;
            }

            public String get币种() {
                return 币种;
            }

            public void set币种(String 币种) {
                this.币种 = 币种;
            }

            public String get对外担保机构数() {
                return 对外担保机构数;
            }

            public void set对外担保机构数(String 对外担保机构数) {
                this.对外担保机构数 = 对外担保机构数;
            }
        }
    }
}
