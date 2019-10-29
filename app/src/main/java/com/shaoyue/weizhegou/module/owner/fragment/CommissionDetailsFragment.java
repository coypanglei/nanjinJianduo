package com.shaoyue.weizhegou.module.owner.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AccountApi;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseNoStatusFragment;
import com.shaoyue.weizhegou.entity.user.CommissionBalanceBean;
import com.shaoyue.weizhegou.entity.user.CommissionDetailBean;
import com.shaoyue.weizhegou.entity.user.CommissionListBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.owner.adapter.CommissionDetailAdapter;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * 作者：PangLei on 2019/5/22 0022 10:22
 * <p>
 * 邮箱：434604925@qq.com
 */
public class CommissionDetailsFragment extends BaseNoStatusFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.ll_visibility)
    LinearLayout mLlVisibility;
    @BindView(R.id.iv_visable)
    ImageView mIvVisable;
    @BindView(R.id.tv_commission_income)
    TextView mTvCommissionIncome;

    @BindView(R.id.tv_to_sum_amount)
    TextView mTvToSumAmount;


    @BindView(R.id.tv_buy_amount)
    TextView mTvBuyAmount;
    @BindView(R.id.tv_to_buy_amount)
    TextView mTvToBuyAmount;
    @BindView(R.id.tv_finish_buy_order)
    TextView mTvFinishBuyOrder;
    @BindView(R.id.tv_trading_buy_order)
    TextView mTvTradingBuyOrder;
    @BindView(R.id.tv_sale_amount)
    TextView mTvSaleAmount;
    @BindView(R.id.tv_to_sale_amount)
    TextView mTvToSaleAmount;
    @BindView(R.id.tv_finish_sale_order)
    TextView mTvFinishSaleOrder;
    @BindView(R.id.tv_trading_sale_order)
    TextView mTvTradingSaleOrder;
    @BindView(R.id.tv_withdrawal_to_the_balance)
    TextView mTvWithdrawalToTheBalance;
    @BindView(R.id.tv_go_withdraw)
    TextView mTvGoWithdraw;
    @BindView(R.id.tv_toast)
    TextView mTvToast;

    private int mllVisibilityHeight;
    private boolean isFold = false;//是否是收起状态
    boolean isAnimating = false;//是否正在执行动画
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;
    CommissionDetailAdapter mCouponAdapter;
    @BindView(R.id.empty_img)
    ImageView mEmptyImg;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    @BindView(R.id.tv_commission)
    TextView mCommissionText;
    private int per_page = 20;
    private String state = "2";
    private int page = 1;
    private int total = 0;

    public static CommissionDetailsFragment newInstance() {
        Bundle args = new Bundle();
        CommissionDetailsFragment fragment = new CommissionDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_commission_details;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mLlVisibility.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mllVisibilityHeight = mLlVisibility.getHeight();
                        mLlVisibility.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        mLlVisibility.setVisibility(View.GONE);
                    }
                });
        initView();
        mEmptyRelative.setBackgroundResource(R.color.white);
    }

    /**
     * 初始化View
     */
    private void initView() {
        mCouponAdapter = new CommissionDetailAdapter();
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContent.setAdapter(mCouponAdapter);
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        mRefreshLayout.setIsShowLoadingMoreView(true);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 清除历史记录
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CommissionBalanceBean event) {
        initCouponList();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (UserMgr.getInstance().getSpCanWithdraw().equals("0")) {
            mTvWithdrawalToTheBalance.setVisibility(View.GONE);
            mTvGoWithdraw.setVisibility(View.GONE);
            mTvToast.setText("联系客服可申请开通提现资格！");
        } else {
            mTvWithdrawalToTheBalance.setVisibility(View.VISIBLE);
            mTvGoWithdraw.setVisibility(View.VISIBLE);
        }
        getCommissionDetails();
    }


    @Override
    protected void loadData() {
        super.loadData();
        initCouponList();
    }

    @OnClick({R.id.iv_back, R.id.iv_visable, R.id.tv_withdrawal_to_the_balance, R.id.tv_go_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.iv_visable:
                if (isAnimating) return;
                //如果动画没在执行,走到这一步就将isAnimating制为true , 防止这次动画还没有执行完毕的
                //情况下,又要执行一次动画,当动画执行完毕后会将isAnimating制为false,这样下次动画又能执行
                isAnimating = true;

                if (mLlVisibility.getVisibility() == View.GONE) {

                    //打开动画
                    animateOpen(mLlVisibility);
                } else {
                    //关闭动画
                    animateClose(mLlVisibility);
                }
                break;
            //提现到余额
            case R.id.tv_withdrawal_to_the_balance:
                addFragment(WithdrawToBalanceFragment.newInstance());
                break;
            case R.id.tv_go_withdraw:
                //提现金额不能少于50元！
                try {
                    double mCommission = Double.valueOf(mCommissionText.getText().toString().trim());
                    if (mCommission >= 50) {
                        addFragment(WithdrawToMoneyFragment.newInstance());
                    } else {
                        ToastUtil.showBlackToastSucess("提现金额不能少于50元！");
                    }
                } catch (NumberFormatException exception) {

                }

                break;
        }
    }


    private void animateOpen(LinearLayout view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0, mllVisibilityHeight);
        ValueAnimator animator1 = createScaleAnimator(1f, 0.7f);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
            }
        });
        animator.setDuration(300);
        animator1.setDuration(300);
        animator.start();
        animator1.start();
    }

    private void animateClose(final LinearLayout view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        ValueAnimator animator1 = createScaleAnimator(0.7f, 1f);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                isAnimating = false;
            }
        });
        animator.setDuration(300);
        animator1.setDuration(300);
        animator.start();
        animator1.start();
    }


    /**
     * 展示ImageButton图标
     */
    private void showIbtn() {
        if (isFold) {
            mIvVisable.setImageResource(R.drawable.icon_drop_down_button);
        } else {
            mIvVisable.setImageResource(R.drawable.icon_push_up_button);
        }
        isFold = !isFold;
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        showIbtn();
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    private ValueAnimator createScaleAnimator(float start, float end) {
        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();

                if (value != 0) {
                    mEmptyRelative.setScaleX(value);
                    mEmptyRelative.setScaleY(value);
                }
            }
        });
        return animator;
    }

    /**
     * 佣金详情参数
     */
    private void getCommissionDetails() {
        AccountApi.getCommissionDetail(new BaseCallback<BaseResponse<CommissionDetailBean>>() {
            @Override
            public void onSucc(BaseResponse<CommissionDetailBean> result) {
                mTvCommissionIncome.setText(result.data.getCommission_income());
                mCommissionText.setText(result.data.getCommission_balance());
                mTvBuyAmount.setText(result.data.getBuy_amount() + "元");
                mTvToSumAmount.setText(result.data.getTo_sum_amount());
                mTvFinishBuyOrder.setText(result.data.getFinish_buy_order() + "笔");
                mTvFinishSaleOrder.setText(result.data.getFinish_sale_order() + "笔");
                mTvToSaleAmount.setText(result.data.getTo_sale_amount() + "元");
                mTvTradingBuyOrder.setText(result.data.getTrading_buy_order() + "笔");
                mTvTradingSaleOrder.setText(result.data.getSale_amount() + "笔");
                mTvSaleAmount.setText(result.data.getSale_amount() + "元");
                mTvToBuyAmount.setText(result.data.getTo_buy_amount() + "元");
            }
        }, this);
    }

    //佣金明细清单
    private void initCouponList() {
        page = 1;

        UserApi.getAllDetail(state, page, new BaseCallback<BaseResponse<CommissionListBean>>() {
            @Override
            public void onSucc(BaseResponse<CommissionListBean> result) {
                total = result.data.getTotal();
                per_page = result.data.getPer_page();
                mCouponAdapter.setNewData(result.data.getData());
                if (result.data.getData().size() > 0) {
                    mEmptyRelative.setVisibility(View.GONE);
                } else {
                    mEmptyRelative.setVisibility(View.VISIBLE);
                    mEmptyText.setText("还没有佣金明细" + ToastUtil.getToastMsg());
                    mEmptyImg.setImageResource(R.drawable.icon_empty_commission);
                }
            }
        }, this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
                initCouponList();
            }
        }, 1000);
    }


    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        int size = (total / per_page) + 1;

        if (page == size) {
            mRefreshLayout.endLoadingMore();
            ToastUtil.showErrorToast("没有更多的数据了");
            return false;
        }

        UserApi.getAllDetail(state, page + 1, new BaseCallback<BaseResponse<CommissionListBean>>() {
            @Override
            public void onSucc(final BaseResponse<CommissionListBean> result) {
                total = result.data.getTotal();
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mRefreshLayout == null) {
                            return;
                        }
                        mRefreshLayout.endLoadingMore();
                        mCouponAdapter.addData(result.data.getData());
                        page++;
                    }
                }, 1000);
            }
        }, this);

        return true;
    }


}
