package com.shaoyue.weizhegou.module.sxdc.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseAppFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class SxykhFragment extends BaseAppFragment {

    @BindView(R.id.tl_1)
    SlidingTabLayout mTl1;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;


    private List<String> mTitles = new ArrayList<>();

    private MyPagerAdapter myPagerAdapter;

    private ArrayList<android.support.v4.app.Fragment> mFragments = new ArrayList<>();

    public static SxykhFragment newInstance() {

        Bundle args = new Bundle();
        SxykhFragment fragment = new SxykhFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_power_of_attorney_sxy;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mTitles.add("上游供应商");
        mTitles.add("下游客户");
        mFragments.add(SxykhOneFragment.newInstance(mTitles.get(0)));
        mFragments.add(SxykhOneFragment.newInstance(mTitles.get(1)));
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
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
