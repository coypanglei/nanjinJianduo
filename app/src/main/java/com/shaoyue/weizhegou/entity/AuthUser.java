package com.shaoyue.weizhegou.entity;

import com.shaoyue.weizhegou.base.BaseBean;

import java.io.Serializable;

/**
 * Created by librabin on 16/10/20.
 */

public class AuthUser extends BaseBean {

    private String authType;

    private String uid;

    private String unionid;

    private String nickName;        // 用户昵称

    private String avatar;      // 头像地址

    private String sex;     // 性别


    public AuthUser() {
    }


    public AuthUser(String authType, String uid, String nickName, String avatar, String sex) {
        this.authType = authType;
        this.uid = uid;
        this.nickName = nickName;
        this.avatar = avatar;
        this.sex = sex;

    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUnionid() {
        return unionid == null ? "" : unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "authType='" + authType + '\'' +
                ", uid='" + uid + '\'' +
                ", unionid='" + unionid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
