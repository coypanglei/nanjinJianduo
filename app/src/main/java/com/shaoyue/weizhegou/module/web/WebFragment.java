package com.shaoyue.weizhegou.module.web;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.just.agentweb.AbsAgentWebSettings;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.IAgentWebSettings;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.manager.AppMgr;

import com.shaoyue.weizhegou.util.ToastUtil;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/31 10:50
 */
public class WebFragment extends BaseTitleFragment {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_WEB_URL = "extra_web_url";


    protected AgentWeb mAgentWeb;

    private String mTitle;
    private String mWebUrl;

    public static WebFragment newInstance(String title, String url) {
        WebFragment fragment = new WebFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TITLE, title);
        args.putString(EXTRA_WEB_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initBundle(Bundle bundle) {
        super.initBundle(bundle);
        if (getArguments() != null) {
            mTitle = getArguments().getString(EXTRA_TITLE);
            mWebUrl = getArguments().getString(EXTRA_WEB_URL);
        }
        if (TextUtils.isEmpty(mWebUrl)) {
            ToastUtil.showSuccToast(getString(R.string.t_urls_cannot_be_empty));
            removeFragment();
        }
//        LogUtils.e(mWebUrl);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_web;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle(mTitle)
                .setImgLeftListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mAgentWeb.back()) {
                            removeFragment();
                        }
                    }
                });

        LinearLayout web_content = (LinearLayout) rootView.findViewById(R.id.web_content);
        String payUrl = AppMgr.getInstance().getH5PayUrl();

        if (payUrl != null) {
            mAgentWeb = AgentWeb.with(this)
                    .setAgentWebParent((LinearLayout) web_content, -1,
                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT))
                    .useDefaultIndicator(getResources().getColor(R.color.color_5ebcb1), 2)
                    .setAgentWebWebSettings(new CustomSettings())
                    .setWebViewClient(mWebViewClient)
                    .createAgentWeb()
                    .ready()
                    .go(mWebUrl);


        }


    }


    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();

    }

    private class CustomSettings extends AbsAgentWebSettings {


        @Override
        protected void bindAgentWebSupport(AgentWeb agentWeb) {

        }

        @Override
        public IAgentWebSettings toSetting(WebView webView) {
            super.toSetting(webView);
            getWebSettings().setUseWideViewPort(true);
            return this;
        }
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work


        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            imgReset();
        }
    };


    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {

        mAgentWeb.getWebCreator().getWebView().loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }

}
