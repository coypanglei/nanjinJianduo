package com.shaoyue.weizhegou.module.main.fragment;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;


import com.shaoyue.weizhegou.AppContext;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AccountApi;
import com.shaoyue.weizhegou.api.remote.SpeedApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.common.CommConfig;
import com.shaoyue.weizhegou.entity.ActivityBean;
import com.shaoyue.weizhegou.entity.home.HomeInitBean;
import com.shaoyue.weizhegou.entity.LineBean;
import com.shaoyue.weizhegou.entity.MemberShipBean;
import com.shaoyue.weizhegou.interfac.CommCallBack;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.manager.SpeedMgr;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.account.dialog.MembershipExpiresFragment;
import com.shaoyue.weizhegou.module.main.dialog.SignDialog;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.TestUtils;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.ObservableScrollView;

import butterknife.BindView;


public class SpeedFragment extends BaseAppFragment {

    private static final String TAG = SpeedFragment.class.getSimpleName();


    @BindView(R.id.tv_speed_seletc_line)
    TextView mSelectLineText;

    @BindView(R.id.tv_speed_line_name)
    TextView mLineNameText;

    @BindView(R.id.tv_speed_type)
    TextView mTypeText;

    @BindView(R.id.lottie_bottom)
    LottieAnimationView mLottieBottom;
    @BindView(R.id.sc_main_speed)
    ObservableScrollView mScMainSpeed;



    TextView mTvLoginTime;
    @BindView(R.id.iv_go_share)
    ImageView mShareImageView;



    private int scMargin = 0;

    private String HOME_INIT_BEAN = "homeInitBean";

    private HomeInitBean mHomeInitBean;

    private ValueAnimator animator;

