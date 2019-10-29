package com.shaoyue.weizhegou.module.account.fragment;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.AuthUser;
import com.shaoyue.weizhegou.entity.user.WebBean;
import com.shaoyue.weizhegou.interfac.CommCallBack;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginFragment extends BaseTitleFragment {


    @BindView(R.id.et_login_name)
    EditText mPhoneEditText;

    @BindView(R.id.et_login_password)
    EditText mPasswordEditText;
    @BindView(R.id.iv_email_ic)
    ImageView mIvEmailIc;
    @BindView(R.id.iv_email_cancel)
    ImageView mIvEmailCancel;
    @BindView(R.id.iv_email_password)
    ImageView mIvEmailPassword;
    @BindView(R.id.iv_password_visibility)
    ImageView mIvPasswordVisibility;


    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);

        setCommonTitle("登录");
        mPhoneEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mIvEmailIc.setImageDrawable(getResources().getDrawable(R.drawable.ic_select_email));
                    mIvEmailCancel.setImageDrawable(getResources().getDrawable(R.drawable.ic_select_cancel));
                } else {
                    mIvEmailIc.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_email));
                    mIvEmailCancel.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_cancel));
                }
            }
        });
        mPasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
    }


    @OnClick({R.id.iv_email_cancel, R.id.iv_password_visibility, R.id.tv_agreement, R.id.tv_register, R.id.iv_wechat_login, R.id.tv_phone_login,
            R.id.tv_pwd_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pwd_reset:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.FORGET_PASSWORD);
                break;
            case R.id.iv_email_cancel:
                //置空
                mPhoneEditText.setText("");
                break;
            case R.id.iv_password_visibility:
                visibilityPassword();
                break;
            case R.id.tv_agreement:
                UserApi.getWebview("user_agreement", new BaseCallback<BaseResponse<WebBean>>() {
                    @Override
                    public void onSucc(BaseResponse<WebBean> result) {
                        if (ObjectUtils.isNotEmpty(result.data)) {
                            UIHelper.showWebPage(getActivity(), result.data.getTitle(), result.data.getUrl());
                        }
                    }
                }, this);
                break;
            case R.id.tv_register:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.REGISTER);
                break;
            case R.id.iv_wechat_login:
                //是否安装微信
                if (AppUtils.isAppInstalled("com.tencent.mm")) {
                    UMShareConfig config = new UMShareConfig();
                    config.isNeedAuthOnGetUserInfo(true);
                    UMShareAPI wxAPI = UMShareAPI.get(getActivity());
                    wxAPI.setShareConfig(config);
                    wxAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, mAuthListener);
                } else {
                    ToastUtil.showToast(getActivity().getResources().getString(R.string.tv_install_wachet));
                }
                break;
            case R.id.tv_phone_login:
                doPhoneLogin();
                break;

        }

    }

    /**
     * 显示隐藏密码
     */
    private void visibilityPassword() {
        if (mPasswordEditText.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            mPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            mPasswordEditText.setSelection(mPasswordEditText.getText().length());
        } else {
            mPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mPasswordEditText.setSelection(mPasswordEditText.getText().length());
        }

    }

    UMAuthListener mAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            AuthUser authUser = new AuthUser();
            if (share_media.toString().equals("QQ")) {
                authUser.setAuthType("qq");
            } else if (share_media.toString().equals("WEIXIN")) {
                authUser.setAuthType("weixin");
            }
            for (String key : map.keySet()) {
                if (key.equals("unionid")) {
                    authUser.setUnionid(map.get(key));
                }
                if (key.equals("openid")) {
                    authUser.setUid(map.get(key));
                }
                if (key.equals("name")) {
                    authUser.setNickName(map.get(key));
                }

                if (key.equals("iconurl")) {
                    authUser.setAvatar(map.get(key));
                }

                if (key.equals("gender")) {
                    if (map.get(key).equals("男")) {
                        authUser.setSex("0");
                    } else {
                        authUser.setSex("1");
                    }
                }

            }

            UserMgr.getInstance().doThirdLogin(authUser, new CommCallBack() {
                @Override
                public void complete(int code, String msg) {
                    if (code == 0) {
                        ToastUtil.showSuccToast(msg);
                        if (ObjectUtils.isNotEmpty(getActivity())) {
                            getActivity().finish();
                        }
                    } else {
                        ToastUtil.showErrorToast(msg);
                    }

                }
            });
        }

        @Override
        public void onStart(SHARE_MEDIA share_media) {
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            stopProgressDialog();
            ToastUtil.showToast(getActivity().getResources().getString(R.string.login_gain_info_error));
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            stopProgressDialog();
            ToastUtil.showToast(getActivity().getResources().getString(R.string.login_cancel));
        }
    };


    /**
     * 手机号登录
     */

    private void doPhoneLogin() {
        // TODO: 次数多要加验证码
        final String phone = mPhoneEditText.getText().toString();
        final String password = mPasswordEditText.getText().toString();

        startProgressDialog(false);
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


}
