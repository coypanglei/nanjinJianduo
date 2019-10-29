package com.shaoyue.weizhegou.module.account.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.libracore.lib.widget.TimerButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CommApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.interfac.CommCallBack;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.CheckUtils;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：HQY on 17/7/8 15:47
 * 邮箱：hqy_xz@126.com
 */

public class PwdChangeFragment extends BaseTitleFragment {


    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_phone_num)
    TextView mTvPhoneNum;
    @BindView(R.id.et_verify_code)
    EditText mEtVerifyCode;
    @BindView(R.id.tb_code)
    TimerButton mTbCode;
    @BindView(R.id.et_pwd_new)
    EditText mEtPwdNew;
    @BindView(R.id.et_pwd_confirm)
    EditText mEtPwdConfirm;
    @BindView(R.id.sb_confirm)
    TextView mSbConfirm;
    @BindView(R.id.tv_title_password)
    TextView mTvTitlePassword;
    @BindView(R.id.tv_title_confirm)
    TextView mTvTitleConfirm;
    @BindView(R.id.id_description)
    TextView mIdDescription;


    private int type;

    private String title;

    private String phone;

    public static PwdChangeFragment newInstance(int type, String title, String phone) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putString("title", title);
        args.putString("phone", phone);
        PwdChangeFragment fragment = new PwdChangeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            type = getArguments().getInt("type");
            title = getArguments().getString("title");
            phone = getArguments().getString("phone");
        } else {
            removeFragment();
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_pwd_change;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle(title);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mTvPhoneNum.setText(phone);
        if (type == 2) {
            mEtPwdConfirm.setHint("确认新密码");
            mEtPwdNew.setHint("输入新密码");
            mTvTitleConfirm.setText("确认新密码");
            mTvTitlePassword.setText("输入新密码");
            mIdDescription.setText("登录密码为6-12位字母加数字组合");
        }
        if (title.equals("设置支付密码") || title.equals("设置登录密码")) {
            mSbConfirm.setText("保存");
        }
    }


    @OnClick({R.id.sb_confirm, R.id.tb_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_confirm:
                changePwd();
                break;
            case R.id.tb_code:
                CommApi.fetchPhoneCode(phone, "2", null, new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        mTbCode.start();
                    }

                    @Override
                    public void onFail(ApiException apiError) {
                        ToastUtil.showErrorToast(apiError.getErrMsg());

                    }
                }, this);
                break;
        }
    }

    /**
     * 修改密码
     */
    private void changePwd() {
        final String newPwd = mEtPwdNew.getText().toString().trim();
        String confirmPwd = mEtPwdConfirm.getText().toString().trim();
        String verifyCode = mEtVerifyCode.getText().toString().trim();
        if (!CheckUtils.checkOldNewConfirmPwd(newPwd, confirmPwd, verifyCode)) {
            return;
        }
        UserApi.setPassword(type, phone, newPwd, verifyCode, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showSuccessToast("保存成功");
                removeFragment();
            }
        }, this);

    }


}