    public static SpeedFragment newInstance(HomeInitBean homeInitBean) {
        SpeedFragment fragment = new SpeedFragment();
        if (ObjectUtils.isNotEmpty(homeInitBean)) {
            Bundle args = new Bundle();
            args.putSerializable(UIHelper.HOME_DATA, homeInitBean);
            fragment.setArguments(args);
        }
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


        SpeedMgr.getInstance().setLineInfo(null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_speed;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        AppMgr.getInstance().doFetchCService(null);


        mScMainSpeed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mScMainSpeed.getChildAt(0).getHeight() - mScMainSpeed.getHeight()
                        == mScMainSpeed.getScrollY()) {
                    setMargins(mLottieBottom, 0, 0, 0, scMargin);
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:

                        if (ObjectUtils.isNotEmpty(animator)) {
                            if (animator.isRunning()) {
                                animator.cancel();
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:

                        animator = ValueAnimator.ofFloat(0f, 1f)
                                .setDuration(300);//1s
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                                       @Override
                                                       public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                                           setMargins(mLottieBottom, 0, 0, 0, (int) (-300 * (Float) valueAnimator.getAnimatedValue()));
                                                       }
                                                   }
                        );
                        animator.start();


                        break;
                    default:
                        if (ObjectUtils.isNotEmpty(animator)) {
                            if (animator.isRunning()) {
                                animator.cancel();
                            }
                        }
                }
                return false;
            }
        });




        checkActivity();
        checkMember();
        fetchSpeedLineName();

    }


    @Override
    public void onResume() {
        super.onResume();

        LineBean lineBean = SpeedMgr.getInstance().getLineInfo();
        if (lineBean != null) {
            mLineNameText.setText(lineBean.getName());
        }
        int speedType = SpeedMgr.getInstance().getSpeedType();
        if (speedType == CommConfig.SPEED_TYPE_SMART) {
            mTypeText.setText(getResources().getString(R.string.content_intelligent_mode));
        } else {
            mTypeText.setText(getResources().getString(R.string.content_global_pattern));
        }

    }

    /**
     * 检查会员是否到期
     */
    private void checkMember() {
        AccountApi.isPopup(new BaseCallback<BaseResponse<MemberShipBean>>() {
            @Override
            public void onSucc(BaseResponse<MemberShipBean> result) {
                showMembersExpiresDialog(getString(R.string.t_your_membership_will_be) + result.data.getTitle() + getString(R.string.t_expire));
            }

            @Override
            public void onFail(ApiException apiError) {

            }
        }, this);

    }


    /**
     * 检查会员是否到期
     */
    private void checkActivity() {
        AccountApi.activityPopup(new BaseCallback<BaseResponse<ActivityBean>>() {
            @Override
            public void onSucc(BaseResponse<ActivityBean> result) {
                UIHelper.showActivityDialog(getActivity(), result.data);
            }

            @Override
            public void onFail(ApiException apiError) {

            }
        }, this);

    }


    /**
     * 第一步检测账户是否有效
     */
    private void checkSpeedEnable() {
        AccountApi.checkSpeedEnable(new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {

                fetchSpeedLine();
            }

            @Override
            public void onFail(ApiException apiError) {

                if (apiError.getErrCode() == 1) {
                    showBuyDialog();
                } else {
                    ToastUtil.showErrorToast(getString(R.string.t_your_account_has_expired));
                    doLogout();
                }

            }
        }, this);
    }


    /**
     * 第二步获取线路
     */
    private void fetchSpeedLine() {
        if (SpeedMgr.getInstance().getLineInfo() != null) {
            startProxy();
            return;
        }

        SpeedApi.fetchRandLine(new BaseCallback<BaseResponse<LineBean>>() {
            @Override
            public void onSucc(BaseResponse<LineBean> result) {
                LineBean lineBean = result.data;
                if (lineBean == null) {
                    return;
                }

                if (SpeedMgr.getInstance().setLineInfo(lineBean)) {
                    mLineNameText.setText(lineBean.getName());
                    startProxy();
                } else {
                    ToastUtil.showSuccToast(getString(R.string.t_random_line_acquisition_failure));
                }

            }

            @Override
            public void onFail(ApiException apiError) {
                ToastUtil.showErrorToast(apiError.getErrMsg());

            }
        }, this);

    }

    /**
     * 获取随机线路显示
     */
    private void fetchSpeedLineName() {
        SpeedApi.fetchRandLine(new BaseCallback<BaseResponse<LineBean>>() {
            @Override
            public void onSucc(BaseResponse<LineBean> result) {
                LineBean lineBean = result.data;
                if (lineBean == null) {
                    return;
                }

                if (SpeedMgr.getInstance().setLineInfo(lineBean)) {
                    mLineNameText.setText(lineBean.getName());
                } else {
                    ToastUtil.showSuccToast(getString(R.string.t_random_line_acquisition_failure));
                }

            }

            @Override
            public void onFail(ApiException apiError) {
                ToastUtil.showErrorToast(apiError.getErrMsg());

            }
        }, this);
    }


    /**
     * 第三步，启动代理
     */
    private void startProxy() {
        LineBean lineInfo = SpeedMgr.getInstance().getLineInfo();
        if (lineInfo == null) {
            ToastUtil.showSuccToast(getString(R.string.t_please_select_the_line_first));
            return;
        }
        int speedType = SpeedMgr.getInstance().getSpeedType();
        String mAllowedPackages = SpeedMgr.getInstance().getmAllowedPackages();
        //是否是智能模式使用包名
        if (speedType != CommConfig.SPEED_TYPE_SMART) {
            LogUtils.e(mAllowedPackages);
            mAllowedPackages = "";
        }


    }






    private void fixSimulatorBug() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TestUtils.fixIp();
            }
        }, 1000);
    }




    /**
     * 显示签到对话框
     */
    private void showSignDialog(boolean signOk, String signTime) {
        String tag = "DIALOG_SIGN";
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment == null) {
            DialogFragment dialog = SignDialog.newInstance(signOk, signTime);
            dialog.show(fm, tag);
        }
    }


    /**
     * 会员到期对话框
     *
     * @param content
     */
    private void showMembersExpiresDialog(String content) {
        String tag = "DIALOG_SHIP";
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment == null) {
            DialogFragment dialog = MembershipExpiresFragment.newInstance(content);
            dialog.show(fm, tag);
        }
    }


    private void showDisconnectDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AppContext.getTopActivity());
        builder.setCancelable(false); // 点击背景窗口不能自动隐藏
        builder.setTitle("友情提示").
                setMessage(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        builder.create().show();
    }


    private AlertDialog.Builder builder = null;

    /**
     * 去购买弹窗
     */
    private void showBuyDialog() {
        if (ObjectUtils.isEmpty(builder)) {
            builder = new AlertDialog.Builder(AppContext.getTopActivity());
            builder.setCancelable(false); // 点击背景窗口不能自动隐藏
            builder.setTitle("套餐升级").
                    setMessage("升级套餐，获取更佳用户体验")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            UIHelper.showProfileCommonActivity(getActivity(), ContentType.PAY_PACKAGE);

                        }
                    });
        }
        builder.show();


    }


    /**
     * 设置Margin
     *
     * @param v view
     * @param l 左
     * @param t 上
     * @param r 右
     * @param b 下
     * @Author: PangLei
     * @Date: 2018/9/12 14:boot_page_num_three
     * @Description:工具
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }

    }




    private void doLogout() {
        startProgressDialog(false);
        UserMgr.getInstance().doLogout(new CommCallBack() {
            @Override
            public void complete(int code, String msg) {
                stopProgressDialog();
                UIHelper.goLoginPage(getActivity());
            }
        });

    }

}