package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
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
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.ZxcxListBean;
import com.shaoyue.weizhegou.entity.cedit.InquiryDetailsBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.entity.dhgl.XcjyZxcxBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryDetailsTwoAdapter;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryProgressAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CreditInquiryDetailsFragmentThree extends BaseAppFragment {

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

    private String title;

    private String id;

    public static CreditInquiryDetailsFragmentThree newInstance(String title, String id) {

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("id", id);
        CreditInquiryDetailsFragmentThree fragment = new CreditInquiryDetailsFragmentThree();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            title = getArguments().getString("title");
            id = getArguments().getString("id");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
    private ZxcxListBean.RecordsBean myHangBean;
    /**
     * 现场检验征信查询
     */
    private void xcjyZxcx() {
        DhApi.sxjyZxcx(id,new BaseCallback<BaseResponse<ZxcxListBean>>() {
            @Override
            public void onSucc(BaseResponse<ZxcxListBean> result) {

                ZxcxListBean.RecordsBean bean = new ZxcxListBean.RecordsBean();
                if ("配偶征信数据".equals(title) && result.data.getTotal() == 2) {
                    myHangBean = result.data.getRecords().get(1);


                }

                if ("申请人征信数据".equals(title)) {
                    if (ObjectUtils.isNotEmpty(result.data.getRecords())) {
                        if (ObjectUtils.isNotEmpty(result.data.getRecords().get(0))) {
                            myHangBean = result.data.getRecords().get(0);

                            //提示信息
                            if (ObjectUtils.isNotEmpty(myHangBean.getDescription())) {
                                tvDescription.setVisibility(View.VISIBLE);

                                tvDescription.setText(myHangBean.getDescription());
                            }

                        }
                    }
                }
                mList.clear();
                mListTwo.clear();
                mListThree.clear();
                mlistFour.clear();
//                mList.add(new InquiryDetailsBean("我行余额(元)", bean.getWhye(), ""));
//                mList.add(new InquiryDetailsBean("他行余额(元)", result.data.getThye(), ""));
//                mList.add(new InquiryDetailsBean("我行不良贷款笔数", result.data.getWhbldkbs(), ""));
//                mList.add(new InquiryDetailsBean("我行不良贷款余额", result.data.getWhbldkye(), ""));
//                mList.add(new InquiryDetailsBean("逾期次数", result.data.getYqcs(), ""));
//                mList.add(new InquiryDetailsBean("逾期金额", result.data.getYqje(), ""));
//                mList.add(new InquiryDetailsBean("他行贷款机构数", result.data.getThdkjgs(), ""));
//                mList.add(new InquiryDetailsBean("", "", ""));
//
//                mListTwo.add(new InquiryDetailsBean("信用卡张数", result.data.getXykzs(), ""));
//                mListTwo.add(new InquiryDetailsBean("信用卡累计逾期期数", result.data.getXykljyqqs(), ""));
//                mListTwo.add(new InquiryDetailsBean("信用卡累计逾期期数", result.data.getXykzgyqqs(), ""));
//                mListTwo.add(new InquiryDetailsBean("信用卡授信额度(元)", result.data.getXyksxed(), ""));
//                mListTwo.add(new InquiryDetailsBean("信用卡已使用额度", result.data.getXykysyed(), ""));
//                mListTwo.add(new InquiryDetailsBean("信用卡最高逾期金额(元)", result.data.getXykzgyqje(), ""));
//                mListTwo.add(new InquiryDetailsBean("信用卡是否不良", result.data.getXyksfbl(), ""));
//                mListTwo.add(new InquiryDetailsBean("", "", ""));
//
//                mListThree.add(new InquiryDetailsBean("担保金额(元)", result.data.getDbje(), ""));
//                mListThree.add(new InquiryDetailsBean("担保笔数", result.data.getDbbs(), ""));
//                mListThree.add(new InquiryDetailsBean("担保余额", result.data.getDbye(), ""));
//                mListThree.add(new InquiryDetailsBean("担保是否不良", result.data.getDbsfbl(), ""));
//                if (ObjectUtils.isNotEmpty(result.data.getCxjl_Android())) {
//                    mlistFour.addAll(result.data.getCxjl_Android());
//                }
//                mAdapter.setNewData(mList);
//                mAdapterTwo.setNewData(mListTwo);
//                mAdapterThree.setNewData(mListThree);
//                mAdapterFour.setNewData(mlistFour);
                sbFind.setVisibility(View.GONE);
                tvDescription.setVisibility(View.GONE);
                if (ObjectUtils.isNotEmpty(bean.getDescription())) {
                    tvDescription.setText(bean.getDescription());
                    nestedSc.setVisibility(View.GONE);
                    if (bean.getDescription().contains("没有")) {
                        sbFind.setVisibility(View.VISIBLE);
                    } else {
                        sbFind.setVisibility(View.GONE);
                    }
                } else {
                    nestedSc.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(ApiException apiError) {
                String str = apiError.getErrMsg();
                if (str.contains("没有相关查询任务")) {
                    sbFind.setVisibility(View.VISIBLE);
                    tvDescription.setText(str);
                    nestedSc.setVisibility(View.GONE);
                } else {
                    tvDescription.setText(str);
                    nestedSc.setVisibility(View.GONE);
                    sbFind.setVisibility(View.GONE);
                }
            }
        }, this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshBean event) {
        xcjyZxcx();
    }

    @OnClick(R.id.sb_find)
    public void onViewClicked() {
        if ("查看详情".equals(SPUtils.getInstance().getString("status")) || "调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
            return;
        }
        DhApi.adddbsqZxcx(id, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                EventBus.getDefault().post(new RefreshBean());
            }
        }, this);

    }
}
