package com.shaoyue.weizhegou.module.credit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.DiaoChaApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.diaocha.StartDiaochaBean;
import com.shaoyue.weizhegou.entity.diaocha.sxDiaoChaBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.diaocha.sqDiaochaAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.util.XClick.SingleClick;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


public class shouXinSurveyFragment extends BaseAppFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    String title;
    @BindView(R.id.et_name)
    EditText mEtName;
    PopupWindow popupBigPhoto;
    View popupBigPhotoview;
    @BindView(R.id.tv_more)
    TextView mTvMore;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.rv_application)
    RecyclerView mRvApplication;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.sb_update)
    StateButton sbUpdate;
    @BindView(R.id.tv_dc_status)
    TextView tvDcStatus;

    private int page = 1;
    View popupSelectView;
    PopupWindow popupSelect;
    private int pages = 1;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    private String approvalFlag = "Y";
    private String lczt;
    private sqDiaochaAdapter mAdapter;
    View popupChangeView;
    PopupWindow popupUpdate;

    View popupDiaochaView;
    PopupWindow popupDiaochaStatus;

    public static shouXinSurveyFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        shouXinSurveyFragment fragment = new shouXinSurveyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sx_survey;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            title = getArguments().getString("title");
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setPopupBigPhoto();
        setPopupSelectView();
        setPopupUpdate();
        setPopupDiaochaStatus();
        mAdapter = new sqDiaochaAdapter();
        mRvApplication.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvApplication.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<sxDiaoChaBean.RecordsBean> mlist = adapter.getData();
                for (sxDiaoChaBean.RecordsBean data : mlist) {
                    data.setClick(false);
                }
                mlist.get(position).setClick(true);
                adapter.setNewData(mlist);
            }
        });
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        initData();
    }

    private String dczt;

    /**
     * 模型弹出框
     */
    private void setPopupDiaochaStatus() {
        popupDiaochaView = getLayoutInflater().inflate(R.layout.popwindow_diaocha_status, null);
        popupDiaochaView.findViewById(R.id.tv_wdc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupDiaochaStatus.isShowing()) {
                    popupDiaochaStatus.dismiss();
                }

                dczt = "未调查";
                initData();

            }
        });
        popupDiaochaView.findViewById(R.id.tv_dcz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupDiaochaStatus.isShowing()) {
                    popupDiaochaStatus.dismiss();
                }

                dczt = "调查中";
                initData();


            }
        });

        popupDiaochaView.findViewById(R.id.tv_ydc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupDiaochaStatus.isShowing()) {
                    popupDiaochaStatus.dismiss();
                }
                dczt = "已调查";
                initData();


            }
        });


    }


    private String sxmx;

    /**
     * 模型弹出框
     */
    private void setPopupUpdate() {
        popupChangeView = getLayoutInflater().inflate(R.layout.popwindow_update_type, null);
        popupChangeView.findViewById(R.id.tv_gx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupUpdate.isShowing()) {
                    popupUpdate.dismiss();
                }
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    sxmx = "工薪类";
                    updateMX();
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }
            }
        });
        popupChangeView.findViewById(R.id.tv_jhjy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupUpdate.isShowing()) {
                    popupUpdate.dismiss();
                }
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    sxmx = "简化经营";
                    updateMX();
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }


            }
        });

        popupChangeView.findViewById(R.id.tv_jinying).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupUpdate.isShowing()) {
                    popupUpdate.dismiss();
                }
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    sxmx = "经营";
                    updateMX();
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }


            }
        });
        popupChangeView.findViewById(R.id.tv_nohu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupUpdate.isShowing()) {
                    popupUpdate.dismiss();
                }
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    sxmx = "农户";
                    updateMX();
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }


            }
        });


    }


    /**
     * 更新模型
     */
    private void updateMX() {
        DiaoChaApi.getUpdateMx(getSelect().getId(), sxmx, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                initData();
            }
        }, this);
    }


    /**
     * 更多弹出框
     */
    private void setPopupBigPhoto() {
        popupBigPhotoview = getLayoutInflater().inflate(R.layout.popwindow_credit_application, null);
        popupBigPhotoview.findViewById(R.id.tv_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupBigPhoto.isShowing()) {
                    popupBigPhoto.dismiss();
                }
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    if (ObjectUtils.isNotEmpty(getSelect().getId())) {
                        //请求id 身份证
                        SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getSxid());
                        SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getSxsfzh());
                        UIHelper.showDcCommonActivity("调查",getActivity(), "查看详情");
                    }
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }
            }
        });
        popupBigPhotoview.findViewById(R.id.tv_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupBigPhoto.isShowing()) {
                    popupBigPhoto.dismiss();
                }
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    if (ObjectUtils.isNotEmpty(getSelect().getInstid())) {
                        UIHelper.showApplicationProgressDialog(getActivity(), getSelect().getInstid());
                    } else {
                        ToastUtil.showBlackToastSucess("未有进度");
                    }
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }


            }
        });


    }

    private void setPopupSelectView() {
        popupSelectView = getLayoutInflater().inflate(R.layout.popwindow_select_lczt, null);

        popupSelectView.findViewById(R.id.tv_dcl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupSelect.isShowing()) {
                    popupSelect.dismiss();
                }
                approvalFlag = "Y";
                lczt = "";
                initData();
            }
        });
        popupSelectView.findViewById(R.id.tv_ycl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupSelect.isShowing()) {
                    popupSelect.dismiss();
                }
                approvalFlag = "N";
                lczt = "已处理";
                initData();

            }
        });
        popupSelectView.findViewById(R.id.tv_spz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupSelect.isShowing()) {
                    popupSelect.dismiss();
                }
                approvalFlag = "N";
                lczt = "审批中";
                initData();
            }
        });
        popupSelectView.findViewById(R.id.tv_yzz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupSelect.isShowing()) {
                    popupSelect.dismiss();
                }
                approvalFlag = "N";
                lczt = "已终止";
                initData();
            }
        });
        popupSelectView.findViewById(R.id.tv_ywc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupSelect.isShowing()) {
                    popupSelect.dismiss();
                }
                approvalFlag = "N";
                lczt = "已完成";
                initData();
            }
        });
        popupSelectView.findViewById(R.id.tv_dtj).setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("是否取消申请?")) {
            sxDiaoChaBean.RecordsBean item = null;
            for (sxDiaoChaBean.RecordsBean data : mAdapter.getData()) {
                if (data.isClick()) {
                    item = data;
                }
            }
            if (ObjectUtils.isNotEmpty(item)) {
                CeditApi.detelProcessInstance(item.getId(), new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        initData();
                    }
                }, this);
            }

        }
    }

    private sxDiaoChaBean.RecordsBean getSelect() {
        for (sxDiaoChaBean.RecordsBean data : mAdapter.getData()) {
            if (data.isClick()) {
                return data;
            }
        }
        return null;
    }

    @SingleClick
    @OnClick({R.id.sb_find, R.id.tv_more, R.id.sb_diaocha, R.id.iv_clear, R.id.sb_update, R.id.tv_dc_status
            , R.id.tv_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_diaocha:
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    if (ObjectUtils.isEmpty(getSelect().getSxlx())) {
                        DiaoChaApi.getMxData(getSelect().getSxid(), new BaseCallback<BaseResponse<StartDiaochaBean>>() {
                            @Override
                            public void onSucc(BaseResponse<StartDiaochaBean> result) {
                                StartDiaochaBean bean = result.data;
                                bean.setId(getSelect().getId());
                                UIHelper.showStartDiaoCha(getActivity(), bean);
                            }
                        }, this);
                    }
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }

                break;

            case R.id.tv_dc_status:
                if (popupDiaochaStatus == null) {
                    popupDiaochaStatus = new PopupWindow(popupDiaochaView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    popupDiaochaStatus.setOutsideTouchable(true);
                }
                final ImageView ivwdc = popupDiaochaView.findViewById(R.id.iv_wdc);
                final ImageView ivydc = popupDiaochaView.findViewById(R.id.iv_ydc);
                final ImageView ivdcz = popupDiaochaView.findViewById(R.id.iv_dcz);
                if ("未调查".equals(dczt)) {
                    ivwdc.setVisibility(View.VISIBLE);
                    ivydc.setVisibility(View.GONE);
                    ivdcz.setVisibility(View.GONE);
                } else if ("已调查".equals(dczt)) {
                    ivwdc.setVisibility(View.GONE);
                    ivydc.setVisibility(View.VISIBLE);
                    ivdcz.setVisibility(View.GONE);
                } else if ("调查中".equals(dczt)) {
                    ivwdc.setVisibility(View.GONE);
                    ivydc.setVisibility(View.GONE);
                    ivdcz.setVisibility(View.VISIBLE);
                }
                if (popupDiaochaStatus.isShowing()) {
                    popupDiaochaStatus.dismiss();
                } else {
                    popupDiaochaStatus.showAsDropDown(tvDcStatus);
                }

                break;
            case R.id.sb_update:
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    sxmx = getSelect().getSxlx();
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                    return;

                }
                if (popupUpdate == null) {
                    popupUpdate = new PopupWindow(popupChangeView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    popupUpdate.setOutsideTouchable(true);
                }

                final ImageView ivGX = popupChangeView.findViewById(R.id.iv_gx);
                final ImageView ivjhjy = popupChangeView.findViewById(R.id.iv_jhjy);
                final ImageView ivjy = popupChangeView.findViewById(R.id.iv_jinying);
                final ImageView ivnh = popupChangeView.findViewById(R.id.iv_nohu);


                if ("工薪类".equals(sxmx)) {

                    ivGX.setVisibility(View.VISIBLE);
                    ivjhjy.setVisibility(View.GONE);
                    ivjy.setVisibility(View.GONE);
                    ivnh.setVisibility(View.GONE);
                }

                if ("简化经营".equals(sxmx)) {

                    ivGX.setVisibility(View.GONE);
                    ivjhjy.setVisibility(View.VISIBLE);
                    ivjy.setVisibility(View.GONE);
                    ivnh.setVisibility(View.GONE);
                }

                if ("经营".equals(sxmx)) {

                    ivGX.setVisibility(View.GONE);
                    ivjhjy.setVisibility(View.GONE);
                    ivjy.setVisibility(View.VISIBLE);
                    ivnh.setVisibility(View.GONE);
                }

                if ("农户".equals(sxmx)) {

                    ivGX.setVisibility(View.GONE);
                    ivjhjy.setVisibility(View.GONE);
                    ivjy.setVisibility(View.GONE);
                    ivnh.setVisibility(View.VISIBLE);
                }


                if (popupUpdate.isShowing()) {
                    popupUpdate.dismiss();
                } else {
                    popupUpdate.showAsDropDown(sbUpdate);
                }
                break;
            case R.id.sb_find:
                initData();
                break;
            case R.id.tv_more:

                if (popupBigPhoto == null) {
                    popupBigPhoto = new PopupWindow(popupBigPhotoview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    popupBigPhoto.setOutsideTouchable(true);
                }
                if (popupBigPhoto.isShowing()) {
                    popupBigPhoto.dismiss();
                } else {
                    popupBigPhoto.showAsDropDown(mTvMore, -200, 0);
                }
                break;
            //清理输入框
            case R.id.iv_clear:
                mEtName.setText("");
                break;
            //更多状态
            case R.id.tv_status:
                if (popupSelect == null) {
                    popupSelect = new PopupWindow(popupSelectView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    popupSelect.setOutsideTouchable(true);


                }
                final ImageView ivDcl = popupSelectView.findViewById(R.id.iv_dcl);
                final ImageView ivYcl = popupSelectView.findViewById(R.id.iv_ycl);
                final ImageView ivSpz = popupSelectView.findViewById(R.id.iv_spz);
                final ImageView ivYzz = popupSelectView.findViewById(R.id.iv_yzz);
                final ImageView ivYwc = popupSelectView.findViewById(R.id.iv_ywc);
                final ImageView ivDtj = popupSelectView.findViewById(R.id.iv_dtj);

                if ("Y".equals(approvalFlag)) {
                    ivDcl.setVisibility(View.VISIBLE);
                    ivYcl.setVisibility(View.GONE);
                    ivSpz.setVisibility(View.GONE);
                    ivYzz.setVisibility(View.GONE);
                    ivYwc.setVisibility(View.GONE);
                    ivDtj.setVisibility(View.GONE);
                }
                if ("N".equals(approvalFlag)) {
                    ivDcl.setVisibility(View.GONE);
                    ivYcl.setVisibility(View.VISIBLE);
                    ivSpz.setVisibility(View.GONE);
                    ivYzz.setVisibility(View.GONE);
                    ivYwc.setVisibility(View.GONE);
                    ivDtj.setVisibility(View.GONE);
                }
                if ("审批中".equals(lczt)) {
                    ivDcl.setVisibility(View.GONE);
                    ivYcl.setVisibility(View.GONE);
                    ivSpz.setVisibility(View.VISIBLE);
                    ivYzz.setVisibility(View.GONE);
                    ivYwc.setVisibility(View.GONE);
                    ivDtj.setVisibility(View.GONE);
                }

                if ("已终止".equals(lczt)) {
                    ivDcl.setVisibility(View.GONE);
                    ivYcl.setVisibility(View.GONE);
                    ivSpz.setVisibility(View.GONE);
                    ivYzz.setVisibility(View.VISIBLE);
                    ivYwc.setVisibility(View.GONE);
                    ivDtj.setVisibility(View.GONE);
                }
                if ("待提交".equals(lczt)) {
                    ivDcl.setVisibility(View.GONE);
                    ivYcl.setVisibility(View.GONE);
                    ivSpz.setVisibility(View.GONE);
                    ivYzz.setVisibility(View.GONE);
                    ivYwc.setVisibility(View.GONE);
                    ivDtj.setVisibility(View.VISIBLE);
                }

                if ("已完成".equals(lczt)) {
                    ivDcl.setVisibility(View.GONE);
                    ivYcl.setVisibility(View.GONE);
                    ivSpz.setVisibility(View.GONE);
                    ivYzz.setVisibility(View.GONE);
                    ivYwc.setVisibility(View.VISIBLE);
                    ivDtj.setVisibility(View.GONE);
                }


                if (popupSelect.isShowing()) {
                    popupSelect.dismiss();
                } else {
                    popupSelect.showAsDropDown(mTvStatus);
                }
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
                initData();
            }
        }, 1000);
    }


    private void initData() {
        page = 1;
        String mNameOrId = mEtName.getText().toString().trim();
        DiaoChaApi.getDiaoChaList(page, "12", mNameOrId, approvalFlag, dczt, lczt, new BaseCallback<BaseResponse<sxDiaoChaBean>>() {
            @Override
            public void onSucc(BaseResponse<sxDiaoChaBean> result) {
                mAdapter.setNewData(result.data.getRecords());
                mAdapter.notifyDataSetChanged();
                if (result.data.getRecords().size() > 0) {
                    mEmptyRelative.setVisibility(View.GONE);
                } else {
                    mEmptyRelative.setVisibility(View.VISIBLE);

                }
            }
        }, this);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (page == pages) {
            mRefreshLayout.endLoadingMore();
            ToastUtil.showBlackToastSucess("没有更多的数据了");
            return false;
        }
        String mNameOrId = mEtName.getText().toString().trim();

        DiaoChaApi.getDiaoChaList(page, "12", mNameOrId, approvalFlag, dczt, lczt, new BaseCallback<BaseResponse<sxDiaoChaBean>>() {
            @Override
            public void onSucc(final BaseResponse<sxDiaoChaBean> result) {

                pages = result.data.getPages();

                if (mRefreshLayout == null) {
                    return;
                }
                mRefreshLayout.endLoadingMore();
                mAdapter.addData(result.data.getRecords());
                page++;

            }
        }, this);


        return true;
    }


}
