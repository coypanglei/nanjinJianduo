package com.shaoyue.weizhegou.module.account.fragment;

import android.os.Bundle;

import android.view.View;

import android.widget.EditText;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AccountApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.center.VerifiedNoticeBean;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;

import butterknife.OnClick;


/**
 * 作者：PangLei on 2019/5/13 0013 09:40
 * <p>
 * 邮箱：434604925@qq.com
 */
public class VerifiedFragment extends BaseTitleFragment {


    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_id_card)
    EditText mEtIdCard;


    public static VerifiedFragment newInstance() {

        Bundle args = new Bundle();

        VerifiedFragment fragment = new VerifiedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_verified;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("实名认证");
    }


    @OnClick({R.id.tv_description, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_description:
                AccountApi.getVerifiedNotice(new BaseCallback<BaseResponse<VerifiedNoticeBean>>() {
                    @Override
                    public void onSucc(BaseResponse<VerifiedNoticeBean> result) {
                        UIHelper.showVerifiedFile(getActivity(), "实名认证", result.data.getContent());
                    }
                }, this);

                break;
            case R.id.sb_confirm:
                setVerifiedName();
                break;
        }
    }

    /**
     * 实名认证
     */
    private void setVerifiedName() {
        String name = mEtName.getText().toString().trim();
        String id_card = mEtIdCard.getText().toString().trim();
        if (ObjectUtils.isEmpty(name)) {
            ToastUtil.showErrorToast("姓名不能为空");
            return;
        }
        if (ObjectUtils.isEmpty(id_card)) {
            ToastUtil.showErrorToast("身份证号码不能为空");
            return;
        }
        AccountApi.setVerified(name, id_card, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showSuccessToast("保存成功");
                removeFragment();
            }
        }, this);
    }
}
