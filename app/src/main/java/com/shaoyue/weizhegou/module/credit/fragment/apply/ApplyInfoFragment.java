package com.shaoyue.weizhegou.module.credit.fragment.apply;

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

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.cedit.FaceBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.entity.user.MainClickBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.MenuAdapter;
import com.shaoyue.weizhegou.widget.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


public class ApplyInfoFragment extends BaseTitleFragment {

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

    public static ApplyInfoFragment newInstance(String mContentType) {
        SPUtils.getInstance().put("status", mContentType);
        Bundle args = new Bundle();
        args.putString("type", mContentType);
        ApplyInfoFragment fragment = new ApplyInfoFragment();
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
        SPUtils.getInstance()
                .put(UserMgr.SP_XT_TYPE, "申请");

        final List<MainClickBean> mMenuList = new ArrayList<>();
        mMenuList.add(new MainClickBean("家庭信息", true));
        mMenuList.add(new MainClickBean("人脸识别", false));
        mMenuList.add(new MainClickBean("征信授权书", false));
        mMenuList.add(new MainClickBean("征信查询", false));
        mMenuList.add(new MainClickBean("影像资料", false));
        mMenuList.add(new MainClickBean("基本信息", false));
        mMenuList.add(new MainClickBean("我行数据", false));
        mMenuList.add(new MainClickBean("担保抵押", false));
        mMenuList.add(new MainClickBean("初审结果", false));


        for (int i = 0; i < mMenuList.size(); i++) {
            if ("家庭信息".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(FamilyInfoFragment.newInstance("申请"));
            } else if ("人脸识别".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(FaceRecognitionFragment.newInstance());
            } else if ("征信授权书".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(ShouQuanShuFragment.newInstance());
            } else if ("征信查询".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(CreditInquiryFragment.newInstance());
            } else if ("影像资料".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(VideoMaterialFragment.newInstance());
            } else if ("基本信息".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(BasicInformationFragment.newInstance());
            } else if ("我行数据".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(MyDataFragment.newInstance());
            } else if ("担保抵押".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(DiyadanbaoFragment.newInstance());
            } else if ("初审结果".equals(mMenuList.get(i).getTitle())) {
                fragmentList.add(ChuShenFragment.newInstance());
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
                    EventBus.getDefault().post(new RefreshBean());
                    LogUtils.e(position);
                    if (position != 0) {
                        if (position == 1) {
                            CeditApi.findFaceInfo(new BaseCallback<BaseResponse<List<FaceBean>>>() {
                                @Override
                                public void onSucc(BaseResponse<List<FaceBean>> result) {
                                    refresh(position, adapter);
                                }

                                @Override
                                public void onFail(ApiException apiError) {
                                    super.onFail(apiError);
                                    mViewpager.setCurrentItem(0);
                                }
                            }, this);
                        } else {
                            Map<String, String> params = new HashMap<>();
                            CeditApi.putRljy(params, new BaseCallback<BaseResponse<Void>>() {
                                @Override
                                public void onSucc(BaseResponse<Void> result) {
                                    refresh(position, adapter);
                                }

                                @Override
                                public void onFail(ApiException apiError) {
                                    super.onFail(apiError);
                                    if (apiError.getErrMsg().contains("人脸识别")) {
                                        mViewpager.setCurrentItem(1);
                                    } else {
                                        mViewpager.setCurrentItem(0);
                                    }
                                }
                            }, this);
                        }
                    }else {
                        refresh(position, adapter);
                    }

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
