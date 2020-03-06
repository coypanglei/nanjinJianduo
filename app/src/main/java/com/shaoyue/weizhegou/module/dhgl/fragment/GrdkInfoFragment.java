package com.shaoyue.weizhegou.module.dhgl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.user.MainClickBean;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.MenuAdapter;
import com.shaoyue.weizhegou.module.credit.fragment.apply.CreditInquiryDetailsFragment;
import com.shaoyue.weizhegou.module.sxdc.fragment.BasicInformationTyFragment;
import com.shaoyue.weizhegou.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class GrdkInfoFragment extends BaseTitleFragment {

    @BindView(R.id.viewpager)
    NoScrollViewPager mViewpager;
    @BindView(R.id.tv_visible)
    TextView mTvVisible;
    @BindView(R.id.rv_menu)
    RecyclerView mRvMenu;

    private int currentPage = 0;
    private MenuAdapter menuAdapter;

    private List<Fragment> fragmentList = new ArrayList<>();

    private String type;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_credit_application;
    }

    public static GrdkInfoFragment newInstance(String mContentType) {
        SPUtils.getInstance().put("status", mContentType);
        Bundle args = new Bundle();
        args.putString("type", mContentType);
        GrdkInfoFragment fragment = new GrdkInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            type = getArguments().getString("type");
        }
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("个人经营性贷款贷后检查报告(50万元以上) - 基本信息").hideLeftButtonV2();


        final List<MainClickBean> mMenuList = new ArrayList<>();
        mMenuList.add(new MainClickBean("基本信息", false));
        mMenuList.add(new MainClickBean("征信查询", false));
        mMenuList.add(new MainClickBean("汇法网查询", false));
        mMenuList.add(new MainClickBean("影像资料", false));
        mMenuList.add(new MainClickBean("检查结果", false));


        for (int i = 0; i < mMenuList.size(); i++) {
           if ("征信查询".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(CreditInquiryDetailsFragment.newInstance());
            } else if ("影像资料".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(dyVideoDetailsFragment.newInstance("个贷检查"));
            } else if ("基本信息".equals(mMenuList.get(i).getTitle())) {
               fragmentList.add(BasicInformationTyFragment.newInstance("个贷基本信息"));
            } else if("检查结果".equals(mMenuList.get(i).getTitle())){
                fragmentList.add(BasicInformationTyFragment.newInstance("个贷检查结果"));
            }else if("汇法网查询".equals(mMenuList.get(i).getTitle())){
               fragmentList.add(BasicInformationTyFragment.newInstance("个贷汇法网查询"));
           }
        }
        mMenuList.get(0).setSelect(true);
        menuAdapter = new MenuAdapter();
        mRvMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvMenu.setAdapter(menuAdapter);
        menuAdapter.setNewData(mMenuList);
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final BaseQuickAdapter adapter, View view, final int position) {
                mRvMenu.setVisibility(View.GONE);
                mTvVisible.setText("显示菜单");
                if (currentPage != position) {
                    refresh(position, adapter);

                }
            }
        });
        mViewpager.setOffscreenPageLimit(0);

        mViewpager.setAdapter(new ContentPagerAdapter(getActivity().getSupportFragmentManager()));
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                refresh(i, menuAdapter);
                setCommonTitle("个人经营性贷款贷后检查报告(50万元以上) - "+mMenuList.get(i).getTitle()).hideLeftButtonV2();

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mViewpager.setCurrentItem(0);
        currentPage = 0;


    }

    /**
     * 刷新
     *
     * @param position
     * @param adapter
     */
    private void refresh(int position, BaseQuickAdapter adapter) {
        currentPage = position;
        mViewpager.setCurrentItem(position);
        List<MainClickBean> mainClickBeans = adapter.getData();
        for (MainClickBean mainClickBean : mainClickBeans) {
            mainClickBean.setSelect(false);
        }
        mainClickBeans.get(position).setSelect(true);
        adapter.setNewData(mainClickBeans);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_visible)
    public void onViewClicked() {
        if (mRvMenu.getVisibility() == View.GONE) {
            mRvMenu.setVisibility(View.VISIBLE);
            mTvVisible.setText("隐藏菜单");
        } else {
            mRvMenu.setVisibility(View.GONE);
            mTvVisible.setText("显示菜单");
        }
    }

    private class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
