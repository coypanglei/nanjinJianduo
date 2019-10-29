package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.InquiryDetailsBean;
import com.shaoyue.weizhegou.entity.dhgl.XcjyZxcxBean;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryDetailsAdapter;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryDetailsTwoAdapter;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryProgressAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CreditInquiryDetailsFragment extends BaseAppFragment {

    @BindView(R.id.rv_credit)
    RecyclerView mRvCredit;
    @BindView(R.id.rv_credit_two)
    RecyclerView mRvCreditTwo;
    @BindView(R.id.rv_credit_three)
    RecyclerView mRvCreditThree;
    @BindView(R.id.rv_credit_four)
    RecyclerView mRvCreditFour;


    private InquiryDetailsAdapter mAdapter;
    private InquiryDetailsTwoAdapter mAdapterTwo;

    private InquiryDetailsTwoAdapter mAdapterThree;

    private InquiryProgressAdapter mAdapterFour;
    private List<InquiryDetailsBean> mList = new ArrayList<>();
    private List<InquiryDetailsBean> mListTwo = new ArrayList<>();
    private List<InquiryDetailsBean> mListThree = new ArrayList<>();

    private List<String> mlistFour = new ArrayList<>();


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
        mList.add(new InquiryDetailsBean("我行余额(万元)", "223,87", "223,87"));
        mList.add(new InquiryDetailsBean("他行余额(万元)", "223,87", "223,87"));
        mList.add(new InquiryDetailsBean("逾期金额(万元)", "223,87", "223,87"));
        mList.add(new InquiryDetailsBean("逾期区间", "1", "2"));
        mList.add(new InquiryDetailsBean("不良余额(万元)", "正常", "正常"));
        mList.add(new InquiryDetailsBean("五级分类", "1", "2"));
        mList.add(new InquiryDetailsBean("个人信用记录评价", "良好", "良好"));
        mList.add(new InquiryDetailsBean("", "", ""));

        mListTwo.add(new InquiryDetailsBean("张数", "12", ""));
        mListTwo.add(new InquiryDetailsBean("授信额度(万元)", "12.00", ""));
        mListTwo.add(new InquiryDetailsBean("已用金额(万元)", "12.00", ""));
        mListTwo.add(new InquiryDetailsBean("累计逾期次数", "12.00", ""));
        mListTwo.add(new InquiryDetailsBean("最高逾期次数", "12.00", ""));
        mListTwo.add(new InquiryDetailsBean("当前逾期次数", "12.00", ""));
        mListTwo.add(new InquiryDetailsBean("最高逾期金额(万元)", "12.00", ""));
        mListTwo.add(new InquiryDetailsBean("", "", ""));

        mListThree.add(new InquiryDetailsBean("担保金额汇总(万元)", "12", ""));
        mListThree.add(new InquiryDetailsBean("担保是否逾期", "12", ""));
        mListThree.add(new InquiryDetailsBean("五级分类", "12", ""));
        mListThree.add(new InquiryDetailsBean("", "", ""));
        mlistFour.add("1");
        mlistFour.add("2");
        mlistFour.add("2");
        mAdapter = new InquiryDetailsAdapter("经营性", "非经营性");
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

        mAdapter.setNewData(mList);
        mAdapterTwo.setNewData(mListTwo);
        mAdapterThree.setNewData(mListThree);
        mAdapterFour.setNewData(mlistFour);
        xcjyZxcx();
    }

    /**
     * 现场检验征信查询
     */
    private void xcjyZxcx() {
       DhApi.xcjyZxcx(new BaseCallback<BaseResponse<XcjyZxcxBean>>() {
           @Override
           public void onSucc(BaseResponse<XcjyZxcxBean> result) {

           }
       },this);
    }

}
