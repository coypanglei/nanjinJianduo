package com.shaoyue.weizhegou.entity.cedit;

import java.util.List;

public class SxspListBean {


    /**
     * records : [{"khxm":"巩崇龙","dqhj":"分发岗","czsj":"2019-12-12 14:00:14","spzt":null,"lczt":"审批中","flag":"1","sxmx":null,"sxsfzh":"320304198804012414","clr":"宋猛","description":"好的","khh":null,"updateTime":"2019-00-12 14:00:15","zzFlag":"1","delFlag":null,"clsj":"2019-12-12 14:00:14","instid":"690044","createBy":"07200140","createTime":"2019-00-12 14:00:14","updateBy":"07200140","sxid":"831206b421e4824088791363a2d8e551","id":"152c70d6b93552363c09806144e74017","xyhj":"指派主查","approvalFlag":null,"taskid":"690083"}]
     * total : 1
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
        public String getJcjd() {
            return jcjd;
        }

        public void setJcjd(String jcjd) {
            this.jcjd = jcjd;
        }

        private String zjhm;

        public String getZjhm() {
            return zjhm;
        }

        public void setZjhm(String zjhm) {
            this.zjhm = zjhm;
        }

        /**
         * khxm : 巩崇龙
         * dqhj : 分发岗
         * czsj : 2019-12-12 14:00:14
         * spzt : null
         * lczt : 审批中
         * flag : 1
         * sxmx : null
         * sxsfzh : 320304198804012414
         * clr : 宋猛
         * description : 好的
         * khh : null
         * updateTime : 2019-00-12 14:00:15
         * zzFlag : 1
         * delFlag : null
         * clsj : 2019-12-12 14:00:14
         * instid : 690044
         * createBy : 07200140
         * createTime : 2019-00-12 14:00:14
         * updateBy : 07200140
         * sxid : 831206b421e4824088791363a2d8e551
         * id : 152c70d6b93552363c09806144e74017
         * xyhj : 指派主查
         * approvalFlag : null
         * taskid : 690083
         */



        private String jcjd;
        private String khxm;
        private String dqhj;
        private String czsj;
        private String spzt;
        private String lczt;
        private String flag;
        private String sxmx;
        private String sxsfzh;
        private String clr;
        private String description;
        private String khh;
        private String updateTime;
        private String zzFlag;
        private String delFlag;
        private String clsj;
        private String instid;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String sxid;
        private String id;
        private String xyhj;
        private String approvalFlag;
        private String taskid;

        private boolean isClick;


        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }

        public String getKhxm() {
            return khxm;
        }

        public void setKhxm(String khxm) {
            this.khxm = khxm;
        }

        public String getDqhj() {
            return dqhj;
        }

        public void setDqhj(String dqhj) {
            this.dqhj = dqhj;
        }

        public String getCzsj() {
            return czsj;
        }

        public void setCzsj(String czsj) {
            this.czsj = czsj;
        }

        public String getSpzt() {
            return spzt;
        }

        public void setSpzt(String spzt) {
            this.spzt = spzt;
        }

        public String getLczt() {
            return lczt;
        }

        public void setLczt(String lczt) {
            this.lczt = lczt;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getSxmx() {
            return sxmx;
        }

        public void setSxmx(String sxmx) {
            this.sxmx = sxmx;
        }

        public String getSxsfzh() {
            return sxsfzh;
        }

        public void setSxsfzh(String sxsfzh) {
            this.sxsfzh = sxsfzh;
        }

        public String getClr() {
            return clr;
        }

        public void setClr(String clr) {
            this.clr = clr;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getKhh() {
            return khh;
        }

        public void setKhh(String khh) {
            this.khh = khh;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
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

        public String getClsj() {
            return clsj;
        }

        public void setClsj(String clsj) {
            this.clsj = clsj;
        }

        public String getInstid() {
            return instid;
        }

        public void setInstid(String instid) {
            this.instid = instid;
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

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getSxid() {
            return sxid;
        }

        public void setSxid(String sxid) {
            this.sxid = sxid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getXyhj() {
            return xyhj;
        }

        public void setXyhj(String xyhj) {
            this.xyhj = xyhj;
        }

        public String getApprovalFlag() {
            return approvalFlag;
        }

        public void setApprovalFlag(String approvalFlag) {
            this.approvalFlag = approvalFlag;
        }

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }
    }
}
