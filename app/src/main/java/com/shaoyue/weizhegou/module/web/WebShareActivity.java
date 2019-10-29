package com.shaoyue.weizhegou.module.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.umeng.socialize.UMShareAPI;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/31 10:41
 */
public class WebShareActivity extends BaseCommonActivity {

    public static void showShareWebPage(Context context, String title, String url, String iconUrl) {
        Intent intent = new Intent(context, WebShareActivity.class);
        intent.putExtra(WebNewShareFragment.EXTRA_TITLE, title);
        intent.putExtra(WebNewShareFragment.EXTRA_WEB_URL, url);
        intent.putExtra(WebNewShareFragment.EXTRA_ICON_URL, iconUrl);
        context.startActivity(intent);
    }

    private String mTitle;
    private String mUrl;
    private String miconUrl;

    @Override
    protected boolean initBundle(Bundle bundle) {
        mTitle = bundle.getString(WebNewShareFragment.EXTRA_TITLE);
        mUrl = bundle.getString(WebNewShareFragment.EXTRA_WEB_URL);
        miconUrl = bundle.getString(WebNewShareFragment.EXTRA_ICON_URL);
        return true;
    }


    @Override
    protected BaseFragment getFirstFragment() {

        return WebNewShareFragment.newInstance(mTitle, mUrl,miconUrl);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
