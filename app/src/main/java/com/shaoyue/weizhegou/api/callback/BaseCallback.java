package com.shaoyue.weizhegou.api.callback;


import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.lzy.okgo.model.Response;
import com.shaoyue.weizhegou.AppContext;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.common.CommErrorCode;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * Created by bin on 17/6/4.
 */

public abstract class BaseCallback<T> extends JsonCallback<T> implements ResultInterface<T> {

    public abstract void onSucc(T result);


    public void onFail(ApiException apiError) {

        ToastUtil.showErrorToast(apiError.getMessage());
        if (apiError.getErrCode() == 500 && apiError.getErrMsg().contains("Token失效")) {
            UIHelper.goLoginPage(AppContext.getInstance());
            UserMgr.getInstance().setLogin(false);
        }

    }

    @Override
    public void onSuccess(Response<T> response) {

        if (response.body() instanceof BaseResponse) {
            onSucc(response.body());
            LogUtils.e(response.body());
        } else {
            ApiException apiError = new ApiException(-1, "模型错误");

            onFail(apiError);
        }
    }

    @Override
    public void onError(Response<T> response) {

        if (response.getException() instanceof ApiException) {
            ApiException apiError = new ApiException(response.code(), response.message());
            onFail((ApiException) response.getException());
        } else {
            int errCode = CommErrorCode.ERROR_CODE_UNKNOWN;
            String errMsg = "网络错误，请检查网络是否正常";
            if (response.getException() != null) {
                if (response.getRawResponse() != null) {
                    LogUtils.e(errMsg);
                    errCode = response.getRawResponse().code();
                    errMsg = response.getRawResponse().message();
                } else if (response.getException() instanceof ConnectException) {

                    String msg = response.getException().getLocalizedMessage();
                    errCode = CommErrorCode.ERROR_CODE_LOCAL_NETWORK;
                    errMsg = "无法连接服务器，请检查网络是否正常";
                } else if (response.getException() instanceof SocketTimeoutException) {
                    errCode = CommErrorCode.ERROR_CODE_LOCAL_NETWORK;
                    errMsg = "连接服务器超时";
                } else {
                    LogUtils.e(errMsg);
                }
            } else {
                if (response.getRawResponse() != null) {
                    errCode = response.getRawResponse().code();
                    errMsg = response.getRawResponse().message();

                }
            }
            if (ObjectUtils.isEmpty(errMsg)) {
                errMsg = "Token失效";
            }
            ApiException apiError = new ApiException(errCode, errMsg);

            onFail(apiError);
        }
    }


}
