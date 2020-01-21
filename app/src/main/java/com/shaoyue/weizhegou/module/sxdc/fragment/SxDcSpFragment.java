package com.shaoyue.weizhegou.module.sxdc.fragment;

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

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.entity.cedit.SxspListBean;
import com.shaoyue.weizhegou.entity.cedit.TiJiaoBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.sxdc.adapter.SxspAdapter;
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


public class SxDcSpFragment extends BaseAppFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

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

    private int pages = 1;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;

    private String lczt;
    private SxspAdapter mAdapter;


    public static SxDcSpFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        SxDcSpFragment fragment = new SxDcSpFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sx_sp;
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

        mAdapter = new SxspAdapter();
        mRvApplication.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvApplication.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<SxspListBean.RecordsBean> mlist = adapter.getData();
                for (SxspListBean.RecordsBean data : mlist) {
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
//                        SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getZjhm());
//                        SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getJcjd());
                        UIHelper.showDcCommonActivity("数据采集", getActivity(), "查看详情");
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshBean event) {
        initData();
    }

    private SxspListBean.RecordsBean getSelect() {
        for (SxspListBean.RecordsBean data : mAdapter.getData()) {
            if (data.isClick()) {
                return data;
            }
        }
        return null;
    }


    @SingleClick
    @OnClick({R.id.sb_find, R.id.tv_more, R.id.sb_start_sp, R.id.tv_ckjd, R.id.tv_ckjy, R.id.tv_ckcj
            , R.id.iv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ckcj:
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    if (ObjectUtils.isNotEmpty(getSelect().getId())) {
                        //请求id 身份证
                        SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getSxid());
                        SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getSxsfzh());
                        UIHelper.showApplyCommonActivity(getActivity(), "查看详情");
                    }
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }
                break;

            case R.id.tv_ckjy:
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    if (ObjectUtils.isNotEmpty(getSelect().getSxmx())) {
                        if (ObjectUtils.isNotEmpty(getSelect().getId())) {
                            //请求id 身份证
                            SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getSxid());
                            SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getSxsfzh());
                            UIHelper.showDcCommonActivity("调查", getActivity(), "查看详情");
                        }
                    } else {
                        ToastUtil.showBlackToastSucess("未选择授信模型,不能查看调查");
                    }

                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }
                break;
//            case R.id.tv_ckjd:
//                if (ObjectUtils.isNotEmpty(getSelect())) {
//                    //请求id 身份证
//                    SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getZjhm());
//                    SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getJcjd());
//                    UIHelper.showXcjyProgressDialog(getActivity(), "");
//                } else {
//                    ToastUtil.showBlackToastSucess("暂未选取数据");
//                }
//                break;
            case R.id.sb_find:
                initData();
                break;
            case R.id.sb_start_sp:
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getSxid());
                    SPUtils.getInstance().put(UserMgr.SP_DC_TASKID, getSelect().getTaskid());
                    SPUtils.getInstance().put(UserMgr.SP_DC_INSTID, getSelect().getInstid());
                    if (ObjectUtils.isNotEmpty(getSelect().getTaskid())) {
                        CeditApi.putDanbaoInfo(getSelect().getTaskid(), getSelect().getInstid(), new BaseCallback<BaseResponse<TiJiaoBean>>() {
                            @Override
                            public void onSucc(BaseResponse<TiJiaoBean> result) {
                                if("分发岗".equals(getSelect().getDqhj())){
                                    UIHelper.showSxSpDialog(getActivity(), result.data, true);
                                }else {
                                    UIHelper.showSxSpDialog(getActivity(), result.data, false);
                                }

                            }

                            @Override
                            public void onFail(ApiException apiError) {
                                super.onFail(apiError);

                            }
                        }, this);


                    }
                } else {
                    ToastUtil.showBlackToastSucess("请选择需要审批的任务");
                }

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
        DhApi.getSxSpList(page, "12", mNameOrId, lczt, new BaseCallback<BaseResponse<SxspListBean>>() {
            @Override
            public void onSucc(BaseResponse<SxspListBean> result) {
                pages = result.data.getPages();
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
        if (page == pages + 1) {
            mRefreshLayout.endLoadingMore();
            ToastUtil.showBlackToastSucess("没有更多的数据了");
            return false;
        }
        String mNameOrId = mEtName.getText().toString().trim();

        DhApi.getSxSpList(page + 1, "12", mNameOrId, lczt, new BaseCallback<BaseResponse<SxspListBean>>() {
            @Override
            public void onSucc(final BaseResponse<SxspListBean> result) {

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
