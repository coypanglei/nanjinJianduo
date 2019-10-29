package com.shaoyue.weizhegou.module.pay.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.PayApi;
import com.shaoyue.weizhegou.event.PayPasswordEvent;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.util.XClick.SingleClick;
import com.shaoyue.weizhegou.widget.PayPasswordView;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NewPayPasswordFragment extends DialogFragment implements DialogInterface.OnKeyListener {
    private Unbinder unbinder;

    private String mbalance;

    public static NewPayPasswordFragment newInstance(String mbalance) {
        Bundle args = new Bundle();
        args.putString("balance", mbalance);
        NewPayPasswordFragment fragment = new NewPayPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ObjectUtils.isNotEmpty(getArguments())) {
            mbalance = getArguments().getString("balance");
        } else {
            dismiss();
        }


    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LogUtils.e(mbalance);
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
//      View view = inflater.inflate(R.layout.dialog_goods_specification_new, null);

        final PayPasswordView payPasswordView = new PayPasswordView(getActivity());
        payPasswordView.setPasswordFullListener(new PayPasswordView.PasswordFullListener() {
            @Override
            public void passwordFull(final String password) {
                PayApi.checkPayPassword(password, new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        dismiss();
                        ThreadUtil.runInUIThread(new Runnable() {
                            @Override
                            public void run() {
                                EventBus.getDefault().post(new PayPasswordEvent(password));
                            }
                        },1000);
                    }

                    @Override
                    public void onFail(ApiException apiError) {
                        ToastUtil.showBlackToastSucess(apiError.getMessage());
                        if (ObjectUtils.isNotEmpty(payPasswordView)) {
                            payPasswordView.setmPasswordEditText();
                        }
                    }
                }, this);
            }
        });
        //设置余额
        payPasswordView.setPriceText("￥  " + mbalance);
        unbinder = ButterKnife.bind(this, payPasswordView);
        initView(dialog, payPasswordView);

        return dialog;
    }


    /**
     * 初始化View
     *
     * @param dialog
     * @param view
     */
    private void initView(Dialog dialog, View view) {

        //动画
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimBottom;
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
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @SingleClick
    @OnClick({R.id.iv_close, R.id.view_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
            case R.id.view_top:
                dismiss();
                break;
        }
    }


}