package com.shaoyue.weizhegou.base.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;

import butterknife.ButterKnife;

public class InfoShowFragment extends DialogFragment implements DialogInterface.OnKeyListener {

    private OkOrCancelEvent mOk;

    public static InfoShowFragment newInstance(OkOrCancelEvent title) {
        Bundle args = new Bundle();
        args.putSerializable("okorcancel", title);
        InfoShowFragment fragment = new InfoShowFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mOk = (OkOrCancelEvent) getArguments().getSerializable("okorcancel");
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_jb_info, null);
        TextView mTitle = view.findViewById(R.id.tv_content);
        mTitle.setText(mOk.getmType());



        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        ButterKnife.bind(this, view);
        return dialog;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return false;
    }
}