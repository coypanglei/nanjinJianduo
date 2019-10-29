package com.shaoyue.weizhegou.module.pay.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.PaySuccessEvent;
import com.shaoyue.weizhegou.entity.WebEntity;
import com.shaoyue.weizhegou.router.UIHelper;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者：HQY on 18/1/26 16:38
 * 邮箱：hqy_xz@126.com
 */

public class PayDialogFragment extends DialogFragment implements DialogInterface.OnKeyListener {


    private WebEntity mWeb;

    public static PayDialogFragment newInstance(WebEntity web) {
        Bundle args = new Bundle();
        args.putSerializable(UIHelper.CONTENT, web);
        PayDialogFragment fragment = new PayDialogFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            mWeb = (WebEntity) getArguments().getSerializable(UIHelper.CONTENT);
        }
    }

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle saveInstance) {

        Dialog dialog = new Dialog(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_pay_success, null);
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;
        dialog.setContentView(rootView);
        dialog.setOnKeyListener(this);
        dialog.setCanceledOnTouchOutside(false);
        setContent(rootView);
        return dialog;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }


    private void setContent(View view) {

        TextView mTvPayName = view.findViewById(R.id.tv_pay_name);
        TextView mTvPayOrder = view.findViewById(R.id.tv_pay_order);
        TextView mTvPayTime = view.findViewById(R.id.tv_pay_time);
        mTvPayName.setText(getString(R.string.content_name) + mWeb.getMoney());
        mTvPayOrder.setText(getString(R.string.content_order_number) + mWeb.getOrderId());
        mTvPayTime.setText(getString(R.string.content_trading_time) + mWeb.getTime());
        view.findViewById(R.id.tv_pay_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new PaySuccessEvent("1"));
                dismiss();
            }
        });
        view.findViewById(R.id.tv_pay_fial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new PaySuccessEvent("0"));
                dismiss();
            }
        });
    }




}
