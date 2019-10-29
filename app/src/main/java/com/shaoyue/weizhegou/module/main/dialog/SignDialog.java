package com.shaoyue.weizhegou.module.main.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;

import butterknife.ButterKnife;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/30 20:26
 */
public class SignDialog extends DialogFragment implements DialogInterface.OnKeyListener {

    private static final String EXTRA_SIGN_OK = "extra_sign_ok";
    private static final String EXTRA_SIGN_TIME = "extra_sign_time";


    private boolean mSignOk;
    private String mSignTime;

    public static SignDialog newInstance(boolean signOk, String signTime) {
        SignDialog dialog = new SignDialog();
        Bundle args = new Bundle();
        args.putBoolean(EXTRA_SIGN_OK, signOk);
        if (signOk && !TextUtils.isEmpty(signTime)) {
            args.putString(EXTRA_SIGN_TIME, signTime);
        }
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mSignOk = bundle.getBoolean(EXTRA_SIGN_OK);
            mSignTime = bundle.getString(EXTRA_SIGN_TIME);
        } else {
            dismiss();
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstance) {
        Dialog dialog = new Dialog(getActivity(), com.shaoyue.weizhegou.R.style.Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(com.shaoyue.weizhegou.R.layout.dialog_sign, null);


        initView(view);


        dialog.getWindow().getAttributes().windowAnimations = com.shaoyue.weizhegou.R.style.dialogAnim;
        dialog.setContentView(view);
        dialog.setOnKeyListener(this);
        dialog.setCanceledOnTouchOutside(false);
        ButterKnife.bind(this, view);
//        setInfo();
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.gravity = Gravity.TOP;//对齐方式
            attributes.y = (int) ConvertUtils.dp2px(75);//具体头部距离
            dialog.getWindow().setAttributes(attributes);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }


    private void initView(View view) {
        LinearLayout signOkLayout = (LinearLayout) view.findViewById(com.shaoyue.weizhegou.R.id.ll_sign_ok);
        LinearLayout signFailLayout = (LinearLayout) view.findViewById(com.shaoyue.weizhegou.R.id.ll_sign_fail);
        view.findViewById(com.shaoyue.weizhegou.R.id.iv_dialog_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        if (mSignOk) {
            signOkLayout.setVisibility(View.VISIBLE);
            signFailLayout.setVisibility(View.GONE);
            TextView signTimeText = (TextView) view.findViewById(com.shaoyue.weizhegou.R.id.tv_sign_get_time);
            if (!TextUtils.isEmpty(mSignTime)) {
                signTimeText.setText(mSignTime);
            }
            view.findViewById(com.shaoyue.weizhegou.R.id.sb_sign_fetch).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });

        } else {
            signOkLayout.setVisibility(View.GONE);
            signFailLayout.setVisibility(View.VISIBLE);
            view.findViewById(com.shaoyue.weizhegou.R.id.sb_sign_tomorrow).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }


    }


}
