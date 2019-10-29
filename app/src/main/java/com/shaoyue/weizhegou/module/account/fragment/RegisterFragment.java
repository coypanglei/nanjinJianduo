package com.shaoyue.weizhegou.module.account.fragment;


import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.libracore.lib.widget.TimerButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CommApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.interfac.CommCallBack;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.CheckUtils;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class RegisterFragment extends BaseTitleFragment {


    @BindView(R.id.et_phone)
    EditText mPhoneField;
    @BindView(R.id.et_password)
    EditText mPasswordField;

    @BindView(R.id.tb_code)
    TimerButton mTimerButton;

    @BindView(R.id.et_verify_code)
    EditText mVerifyCodeField;
    @BindView(R.id.iv_email_ic)
    ImageView mIvEmailIc;
    @BindView(R.id.iv_email_password)
    ImageView mIvEmailPassword;
    @BindView(R.id.iv_code)
    ImageView mIvCode;
    @BindView(R.id.iv_password_visibility)
    ImageView mIvPasswordVisibility;
    @BindView(R.id.iv_referrer)
    ImageView mIvReferrer;
    @BindView(R.id.et_referrer_id)
    EditText mEtReferrerId;
    @BindView(R.id.iv_image_code)
    ImageView mIvImageCode;
    @BindView(R.id.iv_verify_code)
    ImageView mIvVerifyCode;
    @BindView(R.id.et_iv_verify_code)
    EditText mEtIvVerifyCode;
    @BindView(R.id.iv_phone_cancel)
    ImageView mIvPhoneCancel;


    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setCommonTitle("注册");
        initView();
    }


    @OnClick({R.id.tb_code, R.id.sb_register_confirm, R.id.iv_password_visibility, R.id.iv_phone_cancel, R.id.tv_agreement})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tb_code:
                sendCode();
                break;
            case R.id.sb_register_confirm:
                register();
                break;
            case R.id.iv_password_visibility:
                visibilityPassword();
                break;
            case R.id.iv_phone_cancel:
                //置空
                mPhoneField.setText("");
                break;
            case R.id.tv_agreement:
                UIHelper.showWebPage(getActivity(), getResources().getString(R.string.content_user_service_protocol), AppMgr.getInstance().getAgreementUrl());
                break;
        }
    }

    /**
     * 发送手机验证码
     */
    private void sendCode() {
        String phone = mPhoneField.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showErrorToast("手机号不能为空");
            return;
        }
        //1注册
        CommApi.fetchPhoneCode(phone, "1", null, new BaseCallback<BaseResponse<Void>>() {
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

    /**
     * 初始化View
     */
    private void initView() {
        mPhoneField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mIvEmailIc.setImageDrawable(getResources().getDrawable(R.drawable.ic_select_email));
                    mIvPhoneCancel.setImageDrawable(getResources().getDrawable(R.drawable.ic_select_cancel));
                } else {
                    mIvEmailIc.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_email));
                    mIvPhoneCancel.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_cancel));
                }
            }
        });
        mPasswordField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mIvEmailPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_select_password));
                    mIvPasswordVisibility.setImageDrawable(getResources().getDrawable(R.drawable.ic_select_visibility));
                } else {
                    mIvEmailPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_password));
                    mIvPasswordVisibility.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_visibility));
                }
            }
        });
        mVerifyCodeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mIvCode.setImageDrawable(getResources().getDrawable(R.drawable.ic_select_phone_code));
                } else {
                    mIvCode.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_phone_code));

                }
            }
        });
        mEtReferrerId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mIvReferrer.setImageDrawable(getResources().getDrawable(R.drawable.ic_select_referrer));
                } else {
                    mIvReferrer.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_referrer));
                }
            }
        });
        mEtIvVerifyCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mIvImageCode.setImageDrawable(getResources().getDrawable(R.drawable.ic_select_iv_code));
                } else {
                    mIvImageCode.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_iv_code));
                }
            }
        });


    }


    /**
     * 显示隐藏密码
     */
    private void visibilityPassword() {
        if (mPasswordField.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            mPasswordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            mPasswordField.setSelection(mPasswordField.getText().length());
        } else {
            mPasswordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mPasswordField.setSelection(mPasswordField.getText().length());
        }

    }

    /**
     * 注册
     */
    private void register() {
        final String phone = mPhoneField.getText().toString().trim();
        final String password = mPasswordField.getText().toString().trim();
        final String verifyCode = mVerifyCodeField.getText().toString().trim();
        final String id = mEtReferrerId.getText().toString().trim();
        if (!CheckUtils.phonePwdCode(phone, password, verifyCode)) {
            return;
        }
        startProgressDialog(false);
        UserMgr.getInstance().doRegister(phone, password, verifyCode, id, new CommCallBack() {
            @Override
            public void complete(int code, String msg) {
                stopProgressDialog();
                if (code == 0) {
                    ToastUtil.showSuccToast(msg);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            UserMgr.getInstance().doLoginByPhone(phone, password, new CommCallBack() {
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

                            },this);
                        }
                    }, 1500);
                } else {
                    ToastUtil.showErrorToast(msg);
                }
            }
        });

    }


}
