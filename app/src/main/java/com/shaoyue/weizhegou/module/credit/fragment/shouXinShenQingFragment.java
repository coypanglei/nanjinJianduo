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
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.ApplicationBean;
import com.shaoyue.weizhegou.entity.cedit.ApplicationListBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.CreditApplicationAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


public class shouXinShenQingFragment extends BaseAppFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

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
    private int page = 1;
    View popupSelectView;
    PopupWindow popupSelect;
    private int pages = 1;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    private String approvalFlag = "Y";
    private String lczt;
    private CreditApplicationAdapter mAdapter;


    public static shouXinShenQingFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        shouXinShenQingFragment fragment = new shouXinShenQingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_credit_application;
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setPopupBigPhoto();
        setPopupSelectView();
        mAdapter = new CreditApplicationAdapter();
        mRvApplication.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvApplication.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<ApplicationBean> mlist = adapter.getData();
                for (ApplicationBean data : mlist) {
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
                        SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getId());
                        SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getSfzh());
                        UIHelper.showApplyCommonActivity(getActivity(), "查看详情");
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
        popupSelectView.findViewById(R.id.tv_dtj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupSelect.isShowing()) {
                    popupSelect.dismiss();
                }
                approvalFlag = "Y";
                lczt = "待提交";
                initData();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("是否取消申请?")) {
            ApplicationBean item = null;
            for (ApplicationBean data : mAdapter.getData()) {
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

    private ApplicationBean getSelect() {
        for (ApplicationBean data : mAdapter.getData()) {
            if (data.isClick()) {
                return data;
            }
        }
        return null;
    }


    @OnClick({R.id.sb_find, R.id.sb_application, R.id.sb_edit, R.id.sb_cancel, R.id.sb_synchronize, R.id.tv_more
            , R.id.iv_clear, R.id.tv_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_find:
                initData();
                break;
            case R.id.sb_application:
                SPUtils.getInstance().put(UserMgr.SP_DC_TASKID, "");
                SPUtils.getInstance().put(UserMgr.SP_DC_INSTID, "");
                UIHelper.showIDCardDialog(getActivity());
                break;
            case R.id.sb_edit:

                if (ObjectUtils.isNotEmpty(getSelect())) {
                    if ("N".equals(approvalFlag)) {
                        ToastUtil.showBlackToastSucess("当前流程状态无法修改");
                        return;
                    }
                    if(ObjectUtils.isNotEmpty(getSelect().getLczt())){
                        if(!"待提交".equals(getSelect().getLczt())){
                            ToastUtil.showBlackToastSucess("当前流程状态无法修改");
                            return;
                        }
                    }
                    if (ObjectUtils.isNotEmpty(getSelect().getId())) {
                        //请求id 身份证
                        SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getId());
                        SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getSfzh());
                        SPUtils.getInstance().put(UserMgr.SP_DC_TASKID, getSelect().getTaskid());
                        SPUtils.getInstance().put(UserMgr.SP_DC_INSTID, getSelect().getInstid());
                        UIHelper.showApplyCommonActivity(getActivity(), "修改");
                    }
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }


                break;
            case R.id.sb_cancel:

                if (ObjectUtils.isEmpty(getSelect())) {
                    ToastUtil.showBlackToastSucess("请选择数据");
                    return;
                }

                if ("已终止".equals(getSelect().getLczt()) || "已完成".equals(getSelect().getLczt())) {
                    ToastUtil.showBlackToastSucess("当前流程状态无法修取消");
                    return;
                }
                UIHelper.showOkClearDialog(getActivity(), "是否取消申请?");

                break;
            case R.id.sb_synchronize:
                CeditApi.tbInfo(getSelect().getId(), new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        ToastUtil.showBlackToastSucess(result.msg);
                    }
                }, this);
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
        CeditApi.getApplicationList(page, "12", mNameOrId, approvalFlag, lczt, new BaseCallback<BaseResponse<ApplicationListBean>>() {
            @Override
            public void onSucc(BaseResponse<ApplicationListBean> result) {
                pages = result.data.getPages();
                mAdapter.setNewData(result.data.getmBaseBean());
                mAdapter.notifyDataSetChanged();
                if (result.data.getmBaseBean().size() > 0) {
                    mEmptyRelative.setVisibility(View.GONE);
                } else {
                    mEmptyRelative.setVisibility(View.VISIBLE);

                }
            }
        }, this);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (page == pages + 1) {
            mRefreshLayout.endLoadingMore();
            ToastUtil.showBlackToastSucess("没有更多的数据了");
            return false;
        }
        String mNameOrId = mEtName.getText().toString().trim();

        CeditApi.getApplicationList(page + 1, "12", mNameOrId, approvalFlag, lczt, new BaseCallback<BaseResponse<ApplicationListBean>>() {
            @Override
            public void onSucc(final BaseResponse<ApplicationListBean> result) {

                pages = result.data.getPages();

                if (mRefreshLayout == null) {
                    return;
                }
                mRefreshLayout.endLoadingMore();
                mAdapter.addData(result.data.getmBaseBean());
                page++;

            }
        }, this);


        return true;
    }


}
