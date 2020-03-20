package com.shaoyue.weizhegou.module.dhgl.jjnj.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.dhgl.SdInfoListBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.diaocha.sdjcAdapter;
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


public class sdjcFragment extends BaseAppFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    String title;
    @BindView(R.id.et_name)
    EditText mEtName;


    @BindView(R.id.rv_application)
    RecyclerView mRvApplication;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.sb_tianbao)
    StateButton sbtianBao;


    private int page = 1;

    private int pages = 1;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;

    private sdjcAdapter mAdapter;


    public static sdjcFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        sdjcFragment fragment = new sdjcFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sdjc;
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
        SPUtils.getInstance()
                .put(UserMgr.SP_XT_TYPE, "贷后");
        mAdapter = new sdjcAdapter();
        mRvApplication.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvApplication.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<SdInfoListBean.RecordsBean> mlist = adapter.getData();
                for (SdInfoListBean.RecordsBean data : mlist) {
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


    private String sxmx;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("是否取消申请?")) {
            SdInfoListBean.RecordsBean item = null;
            for (SdInfoListBean.RecordsBean data : mAdapter.getData()) {
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

    private SdInfoListBean.RecordsBean getSelect() {
        for (SdInfoListBean.RecordsBean data : mAdapter.getData()) {
            if (data.isClick()) {
                return data;
            }
        }
        return null;
    }


    @OnClick({R.id.sb_find, R.id.sb_tianbao, R.id.iv_clear
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_tianbao:
                if (ObjectUtils.isNotEmpty(getSelect())) {
                    if (ObjectUtils.isEmpty(getSelect().getId())) {   //请求id 身份证

                    } else {
                        //请求id 身份证 模型
                        SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getId());
                        SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getZjhm());
                        UIHelper.showDcCommonActivity("首贷", getActivity(), "首贷检查");
                    }
                } else {
                    ToastUtil.showBlackToastSucess("暂未选取数据");
                }

                break;


            case R.id.sb_find:
                initData();
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

        DhApi.getSDJCSpList(page, "12", mNameOrId, new BaseCallback<BaseResponse<SdInfoListBean>>() {
            @Override
            public void onSucc(BaseResponse<SdInfoListBean> result) {
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


        DhApi.getSDJCSpList(page + 1, "12", mNameOrId, new BaseCallback<BaseResponse<SdInfoListBean>>() {
            @Override
            public void onSucc(BaseResponse<SdInfoListBean> result) {
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
