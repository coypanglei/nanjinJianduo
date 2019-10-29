package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/5/10 0010 10:12
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SafeInfoBean  extends BaseBean {
    //    {
//    "code":"200",
//    "data":{
//        "id":1,
//        "mobile":"18761686266",//手机号码
//        "wechat_nickname":"",//微信昵称
//        "realname":""//真实姓名
//        "is_pay_password" 支付密码
//    "is_password"
//    }
//}
    @SerializedName("id")
    private String id;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("wechat_nickname")
    private String wechatNickName;

    @SerializedName("is_pay_password")
    private String isPayPassword;

    @SerializedName("is_password")
    private String isPassword;

    @SerializedName("realname")
    private String realName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIsPassword() {
        return isPassword;
    }

    public void setIsPassword(String isPassword) {
        this.isPassword = isPassword;
    }

    public String getIsPayPassword() {
        return isPayPassword;
    }

    public void setIsPayPassword(String isPayPassword) {
        this.isPayPassword = isPayPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
