package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.cedit.FaceBean;
import com.shaoyue.weizhegou.entity.cedit.OcrBean;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.wildma.pictureselector.PictureSelector;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class DYFaceRecognitionFragment extends BaseTitleFragment {

    @BindView(R.id.iv_sqr)
    ImageView mIvSqr;
    @BindView(R.id.iv_po)
    ImageView mIvPo;
    @BindView(R.id.tv_gr)
    TextView mTvGr;
    @BindView(R.id.tv_po)
    TextView mTvPo;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    @BindView(R.id.ll_po)
    LinearLayout mLlpo;


    private List<FaceBean> mFaceBeans;

    private String sfzh;

    public static DYFaceRecognitionFragment newInstance(String sfzh) {
        Bundle args = new Bundle();
        args.putString("sfzh", sfzh);
        DYFaceRecognitionFragment fragment = new DYFaceRecognitionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_face_recognition;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            sfzh = getArguments().getString("sfzh");
        }
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("担保人人脸识别");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    /**
     * 刷新界面
     */
    private void initView() {

        CeditApi.findFaceInfo(sfzh,new BaseCallback<BaseResponse<List<FaceBean>>>() {
            @Override
            public void onSucc(BaseResponse<List<FaceBean>> result) {
                LogUtils.e(result.data);
                mFaceBeans = result.data;
                if (ObjectUtils.isNotEmpty(mFaceBeans) && mFaceBeans.size() > 0) {

                    if (mFaceBeans.size() == 1) {
                        mLlpo.setVisibility(View.GONE);
                    } else {
                        mLlpo.setVisibility(View.VISIBLE);

                    }
                    GlideNewImageLoader.displayOwnerImageHeadNoCache(getActivity(), mIvSqr, DomainMgr.getInstance().getBaseUrlImg() + mFaceBeans.get(0).getTxdz(), R.drawable.icon_face);
                    if (mFaceBeans.size() > 1) {
                        GlideNewImageLoader.displayOwnerImageHeadNoCache(getActivity(), mIvPo, DomainMgr.getInstance().getBaseUrlImg() + mFaceBeans.get(1).getTxdz(), R.drawable.icon_face);
                        if (ObjectUtils.isNotEmpty(mFaceBeans.get(1).getJl())) {
                            mLlBottom.setVisibility(View.VISIBLE);
                            mTvPo.setText(mFaceBeans.get(1).getJl());
                        }
                    }


                    if (ObjectUtils.isNotEmpty(mFaceBeans.get(0).getJl())) {
                        mLlBottom.setVisibility(View.VISIBLE);
                        mTvGr.setText(mFaceBeans.get(0).getJl());
                    }
                }
            }

            @Override
            public void onFail(ApiException apiError) {
            }
        }, this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final OcrBean ocrBean) {
        /*结果回调*/
        if (ocrBean.getResultCode() == 1104 || ocrBean.getResultCode() == 1105) {
            if (ObjectUtils.isEmpty(mFaceBeans)) {
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
                                String zjhm = "";
                                if (ocrBean.getResultCode() == 1104) {
                                    zjhm = mFaceBeans.get(0).getSfzh();
                                } else {
                                    zjhm = mFaceBeans.get(1).getSfzh();
                                }
                                //上传头像
                                UserApi.updatePic(file, zjhm, new BaseCallback<BaseResponse<String>>() {
                                    @Override
                                    public void onSucc(BaseResponse<String> result) {

                                        FaceBean faceBean = null;
                                        if (ocrBean.getResultCode() == 1104) {
                                            faceBean = mFaceBeans.get(0);
                                        } else {
                                            faceBean = mFaceBeans.get(1);
                                        }
                                        faceBean.setTxdz(result.msg);
                                        CeditApi.faceOk(faceBean, new BaseCallback<BaseResponse<FaceBean>>() {
                                            @Override
                                            public void onSucc(BaseResponse<FaceBean> result) {
                                                stopProgressDialog();
                                                initView();
                                            }

                                            @Override
                                            public void onFail(ApiException apiError) {
                                                stopProgressDialog();
                                                super.onFail(apiError);

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


    @OnClick({R.id.ll_gr, R.id.ll_po})
    public void onViewClicked(View view) {
        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            return;
        }
        switch (view.getId()) {
            case R.id.ll_gr:
                /**
                 * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
                 * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
                 */
                PictureSelector
                        .create(getActivity(), 1104)
                        .selectPicture(false, 200, 200, 1, 1);

                break;
            case R.id.ll_po:
                PictureSelector
                        .create(getActivity(), 1105)
                        .selectPicture(false, 200, 200, 1, 1);
                break;
        }
    }


}
