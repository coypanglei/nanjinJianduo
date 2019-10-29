package com.shaoyue.weizhegou.module.account.dialog;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.util.WebViewUtils;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：HQY on 17/7/17 14:54
 * 邮箱：hqy_xz@126.com
 */

public class VerifiedDialogFragment extends DialogFragment {

    @BindView(R.id.tv_title)
    TextView  mTvTitle;
    @BindView(R.id.html_text)
    HtmlTextView mTvContent;
    Unbinder unbinder;


    private String title = "";
    private String content = "";

    public static VerifiedDialogFragment newInstance(String title, String content) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("content", content);
        VerifiedDialogFragment fragment = new VerifiedDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ObjectUtils.isNotEmpty(getArguments())) {
            title = getArguments().getString("title");
            content = getArguments().getString("content");
        } else {
            dismiss();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_wave_file, null);
        unbinder = ButterKnife.bind(this, view);
        mTvTitle.setText(title);
        mTvContent.setHtml(content,
                new HtmlResImageGetter(mTvContent));



        LogUtils.e(WebViewUtils.getNewContent(content));
//        mWebview.loadDataWithBaseURL("http://webhost.net", WebViewUtils.getNewContent(content), "text/html", "UTF-8", null);
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;
        dialog.setContentView(view);

        dialog.setCanceledOnTouchOutside(true);
        ButterKnife.bind(this, view);


//        setInfo();
        return dialog;
    }

//    private void initWebView() {
//        WebSettings webSettings = mWebview.getSettings();
//        webSettings.setJavaScriptEnabled(true); // 启用支持javascript
//        webSettings.setAllowFileAccess(true); // 可以访问文件
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
//        webSettings.setTextZoom(200);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }
//        String ua = webSettings.getUserAgentString();
//        webSettings.setUserAgentString(ua + "(MDAPP)");
//        // 先加载网页再加载图片
//        if (Build.VERSION.SDK_INT >= 19) {
//            mWebview.getSettings().setLoadsImagesAutomatically(true);
//        } else {
//            mWebview.getSettings().setLoadsImagesAutomatically(false);
//        }
//
//        mWebview.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // 得到 URL 可以传给应用中的某个 WebView 页面加载显示
//                return true;
//            }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_close)
    public void onViewClicked() {
        dismiss();
    }
}
