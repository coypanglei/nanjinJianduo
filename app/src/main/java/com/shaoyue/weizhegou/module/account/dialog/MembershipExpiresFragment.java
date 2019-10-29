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
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;

import butterknife.ButterKnife;

/**
 * 作者：HQY on 17/7/17 14:54
 * 邮箱：hqy_xz@126.com
 */

public class MembershipExpiresFragment extends DialogFragment implements DialogInterface.OnKeyListener {

    private static final String EXTRA_MEMBER_CONTENT = "extra_member_content";


    private String mActvityContent;


    public static MembershipExpiresFragment newInstance(String activityContent) {
        MembershipExpiresFragment dialog = new MembershipExpiresFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_MEMBER_CONTENT, activityContent);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mActvityContent = bundle.getString(EXTRA_MEMBER_CONTENT);
        } else {
            dismiss();
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstance) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_not_sufficient_funds, null);


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
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        TextView activityContentText = (TextView) view.findViewById(R.id.tv_member_time);
        activityContentText.setText(mActvityContent);
        view.findViewById(R.id.sb_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.PAY_PACKAGE);
            }
        });


    }
}
