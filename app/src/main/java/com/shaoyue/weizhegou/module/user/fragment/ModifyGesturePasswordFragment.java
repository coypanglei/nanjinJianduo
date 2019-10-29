package com.shaoyue.weizhegou.module.user.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.util.ToolUtils;
import com.shaoyue.weizhegou.widget.gesture.GestureLockDisplayView;
import com.shaoyue.weizhegou.widget.gesture.GestureLockLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ModifyGesturePasswordFragment extends BaseTitleFragment {

    @BindView(R.id.tv_account)
    TextView mTvAccount;
    @BindView(R.id.tv_error_msg)
    TextView mTvErrorMsg;
    @BindView(R.id.et_ok_pass)
    EditText mEtOkPass;
    @BindView(R.id.ll_password)
    LinearLayout mLlPassword;
    @BindView(R.id.cl_gesture)
    ConstraintLayout mClGesture;
    @BindView(R.id.display_view)
    GestureLockDisplayView mDisplayView;
    @BindView(R.id.setting_hint)
    TextView mSettingHint;
    @BindView(R.id.hintTV)
    TextView mHintTV;
    @BindView(R.id.gesture_view)
    GestureLockLayout mGestureView;
    @BindView(R.id.reSet)
    TextView mReSet;
    private Animation animation;

    private Context mContext;
    //type为1 第一次设置密码
    private String type;

    public static ModifyGesturePasswordFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        ModifyGesturePasswordFragment fragment = new ModifyGesturePasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_modify_gesture_password;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            type = getArguments().getString("type");
        }
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("手势登录设置").setRightBtn("退出系统", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).setDrawableLeft(R.drawable.icon_logout);
        mTvAccount.setText(SPUtils.getInstance().getString(UserMgr.SP_LOGIN_NAME));
        initView();
    }

    private void initView() {
        mContext = getActivity();
        //设置提示view 每行每列点的个数
        mDisplayView.setDotCount(3);
//        //设置提示view 选中状态的颜色
//        mDisplayView.setDotSelectedColor(Color.parseColor("#01367A"));
//        //设置提示view 非选中状态的颜色
//        mLockDisplayView.setDotUnSelectedColor(Color.parseColor("#999999"));
        //设置手势解锁view 每行每列点的个数
        mGestureView.setDotCount(3);
        //设置手势解锁view 最少连接数
        mGestureView.setMinCount(4);
        //设置手势解锁view 模式为重置密码模式
        mGestureView.setMode(GestureLockLayout.RESET_MODE);

        //初始化动画
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        initEvents();
    }

    @OnClick({R.id.tv_ok, R.id.reSet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ok:
                final String okPassword = mEtOkPass.getText().toString().trim();
                if (ObjectUtils.isEmpty(okPassword)) {
                    mTvErrorMsg.setText("密码不能为空");
                    return;
                }
                if (!ObjectUtils.equals(okPassword, SPUtils.getInstance().getString(UserMgr.SP_LOGIN_PASSWORD))) {
                    mTvErrorMsg.setText("原密码不正确");
                    return;
                }
                KeyboardUtils.hideSoftInput(getActivity());
                mLlPassword.setVisibility(View.GONE);
                mClGesture.setVisibility(View.VISIBLE);

                break;
            // 重置手势布局（布局加逻辑）
            case R.id.reSet:
                mGestureView.setOnLockResetListener(null);
                mSettingHint.setText("绘制解锁图案");
                mDisplayView.setAnswer(new ArrayList<Integer>());
                mGestureView.resetGesture();
                mGestureView.setMode(GestureLockLayout.RESET_MODE);
                mHintTV.setVisibility(View.INVISIBLE);
                initEvents();
                break;
        }
    }


    /**
     * 重置手势布局（只是布局）
     */
    private void resetGesture() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mGestureView.resetGesture();
            }
        }, 300);
    }


    private void initEvents() {

        mGestureView.setOnLockResetListener(new GestureLockLayout.OnLockResetListener() {
            @Override
            public void onConnectCountUnmatched(int connectCount, int minCount) {
                //连接数小于最小连接数时调用
                mSettingHint.setText("最少连接" + minCount + "个点");
                resetGesture();
            }

            @Override
            public void onFirstPasswordFinished(List<Integer> answerList) {
                //第一次绘制手势成功时调用
//                Log.e("TAG", "第一次密码=" + answerList);
                mSettingHint.setText("确认解锁图案");
                //将答案设置给提示view
                mDisplayView.setAnswer(answerList);
                //重置
                resetGesture();
            }

            @Override
            public void onSetPasswordFinished(boolean isMatched, List<Integer> answerList) {
                //第二次密码绘制成功时调用
                Log.e("TAG", "第二次密码=" + answerList.toString());
                if (isMatched) {
                    // 两次答案一致，保存
                    SPUtils.getInstance().put(UserMgr.GESTURELOCK_KEY, answerList.toString());
                    if (type.equals("first")) {
                        ToastUtil.showBlackToastSucess("设置手势密码成功");
                        EventBus.getDefault().post(new OkOrCancelEvent("1006"));
                    } else {
                        ToastUtil.showBlackToastSucess("修改手势密码成功");
                    }
                    removeFragment();
                } else {
                    mHintTV.setVisibility(View.VISIBLE);
                    ToolUtils.setVibrate(mContext);
                    mHintTV.startAnimation(animation);
                    mGestureView.startAnimation(animation);
                    resetGesture();
                }
            }
        });
    }
}
