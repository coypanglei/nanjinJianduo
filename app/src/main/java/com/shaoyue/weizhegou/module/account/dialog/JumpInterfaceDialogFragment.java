package com.shaoyue.weizhegou.module.account.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：PangLei on 2019/5/15 0015 10:25
 * <p>
 * 邮箱：434604925@qq.com
 */
public class JumpInterfaceDialogFragment extends DialogFragment implements DialogInterface.OnKeyListener {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_ok)
    TextView mTvOk;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    Unbinder unbinder;
    private CountDownTimer mCountdownTimer;
    private int jumpTime = 4000;

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return false;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_jump_interface, null);

        unbinder = ButterKnife.bind(this, view);
        initView(dialog, view);

        mCountdownTimer = new CountDownTimer(jumpTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String content = "<font color=\"#333333\">请先绑定手机号</font><font color=\"#cd094d\">"
                        + millisUntilFinished / 1000 + "s</font><font color=\"#333333\">后页面自动跳转绑定手机界面...</font>";
                if (ObjectUtils.isNotEmpty(mTvTitle)) {
                    mTvTitle.setText(Html.fromHtml(content));
                }
            }

            @Override
            public void onFinish() {
                EventBus.getDefault().post(new OkOrCancelEvent(2));
                mCountdownTimer = null;
                dismiss();
            }
        };
        mCountdownTimer.start();
        return dialog;
    }


    public static JumpInterfaceDialogFragment newInstance() {

        Bundle args = new Bundle();

        JumpInterfaceDialogFragment fragment = new JumpInterfaceDialogFragment();
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
// 把 DecorView 的默认 padding 取消，同时 DecorView 的默认大小也会取消
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
// 设置宽度
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
// 给 DecorView 设置背景颜色，很重要，不然导致 Dialog 内容显示不全，有一部分内容会充当 padding，上面例子有举出
        dialog.setContentView(view, layoutParams);
        dialog.setCanceledOnTouchOutside(true);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (ObjectUtils.isNotEmpty(mCountdownTimer)) {
            mCountdownTimer.cancel();
            mCountdownTimer = null;
        }
    }


    @OnClick({R.id.tv_ok, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ok:
                EventBus.getDefault().post(new OkOrCancelEvent(2));
                mCountdownTimer.cancel();
                mCountdownTimer = null;
                dismiss();
                break;
            case R.id.tv_cancel:
                mCountdownTimer.cancel();
                mCountdownTimer = null;
                dismiss();
                break;
        }
    }
}
