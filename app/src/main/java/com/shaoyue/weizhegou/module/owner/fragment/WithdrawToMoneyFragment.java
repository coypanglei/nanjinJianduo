package com.shaoyue.weizhegou.module.owner.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;

import butterknife.BindView;

import butterknife.OnClick;


/**
 * 作者：PangLei on 2019/5/29 0029 14:34
 * <p>
 * 邮箱：434604925@qq.com
 */
public class WithdrawToMoneyFragment extends BaseTitleFragment {

    @BindView(R.id.iv_alipay_check_mark)
    ImageView mIvAlipayCheckMark;
    @BindView(R.id.rl_alipay)
    RelativeLayout mRlAlipay;
    @BindView(R.id.iv_wechat_check_mark)
    ImageView mIvWechatCheckMark;
    @BindView(R.id.rl_wechat)
    RelativeLayout mRlWechat;
    @BindView(R.id.ll_account)
    LinearLayout mLlAccount;
    @BindView(R.id.ll_name)
    LinearLayout mLlName;
    @BindView(R.id.ll_withdraw)
    LinearLayout mLlWithdraw;

    private int bindWechat;

    public static WithdrawToMoneyFragment newInstance() {

        Bundle args = new Bundle();

        WithdrawToMoneyFragment fragment = new WithdrawToMoneyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_withdraw;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("提现");
        UserApi.isBindWechat(new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                bindWechat = 1;
                initView();
                mLlWithdraw.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFail(ApiException apiError) {
                bindWechat = 2;
                mRlWechat.setVisibility(View.INVISIBLE);
                initView();
                mLlWithdraw.setVisibility(View.VISIBLE);
            }
        }, this);
    }

    //初始化view
    private void initView() {
        if (bindWechat == 1) {
            mIvWechatCheckMark.setVisibility(View.VISIBLE);
            mRlWechat.setBackgroundResource(R.drawable.icon_check_box_bg);
            mIvAlipayCheckMark.setVisibility(View.INVISIBLE);
            mRlAlipay.setBackgroundResource(R.drawable.icon_a4a4a4_frame);
            mLlAccount.setVisibility(View.GONE);
            mLlName.setVisibility(View.GONE);
        } else if (bindWechat == 2) {
            mIvWechatCheckMark.setVisibility(View.INVISIBLE);
            mRlWechat.setBackgroundResource(R.drawable.icon_a4a4a4_frame);
            mIvAlipayCheckMark.setVisibility(View.VISIBLE);
            mRlAlipay.setBackgroundResource(R.drawable.icon_check_box_bg);
            mLlAccount.setVisibility(View.VISIBLE);
            mLlName.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.rl_alipay, R.id.rl_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_alipay:
                bindWechat = 2;
                initView();
                break;
            case R.id.rl_wechat:
                bindWechat = 1;
                initView();
                break;
        }
    }
}
