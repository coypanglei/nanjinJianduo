package com.shaoyue.weizhegou.module.user.fragment;

import android.os.Bundle;

import android.view.View;

import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.shaoyue.weizhegou.R;

import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AccountApi;

import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.util.ToastUtil;


import butterknife.BindView;

import butterknife.OnClick;


public class ModifyPasswordFragment extends BaseTitleFragment {

    @BindView(R.id.tv_account)
    TextView mTvAccount;
    @BindView(R.id.tv_error_msg)
    TextView mTvErrorMsg;
    @BindView(R.id.et_old)
    EditText mEtOld;
    @BindView(R.id.et_new_pass)
    EditText mEtNewPass;
    @BindView(R.id.et_ok_pass)
    EditText mEtOkPass;

    public static ModifyPasswordFragment newInstance() {

        Bundle args = new Bundle();

        ModifyPasswordFragment fragment = new ModifyPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_modify_password;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("修改密码").setRightBtn("退出系统", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).setDrawableLeft(R.drawable.icon_logout);
        mTvAccount.setText(SPUtils.getInstance().getString(UserMgr.SP_LOGIN_NAME));
    }


    @OnClick(R.id.tv_ok)
    public void onViewClicked() {
        String oldPassword = mEtOld.getText().toString().trim();
        String newPassword = mEtNewPass.getText().toString().trim();
        final String okPassword = mEtOkPass.getText().toString().trim();
        if (ObjectUtils.isEmpty(oldPassword) || ObjectUtils.isEmpty(newPassword) || ObjectUtils.isEmpty(okPassword)) {

            mTvErrorMsg.setText("密码不能为空");
            return;
        }
        if (!ObjectUtils.equals(oldPassword, SPUtils.getInstance().getString(UserMgr.SP_LOGIN_PASSWORD))) {

            mTvErrorMsg.setText("原密码不正确");
            return;
        }
        if (!ObjectUtils.equals(newPassword, okPassword)) {

            mTvErrorMsg.setText("两次输入密码不一致");
            return;
        }

        AccountApi.changePwd(SPUtils.getInstance().getString(UserMgr.SP_LOGIN_NAME), oldPassword, newPassword, okPassword, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                SPUtils.getInstance().put(UserMgr.SP_LOGIN_PASSWORD, okPassword);
                ToastUtil.showBlackToastSucess("保存成功");
                removeFragment();
            }
        }, this);

    }
}
