package com.shaoyue.weizhegou.module.account.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.libracore.lib.widget.TimerButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CommApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.util.CheckUtils;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：HQY on 17/7/17 14:54
 * 邮箱：hqy_xz@126.com
 */

public class BindPhoneDialogFragment extends DialogFragment {

    Unbinder unbinder;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.tb_code)
    TimerButton mTbCode;
    @BindView(R.id.et_verify_code)
    EditText mEtVerifyCode;
    @BindView(R.id.et_referrer_id)
    EditText mEtReferrerId;


    public static BindPhoneDialogFragment newInstance() {
        Bundle args = new Bundle();

        BindPhoneDialogFragment fragment = new BindPhoneDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_perfect_individual, null);
        unbinder = ButterKnife.bind(this, view);


        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;
        dialog.setContentView(view);

        dialog.setCanceledOnTouchOutside(true);
        ButterKnife.bind(this, view);

        return dialog;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStop() {
        super.onStop();
        mTbCode.stop();
    }

    @OnClick({R.id.iv_close, R.id.tv_bind, R.id.tb_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_bind:
                bindPhone();
                break;
            case R.id.tb_code:
                sendCode();
                break;
        }
    }

    /**
     * 绑定手机号
     */
    private void bindPhone() {
        String phone = mEtPhone.getText().toString().trim();
        String code = mEtVerifyCode.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        String referrer = mEtReferrerId.getText().toString().trim();
        if (!CheckUtils.phoneCodeIDCheck(phone, code, password)) {
            return;
        }
        UserApi.updateInfo(phone, code, password, referrer, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showBlackToastSucess("保存成功");
                dismiss();
            }

            @Override
            public void onFail(ApiException apiError) {
                ToastUtil.showBlackToastSucess(apiError.getMessage());
            }
        }, this);
    }

    /**
     * 发送手机验证码
     */
    private void sendCode() {
        String phoneNum = mEtPhone.getText().toString().trim();

        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtil.showErrorToast("手机号不能为空");
            return;
        }
        //5 解绑验证码
        CommApi.fetchPhoneCode(phoneNum, "5", null, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                mTbCode.start();
            }


        }, this);
    }


}
