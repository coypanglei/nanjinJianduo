package com.shaoyue.weizhegou.module.web;

import android.app.Activity;
import android.content.Intent;

import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.entity.WebEntity;
import com.shaoyue.weizhegou.router.UIHelper;


/**
 * Created by Administrator on 2017/5/28 0028.
 */

public class WxWebViewActivity extends BaseCommonActivity {


    public static Intent newInstance(Activity activity, WebEntity web){

        Intent intent=new Intent(activity, WxWebViewActivity.class);
        intent.putExtra(UIHelper.WEBVIEW,web);
        return intent;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return WxWebFragment.newInstance((WebEntity) getIntent().getSerializableExtra(UIHelper.WEBVIEW));
    }
}
