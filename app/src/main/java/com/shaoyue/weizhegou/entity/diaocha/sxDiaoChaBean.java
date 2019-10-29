package com.shaoyue.weizhegou.entity.diaocha;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class sxDiaoChaBean extends BaseBean {


    /**
     * records : [{"sqrxm":"123","xy":null,"flag":"1","sxlx":"农户","description":"","zzFlag":"1","delFlag":null,"qxjsrq":"2021-07-24","dcrq":"2019-09-24","fpsj":"2019-09-24 10:58:24","instid":"552681","lckz":"审批中","dy":null,"updateBy":"admin","dczt":"调查中","id":"806e24181bcf293b827c0bf835b08c3a","approvalFlag":null,"sfygxd":"否","taskid":"552712","sxsfzh":"110101200703079119","sqrsfzh":"110101200703079119","updateTime":"2019-58-24 10:58:26","sqje":10,"sqjg":"东海农商行","sxed":null,"createBy":"admin","createTime":"2019-58-24 10:58:25","sxid":"c20cde0c6057e6f3eb3f2ea6ff6a3527","db":null,"zy":null},{"sqrxm":"张睛睛","xy":null,"flag":"1","sxlx":"经营","description":"","zzFlag":"1","delFlag":null,"qxjsrq":"2020-09-23","dcrq":"2019-09-23","fpsj":"2019-09-23 11:43:23","instid":"547501","lckz":"审批中","dy":null,"updateBy":"admin","dczt":"调查中","id":"ee240d16e4b4c1446349d4a5f9d7232e","approvalFlag":null,"sfygxd":"否","taskid":"547532","sxsfzh":"320323198406213033","sqrsfzh":"320323198406213033","updateTime":"2019-43-23 11:43:12","sqje":1,"sqjg":"东海农商行","sxed":null,"createBy":"admin","createTime":"2019-43-23 11:43:11","sxid":"c4390f92cdc68da5a5580493787f5fc4","db":null,"zy":null},{"sqrxm":"巩崇龙","xy":null,"flag":"1","sxlx":"经营","description":"","zzFlag":"1","delFlag":null,"qxjsrq":"2022-09-18","dcrq":"2019-09-18","fpsj":"2019-09-18 15:28:18","instid":"525083","lckz":"审批中","dy":null,"updateBy":"admin","dczt":"调查中","id":"3cdfc00b400043c4da4a43a22d8da45c","approvalFlag":null,"sfygxd":"是","taskid":"525114","sxsfzh":"320304198804012414","sqrsfzh":"320304198804012414","updateTime":"2019-28-18 15:28:21","sqje":10,"sqjg":"东海农商行","sxed":null,"createBy":"admin","createTime":"2019-28-18 15:28:20","sxid":"9e0c6b3e5f857c0ed957b89676886235","db":null,"zy":null},{"sqrxm":"小明","xy":null,"flag":"1","sxlx":"简化经营","description":"","zzFlag":"1","delFlag":null,"qxjsrq":"2020-01-18","dcrq":"2019-09-18","fpsj":"2019-09-18 15:26:18","instid":"525048","lckz":"审批中","dy":null,"updateBy":"admin","dczt":"调查中","id":"befaa050f977194b89eb55abecde81f7","approvalFlag":null,"sfygxd":"是","taskid":"525079","sxsfzh":"110101199806014638","sqrsfzh":"110101199806014638","updateTime":"2019-26-18 15:26:35","sqje":4,"sqjg":"东海农商行","sxed":null,"createBy":"admin","createTime":"2019-26-18 15:26:34","sxid":"cb4117a3270d18308a407161669e1ca1","db":null,"zy":null}]
     * total : 4
     * size : 12
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
         * sqrxm : 123
         * xy : null
         * flag : 1
         * sxlx : 农户
         * description :
         * zzFlag : 1
         * delFlag : null
         * qxjsrq : 2021-07-24
         * dcrq : 2019-09-24
         * fpsj : 2019-09-24 10:58:24
         * instid : 552681
         * lckz : 审批中
         * dy : null
         * updateBy : admin
         * dczt : 调查中
         * id : 806e24181bcf293b827c0bf835b08c3a
         * approvalFlag : null
         * sfygxd : 否
         * taskid : 552712
         * sxsfzh : 110101200703079119
         * sqrsfzh : 110101200703079119
         * updateTime : 2019-58-24 10:58:26
         * sqje : 10.0
         * sqjg : 东海农商行
         * sxed : null
         * createBy : admin
         * createTime : 2019-58-24 10:58:25
         * sxid : c20cde0c6057e6f3eb3f2ea6ff6a3527
         * db : null
         * zy : null
         */
        private boolean click;

        public boolean isClick() {
            return click;
        }

        public void setClick(boolean click) {
            this.click = click;
        }

        private String sqrxm;
        private String xy;
        private String flag;
        private String sxlx;
        private String description;
        private String zzFlag;
        private String delFlag;
        private String qxjsrq;
        private String dcrq;
        private String fpsj;
        private String instid;
        private String lckz;
        private String dy;
        private String updateBy;
        private String dczt;
        private String id;
        private String approvalFlag;
        private String sfygxd;
        private String taskid;
        private String sxsfzh;
        private String sqrsfzh;
        private String updateTime;
        private String sqje;
        private String sqjg;
        private String sxed;
        private String createBy;
        private String createTime;
        private String sxid;
        private String db;
        private String zy;
        private String slr;

        public String getSlr() {
            return slr;
        }

        public void setSlr(String slr) {
            this.slr = slr;
        }

        public String getSqrxm() {
            return sqrxm;
        }

        public void setSqrxm(String sqrxm) {
            this.sqrxm = sqrxm;
        }

        public String getXy() {
            return xy;
        }

        public void setXy(String xy) {
            this.xy = xy;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getSxlx() {
            return sxlx;
        }

        public void setSxlx(String sxlx) {
            this.sxlx = sxlx;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getZzFlag() {
            return zzFlag;
        }

        public void setZzFlag(String zzFlag) {
            this.zzFlag = zzFlag;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getQxjsrq() {
            return qxjsrq;
        }

        public void setQxjsrq(String qxjsrq) {
            this.qxjsrq = qxjsrq;
        }

        public String getDcrq() {
            return dcrq;
        }

        public void setDcrq(String dcrq) {
            this.dcrq = dcrq;
        }

        public String getFpsj() {
            return fpsj;
        }

        public void setFpsj(String fpsj) {
            this.fpsj = fpsj;
        }

        public String getInstid() {
            return instid;
        }

        public void setInstid(String instid) {
            this.instid = instid;
        }

        public String getLckz() {
            return lckz;
        }

        public void setLckz(String lckz) {
            this.lckz = lckz;
        }

        public String getDy() {
            return dy;
        }

        public void setDy(String dy) {
            this.dy = dy;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getDczt() {
            return dczt;
        }

        public void setDczt(String dczt) {
            this.dczt = dczt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getApprovalFlag() {
            return approvalFlag;
        }

        public void setApprovalFlag(String approvalFlag) {
            this.approvalFlag = approvalFlag;
        }

        public String getSfygxd() {
            return sfygxd;
        }

        public void setSfygxd(String sfygxd) {
            this.sfygxd = sfygxd;
        }

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }

        public String getSxsfzh() {
            return sxsfzh;
        }

        public void setSxsfzh(String sxsfzh) {
            this.sxsfzh = sxsfzh;
        }

        public String getSqrsfzh() {
            return sqrsfzh;
        }

        public void setSqrsfzh(String sqrsfzh) {
            this.sqrsfzh = sqrsfzh;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getSqje() {
            return sqje;
        }

        public void setSqje(String sqje) {
            this.sqje = sqje;
        }

        public String getSqjg() {
            return sqjg;
        }

        public void setSqjg(String sqjg) {
            this.sqjg = sqjg;
        }

        public String getSxed() {
            return sxed;
        }

        public void setSxed(String sxed) {
            this.sxed = sxed;
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

        public String getDb() {
            return db;
        }

        public void setDb(String db) {
            this.db = db;
        }

        public String getZy() {
            return zy;
        }

        public void setZy(String zy) {
            this.zy = zy;
        }
    }
}
