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

import com.blankj.utilcode.util.LogUtils;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.util.ToastUtil;


/**
 * 作者：HQY on 18/1/26 16:38
 * 邮箱：hqy_xz@126.com
 */

public class PayFialDialogFragment extends DialogFragment implements DialogInterface.OnKeyListener {


    public static PayFialDialogFragment newInstance() {
        Bundle args = new Bundle();
        PayFialDialogFragment fragment = new PayFialDialogFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle saveInstance) {
        Dialog dialog = new Dialog(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_pay_fial, null);
        dialog.getWindow().getAttributes().windowAnimations =R.style.dialogAnim;
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

        TextView mTvPayName = view.findViewById(R.id.tv_pay_time);
        String mQQ = getText(R.string.tv_pay_content) + AppMgr.getInstance().getQQ();
        LogUtils.e(mQQ);
        mTvPayName.setText(mQQ);

        view.findViewById(R.id.tv_pay_fial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showErrorToast(getString(R.string.t_failure_to_pay));
                getActivity().finish();

                dismiss();
            }
        });
    }

}
