package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class TiJiaoBean extends BaseBean {


    /**
     * canReturnTo : [{"activityName":"填写授信申请","activityId":"usertask1","assignee":"管理"},{"activityName":"分发岗","activityId":"usertask11","assignee":"周仟"},{"activityName":"指派主查","activityId":"usertask13","assignee":"陈强"},{"activityName":"协查","activityId":"usertask18","assignee":"周仟"},{"activityName":"独立审批人","activityId":"usertask19","assignee":"朱大伟"}]
     * variableName : xxx
     * code : 200
     * currentTaskName : 总经理审批
     * success : true
     * nextNodeName : 副行长
     * selectApprovalUser : false
     * users : [{"id":"52386d62b28d47e784416fca54f7e4a0","realname":"蔡连军","positionName":"副行长","positionCode":"00104","departName":"领导班子","departCOde":"320722901"},{"id":"d6d8a00223a44657bb45186a025f1aef","realname":"冯良善","positionName":"行领导","positionCode":"00103","departName":"领导班子","departCOde":"320722901"},{"id":"ea7260f5467a477987aca6ebcc0b8046","realname":"仲伟强","positionName":"副行长","positionCode":"00104","departName":"领导班子","departCOde":"320722901"},{"id":"fd39cd8fb8c84bccbe573fe25bdd8e5f","realname":"徐磊","positionName":"副行长","positionCode":"00104","departName":"领导班子","departCOde":"320722901"},{"id":"e8cef93d1a32432387c3e08f7cfae460","realname":"赵庆团","positionName":"行领导","positionCode":"00103","departName":"领导班子","departCOde":"320722901"},{"id":"343836e24f73460994f8d13106245e2e","realname":"邵军","positionName":"副行长","positionCode":"00104","departName":"领导班子","departCOde":"320722901"},{"id":"d10c105d1a554a9fb3c6c4598df16d1b","realname":"邹兵","positionName":"行领导","positionCode":"00103","departName":"领导班子","departCOde":"320722901"}]
     */

    private String variableName;
    private int code;
    private String currentTaskName;
    private boolean success;
    private String nextNodeName;
    private boolean selectApprovalUser;

    private String vsfc;


    public String getVsfc() {
        return vsfc;
    }

    public void setVsfc(String vsfc) {
        this.vsfc = vsfc;
    }

    private List<CanReturnToBean> canReturnTo;
    private List<UsersBean> users;

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCurrentTaskName() {
        return currentTaskName;
    }

    public void setCurrentTaskName(String currentTaskName) {
        this.currentTaskName = currentTaskName;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getNextNodeName() {
        return nextNodeName;
    }

    public void setNextNodeName(String nextNodeName) {
        this.nextNodeName = nextNodeName;
    }

    public boolean isSelectApprovalUser() {
        return selectApprovalUser;
    }

    public void setSelectApprovalUser(boolean selectApprovalUser) {
        this.selectApprovalUser = selectApprovalUser;
    }

    public List<CanReturnToBean> getCanReturnTo() {
        return canReturnTo;
    }

    public void setCanReturnTo(List<CanReturnToBean> canReturnTo) {
        this.canReturnTo = canReturnTo;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class CanReturnToBean {
        /**
         * activityName : 填写授信申请
         * activityId : usertask1
         * assignee : 管理
         */

        private String activityName;
        private String activityId;
        private String assignee;

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }
    }

    public static class UsersBean {
        /**
         * id : 52386d62b28d47e784416fca54f7e4a0
         * realname : 蔡连军
         * positionName : 副行长
         * positionCode : 00104
         * departName : 领导班子
         * departCOde : 320722901
         */

        private String id;
        private String realname;
        private String positionName;
        private String positionCode;
        private String departName;
        private String departCOde;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getPositionCode() {
            return positionCode;
        }

        public void setPositionCode(String positionCode) {
            this.positionCode = positionCode;
        }

        public String getDepartName() {
            return departName;
        }

        public void setDepartName(String departName) {
            this.departName = departName;
        }

        public String getDepartCOde() {
            return departCOde;
        }

        public void setDepartCOde(String departCOde) {
            this.departCOde = departCOde;
        }
    }
}
