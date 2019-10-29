package com.shaoyue.weizhegou.module.user.dialog;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.gcssloop.widget.RCRelativeLayout;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.util.ToolUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：PangLei on 2019/5/15 0015 10:25
 * <p>
 * 邮箱：434604925@qq.com
 */
public class AccountPasswordFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.tv_my_info)
    TextView mTvMyInfo;
    @BindView(R.id.iv_close)
    ImageView mIvClose;
    @BindView(R.id.change_login_password)
    AppCompatTextView mChangeLoginPassword;
    @BindView(R.id.fingerprintImg)
    ImageView mFingerprintImg;
    @BindView(R.id.fingerprintCL)
    ConstraintLayout mFingerprintCL;
    @BindView(R.id.fingerprintTV)
    AppCompatTextView mFingerprintTV;
    @BindView(R.id.gestureImg)
    ImageView mGestureImg;
    @BindView(R.id.gestureLockCL)
    ConstraintLayout mGestureLockCL;
    @BindView(R.id.changeGesture)
    AppCompatTextView mChangeGesture;
    @BindView(R.id.rcrel_iv)
    RCRelativeLayout mRcrelIv;
    private Boolean isFingerprint, isGesture;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_account_password, null);
        isFingerprint = SPUtils.getInstance().getBoolean(UserMgr.ISFINGERPRINT_KEY, false);
        isGesture = SPUtils.getInstance().getBoolean(UserMgr.ISGESTURELOCK_KEY, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initView(dialog, view);
        if (ToolUtils.supportFingerprint(getActivity())) {


            if (isFingerprint) {
                mFingerprintImg.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_btn_open));
                mFingerprintTV.setVisibility(View.VISIBLE);
            }


        }

        if (isGesture) {
            mGestureImg.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_btn_open));
            mChangeGesture.setVisibility(View.VISIBLE);
        }
        return dialog;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static AccountPasswordFragment newInstance() {
        Bundle args = new Bundle();
        AccountPasswordFragment fragment = new AccountPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 初始化View
     *
     * @param dialog
     * @param view
     */
    private void initView(Dialog dialog, View view) {

        //动画
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;

        Window window = dialog.getWindow();
//// 把 DecorView 的默认 padding 取消，同时 DecorView 的默认大小也会取消
        WindowManager.LayoutParams layoutParams = window.getAttributes();
// 设置宽度
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
// 给 DecorView 设置背景颜色，很重要，不然导致 Dialog 内容显示不全，有一部分内容会充当 padding，上面例子有举出
        dialog.setContentView(view, layoutParams);
        dialog.setCanceledOnTouchOutside(true);
    }


    /**
     * 清除历史记录
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("1006")) {
            mChangeGesture.setVisibility(View.VISIBLE);
            SPUtils.getInstance().put(UserMgr.ISGESTURELOCK_KEY, true);
            mGestureImg.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_btn_open));
        }
    }


//
//    private void showDeleteDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("南网商旅");
//        builder.setMessage("是否关闭指纹登录？");
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                mFingerprintImg.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_btn_close));
//                mFingerprintTV.setVisibility(View.GONE);
//                mChangeGesture.setVisibility(View.GONE);
//                isFingerprint = false;
//                isFingerprint = SPUtils.getInstance().getBoolean(UserMgr.ISFINGERPRINT_KEY, false);
//                dialog.dismiss();
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
//        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
//    }

    @OnClick({R.id.iv_close, R.id.fingerprintImg, R.id.gestureImg, R.id.change_login_password, R.id.changeGesture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_login_password:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.MODIFY_PASSWORD);
                break;
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.fingerprintImg:
                isFingerprint = SPUtils.getInstance().getBoolean(UserMgr.ISFINGERPRINT_KEY, false);

                if (isFingerprint) {
                    mFingerprintTV.setVisibility(View.GONE);
                    SPUtils.getInstance().put(UserMgr.ISFINGERPRINT_KEY, false);
                    mFingerprintImg.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_btn_close));
                } else {
                    if (ToolUtils.haveoneFingerprint(getActivity())) {
                        mFingerprintTV.setVisibility(View.VISIBLE);
                        SPUtils.getInstance().put(UserMgr.ISFINGERPRINT_KEY, true);
                        ToastUtil.showBlackToastSucess("指纹解锁设置成功！");
                        mFingerprintImg.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_btn_open));
//
                    } else {
                        //跳转系统设置
                        try {
                            String pcgName = "com.android.settings";
                            String clsName = "com.android.settings.fingerprint.FingerprintSettingsActivity";
                            Intent intent = new Intent();
                            ComponentName componentName = new ComponentName(pcgName, clsName);
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setComponent(componentName);
                            startActivity(intent);
                        } catch (Exception e) {
                            LogUtils.e(e);
                        }

                    }

                }
                break;
            case R.id.gestureImg:
                isGesture = SPUtils.getInstance().getBoolean(UserMgr.ISGESTURELOCK_KEY, false);
                if (isGesture) {
                    mChangeGesture.setVisibility(View.GONE);
                    SPUtils.getInstance().put(UserMgr.ISGESTURELOCK_KEY, false);
                    mGestureImg.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_btn_close));
                } else {
                    if (ObjectUtils.isNotEmpty(SPUtils.getInstance().getString(UserMgr.GESTURELOCK_KEY))) {
                        mChangeGesture.setVisibility(View.VISIBLE);
                        SPUtils.getInstance().put(UserMgr.ISGESTURELOCK_KEY, true);
                        ToastUtil.showBlackToastSucess("手势设置成功！");
                        mGestureImg.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_btn_open));
                    } else {
                        //跳转手势界面 验证密码 设置手势开启
                        UIHelper.showProfileCommonActivity(getActivity(), ContentType.MODIFY_GESTURE_PASSWORD, new profileBean("first"));

                    }
                }
                break;
            //修改手势密码
            case R.id.changeGesture:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.MODIFY_GESTURE_PASSWORD, new profileBean("two"));
                break;
        }
    }
}
