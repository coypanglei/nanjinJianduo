package com.shaoyue.weizhegou.module.credit.fragment.apply.diyao;

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
import com.shaoyue.weizhegou.entity.cedit.GoAllSelect;
import com.shaoyue.weizhegou.entity.cedit.QiYeDanBaoBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.fragment.apply.diyao.adapter.ZiRanDanBaoFourAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ObjectToMapUtils;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


public class DiyadanbaoDetailsFourFragment extends BaseAppFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.rv_application)
    RecyclerView mRvApplication;

    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.sb_add)
    StateButton sbAdd;
    @BindView(R.id.sb_edit)
    StateButton sbEdit;
    @BindView(R.id.sb_detel)
    StateButton sbDetel;
    @BindView(R.id.rl_all)
    RelativeLayout rlAll;


    private int page = 1;
    private int pages = 1;
    private ZiRanDanBaoFourAdapter mAdapter;


    public static DiyadanbaoDetailsFourFragment newInstance() {

        Bundle args = new Bundle();

        DiyadanbaoDetailsFourFragment fragment = new DiyadanbaoDetailsFourFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_diyadanbao_4;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        if ("调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {

//                sbEdit.setVisibility(View.GONE);
        }
        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            rlAll.setVisibility(View.GONE);
            sbAdd.setVisibility(View.GONE);
            sbEdit.setVisibility(View.GONE);
            sbDetel.setVisibility(View.GONE);
        }
        mEmptyText.setText("未添加信息");
        mAdapter = new ZiRanDanBaoFourAdapter();
        mRvApplication.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvApplication.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<QiYeDanBaoBean.RecordsBean> mlist = adapter.getData();
                for (QiYeDanBaoBean.RecordsBean data : mlist) {
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
        if (event.getmType().equals("是否删除企业担保分析?")) {
            QiYeDanBaoBean.RecordsBean item = null;
            for (QiYeDanBaoBean.RecordsBean data : mAdapter.getData()) {
                if (data.isClick()) {
                    item = data;
                }
            }
            if (ObjectUtils.isNotEmpty(item)) {
                CeditApi.detelDanbaoInfo(CeditApi.DANBAOREN_DETEL_QIYE, item.getId(), new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        ToastUtil.showBlackToastSucess("删除企业担保分析成功");
                        initData();
                    }
                }, this);
            }

        }
    }

    private QiYeDanBaoBean.RecordsBean getSelect() {
        for (QiYeDanBaoBean.RecordsBean data : mAdapter.getData()) {
            if (data.isClick()) {
                return data;
            }
        }
        return null;
    }


    private void initData() {
        page = 1;
        CeditApi.getQiyeInfo(page, "12", new BaseCallback<BaseResponse<QiYeDanBaoBean>>() {
            @Override
            public void onSucc(BaseResponse<QiYeDanBaoBean> result) {
                mAdapter.setNewData(result.data.getRecords());
                mAdapter.notifyDataSetChanged();
                pages = result.data.getPages();
                if (result.data.getRecords().size() > 0) {
                    mEmptyRelative.setVisibility(View.GONE);
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
                if ("调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
                    UIHelper.showDhDiyaFragment(getActivity(), new GoAllSelect(true, "授信调查-企业担保分析", new HashMap()));
                } else {
                    UIHelper.showDiyaFragment(getActivity(), new GoAllSelect(true, "企业担保分析", getSelect()));
                }
                break;
            case R.id.sb_edit:
                if (ObjectUtils.isEmpty(getSelect())) {
                    ToastUtil.showBlackToastSucess("没有选中数据");
                    return;
                }
                if ("调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
                    UIHelper.showDhDiyaFragment(getActivity(), new GoAllSelect(false, "授信调查-企业担保分析", ObjectToMapUtils.str2Map(getSelect())));
                } else {
                    UIHelper.showDiyaFragment(getActivity(), new GoAllSelect(false, "企业担保分析", getSelect()));
                }
                break;
            case R.id.sb_detel:
                if (ObjectUtils.isEmpty(getSelect())) {
                    ToastUtil.showBlackToastSucess("请选择数据");
                    return;
                }
                UIHelper.showOkClearDialog(getActivity(), "是否删除企业担保分析?");
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


    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        if (page == pages + 1) {
            mRefreshLayout.endLoadingMore();
            ToastUtil.showBlackToastSucess("没有更多的数据了");
            return false;
        }


        CeditApi.getQiyeInfo(page + 1, "12", new BaseCallback<BaseResponse<QiYeDanBaoBean>>() {
            @Override
            public void onSucc(BaseResponse<QiYeDanBaoBean> result) {

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
