package com.shaoyue.weizhegou.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 作者：HQY on 18/3/19 10:06
 * 邮箱：hqy_xz@126.com
 */

public class MyScrollView extends NestedScrollView {
    private OnScrollListener mListener;
    /**
     * ScrollView正在向上滑动
     */
    public static final int SCROLL_UP = 0x01;
    /**
     * ScrollView正在向下滑动
     */
    public static final int SCROLL_DOWN = 0x10;
    /**
     * 最小的滑动距离
     */
    private static final int SCROLLLIMIT = 5;

    public void setOnScrollListener(OnScrollListener listener) {
        this.mListener = listener;
    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnScrollListener {
        void onScroll(int scrollY);



    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener != null) {
            mListener.onScroll(t);
        }

    }
}
