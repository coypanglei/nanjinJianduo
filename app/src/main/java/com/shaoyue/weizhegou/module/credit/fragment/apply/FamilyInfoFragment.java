package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.FamilyBean;
import com.shaoyue.weizhegou.entity.cedit.FamilyListBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.FamilyInfoAdapter;
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


public class FamilyInfoFragment extends BaseAppFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    String title;

    @BindView(R.id.rv_application)
    RecyclerView mRvApplication;

    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.sb_add)
    StateButton sbAdd;
    @BindView(R.id.sb_edit)
    StateButton sbEdit;
    @BindView(R.id.sb_detel)
    StateButton sbDetel;


    private FamilyInfoAdapter mAdapter;
    private int page = 1;
    private int pages = 1;

    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;

    public static FamilyInfoFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        FamilyInfoFragment fragment = new FamilyInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_family_info;
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
    protected void initView(View rootView) {
        super.initView(rootView);
//        LogUtils.e(SPUtils.getInstance().getString("status"));

        if ("查看详情".equals(SPUtils.getInstance().getString("status")) || "调查".equals(title)) {
            sbAdd.setVisibility(View.GONE);
            sbDetel.setVisibility(View.GONE);
            sbEdit.setVisibility(View.GONE);
        }
        mEmptyText.setText("未添加信息");
        mAdapter = new FamilyInfoAdapter();
        mRvApplication.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvApplication.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<FamilyBean> mlist = adapter.getData();
                for (FamilyBean data : mlist) {
                    data.setClick(false);
                }
                mlist.get(position).setClick(true);
                adapter.setNewData(mlist);
            }
        });

        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
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


    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        if (page == pages+1) {
            mRefreshLayout.endLoadingMore();
            ToastUtil.showBlackToastSucess("没有更多的数据了");
            return false;
        }


        CeditApi.getFamilyInfo(page+1, "12", new BaseCallback<BaseResponse<FamilyListBean>>() {
            @Override
            public void onSucc(BaseResponse<FamilyListBean> result) {

                pages = result.data.getPages();

                if (mRefreshLayout == null) {
                    return;
                }
                mRefreshLayout.endLoadingMore();
                mAdapter.addData(result.data.getData());
                page++;

            }
        }, this);


        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshBean event) {
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("是否删除所选家庭信息?")) {
            FamilyBean item = null;
            for (FamilyBean data : mAdapter.getData()) {
                if (data.isClick()) {
                    item = data;
                }
            }
            if (ObjectUtils.isNotEmpty(item)) {
                CeditApi.detelFamilyInfo(item.getId(), new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        ToastUtil.showBlackToastSucess("删除家庭信息成功");
                        initData();
                    }
                }, this);
            }

        }
    }

    private FamilyBean getSelect() {
        for (FamilyBean data : mAdapter.getData()) {
            if (data.isClick()) {
                return data;
            }
        }
        return null;
    }


    private void initData() {
        page = 1;
        CeditApi.getFamilyInfo(page, "12", new BaseCallback<BaseResponse<FamilyListBean>>() {
            @Override
            public void onSucc(BaseResponse<FamilyListBean> result) {
                mAdapter.setNewData(result.data.getData());
                mAdapter.notifyDataSetChanged();

                if (result.data.getData().size() > 0) {
                    mEmptyRelative.setVisibility(View.GONE);
                    SPUtils.getInstance().put(UserMgr.SP_IS_PO, false);
                    if (result.data.getData().size() > 1) {
                        SPUtils.getInstance().put(UserMgr.SP_IS_PO, true);
                    }
                } else {


                    mEmptyRelative.setVisibility(View.VISIBLE);

                }
            }
        }, this);
    }


    @OnClick({R.id.sb_add, R.id.sb_edit, R.id.sb_detel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_add:
                UIHelper.showAddFamilyInfoDialog(getActivity(), null);
                break;
            case R.id.sb_edit:
                if (ObjectUtils.isEmpty(getSelect())) {
                    ToastUtil.showBlackToastSucess("没有选中数据");
                    return;
                }
                UIHelper.showAddFamilyInfoDialog(getActivity(), getSelect());
                break;
            case R.id.sb_detel:
                if (ObjectUtils.isEmpty(getSelect())) {
                    ToastUtil.showBlackToastSucess("请选择数据");
                    return;
                }

                UIHelper.showOkClearDialog(getActivity(), "是否删除所选家庭信息?");
                break;
        }
    }


}
