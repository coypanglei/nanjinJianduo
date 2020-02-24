package com.shaoyue.weizhegou.module.main.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseAppActivity;
import com.shaoyue.weizhegou.module.credit.fragment.FullCreditProcessFragment;
import com.shaoyue.weizhegou.module.main.fragment.DhglNavFragment;

import java.util.ArrayList;

import butterknife.BindView;


public class DhglNewNavActivity extends BaseAppActivity {


    @BindView(R.id.tl_1)
    SlidingTabLayout mTl1;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.rl_1)
    RelativeLayout rlTitle;
    private final String[] mTitles = {
            "常检", "首检", "季检"
    };
    private final String[] mArgs = {
            "cj", "sj", "dhgl"
    };

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_navigation_credit;
    }


    @Override
    protected void initView() {
        super.initView();
        rlTitle.setVisibility(View.VISIBLE);
        for (int i = 0; i < mArgs.length; i++) {
            mFragments.add(DhglNavFragment.newInstance(mArgs[i]));
        }
        mTl1.setViewPager(mViewpager, mTitles, this, mFragments);
    }


}
