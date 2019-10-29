package com.shaoyue.weizhegou.entity.cedit;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class VideoMaterialBean extends BaseBean {
    @SerializedName("p-category")
    private String category;

    public String getCategory() {
        return category;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * title : 营业执照(经营相关材料)
     * list : [{"id":"12dde28be6d1c84d7afc29e2c1f1e766","sxid":"770fc215263b0d7ef0e310b4de57400c","sxsfzh":"","zllx":"营业执照(经营相关材料)","zlsx":null,"zlmc":null,"zldz":"files/20190909/2345Explorer_1567998643593.exe","delFlag":null,"createBy":"admin","createTime":"2019-09-09 11:10:45","updateBy":null,"updateTime":null,"description":null},{"id":"effdc798b53fd428d419036154d04425","sxid":"770fc215263b0d7ef0e310b4de57400c","sxsfzh":"","zllx":"营业执照(经营相关材料)","zlsx":null,"zlmc":null,"zldz":"files/20190909/360se_1567998674830.exe","delFlag":null,"createBy":"admin","createTime":"2019-09-09 11:11:16","updateBy":null,"updateTime":null,"description":null},{"id":"02356cbdba46162193ce1f0d5940bb8b","sxid":"770fc215263b0d7ef0e310b4de57400c","sxsfzh":"","zllx":"营业执照(经营相关材料)","zlsx":null,"zlmc":null,"zldz":"files/20190909/360se_1567998735704.exe","delFlag":null,"createBy":"admin","createTime":"2019-09-09 11:12:16","updateBy":null,"updateTime":null,"description":null}]
     */

    private String title;
    private List<ListBean> list;

    private int select;

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean extends BaseBean{
        /**
         * id : 12dde28be6d1c84d7afc29e2c1f1e766
         * sxid : 770fc215263b0d7ef0e310b4de57400c
         * sxsfzh :
         * zllx : 营业执照(经营相关材料)
         * zlsx : null
         * zlmc : null
         * zldz : files/20190909/2345Explorer_1567998643593.exe
         * delFlag : null
         * createBy : admin
         * createTime : 2019-09-09 11:10:45
         * updateBy : null
         * updateTime : null
         * description : null
         */


        private String id;
        private String sxid;
        private String sxsfzh;
        private String zllx;
        private Object zlsx;
        private Object zlmc;
        private String zldz;
        private Object delFlag;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSxid() {
            return sxid;
        }

        public void setSxid(String sxid) {
            this.sxid = sxid;
        }

        public String getSxsfzh() {
            return sxsfzh;
        }

        public void setSxsfzh(String sxsfzh) {
            this.sxsfzh = sxsfzh;
        }

        public String getZllx() {
            return zllx;
        }

        public void setZllx(String zllx) {
            this.zllx = zllx;
        }

        public Object getZlsx() {
            return zlsx;
        }

        public void setZlsx(Object zlsx) {
            this.zlsx = zlsx;
        }

        public Object getZlmc() {
            return zlmc;
        }

        public void setZlmc(Object zlmc) {
            this.zlmc = zlmc;
        }

        public String getZldz() {
            return zldz;
        }

        public void setZldz(String zldz) {
            this.zldz = zldz;
        }

        public Object getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Object delFlag) {
            this.delFlag = delFlag;
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

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }
    }
}
