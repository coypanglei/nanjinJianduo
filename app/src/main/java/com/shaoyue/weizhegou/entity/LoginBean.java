package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

import com.shaoyue.weizhegou.base.BaseBean;
import com.shaoyue.weizhegou.entity.user.loginUserBean;
import com.shaoyue.weizhegou.entity.user.loginUserDepartsBean;
import com.shaoyue.weizhegou.entity.user.loginUserPosition;

import java.util.List;


/**
 * 登录信息
 */
public class LoginBean extends BaseBean {
    //id复制
    //[number]	是
    //nickname

    @SerializedName("token")
    private String sessionId;


    @SerializedName("userInfo")
    private loginUserBean userInfo;

    @SerializedName("position")
    private loginUserPosition position;

    @SerializedName("departs")
    private List<loginUserDepartsBean> mDeparts;


    public loginUserPosition getPosition() {
        return position;
    }

    public void setPosition(loginUserPosition position) {
        this.position = position;
    }

    public List<loginUserDepartsBean> getmDeparts() {
        return mDeparts;
    }

    public void setmDeparts(List<loginUserDepartsBean> mDeparts) {
        this.mDeparts = mDeparts;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public loginUserBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(loginUserBean userInfo) {
        this.userInfo = userInfo;
    }
}
