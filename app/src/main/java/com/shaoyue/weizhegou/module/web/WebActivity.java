package com.shaoyue.weizhegou.module.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;


/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/31 10:41
 */
public class WebActivity extends BaseCommonActivity {

    public static void showWebPage(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WebFragment.EXTRA_TITLE, title);
        intent.putExtra(WebFragment.EXTRA_WEB_URL, url);
        context.startActivity(intent);
    }

    private String mTitle;
    private String mUrl;

    @Override
    protected boolean initBundle(Bundle bundle) {
        mTitle = bundle.getString(WebFragment.EXTRA_TITLE);
        mUrl = bundle.getString(WebFragment.EXTRA_WEB_URL);
        return true;
    }


    @Override
    protected BaseFragment getFirstFragment() {
        if (mUrl.contains("png")||mTitle.contains(getString(R.string.t_user_service_protocol))) {
            LogUtils.e(mUrl);
            return WebNewFragment.newInstance(mTitle, mUrl);
        } else {
            return WebFragment.newInstance(mTitle, mUrl);
        }
    }



}
