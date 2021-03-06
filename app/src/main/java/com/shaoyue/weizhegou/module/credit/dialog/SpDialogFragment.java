package com.shaoyue.weizhegou.module.credit.dialog;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.entity.dhgl.YunSpBean;
import com.shaoyue.weizhegou.util.McUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：PangLei on 2019/5/15 0015 10:25
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SpDialogFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.tv_xyhj)
    TextView tvXyhj;
    @BindView(R.id.tv_xyhj_spr)
    TextView tvXyhjSpr;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.ll_sp)
    LinearLayout llSp;
    @BindView(R.id.tv_pass)
    TextView tvPass;
    @BindView(R.id.tv_ht)
    TextView tvHt;


    private String id;

    private String cjjg = "同意";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sp, null);
        unbinder = ButterKnife.bind(this, view);
        etName.clearFocus();
        DhApi.yuSp(id, new BaseCallback<BaseResponse<YunSpBean>>() {
            @Override
            public void onSucc(BaseResponse<YunSpBean> result) {
                tvXyhj.setText(McUtils.getString(result.data.getXyhj()));
                tvXyhjSpr.setText(result.data.getClrmc());
            }
        }, this);
        initView(dialog, view);

        return dialog;
    }


    public static SpDialogFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putSerializable("id", id);
        SpDialogFragment fragment = new SpDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            id = getArguments().getString("id");
        }
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


    @OnClick({R.id.tv_cancel, R.id.iv_close, R.id.tv_pass, R.id.tv_ht, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_pass:
                llSp.setVisibility(View.VISIBLE);
                Drawable drawable = getResources().getDrawable(R.drawable.icon_left_star_black);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                cjjg = "同意";
                tvHt.setCompoundDrawables(drawable, null, null, null);
                Drawable drawable2 = getResources().getDrawable(R.drawable.icon_left_blue);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                tvPass.setCompoundDrawables(drawable2, null, null, null);
                break;
            case R.id.tv_ht:
                cjjg = "退回至上一节点";
                llSp.setVisibility(View.GONE);
                Drawable drawable3 = getResources().getDrawable(R.drawable.icon_left_star_black);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());

                tvPass.setCompoundDrawables(drawable3, null, null, null);
                Drawable drawable4 = getResources().getDrawable(R.drawable.icon_left_blue);
                drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
                tvHt.setCompoundDrawables(drawable4, null, null, null);
                break;
            case R.id.tv_save:
                String clyi = etName.getText().toString().trim();
                DhApi.goSp(id, cjjg, clyi, new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        EventBus.getDefault().post(new RefreshBean());
                        dismiss();
                    }
                }, this);
                break;

        }
    }


}