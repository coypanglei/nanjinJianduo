package com.shaoyue.weizhegou.module.dhgl.fragment;

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
import com.shaoyue.weizhegou.entity.cedit.InquiryDetailsBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.entity.dhgl.DgZxcxBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryDetailsTwoAdapter;
import com.shaoyue.weizhegou.module.dhgl.adapter.DgdbInfoAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CreditInquiryDetailsDgFragment extends BaseAppFragment {

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


    @BindView(R.id.rv_credit_five)
    RecyclerView mRvCreditFive;
    Unbinder unbinder;
    @BindView(R.id.rv_credit_six)
    RecyclerView rvCreditSix;


    private InquiryDetailsTwoAdapter mAdapter;
    private InquiryDetailsTwoAdapter mAdapterTwo;

    private InquiryDetailsTwoAdapter mAdapterThree;

    //    private zxdbInfoAdapter mAdapterFour;
    private InquiryDetailsTwoAdapter mAdapterFour;
    private InquiryDetailsTwoAdapter mAdapterFive;
    private List<InquiryDetailsBean> mList = new ArrayList<>();
    private List<InquiryDetailsBean> mListTwo = new ArrayList<>();
    private List<InquiryDetailsBean> mListThree = new ArrayList<>();
    private List<InquiryDetailsBean> mListFive = new ArrayList<>();
    private List<InquiryDetailsBean> mlistFour = new ArrayList<>();

    private List<DgZxcxBean.RecordsBean.JkBean> mlistSix = new ArrayList<>();

    private DgdbInfoAdapter mAdapterSix;

    private String title;

    private String id;

    private String tvError;

    public static CreditInquiryDetailsDgFragment newInstance(String title, String id) {

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("id", id);
        CreditInquiryDetailsDgFragment fragment = new CreditInquiryDetailsDgFragment();
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
        return R.layout.fragment_credit_inquiry_dg;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);

        myHangBean = new DgZxcxBean.RecordsBean();
        mAdapter = new InquiryDetailsTwoAdapter();
        mAdapterTwo = new InquiryDetailsTwoAdapter();
        mAdapterThree = new InquiryDetailsTwoAdapter();
        mAdapterFour = new InquiryDetailsTwoAdapter();
        mAdapterFive = new InquiryDetailsTwoAdapter();
        mAdapterSix = new DgdbInfoAdapter();

        mRvCredit.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCredit.setAdapter(mAdapter);
        mRvCredit.setNestedScrollingEnabled(false);//禁止滑动
        mRvCreditTwo.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCreditTwo.setAdapter(mAdapterTwo);
        mRvCreditTwo.setNestedScrollingEnabled(false);//禁止滑动
        mRvCreditThree.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCreditThree.setAdapter(mAdapterThree);
        mRvCreditThree.setNestedScrollingEnabled(false);//禁止滑动

        mRvCreditFive.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCreditFive.setAdapter(mAdapterFive);
        mRvCreditFive.setNestedScrollingEnabled(false);//禁止滑动
        mRvCreditFour.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCreditFour.setAdapter(mAdapterFour);
        mRvCreditFour.setNestedScrollingEnabled(false);//禁止滑动

        rvCreditSix.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCreditSix.setAdapter(mAdapterSix);
        rvCreditSix.setNestedScrollingEnabled(false);//禁止滑动
        xcjyZxcx();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private DgZxcxBean.RecordsBean myHangBean;

    /**
     * 现场检验征信查询
     */
    private void xcjyZxcx() {
        if ("查看详情".equals(SPUtils.getInstance().getString("status")) || "调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
            sbFind.setVisibility(
                    View.GONE
            );
        }

        startProgressDialog(true);
        DhApi.dgZxcx(id, new BaseCallback<BaseResponse<DgZxcxBean>>() {
            @Override
            public void onSucc(BaseResponse<DgZxcxBean> result) {

                nestedSc.setVisibility(View.VISIBLE);
                stopProgressDialog();

                if (ObjectUtils.isNotEmpty(result.data.getRecords()) && result.data.getRecords().size() > 0) {

                    myHangBean = result.data.getRecords().get(0);
                }
                mList.clear();
                mListTwo.clear();
                mListThree.clear();
                mlistFour.clear();
                mListFive.clear();
                mlistSix.clear();

                mList.add(new InquiryDetailsBean("首次有贷交易的年份", myHangBean.getSnf(), ""));
                mList.add(new InquiryDetailsBean("发生信贷交易机构数", myHangBean.getJgs(), ""));
                mList.add(new InquiryDetailsBean("当前有未结清信贷交易的机构数", myHangBean.getWjqjgs(), ""));
                mList.add(new InquiryDetailsBean("首次有相关还贷责任的年份", myHangBean.getShknf(), ""));
//
                mListTwo.add(new InquiryDetailsBean("余额", myHangBean.getJdjy().getYe(), ""));
                mListTwo.add(new InquiryDetailsBean("被追偿余额", myHangBean.getJdjy().getBpche(), ""));
                mListTwo.add(new InquiryDetailsBean("关注类余额", myHangBean.getJdjy().getGzlye(), ""));
                mListTwo.add(new InquiryDetailsBean("不良类余额", myHangBean.getJdjy().getBlye(), ""));
                mListThree.add(new InquiryDetailsBean("余额", myHangBean.getDbjy().getYe(), ""));
                mListThree.add(new InquiryDetailsBean("被追偿余额", myHangBean.getDbjy().getGzlye(), ""));
                mListThree.add(new InquiryDetailsBean("不良类余额", myHangBean.getDbjy().getBlye(), ""));
                mListThree.add(new InquiryDetailsBean("", ""));

                mlistFour.add(new InquiryDetailsBean("总额", myHangBean.getFxhxy().getZe(), ""));
                mlistFour.add(new InquiryDetailsBean("已用额度", myHangBean.getFxhxy().getYyed(), ""));
                mlistFour.add(new InquiryDetailsBean("剩余可用额度", myHangBean.getFxhxy().getSyed(), ""));
                mlistFour.add(new InquiryDetailsBean("", "", ""));
//
                mListFive.add(new InquiryDetailsBean("总额", myHangBean.getXhxy().getZe(), ""));
                mListFive.add(new InquiryDetailsBean("已用额度", myHangBean.getXhxy().getYyed(), ""));
                mListFive.add(new InquiryDetailsBean("剩余可用额度", myHangBean.getXhxy().getSyed(), ""));
                mListFive.add(new InquiryDetailsBean("", "", ""));
//                //是否逾期
//                if (ObjectUtils.isNotEmpty(myHangBean.getYqbs())) {
//                    tvYqbz.setText(myHangBean.getYqbs());
//                }
//                //是否失信人
//                if (ObjectUtils.isNotEmpty(myHangBean.getSfwsxzxr())) {
//                    tvSfsxzxr.setText(myHangBean.getSfwsxzxr());
//                }
////String str1=String.format("欢迎来到西说，<font color=\"#550000\">%s", "李先生");
//                String yqqs = "";
//                String xykyqqs = "";
//                Calendar c = Calendar.getInstance();
//                int year = c.get(Calendar.YEAR);   //获取年份
//                if (ObjectUtils.isNotEmpty(myHangBean.getXyklxyq_Android())) {
//                    for (int i = 0; i < myHangBean.getLxyqzdqs_Android().size(); i++) {
//                        yqqs = yqqs + "<font color=\"#23a7f0\">" + myHangBean.getLxyqzdqs_Android().get(i) + "</font>&nbsp;&nbsp;(" + (year - i) + ")&nbsp;&nbsp;&nbsp;";
//                    }
//                }
//                if (ObjectUtils.isNotEmpty(myHangBean.getXyklxyq_Android())) {
//                    for (int i = 0; i < myHangBean.getXyklxyq_Android().size(); i++) {
//                        xykyqqs = xykyqqs + "<font color=\"#23a7f0\">" + myHangBean.getXyklxyq_Android().get(i) + "</font>&nbsp;&nbsp;(" + (year - i) + ")&nbsp;&nbsp;&nbsp;";
//                    }
//                }
//
//                tvYqqs.setText(Html.fromHtml(yqqs));
//                tvXykyq.setText(Html.fromHtml(xykyqqs));
//
                if (ObjectUtils.isNotEmpty(myHangBean.getJk())) {
                    mlistSix.addAll(myHangBean.getJk());
                }
                mAdapter.setNewData(mList);
                mAdapterTwo.setNewData(mListTwo);
                mAdapterThree.setNewData(mListThree);
                mAdapterFour.setNewData(mlistFour);
                mAdapterFive.setNewData(mListFive);
                mAdapterSix.setNewData(mlistSix);
                sbFind.setVisibility(View.GONE);
                tvDescription.setVisibility(View.GONE);

                nestedSc.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFail(ApiException apiError) {
                stopProgressDialog();
                tvError = apiError.getErrMsg();

                if (tvError.contains("没有相关查询任务") || tvError.contains("查询失败")) {
                    sbFind.setVisibility(View.VISIBLE);
                    tvDescription.setText(tvError);
                    nestedSc.setVisibility(View.GONE);
                } else {
                    tvDescription.setText(tvError);
                    nestedSc.setVisibility(View.GONE);
                    sbFind.setVisibility(View.GONE);
                }

                if ("查看详情".equals(SPUtils.getInstance().getString("status")) || "调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {

                    sbFind.setVisibility(View.GONE);
                }
            }
        }, this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshBean event) {
        xcjyZxcx();
    }


    @OnClick({R.id.sb_find, R.id.sb_resh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_find:
                if ("查看详情".equals(SPUtils.getInstance().getString("status")) || "调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
                    return;
                }
                if (tvError.contains("没有相关查询任务")) {
                    DhApi.addgdZxcx(id, "企业", new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            EventBus.getDefault().post(new RefreshBean());
                        }
                    }, this);
                } else if (tvError.contains("查询失败")) {
                    DhApi.csgdZxcx(id, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            EventBus.getDefault().post(new RefreshBean());
                        }
                    }, this);
                }
                break;
            case R.id.sb_resh:
                xcjyZxcx();
                break;
        }
    }


}
