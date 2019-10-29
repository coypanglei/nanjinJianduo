package com.shaoyue.weizhegou.module.dhgl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.OcrBean;
import com.shaoyue.weizhegou.entity.cedit.VideoBean;
import com.shaoyue.weizhegou.entity.cedit.VideoMaterialBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.VideoListAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.wildma.pictureselector.PictureSelector;

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


public class XcjyVideoDetailsFragment extends BaseAppFragment {


    @BindView(R.id.rv_video)
    RecyclerView mRvVideo;

    private VideoListAdapter mAdapter;

    private String title;

    //有图片
    List<VideoMaterialBean.ListBean> unPic = new ArrayList<>();

    public static XcjyVideoDetailsFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        XcjyVideoDetailsFragment fragment = new XcjyVideoDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_materia;
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
        mAdapter = new VideoListAdapter(getActivity());
        mRvVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvVideo.setAdapter(mAdapter);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final OcrBean ocrBean) {
        String mTitle = SPUtils.getInstance().getString("selectPic");
        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            return;
        }

        LogUtils.e(mTitle);
        LogUtils.e(title);
//        if (mTitle.contains(title)) {
            addPic(ocrBean);
//        }
    }

    private void addPic(final OcrBean ocrBean) {

        /*结果回调*/
        if (ocrBean.getResultCode() == 1007) {
            if (ObjectUtils.isEmpty(mAdapter.getSelet())) {
                return;
            }
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

                                LogUtils.e(mAdapter.getSelet().getZllx());
                                final String zllx = mAdapter.getSelet().getZllx().replace("反", "").replace("正", "").replace("面", "");
//                                //上传头像

                                UserApi.updatePic(file, new BaseCallback<BaseResponse<String>>() {
                                    @Override
                                    public void onSucc(BaseResponse<String> result) {
                                        VideoMaterialBean.ListBean mdata = mAdapter.getSelet();
                                        mdata.setZldz(result.msg);
                                        mdata.setZllx(zllx);
                                        mAdapter.setSelet(new VideoMaterialBean.ListBean());
                                        DhApi.addVideo(mdata, new BaseCallback<BaseResponse<Void>>() {
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

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                                ToastUtil.showBlackToastSucess("压缩文件失败");
                            }
                        }).launch();
            }
        }







    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final VideoMaterialBean.ListBean listBean) {

        if (mAdapter.getSelet().getId().equals(listBean.getId())) {
            VideoMaterialBean videoMaterialBean = new VideoMaterialBean();
            unPic.clear();
            for (VideoMaterialBean bean : mAdapter.getData()) {
//                if (bean.getTitle().contains(title)) {

                    if (ObjectUtils.isNotEmpty(bean.getList())) {
                        for (VideoMaterialBean.ListBean data : bean.getList()) {
                            if (ObjectUtils.isNotEmpty(data.getZldz())) {
                                if (!unPic.contains(data.getZldz())) {
                                    unPic.add(data);
                                }
                            }
                        }
                    }

//                }

            }
            videoMaterialBean.setList(unPic);
            int select = 0;
            for (int i = 0; i < unPic.size(); i++) {
                if (unPic.get(i).getId().equals(mAdapter.getSelet().getId())) {
                    select = i;
                }
            }
            videoMaterialBean.setSelect(select);
            //更换接口
            videoMaterialBean.setUrl("XCJY");
            UIHelper.showPicDialog(getActivity(), videoMaterialBean);
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
        DhApi.getVideoDetailsList(new BaseCallback<BaseResponse<VideoBean>>() {
            @Override
            public void onSucc(BaseResponse<VideoBean> result) {

                unPic.clear();
                //分析数据
                if (ObjectUtils.isNotEmpty(result.data.getMlist())) {
                    List<VideoMaterialBean> mlist = result.data.getMlist();
                    List<VideoMaterialBean> mDataListBean = new ArrayList<>();

                    for (VideoMaterialBean bean : mlist) {
//                        if (bean.getTitle().contains(title)) {
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
                    mAdapter.setNewData(mDataListBean);

                }

            }
        }, this);
    }

}
