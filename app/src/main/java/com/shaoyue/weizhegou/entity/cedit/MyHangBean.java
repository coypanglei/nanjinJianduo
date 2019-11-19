package com.shaoyue.weizhegou.entity.cedit;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class MyHangBean extends BaseBean {


    /**
     * records : [{"sycsxqsrq":"11","df":"未开通","nqf":"未开通","ysxje":11.1,"rqf":"未开通","cksdye":11,"xtshjl":"未通过","ysf":"开通","sjyh":"11","yl":"11","blcs":11,"jynckrj":11,"dx":"未开通","sqwjfl":"11","id":"cf08c44eef12e009c2080416ba8e2c90","yjsf":"11","qkqxcs":11,"sxcs":11,"sxsfzh":"320304198804012414","zfb":"开通","xdfxyj":"11","dqcknrj":11,"zhybyxrq":"11","whpj":11,"ywhhzgx":"11","whsjshjl":"11","zhybdkjqsj":"11","js":"本人","description":"系统审核结论为通过，人工修改为未通过","ddrjsr":11,"delFlag":"","bwbldk":11,"yxye":11.1,"sf":"未开通","hqcknrj":11,"pos":"11","updateBy":"admin","jdzf":"开通","wsyh":"11","wx":"开通","bndbbl":11,"ckrjye180":11,"bnbldk":11,"updateTime":"2019-11-30 10:11:34","yjdf":"11","cft":"开通","whblyycs":"不行呀","createBy":"admin","sfzh":"320304198804012414","createTime":"2019-11-30 10:11:21","sxid":"120","lc":11,"sqjjnj":"11","bdqb":"未开通","dwdb":11},{"sycsxqsrq":"11","df":"开通","nqf":"开通","ysxje":11.1,"rqf":"开通","cksdye":11,"xtshjl":"不通过","ysf":"未开通","sjyh":"11","yl":"11","blcs":11,"jynckrj":11,"dx":"开通","sqwjfl":"11","id":null,"yjsf":"11","qkqxcs":11,"sxcs":11,"sxsfzh":"320304198804012424","zfb":"未开通","xdfxyj":"11","dqcknrj":11,"zhybyxrq":"11","whpj":11,"ywhhzgx":"11","whsjshjl":"11","zhybdkjqsj":"11","js":"配偶","description":"","ddrjsr":11,"delFlag":"","bwbldk":11,"yxye":11.1,"sf":"开通","hqcknrj":11,"pos":"11","updateBy":"admin","jdzf":"未开通","wsyh":"11","wx":"未开通","bndbbl":11,"ckrjye180":11,"bnbldk":11,"updateTime":"2019-11-30 10:11:34","yjdf":"11","cft":"未开通","whblyycs":"11","createBy":"admin","sfzh":"320304198804012424","createTime":"2019-11-30 10:11:21","sxid":"1201","lc":11,"sqjjnj":"11","bdqb":"开通","dwdb":11}]
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
         * sycsxqsrq : 11
         * df : 未开通
         * nqf : 未开通
         * ysxje : 11.1
         * rqf : 未开通
         * cksdye : 11.0
         * xtshjl : 未通过
         * ysf : 开通
         * sjyh : 11
         * yl : 11
         * blcs : 11
         * jynckrj : 11.0
         * dx : 未开通
         * sqwjfl : 11
         * id : cf08c44eef12e009c2080416ba8e2c90
         * yjsf : 11
         * qkqxcs : 11
         * sxcs : 11
         * sxsfzh : 320304198804012414
         * zfb : 开通
         * xdfxyj : 11
         * dqcknrj : 11.0
         * zhybyxrq : 11
         * whpj : 11
         * ywhhzgx : 11
         * whsjshjl : 11
         * zhybdkjqsj : 11
         * js : 本人
         * description : 系统审核结论为通过，人工修改为未通过
         * ddrjsr : 11.0
         * delFlag :
         * bwbldk : 11.0
         * yxye : 11.1
         * sf : 未开通
         * hqcknrj : 11.0
         * pos : 11
         * updateBy : admin
         * jdzf : 开通
         * wsyh : 11
         * wx : 开通
         * bndbbl : 11.0
         * ckrjye180 : 11.0
         * bnbldk : 11.0
         * updateTime : 2019-11-30 10:11:34
         * yjdf : 11
         * cft : 开通
         * whblyycs : 不行呀
         * createBy : admin
         * sfzh : 320304198804012414
         * createTime : 2019-11-30 10:11:21
         * sxid : 120
         * lc : 11.0
         * sqjjnj : 11
         * bdqb : 未开通
         * dwdb : 11.0
         */


        @SerializedName("sxrq")
        private String sxrq;
        private String sycsxqsrq;
        @SerializedName("dfkh")
        private String df;
        private String nqf;
        @SerializedName("sxje")
        private double ysxje;
        private String mt;
        @SerializedName("mqkh")
        private String rqf;
        private double cksdye;
        private String xtshjl;
        private String ysf;

        public String getMt() {
            return mt;
        }

        public void setMt(String mt) {
            this.mt = mt;
        }

        private String sjyh;
        @SerializedName("wyzx")
        private String yl;
        private int blcs;
        private double jynckrj;
        private String dx;
        @SerializedName("wjfl")
        private String sqwjfl;
        private String id;
        private String yjsf;
        private int qkqxcs;
        private int sxcs;
        private String sxsfzh;
        private String zfb;
        private String xdfxyj;
        private double dqcknrj;
        private String zhybyxrq;
        private int whpj;
        private String ywhhzgx;
        private String whsjshjl;
        private String zhybdkjqsj;
        private String js;
        private String description;
        private double ddrjsr;
        private String delFlag;
        private double bwbldk;
        private double yxye;
        @SerializedName("sfkh")
        private String sf;
        private double hqcknrj;
        private String pos;
        private String updateBy;
        @SerializedName("jd")
        private String jdzf;
        private String wsyh;
        private String wx;
        private double bndbbl;
        private double ckrjye180;
        private double bnbldk;
        private String updateTime;
        private String yjdf;
        private String cft;
        private String whblyycs;
        private String createBy;
        private String sfzh;
        private String createTime;
        private String sxid;
        private double lc;
        private String sqjjnj;
        @SerializedName("bfb")
        private String bdqb;
        private double dwdb;

        private String dbbldk;

        public String getSxrq() {
            return sxrq;
        }

        public void setSxrq(String sxrq) {
            this.sxrq = sxrq;
        }

        public String getDbbldk() {
            return dbbldk;
        }

        public void setDbbldk(String dbbldk) {
            this.dbbldk = dbbldk;
        }

        public String getSycsxqsrq() {
            return sycsxqsrq;
        }

        public void setSycsxqsrq(String sycsxqsrq) {
            this.sycsxqsrq = sycsxqsrq;
        }

        public String getDf() {
            return df;
        }

        public void setDf(String df) {
            this.df = df;
        }

        public String getNqf() {
            return nqf;
        }

        public void setNqf(String nqf) {
            this.nqf = nqf;
        }

        public double getYsxje() {
            return ysxje;
        }

        public void setYsxje(double ysxje) {
            this.ysxje = ysxje;
        }

        public String getRqf() {
            return rqf;
        }

        public void setRqf(String rqf) {
            this.rqf = rqf;
        }

        public double getCksdye() {
            return cksdye;
        }

        public void setCksdye(double cksdye) {
            this.cksdye = cksdye;
        }

        public String getXtshjl() {
            return xtshjl;
        }

        public void setXtshjl(String xtshjl) {
            this.xtshjl = xtshjl;
        }

        public String getYsf() {
            return ysf;
        }

        public void setYsf(String ysf) {
            this.ysf = ysf;
        }

        public String getSjyh() {
            return sjyh;
        }

        public void setSjyh(String sjyh) {
            this.sjyh = sjyh;
        }

        public String getYl() {
            return yl;
        }

        public void setYl(String yl) {
            this.yl = yl;
        }

        public int getBlcs() {
            return blcs;
        }

        public void setBlcs(int blcs) {
            this.blcs = blcs;
        }

        public double getJynckrj() {
            return jynckrj;
        }

        public void setJynckrj(double jynckrj) {
            this.jynckrj = jynckrj;
        }

        public String getDx() {
            return dx;
        }

        public void setDx(String dx) {
            this.dx = dx;
        }

        public String getSqwjfl() {
            return sqwjfl;
        }

        public void setSqwjfl(String sqwjfl) {
            this.sqwjfl = sqwjfl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYjsf() {
            return yjsf;
        }

        public void setYjsf(String yjsf) {
            this.yjsf = yjsf;
        }

        public int getQkqxcs() {
            return qkqxcs;
        }

        public void setQkqxcs(int qkqxcs) {
            this.qkqxcs = qkqxcs;
        }

        public int getSxcs() {
            return sxcs;
        }

        public void setSxcs(int sxcs) {
            this.sxcs = sxcs;
        }

        public String getSxsfzh() {
            return sxsfzh;
        }

        public void setSxsfzh(String sxsfzh) {
            this.sxsfzh = sxsfzh;
        }

        public String getZfb() {
            return zfb;
        }

        public void setZfb(String zfb) {
            this.zfb = zfb;
        }

        public String getXdfxyj() {
            return xdfxyj;
        }

        public void setXdfxyj(String xdfxyj) {
            this.xdfxyj = xdfxyj;
        }

        public double getDqcknrj() {
            return dqcknrj;
        }

        public void setDqcknrj(double dqcknrj) {
            this.dqcknrj = dqcknrj;
        }

        public String getZhybyxrq() {
            return zhybyxrq;
        }

        public void setZhybyxrq(String zhybyxrq) {
            this.zhybyxrq = zhybyxrq;
        }

        public int getWhpj() {
            return whpj;
        }

        public void setWhpj(int whpj) {
            this.whpj = whpj;
        }

        public String getYwhhzgx() {
            return ywhhzgx;
        }

        public void setYwhhzgx(String ywhhzgx) {
            this.ywhhzgx = ywhhzgx;
        }

        public String getWhsjshjl() {
            return whsjshjl;
        }

        public void setWhsjshjl(String whsjshjl) {
            this.whsjshjl = whsjshjl;
        }

        public String getZhybdkjqsj() {
            return zhybdkjqsj;
        }

        public void setZhybdkjqsj(String zhybdkjqsj) {
            this.zhybdkjqsj = zhybdkjqsj;
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

        public double getDdrjsr() {
            return ddrjsr;
        }

        public void setDdrjsr(double ddrjsr) {
            this.ddrjsr = ddrjsr;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public double getBwbldk() {
            return bwbldk;
        }

        public void setBwbldk(double bwbldk) {
            this.bwbldk = bwbldk;
        }

        public double getYxye() {
            return yxye;
        }

        public void setYxye(double yxye) {
            this.yxye = yxye;
        }

        public String getSf() {
            return sf;
        }

        public void setSf(String sf) {
            this.sf = sf;
        }

        public double getHqcknrj() {
            return hqcknrj;
        }

        public void setHqcknrj(double hqcknrj) {
            this.hqcknrj = hqcknrj;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getJdzf() {
            return jdzf;
        }

        public void setJdzf(String jdzf) {
            this.jdzf = jdzf;
        }

        public String getWsyh() {
            return wsyh;
        }

        public void setWsyh(String wsyh) {
            this.wsyh = wsyh;
        }

        public String getWx() {
            return wx;
        }

        public void setWx(String wx) {
            this.wx = wx;
        }

        public double getBndbbl() {
            return bndbbl;
        }

        public void setBndbbl(double bndbbl) {
            this.bndbbl = bndbbl;
        }

        public double getCkrjye180() {
            return ckrjye180;
        }

        public void setCkrjye180(double ckrjye180) {
            this.ckrjye180 = ckrjye180;
        }

        public double getBnbldk() {
            return bnbldk;
        }

        public void setBnbldk(double bnbldk) {
            this.bnbldk = bnbldk;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getYjdf() {
            return yjdf;
        }

        public void setYjdf(String yjdf) {
            this.yjdf = yjdf;
        }

        public String getCft() {
            return cft;
        }

        public void setCft(String cft) {
            this.cft = cft;
        }

        public String getWhblyycs() {
            return whblyycs;
        }

        public void setWhblyycs(String whblyycs) {
            this.whblyycs = whblyycs;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getSfzh() {
            return sfzh;
        }

        public void setSfzh(String sfzh) {
            this.sfzh = sfzh;
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

        public double getLc() {
            return lc;
        }

        public void setLc(double lc) {
            this.lc = lc;
        }

        public String getSqjjnj() {
            return sqjjnj;
        }

        public void setSqjjnj(String sqjjnj) {
            this.sqjjnj = sqjjnj;
        }

        public String getBdqb() {
            return bdqb;
        }

        public void setBdqb(String bdqb) {
            this.bdqb = bdqb;
        }

        public double getDwdb() {
            return dwdb;
        }

        public void setDwdb(double dwdb) {
            this.dwdb = dwdb;
        }
    }
}
