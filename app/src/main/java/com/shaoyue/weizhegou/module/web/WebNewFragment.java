package com.shaoyue.weizhegou.module.web;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.base.BaseTitleFragment;


import butterknife.BindView;

/**
 * Created by bin on 17/7/6.
 */

public class WebNewFragment extends BaseTitleFragment {

    public static final String EXTRA_TITLE = "extra_title";

    public static final String EXTRA_WEB_URL = "extra_web_url";
    @BindView(com.shaoyue.weizhegou.R.id.webView)
    WebView mWebView;
    @BindView(com.shaoyue.weizhegou.R.id.progress_bar)
    ProgressBar mProgressBar;



    private String mTitle;

    private String mWebUrl;


    public static WebNewFragment newInstance(String title, String url) {
        WebNewFragment fragment = new WebNewFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TITLE, title);
        args.putString(EXTRA_WEB_URL, url);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mWebView != null) {
            mWebView.destroy();
        }

    }


    @Override
    protected void initBundle(Bundle bundle) {
        super.initBundle(bundle);
        if (getArguments() != null) {
            mTitle = getArguments().getString(EXTRA_TITLE);
            mWebUrl = getArguments().getString(EXTRA_WEB_URL);
        }

        if (TextUtils.isEmpty(mWebUrl)) {
            removeFragment();
        }

    }


    @Override
    protected int getContentLayoutId() {
        return com.shaoyue.weizhegou.R.layout.fragment_new_web;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        if (!ObjectUtils.isEmpty(mTitle)) {
            setCommonTitle(mTitle).setImgLeftListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mWebView != null && mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        removeFragment();
                    }
                }
            });
        }

        initWebView();

        mWebView.loadUrl(mWebUrl);
    }


    private void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 启用支持javascript
        webSettings.setAllowFileAccess(true); // 可以访问文件
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        String ua = webSettings.getUserAgentString();
        webSettings.setUserAgentString(ua + "(MDAPP)");
        // 先加载网页再加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWebView.getSettings().setLoadsImagesAutomatically(false);
        }

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);

//                if (ObjectUtils.isNotEmpty(view.getTitle()) && !view.getTitle().equals("Title")) {
//                    setTitleText(view.getTitle());
//                }
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress > 90) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
                        if (mWebView != null && mWebView.canGoBack()) {
                            mWebView.goBack();
                        } else {
                            removeFragment();
                        }
                        return true;
                    }
                }
                return false;
            }
        });


    }


}
