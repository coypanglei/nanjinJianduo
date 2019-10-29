package com.shaoyue.weizhegou.module.user.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.entity.user.UserMsgBean;
import com.shaoyue.weizhegou.module.user.adapter.NotesAdapter;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 作者：PangLei on 2019/5/15 0015 10:25
 * <p>
 * 邮箱：434604925@qq.com
 */
public class AccountNotesFragment extends DialogFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {


    Unbinder unbinder;
    @BindView(R.id.rv_notes)
    RecyclerView mRvContent;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;
    private int page = 1;
    private NotesAdapter mNotesAdapter;

    private int pages = 1;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_account_notes, null);
        unbinder = ButterKnife.bind(this, view);
        initView(dialog, view);
        mNotesAdapter =new NotesAdapter();
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContent.setAdapter(mNotesAdapter);
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        initMsg();
        return dialog;
    }


    public static AccountNotesFragment newInstance() {
        Bundle args = new Bundle();
        AccountNotesFragment fragment = new AccountNotesFragment();
        fragment.setArguments(args);
        return fragment;
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


//
//    private void showDeleteDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("南网商旅");
//        builder.setMessage("是否关闭指纹登录？");
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                mFingerprintImg.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.icon_btn_close));
//                mFingerprintTV.setVisibility(View.GONE);
//                mChangeGesture.setVisibility(View.GONE);
//                isFingerprint = false;
//                isFingerprint = SPUtils.getInstance().getBoolean(UserMgr.ISFINGERPRINT_KEY, false);
//                dialog.dismiss();
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
//        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
//    }

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
        page = 1;
        UserApi.getInfoMsg(page, "10", new BaseCallback<BaseResponse<UserMsgBean>>() {
            @Override
            public void onSucc(BaseResponse<UserMsgBean> result) {
                mNotesAdapter.setNewData(result.data.getRecord());
            }
        }, this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
               initMsg();
            }
        }, 1000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        int size = pages + 1;
        if (page == size) {
            mRefreshLayout.endLoadingMore();
            ToastUtil.showBlackToastSucess("没有更多的数据了");
            return false;
        }
        UserApi.getInfoMsg(page, "10", new BaseCallback<BaseResponse<UserMsgBean>>() {
            @Override
            public void onSucc(final BaseResponse<UserMsgBean> result) {

                pages = result.data.getPages();

                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mRefreshLayout == null) {
                            return;
                        }
                        mRefreshLayout.endLoadingMore();
                        mNotesAdapter.addData(result.data.getRecord());
                        page++;
                    }
                }, 1000);
            }
        }, this);


        return true;
    }
}
