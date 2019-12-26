package com.shaoyue.weizhegou.api.remote;

import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.CServiceBean;
import com.shaoyue.weizhegou.entity.InitBean;
import com.shaoyue.weizhegou.entity.SignBean;
import com.shaoyue.weizhegou.entity.VersionBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bin on 17/7/6.
 */

public class CommApi {


    private static final String API_CHECK_UPDATE = "jeecg-boot/system/sysVersion/version";

    // 获取初始化参数
    private static final String API_GET_INIT_DATA = "api/system/init";

    // 获取服务信息
    private static final String API_GET_CSERVICE = "api/system/get_service";

    // 获取邮箱验证码
    private static final String API_FETCH_EMAIL_CODE = "user/send_code";

    private static final String API_REPORT_APP_STATE = "api/system/operation_report";

    private static final String API_SPEED_SIGN = "api/account/sign";

    private static final String API_APP_CHECK_ENABLE = "";

    //	1：会员注册 2：找回密码 3：绑定手机号 4：手机验证码登录 5：解绑手机号 6：只绑定手机号
    public static void fetchPhoneCode(String phone, String type, String verify,
                                      BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("mobile", phone);
        params.put("scene", type);
        if (!TextUtils.isEmpty(verify)) {
            params.put("verify", verify);
        }
        ApiHttpClient.post(API_FETCH_EMAIL_CODE, params, callback, tag);
    }


    public static void checkUpdate(BaseCallback<BaseResponse<VersionBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("version", AppUtils.getAppVersionCode() + "");
        ApiHttpClient.post(API_CHECK_UPDATE, params,callback, tag);
    }


    public static void fetchInitData(BaseCallback<BaseResponse<InitBean>> callback, Object tag) {
        ApiHttpClient.post(API_GET_INIT_DATA, callback, tag);
    }

    public static void reportAppState(String type, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        ApiHttpClient.post(API_REPORT_APP_STATE, params, callback, tag);
    }

    public static void fetchCService(BaseCallback<BaseResponse<CServiceBean>> callback, Object tag) {
        ApiHttpClient.post(API_GET_CSERVICE, callback, tag);
    }

    public static void speedSign(BaseCallback<BaseResponse<SignBean>> callback, Object tag) {
        ApiHttpClient.post(API_SPEED_SIGN, callback, tag);
    }


}
