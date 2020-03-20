package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class ZiRanDanBaoListBean extends BaseBean {


    /**
     * records : [{"sxsfzh":null,"description":null,"updateTime":null,"delFlag":null,"dbrposfzh":null,"createBy":"admin","hyzk":"未婚","sfzh":"320303199303270819","xm":"阿萨","createTime":"2019-19-18 14:19:33","updateBy":null,"sxid":"d807acf39b08539e79c602a01962a42d","id":"50d236ed31ce81c90cf02f427dcf1eb3","dbrpo":null}]
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
         * sxsfzh : null
         * description : null
         * updateTime : null
         * delFlag : null
         * dbrposfzh : null
         * createBy : admin
         * hyzk : 未婚
         * sfzh : 320303199303270819
         * xm : 阿萨
         * createTime : 2019-19-18 14:19:33
         * updateBy : null
         * sxid : d807acf39b08539e79c602a01962a42d
         * id : 50d236ed31ce81c90cf02f427dcf1eb3
         * dbrpo : null
         */
        private boolean click;
        /**
         * jtzcfzqk : null
         * zxqkms : null
         * dbrwhsx : 22
         * powhsx : null
         * description : null
         * powhqtdbje : 123
         * delFlag : null
         * jtnzsr : 111
         * jtsjdz : null
         * dbrposfzh : 110101199003075664
         * jtnjsr : 11
         * updateBy : sxjl
         * jtzzc : 11
         * id : 39d0978c8801c147b826dfffa5da2a74
         * dbrpo : 311212
         * polxdh : 15380844201
         * lxdh : 15380844201
         * porqz : files/1f56b01fd67455414e77766b9f9839a8/qianming_1584616990769.png
         * dbrwhqtdbje : 11
         * dbrnsr : 11
         * bcdbje : 1111
         * sxsfzh : null
         * updateTime : 2020-03-19 19:23:32
         * zyywyzw : 1111
         * pogzdw : null
         * ponsr : 2
         * dbrztdbnlfx : null
         * gx : 子女
         * createBy : sxjl
         * dbrqz : files/1f56b01fd67455414e77766b9f9839a8/qianming_1584616986866.png
         * jtscjyqk : null
         * hyzk : 已婚
         * sfzh : 320102199003071810
         * xm : 321
         * createTime : 2020-03-19 19:15:08
         * sfgtdb : 是
         * sxid : 1f56b01fd67455414e77766b9f9839a8
         * pozyywyzw : 123
         */

        private String jtzcfzqk;
        private String zxqkms;
        private String dbrwhsx;
        private String powhsx;
        private String description;
        private String powhqtdbje;
        private String delFlag;
        private String jtnzsr;
        private String jtsjdz;
        private String dbrposfzh;
        private String jtnjsr;
        private String updateBy;
        private String jtzzc;
        private String id;
        private String dbrpo;
        private String polxdh;
        private String lxdh;
        private String porqz;
        private String dbrwhqtdbje;
        private String dbrnsr;
        private String bcdbje;
        private String sxsfzh;
        private String updateTime;
        private String zyywyzw;
        private String pogzdw;
        private String ponsr;
        private String dbrztdbnlfx;
        private String gx;
        private String createBy;
        private String dbrqz;
        private String jtscjyqk;
        private String hyzk;
        private String sfzh;
        private String xm;
        private String createTime;
        private String sfgtdb;
        private String sxid;
        private String pozyywyzw;

        public boolean isClick() {
            return click;
        }

        public void setClick(boolean click) {
            this.click = click;
        }


        public String getJtzcfzqk() {
            return jtzcfzqk;
        }

        public void setJtzcfzqk(String jtzcfzqk) {
            this.jtzcfzqk = jtzcfzqk;
        }

        public String getZxqkms() {
            return zxqkms;
        }

        public void setZxqkms(String zxqkms) {
            this.zxqkms = zxqkms;
        }

        public String getDbrwhsx() {
            return dbrwhsx;
        }

        public void setDbrwhsx(String dbrwhsx) {
            this.dbrwhsx = dbrwhsx;
        }

        public String getPowhsx() {
            return powhsx;
        }

        public void setPowhsx(String powhsx) {
            this.powhsx = powhsx;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPowhqtdbje() {
            return powhqtdbje;
        }

        public void setPowhqtdbje(String powhqtdbje) {
            this.powhqtdbje = powhqtdbje;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getJtnzsr() {
            return jtnzsr;
        }

        public void setJtnzsr(String jtnzsr) {
            this.jtnzsr = jtnzsr;
        }

        public String getJtsjdz() {
            return jtsjdz;
        }

        public void setJtsjdz(String jtsjdz) {
            this.jtsjdz = jtsjdz;
        }

        public String getDbrposfzh() {
            return dbrposfzh;
        }

        public void setDbrposfzh(String dbrposfzh) {
            this.dbrposfzh = dbrposfzh;
        }

        public String getJtnjsr() {
            return jtnjsr;
        }

        public void setJtnjsr(String jtnjsr) {
            this.jtnjsr = jtnjsr;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getJtzzc() {
            return jtzzc;
        }

        public void setJtzzc(String jtzzc) {
            this.jtzzc = jtzzc;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDbrpo() {
            return dbrpo;
        }

        public void setDbrpo(String dbrpo) {
            this.dbrpo = dbrpo;
        }

        public String getPolxdh() {
            return polxdh;
        }

        public void setPolxdh(String polxdh) {
            this.polxdh = polxdh;
        }

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public String getPorqz() {
            return porqz;
        }

        public void setPorqz(String porqz) {
            this.porqz = porqz;
        }

        public String getDbrwhqtdbje() {
            return dbrwhqtdbje;
        }

        public void setDbrwhqtdbje(String dbrwhqtdbje) {
            this.dbrwhqtdbje = dbrwhqtdbje;
        }

        public String getDbrnsr() {
            return dbrnsr;
        }

        public void setDbrnsr(String dbrnsr) {
            this.dbrnsr = dbrnsr;
        }

        public String getBcdbje() {
            return bcdbje;
        }

        public void setBcdbje(String bcdbje) {
            this.bcdbje = bcdbje;
        }

        public String getSxsfzh() {
            return sxsfzh;
        }

        public void setSxsfzh(String sxsfzh) {
            this.sxsfzh = sxsfzh;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getZyywyzw() {
            return zyywyzw;
        }

        public void setZyywyzw(String zyywyzw) {
            this.zyywyzw = zyywyzw;
        }

        public String getPogzdw() {
            return pogzdw;
        }

        public void setPogzdw(String pogzdw) {
            this.pogzdw = pogzdw;
        }

        public String getPonsr() {
            return ponsr;
        }

        public void setPonsr(String ponsr) {
            this.ponsr = ponsr;
        }

        public String getDbrztdbnlfx() {
            return dbrztdbnlfx;
        }

        public void setDbrztdbnlfx(String dbrztdbnlfx) {
            this.dbrztdbnlfx = dbrztdbnlfx;
        }

        public String getGx() {
            return gx;
        }

        public void setGx(String gx) {
            this.gx = gx;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getDbrqz() {
            return dbrqz;
        }

        public void setDbrqz(String dbrqz) {
            this.dbrqz = dbrqz;
        }

        public String getJtscjyqk() {
            return jtscjyqk;
        }

        public void setJtscjyqk(String jtscjyqk) {
            this.jtscjyqk = jtscjyqk;
        }

        public String getHyzk() {
            return hyzk;
        }

        public void setHyzk(String hyzk) {
            this.hyzk = hyzk;
        }

        public String getSfzh() {
            return sfzh;
        }

        public void setSfzh(String sfzh) {
            this.sfzh = sfzh;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getSfgtdb() {
            return sfgtdb;
        }

        public void setSfgtdb(String sfgtdb) {
            this.sfgtdb = sfgtdb;
        }

        public String getSxid() {
            return sxid;
        }

        public void setSxid(String sxid) {
            this.sxid = sxid;
        }

        public String getPozyywyzw() {
            return pozyywyzw;
        }

        public void setPozyywyzw(String pozyywyzw) {
            this.pozyywyzw = pozyywyzw;
        }
    }
}
