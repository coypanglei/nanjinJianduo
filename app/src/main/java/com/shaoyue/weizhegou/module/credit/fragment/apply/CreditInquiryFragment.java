package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.blankj.utilcode.util.SPUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.shaoyue.weizhegou.R;

import com.shaoyue.weizhegou.base.BaseAppFragment;

import com.shaoyue.weizhegou.manager.UserMgr;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CreditInquiryFragment extends BaseAppFragment {

    @BindView(R.id.tl_1)
    SlidingTabLayout mTl1;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;


    private List<String> mTitles = new ArrayList<>();

    private MyPagerAdapter myPagerAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public static CreditInquiryFragment newInstance() {

        Bundle args = new Bundle();

        CreditInquiryFragment fragment = new CreditInquiryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_power_of_attorney;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);


    }

    @Override
    public void onResume() {
        super.onResume();
        mTitles.clear();
        int size = 1;
        if (SPUtils.getInstance().getBoolean(UserMgr.SP_IS_PO)) {
            size = 2;
        }
        mFragments.clear();
        for (int i = 0; i < size; i++) {
            mFragments.add(CreditInquiryDetailsFragment.newInstance());
        }
        if (size == 1) {
            mTitles.add("申请人征信数据");
        }
        if (size > 1) {
            mTitles.add("申请人征信数据");
            mTitles.add("配偶征信数据");
        }

        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mViewpager.setAdapter(myPagerAdapter);
        mTl1.setViewPager(mViewpager);

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
