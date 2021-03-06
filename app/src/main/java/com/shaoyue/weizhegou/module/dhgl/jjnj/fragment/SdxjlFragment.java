package com.shaoyue.weizhegou.module.dhgl.jjnj.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.dhgl.SdxjlBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.module.credit.adapter.diaocha.SdXjlAdapter;
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


public class SdxjlFragment extends BaseAppFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

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

    private SdXjlAdapter mAdapter;


    public static SdxjlFragment newInstance() {

        Bundle args = new Bundle();
        SdxjlFragment fragment = new SdxjlFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sd_xjl;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

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

        mAdapter = new SdXjlAdapter();
        mRvApplication.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvApplication.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<SdxjlBean.RecordsBean> mlist = adapter.getData();
                for (SdxjlBean.RecordsBean data : mlist) {
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
            SdxjlBean.RecordsBean  item = null;
            for (SdxjlBean.RecordsBean data : mAdapter.getData()) {
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

    private SdxjlBean.RecordsBean  getSelect() {
        for (SdxjlBean.RecordsBean  data : mAdapter.getData()) {
            if (data.isClick()) {
                return data;
            }
        }
        return null;
    }


    @OnClick({


    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.sb_xzhy:
//                if (ObjectUtils.isNotEmpty(getSelect())) {
//                    if (ObjectUtils.isEmpty(getSelect().getId())) {   //请求id 身份证
//
//                    } else {
////                        SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getId());
////                        SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getQyzjhm());
//                        UIHelper.showStartDg(getActivity(),new GoAllSelect(false,"选择行业", ObjectToMapUtils.str2Map(getSelect())));
//                    }
//                } else {
//                    ToastUtil.showBlackToastSucess("暂未选取数据");
//                }
//
//                break;
//            case R.id.sb_tianbao:
//                if (ObjectUtils.isNotEmpty(getSelect())) {
//                    if (ObjectUtils.isEmpty(getSelect().getId())) {   //请求id 身份证
//
//                    } else {
//                        if(ObjectUtils.isNotEmpty(getSelect().getSshy())&&ObjectUtils.isNotEmpty(getSelect().getZzm())) {
//                            //请求id 身份证 模型
//                            SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getId());
//                            SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getQyzjhm());
//                            UIHelper.showDgCommonActivity("对公", getActivity(), "对公检查", getSelect().getSshy(),getSelect().getDgdk());
//                        }else {
//                            SPUtils.getInstance().put(UserMgr.SP_ID_CARD, getSelect().getId());
//                            SPUtils.getInstance().put(UserMgr.SP_APPLY_ID, getSelect().getQyzjhm());
//                            UIHelper.showStartDg(getActivity(),new GoAllSelect(true,"选择行业", ObjectToMapUtils.str2Map(getSelect())));
//                        }
//                    }
//                } else {
//                    ToastUtil.showBlackToastSucess("暂未选取数据");
//                }
//
//                break;
//
//
//            case R.id.sb_find:
//                initData();
//                break;
//
//            //清理输入框
//            case R.id.iv_clear:
//                mEtName.setText("");
//                break;

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

        DhApi.getSdxjlList(page, "12", mNameOrId, new BaseCallback<BaseResponse<SdxjlBean>>() {
            @Override
            public void onSucc(BaseResponse<SdxjlBean> result) {
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


        DhApi.getSdxjlList(page + 1, "12", mNameOrId, new BaseCallback<BaseResponse<SdxjlBean>>() {
            @Override
            public void onSucc(BaseResponse<SdxjlBean> result) {
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
