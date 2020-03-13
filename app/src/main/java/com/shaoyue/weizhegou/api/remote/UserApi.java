package com.shaoyue.weizhegou.api.remote;


import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.callback.BitmapCallback;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.cedit.IDCardBack;
import com.shaoyue.weizhegou.entity.cedit.IDCardFrontBean;
import com.shaoyue.weizhegou.entity.user.CommissionBalanceBean;
import com.shaoyue.weizhegou.entity.user.CommissionListBean;
import com.shaoyue.weizhegou.entity.user.DistributionNumBean;
import com.shaoyue.weizhegou.entity.user.OrdererNumBean;
import com.shaoyue.weizhegou.entity.user.SafeInfoBean;
import com.shaoyue.weizhegou.entity.user.UserInfoBean;
import com.shaoyue.weizhegou.entity.user.UserMsgBean;
import com.shaoyue.weizhegou.entity.user.WalletDetailsBean;
import com.shaoyue.weizhegou.entity.user.WebBean;
import com.shaoyue.weizhegou.manager.UserMgr;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UserApi extends BaseApi {

    //获取用户信息
    private static final String API_USER_INFO = "jeecg-boot/sys/user/queryById";

    //修改昵称
    private static final String API_NICK_NAME = "user/profile";


    //上传地址接口
    private static final String API_HAEDER_PIC = "jeecg-boot/sys/common/upload";

    private static final String API_CHANGE_USER_INFO = "jeecg-boot/sys/user/editUser";


    private static final String API_GET_MSG = "jeecg-boot/process/getShouYeApprovalList";

    //获取安全信息
    private static final String API_GET_SAFE_INFO = "user/getSafeInfo";


    //绑定手机号
    private static final String API_BIND_PHONE = "user/bind_phone";

    //绑定新接口
    private static final String API_MODIFY_MOBILE = "user/modify_mobile";

    //设置支付密码
    private static final String API_SET_PAY_PASSWORD = "user/setPayPassword";

    //设置密码
    private static final String API_SET_PASSWORD = "user/password_reset";


    //获取分销产品 分销商 会员 分销订单数量
    private static final String API_USER_BADGECOUNT = "user/badgeCount";
    //获取下级订货商 下级会员 团队订单
    private static final String API_USER_BUYBAGDGE = "user/buyBadgeCount";

    //生成图片二维码
    private static final String API_USER_GET_QRCODE = "user/getQrCode";

    //是否绑定手机号
    private static final String API_IS_BIND_PHONE = "user/isMobile";

    //佣金明细
    private static final String API_GET_ALL_DETAIL = "user/getAllDetail";
    //完善个人信息
    private static final String API_USER_UPDATE_INFO = "user/updateInfo";
    //我的上级
    private static final String API_USER_PARENT_INFO = "user/getParentInfo";

    //下级会员
    private static final String API_SONUSER_LIST = "user/sonUser";

    //下级订货商列表
    private static final String API_USER_SONBUYUSER = "user/sonBuyUser";

    //提现到余额
    private static final String API_USER_TOBAlANCE = "user/toBalance";

    //是否绑定微信
    private static final String API_BIND_WECHAT = "user/isWechat";

    //分销商会员列表
    private static final String API_GET_SONUSER = "user/getSonUser";

    //分销商列表
    private static final String API_GET_SONSALE = "user/getSonSale";


    //我的钱包
    private static final String API_WALLET_DETAILS = "user/asset/getWalletInfo";

    //获取webview
    private static final String API_GET_WEBVIEW = "user/center/getWebView";

    //身份证识别
    private static final String API_OCR = "jeecg-boot/business/common/idCard";

    //系统版本-获取版本
    private static final String SYSVERSION_VERSION = "jeecg-boot/system/sysVersion/version";



    public static void getInfoMsg(int pageNo, String pageSize, BaseCallback<BaseResponse<UserMsgBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize);
        ApiHttpClient.post(API_GET_MSG, params, callback, tag);
    }

    public static void changeUserInfo(String name, String avatar, String email, String phone, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", UserMgr.getInstance().getUid());
        params.put("realname", name);
        params.put("avatar", avatar);
        params.put("email", email);
        params.put("phone", phone);
        ApiHttpClient.putJson(API_CHANGE_USER_INFO, params, callback, tag);
    }

    /**
     * 获取webview
     *
     * @param callback
     * @param tag
     */
    public static void getWebview(String code, BaseCallback<BaseResponse<WebBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        ApiHttpClient.post(API_GET_WEBVIEW, params, callback, tag);
    }


    /**
     * 获取钱包明细
     *
     * @param callback
     * @param tag
     */
    public static void getGetWalletDetails(BaseCallback<BaseResponse<WalletDetailsBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_WALLET_DETAILS, params, callback, tag);
    }





    /**
     * 是否绑定微信
     */
    public static void isBindWechat(BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_BIND_WECHAT, params, callback, tag);
    }

    /**
     * 获取订货商中心的数量
     */
    public static void getOrdererCenterNum(BaseCallback<BaseResponse<OrdererNumBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();

        ApiHttpClient.post(API_USER_BUYBAGDGE, params, callback, tag);
    }

    /**
     * 提现到余额
     */
    public static void withdrawalToTheBalance(String amount, BaseCallback<BaseResponse<CommissionBalanceBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("amount", amount);
        ApiHttpClient.post(API_USER_TOBAlANCE, params, callback, tag);
    }






    /**
     * 完善个人信息
     *
     * @param callback
     * @param tag
     */
    public static void updateInfo(String mobile, String code, String password, String referrer, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("code", code);
        params.put("password", password);
        params.put("referrer", referrer);
        ApiHttpClient.post(API_USER_UPDATE_INFO, params, callback, tag);
    }

    /**
     * 获取充值明细
     *
     * @param callback
     * @param tag
     */
    public static void getAllDetail(String source, int page, String type, BaseCallback<BaseResponse<CommissionListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("source", source);
        params.put("page", page + "");
        params.put("type", type);
        ApiHttpClient.post(API_GET_ALL_DETAIL, params, callback, tag);
    }

    /**
     * 获取交易明细
     *
     * @param callback
     * @param tag
     */
    public static void getAllDetail(String source, int page, BaseCallback<BaseResponse<CommissionListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("source", source);
        params.put("page", page + "");
        ApiHttpClient.post(API_GET_ALL_DETAIL, params, callback, tag);
    }


    /**
     * 是否绑定弹窗
     */
    public static void isBindPhone(BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_IS_BIND_PHONE, params, callback, tag);
    }

    /**
     * 获取图片二维码
     *
     * @param callback
     * @param tag
     */
    public static void getShareImg(BitmapCallback callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.getImg(API_USER_GET_QRCODE, params, callback, tag);
    }

    /**
     * 获取分销商中心的数量
     */
    public static void getDistributionCenterNum(BaseCallback<BaseResponse<DistributionNumBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();

        ApiHttpClient.post(API_USER_BADGECOUNT, params, callback, tag);
    }

    /**
     * 绑定手机
     */
    public static void bindPhone(String mobile, String password, String code, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("password", password);
        params.put("code", code);
        ApiHttpClient.post(API_BIND_PHONE, params, callback, tag);
    }


    /**
     * 换绑手机
     */
    public static void bindNewPhone(String old_code, String new_mobile, String code, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("old_code", old_code);
        params.put("new_mobile", new_mobile);
        params.put("new_code", code);
        ApiHttpClient.post(API_MODIFY_MOBILE, params, callback, tag);
    }


    /**
     * 获取用户信息
     *
     * @param callback
     * @param tag
     */
    public static void getUserInfo(BaseCallback<BaseResponse<UserInfoBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();

        params.put("id", UserMgr.getInstance().getUid());
        ApiHttpClient.post(API_USER_INFO, params, callback, tag);
    }

    /**
     * 修改昵称
     */
    public static void changeNickName(String nickname, BaseCallback<BaseResponse<UserInfoBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("nickname", nickname);
        ApiHttpClient.post(API_NICK_NAME, params, callback, tag);
    }


    /**
     * 设置支付密码或密码
     */
    public static void setPassword(int type, String mobile, String password, String code, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        String url = API_SET_PAY_PASSWORD;
        if (type == 2) {
            url = API_SET_PASSWORD;
        }
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("password", password);
        params.put("code", code);
        ApiHttpClient.post(url, params, callback, tag);
    }


    /**
     * Ocr识别正面
     *
     * @param mFile
     * @param callback
     * @param tag
     */
    public static void updatOcr(String side, File mFile, BaseCallback<BaseResponse<IDCardFrontBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("side", side);

        ApiHttpClient.updateImg(API_OCR, params, mFile, callback, tag);
    }

    /**
     * Ocr识别反面
     *
     * @param mFile
     * @param callback
     * @param tag
     */
    public static void updatOcrBack(String side, File mFile, BaseCallback<BaseResponse<IDCardBack>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("side", side);

        ApiHttpClient.updateImg(API_OCR, params, mFile, callback, tag);
    }

    /**
     * 人脸识别图片
     *
     * @param mFile
     * @param callback
     * @param tag
     */
    public static void updatePic(File mFile, String zjhm, BaseCallback<BaseResponse<String>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.updateImg(API_HAEDER_PIC, params, mFile, callback, tag);
    }


    /**
     * 上传图片
     *
     * @param mFile
     * @param callback
     * @param tag
     */
    public static void updatePic(File mFile, BaseCallback<BaseResponse<String>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.updateImg(API_HAEDER_PIC, params, mFile, callback, tag);
    }





    /**
     * 获取安全信息
     */
    public static void getSafeInfo(BaseCallback<BaseResponse<SafeInfoBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_GET_SAFE_INFO, params, callback, tag);
    }


}
