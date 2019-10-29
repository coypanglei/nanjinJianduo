package com.shaoyue.weizhegou.entity.cedit;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class ProgressBean extends BaseBean {


    /**
     * taskname : 填写授信申请
     * approvaltime : 2019-08-19 17:27:10
     * approvaluser : 测试用户1
     * approvaluserid : 697e4c1c37274164bb3ff8bd7b978f2d
     * approvaltype : 申请
     * comment : zheshipinglun
     * starttime : 2019-08-19 17:27:10
     * list : [{"url":"213123"}]
     */
    private boolean isOne;
    private boolean isLast;

   @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOne() {
        return isOne;
    }

    public void setOne(boolean one) {
        isOne = one;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    private String taskname;
    private String approvaltime;
    private String approvaluser;
    private String approvaluserid;
    private String approvaltype;
    private String comment;
    private String starttime;
    private List<ListBean> list;

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getApprovaltime() {
        return approvaltime;
    }

    public void setApprovaltime(String approvaltime) {
        this.approvaltime = approvaltime;
    }

    public String getApprovaluser() {
        return approvaluser;
    }

    public void setApprovaluser(String approvaluser) {
        this.approvaluser = approvaluser;
    }

    public String getApprovaluserid() {
        return approvaluserid;
    }

    public void setApprovaluserid(String approvaluserid) {
        this.approvaluserid = approvaluserid;
    }

    public String getApprovaltype() {
        return approvaltype;
    }

    public void setApprovaltype(String approvaltype) {
        this.approvaltype = approvaltype;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * url : 213123
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
