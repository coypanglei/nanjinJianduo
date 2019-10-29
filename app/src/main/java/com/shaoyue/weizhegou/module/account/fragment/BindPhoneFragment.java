package com.shaoyue.weizhegou.module.account.fragment;

import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;

import android.widget.EditText;


import com.libracore.lib.widget.TimerButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;

import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CommApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;

import com.shaoyue.weizhegou.util.CheckUtils;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;

import butterknife.OnClick;


/**
 * 作者：PangLei on 2019/5/11 0011 09:30
 * <p>
 * 邮箱：434604925@qq.com
 */
public class BindPhoneFragment extends BaseTitleFragment {


    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tb_code)
    TimerButton mTbCode;
    @BindView(R.id.et_verify_code)
    EditText mEtVerifyCode;
    @BindView(R.id.et_pwd_new)
    EditText mEtPwdNew;


    public static BindPhoneFragment newInstance() {

        Bundle args = new Bundle();

        BindPhoneFragment fragment = new BindPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_bind_phone;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("绑定手机");
    }


    @OnClick({R.id.tb_code, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tb_code:
                sendCode();
                break;
            case R.id.sb_confirm:
                bindPhone();
                break;
        }
    }

    /**
     * 绑定手机号
     */
    private void bindPhone() {
        String phone = mEtPhone.getText().toString().trim();
        String code = mEtVerifyCode.getText().toString().trim();
        String password = mEtPwdNew.getText().toString().trim();
        if (!CheckUtils.phoneCodeCheck(phone, code, password)) {
            return;
        }


        UserApi.bindPhone(phone, password, code, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {

                ToastUtil.showSuccessToast("绑定成功");
                removeFragment();
            }

        }, this);

    }

    /**
     * 发送手机验证码
     */
    private void sendCode() {
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showErrorToast("手机号不能为空");
            return;
        }
        //3绑定验证码
        CommApi.fetchPhoneCode(phone, "3", null, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                mTbCode.start();
            }


        }, this);
    }


}
