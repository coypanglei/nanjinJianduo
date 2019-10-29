package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/5/24 0024 13:58
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SuperiorInfoBean extends BaseBean {


//    "id":10,
//            "headerpic":"",
//            "realname":"",
//            "nickname":"17714170282",
//            "mobile":"17714170282",
//            "wechat_nickname":""

    @SerializedName("id")
    private String id;

    @SerializedName("headerpic")
    private String headerpic;

    @SerializedName("realname")
    private String realname;


    @SerializedName("nickname")
    private String nickName;


    @SerializedName("mobile")
    private String mobile;

    @SerializedName("wechat_nickname")
    private String wechatNickName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeaderpic() {
        return headerpic;
    }

    public void setHeaderpic(String headerpic) {
        this.headerpic = headerpic;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWechatNickName() {
        return wechatNickName;
    }

    public void setWechatNickName(String wechatNickName) {
        this.wechatNickName = wechatNickName;
    }
}
