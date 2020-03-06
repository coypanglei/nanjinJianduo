package com.shaoyue.weizhegou.module.credit.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.DiaoChaApi;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.diaocha.StartDiaochaBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.diaocha.StartDCAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ObjectToMapUtils;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.DropDownView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：PangLei on 2019/5/15 0015 10:25
 * <p>
 * 邮箱：434604925@qq.com
 */
public class StartDcDialogFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.tv_my_info)
    TextView mTvinfo;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    StartDCAdapter mAdapter;

    StartDCAdapter mAdapterTwo;
    @BindView(R.id.rv_list_two)
    RecyclerView rvListTwo;
    @BindView(R.id.ddv_xb)
    DropDownView ddvXb;
    @BindView(R.id.sc_all)
    NestedScrollView scAll;


    private String sxmx;
    private List<BasicInformationBean.RecordsBean> mlist = new ArrayList<>();
    private List<BasicInformationBean.RecordsBean> mlistTwo = new ArrayList<>();
    private StartDiaochaBean startDiaochaBean;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_start_dc, null);
        unbinder = ButterKnife.bind(this, view);
        initView(dialog, view);
        mAdapter = new StartDCAdapter();
        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvList.setAdapter(mAdapter);
        mAdapterTwo = new StartDCAdapter();
        rvListTwo.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvListTwo.setAdapter(mAdapterTwo);
        ddvXb.setOnItemClickListener(new DropDownView.OnItemClickListener() {
            @Override
            public void onItemClick(Map<String, Object> map, int pos, int realPos) {

                sxmx = map.get("key").toString();


            }
        });

        shaxing();

        return dialog;
    }


    public static StartDcDialogFragment newInstance(StartDiaochaBean goAllSelect) {
        Bundle args = new Bundle();
        args.putSerializable("start_dc", goAllSelect);
        StartDcDialogFragment fragment = new StartDcDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            startDiaochaBean = (StartDiaochaBean) getArguments().getSerializable("start_dc");
        }
    }

    /**
     * 刷新数据
     */

    private void shaxing() {
        CeditApi.getListAll("开始调查", "", new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                scAll.setVisibility(View.VISIBLE);
                mlist.clear();
                mlistTwo.clear();

                Map<String, String> map = ObjectToMapUtils.str2Map(startDiaochaBean);
                for (BasicInformationBean.RecordsBean bean : result.data.getRecords()) {

                    if (map.containsKey(bean.getName())) {
                        bean.setEdit(true);
                        bean.setDefaultvalue(map.get(bean.getName()));

                    }
                    if ("基本信息".equals(bean.getCategory())) {
                        mlist.add(bean);
                    } else if ("经营信息".equals(bean.getCategory())) {
                        mlistTwo.add(bean);
                    }
                }

                mAdapter.setNewData(mlist);
                mAdapter.notifyDataSetChanged();
                mAdapterTwo.setNewData(mlistTwo);
                mAdapterTwo.notifyDataSetChanged();

            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);

            }
        }, this);
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


    @OnClick({R.id.iv_close, R.id.sb_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.sb_edit:
                if (ObjectUtils.isEmpty(sxmx)) {
                    ToastUtil.showBlackToastSucess("没有选择授信调查模板");
                    return;
                }

                DiaoChaApi.getUpdateMx(startDiaochaBean.getId(), sxmx, new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        dismiss();
                        SPUtils.getInstance().put(UserMgr.SP_DC_TYPE, sxmx);
                        UIHelper.showDcCommonActivity("调查", getActivity(), "调查");
                    }
                }, this);
                break;

        }
    }


}