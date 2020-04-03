package com.shaoyue.weizhegou.api.remote;

import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.callback.ResultInterface;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.ActivityBean;
import com.shaoyue.weizhegou.entity.InviteCode;
import com.shaoyue.weizhegou.entity.LoginBean;
import com.shaoyue.weizhegou.entity.MemberShipBean;
import com.shaoyue.weizhegou.entity.NewsList;
import com.shaoyue.weizhegou.entity.center.VerifiedNoticeBean;
import com.shaoyue.weizhegou.entity.coupon.CouponListBean;
import com.shaoyue.weizhegou.entity.user.CommissionDetailBean;
import com.shaoyue.weizhegou.entity.user.OrderStatisticsBean;
import com.shaoyue.weizhegou.entity.user.PayTypeInfoBean;
import com.shaoyue.weizhegou.manager.UserMgr;

import java.util.HashMap;
import java.util.Map;

public class AccountApi extends BaseApi {


    // 注册
    private static final String API_REGISTER = "user/register";

    // 账户登录
    private static final String API_ACCOUNT_LOGIN = "jeecg-boot/sys/login";


    //手机验证码登陆
    private static final String API_PHONE_CODE_LOGIN = "user/code_login";

    // 设备号登录
    private static final String API_ACCOUNT_LOGIN_DEVICE_ID = "api/account/dev_sn_login";

    // 退出
    private static final String API_LOGOUT = "user/logout";


    // 修改密码
    private static final String API_CHANGE_PWD = "jeecg-boot/sys/user/updatePassword";


    // 重置密码
    private static final String API_RESET_PWD = "api/account/reset_pass";

    // 绑定邮箱
    private static final String API_BIND_EMAIL = "api/account/bind";


    private static final String API_CHECK_SPPED_ENABLE = "api/account/chk_account_enable";
    //会员到期
    private static final String API_IS_POPUP = "api/account/is_popup";

    //活动弹窗
    private static final String API_ACTIVITY_POPUP = "api/account/active_popup";

    //分享成功
    private static final String API_SHARE_TIME = "api/account/share_add_time";

    //消息列表
    private static final String API_ARTICLE_GET_LIST = "api/article/get_list";

    //邀请码获取
    private static final String API_ACCOUNT_GET_INVITE_CODE = "api/account/get_invite_code";


    //填写验证码
    private static final String API_ACCOUNT_SET_INVITE = "api/account/set_invite";

    //三方登录
    private static final String API_THIRD_LOGIN = "user/third_login";

    //快速登陆
    private static final String API_AUTO_LOGIN = "user/auto_login";


    //实名认证须知
    private static final String API_VERIFIED_NOTICE = "user/getRealnameAuthNotice";

    //购物须知
    private static final String API_GET_BUY_READ = "user/getBuyRead";

    //实名认证
    private static final String API_VERIFIED = "user/setRealname";

    //绑定微信
    private static final String API_BIND_WEICHAT = "user/bind_wechat";

    //获取支付密码须知
    private static final String API_PAY_PASSWORD_NOTICE = "user/getPayPasswordNotice";

    //获取优惠卷列表
    private static final String API_COUPON_LIST = "user/getCoupon";

    //获取支付方式
    private static final String API_PAY_TYPE = "user/getPaymentTypeInfo";

    //获取佣金明细
    private static final String API_GET_COMMISSION_DETAIL = "user/getCommissionDetail";

    //会员订单统计
    private static final String API_USER_ORDER_STATISTICS = "order/statistics";

