package com.shaoyue.weizhegou.module.credit.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.entity.user.MainClickBean;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.MenuAdapter;
import com.shaoyue.weizhegou.module.dhgl.fragment.SjcjFragment;
import com.shaoyue.weizhegou.module.dhgl.fragment.SpFragment;
import com.shaoyue.weizhegou.module.dhgl.fragment.XcjyFragment;
import com.shaoyue.weizhegou.module.dhgl.fragment.XzRlFragment;
import com.shaoyue.weizhegou.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class CreditApplicationFragment extends BaseTitleFragment {

    @BindView(R.id.viewpager)
    NoScrollViewPager mViewpager;
    @BindView(R.id.tv_visible)
    TextView mTvVisible;
    @BindView(R.id.rv_menu)
    RecyclerView mRvMenu;


    private int currentPage = 0;
    private MenuAdapter menuAdapter;
    private profileBean profileBean;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_credit_application;
    }

    public static CreditApplicationFragment newInstance(profileBean profileBean) {

        Bundle args = new Bundle();
        args.putSerializable("profileBean", profileBean);
        CreditApplicationFragment fragment = new CreditApplicationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            profileBean = (profileBean) getArguments().getSerializable("profileBean");
        } else {
            removeFragment();
        }
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        hideLeftButtonV2();
        final List<MainClickBean> mMenuList = profileBean.getMainClickBeans();
        for (int i = 0; i < mMenuList.size(); i++) {
            if ("授信申请".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(shouXinShenQingFragment.newInstance(mMenuList.get(i).getTitle()));
            } else if ("授信调查".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(shouXinSurveyFragment.newInstance(mMenuList.get(i).getTitle()));
            } else if ("授信审批".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(shouXinShenQingFragment.newInstance(mMenuList.get(i).getTitle()));
            } else if ("待办事项".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(shouXinShenQingFragment.newInstance(mMenuList.get(i).getTitle()));
            } else if ("现场检验".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(XcjyFragment.newInstance(mMenuList.get(i).getTitle()));
            } else if ("数据采集".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(SjcjFragment.newInstance(mMenuList.get(i).getTitle()));
            } else if ("小组任务认领".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(XzRlFragment.newInstance(mMenuList.get(i).getTitle()));
            } else if ("审批".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(SpFragment.newInstance(mMenuList.get(i).getTitle()));
            }
        }
        mMenuList.get(profileBean.getType()).setSelect(true);
        menuAdapter = new MenuAdapter();
        mRvMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvMenu.setAdapter(menuAdapter);
        menuAdapter.setNewData(mMenuList);
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (currentPage != position) {
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
            }
        });

        mViewpager.setOffscreenPageLimit(0);

        mViewpager.setAdapter(new ContentPagerAdapter(getActivity().getSupportFragmentManager()));
        setCommonTitle(mMenuList.get(0).getTitle());
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                refresh(i, menuAdapter);
                setCommonTitle(mMenuList.get(i).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mViewpager.setCurrentItem(profileBean.getType());
        currentPage = profileBean.getType();


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




    @OnClick({R.id.viewpager, R.id.tv_visible})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.viewpager:
//
                break;
            case R.id.tv_visible:
                if (mRvMenu.getVisibility() == View.GONE) {
                    mRvMenu.setVisibility(View.VISIBLE);
                    mTvVisible.setText("隐藏菜单");
                } else {
                    mRvMenu.setVisibility(View.GONE);
                    mTvVisible.setText("显示菜单");
                }
                break;
        }
    }

    private class ContentPagerAdapter extends FragmentStatePagerAdapter {

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
