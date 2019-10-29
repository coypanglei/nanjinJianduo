package com.shaoyue.weizhegou.module.credit.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.entity.cedit.FamilyBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.DropDownView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
        initView(dialog, view);

        return dialog;
    }


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
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }


    @OnClick({R.id.tv_cancel, R.id.iv_close, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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