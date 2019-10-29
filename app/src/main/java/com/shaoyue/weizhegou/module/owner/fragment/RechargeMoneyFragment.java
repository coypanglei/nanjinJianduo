package com.shaoyue.weizhegou.module.owner.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AccountApi;
import com.shaoyue.weizhegou.api.remote.OrderApi;
import com.shaoyue.weizhegou.api.remote.PayApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.order.TopUpofferBean;
import com.shaoyue.weizhegou.entity.user.PayTypeInfoBean;
import com.shaoyue.weizhegou.module.owner.adapter.RechargeMoneyAdapter;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.MaxHeightRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 作者：PangLei on 2019/5/29 0029 14:34
 * <p>
 * 邮箱：434604925@qq.com
 */
public class RechargeMoneyFragment extends BaseTitleFragment {

    @BindView(R.id.iv_alipay_check_mark)
    ImageView mIvAlipayCheckMark;
    @BindView(R.id.rl_alipay)
    RelativeLayout mRlAlipay;
    @BindView(R.id.iv_wechat_check_mark)
    ImageView mIvWechatCheckMark;
    @BindView(R.id.rl_wechat)
    RelativeLayout mRlWechat;
    @BindView(R.id.rl_max_rl)
    MaxHeightRecyclerView mRlMaxRl;
    @BindView(R.id.et_price)
    EditText mEtPrice;
    @BindView(R.id.tv_recharge_title)
    TextView mTvRechargeTitle;
    @BindView(R.id.sb_confirm)
    TextView mSbConfirm;
    @BindView(R.id.ll_recharge)
    LinearLayout mLlRecharge;
    Unbinder unbinder;


    private RechargeMoneyAdapter mRechargeMoney;

    private int bindWechat = 0;

    public static RechargeMoneyFragment newInstance() {

        Bundle args = new Bundle();
        RechargeMoneyFragment fragment = new RechargeMoneyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_recharge;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("账户充值").setRightBtn("充值明细", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(RechargeDetailsFragment.newInstance());
            }
        });
        mRechargeMoney = new RechargeMoneyAdapter();
        mRlMaxRl.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlMaxRl.setAdapter(mRechargeMoney);
        AccountApi.getPayTypeInfo(new BaseCallback<BaseResponse<PayTypeInfoBean>>() {
            @Override
            public void onSucc(BaseResponse<PayTypeInfoBean> result) {
                if (result.data.getAlipay().equals("0")) {
                    mRlAlipay.setVisibility(View.GONE);
                } else {
                    mRlAlipay.setVisibility(View.VISIBLE);
                }
                if (result.data.getWechat().equals("0")) {
                    mRlWechat.setVisibility(View.GONE);
                } else {
                    mRlWechat.setVisibility(View.VISIBLE);
                }
                if (ObjectUtils.isEmpty(result.data.getIs_default())) {

                    mLlRecharge.setVisibility(View.GONE);
                    mRlWechat.setVisibility(View.GONE);
                    mRlAlipay.setVisibility(View.GONE);
                    mSbConfirm.setBackgroundResource(R.drawable.icon_d4d4d4_20dp);
                    bindWechat = 0;
                } else {

                    mLlRecharge.setVisibility(View.VISIBLE);

                    mLlRecharge.setVisibility(View.VISIBLE);
                    mSbConfirm.setBackgroundResource(R.drawable.icon_blue_jbshape);
                    if (result.data.getIs_default().equals("wechat_pay")) {
                        bindWechat = 1;
                        initView();
                    } else {
                        bindWechat = 2;
                        initView();
                    }
                }
            }
        }, this);
        OrderApi.getRechargeExplain(new BaseCallback<BaseResponse<List<TopUpofferBean>>>() {
            @Override
            public void onSucc(BaseResponse<List<TopUpofferBean>> result) {

                mRechargeMoney.setNewData(result.data);
                if (result.data.size() > 0) {
                    mRlMaxRl.setVisibility(View.VISIBLE);
                    mTvRechargeTitle.setVisibility(View.VISIBLE);
                }
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
        } else if (bindWechat == 2) {
            mIvWechatCheckMark.setVisibility(View.INVISIBLE);
            mRlWechat.setBackgroundResource(R.drawable.icon_a4a4a4_frame);
            mIvAlipayCheckMark.setVisibility(View.VISIBLE);
            mRlAlipay.setBackgroundResource(R.drawable.icon_check_box_bg);
        }

    }


    @OnClick({R.id.rl_alipay, R.id.rl_wechat, R.id.sb_confirm})
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
            case R.id.sb_confirm:
                String mPrice = mEtPrice.getText().toString().trim();
                if (bindWechat == 0) {
                    ToastUtil.showSuccToast("暂时无法充值");
                    return;
                }
                PayApi.preRechargeOrder(mPrice, bindWechat + "", new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {

                    }
                }, this);
                break;
        }
    }


}
