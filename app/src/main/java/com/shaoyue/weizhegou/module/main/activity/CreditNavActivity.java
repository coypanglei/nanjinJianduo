package com.shaoyue.weizhegou.module.main.activity;

import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseAppActivity;
import com.shaoyue.weizhegou.module.credit.fragment.FullCreditProcessFragment;

import java.util.ArrayList;

import butterknife.BindView;


public class CreditNavActivity extends BaseAppActivity {


    @BindView(R.id.tl_1)
    SlidingTabLayout mTl1;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private final String[] mTitles = {
            "授信全流程", "年检"
    };

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_navigation_credit;
    }


    @Override
    protected void initView() {
        super.initView();
        mFragments.add(FullCreditProcessFragment.newInstance());
        mFragments.add(FullCreditProcessFragment.newInstance());
        mTl1.setViewPager(mViewpager, mTitles, this, mFragments);
    }



}
