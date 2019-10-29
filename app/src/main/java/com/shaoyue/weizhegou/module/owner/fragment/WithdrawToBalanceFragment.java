package com.shaoyue.weizhegou.module.owner.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.user.CommissionBalanceBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.util.XClick.SingleClick;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：PangLei on 2019/5/27 0027 15:12
 * <p>
 * 邮箱：434604925@qq.com
 */
public class WithdrawToBalanceFragment extends BaseTitleFragment {

    @BindView(R.id.et_withdrawal_amount)
    EditText mEtWithdrawalAmount;

    private boolean click = true;

    public static WithdrawToBalanceFragment newInstance() {

        Bundle args = new Bundle();

        WithdrawToBalanceFragment fragment = new WithdrawToBalanceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_withdrawal_to_the_balance;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("提现");
    }


    @OnClick(R.id.sb_confirm)
    public void onViewClicked() {
        String myMoney = mEtWithdrawalAmount.getText().toString().trim();
        if (click) {
            click = false;
            UserApi.withdrawalToTheBalance(myMoney, new BaseCallback<BaseResponse<CommissionBalanceBean>>() {
                @Override
                public void onSucc(BaseResponse<CommissionBalanceBean> result) {
                    click = true;
                    UserMgr.getInstance().setSP_COMMISSION_BAlANCE(result.data.getCommission_balance());
                    EventBus.getDefault().post(result.data);
                    ToastUtil.showSuccessToast("提现成功");
                    removeFragment();
                }

                @Override
                public void onFail(ApiException apiError) {
                    super.onFail(apiError);
                    click = true;
                }
            }, this);
        }
    }


}
