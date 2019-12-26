package com.shaoyue.weizhegou.module.credit.fragment.diaocha;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.dhgl.XjlBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.dhgl.adapter.XjlAdapter;
import com.shaoyue.weizhegou.widget.lineTU.LineView;

import java.util.ArrayList;

import butterknife.BindView;

public class DcMoneyFragment extends BaseAppFragment {


    @BindView(R.id.line_view_float)
    LineView lineViewFloat;
    @BindView(R.id.rl_list)
    RecyclerView rlList;
    @BindView(R.id.rl_list_2)
    RecyclerView rlListTwo;
    XjlAdapter mAdapter;
    @BindView(R.id.tv_liuru)
    TextView tvLiuru;
    @BindView(R.id.tv_liucu)
    TextView tvLiucu;
    @BindView(R.id.tv_content)
    TextView tvContent;


    private int randomint = 12;
    XjlAdapter mAdapterTwo;

    public static DcMoneyFragment newInstance() {
        Bundle args = new Bundle();
        DcMoneyFragment fragment = new DcMoneyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_money_liu;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        initLineView(lineViewFloat);
        mAdapter = new XjlAdapter();
        mAdapterTwo = new XjlAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlList.setLayoutManager(manager);
        rlList.setAdapter(mAdapter);
        LinearLayoutManager managerTwo = new LinearLayoutManager(getActivity());
        managerTwo.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlListTwo.setLayoutManager(managerTwo);
        rlListTwo.setAdapter(mAdapterTwo);
        if ("调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
            CeditApi.getXjlInfo(new BaseCallback<BaseResponse<XjlBean>>() {
                @Override
                public void onSucc(BaseResponse<XjlBean> result) {
                    if (ObjectUtils.isNotEmpty(result.data)) {
                        ArrayList<ArrayList<Double>> dataListFs = new ArrayList<>();
                        if (ObjectUtils.isNotEmpty(result.data.get近一年现金流入()) && ObjectUtils.isNotEmpty(result.data.get近一年现金流出())) {
                            dataListFs.add(result.data.get近一年现金流入());
                            dataListFs.add(result.data.get近一年现金流出());
                            lineViewFloat.setFloatDataList(dataListFs);
                        }

                        mAdapter.setNewData(result.data.get近一年现金流入());
                        mAdapterTwo.setNewData(result.data.get近一年现金流出());
                        Double liuru = 0.00;
                        Double liucu = 0.00;
                        for (Double bean : result.data.get近一年现金流入()) {
                            liuru = liuru + bean;
                        }
                        for (Double bean : result.data.get近一年现金流出()) {
                            liucu = liucu + bean;
                        }
                        tvLiuru.setText(String.format("%.2f", liuru));
                        tvLiucu.setText(String.format("%.2f", liucu));
                        tvContent.setText(result.data.getDesc());

                    }
                }
            }, this);
        }else {
            DhApi.getXjlInfo(new BaseCallback<BaseResponse<XjlBean>>() {
                @Override
                public void onSucc(BaseResponse<XjlBean> result) {
                    if (ObjectUtils.isNotEmpty(result.data)) {
                        ArrayList<ArrayList<Double>> dataListFs = new ArrayList<>();
                        if (ObjectUtils.isNotEmpty(result.data.get近一年现金流入()) && ObjectUtils.isNotEmpty(result.data.get近一年现金流出())) {
                            dataListFs.add(result.data.get近一年现金流入());
                            dataListFs.add(result.data.get近一年现金流出());
                            lineViewFloat.setFloatDataList(dataListFs);
                        }

                        mAdapter.setNewData(result.data.get近一年现金流入());
                        mAdapterTwo.setNewData(result.data.get近一年现金流出());
                        Double liuru = 0.00;
                        Double liucu = 0.00;
                        for (Double bean : result.data.get近一年现金流入()) {
                            liuru = liuru + bean;
                        }
                        for (Double bean : result.data.get近一年现金流出()) {
                            liucu = liucu + bean;
                        }
                        tvLiuru.setText(String.format("%.2f", liuru));
                        tvLiucu.setText(String.format("%.2f", liucu));
                        tvContent.setText(result.data.getDesc());

                    }
                }
            }, this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ("调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
            CeditApi.getXjlInfo(new BaseCallback<BaseResponse<XjlBean>>() {
                @Override
                public void onSucc(BaseResponse<XjlBean> result) {
                    if (ObjectUtils.isNotEmpty(result.data)) {
                        ArrayList<ArrayList<Double>> dataListFs = new ArrayList<>();
                        if (ObjectUtils.isNotEmpty(result.data.get近一年现金流入()) && ObjectUtils.isNotEmpty(result.data.get近一年现金流出())) {
                            dataListFs.add(result.data.get近一年现金流入());
                            dataListFs.add(result.data.get近一年现金流出());
                            lineViewFloat.setFloatDataList(dataListFs);
                        }

                        mAdapter.setNewData(result.data.get近一年现金流入());
                        mAdapterTwo.setNewData(result.data.get近一年现金流出());
                        Double liuru = 0.00;
                        Double liucu = 0.00;
                        for (Double bean : result.data.get近一年现金流入()) {
                            liuru = liuru + bean;
                        }
                        for (Double bean : result.data.get近一年现金流出()) {
                            liucu = liucu + bean;
                        }
                        tvLiuru.setText(String.format("%.2f", liuru));
                        tvLiucu.setText(String.format("%.2f", liucu));
                        tvContent.setText(result.data.getDesc());

                    }
                }
            }, this);
        }else {
            DhApi.getXjlInfo(new BaseCallback<BaseResponse<XjlBean>>() {
                @Override
                public void onSucc(BaseResponse<XjlBean> result) {
                    if (ObjectUtils.isNotEmpty(result.data)) {
                        ArrayList<ArrayList<Double>> dataListFs = new ArrayList<>();
                        if (ObjectUtils.isNotEmpty(result.data.get近一年现金流入()) && ObjectUtils.isNotEmpty(result.data.get近一年现金流出())) {
                            dataListFs.add(result.data.get近一年现金流入());
                            dataListFs.add(result.data.get近一年现金流出());
                            lineViewFloat.setFloatDataList(dataListFs);
                        }

                        mAdapter.setNewData(result.data.get近一年现金流入());
                        mAdapterTwo.setNewData(result.data.get近一年现金流出());
                        Double liuru = 0.00;
                        Double liucu = 0.00;
                        for (Double bean : result.data.get近一年现金流入()) {
                            liuru = liuru + bean;
                        }
                        for (Double bean : result.data.get近一年现金流出()) {
                            liucu = liucu + bean;
                        }
                        tvLiuru.setText(String.format("%.2f", liuru));
                        tvLiucu.setText(String.format("%.2f", liucu));
                        tvContent.setText(result.data.getDesc());

                    }
                }
            }, this);
        }
    }

    private void initLineView(LineView lineView) {
        ArrayList<String> test = new ArrayList<String>();
        for (int i = 0; i < randomint; i++) {
            test.add(String.valueOf(i + 1) + "月");
        }
        lineView.setBottomTextList(test);
        lineView.setColorArray(new int[]{
                Color.parseColor("#23bf86"), Color.parseColor("#cc6bcc"),
        });
        lineView.setDrawDotLine(true);
        lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);
    }


}