    /**
     * 会员订单统计
     *
     * @param callback
     * @param tag
     */
    public static void getOrderStatistics(BaseCallback<BaseResponse<OrderStatisticsBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_USER_ORDER_STATISTICS, params, callback, tag);
    }


    /**
     * 获取佣金详细信息
     *
     * @param callback
     * @param tag
     */
    public static void getCommissionDetail(BaseCallback<BaseResponse<CommissionDetailBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_GET_COMMISSION_DETAIL, params, callback, tag);
    }

    public static void getPayTypeInfo(BaseCallback<BaseResponse<PayTypeInfoBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_PAY_TYPE, params, callback, tag);
    }


    public static void getCouponList(String state, int page, BaseCallback<BaseResponse<CouponListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("state", state);
        params.put("page", page + "");
        ApiHttpClient.post(API_COUPON_LIST, params, callback, tag);
    }

    /**
     * 邀请码获取
     */
    public static void getInviteCode(BaseCallback<BaseResponse<InviteCode>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_ACCOUNT_GET_INVITE_CODE, params, callback, tag);
    }

    /**
     * 获取实名认证须知
     *
     * @param callback
     * @param tag
     */
    public static void getBuyRead(BaseCallback<BaseResponse<VerifiedNoticeBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_GET_BUY_READ, params, callback, tag);
    }


    /**
     * 获取实名认证须知
     *
     * @param callback
     * @param tag
     */
    public static void getVerifiedNotice(BaseCallback<BaseResponse<VerifiedNoticeBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_VERIFIED_NOTICE, params, callback, tag);
    }


    /**
     * 获取实名认证须知
     *
     * @param callback
     * @param tag
     */
    public static void getPayPasswordNotice(BaseCallback<BaseResponse<VerifiedNoticeBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_PAY_PASSWORD_NOTICE, params, callback, tag);
    }


    /**
     * 实名认证
     *
     * @param callback
     * @param tag
     */
    public static void setVerified(String realname, String id_card, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("realname", realname);
        params.put("id_card", id_card);
        ApiHttpClient.post(API_VERIFIED, params, callback, tag);
    }


    /**
     * 提交验证码
     */
    public static void setInviteCode(String fill_code, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("fill_code", fill_code);
        ApiHttpClient.post(API_ACCOUNT_SET_INVITE, params, callback, tag);
    }

    /**
     * 消息列表
     */
    public static void getNewsList(int pageNum, int pageSize, BaseCallback<BaseResponse<NewsList>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("code", "turtle_push_message");
        params.put("page", pageNum + "");
        params.put("pre_page", pageSize + "");
        ApiHttpClient.post(API_ARTICLE_GET_LIST, params, callback, tag);
    }

    /**
     * 会员到期弹窗
     */
    public static void isPopup(BaseCallback<BaseResponse<MemberShipBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();

        ApiHttpClient.post(API_IS_POPUP, params, callback, tag);
    }


    /**
     * 活动弹窗
     */
    public static void activityPopup(BaseCallback<BaseResponse<ActivityBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_ACTIVITY_POPUP, params, callback, tag);
    }


    /**
     * 分享成功
     */
    public static void shareTime(BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_SHARE_TIME, params, callback, tag);
    }


    /**
     * 通过设备ID登录
     *
     * @param callback
     * @param tag
     */
    public static void loginByDeviceId(String accountId, BaseCallback<BaseResponse<LoginBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", accountId);
        params.put("origin", "android");
        params.put("spec", "paid");
        params.put("um_token", UserMgr.getInstance().getSpDeviceToken());
        ApiHttpClient.post(API_ACCOUNT_LOGIN_DEVICE_ID, params, callback, tag);
    }


    /**
     * 三方登录
     *
     * @param unionid   微信登录用户唯一标识
     * @param nickname  微信用户昵称
     * @param sex       微信用户性别
     * @param headerpic 微信用户头像地址
     * @param callback
     * @param tag
     */
    public static void loginByThird(String openid, String unionid, String nickname, String sex, String headerpic, BaseCallback<BaseResponse<LoginBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("unionid", unionid);
        params.put("openid", openid);
        params.put("nickname", nickname);
        params.put("sex", sex);
        params.put("headerpic", headerpic);
        params.put("um_token", UserMgr.getInstance().getSpDeviceToken());
        ApiHttpClient.post(API_THIRD_LOGIN, params, callback, tag);
    }

    /**
     * 绑定微信
     *
     * @param unionid 微信登录用户唯一标识
     */
    public static void bindWeichat(String unionid, String openid, String nickname, String portrait, BaseCallback<BaseResponse<LoginBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("openid", openid);
        params.put("unionid", unionid);
        params.put("nickname", nickname);
        params.put("portrait", portrait);
        ApiHttpClient.post(API_BIND_WEICHAT, params, callback, tag);
    }

    /**
     * 快速登录
     *
     * @param tokenId  登录保留的token
     * @param callback
     * @param tag
     */
    public static void loginByToken(String tokenId, BaseCallback<BaseResponse<LoginBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("token", tokenId);
        params.put("um_token", UserMgr.getInstance().getSpDeviceToken());
        ApiHttpClient.post(API_AUTO_LOGIN, params, callback, tag);
    }

    /**
     * 通过手机号登录
     *
     * @param phone
     * @param password
     * @param callback
     * @param tag
     */
    public static void loginByPhone(String phone, String password,String imei,
                                    BaseCallback<BaseResponse<LoginBean>> callback, Object tag) {
        if (!checkPhoneAndPwd(phone, password, callback)) {
            return;
        }

        Map<String, String> params = new HashMap<>();


        params.put("um_token", UserMgr.getInstance().getSpDeviceToken());
        params.put("username", phone);
        params.put("password", password);
        LogUtils.e(imei);
        params.put("captcha", imei);
        ApiHttpClient.postJson(API_ACCOUNT_LOGIN, params, callback, tag);

    }


    /**
     * 通过手机号验证码登录
     *
     * @param phone
     * @param code
     * @param callback
     * @param tag
     */
    public static void loginByPhoneCode(String phone, String code,
                                        BaseCallback<BaseResponse<LoginBean>> callback, Object tag) {

        Map<String, String> params = new HashMap<>();
        params.put("um_token", UserMgr.getInstance().getSpDeviceToken());
        params.put("mobile", phone);
        params.put("code", code);
        ApiHttpClient.post(API_PHONE_CODE_LOGIN, params, callback, tag);

    }


    // 邮箱注册
    public static void registerByEmail(String phone, String password, String verifyCode, String referrer_id,
                                       BaseCallback<BaseResponse<LoginBean>> callback, Object tag) {

        Map<String, String> params = new HashMap<>();
        params.put("mobile", phone);
        params.put("password", password);
        params.put("code", verifyCode);
        params.put("platform", "android");
        params.put("referrer_id", referrer_id);

        ApiHttpClient.post(API_REGISTER, params, callback, tag);

    }


    /**
     * 退出登录
     *
     * @param callback
     * @param tag
     */
    public static void logout(BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("token", UserMgr.getInstance().getSessionId());

        ApiHttpClient.post(API_LOGOUT, params, callback, tag);
    }


    public static void logoutAll(String accountId,
                                 BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("username", accountId);
        ApiHttpClient.post(API_LOGOUT, params, callback, tag);
    }


    public static void changePwd(String username,  String oldPwd,String newPwd, String confirmpassword, BaseCallback<BaseResponse<Void>> callback,
                                 Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("oldpassword", oldPwd);
        params.put("password", newPwd);
        params.put("confirmpassword", confirmpassword);
        ApiHttpClient.putJson(API_CHANGE_PWD, params, callback, tag);
    }


    public static void resetPwd(String accountId, String newPwd, String verifyCode, String imageCode,
                                BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("account", accountId);
        params.put("code", verifyCode);
        params.put("password", newPwd);
        if (!TextUtils.isEmpty(imageCode)) {
            params.put("verify", imageCode);
        }

        ApiHttpClient.post(API_RESET_PWD, params, callback, tag);
    }


    public static void bindEmail(String accountId, String password, String emailCode,
                                 BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("account", accountId);
        params.put("code", emailCode);
        params.put("password", password);
        params.put("type", "email");

        ApiHttpClient.post(API_BIND_EMAIL, params, callback, tag);
    }


    public static void checkSpeedEnable(BaseCallback<BaseResponse<Void>> callback, Object tag) {
        ApiHttpClient.post(API_CHECK_SPPED_ENABLE, callback, tag);
    }


    private static boolean checkPhoneAndPwd(String phone, String password,
                                            ResultInterface callback) {

        if (ObjectUtils.isEmpty(phone)) {
            callback.onFail(new ApiException(ERROR_CODE_APP, "账户不能为空"));
            return false;
        }

        if (ObjectUtils.isEmpty(password)) {
            callback.onFail(new ApiException(ERROR_CODE_APP, "密码不能为空"));
            return false;
        }

        if (password.length() < 6 || password.length() > 30) {
            callback.onFail(new ApiException(ERROR_CODE_APP, "密码长度不合理"));
            return false;
        }

        return true;
    }


}
