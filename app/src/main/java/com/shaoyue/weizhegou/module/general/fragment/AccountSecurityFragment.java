package com.shaoyue.weizhegou.module.general.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.View;

import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AccountApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.AuthUser;
import com.shaoyue.weizhegou.entity.LoginBean;
import com.shaoyue.weizhegou.entity.center.VerifiedNoticeBean;
import com.shaoyue.weizhegou.entity.user.SafeInfoBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;

import com.shaoyue.weizhegou.module.account.fragment.BindPhoneFragment;
import com.shaoyue.weizhegou.module.account.fragment.PwdChangeFragment;
import com.shaoyue.weizhegou.module.account.fragment.UntiedPhoneFragment;
import com.shaoyue.weizhegou.module.account.fragment.VerifiedFragment;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.BindView;

import butterknife.OnClick;


/**
 * 作者：PangLei on 2019/5/8 0008 15:33
 * <p>
 * 邮箱：434604925@qq.com
 */
public class AccountSecurityFragment extends BaseTitleFragment {


    @BindView(R.id.tv_weichat_name)
    TextView mTvWeichatName;
    @BindView(R.id.tv_phone_num)
    TextView mTvPhoneNum;
    @BindView(R.id.tv_realname)
    TextView mTvRealname;
    @BindView(R.id.tv_change_pay_password)
    TextView mTvChangePayPassword;
    @BindView(R.id.tv_change_password)
    TextView mTvChangePassword;

    private String payPasswordTitle;
    private String passwordTitle;

    private String mobile;

    public static AccountSecurityFragment newInstance() {
        Bundle args = new Bundle();
        AccountSecurityFragment fragment = new AccountSecurityFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_account_security;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("账户安全");
        getAccountSafeInfo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getAccountSafeInfo();
    }

    private void getAccountSafeInfo() {
        UserApi.getSafeInfo(new BaseCallback<BaseResponse<SafeInfoBean>>() {
            @Override
            public void onSucc(BaseResponse<SafeInfoBean> result) {
                SafeInfoBean mSafeInfoBean = result.data;
                if ("1.0.x.g.q.q.x.e.s.f.r.xs.ds.fe.vrt.cnm".equals(mSafeInfoBean.getWechatNickName())) {
                    mTvWeichatName.setText("去绑定");
                    mTvWeichatName.setTextColor(getResources().getColor(R.color.color_cd0946));
                } else {
                    mTvWeichatName.setText(mSafeInfoBean.getWechatNickName());
                    mTvWeichatName.setTextColor(getResources().getColor(R.color.color_a4a4a4));
                    mTvWeichatName.setCompoundDrawables(null, null, null, null);
                }
                if (ObjectUtils.isEmpty(mSafeInfoBean.getMobile())) {
                    mTvPhoneNum.setText("去绑定");
                    mTvPhoneNum.setTextColor(getResources().getColor(R.color.color_cd0946));
                } else {
                    mobile = mSafeInfoBean.getMobile();
                    mTvPhoneNum.setText(mSafeInfoBean.getMobile());
                    mTvPhoneNum.setTextColor(getResources().getColor(R.color.color_a4a4a4));
                }
                if (ObjectUtils.isEmpty(mSafeInfoBean.getRealName())) {
                    mTvRealname.setText("去绑定");
                    mTvRealname.setTextColor(getResources().getColor(R.color.color_cd0946));
                } else {
                    mTvRealname.setText(mSafeInfoBean.getRealName());
                    mTvRealname.setTextColor(getResources().getColor(R.color.color_a4a4a4));
                    mTvRealname.setCompoundDrawables(null, null, null, null);
                }

                if (mSafeInfoBean.getIsPayPassword().equals("1")) {
                    mTvChangePayPassword.setText("修改支付密码");
                    payPasswordTitle = "修改支付密码";
                    mTvChangePayPassword.setCompoundDrawables(null, null, null, null);
                } else {
                    mTvChangePayPassword.setText("设置支付密码");
                    payPasswordTitle = "设置支付密码";
                    Drawable drawable = getResources().getDrawable(R.drawable.icon_notice);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                            drawable.getMinimumHeight());
                    mTvChangePayPassword.setCompoundDrawables(null, null, drawable, null);
                }
                if (mSafeInfoBean.getIsPassword().equals("1")) {
                    mTvChangePassword.setText("修改密码");
                    passwordTitle = "修改登录密码";
                } else {
                    mTvChangePassword.setText("设置密码");
                    passwordTitle = "设置登录密码";
                }

            }
        }, this);
    }

    /**
     * 跳转到绑定手机号界面
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmBind() == 2) {
            addFragment(BindPhoneFragment.newInstance());
        }
    }


    @OnClick({R.id.rl_go_pay_password, R.id.rl_go_password, R.id.rl_go_phone, R.id.rl_realname, R.id.rl_bind_weichat, R.id.tv_change_pay_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_go_pay_password:
                if ("去绑定".equals(mTvPhoneNum.getText().toString().trim())) {
                    UIHelper.showjumpDialog(getActivity());
                } else {
                    addFragment(PwdChangeFragment.newInstance(1, payPasswordTitle, mobile));
                }
                break;
            case R.id.rl_go_password:
                if ("去绑定".equals(mTvPhoneNum.getText().toString().trim())) {
                    UIHelper.showjumpDialog(getActivity());
                } else {
                    addFragment(PwdChangeFragment.newInstance(2, passwordTitle, mobile));
                }
                break;
            case R.id.rl_go_phone:
                if ("去绑定".equals(mTvPhoneNum.getText().toString().trim())) {
                    addFragment(BindPhoneFragment.newInstance());
                } else {
                    addFragment(UntiedPhoneFragment.newInstance(mobile));
                }
                break;
            case R.id.rl_realname:
                if ("去绑定".equals(mTvRealname.getText().toString().trim())) {
                    addFragment(VerifiedFragment.newInstance());
                }
                break;
            case R.id.rl_bind_weichat:
                if ("去绑定".equals(mTvWeichatName.getText().toString().trim())) {
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
                }
                break;
            case R.id.tv_change_pay_password:
                if ("设置支付密码".equals(payPasswordTitle)) {
                    AccountApi.getPayPasswordNotice(new BaseCallback<BaseResponse<VerifiedNoticeBean>>() {
                        @Override
                        public void onSucc(BaseResponse<VerifiedNoticeBean> result) {
                            UIHelper.showVerifiedFile(getActivity(), "设置支付密码须知", result.data.getContent());
                        }
                    }, this);
                }
                break;
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

            AccountApi.bindWeichat(authUser.getUnionid(), authUser.getUid(), authUser.getNickName(), authUser.getAvatar(), new BaseCallback<BaseResponse<LoginBean>>() {
                @Override
                public void onSucc(BaseResponse<LoginBean> result) {
                    ToastUtil.showSuccToast("绑定成功");
                    getAccountSafeInfo();
                }
            }, this);
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

}
