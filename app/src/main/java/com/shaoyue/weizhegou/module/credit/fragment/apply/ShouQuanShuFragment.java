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
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.OcrBean;
import com.shaoyue.weizhegou.entity.cedit.QianziBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.entity.cedit.ScreenObject;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.util.GlideEngine;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;

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


public class ShouQuanShuFragment extends BaseAppFragment {

    @BindView(R.id.tl_1)
    SlidingTabLayout mTl1;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.sb_edit)
    StateButton sbEdit;
    @BindView(R.id.sb_sc)
    StateButton sbSc;
    private List<QianziBean> mlist = new ArrayList<>();


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
        CeditApi.findFamilyInfo(new BaseCallback<BaseResponse<List<QianziBean>>>() {
            @Override
            public void onSucc(BaseResponse<List<QianziBean>> result) {

                mFragments.clear();
                mTitles.clear();
                mlist = result.data;
                if (ObjectUtils.isNotEmpty(mlist)) {
                    for (int i = 0; i < mlist.size(); i++) {
                        if (i == 0) {
                            mTitles.add("申请人");


                            if ("查看详情".equals(SPUtils.getInstance().getString("status")) || "调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
                                sbEdit.setVisibility(View.GONE);
                                sbSc.setVisibility(View.GONE);
                            } else {
                                sbEdit.setVisibility(View.VISIBLE);
                                sbSc.setVisibility(View.VISIBLE);
                            }
                            qianziBean = mlist.get(0);
                            mFragments.add(QianZiBanFragment.newInstance(mlist.get(i), 0));
                        }
                        if (i == 1) {
                            qianziBeanTwo = mlist.get(1);
                            mTitles.add("申请人配偶");
                            mFragments.add(QianZiBanFragment.newInstance(mlist.get(i), 0));
                        }
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();


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

        if (ObjectUtils.isNotEmpty(mlist.get(0).getId())) {
//            sbEdit.setVisibility(View.GONE);
//            sbSc.setVisibility(View.GONE);
        } else {
            sbEdit.setVisibility(View.VISIBLE);
            sbSc.setVisibility(View.VISIBLE);
        }
        if (ObjectUtils.isNotEmpty(event)) {
            if (mlist.get(0).getJs().equals(event.getJs())) {
                qianziBean = event;

                mlist.set(0, qianziBean);
            }
            if (mlist.get(1).getJs().equals(event.getJs())) {
                qianziBeanTwo = event;
                mlist.set(1, qianziBeanTwo);
            }

        }
    }


    /**
     * 刷新界面
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(ScreenObject event) {
        LogUtils.e(event);

        if (ObjectUtils.isNotEmpty(event)) {
            if (mlist.get(0).getJs().equals(event.getJs())) {
                qianziBean.setScreenshotImg(event.getBitmap());
            }
            if (mlist.get(1).getJs().equals(event.getJs())) {
                qianziBeanTwo.setScreenshotImg(event.getBitmap());
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

                startProgressDialog(true);
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ObjectUtils.isEmpty(qianziBean.getUploadImg())) {
                            if (ObjectUtils.isEmpty(qianziBean.getSqrqm()) || ObjectUtils.isEmpty(qianziBean.getSqrjbkhjlqm())) {
                                ToastUtil.showBlackToastSucess(ObjectUtils.isEmpty(qianziBean.getSqrqm()) ? "缺少申请人签名" : "缺少工作人员签名");
                                stopProgressDialog();
                                return;
                            }

                        } else {
                            if (ObjectUtils.isEmpty(qianziBean.getTxrq())) {
                                ToastUtil.showBlackToastSucess("缺少本人授权日期");
                                stopProgressDialog();
                                return;
                            }
                        }
                        boolean isBcpo = true;
                        if (ObjectUtils.isNotEmpty(qianziBeanTwo)) {
                            LogUtils.e(qianziBeanTwo.getUploadImg());
                            if (ObjectUtils.isEmpty(qianziBeanTwo.getUploadImg())) {
                                if (ObjectUtils.isEmpty(qianziBeanTwo.getSqrqm()) || ObjectUtils.isEmpty(qianziBeanTwo.getSqrjbkhjlqm())) {
                                    //ToastUtil.showBlackToastSucess(ObjectUtils.isEmpty(qianziBeanTwo.getSqrqm()) ? "缺少申请人配偶签名" : "申请人配偶缺少工作人员签名");
                                    isBcpo = false;
                                }
                            } else {
                                if (ObjectUtils.isEmpty(qianziBeanTwo.getTxrq())) {
                                    ToastUtil.showBlackToastSucess("缺少配偶授权日期");
                                    stopProgressDialog();
                                    return;
                                }
                            }
                        }


                        if (ObjectUtils.isEmpty(qianziBean.getId())) {
                            if (ObjectUtils.isNotEmpty(qianziBean.getScreenshotImg())) {


                                CeditApi.updateQianMing(str2Map(qianziBean), new BaseCallback<BaseResponse<QianziBean>>() {
                                    @Override
                                    public void onSucc(BaseResponse<QianziBean> result) {
                                        EventBus.getDefault().post(result.data);
//                                            sbEdit.setVisibility(View.GONE);
//                                            sbSc.setVisibility(View.GONE);
                                        stopProgressDialog();
                                    }

                                    @Override
                                    public void onFail(ApiException apiError) {
                                        super.onFail(apiError);
                                        stopProgressDialog();
                                    }
                                }, this);
                            } else {
                                if (ObjectUtils.isNotEmpty(qianziBean.getSqrqm()) && ObjectUtils.isNotEmpty(qianziBean.getSqrjbkhjlqm())) {
                                    ToastUtil.showBlackToastSucess("授权书保存中，请稍后重试");
                                    stopProgressDialog();
                                    return;
                                }

                                CeditApi.updateQianMing(str2Map(qianziBean), new BaseCallback<BaseResponse<QianziBean>>() {
                                    @Override
                                    public void onSucc(BaseResponse<QianziBean> result) {
                                        EventBus.getDefault().post(result.data);
                                        //                                            sbEdit.setVisibility(View.GONE);
//                                            sbSc.setVisibility(View.GONE);
                                        stopProgressDialog();
                                    }

                                    @Override
                                    public void onFail(ApiException apiError) {
                                        super.onFail(apiError);
                                        stopProgressDialog();
                                    }
                                }, this);
                            }


                        }

                        if (ObjectUtils.isNotEmpty(qianziBeanTwo) && ObjectUtils.isEmpty(qianziBeanTwo.getId())) {
                            if (isBcpo) {
                                if (ObjectUtils.isNotEmpty(qianziBeanTwo)) {
                                    if (ObjectUtils.isNotEmpty(qianziBeanTwo.getScreenshotImg())) {
                                        CeditApi.updateQianMing(str2Map(qianziBeanTwo), new BaseCallback<BaseResponse<QianziBean>>() {
                                            @Override
                                            public void onSucc(BaseResponse<QianziBean> result) {
                                                EventBus.getDefault().post(result.data);
                                                //                                            sbEdit.setVisibility(View.GONE);
//                                                    sbSc.setVisibility(View.GONE);
                                                stopProgressDialog();
                                            }

                                            @Override
                                            public void onFail(ApiException apiError) {
                                                super.onFail(apiError);
                                                stopProgressDialog();
                                            }
                                        }, this);
                                    } else {
                                        if (ObjectUtils.isNotEmpty(qianziBeanTwo.getSqrqm()) && ObjectUtils.isNotEmpty(qianziBeanTwo.getSqrjbkhjlqm())) {
                                            ToastUtil.showBlackToastSucess("授权书保存中，请稍后重试");
                                            stopProgressDialog();
                                            return;
                                        }
                                        if (ObjectUtils.isNotEmpty(qianziBeanTwo)) {
                                            CeditApi.updateQianMing(str2Map(qianziBeanTwo), new BaseCallback<BaseResponse<QianziBean>>() {
                                                @Override
                                                public void onSucc(BaseResponse<QianziBean> result) {
                                                    EventBus.getDefault().post(result.data);
                                                    //                                            sbEdit.setVisibility(View.GONE);
//                                                        sbSc.setVisibility(View.GONE);
                                                    stopProgressDialog();
                                                }

                                                @Override
                                                public void onFail(ApiException apiError) {
                                                    super.onFail(apiError);
                                                    stopProgressDialog();
                                                }
                                            }, this);
                                        }
                                    }
                                }
                            }
                        }
                        stopProgressDialog();
                        EventBus.getDefault().post(new RefreshBean());
                    }

                }, 2000);

                break;
            //上传
            case R.id.sb_sc:
                if (ObjectUtils.isEmpty(mlist.get(mTl1.getCurrentTab()).getId())) {
                    EasyPhotos.createAlbum(getActivity(), true, GlideEngine.getInstance())//参数说明：上下文，是否显示相机按钮，[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                            .setFileProviderAuthority("com.shaoyue.weizhegou.fileprovider")//参数说明：见下方`FileProvider的配置`
                            .setCount(1)//参数说明：最大可选数，默认1
                            .start(1008);
                }
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
                final ArrayList<Photo> resultPhotos = ocrBean.getData().getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
                if (ObjectUtils.isEmpty(resultPhotos) || resultPhotos.size() == 0) {
                    return;
                }
                String picturePath = resultPhotos.get(0).path;

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

    //适配器
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
