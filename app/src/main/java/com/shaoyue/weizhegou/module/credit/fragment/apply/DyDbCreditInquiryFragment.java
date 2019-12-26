package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.cedit.QianziBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class DyDbCreditInquiryFragment extends BaseTitleFragment {

    @BindView(R.id.tl_1)
    SlidingTabLayout mTl1;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;


    private List<String> mTitles = new ArrayList<>();
    private List<QianziBean> mlist = new ArrayList<>();
    private MyPagerAdapter myPagerAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String id;

    public static DyDbCreditInquiryFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id",id);
        DyDbCreditInquiryFragment fragment = new DyDbCreditInquiryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_power_of_attorney;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);

        setCommonTitle("担保人征信授权");

        CeditApi.findDbFamilyInfo(id, new BaseCallback<BaseResponse<List<QianziBean>>>() {
            @Override
            public void onSucc(BaseResponse<List<QianziBean>> result) {
                mFragments.clear();
                mTitles.clear();
                mlist = result.data;
                if (ObjectUtils.isNotEmpty(mlist)) {
                    for (int i = 0; i < mlist.size(); i++) {
                        if (i == 0) {
                            mTitles.add("申请人征信数据");
                            mFragments.add(CreditInquiryDetailsFragmentThree.newInstance(mTitles.get(i),id));
                        }

                        if (i == 1) {
                            mTitles.add("配偶征信数据");
                            mFragments.add(CreditInquiryDetailsFragmentThree.newInstance(mTitles.get(i),id));
                        }

                    }
                    myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
                    mViewpager.setAdapter(myPagerAdapter);
                    mTl1.setViewPager(mViewpager);
                }
            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
            }
        }, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            id = getArguments().getString("id");
        }
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
