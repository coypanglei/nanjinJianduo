package com.shaoyue.weizhegou.module.credit.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.f1reking.signatureview.SignatureView;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.entity.cedit.NewQianZiBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.wildma.pictureselector.Constant;
import com.wildma.pictureselector.FileUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：PangLei on 2019/5/15 0015 10:25
 * <p>
 * 邮箱：434604925@qq.com
 */
public class NewQianziDialogFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.sign_view)
    SignatureView mSignView;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_qianzi, null);
        unbinder = ButterKnife.bind(this, view);


        initView(dialog, view);

        return dialog;
    }


    public static NewQianziDialogFragment newInstance() {
        Bundle args = new Bundle();

        NewQianziDialogFragment fragment = new NewQianziDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /**
     * 初始化View
     *
     * @param dialog
     * @param view
     */
    private void initView(Dialog dialog, View view) {

        //动画
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;

        Window window = dialog.getWindow();
//// 把 DecorView 的默认 padding 取消，同时 DecorView 的默认大小也会取消
        WindowManager.LayoutParams layoutParams = window.getAttributes();
// 设置宽度
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
// 给 DecorView 设置背景颜色，很重要，不然导致 Dialog 内容显示不全，有一部分内容会充当 padding，上面例子有举出
        dialog.setContentView(view, layoutParams);
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }


    @OnClick({R.id.tv_clear, R.id.iv_close, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_clear:
                mSignView.clear();
                break;
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_ok:
                //上传签名

                AndPermission.with(this)
                        .runtime()
                        .permission(Permission.WRITE_EXTERNAL_STORAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                try {
                                    FileUtils.createOrExistsDir(Constant.DIR_ROOT);
                                    //bitmap转签名文件
                                    StringBuffer buffer = new StringBuffer();
                                    String pathName = buffer.append(Constant.DIR_ROOT).append(Constant.APP_NAME).append(".").append(System.currentTimeMillis()).append(".jpg").toString();
                                    mSignView.save(pathName, false, 1);
                                    //上传图片
                                    UserApi.updatePic(FileUtils.getFileByPath(pathName), SPUtils.getInstance().getString(UserMgr.SP_ID_CARD), new BaseCallback<BaseResponse<String>>() {
                                        @Override
                                        public void onSucc(BaseResponse<String> result) {
                                            //传递给上个界面
                                            EventBus.getDefault().post(new NewQianZiBean(result.msg));
                                            dismiss();
                                        }

                                        @Override
                                        public void onFail(ApiException apiError) {
                                            super.onFail(apiError);
                                            LogUtils.e(apiError);
                                        }
                                    }, this);
                                } catch (IOException e) {
                                    LogUtils.e(e);
                                    e.printStackTrace();
                                }

                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                ToastUtil.showErrorToast(getResources().getString(R.string.t_permission_denied));
                            }
                        })
                        .start();

                break;

        }
    }


}