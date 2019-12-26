package com.shaoyue.weizhegou.module.dhgl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.XcjyBean;
import com.shaoyue.weizhegou.entity.cedit.XcjyListBean;
import com.shaoyue.weizhegou.entity.dhgl.SelectDataBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.dhgl.adapter.XcjyAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.util.XClick.SingleClick;
import com.shaoyue.weizhegou.widget.DropDownView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


public class XcjyFragment extends BaseAppFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

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
    @BindView(R.id.ddv_xb)
    DropDownView ddvXb;

    private int page = 1;
    View popupSelectView;
    PopupWindow popupSelect;
    private int pages = 1;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    private String approvalFlag = "Y";
    private String lczt;
    private XcjyAdapter mAdapter;
    private List<SelectDataBean> mSelect = new ArrayList<>();

    public static XcjyFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        XcjyFragment fragment = new XcjyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dh_xcdc;
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
        List<Map<String, Object>> dataList = new ArrayList<>();

        mSelect.clear();
//        mSelect.add(new SelectDataBean("-1", "待采集"));
//        mSelect.add(new SelectDataBean("0", "待认领"));
        mSelect.add(new SelectDataBean("1", "待现场检验"));
        mSelect.add(new SelectDataBean("2", "待协查"));
        mSelect.add(new SelectDataBean("3", "待小组组长检查"));
        mSelect.add(new SelectDataBean("4", "待信贷部总经理审核"));
        mSelect.add(new SelectDataBean("6", "待授信部总经理审核"));
        mSelect.add(new SelectDataBean("5", "待授信部审批岗审核"));
        mSelect.add(new SelectDataBean("200", "完成"));
        mSelect.add(new SelectDataBean("500", "终止"));
        for (int i = 0; i < mSelect.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", mSelect.get(i).getName());
            map.put("key", mSelect.get(i).getKey());
            dataList.add(map);
        }


        ddvXb.setupDataList(dataList);
        ddvXb.setOnItemClickListener(new DropDownView.OnItemClickListener() {
            @Override
            public void onItemClick(Map<String, Object> map, int pos, int realPos) {
                lczt = map.get("key").toString();
                initData();
            }
        });
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
        popupBigPhotoview = getLayoutInflater().inflate(R.layout.popwindow_credit_application_three, null);
        popupBigPhotoview.findViewById(R.id.tv_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupBigPhoto.isShowing()) {
                    popupBigPhoto.dismiss();
                }

                if (ObjectUtils.isNotEmpty(getSelect())) {
                    if (!isOne()) {
                        ToastUtil.showBlackToastSucess("只能选择一条数据");
                        return;
                    }
                    if (ObjectUtils.isNotEmpty(getSelect().getId())) {
                        //请求id 身份证
                        SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getZjhm());
                        SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getJcjd());
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
                    if (!isOne()) {
                        ToastUtil.showBlackToastSucess("只能选择一条数据");
                        return;
                    }
                    //请求id 身份证
                    SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getZjhm());
                    SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getJcjd());
                    UIHelper.showXcjyProgressDialog(getActivity(), "");
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }


            }
        });
        popupBigPhotoview.findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupBigPhoto.isShowing()) {
                    popupBigPhoto.dismiss();
                }

                for (XcjyBean mMessageBean : mAdapter.getData()) {
                    if (mMessageBean.isClick()) {

                        if ("1".equals(mMessageBean.getLczt())) {
                            continue;
                        } else {
                            ToastUtil.showBlackToastSucess("请选择流程状态为待现场检验的数据");
                            return;
                        }
                    }


                }

                if (ObjectUtils.isNotEmpty(getSelect())) {
                    if ("1".equals(getSelect().getLczt())) {

                        UIHelper.showOkClearDialog(getActivity(), "确定要退回吗");
                    } else {
                        ToastUtil.showBlackToastSucess("请选择流程状态为待现场检验的数据");
                    }
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }


            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("确定要退回吗")) {
            StringBuilder sb = new StringBuilder();
            for (XcjyBean mMessageBean : mAdapter.getData()) {
                if (mMessageBean.isClick()) {
                    sb.append(",");
                    sb.append(mMessageBean.getId());

                    if (mMessageBean.getLczt().equals("1")) {

                        continue;
                    } else {
                        ToastUtil.showBlackToastSucess("请选择环节为待现场检验的数据");
                        return;

                    }
                }

            }
            String selectSb = sb.toString().substring(1);
            DhApi.cancelRl(selectSb, new BaseCallback<BaseResponse<Void>>() {
                @Override
                public void onSucc(BaseResponse<Void> result) {
                    ToastUtil.showBlackToastSucess("取消认领成功");
                    initData();
                }

                @Override
                public void onFail(ApiException apiError) {
                    super.onFail(apiError);
                    initData();
                }
            }, this);
        }
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


    private XcjyBean getSelect() {
        for (XcjyBean data : mAdapter.getData()) {
            if (data.isClick()) {
                return data;
            }
        }
        return null;
    }


    private boolean isOne() {
        StringBuilder sb = new StringBuilder();
        for (XcjyBean mMessageBean : mAdapter.getData()) {
            if (mMessageBean.isClick()) {
                sb.append(",");
                sb.append(mMessageBean.getId());
            }

        }
        if (ObjectUtils.isEmpty(sb.toString())) {
            return false;
        }
        String selectSb = sb.toString().substring(1);
        LogUtils.e(selectSb);
        if (ObjectUtils.isEmpty(selectSb.split(",")) || selectSb.split(",").length > 1) {

            return false;
        } else {
            return true;
        }

    }

    @SingleClick
    @OnClick({R.id.sb_find, R.id.tv_more, R.id.sb_start_test
            , R.id.iv_clear, R.id.tv_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_start_test:
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    StringBuilder sb = new StringBuilder();
                    for (XcjyBean mMessageBean : mAdapter.getData()) {
                        if (mMessageBean.isClick()) {
                            sb.append(",");
                            sb.append(mMessageBean.getId());

                            if (mMessageBean.getLczt().equals("1")) {

                                continue;
                            } else {
                                ToastUtil.showBlackToastSucess("请选择环节为待现场检验的数据");
                                return;

                            }
                        }

                    }
                    String selectSb = sb.toString().substring(1);
                    LogUtils.e(selectSb);
                    if (ObjectUtils.isEmpty(selectSb.split(",")) || selectSb.split(",").length > 1) {
                        ToastUtil.showBlackToastSucess("只能选择一条任务检验");
                        return;
                    }
                    if (ObjectUtils.isNotEmpty(getSelect().getZjhm())) {
                        //请求id 身份证
                        SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getZjhm());
                        SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getJcjd());
                        UIHelper.showDcCommonActivity("现场检验", getActivity(), "申请");
                    } else {
                        ToastUtil.showBlackToastSucess("证件号码为空");
                    }
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
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
        DhApi.getXcjyList(page, "12", mNameOrId, lczt, new BaseCallback<BaseResponse<XcjyListBean>>() {
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

        if (page == pages + 1) {
//            page = pages;
            mRefreshLayout.endLoadingMore();
            ToastUtil.showBlackToastSucess("没有更多的数据了");
            return false;
        }
        String mNameOrId = mEtName.getText().toString().trim();

        DhApi.getXcjyList(page + 1, "12", mNameOrId, lczt, new BaseCallback<BaseResponse<XcjyListBean>>() {
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
