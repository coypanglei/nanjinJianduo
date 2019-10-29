package com.shaoyue.weizhegou.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.widget.CommonTopBar;


/**
 * Created by librabin on 16/11/15.
 */

public abstract class BaseTitleFragment extends BaseAppFragment {

    private CommonTopBar mTitlebar;

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true)
                .statusBarColor(R.color.black)
//                .statusBarDarkFont(false)
                .titleBar(mTitlebar)
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_title;
    }

    @Override
    protected void onBindViewBefore(View rootView) {
        super.onBindViewBefore(rootView);
        ViewStub stub = (ViewStub) rootView.findViewById(R.id.layout_content);
        stub.setLayoutResource(getContentLayoutId());
        stub.inflate();
    }


    protected abstract
    @LayoutRes
    int getContentLayoutId();

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mTitlebar = (CommonTopBar) rootView.findViewById(R.id.top_bar);
    }


    public BaseTitleFragment setTitleText(String title) {
        if (mTitlebar != null) {
            mTitlebar.setTextCenter(title);
        }

        return this;
    }

    public BaseTitleFragment setCommonTitle(String titleStr) {
        return setCommonTitle(titleStr, R.color.white);
    }


    public BaseTitleFragment setCommonTitle(@StringRes int titleId) {
        return setCommonTitle(getResources().getString(titleId), R.color.white);
    }

    public BaseTitleFragment setCommonTitle(@StringRes int titleId, @ColorRes int titleColorId) {
        return setCommonTitle(getResources().getString(titleId), R.color.white);
    }

    public BaseTitleFragment setCommonTitle(String titleStr, @ColorRes int titleColorId) {
        return setTitle(titleStr, R.color.white)
                .setTitleBgColor(titleColorId)
                .setImgLeftListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeFragment();
                    }
                });
    }

    public BaseTitleFragment setTitle(@StringRes int titleId, @ColorRes int titleColorId) {
        return setTitle(getResources().getString(titleId), titleColorId);
    }


    public BaseTitleFragment setTitle(String titleText, @ColorRes int titleColorId) {
        if (mTitlebar != null) {
            TextView titleField = (TextView) mTitlebar.findViewById(R.id.tv_title_center);
            titleField.setText(titleText);
            titleField.setTextColor(getResources().getColor(titleColorId));
            mTitlebar.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public BaseTitleFragment withoutReturn() {
        if (mTitlebar != null) {
            mTitlebar.setShowImgLeft(false);
        }
        return this;
    }


    public BaseTitleFragment setTitleText(@StringRes int titleId) {
        if (mTitlebar != null) {
            mTitlebar.setTextCenter(getResources().getString(titleId));
        }
        return this;
    }

    public BaseTitleFragment setTitleColor(@ColorRes int titleColorId) {
        if (mTitlebar != null) {
            TextView titleText = (TextView) mTitlebar.findViewById(R.id.tv_title_center);
            titleText.setTextColor(getResources().getColor(titleColorId));
        }
        return this;
    }

    public BaseTitleFragment setTitleBgColor(@ColorRes int bgColorId) {
        if (mTitlebar != null) {
//            ImageView bgView = (ImageView) mTitlebar.findViewById(R.id.iv_title_bg);
//            if (bgView != null) {
//                if (bgColorId == 0) {
//                    bgView.setBackgroundColor(getResources().getColor(R.color.white));
//                } else {
//                    bgView.setBackgroundColor(getResources().getColor(bgColorId));
//                }
//            }
//            mTitlebar.setBackgroundColor(getResources().getColor(bgColorId));
        }

        return this;
    }


    public BaseTitleFragment setImgLeftBg(@DrawableRes int id) {
        if (mTitlebar != null) {
            mTitlebar.setImgLeft(id);
            mTitlebar.setShowImgLeft(true);
        }

        return this;
    }

    public BaseTitleFragment setRightBtn(String rightTextStr, View.OnClickListener clickListener) {
        if (mTitlebar != null) {
            mTitlebar.setShowRight(true);
            FrameLayout rigthLayout = (FrameLayout) mTitlebar.findViewById(R.id.fl_title_right);
            rigthLayout.setOnClickListener(clickListener);
            TextView rightText = (TextView) mTitlebar.findViewById(R.id.tv_title_right);
            rightText.setVisibility(View.VISIBLE);
            rightText.setText(rightTextStr);
        }


        return this;
    }


    public BaseTitleFragment setRightBtn(View.OnClickListener clickListener) {
        if (mTitlebar != null) {
            mTitlebar.setShowRight(true);
            FrameLayout rigthLayout = (FrameLayout) mTitlebar.findViewById(R.id.fl_title_right);
            rigthLayout.setOnClickListener(clickListener);

        }
        return this;
    }

    public BaseTitleFragment setRightBtnV2(String text) {
        if (mTitlebar != null) {
            mTitlebar.setShowRight(true);
            TextView rightText = (TextView) mTitlebar.findViewById(R.id.tv_title_right);
            rightText.setVisibility(View.VISIBLE);
            rightText.setText(text);
        }
        return this;
    }

    public BaseTitleFragment setRightBtnV3(int resId, View.OnClickListener clickListener) {
        if (mTitlebar != null) {
            mTitlebar.setShowRight(true);
            FrameLayout rigthLayout = (FrameLayout) mTitlebar.findViewById(R.id.fl_title_right);
            rigthLayout.setOnClickListener(clickListener);
            ImageView rightIv = (ImageView) mTitlebar.findViewById(R.id.iv_title_right);
            rightIv.setVisibility(View.VISIBLE);
            rightIv.setImageResource(resId);

        }
        return this;
    }

    /**
     * 设置右边textview 的drawable属性
     *
     * @param drawableId
     */
    public BaseTitleFragment setDrawableLeft(int drawableId) {
        TextView mTxtRight = (TextView) mTitlebar.findViewById(R.id.tv_title_right);
        Drawable drawable = getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTxtRight.setCompoundDrawables(drawable, null, null, null);
        return this;
    }


    /**
     * 设置左边图片 监听器
     */
    public BaseTitleFragment setImgLeftListener(View.OnClickListener listener) {
        if (mTitlebar != null) {
            FrameLayout leftLayout = (FrameLayout) mTitlebar.findViewById(R.id.fl_title_left);
            leftLayout.setOnClickListener(listener);
        }

        return this;
    }

    public BaseTitleFragment hideLeftButtonV2() {
        if (mTitlebar != null) {
            FrameLayout leftLayout = (FrameLayout) mTitlebar.findViewById(R.id.fl_title_left);
            leftLayout.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    public BaseTitleFragment hideTitle() {
        if (mTitlebar != null) {
            mTitlebar.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    public BaseTitleFragment hideLeftButton() {
        if (mTitlebar != null) {
            mTitlebar.setShowImgLeft(false);
        }

        return this;
    }


}
