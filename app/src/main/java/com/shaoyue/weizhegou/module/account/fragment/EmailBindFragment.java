package com.shaoyue.weizhegou.module.account.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.libracore.lib.widget.TimerButton;

import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.callback.DialogCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AccountApi;
import com.shaoyue.weizhegou.api.remote.CommApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.util.CheckUtils;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by USER on 2018/5/5.
 */

public class EmailBindFragment extends BaseTitleFragment {

    @BindView(com.shaoyue.weizhegou.R.id.et_email_name)
    EditText mEmailNameFiled;

    @BindView(com.shaoyue.weizhegou.R.id.et_pwd_new)
    EditText mNewPwdField;

    @BindView(com.shaoyue.weizhegou.R.id.et_pwd_confirm)
    EditText mConfirmPwdField;


    @BindView(com.shaoyue.weizhegou.R.id.tb_code)
    TimerButton mTimerButton;


    @BindView(com.shaoyue.weizhegou.R.id.et_verify_code)
    EditText mVerifyCodeField;


    @Override
    protected int getContentLayoutId() {
        return com.shaoyue.weizhegou.R.layout.fragment_email_bind;
    }

    public static EmailBindFragment newInstance() {

        Bundle args = new Bundle();

        EmailBindFragment fragment = new EmailBindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle(com.shaoyue.weizhegou.R.string.title_binding_mailbox);
    }


    @OnClick({com.shaoyue.weizhegou.R.id.sb_confirm, com.shaoyue.weizhegou.R.id.tb_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case com.shaoyue.weizhegou.R.id.sb_confirm:
                bindEmail();
                break;

            case com.shaoyue.weizhegou.R.id.tb_code:
                sendEmailCode();
                break;
        }


    }

    private void bindEmail() {
        final String email = mEmailNameFiled.getText().toString().trim();
        final String password = mNewPwdField.getText().toString().trim();
        String confirmPwd = mConfirmPwdField.getText().toString().trim();
        String verifyCode = mVerifyCodeField.getText().toString().trim();

        if (!CheckUtils.emailCodePwdConfirm(email, verifyCode, password, confirmPwd)) {
            return;
        }

        AccountApi.bindEmail(email, password, verifyCode, new DialogCallback<BaseResponse<Void>>(getActivity()) {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showSuccToast("邮箱绑定成功");
                UserMgr.getInstance().setPassword(password);
                UserMgr.getInstance().setEmail(email);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        removeFragment();
                    }
                }, 1500);
            }
        }, this);
    }


    private void sendEmailCode() {
        String email = mEmailNameFiled.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            ToastUtil.showErrorToast("邮箱不能为空");

            return;
        }

        CommApi.fetchPhoneCode(email, "change_bind", null, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
             mTimerButton.start();
            }

            @Override
            public void onFail(ApiException apiError) {
                ToastUtil.showErrorToast(apiError.getErrMsg());

            }
        }, this);
    }
}
