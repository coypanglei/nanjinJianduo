package com.shaoyue.weizhegou.manager;


import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AccountApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.common.CommConfig;
import com.shaoyue.weizhegou.entity.AuthUser;
import com.shaoyue.weizhegou.entity.LoginBean;
import com.shaoyue.weizhegou.entity.user.UserInfoBean;
import com.shaoyue.weizhegou.interfac.CommCallBack;

import org.greenrobot.eventbus.EventBus;


public class

UserMgr {


    // 登录类型: 0,没有登录; 1:三方登录; 2:手机号登录
    private static final String SP_LOGIN_TYPE = "sp_login_type";

    //会员等级: 0没有登录；1：普通会员；2:店主
    private static final String SP_MEMBER_SHIPLEVEL = "sp_member_shipLevel";

    private static final String SP_SESSION_ID = "sp_session_id";

    private static final String SP_RANK_NAME = "sp_rank_name";

    private static final String SP_UID = "sp_uid";

    private static final String SP_PIC = "sp_pic";

    private static final String SP_OWNER_PICTURE = "sp_owner_picture";


    private static final String SP_DEVICE_TOKEN = "sp_device_token";

    private static final String SP_ACCOUNT_ID = "sp_account_id";

    private static final String SP_PASSWORD = "sp_password";

    private static final String SP_USERNAME = "sp_username";

    private static final String SP_HASREGDAY = "sp_hasRegDay";

    private static final String SP_EMAIL = "sp_email";
    //消费金额
    private static final String SP_AMOUNT_OF_CONSUMPTION = "amount_of_consumption";
    //账户余额
    private static final String SP_BAlANCE = "sp_balance";


    //佣金金额
    private static final String SP_COMMISSION_BAlANCE = "sp_commission_balance";
    //积分
    private static final String SP_SCORE = "sp_score";

    private static final String SP_REFERRER = "referrer_id";

    private static final String SP_CAN_WITHDRAW = "sp_withdraw";

    public static final String SP_LOGIN_NAME = "sp_login_name";

    public static final String SP_LOGIN_PASSWORD = "sp_login_password";
    //所在支行
    public static final String SP_LOGIN_BRANCH = "sp_login_branch";

    //职位
    public static final String SP_LOGIN_POSITION = "sp_login_position";


    //授信申请id
    public static final String SP_APPLY_ID = "sp_apply_id";
    //授信身份证
    public static final String SP_APPLY_SFZ = "sp_apply_id";
    //身份证id
    public static final String SP_ID_CARD = "sp_id_card";

    //调查类型
    public static final String SP_DC_TYPE = "sp_dc_type";

    //taskid
    public static final String SP_DC_TASKID = "sp_dc_taskid";

    //instid
    public static final String SP_DC_INSTID = "sp_dc_instid";

    //系统类型
    public static final String SP_XT_TYPE = "sp_xt_type";

    public static final String SP_IS_PO = "sp_is_po";


    private boolean enableRetry = true;

    private static UserMgr instance;

    private String sessionId;

    private String mUid;


    private boolean isLogin;

    private String mUsername;

    private boolean isVip = false;


    private String hasRegDay;


    private int memberShipLevel;

    private String headerpic;

    private String referrer_id;
    /**
     * 手势密码key
     */
    public static final String GESTURELOCK_KEY = "gesturelock_key";

    /**
     * 是否设置指纹解锁key
     */
    public static final String ISFINGERPRINT_KEY = "fingerprint_key";

    /**
     * 是否设置手势解锁key
     */
    public static final String ISGESTURELOCK_KEY = "isgesturelock_key";


    private UserMgr() {

    }

    public static UserMgr getInstance() {
        if (instance == null) {
            instance = new UserMgr();
        }
        return instance;
    }


    public void doRegister(String phone, String password, String verifyCode, String id, final CommCallBack commCallBack) {
        AccountApi.registerByEmail(phone, password, verifyCode, id, new BaseCallback<BaseResponse<LoginBean>>() {
            @Override
            public void onSucc(BaseResponse<LoginBean> result) {
                commCallBack.complete(0, "注册成功");
                clearUserInfo();
            }

            @Override
            public void onFail(ApiException apiError) {
                commCallBack.complete(apiError.getErrCode(), apiError.getErrMsg());
            }
        }, this);
    }

    /**
     * 快速登录
     */
    public void doLoginByToken(final CommCallBack callBack) {
        String tokenID = UserMgr.getInstance().getSessionId();
        AccountApi.loginByToken(tokenID, new BaseCallback<BaseResponse<LoginBean>>() {
            @Override
            public void onSucc(BaseResponse<LoginBean> result) {
                updateLoginInfo(result.data);
                callBack.complete(0, "登录成功");
            }

            @Override
            public void onFail(ApiException apiError) {
                callBack.complete(apiError.getErrCode(), apiError.getErrMsg());
            }
        }, this);

    }

    /**
     * 刷新会员信息
     *
     * @param callback
     */
    public void refreshMyCenter(final CommCallBack callback) {
        UserApi.getUserInfo(new BaseCallback<BaseResponse<UserInfoBean>>() {
            @Override
            public void onSucc(BaseResponse<UserInfoBean> result) {
                updateUserInfo(result.data);
                callback.complete(0, result.msg);
            }

            @Override
            public void onFail(ApiException apiError) {
                callback.complete(apiError.getErrCode(), apiError.getErrMsg());
            }
        }, this);
    }


    /**
     * 手机号短信验证码登录
     *
     * @param phone
     * @param code
     * @param callback
     */
    public void doLoginByPhoneCode(final String phone, final String code, final CommCallBack callback) {
        AccountApi.loginByPhoneCode(phone, code, new BaseCallback<BaseResponse<LoginBean>>() {
            @Override
            public void onSucc(BaseResponse<LoginBean> result) {
                updateLoginInfo(result.data);
                callback.complete(0, "登录成功");
            }

            @Override
            public void onFail(ApiException apiError) {
                callback.complete(apiError.getErrCode(), apiError.getErrMsg());
            }
        }, this);

    }

    /**
     * 手机号密码登录
     *
     * @param phone
     * @param password
     * @param callback
     */
    public void doLoginByPhone(final String phone, final String password, final CommCallBack callback, Object object) {
        AccountApi.loginByPhone(phone, password, new BaseCallback<BaseResponse<LoginBean>>() {
            @Override
            public void onSucc(BaseResponse<LoginBean> result) {

                UserMgr.getInstance().setLoginType(CommConfig.LOGIN_TYPE_ACCOUNT);
                SPUtils.getInstance().put(SP_LOGIN_NAME, phone);
                SPUtils.getInstance().put(SP_LOGIN_PASSWORD, password);
                SPUtils.getInstance().put(SP_LOGIN_POSITION, result.data.getPosition().getPositionName());
                SPUtils.getInstance().put(SP_LOGIN_BRANCH, result.data.getmDeparts().get(0).getDepartName());

                updateLoginInfo(result.data);
                callback.complete(0, "登录成功");
                LogUtils.e(result.msg);
            }

            @Override
            public void onFail(ApiException apiError) {
                LogUtils.e(apiError.getErrCode());
                LogUtils.e(apiError.getErrMsg());
                callback.complete(apiError.getErrCode(), apiError.getErrMsg());
            }
        }, object);
    }

    /**
     * 三方登录
     *
     * @param auth 微信信息
     */
    public void doThirdLogin(AuthUser auth, final CommCallBack callback) {
        AccountApi.loginByThird(auth.getUid(), auth.getUnionid(), auth.getNickName(), auth.getSex(), auth.getAvatar(), new BaseCallback<BaseResponse<LoginBean>>() {
            @Override
            public void onSucc(BaseResponse<LoginBean> result) {
                updateLoginInfo(result.data);
                callback.complete(0, "登录成功");
            }

            @Override
            public void onFail(ApiException apiError) {
                callback.complete(apiError.getErrCode(), apiError.getErrMsg());
            }
        }, this);

    }


    public void doLogoutAllClient(String accountId, final CommCallBack commCallBack) {
        AccountApi.logoutAll(accountId, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                clearUserInfo();
                commCallBack.complete(0, "下线成功");
            }

            @Override
            public void onFail(ApiException apiError) {
                clearUserInfo();
                commCallBack.complete(-1, "下线失败");
            }
        }, this);
    }

    public void doLogout(final CommCallBack commCallBack) {
        AccountApi.logout(new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                clearUserInfo();
                commCallBack.complete(0, "退出成功");
            }

            @Override
            public void onFail(ApiException apiError) {
                clearUserInfo();
                commCallBack.complete(apiError.getErrCode(), apiError.getErrMsg());
            }
        }, this);

    }


    public void doResetPwd(String accountId, String newPwd, String verifyCode, final CommCallBack commCallBack) {

        AccountApi.resetPwd(accountId, newPwd, verifyCode, null, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                clearUserInfo();
                commCallBack.complete(0, "密码重置成功");
            }

            @Override
            public void onFail(ApiException apiError) {
                commCallBack.complete(apiError.getErrCode(), apiError.getErrMsg());
            }
        }, this);
    }

    /**
     * 更新登陆信息
     *
     * @param loginBean
     */
    private void updateLoginInfo(LoginBean loginBean) {
        isLogin = true;
        setSessionId(loginBean.getSessionId());
        setUid(loginBean.getUserInfo().getId());
//        setHeaderpic(loginBean.getHeaderPic());
    }

    private void updateUserInfo(UserInfoBean userInfoBean) {
        setUsername(userInfoBean.getNickName());
        setUid(userInfoBean.getId());
        setHasRegDay(userInfoBean.getCreate_time());
        setHeaderpic(userInfoBean.getHeaderPic());
        setMemberShipLevel(userInfoBean.getRank_id());
        setSpRankName(userInfoBean.getRankName());
        setSP_BAlANCE(userInfoBean.getBalance());
        setSP_COMMISSION_BAlANCE(userInfoBean.getCommission_income());
        setSpScore(userInfoBean.getScore());
        setSpAmountOfConsumption(userInfoBean.getTotal_year_consume());
        setSpReferrer(userInfoBean.getReferrer_id());
        setmOwnerPic(userInfoBean.getShop_protrait());
        setSpCanWithdraw(userInfoBean.getWithdraw_auth());
        EventBus.getDefault().post(userInfoBean);
    }


    public void clearUserInfo() {
        isLogin = false;
        setSessionId("");
        setLoginType(0);
        setUsername("");
        setHasRegDay("");
        setUid("");
        setPassword("");
        setEmail("");
        setSpRankName("");
        setMemberShipLevel(0);
        setHeaderpic("");
        setSpAmountOfConsumption("");
        setSpScore("");
        setSP_BAlANCE("");
        setSP_COMMISSION_BAlANCE("");
        setSpReferrer("");
        setmOwnerPic("");
        setSpCanWithdraw("");
    }


    public String getSessionId() {
        if (sessionId == null) {
            sessionId = SPUtils.getInstance().getString(SP_SESSION_ID);
        }

        return sessionId;
    }

    public String getUid() {
        if (mUid == "") {
            mUid = SPUtils.getInstance().getString(SP_UID);
        }
        return mUid;
    }

    public String getHeaderpic() {
        headerpic = SPUtils.getInstance().getString(SP_PIC);
        return headerpic;
    }

    public void setHeaderpic(String headerpic) {
        this.headerpic = headerpic;
        SPUtils.getInstance().put(SP_PIC, headerpic);
    }

    public int getMemberShipLevel() {

        if (memberShipLevel == 0) {

            memberShipLevel = SPUtils.getInstance().getInt(SP_MEMBER_SHIPLEVEL);
        }
        return memberShipLevel;
    }


    public void setMemberShipLevel(int memberShipLevel) {
        this.memberShipLevel = memberShipLevel;
        SPUtils.getInstance().put(SP_MEMBER_SHIPLEVEL, memberShipLevel);
    }

    public void setmOwnerPic(String ownerPic) {
        SPUtils.getInstance().put(SP_OWNER_PICTURE, ownerPic);
    }

    public String getSpOwnerPicture() {
        return SPUtils.getInstance().getString(SP_OWNER_PICTURE, "");
    }


    public void setmDeviceToken(String mDeviceToken) {
        SPUtils.getInstance().put(SP_DEVICE_TOKEN, mDeviceToken);
    }

    public String getSpDeviceToken() {
        return SPUtils.getInstance().getString(SP_DEVICE_TOKEN, "");
    }


    public String getPassword() {
        return SPUtils.getInstance().getString(SP_PASSWORD);
    }


    public void setLoginType(int loginType) {
        SPUtils.getInstance().put(SP_LOGIN_TYPE, loginType);
    }

    public int getLoginType() {
        return SPUtils.getInstance().getInt(SP_LOGIN_TYPE, 0);
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
        if (ObjectUtils.isEmpty(sessionId)) {
            isLogin = false;
        } else {
            isLogin = true;
        }
        SPUtils.getInstance().put(SP_SESSION_ID, sessionId);
    }

    public void setUid(String uid) {
        mUid = uid;
        SPUtils.getInstance().put(SP_UID, uid);
    }


    public void setPassword(String password) {
        // TODO: 加密
        SPUtils.getInstance().put(SP_PASSWORD, password);
    }

    public void setUsername(String username) {
        mUsername = username;
        SPUtils.getInstance().put(SP_USERNAME, username);
    }

    public String getUsername() {
        if (ObjectUtils.isEmpty(mUsername)) {
            mUsername = SPUtils.getInstance().getString(SP_USERNAME);
        }
        return mUsername;
    }

    public void setEmail(String email) {
        SPUtils.getInstance().put(SP_EMAIL, email);
    }

    public String getEmail() {
        return SPUtils.getInstance().getString(SP_EMAIL);
    }


    public void setSpRankName(String spRankName) {
        SPUtils.getInstance().put(SP_RANK_NAME, spRankName);
    }

    public String getSpRankName() {
        return SPUtils.getInstance().getString(SP_RANK_NAME);
    }

    public void setSpAmountOfConsumption(String spRankName) {
        SPUtils.getInstance().put(SP_AMOUNT_OF_CONSUMPTION, spRankName);
    }

    public String getSpAmountOfConsumption() {
        return SPUtils.getInstance().getString(SP_AMOUNT_OF_CONSUMPTION);
    }

    public void setSP_COMMISSION_BAlANCE(String spRankName) {
        SPUtils.getInstance().put(SP_COMMISSION_BAlANCE, spRankName);

    }

    public String getSP_COMMISSION_BAlANCE() {
        return SPUtils.getInstance().getString(SP_COMMISSION_BAlANCE);
    }

    public void setSP_BAlANCE(String spRankName) {
        SPUtils.getInstance().put(SP_BAlANCE, spRankName);
    }

    public String getSP_BAlANCE() {
        return SPUtils.getInstance().getString(SP_BAlANCE);
    }


    public void setSpScore(String spRankName) {
        SPUtils.getInstance().put(SP_SCORE, spRankName);
    }

    public String getSpScore() {
        return SPUtils.getInstance().getString(SP_SCORE);
    }

    public boolean isLogin() {

        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }


    public boolean isVip() {
        return isVip;
    }


    public String getHasRegDay() {
        if (ObjectUtils.isEmpty(hasRegDay)) {
            hasRegDay = SPUtils.getInstance().getString(SP_HASREGDAY);
        }
        return hasRegDay;
    }

    public void setHasRegDay(String hasRegDay) {
        this.hasRegDay = hasRegDay;
        SPUtils.getInstance().put(SP_HASREGDAY, hasRegDay);
    }

    public String getSpReferrer() {
        return SPUtils.getInstance().getString(SP_REFERRER);
    }

    public void setSpReferrer(String referrer_id) {
        SPUtils.getInstance().put(SP_REFERRER, referrer_id);
    }


    public String getSpCanWithdraw() {
        return SPUtils.getInstance().getString(SP_CAN_WITHDRAW);
    }

    public void setSpCanWithdraw(String can_withdraw) {
        SPUtils.getInstance().put(SP_CAN_WITHDRAW, can_withdraw);
    }
}
