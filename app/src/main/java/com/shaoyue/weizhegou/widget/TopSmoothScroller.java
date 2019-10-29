package com.shaoyue.weizhegou.widget;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;

/**
 * 作者：PangLei on 2019/4/1 0001 10:22
 * <p>
 * 邮箱：434604925@qq.com
 */
public class TopSmoothScroller extends LinearSmoothScroller {
    public TopSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }
}
