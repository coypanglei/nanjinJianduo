package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.InquiryDetailsBean;
import com.shaoyue.weizhegou.entity.dhgl.XcjyZxcxBean;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryDetailsTwoAdapter;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryProgressAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CreditInquiryDetailsFragment extends BaseAppFragment {

    @BindView(R.id.rv_credit)
    RecyclerView mRvCredit;
    @BindView(R.id.rv_credit_two)
    RecyclerView mRvCreditTwo;
    @BindView(R.id.rv_credit_three)
    RecyclerView mRvCreditThree;
    @BindView(R.id.rv_credit_four)
    RecyclerView mRvCreditFour;
    @BindView(R.id.sb_find)
    StateButton sbFind;
    @BindView(R.id.nested_sc)
    NestedScrollView nestedSc;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    Unbinder unbinder;


    private InquiryDetailsTwoAdapter mAdapter;
    private InquiryDetailsTwoAdapter mAdapterTwo;

    private InquiryDetailsTwoAdapter mAdapterThree;

    private InquiryProgressAdapter mAdapterFour;
    private List<InquiryDetailsBean> mList = new ArrayList<>();
    private List<InquiryDetailsBean> mListTwo = new ArrayList<>();
    private List<InquiryDetailsBean> mListThree = new ArrayList<>();

    private List<XcjyZxcxBean.CxjlAndroidBean> mlistFour = new ArrayList<>();


    public static CreditInquiryDetailsFragment newInstance() {

        Bundle args = new Bundle();

        CreditInquiryDetailsFragment fragment = new CreditInquiryDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_credit_inquiry;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);

        mAdapter = new InquiryDetailsTwoAdapter();
        mAdapterTwo = new InquiryDetailsTwoAdapter();
        mAdapterThree = new InquiryDetailsTwoAdapter();
        mAdapterFour = new InquiryProgressAdapter();


        mRvCredit.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCredit.setAdapter(mAdapter);
        mRvCredit.setNestedScrollingEnabled(false);//禁止滑动
        mRvCreditTwo.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCreditTwo.setAdapter(mAdapterTwo);
        mRvCreditTwo.setNestedScrollingEnabled(false);//禁止滑动
        mRvCreditThree.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCreditThree.setAdapter(mAdapterThree);
        mRvCreditThree.setNestedScrollingEnabled(false);//禁止滑动
        mRvCreditFour.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvCreditFour.setAdapter(mAdapterFour);
        mRvCreditFour.setNestedScrollingEnabled(false);//禁止滑动


    }

    @Override
    public void onResume() {
        super.onResume();
        xcjyZxcx();
    }

    /**
     * 现场检验征信查询
     */
    private void xcjyZxcx() {
        DhApi.xcjyZxcx(new BaseCallback<BaseResponse<XcjyZxcxBean>>() {
            @Override
            public void onSucc(BaseResponse<XcjyZxcxBean> result) {
                mList.clear();
                mListTwo.clear();
                mListThree.clear();
                mlistFour.clear();
                mList.add(new InquiryDetailsBean("我行余额(元)", result.data.getWhye(), ""));
                mList.add(new InquiryDetailsBean("他行余额(元)", result.data.getThye(), ""));
                mList.add(new InquiryDetailsBean("我行不良贷款笔数", result.data.getWhbldkbs(), ""));
                mList.add(new InquiryDetailsBean("我行不良贷款余额", result.data.getWhbldkye(), ""));
                mList.add(new InquiryDetailsBean("逾期次数", result.data.getYqcs(), ""));
                mList.add(new InquiryDetailsBean("逾期金额", result.data.getYqje(), ""));
                mList.add(new InquiryDetailsBean("他行贷款机构数", result.data.getThdkjgs(), ""));
                mList.add(new InquiryDetailsBean("", "", ""));

                mListTwo.add(new InquiryDetailsBean("信用卡张数", result.data.getXykzs(), ""));
                mListTwo.add(new InquiryDetailsBean("信用卡累计逾期期数", result.data.getXykljyqqs(), ""));
                mListTwo.add(new InquiryDetailsBean("信用卡累计逾期期数", result.data.getXykzgyqqs(), ""));
                mListTwo.add(new InquiryDetailsBean("信用卡授信额度(元)", result.data.getXyksxed(), ""));
                mListTwo.add(new InquiryDetailsBean("信用卡已使用额度", result.data.getXykysyed(), ""));
                mListTwo.add(new InquiryDetailsBean("信用卡最高逾期金额(元)", result.data.getXykzgyqje(), ""));
                mListTwo.add(new InquiryDetailsBean("信用卡是否不良", result.data.getXyksfbl(), ""));
                mListTwo.add(new InquiryDetailsBean("", "", ""));

                mListThree.add(new InquiryDetailsBean("担保金额(元)", result.data.getDbje(), ""));
                mListThree.add(new InquiryDetailsBean("担保笔数", result.data.getDbbs(), ""));
                mListThree.add(new InquiryDetailsBean("担保余额", result.data.getDbye(), ""));
                mListThree.add(new InquiryDetailsBean("担保是否不良", result.data.getDbsfbl(), ""));
                if (ObjectUtils.isNotEmpty(result.data.getCxjl_Android())) {
                    mlistFour.addAll(result.data.getCxjl_Android());
                }
                mAdapter.setNewData(mList);
                mAdapterTwo.setNewData(mListTwo);
                mAdapterThree.setNewData(mListThree);
                mAdapterFour.setNewData(mlistFour);
                if (ObjectUtils.isNotEmpty(result.data.getDescription())) {
                    tvDescription.setText(result.data.getDescription());
                    nestedSc.setVisibility(View.GONE);
                    if (result.data.getDescription().contains("没有")) {
                        sbFind.setVisibility(View.VISIBLE);
                    } else {
                        sbFind.setVisibility(View.GONE);
                    }
                } else {
                    nestedSc.setVisibility(View.VISIBLE);
                }
            }
        }, this);
    }



    @OnClick(R.id.sb_find)
    public void onViewClicked() {
      DhApi.xcjyZxcxAdd(new BaseCallback<BaseResponse<Void>>() {
          @Override
          public void onSucc(BaseResponse<Void> result) {
              xcjyZxcx();
          }
      },this);

    }
}
