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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class VideoMaterialFragment extends BaseAppFragment {

    @BindView(R.id.tl_1)
    SlidingTabLayout mTl1;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;


    private List<String> mTitles = new ArrayList<>();

    private MyPagerAdapter myPagerAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public static VideoMaterialFragment newInstance() {

        Bundle args = new Bundle();

        VideoMaterialFragment fragment = new VideoMaterialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_material;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mTitles.add("基础证件");
        mTitles.add("授信材料");
        mTitles.add("经营相关材料");
        mTitles.add("担保抵押");
        mTitles.add("授信调查审批");
        for (int i = 0; i < mTitles.size(); i++) {
            if (mTitles.get(i).equals("经营相关材料")) {
                mFragments.add(VideoJinyingFragment.newInstance(mTitles.get(i)));
            } else {
                mFragments.add(VideoDetailsFragment.newInstance(mTitles.get(i)));
            }

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
