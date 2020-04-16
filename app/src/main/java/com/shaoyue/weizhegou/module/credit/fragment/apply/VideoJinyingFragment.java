package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.TabEntity;
import com.shaoyue.weizhegou.entity.cedit.OcrBean;
import com.shaoyue.weizhegou.entity.cedit.VideoBean;
import com.shaoyue.weizhegou.entity.cedit.VideoMaterialBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.VideoListAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.TopSmoothScroller;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


public class VideoJinyingFragment extends BaseAppFragment {

    //滑块的数据
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    @BindView(R.id.rv_video)
    RecyclerView mRvVideo;
    @BindView(R.id.tl_all_commodity)
    CommonTabLayout mTlAllCommodity;
    private VideoListAdapter mAdapter;

    private String title;
    int selectTwo = 0;
    int selectThree = 0;
    //有图片
    List<VideoMaterialBean.ListBean> unPic = new ArrayList<>();

    private LinearLayoutManager manager;

    public static VideoJinyingFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        VideoJinyingFragment fragment = new VideoJinyingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_materia_jingying;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            title = getArguments().getString("title");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mTabEntities.add(new TabEntity("个体经营户", 0, 0));
        mTabEntities.add(new TabEntity("工薪类客户", 0, 0));
        mTabEntities.add(new TabEntity("法人客户", 0, 0));
        mTlAllCommodity.setTabData(mTabEntities);
        mTlAllCommodity.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                LinearSmoothScroller s1 = new TopSmoothScroller(getActivity());
                if (position == 0) {
                    s1.setTargetPosition(0);
                } else if (position == 1) {
                    LogUtils.e(selectTwo);
                    s1.setTargetPosition(selectTwo);
                } else if (position == 2) {
                    LogUtils.e(selectThree);
                    s1.setTargetPosition(selectThree);
                }
                manager.startSmoothScroll(s1);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        manager = new LinearLayoutManager(getActivity());
        mAdapter = new VideoListAdapter(getActivity());
        mRvVideo.setLayoutManager(manager);
        mRvVideo.setAdapter(mAdapter);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final OcrBean ocrBean) {
        String mTitle = SPUtils.getInstance().getString("selectPic");
        String id = SPUtils.getInstance().getString("selectPic_zllx");
        if (mTitle.equals("经营相关材料")) {
            if (id.contains("法人客户") || id.contains("工薪类客户") || id.contains("个体经营户")) {


                /*结果回调*/
                if (ocrBean.getResultCode() == 1007) {

//            LogUtils.e(id);
                    if (ObjectUtils.isEmpty(id)) {
                        return;
                    }
                    //4m大小 支持
                    if (null != ocrBean.getData()) {
                        final ArrayList<Photo> resultPhotos = ocrBean.getData().getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
                        if (ObjectUtils.isEmpty(resultPhotos) || resultPhotos.size() == 0) {
                            return;
                        }
                        final List<File> files = new ArrayList<>();
                        for (int i = 0; i < resultPhotos.size(); i++) {
                            String picturePath = resultPhotos.get(i).path;
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
                                            //添加文件
                                            files.add(file);
                                            //判断集合是否为空
                                            if (files.size() == resultPhotos.size()) {
                                                final String zllx = SPUtils.getInstance().getString("selectPic_zllx").replace("反", "").replace("正", "").replace("面", "");
//                                //上传头像

                                                UserApi.updatePic(files, new BaseCallback<BaseResponse<String>>() {
                                                    @Override
                                                    public void onSucc(BaseResponse<String> result) {
                                                        VideoMaterialBean.ListBean mdata = new VideoMaterialBean.ListBean();
                                                        mdata.setZldz(result.msg);
                                                        mdata.setZllx(zllx);
                                                        mdata.setSxsfzh(SPUtils.getInstance().getString("selectPic_sxsfzh"));
                                                        CeditApi.addVideo(mdata, new BaseCallback<BaseResponse<Void>>() {
                                                            @Override
                                                            public void onSucc(BaseResponse<Void> result) {
                                                                resh();
                                                                stopProgressDialog();
                                                            }

                                                            @Override
                                                            public void onFail(ApiException apiError) {
                                                                super.onFail(apiError);
                                                                stopProgressDialog();
                                                            }
                                                        }, this);

                                                    }

                                                    @Override
                                                    public void onFail(ApiException apiError) {
                                                        stopProgressDialog();
                                                    }
                                                }, this);
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            // TODO 当压缩过程出现问题时调用
                                            ToastUtil.showBlackToastSucess("压缩文件失败");
                                        }
                                    }).launch();
                        }


                    }

                }
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final VideoMaterialBean.ListBean listBean) {

        String id = SPUtils.getInstance().getString("selectPic_id");
        if (id.equals(listBean.getId())) {
            VideoMaterialBean videoMaterialBean = new VideoMaterialBean();
            unPic.clear();
            for (VideoMaterialBean bean : mAdapter.getData()) {


                if (ObjectUtils.isNotEmpty(bean.getList())) {
                    for (VideoMaterialBean.ListBean data : bean.getList()) {
                        if (ObjectUtils.isNotEmpty(data.getZldz())) {
                            if (!unPic.contains(data.getZldz())) {
                                unPic.add(data);
                            }
                        }
                    }
                }

            }
            videoMaterialBean.setList(unPic);
            int select = 0;
            boolean re = false;
            for (int i = 0; i < unPic.size(); i++) {
                if (unPic.get(i).getId().equals(id)) {
                    select = i;
                    re = true;
                }

            }
            if (re) {

                videoMaterialBean.setSelect(select);
                //更换接口
                videoMaterialBean.setUrl("SXSQ");
                LogUtils.e("saddasd");
                UIHelper.showPicDialog(getActivity(), videoMaterialBean);
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("是否删除所选图片")) {
            resh();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        resh();
    }

    /**
     * 刷新
     */
    private void resh() {
        CeditApi.getVideoDetailsList(new BaseCallback<BaseResponse<VideoBean>>() {
            @Override
            public void onSucc(BaseResponse<VideoBean> result) {
                unPic.clear();
                //分析数据
                if (ObjectUtils.isNotEmpty(result.data.getMlist())) {
                    List<VideoMaterialBean> mlist = result.data.getMlist();
                    List<VideoMaterialBean> mDataListBean = new ArrayList<>();

                    for (VideoMaterialBean bean : mlist) {
                        if (bean.getCategory().equals("经营相关材料")) {
                            mDataListBean.add(bean);

                            if (ObjectUtils.isNotEmpty(bean.getList())) {
                                for (VideoMaterialBean.ListBean data : bean.getList()) {
                                    if (ObjectUtils.isNotEmpty(data.getZldz())) {
                                        if (!unPic.contains(data.getZldz())) {
                                            unPic.add(data);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    for (int i = 0; i < mDataListBean.size(); i++) {
                        if (mDataListBean.get(i).getCategory().equals("经营相关材料")) {
                            if (mDataListBean.get(i).getTitle().contains("工薪类客户")) {
                                selectTwo = i;
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < mDataListBean.size(); i++) {
                        if (mDataListBean.get(i).getCategory().equals("经营相关材料")) {
                            if (mDataListBean.get(i).getTitle().contains("法人客户")) {
                                selectThree = i;
                                break;
                            }
                        }
                    }
                    mAdapter.setNewData(mDataListBean);

                }

            }
        }, this);
    }

}
