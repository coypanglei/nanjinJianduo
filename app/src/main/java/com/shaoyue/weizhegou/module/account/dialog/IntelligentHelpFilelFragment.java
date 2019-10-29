package com.shaoyue.weizhegou.module.account.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.shaoyue.weizhegou.R;

import butterknife.ButterKnife;

/**
 * 作者：HQY on 17/7/17 14:54
 * 邮箱：hqy_xz@126.com
 */

public class IntelligentHelpFilelFragment extends DialogFragment implements DialogInterface.OnKeyListener {

    private String title = "";

    public static IntelligentHelpFilelFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        IntelligentHelpFilelFragment fragment = new IntelligentHelpFilelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_intelligent_file, null);
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
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
    public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }
}
