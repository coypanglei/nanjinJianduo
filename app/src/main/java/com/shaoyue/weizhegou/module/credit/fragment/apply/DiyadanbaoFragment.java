package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.module.credit.fragment.apply.diyao.DiyadanbaoDetailsFourFragment;
import com.shaoyue.weizhegou.module.credit.fragment.apply.diyao.DiyadanbaoDetailsOneFragment;
import com.shaoyue.weizhegou.module.credit.fragment.apply.diyao.DiyadanbaoDetailsThreeFragment;
import com.shaoyue.weizhegou.module.credit.fragment.apply.diyao.DiyadanbaoDetailsTwoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class DiyadanbaoFragment extends BaseAppFragment {

    @BindView(R.id.tl_1)
    SlidingTabLayout mTl1;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;


    private List<String> mTitles = new ArrayList<>();

    private MyPagerAdapter myPagerAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public static DiyadanbaoFragment newInstance() {

        Bundle args = new Bundle();
        DiyadanbaoFragment fragment = new DiyadanbaoFragment();
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
        mTitles.add("自然人担保分析");
        mTitles.add("抵(质)押分析");
        mTitles.add("公司担保分析");
        mTitles.add("企业担保分析");
        mFragments.add(DiyadanbaoDetailsOneFragment.newInstance());
        mFragments.add(DiyadanbaoDetailsTwoFragment.newInstance());
        mFragments.add(DiyadanbaoDetailsThreeFragment.newInstance());
        mFragments.add(DiyadanbaoDetailsFourFragment.newInstance());
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
