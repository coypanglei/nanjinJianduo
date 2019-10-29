package com.shaoyue.weizhegou.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import butterknife.ButterKnife;
import me.jessyan.autosize.AutoSizeConfig;

/**
 * Created by librabin on 16/11/14.
 */

public abstract class BaseFragment extends Fragment {

    protected View mRootView;

    protected Bundle mBundle;

    protected BaseActivity mActivity;

    private static final long SHOW_SPACE = 200L;

    public static int ANIMA_TYPE_NONE = 0; // 无动画
    public static  int ANIMA_TYPE_DEFAULT = 1; // 默认左进右出
    public static  int ADIMA_TYPE_BOTTOM_IN_BOTTOM_OUT = 2; // 底部进底部出



    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }  else {
            throw new RuntimeException(context.toString() + "must extends BaseActivity!");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        initBundle(mBundle);
        AutoSizeConfig.getInstance().setCustomFragment(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        } else {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            onBindViewBefore(mRootView);
            ButterKnife.bind(this, mRootView);
            if (savedInstanceState != null) {
                onRestartInstance(savedInstanceState);
            }
            initView(mRootView);
            loadData();
        }
        return mRootView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unSubscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBundle = null;
    }

    protected void initBundle(Bundle bundle) {

    }

    protected abstract int getLayoutId();

    protected void initView(View rootView) {

    }

    protected void loadData() {

    }

    protected void onBindViewBefore(View rootView) {

    }

    protected void onRestartInstance(Bundle bundle) {

    }











}
