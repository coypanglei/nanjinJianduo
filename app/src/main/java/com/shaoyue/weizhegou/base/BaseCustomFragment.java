package com.shaoyue.weizhegou.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.shaoyue.weizhegou.R;

public abstract class BaseCustomFragment extends BaseAppFragment {


    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(false, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN).statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)


//                .fitsSystemWindows(true)
//                .transparentBar()
//                .statusBarColorInt(getResources().getColor(R.color.default_theme_color))
                .init();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initImmersionBar();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    protected ImmersionBar mImmersionBar;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
            mImmersionBar.init();
    }


}
