package com.shaoyue.weizhegou.module.credit.fragment.diaocha;

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
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.MenuAdapter;
import com.shaoyue.weizhegou.module.credit.fragment.apply.CreditInquiryFragment;
import com.shaoyue.weizhegou.module.credit.fragment.apply.DiyadanbaoFragment;
import com.shaoyue.weizhegou.module.credit.fragment.apply.FamilyInfoFragment;
import com.shaoyue.weizhegou.module.credit.fragment.apply.VideoMaterialFragment;
import com.shaoyue.weizhegou.module.sxdc.fragment.BasicInformationDcFragment;
import com.shaoyue.weizhegou.module.sxdc.fragment.SxdcInfoDcFragment;
import com.shaoyue.weizhegou.module.sxdc.fragment.SxykhFragment;
import com.shaoyue.weizhegou.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class DcInfoFragment extends BaseTitleFragment {

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

    public static DcInfoFragment newInstance(String mContentType) {
        SPUtils.getInstance().put("status", mContentType);
        Bundle args = new Bundle();
        args.putString("type", mContentType);
        DcInfoFragment fragment = new DcInfoFragment();
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
        setCommonTitle("家庭信息").hideLeftButtonV2();
        type = SPUtils.getInstance().getString(UserMgr.SP_DC_TYPE);
        SPUtils.getInstance()
                .put(UserMgr.SP_XT_TYPE, "调查");
        final List<MainClickBean> mMenuList = new ArrayList<>();
        switch (type) {
            case "简化经营":
                mMenuList.add(new MainClickBean("家庭信息", true));
                mMenuList.add(new MainClickBean("基本信息", false));
                mMenuList.add(new MainClickBean("系统数据", false));
                mMenuList.add(new MainClickBean("征信查询", false));
                mMenuList.add(new MainClickBean("实地调查", false));
                mMenuList.add(new MainClickBean("担保抵押", false));
                mMenuList.add(new MainClickBean("现金流", false));
                mMenuList.add(new MainClickBean("影像资料", false));
                mMenuList.add(new MainClickBean("评级指标", false));
                mMenuList.add(new MainClickBean("授信结论", false));
                break;
            case "工薪类":
                mMenuList.add(new MainClickBean("家庭信息", true));
                mMenuList.add(new MainClickBean("基本信息", false));
                mMenuList.add(new MainClickBean("系统数据", false));
                mMenuList.add(new MainClickBean("征信查询", false));
                mMenuList.add(new MainClickBean("年收入情况", false));
                mMenuList.add(new MainClickBean("实地调查", false));
                mMenuList.add(new MainClickBean("资产负债", false));
                mMenuList.add(new MainClickBean("担保抵押", false));
                mMenuList.add(new MainClickBean("现金流", false));
                mMenuList.add(new MainClickBean("影像资料", false));
                mMenuList.add(new MainClickBean("评级指标", false));
                mMenuList.add(new MainClickBean("授信结论", false));

                break;
            case "经营":
                mMenuList.add(new MainClickBean("家庭信息", true));
                mMenuList.add(new MainClickBean("基本信息", false));
                mMenuList.add(new MainClickBean("系统数据", false));
                mMenuList.add(new MainClickBean("征信查询", false));
                mMenuList.add(new MainClickBean("实地调查", false));
                mMenuList.add(new MainClickBean("上下游客户", false));
                mMenuList.add(new MainClickBean("现金流", false));
                mMenuList.add(new MainClickBean("损益简表", false));
                mMenuList.add(new MainClickBean("财务简表", false));
                mMenuList.add(new MainClickBean("担保抵押", false));
                mMenuList.add(new MainClickBean("影像资料", false));
                mMenuList.add(new MainClickBean("评级指标", false));
                mMenuList.add(new MainClickBean("授信结论", false));
                break;
            case "农户":
                mMenuList.add(new MainClickBean("家庭信息", true));
                mMenuList.add(new MainClickBean("基本信息", false));
                mMenuList.add(new MainClickBean("系统数据", false));
                mMenuList.add(new MainClickBean("征信查询", false));
                mMenuList.add(new MainClickBean("实地调查", false));
                mMenuList.add(new MainClickBean("种植、养殖户", false));
                mMenuList.add(new MainClickBean("担保抵押", false));
                mMenuList.add(new MainClickBean("现金流", false));
                mMenuList.add(new MainClickBean("影像资料", false));
                mMenuList.add(new MainClickBean("评级指标", false));
                mMenuList.add(new MainClickBean("授信结论", false));
                break;
        }


        for (int i = 0; i < mMenuList.size(); i++) {
            if ("家庭信息".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(FamilyInfoFragment.newInstance("调查"));
            } else if ("征信查询".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(CreditInquiryFragment.newInstance());
            } else if ("影像资料".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(VideoMaterialFragment.newInstance());
            } else if ("基本信息".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(BasicInformationDcFragment.newInstance());
            } else if ("系统数据".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(MyDataDcFragment.newInstance());
            } else if ("担保抵押".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(DiyadanbaoFragment.newInstance());
            } else if ("现金流".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(DcMoneyLiuFragment.newInstance());
            } else if ("评级指标".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(DcPjzbFragment.newInstance());
            } else if ("实地调查".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(DcSddcFragment.newInstance());
            } else if ("种植、养殖户".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(DcZYFragment.newInstance());
            } else if ("资产负债".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(DcZCFZFragment.newInstance());
            } else if ("损益简表".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(DcSyjbFragment.newInstance());
            } else if ("财务简表".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(DcCwjbFragment.newInstance());
            } else if ("授信结论".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(SxdcInfoDcFragment.newInstance());
            } else if ("上下游客户".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(SxykhFragment.newInstance());
            } else if ("年收入情况".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(DcnsrFragment.newInstance());
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
                setCommonTitle(mMenuList.get(i).getTitle()).hideLeftButtonV2();

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
