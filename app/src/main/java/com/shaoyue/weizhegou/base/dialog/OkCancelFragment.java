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

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public class OkCancelFragment extends DialogFragment implements DialogInterface.OnKeyListener {

    private String title = "";

    public static OkCancelFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        OkCancelFragment fragment = new OkCancelFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        title = getArguments().getString("title");
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_clear_history, null);
        TextView mTitle = view.findViewById(R.id.tv_title);
        mTitle.setText(title);
        view.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventBus.getDefault().post(new OkOrCancelEvent(title));
                dismiss();
            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        dialog.getWindow().getAttributes().windowAnimations = com.shaoyue.weizhegou.R.style.dialogAnim;
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