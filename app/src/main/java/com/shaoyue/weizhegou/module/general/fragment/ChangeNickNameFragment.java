package com.shaoyue.weizhegou.module.general.fragment;

import android.os.Bundle;

import android.view.View;

import android.widget.EditText;


import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.user.UserInfoBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;


/**
 * 作者：PangLei on 2019/5/9 0009 16:07
 * <p>
 * 邮箱：434604925@qq.com
 */
public class ChangeNickNameFragment extends BaseTitleFragment {


    @BindView(R.id.edit_nick_name)
    EditText mEditNickName;


    public static ChangeNickNameFragment newInstance() {

        Bundle args = new Bundle();

        ChangeNickNameFragment fragment = new ChangeNickNameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mEditNickName.setText(UserMgr.getInstance().getUsername());
        setCommonTitle("修改昵称").setRightBtn("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nickName = mEditNickName.getText().toString().trim();
                if (ObjectUtils.isEmpty(nickName)) {
                    ToastUtil.showErrorToast("内容不能为空");
                    return;
                }
                UserApi.changeNickName(nickName, new BaseCallback<BaseResponse<UserInfoBean>>() {
                    @Override
                    public void onSucc(BaseResponse<UserInfoBean> result) {
                        ToastUtil.showSuccessToast("保存成功");
                        UserMgr.getInstance().setUsername(nickName);
                        removeFragment();
                    }


                }, this);
            }
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_nick_name;
    }


}
