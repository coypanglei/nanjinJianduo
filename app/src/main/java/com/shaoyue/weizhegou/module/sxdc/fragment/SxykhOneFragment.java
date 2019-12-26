package com.shaoyue.weizhegou.module.sxdc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
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
import com.shaoyue.weizhegou.entity.cedit.GoAllSelect;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.entity.cedit.SxykhListBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.module.sxdc.adapter.SxykhAdapter;
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


public class SxykhOneFragment extends BaseAppFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.rv_application)
    RecyclerView mRvApplication;

    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    @BindView(R.id.empty_text)
    TextView mEmptyText;

    @BindView(R.id.ll_visable)
    LinearLayout llVisable;
    @BindView(R.id.rl_all)
    RelativeLayout rlAll;
    @BindView(R.id.tv_cgbl)
    TextView tvCgbl;


    private int page = 1;
    private int pages = 1;

    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;

    private SxykhAdapter mAdapter;

    private String title;

    public static SxykhOneFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        SxykhOneFragment fragment = new SxykhOneFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dc_sxykh;
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
        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            rlAll.setVisibility(View.GONE);
            llVisable.setVisibility(View.GONE);
        }
        if ("上游供应商".equals(title)) {
            tvCgbl.setText("采购比例");
        }
        mEmptyText.setText("未添加信息");
        mAdapter = new SxykhAdapter();
        mRvApplication.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvApplication.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<SxykhListBean.RecordsBean> mlist = adapter.getData();
                for (SxykhListBean.RecordsBean data : mlist) {
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
        if (event.getmType().equals("是否删除上游供应商信息?")) {
            SxykhListBean.RecordsBean item = null;
            for (SxykhListBean.RecordsBean data : mAdapter.getData()) {
                if (data.isClick()) {
                    item = data;
                }
            }
            String address;
            if ("上游供应商".equals(title)) {

                address = CeditApi.SYKH_DELETE;
            } else {
                address = CeditApi.XYKH_DELETE;
            }
            if (ObjectUtils.isNotEmpty(item)) {
                CeditApi.detelDanbaoInfo(address, item.getId(), new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        ToastUtil.showBlackToastSucess("删除上游供应商成功");
                        initData();
                    }
                }, this);
            }

        }
    }

    private SxykhListBean.RecordsBean getSelect() {
        for (SxykhListBean.RecordsBean data : mAdapter.getData()) {
            if (data.isClick()) {
                return data;
            }
        }
        return null;
    }


    private void initData() {
        page = 1;
        String address = CeditApi.SXYKH_SY;
        if ("上游供应商".equals(title)) {

            address = CeditApi.SXYKH_SY;
        } else {
            address = CeditApi.XYKH_SY;
        }
        CeditApi.getSykhInfo(address, page, "12", new BaseCallback<BaseResponse<SxykhListBean>>() {
            @Override
            public void onSucc(BaseResponse<SxykhListBean> result) {
                mAdapter.setNewData(result.data.getRecords());
                mAdapter.notifyDataSetChanged();
//                if(ObjectUtils.isNotEmpty(result.data.getRecords())) {
                if (result.data.getRecords().size() > 0) {
                    mEmptyRelative.setVisibility(View.GONE);
                } else {
                    mEmptyRelative.setVisibility(View.VISIBLE);

                }
//                }
            }
        }, this);
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

        if (page == pages + 1) {
            mRefreshLayout.endLoadingMore();
            ToastUtil.showBlackToastSucess("没有更多的数据了");
            return false;
        }

        String address = CeditApi.SXYKH_SY;
        if ("上游供应商".equals(title)) {

            address = CeditApi.SXYKH_SY;
        } else {
            address = CeditApi.XYKH_SY;
        }
        CeditApi.getSykhInfo(address, page + 1, "12", new BaseCallback<BaseResponse<SxykhListBean>>() {
            @Override
            public void onSucc(BaseResponse<SxykhListBean> result) {

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

    @OnClick({R.id.sb_add, R.id.sb_edit, R.id.sb_detel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_add:
                UIHelper.showAddSykhFragment(getActivity(), new GoAllSelect(true, title, getSelect()));
                break;
            case R.id.sb_edit:
                if (ObjectUtils.isEmpty(getSelect())) {
                    ToastUtil.showBlackToastSucess("没有选中数据");
                    return;
                }

                UIHelper.showAddSykhFragment(getActivity(), new GoAllSelect(false, title, getSelect()));
                break;
            case R.id.sb_detel:
                if (ObjectUtils.isEmpty(getSelect())) {
                    ToastUtil.showBlackToastSucess("请选择数据");
                    return;
                }
                UIHelper.showOkClearDialog(getActivity(), "是否删除上游供应商信息?");
                break;


        }
    }


}


