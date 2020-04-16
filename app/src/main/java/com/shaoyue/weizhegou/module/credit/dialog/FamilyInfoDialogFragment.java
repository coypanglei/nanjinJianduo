package com.shaoyue.weizhegou.module.credit.dialog;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.shaoyue.weizhegou.entity.cedit.FamilyBean;
import com.shaoyue.weizhegou.entity.cedit.IDCardBack;
import com.shaoyue.weizhegou.entity.cedit.IDCardFrontBean;
import com.shaoyue.weizhegou.entity.cedit.OcrBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.util.GlideEngine;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.DropDownView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
public class FamilyInfoDialogFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.tv_my_info)
    TextView mTvinfo;
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.ddv_xb)
    DropDownView mDdvXB;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_id_card)
    EditText mEtIdCard;
    @BindView(R.id.et_nl)
    EditText mEtNl;
    @BindView(R.id.ddv_mz)
    DropDownView mDdvMz;
    @BindView(R.id.tv_mz)
    TextView mTvMz;
    @BindView(R.id.ddv_xw)
    DropDownView mDdvXw;
    @BindView(R.id.tv_xw)
    TextView mTvXw;
    @BindView(R.id.ddv_xl)
    DropDownView mDdvXl;
    @BindView(R.id.tv_xl)
    TextView mTvXl;
    @BindView(R.id.ddv_jkzk)
    DropDownView mDdvJkzk;
    @BindView(R.id.tv_jkzk)
    TextView mTvJkzk;
    @BindView(R.id.ddv_hyzk)
    DropDownView mDdvHyzk;
    @BindView(R.id.tv_hyzk)
    TextView mTvHyzk;
    @BindView(R.id.et_phone_num)
    EditText mEtPhoneNum;
    @BindView(R.id.et_dz)
    EditText mEtDz;
    @BindView(R.id.et_zy)
    EditText mEtZy;
    @BindView(R.id.ddv_js)
    DropDownView mDdvJs;
    @BindView(R.id.tv_js)
    TextView mTvJs;
    @BindView(R.id.et_ms)
    EditText mEtMs;
    @BindView(R.id.iv_zheng)
    ImageView ivZheng;
    @BindView(R.id.iv_fan)
    ImageView ivFan;
    @BindView(R.id.ll_png)
    LinearLayout llPng;

    private FamilyBean familyBean;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_family_info, null);
        unbinder = ButterKnife.bind(this, view);

        if (ObjectUtils.isNotEmpty(familyBean)) {
            mTvinfo.setText("修改家庭信息");
            mTvAdd.setText("修改");
            mEtIdCard.setEnabled(false);
            mDdvJs.setEnabled(false);
            mDdvXB.setEnabled(false);
           llPng.setVisibility(View.GONE);
            if (ObjectUtils.isNotEmpty(familyBean.getDz())) {
                mEtDz.setText(familyBean.getDz());
            }

            if (ObjectUtils.isNotEmpty(familyBean.getHyzk())) {
                mDdvHyzk.setText(familyBean.getHyzk());
            }
            if (ObjectUtils.isNotEmpty(familyBean.getDescription())) {
                mEtMs.setText(familyBean.getDescription());
            }
            if (ObjectUtils.isNotEmpty(familyBean.getJkzk())) {
                mDdvJkzk.setText(familyBean.getJkzk());
            }

            if (ObjectUtils.isNotEmpty(familyBean.getJs())) {
                mDdvJs.setText(familyBean.getJs());
            }

            if (ObjectUtils.isNotEmpty(familyBean.getLxdh())) {
                mEtPhoneNum.setText(familyBean.getLxdh());
            }
            if (ObjectUtils.isNotEmpty(familyBean.getXw())) {
                mDdvXw.setText(familyBean.getXw());
            }

            if (ObjectUtils.isNotEmpty(familyBean.getXl())) {
                mDdvXl.setText(familyBean.getXl());
            }
            if (ObjectUtils.isNotEmpty(familyBean.getXb())) {
                mDdvXB.setText(familyBean.getXb());
            }
            if (ObjectUtils.isNotEmpty(familyBean.getXm())) {
                mEtName.setText(familyBean.getXm());
            }

            if (ObjectUtils.isNotEmpty(familyBean.getMz())) {
                mDdvMz.setText(familyBean.getMz());
            }

            if (ObjectUtils.isNotEmpty(familyBean.getNl())) {
                mEtNl.setText(familyBean.getNl());
            }
            if (ObjectUtils.isNotEmpty(familyBean.getSfzh())) {
                mEtIdCard.setText(familyBean.getSfzh());
            }
            if (ObjectUtils.isNotEmpty(familyBean.getZy())) {
                mEtZy.setText(familyBean.getZy());
            }

        }

        mEtIdCard.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mEtIdCard.addTextChangedListener(textWatcher);
                } else {

                    mEtIdCard.removeTextChangedListener(textWatcher);
                }
            }
        });
        initView(dialog, view);

        return dialog;
    }

    final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (mEtIdCard.hasFocus()) {

                LogUtils.e(editable.toString().trim().length());

                if (editable.toString().trim().length() == 18) {
                    try {
                        Map<String, String> map = identityCard18(editable.toString().trim());
                        if (ObjectUtils.isNotEmpty(map.get("sex"))) {
                            mDdvXB.setText(map.get("sex"));
                        }
                        if (ObjectUtils.isNotEmpty(map.get("age"))) {
                            mEtNl.setText(map.get("age"));
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }
    };

    public static FamilyInfoDialogFragment newInstance(FamilyBean familyBean) {
        Bundle args = new Bundle();
        args.putSerializable("FamilyBean", familyBean);
        FamilyInfoDialogFragment fragment = new FamilyInfoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            familyBean = (FamilyBean) getArguments().getSerializable("FamilyBean");
        }
    }


    /**
     * 初始化View
     *
     * @param dialog
     * @param view
     */
    private void initView(Dialog dialog, View view) {

        //动画
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;

        Window window = dialog.getWindow();
//// 把 DecorView 的默认 padding 取消，同时 DecorView 的默认大小也会取消
        WindowManager.LayoutParams layoutParams = window.getAttributes();
// 设置宽度
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
// 给 DecorView 设置背景颜色，很重要，不然导致 Dialog 内容显示不全，有一部分内容会充当 padding，上面例子有举出
        dialog.setContentView(view, layoutParams);
        dialog.setCanceledOnTouchOutside(false);
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
                                            if (ObjectUtils.isNotEmpty(result.data.getWords_result().get姓名().getWords())) {
                                                mEtName.setText(result.data.getWords_result().get姓名().getWords());
                                            }
                                            if (ObjectUtils.isNotEmpty(result.data.getWords_result().get公民身份号码().getWords())) {
                                                mEtIdCard.setText(result.data.getWords_result().get公民身份号码().getWords());
                                            }
                                            if (ObjectUtils.isNotEmpty(result.data.getWords_result().get住址())) {
                                                mEtDz.setText(result.data.getWords_result().get住址().getWords());
                                            }
                                            try {
                                                Map<String, String> map = identityCard18(result.data.getWords_result().get公民身份号码().getWords());
                                                if (ObjectUtils.isNotEmpty(map.get("sex"))) {
                                                    mDdvXB.setText(map.get("sex"));
                                                }
                                                if (ObjectUtils.isNotEmpty(map.get("age"))) {
                                                    mEtNl.setText(map.get("age"));
                                                }
                                            } catch (Exception e) {

                                            }

                                            Glide.with(getActivity()).load(Uri.fromFile(file)).into(ivZheng);
                                            UserApi.updatePic(file, new BaseCallback<BaseResponse<String>>() {
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


                                            Glide.with(getActivity()).load(Uri.fromFile(file)).into(ivFan);
                                            UserApi.updatePic(file, new BaseCallback<BaseResponse<String>>() {
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

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 18位身份证获取性别和年龄
     *
     * @param CardCode
     * @return
     * @throws Exception
     */
    public static Map<String, String> identityCard18(String CardCode) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        // 得到年份
        String year = CardCode.substring(6).substring(0, 4);
        // 得到月份
        String month = CardCode.substring(10).substring(0, 2);
        //得到日
        //String day=CardCode.substring(12).substring(0,2);
        String sex;
        // 判断性别
        if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {
            sex = "女";
        } else {
            sex = "男";
        }
        // 得到当前的系统时间
        Date date = new Date();
        // 当前年份
        String currentYear = format.format(date).substring(0, 4);
        // 月份
        String currentMonth = format.format(date).substring(5, 7);
        //String currentdDay=format.format(date).substring(8,10);
        int age = 0;
//        // 当前月份大于用户出身的月份表示已过生日
//        if (Integer.parseInt(month) <= Integer.parseInt(currentMonth)) {
//            age = Integer.parseInt(currentYear) - Integer.parseInt(year) + 1;
//        } else {
        // 当前用户还没过生日
        age = Integer.parseInt(currentYear) - Integer.parseInt(year);
//        }
        map.put("sex", sex);
        map.put("age", age + "");
        return map;
    }

    @OnClick({R.id.tv_cancel, R.id.iv_close, R.id.tv_add, R.id.iv_zheng,R.id.iv_fan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_zheng:
                EasyPhotos.createAlbum(getActivity(),true, GlideEngine.getInstance())//参数说明：上下文，是否显示相机按钮，[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                        .setFileProviderAuthority("com.shaoyue.weizhegou.fileprovider")//参数说明：见下方`FileProvider的配置`
                        .setCount(1)//参数说明：最大可选数，默认1
                        .start(1001);
                break;
            case R.id.iv_fan:
                EasyPhotos.createAlbum(getActivity(),true, GlideEngine.getInstance())//参数说明：上下文，是否显示相机按钮，[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                        .setFileProviderAuthority("com.shaoyue.weizhegou.fileprovider")//参数说明：见下方`FileProvider的配置`
                        .setCount(1)//参数说明：最大可选数，默认1
                        .start(1002);

                break;
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_add:
                String name = mEtName.getText().toString().trim();
                String sfzh = mEtIdCard.getText().toString().trim();
                String nl = mEtNl.getText().toString().trim();
                String xb = mDdvXB.getText().toString().trim();
                String mz = mDdvMz.getText().toString().trim();
                String xw = mDdvXw.getText().toString().trim();
                String xl = mDdvXl.getText().toString().trim();
                String jkzk = mDdvJkzk.getText().toString().trim();
                String hyzk = mDdvHyzk.getText().toString().trim();
                String phoneNum = mEtPhoneNum.getText().toString().trim();
                String dz = mEtDz.getText().toString().trim();
                String zy = mEtZy.getText().toString().trim();
                String js = mDdvJs.getText().toString().trim();
                String ms = mEtMs.getText().toString().trim();

                if (ObjectUtils.isEmpty(name)) {
                    ToastUtil.showBlackToastSucess("姓名未填写");
                    return;
                }
                if (ObjectUtils.isEmpty(sfzh)) {
                    ToastUtil.showBlackToastSucess("身份证号未填写");
                    return;
                } else {
                    if (!RegexUtils.isIDCard18(sfzh)) {
                        ToastUtil.showBlackToastSucess("身份证号不正确");
                        return;
                    }
                }


                if (ObjectUtils.isEmpty(nl)) {
                    ToastUtil.showBlackToastSucess("年龄未填写");
                    return;
                }

                if (ObjectUtils.isEmpty(xb) || xb.contains("请选择")) {
                    ToastUtil.showBlackToastSucess("性别未选择");
                    return;
                }

                if (ObjectUtils.isEmpty(mz) || mz.contains("请选择")) {
                    ToastUtil.showBlackToastSucess("民族未选择");
                    return;
                }

                if (ObjectUtils.isEmpty(xw) || xw.contains("请选择")) {
                    ToastUtil.showBlackToastSucess("学位未选择");
                    return;
                }


                if (ObjectUtils.isEmpty(xl) || xl.contains("请选择学历")) {
                    ToastUtil.showBlackToastSucess("学历未选择");
                    return;
                }


                if (ObjectUtils.isEmpty(jkzk) || jkzk.contains("请选择健康状况")) {
                    ToastUtil.showBlackToastSucess("健康状况未选择");
                    return;
                }

                if (ObjectUtils.isEmpty(hyzk) || hyzk.contains("请选择婚姻状况")) {
                    ToastUtil.showBlackToastSucess("婚姻状况未选择");
                    return;
                }

                if (ObjectUtils.isEmpty(phoneNum)) {
                    ToastUtil.showBlackToastSucess("手机号码未填写");
                    return;
                }

                if (ObjectUtils.isEmpty(dz)) {
                    ToastUtil.showBlackToastSucess("地址未填写");
                    return;
                }
                if (ObjectUtils.isEmpty(zy)) {
                    ToastUtil.showBlackToastSucess("职业未填写");
                    return;
                }

                if (ObjectUtils.isEmpty(js)) {
                    ToastUtil.showBlackToastSucess("角色未填写");
                    return;
                }
                //	"description": "",
                //	"dz": "",
                //	"hyzk": "",
                //	"id": "",
                //	"jkzk": "",
                //	"js": "",
                //	"lxdh": "",
                //	"mz": "",
                //	"nl": 0,
                //	"sfzh": "",
                //	"sxid": "",
                //	"sxsfzh": "",
                //	"updateBy": "",
                //	"updateTime": "",
                //	"xb": "",
                //	"xl": "",
                //	"xm": "",
                //	"xw": "",
                //	"zy": ""
                if (ObjectUtils.isEmpty(familyBean)) {
                    Map<String, String> params = new HashMap<>();
                    params.put("description", ms);
                    params.put("dz", dz);
                    params.put("hyzk", hyzk);
                    params.put("jkzk", jkzk);
                    params.put("js", js);
                    params.put("lxdh", phoneNum);
                    params.put("mz", mz);
                    params.put("nl", nl);
                    params.put("sfzh", sfzh);
                    params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
                    params.put("sxsfzh", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
                    params.put("xb", xb);
                    params.put("xl", xl);
                    params.put("xm", name);
                    params.put("xw", xw);
                    params.put("zy", zy);
                    params.put("zmdz", sfzm);
                    params.put("fmdz", sffm);
                    CeditApi.addFamilyInfo(params, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            dismiss();
                            EventBus.getDefault().post(new RefreshBean());
                        }
                    }, this);
                } else {
                    Map<String, String> params = new HashMap<>();
                    params.put("description", ms);
                    params.put("dz", dz);
                    params.put("id", familyBean.getId());
                    params.put("hyzk", hyzk);
                    params.put("jkzk", jkzk);
                    params.put("js", js);
                    params.put("lxdh", phoneNum);
                    params.put("mz", mz);
                    params.put("nl", nl);
                    params.put("sfzh", sfzh);
                    params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
                    params.put("sxsfzh", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
                    params.put("xb", xb);
                    params.put("xl", xl);
                    params.put("xm", name);
                    params.put("xw", xw);
                    params.put("zy", zy);
//                    params.put("zmdz", sfzm);
//                    params.put("fmdz", sffm);
                    CeditApi.editFamilyInfo(params, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            dismiss();
                            EventBus.getDefault().post(new RefreshBean());
                        }
                    }, this);

                }
                break;
        }
    }


}