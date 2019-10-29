package com.shaoyue.weizhegou.wxapi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shaoyue.weizhegou.entity.PayEvent;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;


public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        api = WXAPIFactory.createWXAPI(this.getApplicationContext(), ConfigContant.WX_APP_ID());
//        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.errCode == 0) {
            ToastUtil.showToast(getString(com.shaoyue.weizhegou.R.string.toast_payment_success));
            EventBus.getDefault().post(new PayEvent("0"));
            finish();
        } else if (resp.errCode == -1) {
            EventBus.getDefault().post(new PayEvent("1"));
            ToastUtil.showToast(getString(com.shaoyue.weizhegou.R.string.toast_server_error));
            finish();
        } else if (resp.errCode == -2) {
            EventBus.getDefault().post(new PayEvent("1"));
            ToastUtil.showToast(getString(com.shaoyue.weizhegou.R.string.toast_cancel_payment));
            finish();
        }
    }


}