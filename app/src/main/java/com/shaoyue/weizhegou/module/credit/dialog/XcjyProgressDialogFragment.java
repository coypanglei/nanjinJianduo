package com.shaoyue.weizhegou.module.credit.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.entity.dhgl.XcjyProgressBean;
import com.shaoyue.weizhegou.entity.dhgl.XcjyProgressListBean;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.XcjyProgressAdapter;

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
public class XcjyProgressDialogFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.rv_notes)
    RecyclerView mRvContent;
    private XcjyProgressAdapter mNotesAdapter;
    private String id;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_application_schedule, null);
        unbinder = ButterKnife.bind(this, view);
        initView(dialog, view);
        mNotesAdapter = new XcjyProgressAdapter();
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContent.setAdapter(mNotesAdapter);

        initMsg();
        return dialog;
    }


    public static XcjyProgressDialogFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("id", id);
        XcjyProgressDialogFragment fragment = new XcjyProgressDialogFragment();
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
        dialog.setCanceledOnTouchOutside(true);
    }


    @OnClick({R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_close:
                dismiss();
                break;

        }
    }

    /**
     * 获取消息列表
     */
    private void initMsg() {

        DhApi.xcjyProgress(new BaseCallback<BaseResponse<XcjyProgressListBean>>() {
            @Override
            public void onSucc(BaseResponse<XcjyProgressListBean> result) {

                List<XcjyProgressBean> mlist = result.data.getRecords();
                if (ObjectUtils.isNotEmpty(mlist) && mlist.size() > 0) {
                    mlist.get(0).setOne(true);
                    mNotesAdapter.setNewData(mlist);
                }

            }
        }, this);
    }


}