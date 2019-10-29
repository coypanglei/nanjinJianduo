package com.shaoyue.weizhegou.module.pay.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.PayBean;
import com.shaoyue.weizhegou.module.pay.util.PayHelper;
import com.shaoyue.weizhegou.module.pay.util.PayView;

import butterknife.ButterKnife;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/31 11:18
 */
public class PayDialog extends DialogFragment implements DialogInterface.OnKeyListener, PayView {
    private static final String EXTRA_PAYBEAN = "extra_paybean";

    private PayBean payBean;

    private String type = "1";

    private PayHelper mPayHelper;

    public static PayDialog newInstance(PayBean payBean) {
        PayDialog dialog = new PayDialog();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_PAYBEAN, payBean);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            payBean = (PayBean) bundle.getSerializable(EXTRA_PAYBEAN);

        } else {
            dismiss();
        }
        mPayHelper = new PayHelper(getActivity(), this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstance) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_pay, null);


        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        TextView mPrice = view.findViewById(R.id.tv_pay_price);
        mPrice.setText("￥ " + payBean.getPayPrice());


        TextView mJianPrice = view.findViewById(R.id.jian_price);
        if (!"".equals(payBean.getReducePrice())) {
            mJianPrice.setVisibility(View.VISIBLE);
            mJianPrice.setText(payBean.getReducePrice());
        }
        final ImageView mAlipay = view.findViewById(R.id.iv_alipay_click);
        final ImageView mWechat = view.findViewById(R.id.iv_wechat_click);type = "1";
        mAlipay.setImageResource(R.drawable.ic_speed_type_unselect_bg);
        mWechat.setImageResource(R.drawable.icon_pay_select);

        view.findViewById(R.id.tv_go_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPayHelper.setPayTime(payBean ,type);
            }
        });
        view.findViewById(R.id.rl_alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "2";
                mAlipay.setImageResource(R.drawable.icon_pay_select);
                mWechat.setImageResource(R.drawable.ic_speed_type_unselect_bg);
            }
        });
        view.findViewById(R.id.rl_weichat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "1";
                mAlipay.setImageResource(R.drawable.ic_speed_type_unselect_bg);
                mWechat.setImageResource(R.drawable.icon_pay_select);
            }
        });

        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;
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
            attributes.gravity = Gravity.BOTTOM;//对齐方式
            dialog.getWindow().setAttributes(attributes);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }


    @Override
    public void paySucc() {
        dismiss();
    }

    @Override
    public void payFial() {

    }
}
