package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.HfwBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.sxdc.adapter.HfwInfoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HfwInformationFragment extends BaseAppFragment {


    HfwInfoAdapter mAdapter;
    @BindView(R.id.sb_find)
    StateButton sbFind;
    @BindView(R.id.sb_resh)
    StateButton sbResh;
    @BindView(R.id.rv_all)
    RecyclerView rvAll;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    Unbinder unbinder;
    @BindView(R.id.ns_all)
    NestedScrollView nsAll;


    public static HfwInformationFragment newInstance() {
        Bundle args = new Bundle();
        HfwInformationFragment fragment = new HfwInformationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        if ("查看详情".equals(SPUtils.getInstance().getString("status")) || "调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
            sbFind.setVisibility(View.GONE);
        }


        mAdapter = new HfwInfoAdapter();
        mAdapter.setActivity(getActivity());
        rvAll.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAll.setAdapter(mAdapter);


        startProgressDialog(true);
        getListById();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_credit_hfw_new;
    }


    /**
     * 获取界面数据byid
     */
    private void getListById() {
        startProgressDialog(true);
        CeditApi.getHfWInfo(new BaseCallback<BaseResponse<HfwBean>>() {
            @Override
            public void onSucc(BaseResponse<HfwBean> result) {

                tvDescription.setText("");
                boolean contail = false;
                if (ObjectUtils.isNotEmpty(result.data)) {
                    List<HfwBean.ListBeanX.汇法网信息Bean> list = new ArrayList<>();
                    if (ObjectUtils.isNotEmpty(result.data.getList())) {

                        if (ObjectUtils.isNotEmpty(result.data.getList().get(0).get汇法网信息())) {
                            if (result.data.getList().size() > 0) {
                                HfwBean.ListBeanX.汇法网信息Bean bean = new HfwBean.ListBeanX.汇法网信息Bean();
                                bean.setTitle("本人信息");
                                list.add(bean);
                                list.addAll(result.data.getList().get(0).get汇法网信息());

                                contail = list.size() == 1 ? false : true;

                            }

                        }

                        if (result.data.getList().size() > 1 && ObjectUtils.isNotEmpty(result.data.getList().get(1).get汇法网信息())) {
                            HfwBean.ListBeanX.汇法网信息Bean bean = new HfwBean.ListBeanX.汇法网信息Bean();
                            bean.setTitle("配偶信息");
                            list.add(bean);
                            list.addAll(result.data.getList().get(1).get汇法网信息());
                            contail = (!contail && list.size() == 2) ? false : true;
                        }

                    }
                    if (!contail) {
                        tvDescription.setText("汇法网未查询到客户不良记录");
                        nsAll.setVisibility(View.GONE);
                    }
                    mAdapter.setNewData(list);


                }
                sbFind.setVisibility(View.GONE);
                nsAll.setVisibility(View.VISIBLE);
                stopProgressDialog();
            }

            @Override
            public void onFail(ApiException apiError) {
                tvDescription.setText(apiError.getErrMsg());
                nsAll.setVisibility(View.GONE);
                sbFind.setVisibility(View.VISIBLE);
                stopProgressDialog();
            }
        }, this);
    }


    @OnClick({R.id.sb_find, R.id.sb_resh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //查询
            case R.id.sb_find:
                CeditApi.cxHfWInfo(new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        getListById();
                    }

                }, this);
                break;
            //刷新界面
            case R.id.sb_resh:
                getListById();
                break;
        }
    }


}
