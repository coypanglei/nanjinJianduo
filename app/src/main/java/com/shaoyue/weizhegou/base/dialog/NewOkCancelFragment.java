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
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.util.XClick.SingleClick;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public class NewOkCancelFragment extends DialogFragment implements DialogInterface.OnKeyListener {

    private OkOrCancelEvent mOk;

    public static NewOkCancelFragment newInstance(OkOrCancelEvent title) {
        Bundle args = new Bundle();
        args.putSerializable("okorcancel", title);
        NewOkCancelFragment fragment = new NewOkCancelFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mOk = (OkOrCancelEvent) getArguments().getSerializable("okorcancel");
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_clear_history, null);
        TextView mTitle = view.findViewById(R.id.tv_title);
        mTitle.setText(mOk.getmType());
        view.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View view) {
                switch (mOk.getUrl()) {
                    case "SXSQ":
                        CeditApi.deteleVideo(mOk.getId(), new BaseCallback<BaseResponse<Void>>() {
                            @Override
                            public void onSucc(BaseResponse<Void> result) {
                                EventBus.getDefault().post(mOk);
                                dismiss();
                            }

                            @Override
                            public void onFail(ApiException apiError) {
                                super.onFail(apiError);
                                dismiss();
                            }
                        }, this);

                        break;
                    case "XCJY":
                        DhApi.deteleVideo(DhApi.XCJY_VIDEO_DETAILS_DETELE, mOk.getId(), new BaseCallback<BaseResponse<Void>>() {
                            @Override
                            public void onSucc(BaseResponse<Void> result) {
                                EventBus.getDefault().post(mOk);
                                dismiss();
                            }

                            @Override
                            public void onFail(ApiException apiError) {
                                super.onFail(apiError);
                                dismiss();
                            }
                        }, this);
                        break;

                    case "GDJC":
                        DhApi.deteleVideo(DhApi.GD_VIDEO_DETAILS_DETELE, mOk.getId(), new BaseCallback<BaseResponse<Void>>() {
                            @Override
                            public void onSucc(BaseResponse<Void> result) {
                                EventBus.getDefault().post(mOk);
                                dismiss();
                            }

                            @Override
                            public void onFail(ApiException apiError) {
                                super.onFail(apiError);
                                dismiss();
                            }
                        }, this);
                        break;
                    case "DGJC":
                        DhApi.deteleVideo(DhApi.DG_VIDEO_DETAILS_DETELE, mOk.getId(), new BaseCallback<BaseResponse<Void>>() {
                            @Override
                            public void onSucc(BaseResponse<Void> result) {
                                EventBus.getDefault().post(mOk);
                                dismiss();
                            }

                            @Override
                            public void onFail(ApiException apiError) {
                                super.onFail(apiError);
                                dismiss();
                            }
                        }, this);
                        break;
                    case "SDJC":
                        DhApi.deteleVideo(DhApi.DHSJ_VIDEO_DETAILS_DETELE, mOk.getId(), new BaseCallback<BaseResponse<Void>>() {
                            @Override
                            public void onSucc(BaseResponse<Void> result) {
                                EventBus.getDefault().post(mOk);
                                dismiss();
                            }

                            @Override
                            public void onFail(ApiException apiError) {
                                super.onFail(apiError);
                                dismiss();
                            }
                        }, this);
                        break;
                    case "DBRYXZL":
                        DhApi.deteleVideo(DhApi.DBR_VIDEO_DETAILS_DETELE, mOk.getId(), new BaseCallback<BaseResponse<Void>>() {
                            @Override
                            public void onSucc(BaseResponse<Void> result) {
                                EventBus.getDefault().post(mOk);
                                dismiss();
                            }

                            @Override
                            public void onFail(ApiException apiError) {
                                super.onFail(apiError);
                                dismiss();
                            }
                        }, this);
                        break;

                    case "ZXSQ":

                        EventBus.getDefault().post(mOk);
                        dismiss();
                        break;

                    default:
                        break;
                }

            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
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