package com.shaoyue.weizhegou.module.dhgl.fragment;

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
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.XcjyBean;
import com.shaoyue.weizhegou.entity.cedit.XcjyListBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.dhgl.adapter.XcjyAdapter;
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


public class XzRlFragment extends BaseAppFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

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
    @BindView(R.id.sb_select_all)
    StateButton sbSelectAll;


    private int page = 1;
    View popupSelectView;
    PopupWindow popupSelect;
    private int pages = 1;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    private String approvalFlag = "Y";
    private String lczt;
    private XcjyAdapter mAdapter;


    public static XzRlFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        XzRlFragment fragment = new XzRlFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dh_xzrwrl;
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
        mAdapter = new XcjyAdapter();
        mRvApplication.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvApplication.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<XcjyBean> mlist = adapter.getData();
                if (!mlist.get(position).isClick()) {
                    mlist.get(position).setClick(true);
                } else {
                    mlist.get(position).setClick(false);
                }
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
                        SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getZjhm());
                        SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getZjhm());
                        UIHelper.showDcCommonActivity("现场检验", getActivity(), "查看详情");
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
        if (event.getmType().equals("确定要认领任务吗")) {
            List<XcjyBean> mlist = mAdapter.getData();
            StringBuilder sb = new StringBuilder();
            for (XcjyBean mMessageBean : mlist) {
                if (mMessageBean.isClick()) {
                    sb.append(mMessageBean.getId());
                    sb.append(",");
                }
            }
            String selectSb = sb.toString();
            if (ObjectUtils.isEmpty(selectSb) && selectSb.length() == 0) {
                ToastUtil.showBlackToastSucess("请选择需要删除的消息");
                return;
            }
            String ids = selectSb.substring(0, sb.length() - 1);
            DhApi.xcjyRl(ids, new BaseCallback<BaseResponse<Void>>() {
                @Override
                public void onSucc(BaseResponse<Void> result) {
                    ToastUtil.showBlackToastSucess("认领成功");
                    initData();
                }
            }, this);
        }
    }

    private XcjyBean getSelect() {
        for (XcjyBean data : mAdapter.getData()) {
            if (data.isClick()) {
                return data;
            }
        }
        return null;
    }



    @OnClick({R.id.sb_find, R.id.tv_more, R.id.sb_start_rl, R.id.sb_select_all
            , R.id.iv_clear, R.id.tv_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_select_all:
                List<XcjyBean> mlist = mAdapter.getData();
                for (XcjyBean bean : mlist) {
                    bean.setClick(true);
                }
                mAdapter.setNewData(mlist);

                break;
            case R.id.sb_find:
                initData();
                break;
            case R.id.sb_start_rl:
                StringBuilder sb = new StringBuilder();
                for (XcjyBean mMessageBean : mAdapter.getData()) {
                    if (mMessageBean.isClick()) {
                        sb.append(mMessageBean.getId());
                        sb.append(",");
                    }
                }
                String selectSb = sb.toString();
                if (ObjectUtils.isEmpty(selectSb) && selectSb.length() == 0) {
                    ToastUtil.showBlackToastSucess("请选择需要认领的任务");
                    return;
                }
                UIHelper.showOkClearDialog(getActivity(), "确定要认领任务吗");
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
        DhApi.getXcjyRlList(page, "12", mNameOrId, lczt, new BaseCallback<BaseResponse<XcjyListBean>>() {
            @Override
            public void onSucc(BaseResponse<XcjyListBean> result) {
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
        if (page == pages+1) {
            mRefreshLayout.endLoadingMore();
            ToastUtil.showBlackToastSucess("没有更多的数据了");
            return false;
        }
        String mNameOrId = mEtName.getText().toString().trim();

        DhApi.getXcjyRlList(page+1, "12", mNameOrId, lczt, new BaseCallback<BaseResponse<XcjyListBean>>() {
            @Override
            public void onSucc(final BaseResponse<XcjyListBean> result) {

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
