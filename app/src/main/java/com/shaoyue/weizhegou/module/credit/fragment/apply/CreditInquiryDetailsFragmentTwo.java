package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.ZxcxListBean;
import com.shaoyue.weizhegou.entity.cedit.InquiryDetailsBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryDetailsAdapter;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryDetailsTwoAdapter;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.zxdbInfoAdapter;
import com.shaoyue.weizhegou.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CreditInquiryDetailsFragmentTwo extends BaseAppFragment {

    @BindView(R.id.rv_credit)
    RecyclerView mRvCredit;
    @BindView(R.id.rv_credit_two)
    RecyclerView mRvCreditTwo;
    @BindView(R.id.rv_credit_three)
    RecyclerView mRvCreditThree;
    @BindView(R.id.rv_credit_four)
    RecyclerView mRvCreditFour;
    @BindView(R.id.rv_credit_five)
    RecyclerView mRvCreditFive;
    @BindView(R.id.sb_find)
    StateButton sbFind;
    @BindView(R.id.nested_sc)
    NestedScrollView nestedSc;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    Unbinder unbinder;
    @BindView(R.id.ll_whpj)
    LinearLayout llWhpj;
    @BindView(R.id.et_cs)
    EditText etCs;
    @BindView(R.id.ll_cs)
    RelativeLayout llCs;
    @BindView(R.id.tv_tg)
    TextView tvTg;
    @BindView(R.id.tv_wtg)
    TextView tvWtg;
    @BindView(R.id.et_buliang)
    EditText etBuliang;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.sb_save)
    StateButton sbSave;
    @BindView(R.id.tv_yqbz)
    TextView tvYqbz;
    @BindView(R.id.tv_sfsxzxr)
    TextView tvSfsxzxr;
    @BindView(R.id.tv_xykyq)
    TextView tvXykyq;
    @BindView(R.id.tv_yqqs)
    TextView tvYqqs;


    private InquiryDetailsTwoAdapter mAdapter;
    private InquiryDetailsAdapter mAdapterTwo;
    private String xtshjl;
    private InquiryDetailsTwoAdapter mAdapterThree;

    private zxdbInfoAdapter mAdapterFour;
    private InquiryDetailsAdapter mAdapterFive;
    private List<InquiryDetailsBean> mList = new ArrayList<>();
    private List<InquiryDetailsBean> mListTwo = new ArrayList<>();
    private List<InquiryDetailsBean> mListThree = new ArrayList<>();
    private List<InquiryDetailsBean> mListFive = new ArrayList<>();
    private List<ZxcxListBean.RecordsBean.DbqkAndroidBean> mlistFour = new ArrayList<>();

    private String title;
    private ZxcxListBean.RecordsBean myHangBean;


    public static CreditInquiryDetailsFragmentTwo newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        CreditInquiryDetailsFragmentTwo fragment = new CreditInquiryDetailsFragmentTwo();
        fragment.setArguments(args);
        return fragment;
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
    protected int getLayoutId() {
        return R.layout.fragment_credit_inquiry_new;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        myHangBean = new ZxcxListBean.RecordsBean();
        mAdapter = new InquiryDetailsTwoAdapter();
        mAdapterTwo = new InquiryDetailsAdapter("笔数", "余额(元)");
        mAdapterThree = new InquiryDetailsTwoAdapter();
        mAdapterFour = new zxdbInfoAdapter();
        mAdapterFive = new InquiryDetailsAdapter("笔数", "余额(元)");

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
        startProgressDialog(true);
        DhApi.sxjyZxcx(new BaseCallback<BaseResponse<ZxcxListBean>>() {
            @Override
            public void onSucc(BaseResponse<ZxcxListBean> result) {
                nestedSc.setVisibility(View.VISIBLE);
                stopProgressDialog();
                ZxcxListBean.RecordsBean bean = new ZxcxListBean.RecordsBean();
                if ("配偶征信数据".equals(title) && result.data.getTotal() == 2) {
                    myHangBean = result.data.getRecords().get(1);
                    llWhpj.setVisibility(View.GONE);
                }

                if ("申请人征信数据".equals(title)) {
                    if (ObjectUtils.isNotEmpty(result.data.getRecords())) {
                        if (ObjectUtils.isNotEmpty(result.data.getRecords().get(0))) {
                            myHangBean = result.data.getRecords().get(0);

                            sbSave.setVisibility(View.VISIBLE);
                            llWhpj.setVisibility(View.VISIBLE);
                            etBuliang.setText(myHangBean.getBlyyms());
                            etCs.setText(myHangBean.getCs());
                            //提示信息
                            if (ObjectUtils.isNotEmpty(myHangBean.getDescription())) {
                                tvError.setVisibility(View.VISIBLE);
                                llCs.setVisibility(View.VISIBLE);
                                tvError.setText(myHangBean.getDescription());
                            } else {
                                tvError.setVisibility(View.GONE);
                            }
                            xtshjl = myHangBean.getZxshjl();
                            //通过未通过
                            if ("未通过".equals(myHangBean.getZxshjl())) {
                                Drawable drawable = getResources().getDrawable(R.drawable.icon_left_star_black);
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

                                tvTg.setCompoundDrawables(drawable, null, null, null);
                                tvTg.setTextColor(getResources().getColor(R.color.color_b9b8b8));
                                Drawable drawable2 = getResources().getDrawable(R.drawable.icon_left_blue);
                                drawable2.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

                                tvWtg.setCompoundDrawables(drawable2, null, null, null);
                                tvWtg.setTextColor(getResources().getColor(R.color.color_49a0ed));
                            } else {
                                Drawable drawable = getResources().getDrawable(R.drawable.icon_left_star_black);
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

                                tvWtg.setCompoundDrawables(drawable, null, null, null);
                                tvWtg.setTextColor(getResources().getColor(R.color.color_b9b8b8));
                                Drawable drawable2 = getResources().getDrawable(R.drawable.icon_left_blue);
                                drawable2.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

                                tvTg.setCompoundDrawables(drawable2, null, null, null);
                                tvTg.setTextColor(getResources().getColor(R.color.color_49a0ed));
                            }
                        }
                    }
                }


                mList.clear();
                mListTwo.clear();
                mListThree.clear();
                mlistFour.clear();
                mListFive.clear();
                mList.add(new InquiryDetailsBean("授信机构数", myHangBean.getSxjgs(), ""));
                mList.add(new InquiryDetailsBean("授信贷款总额", myHangBean.getDksxze(), ""));
                mList.add(new InquiryDetailsBean("贷款用信总额(元)", myHangBean.getDkyxze(), ""));
                mList.add(new InquiryDetailsBean("担保总额(元)", myHangBean.getDbze(), ""));

                mListTwo.add(new InquiryDetailsBean("总贷款情况", myHangBean.getZdkbs(), myHangBean.getZdkye()));
                mListTwo.add(new InquiryDetailsBean("住房贷款情况", myHangBean.getZfdkbs(), myHangBean.getZfdkye()));
                mListTwo.add(new InquiryDetailsBean("经营贷款情况", myHangBean.getJydkbs(), myHangBean.getJydkye()));
                mListTwo.add(new InquiryDetailsBean("农户贷款情况", myHangBean.getNhdkbs(), myHangBean.getNhdkye()));
                mListTwo.add(new InquiryDetailsBean("助学贷款情况", myHangBean.getZxdkbs(), myHangBean.getZxdkye()));
                mListTwo.add(new InquiryDetailsBean("汽车贷款情况", myHangBean.getQcdkbs(), myHangBean.getQcdkye()));
                mListTwo.add(new InquiryDetailsBean("非银行贷款情况", myHangBean.getFyhdkzbs(), myHangBean.getFyhdkye()));
                for (int i = 0; i < mListTwo.size() % 4; i++) {
                    mListTwo.add(new InquiryDetailsBean("", "", ""));
                }

                mListThree.add(new InquiryDetailsBean("机构数", myHangBean.getXykjgs(), ""));
                mListThree.add(new InquiryDetailsBean("最高授信额度(元)", myHangBean.getZgsxed(), ""));
                mListThree.add(new InquiryDetailsBean("当前已用额度(元)", myHangBean.getDqyyed(), ""));
                mListThree.add(new InquiryDetailsBean("账户数", myHangBean.getXykzhs(), ""));

                mListFive.add(new InquiryDetailsBean("欠税信息", myHangBean.getQsxxbs(), myHangBean.getQsxxje()));
                mListFive.add(new InquiryDetailsBean("行政处罚", myHangBean.getXzcfbs(), myHangBean.getXzcfje()));
                mListFive.add(new InquiryDetailsBean("民事判决信息", myHangBean.getMspjxxbs(), myHangBean.getMspjxxje()));
                mListFive.add(new InquiryDetailsBean("涉诉情况", myHangBean.getSsqkbs(), myHangBean.getSsqkje()));
                //是否逾期
                if (ObjectUtils.isNotEmpty(myHangBean.getYqbs())) {
                    tvYqbz.setText(myHangBean.getYqbs());
                }
                //是否失信人
                if (ObjectUtils.isNotEmpty(myHangBean.getSfwsxzxr())) {
                    tvSfsxzxr.setText(myHangBean.getSfwsxzxr());
                }
//String str1=String.format("欢迎来到西说，<font color=\"#550000\">%s", "李先生");
                String yqqs = "";
                String xykyqqs = "";
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);   //获取年份
                LogUtils.e(myHangBean.getLxyqzdqs_Android());
                LogUtils.e(myHangBean.getLxyqzdqs_Android().size());
                try {
                    for (int i = 0; i < myHangBean.getLxyqzdqs_Android().size(); i++) {
                        yqqs = yqqs + "<font color=\"#23a7f0\">" + myHangBean.getLxyqzdqs_Android().get(i) + "</font>&nbsp;&nbsp;(" + (year - i) + ")&nbsp;&nbsp;&nbsp;";
                    }
                    for (int i = 0; i < myHangBean.getXyklxyq_Android().size(); i++) {
                        xykyqqs = xykyqqs + "<font color=\"#23a7f0\">" + myHangBean.getXyklxyq_Android().get(i) + "</font>&nbsp;&nbsp;(" + (year - i) + ")&nbsp;&nbsp;&nbsp;";
                    }

                } catch (Exception e) {

                }

                tvYqqs.setText(Html.fromHtml(yqqs));
                tvXykyq.setText(Html.fromHtml(xykyqqs));

                if (ObjectUtils.isNotEmpty(myHangBean.getDbqk_Android())) {
                    mlistFour.addAll(myHangBean.getDbqk_Android());
                }
                mAdapter.setNewData(mList);
                mAdapterTwo.setNewData(mListTwo);
                mAdapterThree.setNewData(mListThree);
                mAdapterFour.setNewData(mlistFour);
                mAdapterFive.setNewData(mListFive);
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

                if ("查看详情".equals(SPUtils.getInstance().getString("status")) || "调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
                    sbSave.setVisibility(View.GONE);
                    sbFind.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFail(ApiException apiError) {
                stopProgressDialog();
                errorTv = apiError.getErrMsg();
                if (errorTv.contains("没有相关查询任务") || errorTv.contains("查询失败")) {
                    sbFind.setVisibility(View.VISIBLE);
                    tvDescription.setText(errorTv);
                    nestedSc.setVisibility(View.GONE);

                } else {
                    tvDescription.setText(errorTv);
                    nestedSc.setVisibility(View.GONE);
                    sbFind.setVisibility(View.GONE);
                }
                if ("查看详情".equals(SPUtils.getInstance().getString("status")) || "调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
                    sbSave.setVisibility(View.GONE);
                    sbFind.setVisibility(View.GONE);
                }
            }
        }, this);
    }

    private String errorTv;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshBean event) {
        xcjyZxcx();
    }

    @OnClick({R.id.sb_find, R.id.tv_tg, R.id.tv_wtg, R.id.sb_save, R.id.sb_resh})
    public void onViewClicked(View view) {
        Drawable drawable = getResources().getDrawable(R.drawable.icon_left_star_black);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        Drawable drawable2 = getResources().getDrawable(R.drawable.icon_left_blue);
        drawable2.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        switch (view.getId()) {


            case R.id.sb_resh:
                xcjyZxcx();
                break;
            case R.id.sb_find:

                if (errorTv.contains("没有相关查询任务")) {
                    DhApi.addsqZxcx(new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            EventBus.getDefault().post(new RefreshBean());
                        }
                    }, this);
                } else if (errorTv.contains("查询失败")) {
                    DhApi.cssqZxcx(new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            EventBus.getDefault().post(new RefreshBean());
                        }
                    }, this);
                }
                break;
            case R.id.tv_tg:
                xtshjl = "通过";
                if (!"通过".equals(myHangBean.getYzxshjl())) {
                    tvError.setVisibility(View.VISIBLE);
                    llCs.setVisibility(View.VISIBLE);
                    tvError.setText("系统未通过，人工干预已通过!!");
                } else {
                    tvError.setVisibility(View.GONE);
                    llCs.setVisibility(View.GONE);
                }
                tvWtg.setCompoundDrawables(drawable, null, null, null);
                tvWtg.setTextColor(getResources().getColor(R.color.color_b9b8b8));
                tvTg.setCompoundDrawables(drawable2, null, null, null);
                tvTg.setTextColor(getResources().getColor(R.color.color_49a0ed));
                break;
            case R.id.tv_wtg:
                xtshjl = "未通过";

                if ("通过".equals(myHangBean.getYzxshjl())) {
                    tvError.setVisibility(View.VISIBLE);
                    llCs.setVisibility(View.VISIBLE);
                    tvError.setText("系统已通过，人工干预未通过!!");
                } else {
                    tvError.setVisibility(View.GONE);
                    llCs.setVisibility(View.GONE);
                }
                tvTg.setCompoundDrawables(drawable, null, null, null);
                tvTg.setTextColor(getResources().getColor(R.color.color_b9b8b8));
                tvWtg.setCompoundDrawables(drawable2, null, null, null);
                tvWtg.setTextColor(getResources().getColor(R.color.color_49a0ed));
                break;
            case R.id.sb_save:
                //blyyms: "似的发射点"
                //cs: ""
                //description: ""
                //sxid: "d0fd82770ea5e6ca1f61bd6c5b2f9ca4"
                //zxshjl: "未通过"
                String whblyycs = etBuliang.getText().toString().trim();
                String description = tvError.getText().toString();
                Map<String, String> map = new HashMap<>();
                map.put("zxshjl", xtshjl);
                if (tvError.getVisibility() == View.VISIBLE) {
                    map.put("description", description);
                } else {
                    map.put("description", "");
                }
                if (llCs.getVisibility() == View.VISIBLE) {
                    String cs = etCs.getText().toString().trim();
                    if (ObjectUtils.isNotEmpty(cs)) {
                        map.put("cs", etCs.getText().toString().trim());
                    } else {
                        ToastUtil.showBlackToastSucess("请填写陈述");
                        return;
                    }

                }

                map.put("blyyms", whblyycs);

                CeditApi.saveZxcx(map, new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        ToastUtil.showBlackToastSucess("保存成功");
                        xcjyZxcx();
                    }
                }, this);
                break;
        }


    }


}
