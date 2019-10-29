package com.shaoyue.weizhegou.module.account.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import android.view.View;

import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.libracore.lib.widget.TimerButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CommApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;

import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;

import butterknife.OnClick;


/**
 * 作者：PangLei on 2019/5/11 0011 14:53
 * <p>
 * 邮箱：434604925@qq.com
 */
public class UntiedPhoneFragment extends BaseTitleFragment {

    @BindView(R.id.tb_code)
    TimerButton mTbCode;
    @BindView(R.id.et_verify_code)
    EditText mEtVerifyCode;
    @BindView(R.id.sb_confirm)
    TextView mSbConfirm;

    @BindView(R.id.tv_phone_num)
    TextView mTvPhoneNum;

    private String phoneNum;

    public static UntiedPhoneFragment newInstance(String phone) {

        Bundle args = new Bundle();
        args.putString("phone", phone);
        UntiedPhoneFragment fragment = new UntiedPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            phoneNum = getArguments().getString("phone");
        }
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("修改手机号");
        mTvPhoneNum.setText(phoneNum);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_change_bind_phone;
    }

    @Override
    public void onStop() {
        super.onStop();
        mTbCode.stop();
    }

    @OnClick({R.id.tb_code, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tb_code:
                sendCode();
                break;
            case R.id.sb_confirm:
                String mCode = mEtVerifyCode.getText().toString().trim();
                if (ObjectUtils.isEmpty(mCode)) {
                    ToastUtil.showErrorToast("验证码为空");
                    return;
                }
                addFragment(UntiedNewPhoneFragment.newInstance(mCode));
                break;
        }
    }


    /**
     * 发送手机验证码
     */
    private void sendCode() {
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
