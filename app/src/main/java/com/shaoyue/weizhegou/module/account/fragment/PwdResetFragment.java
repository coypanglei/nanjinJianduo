package com.shaoyue.weizhegou.module.account.fragment;


import android.text.TextUtils;

import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.libracore.lib.widget.TimerButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;

import com.shaoyue.weizhegou.api.remote.CommApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.interfac.CommCallBack;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.CheckUtils;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;

import butterknife.OnClick;


public class PwdResetFragment extends BaseTitleFragment {


    @BindView(R.id.iv_phone_ic)
    ImageView mIvPhoneIc;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.iv_phone_cancel)
    ImageView mIvPhoneCancel;
    @BindView(R.id.iv_code)
    ImageView mIvCode;
    @BindView(R.id.tb_code)
    TimerButton mTbCode;
    @BindView(R.id.et_verify_code)
    EditText mEtVerifyCode;
    @BindView(R.id.tv_login)
    TextView mTvLogin;


    public static PwdResetFragment newInstance() {
        return new PwdResetFragment();
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_pwd_reset;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("验证码登录");
        initView();
    }

    //初始化View
    private void initView() {
        mEtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mIvPhoneIc.setImageDrawable(getResources().getDrawable(R.drawable.ic_select_email));
                    mIvPhoneCancel.setImageDrawable(getResources().getDrawable(R.drawable.ic_select_cancel));
                } else {
                    mIvPhoneIc.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_email));
                    mIvPhoneCancel.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_cancel));
                }
            }
        });
        mEtVerifyCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mIvCode.setImageDrawable(getResources().getDrawable(R.drawable.ic_select_phone_code));
                } else {
                    mIvCode.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_phone_code));

                }
            }
        });

    }


    @OnClick({R.id.tb_code, R.id.tv_login, R.id.iv_phone_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tb_code:
                sendCode();
                break;
            case R.id.tv_login:
                phoneLogin();
                break;
            case R.id.iv_phone_cancel:
                mEtPhone.setText("");
                break;
        }
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
        //4验证码登录
        CommApi.fetchPhoneCode(phone, "4", null, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                mTbCode.start();
            }

            @Override
            public void onFail(ApiException apiError) {
                ToastUtil.showErrorToast(apiError.getErrMsg());
            }
        }, this);
    }

    /**
     * 手机验证码登录
     */
    private void phoneLogin() {
        final String phone = mEtPhone.getText().toString().trim();
        final String code = mEtVerifyCode.getText().toString().trim();
        if (!CheckUtils.phoneCodeCheck(phone, code)) {
            return;
        }
        startProgressDialog(true);
        UserMgr.getInstance().doLoginByPhoneCode(phone, code, new CommCallBack() {
            @Override
            public void complete(int code, String msg) {
                stopProgressDialog();
                if (code == 0) {
                    ToastUtil.showSuccToast(msg);
                    UIHelper.showMainPage(getActivity());
                } else {
                    ToastUtil.showErrorToast(msg);
                }
            }
        });
    }
//
//    private void resetPwd() {
//        String emailName = mEmailNameFiled.getText().toString().trim();
//        String newPwd = mNewPwdField.getText().toString().trim();
//        String confirmPwd = mConfirmPwdField.getText().toString().trim();
//        String verifyCode = mVerifyCodeField.getText().toString().trim();
//        if (!CheckUtils.emailCodePwdConfirm(emailName, verifyCode, newPwd, confirmPwd)) {
//            return;
//        }
//
//
//        startProgressDialog(false);
//        UserMgr.getInstance().doResetPwd(emailName, newPwd, verifyCode, new CommCallBack() {
//            @Override
//            public void complete(int code, String msg) {
//                stopProgressDialog();
//                if (code == 0) {
//                    ToastUtil.showSuccToast(msg);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            removeFragment();
//                        }
//                    }, 1500);
//                } else {
//                    ToastUtil.showErrorToast(msg);
//                }
//
//
//            }
//        });
//
//
//    }


}
