package com.shaoyue.weizhegou.api.callback;

import android.app.Dialog;
import android.content.Context;

import com.dyhdyh.widget.loading.dialog.LoadingDialog;
import com.lzy.okgo.request.base.Request;

import com.shaoyue.weizhegou.widget.SimpleDialogFactory;

/**
 * Created by bin on 17/6/4.
 */

public abstract class DialogCallback<T> extends BaseCallback<T> {

    private Dialog dialog;



    public DialogCallback(Context context) {
        super();
        initDialog(context, "加载中...");
    }

    public DialogCallback(Context context, String msg) {
        super();
        initDialog(context, msg);
    }

    private void initDialog(Context context, String msg) {
        dialog = LoadingDialog.make(context, new SimpleDialogFactory())
                .setMessage(msg)
                .setCancelable(false)
                .create();
    }


    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
