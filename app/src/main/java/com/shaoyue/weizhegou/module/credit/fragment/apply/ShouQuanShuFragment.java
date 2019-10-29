package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.blankj.utilcode.util.ObjectUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.QianziBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ShouQuanShuFragment extends BaseAppFragment {

    @BindView(R.id.tl_1)
    SlidingTabLayout mTl1;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;


    private List<String> mTitles = new ArrayList<>();

    private MyPagerAdapter myPagerAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public static ShouQuanShuFragment newInstance() {

        Bundle args = new Bundle();

        ShouQuanShuFragment fragment = new ShouQuanShuFragment();
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
        CeditApi.findFamilyInfo(new BaseCallback<BaseResponse<List<QianziBean>>>() {
            @Override
            public void onSucc(BaseResponse<List<QianziBean>> result) {
                mFragments.clear();
                List<QianziBean> mlist = result.data;
                if (ObjectUtils.isNotEmpty(mlist)) {
                    for (int i = 0; i < mlist.size(); i++) {
                        mFragments.add(QianZiBanFragment.newInstance(mlist.get(i)));
                    }
                    if (mlist.size() == 1) {
                        mTitles.add(" 申请人");
                    }
                    if (mlist.size() > 1) {
                        mTitles.add("申请人");
                        mTitles.add("申请人配偶");
                    }
                }
                myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
                mViewpager.setAdapter(myPagerAdapter);
                mTl1.setViewPager(mViewpager);
            }

            @Override
            public void onFail(ApiException apiError) {

            }
        }, this);


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
