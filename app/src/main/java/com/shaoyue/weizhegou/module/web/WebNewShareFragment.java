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

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AccountApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import butterknife.BindView;

/**
 * Created by bin on 17/7/6.
 */

public class WebNewShareFragment extends BaseTitleFragment {

    public static final String EXTRA_TITLE = "extra_title";

    public static final String EXTRA_WEB_URL = "extra_web_url";
    public static final String EXTRA_ICON_URL = "extra_icon_url";
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;


    private String mTitle;

    private String mWebUrl;

    private String mIconUrl;

    public static WebNewShareFragment newInstance(String title, String url, String iconUrl) {
        WebNewShareFragment fragment = new WebNewShareFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TITLE, title);
        args.putString(EXTRA_WEB_URL, url);
        args.putString(EXTRA_ICON_URL, iconUrl);
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
            mIconUrl = getArguments().getString(EXTRA_ICON_URL);
        }

        if (TextUtils.isEmpty(mWebUrl)) {
            removeFragment();
        }

    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_new_web;
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
            }).setRightBtnV3(R.drawable.share_btn_select, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShareBoardConfig config = new ShareBoardConfig();//新建ShareBoardConfig               config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);//设置位置
                    config.setIndicatorVisibility(false);
                    UMImage image = new UMImage(getActivity(), mIconUrl);
                    new ShareAction(getActivity()).withMedia(image).setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE)
                            .setCallback(shareListener).open(config);
                }
            });
        }

        initWebView();

        mWebView.loadUrl(mWebUrl);
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            LogUtils.e(platform.getName());
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {

            AccountApi.shareTime(new BaseCallback<BaseResponse<Void>>() {
                @Override
                public void onSucc(BaseResponse<Void> result) {
                    ToastUtil.showSuccToast(getString(R.string.t_share_success));
                }
            }, this);
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtil.showToast(getString(R.string.t_sharing_failure));
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            LogUtils.e("error");
//            LogUtils.e(platform.getName());

        }
    };

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
