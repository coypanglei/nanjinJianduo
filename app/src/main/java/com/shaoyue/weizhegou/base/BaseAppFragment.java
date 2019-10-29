package com.shaoyue.weizhegou.base;

import android.app.Dialog;

import com.dyhdyh.widget.loading.dialog.LoadingDialog;
import com.lzy.okgo.OkGo;

import com.shaoyue.weizhegou.widget.SimpleDialogFactory;


/**
 * Created by librabin on 16/11/28.
 */

public abstract class BaseAppFragment extends BaseFragment {


    /**
     * 创建Dialog对象
     *
     * @param msg
     * @param
     */
    public void startProgressDialog(String msg, boolean canCancel) {
        Dialog dialog = LoadingDialog.make(getActivity(), new SimpleDialogFactory())
                .setMessage(msg)
                .setCancelable(canCancel)
                .create();
        dialog.show();
    }

    public void startProgressDialog(boolean canCancel) {
        startProgressDialog("加载中", canCancel);
    }

    /**
     * 关闭Dialog对象
     */
    public void stopProgressDialog() {
        LoadingDialog.cancel();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unSubscribe();
        OkGo.getInstance().cancelTag(this);
    }


    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        addFragment(fragment, ANIMA_TYPE_DEFAULT);
    }

    protected void addFragment(BaseFragment fragment, int animType) {
        if (null != fragment && getHoldingActivity() instanceof BaseCommonActivity) {
            ((BaseCommonActivity) getHoldingActivity()).addFragment(fragment, animType);
        }
    }




    //移除fragment
    protected void removeFragment() {
        if (getHoldingActivity() instanceof BaseCommonActivity) {
            ((BaseCommonActivity) getHoldingActivity()).removeFragment();
        }
    }
}








