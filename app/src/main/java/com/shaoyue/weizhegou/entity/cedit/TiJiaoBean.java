package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class TiJiaoBean extends BaseBean {


    /**
     * variableName : xxx
     * currentTaskName : null
     * nextNodeName : 主查
     * selectApprovalUser : true
     * users : [{"id":"e9ca23d68d884d4ebb19d07889727dae","realname":"管理","positionName":"管理员","positionCode":"00","departName":"东海农商行","departCOde":"320722000"}]
     */

    private String variableName;
    private Object currentTaskName;
    private String nextNodeName;
    private boolean selectApprovalUser;
    private List<UsersBean> users;

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Object getCurrentTaskName() {
        return currentTaskName;
    }

    public void setCurrentTaskName(Object currentTaskName) {
        this.currentTaskName = currentTaskName;
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

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean {
        /**
         * id : e9ca23d68d884d4ebb19d07889727dae
         * realname : 管理
         * positionName : 管理员
         * positionCode : 00
         * departName : 东海农商行
         * departCOde : 320722000
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
