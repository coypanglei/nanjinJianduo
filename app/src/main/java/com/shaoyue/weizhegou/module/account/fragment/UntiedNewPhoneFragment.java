package com.shaoyue.weizhegou.module.account.fragment;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import android.widget.EditText;

import com.blankj.utilcode.util.ObjectUtils;
import com.libracore.lib.widget.TimerButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CommApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;

import butterknife.OnClick;

import static com.shaoyue.weizhegou.router.ContentType.ACCOUNT_SECRITY;

/**
 * 作者：PangLei on 2019/5/11 0011 15:12
 * <p>
 * 邮箱：434604925@qq.com
 */
public class UntiedNewPhoneFragment extends BaseTitleFragment {


    @BindView(R.id.et_new_phone)
    EditText mEtNewPhone;
    @BindView(R.id.tb_code)
    TimerButton mTbCode;
    @BindView(R.id.et_verify_code)
    EditText mEtVerifyCode;

    private String oldCode;


    public static UntiedNewPhoneFragment newInstance(String oldCode) {

        Bundle args = new Bundle();
        args.putString("oldCode", oldCode);

        UntiedNewPhoneFragment fragment = new UntiedNewPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("修改手机号");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            oldCode = getArguments().getString("oldCode");
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_change_new_bind_phone;
    }


    @OnClick({R.id.tb_code, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tb_code:
                sendCode();
                break;
            case R.id.sb_confirm:
                bindNewPhone();
                break;
        }
    }


    /**
     * 发送手机验证码
     */
    private void sendCode() {
        String phoneNum = mEtNewPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtil.showErrorToast("手机号不能为空");
            return;
        }
        //3 解绑验证码
        CommApi.fetchPhoneCode(phoneNum, "6", null, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                mTbCode.start();
            }
        }, this);
    }

    /**
     * 换绑手机号
     */
    private void bindNewPhone() {
        String phone = mEtNewPhone.getText().toString().trim();
        String code = mEtVerifyCode.getText().toString().trim();
        if (ObjectUtils.isEmpty(phone)) {
            ToastUtil.showErrorToast("手机号不能为空");
            return;
        }
        if (ObjectUtils.isEmpty(code)) {
            ToastUtil.showErrorToast("验证码不能为空");
            return;
        }
        UserApi.bindNewPhone(oldCode, phone, code, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {

                ToastUtil.showSuccessToast("绑定成功");
                getActivity().finish();
                UIHelper.showProfileCommonActivity(getActivity(), ACCOUNT_SECRITY);

            }
        }, this);
    }

}
