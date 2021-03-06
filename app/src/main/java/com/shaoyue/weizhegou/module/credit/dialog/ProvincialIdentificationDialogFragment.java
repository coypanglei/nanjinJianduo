package com.shaoyue.weizhegou.module.credit.dialog;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.entity.cedit.IDCardBack;
import com.shaoyue.weizhegou.entity.cedit.IDCardFrontBean;
import com.shaoyue.weizhegou.entity.cedit.OcrBean;
import com.shaoyue.weizhegou.entity.cedit.applyBean;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.DialogInitUtil;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.datepicker.CustomDatePicker;
import com.shaoyue.weizhegou.widget.datepicker.DateFormatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 作者：PangLei on 2019/5/15 0015 10:25
 * <p>
 * 邮箱：434604925@qq.com
 */
public class ProvincialIdentificationDialogFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.iv_zheng)
    ImageView mIvZheng;
    @BindView(R.id.iv_fan)
    ImageView mIvFan;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_id_card)
    EditText mEtIdCard;
    @BindView(R.id.et_id_address)
    EditText mEtIdAddress;
    @BindView(R.id.et_start_time)
    TextView mTvSelectedDate;
    @BindView(R.id.et_qf_time)
    TextView etQfTime;

    private CustomDatePicker mDatePicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_id_card_identification, null);
        unbinder = ButterKnife.bind(this, view);
        initDatePicker();
        DialogInitUtil.initView(dialog, view);

        return dialog;
    }


    public static ProvincialIdentificationDialogFragment newInstance() {
        Bundle args = new Bundle();
        ProvincialIdentificationDialogFragment fragment = new ProvincialIdentificationDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    private String sfzm;

    private String sffm;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OcrBean ocrBean) {
        LogUtils.e(ocrBean.getData());
        /*结果回调*/
        if (ocrBean.getResultCode() == 1001) {
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

                            }

                            @Override
                            public void onSuccess(final File file) {

                                // TODO 压缩成功后调用，返回压缩后的图片文件
                                UserApi.updatOcr("front", file, new BaseCallback<BaseResponse<IDCardFrontBean>>() {
                                    @Override
                                    public void onSucc(BaseResponse<IDCardFrontBean> result) {
                                        if (result.data.getImage_status().equals("normal")) {
                                            mEtName.setText(result.data.getWords_result().get姓名().getWords());
                                            mEtIdCard.setText(result.data.getWords_result().get公民身份号码().getWords());
                                            mEtIdAddress.setText(result.data.getWords_result().get住址().getWords());
                                            LogUtils.e(file);
                                            Glide.with(getActivity()).load(Uri.fromFile(file)).into(mIvZheng);
                                            UserApi.updateSfzPic(file, new BaseCallback<BaseResponse<String>>() {
                                                @Override
                                                public void onSucc(BaseResponse<String> result) {

                                                    sfzm = result.msg;

                                                }

                                                @Override
                                                public void onFail(ApiException apiError) {
                                                    ToastUtil.showBlackToastSucess("上传身份证失败");
                                                }
                                            }, this);

                                        } else {
                                            ToastUtil.showBlackToastSucess("身份证正面证识别失败");
                                        }
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
        } else if (ocrBean.getResultCode() == 1002) {
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
                            }

                            @Override
                            public void onSuccess(final File file) {
                                // TODO 压缩成功后调用，返回压缩后的图片文件
                                UserApi.updatOcrBack("back", mFile, new BaseCallback<BaseResponse<IDCardBack>>() {
                                    @Override
                                    public void onSucc(BaseResponse<IDCardBack> result) {
                                        if (result.data.getImage_status().equals("normal")) {
                                            LogUtils.e(result.data.getWords_result().get失效日期().getWords());
                                            if (ObjectUtils.isNotEmpty(result.data.getWords_result().get失效日期().getWords())) {
                                                if (!"长期".equals(result.data.getWords_result().get失效日期().getWords())) {
                                                    try {
                                                        String data = result.data.getWords_result().get失效日期().getWords();
                                                        String year = data.substring(0, 4);
                                                        String month = data.substring(4, 6);
                                                        String day = data.substring(6, 8);
                                                        String str = year + "-" + month + "-" + day;
                                                        mTvSelectedDate.setText(str);
                                                    } catch (Exception e) {

                                                    }

                                                } else {
                                                    mTvSelectedDate.setText("2099-12-31");
                                                }
                                            }
                                            if (ObjectUtils.isNotEmpty(result.data.getWords_result().get签发日期().getWords())) {
                                                if (!"长期".equals(result.data.getWords_result().get签发日期().getWords())) {
                                                    try {
                                                        String data = result.data.getWords_result().get签发日期().getWords();
                                                        String year = data.substring(0, 4);
                                                        String month = data.substring(4, 6);
                                                        String day = data.substring(6, 8);
                                                        String str = year + "-" + month + "-" + day;
                                                        etQfTime.setText(str);
                                                    } catch (Exception e) {

                                                    }

                                                } else {
                                                    etQfTime.setText("");
                                                }
                                            }


                                            Glide.with(getActivity()).load(Uri.fromFile(file)).into(mIvFan);
                                            UserApi.updateSfzPic(file, new BaseCallback<BaseResponse<String>>() {
                                                @Override
                                                public void onSucc(BaseResponse<String> result) {
                                                    sffm = result.msg;

                                                }

                                                @Override
                                                public void onFail(ApiException apiError) {
                                                    ToastUtil.showBlackToastSucess("上传身份证失败");
                                                }
                                            }, this);
                                        } else {
                                            ToastUtil.showBlackToastSucess("身份证背面证识别失败");
                                        }
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


    @OnClick({R.id.iv_zheng, R.id.iv_fan, R.id.iv_close, R.id.tv_shenqing, R.id.tv_restart, R.id.et_start_time, R.id.et_qf_time})
    public void onViewClicked(View view) {


        switch (view.getId()) {
            case R.id.iv_zheng:
                /**
                 * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
                 * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
                 */
                EasyPhotos.createCamera(getActivity())//参数说明：上下文，是否显示相机按钮，[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                        .setFileProviderAuthority("com.shaoyue.weizhegou.fileprovider")//参数说明：见下方`FileProvider的配置`
                        .setCount(1)//参数说明：最大可选数，默认1
                        .start(1001);
//
                break;
            case R.id.iv_fan:
                EasyPhotos.createCamera(getActivity())//参数说明：上下文，是否显示相机按钮，[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                        .setFileProviderAuthority("com.shaoyue.weizhegou.fileprovider")//参数说明：见下方`FileProvider的配置`
                        .setCount(1)//参数说明：最大可选数，默认1
                        .start(1002);


                break;
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_shenqing:


                //申请
                final String mIdAddress = mEtIdAddress.getText().toString().trim();
                final String mIdCard = mEtIdCard.getText().toString().trim();
                final String mName = mEtName.getText().toString().trim();
                final String mStartTime = mTvSelectedDate.getText().toString().trim();
                final String zjqfr = etQfTime.getText().toString().trim();
                if (ObjectUtils.isEmpty(mName)) {
                    ToastUtil.showBlackToastSucess("未填写姓名");
                    return;
                }
                if (ObjectUtils.isEmpty(mIdCard)) {
                    ToastUtil.showBlackToastSucess("未填写身份证号码");
                    return;
                }

                if (ObjectUtils.isEmpty(mStartTime)) {
                    ToastUtil.showBlackToastSucess("未填写截止日期");
                    return;
                }
                if (!RegexUtils.isIDCard18(mIdCard)) {
                    ToastUtil.showBlackToastSucess("身份证格式不正确");
                    return;
                }

                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        CeditApi.creditApplication(zjqfr,mName, mIdCard, mStartTime, mIdAddress, sfzm, sffm, new BaseCallback<BaseResponse<applyBean>>() {
                            @Override
                            public void onSucc(BaseResponse<applyBean> result) {
                                //请求id 身份证
                                SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, result.data.getId());
                                SPUtils.getInstance().put(UserMgr.SP_ID_CARD, result.data.getSfzh());
                                UIHelper.showApplyCommonActivity(getActivity(), "申请");
                            }
                        }, this);
                    }
                }, 2000);


                break;
            case R.id.tv_restart:
                //重置
                mEtIdAddress.setText("");
                mEtIdCard.setText("");
                mEtName.setText("");
                mTvSelectedDate.setText("");
                mIvZheng.setImageResource(R.drawable.icon_id_card_zhen);
                mIvFan.setImageResource(R.drawable.icon_id_card_fan);
                break;
            case R.id.et_start_time:
                startTime = true;
                mDatePicker.show(mTvSelectedDate.getText().toString());


                break;
            case R.id.et_qf_time:
                startTime = false;
                mDatePicker.show(etQfTime.getText().toString());
                break;
        }


    }

    private boolean startTime;

    /**
     * 时间滑轮
     */
    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2009-05-01", false);
        long endTimestamp = DateFormatUtils.str2Long("2119-05-01", false);


//        mTvSelectedDate.setText(DateFormatUtils.long2Str(System.currentTimeMillis(), false));

        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                if (startTime) {
                    mTvSelectedDate.setText(DateFormatUtils.long2Str(timestamp, false));
                } else {
                    etQfTime.setText(DateFormatUtils.long2Str(timestamp, false));
                }
            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(false);
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
    }

}