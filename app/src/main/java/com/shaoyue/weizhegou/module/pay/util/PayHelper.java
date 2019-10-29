package com.shaoyue.weizhegou.module.pay.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.lzy.okgo.OkGo;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.PayApi;
import com.shaoyue.weizhegou.entity.PayBean;
import com.shaoyue.weizhegou.entity.PayOrderBean;
import com.shaoyue.weizhegou.entity.WebEntity;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 作者：HQY on 17/3/21 10:45
 * 邮箱：hqy_xz@126.com
 */

public class PayHelper extends MDPresenter {

    private static final int SDK_PAY_FLAG = 1;

    private PayView payListener;


    private Activity mActivity;

    private String type;

    private IWXAPI api;
    private boolean payClick = true;

    public PayHelper(Activity activity, PayView listener) {

        this.mActivity = activity;
        this.payListener = listener;

    }


    public void setPayTime(PayBean payTime, String type) {
        setPackageTime(payTime, type);

    }


    /**
     * 套餐购买
     *
     * @param payTime
     */
    private void setPackageTime(final PayBean payTime, final String type) {
        if (payClick = true) {
            payClick = false;
            final Map<String, String> map = new HashMap<>();
            String mType = "";
            //alipay_jump,wechat_h5
            if (type.equals("2")) {
                mType = "alipay_jump";
            } else {
                mType = "wechat_h5";
            }

            PayApi.getOrder(UserMgr.getInstance().getUsername(), payTime.getSn(), mType, new BaseCallback<BaseResponse<PayOrderBean>>() {
                @Override
                public void onSucc(BaseResponse<PayOrderBean> result) {
                    PayOrderBean pay = result.data;


                    if (type.equals("2")) {
                        onPayScc(pay.getAlipaySign());
                    } else if (type.equals("1")) {
                        try {
                            if (AppUtils.isAppInstalled("com.tencent.mm")) {
                                UIHelper.showWxWebPage(mActivity, new WebEntity(mActivity.getString(R.string.title_wechat_payment), result.data.getPayUrl()
                                        , payTime.getPayPrice(), result.data.getOrderId(),
                                        TimeUtils.millis2String(TimeUtils.getNowMills(), new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault()))));
                                LogUtils.e(pay.getPayUrl());
                                payClick = true;
                            } else {
                                ToastUtil.showErrorToast(mActivity.getString(R.string.content_please_install_wechat_first));
                            }
                        } catch (Exception e) {
                            ToastUtil.showErrorToast(e.toString());
                        }

                    }
                }


                @Override
                public void onFail(ApiException e) {
                    super.onFail(e);
                    payClick = true;
                    ToastUtil.showToast(e.getErrMsg());
                }
            }, "pay");
        }
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        payListener.paySucc();
                        ToastUtil.showToast(mActivity.getResources().getString(R.string.pay_success));
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtil.showToast(mActivity.getResources().getString(R.string.pay_in));
                            payListener.payFial();
                        } else if (TextUtils.equals(resultStatus, "4000")) {
                            ToastUtil.showToast(mActivity.getResources().getString(R.string.pay_fial));
                            payListener.payFial();
                        } else if (TextUtils.equals(resultStatus, "6001")) {
                            ToastUtil.showToast(mActivity.getResources().getString(R.string.pay_cancel_fial));
                            payListener.payFial();
                        } else if (TextUtils.equals(resultStatus, "6002")) {
                            ToastUtil.showToast(mActivity.getResources().getString(R.string.pay_network_fial));
                            payListener.payFial();
                        } else if (TextUtils.equals(resultStatus, "6004")) {
                            ToastUtil.showToast(mActivity.getResources().getString(R.string.pay_handle_fial));
                            payListener.payFial();
                        } else {
                            ToastUtil.showToast(mActivity.getResources().getString(R.string.pay_other_fial));
                            payListener.payFial();
                        }
                    }

                    break;
                }
                default:
                    break;
            }
        }

    };

    //
    public void onPayScc(final String sign) {


//            LogUtil.e(payEntity.toString());
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(mActivity);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(sign, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    @Override
    public void onDestory() {
        OkGo.getInstance().cancelTag("pay");
        payListener = null;
        mActivity = null;
    }
}
