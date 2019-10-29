package com.shaoyue.weizhegou.module.web;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.PayApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.PaySuccessEvent;
import com.shaoyue.weizhegou.entity.WebEntity;
import com.shaoyue.weizhegou.entity.WxPayEvent;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;


/**
 * 作者：HQY on 18/1/24 11:51
 * 邮箱：hqy_xz@126.com
 */

public class WxWebFragment extends BaseTitleFragment {


    @BindView(R.id.wv_url)
    WebView mWvUrl;

    private WebEntity webInfo;
    private boolean isLoadUrl = false;

    public static WxWebFragment newInstance(WebEntity web) {
        Bundle args = new Bundle();
        args.putSerializable(UIHelper.WEBVIEW, web);
        WxWebFragment fragment = new WxWebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isEmpty(getArguments())) {
            removeFragment();
        }
        webInfo = (WebEntity) getArguments().getSerializable(UIHelper.WEBVIEW);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_webview;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);

        setCommonTitle(webInfo.getTitle(), R.color.white)
                .setImgLeftListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mWvUrl != null && mWvUrl.canGoBack()) {
                            mWvUrl.goBack();
                        } else {
                            removeFragment();
                        }
                    }
                });

        showWebview();
        Map<String, String> extraHeaders = new HashMap<String, String>();

//        extraHeaders.put("Referer", "http://turtle.wapi.yuanzidian.com/");
        LogUtils.e(AppMgr.getInstance().getmSpAppClientapiurl());
        extraHeaders.put("Referer", AppMgr.getInstance().getmSpAppClientapiurl());


        mWvUrl.loadUrl(webInfo.getUrl(), extraHeaders);
    }

    private void showWebview() {
        WebSettings webSettings = mWvUrl.getSettings();
        webSettings.setJavaScriptEnabled(true); // 启用支持javascript
        webSettings.setAllowFileAccess(true); // 可以访问文件
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        String ua = webSettings.getUserAgentString();
        webSettings.setUserAgentString(ua + "(MDAPP)");
        // 先加载网页再加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            mWvUrl.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWvUrl.getSettings().setLoadsImagesAutomatically(false);
        }
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWvUrl.clearHistory();
        mWvUrl.clearFormData();
        mWvUrl.clearCache(true);
        mWvUrl.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("weixin://wap/pay?")) {
                    //如果return false  就会先提示找不到页面，然后跳转微信
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    UIHelper.showPayDialog(getActivity(), webInfo);
                    return true;
                }
                return false;
            }


        });
    }


    //
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PaySuccessEvent event) {
        if (event.getType().equals("1")) {
            getOrder();
        } else {
            getOrder();
        }
    }


    private void getOrder() {
        PayApi.getOrderSuccess(webInfo.getOrderId(), new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showSuccToast(getString(R.string.toast_payment_success));
                EventBus.getDefault().post(new WxPayEvent("1"));
                getActivity().finish();
            }

            @Override
            public void onFail(ApiException e) {
                UIHelper.showPayFialDialog(getActivity());
            }
        }, this);
    }


}
