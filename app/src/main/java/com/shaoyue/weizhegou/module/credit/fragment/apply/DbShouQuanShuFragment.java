package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.cedit.OcrBean;
import com.shaoyue.weizhegou.entity.cedit.QianziBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.wildma.pictureselector.PictureSelector;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.shaoyue.weizhegou.util.ObjectToMapUtils.str2Map;


public class DbShouQuanShuFragment extends BaseTitleFragment {

    @BindView(R.id.tl_1)
    SlidingTabLayout mTl1;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;


    private List<String> mTitles = new ArrayList<>();

    private MyPagerAdapter myPagerAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @BindView(R.id.sb_edit)
    StateButton sbEdit;
    @BindView(R.id.sb_sc)
    StateButton sbSc;
    private List<QianziBean> mlist = new ArrayList<>();
    private String id;

    public static DbShouQuanShuFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id", id);
        DbShouQuanShuFragment fragment = new DbShouQuanShuFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentLayoutId() {

        return R.layout.fragment_power_of_attorney;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            id = getArguments().getString("id");
        }
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
                            mTitles.add("申请人");


                            if (ObjectUtils.isNotEmpty(mlist.get(0).getId()) || "查看详情".equals(SPUtils.getInstance().getString("status"))) {
                                sbEdit.setVisibility(View.GONE);
                                sbSc.setVisibility(View.GONE);
                            } else {
                                sbEdit.setVisibility(View.VISIBLE);
                                sbSc.setVisibility(View.VISIBLE);
                            }
                            qianziBean = mlist.get(0);
                            mFragments.add(QianZiBanFragment.newInstance(mlist.get(i), 2));
                        }
                        if (i == 1) {
                            qianziBeanTwo = mlist.get(1);
                            mTitles.add("申请人配偶");
                            mFragments.add(QianZiBanFragment.newInstance(mlist.get(i), 2));
                        }
                    }
                }
                myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
                mViewpager.setAdapter(myPagerAdapter);
                mTl1.setViewPager(mViewpager);
            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
            }
        }, this);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }



    private QianziBean qianziBean;
    private QianziBean qianziBeanTwo;

    /**
     * 刷新界面
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(QianziBean event) {
        LogUtils.e(event);
        if (ObjectUtils.isNotEmpty(mlist.get(0).getId())) {
            sbEdit.setVisibility(View.GONE);
            sbSc.setVisibility(View.GONE);
        } else {
            sbEdit.setVisibility(View.VISIBLE);
            sbSc.setVisibility(View.VISIBLE);
        }
        if (ObjectUtils.isNotEmpty(event)) {
            if (mlist.get(0).getJs().equals(event.getJs())) {
                qianziBean = event;
            }
            if (mlist.get(1).getJs().equals(event.getJs())) {
                qianziBeanTwo = event;
            }

        }
    }

    //删除图片
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("是否删除所选图片")) {
            if (event.getId().equals(mlist.get(0).getUploadImg())) {
                qianziBean.setUploadImg("");

            }
            if (event.getId().equals(mlist.get(1).getUploadImg())) {
                qianziBeanTwo.setUploadImg("");

            }

        }
    }


    @OnClick({R.id.sb_edit, R.id.sb_sc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //保存
            case R.id.sb_edit:
                LogUtils.e(qianziBean.getUploadImg());

                if (ObjectUtils.isEmpty(qianziBean.getUploadImg())) {
                    if (ObjectUtils.isEmpty(qianziBean.getSqrqm()) || ObjectUtils.isEmpty(qianziBean.getSqrjbkhjlqm())) {
                        ToastUtil.showBlackToastSucess(ObjectUtils.isEmpty(qianziBean.getSqrqm()) ? "缺少申请人签名" : "缺少工作人员签名");
                        return;
                    }

                }
                if (ObjectUtils.isNotEmpty(qianziBeanTwo)) {
                    LogUtils.e(qianziBeanTwo.getUploadImg());
                    if (ObjectUtils.isEmpty(qianziBeanTwo.getUploadImg())) {
                        if (ObjectUtils.isEmpty(qianziBeanTwo.getSqrqm()) || ObjectUtils.isEmpty(qianziBeanTwo.getSqrjbkhjlqm())) {
                            ToastUtil.showBlackToastSucess(ObjectUtils.isEmpty(qianziBeanTwo.getSqrqm()) ? "缺少申请人配偶签名" : "申请人配偶缺少工作人员签名");
                            return;
                        }
                    }
                }


                if (ObjectUtils.isEmpty(qianziBean.getId())) {
                    LogUtils.e(qianziBean);
                    LogUtils.e(str2Map(qianziBean));
                    CeditApi.updateQianMing(str2Map(qianziBean), new BaseCallback<BaseResponse<QianziBean>>() {
                        @Override
                        public void onSucc(BaseResponse<QianziBean> result) {
                            EventBus.getDefault().post(result.data);
                            sbEdit.setVisibility(View.GONE);
                            sbSc.setVisibility(View.GONE);
                        }
                    }, this);
                    if (ObjectUtils.isNotEmpty(qianziBeanTwo)) {
                        CeditApi.updateQianMing(str2Map(qianziBeanTwo), new BaseCallback<BaseResponse<QianziBean>>() {
                            @Override
                            public void onSucc(BaseResponse<QianziBean> result) {
                                EventBus.getDefault().post(result.data);
                                sbEdit.setVisibility(View.GONE);
                                sbSc.setVisibility(View.GONE);
                            }
                        }, this);
                    }
                }
                break;
            //上传
            case R.id.sb_sc:
                PictureSelector
                        .create(getActivity(), 1008)
                        .selectPicture(false, 200, 200, 1, 1);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final OcrBean ocrBean) {

        /*结果回调*/
        if (ocrBean.getResultCode() == 1008) {
            LogUtils.e(mTl1.getCurrentTab());
            //4m大小 支持
            if (null != ocrBean.getData()) {
                String picturePath = ocrBean.getData().getStringExtra(PictureSelector.PICTURE_PATH);

                final File mFile = new File(picturePath);
                Luban.with(getActivity())
                        .load(mFile)
                        .ignoreBy(80)
                        .setTargetDir(AppMgr.getInstance().getApkPath())
                        .filter(new CompressionPredicate() {
                            @Override
                            public boolean apply(String path) {
                                return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                            }
                        })
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                startProgressDialog(true);
                            }

                            @Override
                            public void onSuccess(final File file) {


                                UserApi.updatePic(file, new BaseCallback<BaseResponse<String>>() {
                                    @Override
                                    public void onSucc(BaseResponse<String> result) {
                                        stopProgressDialog();

                                        if (ObjectUtils.isNotEmpty(mlist)) {
                                            mlist.get(mTl1.getCurrentTab()).setUploadImg(result.msg);

                                            EventBus.getDefault().post(mlist.get(mTl1.getCurrentTab()));
                                        }

                                    }


                                }, this);

                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                                stopProgressDialog();
                                ToastUtil.showBlackToastSucess("压缩文件失败");
                            }
                        }).launch();

            }

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
