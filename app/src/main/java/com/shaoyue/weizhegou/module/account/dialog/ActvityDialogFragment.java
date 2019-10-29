package com.shaoyue.weizhegou.module.account.dialog;

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
import android.widget.TextView;


import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.ActivityBean;
import com.shaoyue.weizhegou.router.UIHelper;

import butterknife.ButterKnife;

/**
 * 作者：HQY on 17/7/17 14:54
 * 邮箱：hqy_xz@126.com
 */

public class ActvityDialogFragment extends DialogFragment implements DialogInterface.OnKeyListener {

    private static final String EXTRA_MEMBER_CONTENT = "extra_member_content";


    private ActivityBean mActvityBean;


    public static ActvityDialogFragment newInstance(ActivityBean activityBean) {
        ActvityDialogFragment dialog = new ActvityDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_MEMBER_CONTENT, activityBean);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mActvityBean = (ActivityBean) bundle.getSerializable(EXTRA_MEMBER_CONTENT);
        } else {
            dismiss();
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstance) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_activity_everyday, null);


        initView(view);


        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;
        dialog.setContentView(view);
        dialog.setOnKeyListener(this);
        dialog.setCanceledOnTouchOutside(false);
        ButterKnife.bind(this, view);

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
            attributes.gravity = Gravity.CENTER;//对齐方式

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
        if (!mActvityBean.getIsClosed().equals("1")) {
            view.findViewById(R.id.tv_close).setVisibility(View.VISIBLE);
            view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();

                }
            });
        }


        TextView activityNameText = (TextView) view.findViewById(R.id.tv_activity_title);
        activityNameText.setText(mActvityBean.getName());
        TextView activityContentText = (TextView) view.findViewById(R.id.tv_activity_content);
        activityContentText.setText(mActvityBean.getContent());
        TextView activityBtnText = (TextView) view.findViewById(R.id.sb_confirm);
        activityBtnText.setText(mActvityBean.getBtn());
        view.findViewById(R.id.sb_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                UIHelper.showWebPage(getActivity(), mActvityBean.getJumpTitle(), mActvityBean.getJumpUrl());
            }
        });


    }
}
