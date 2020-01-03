package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.shaoyue.weizhegou.entity.dhgl.XcjyZxcxBean;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryDetailsTwoAdapter;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryProgressAdapter;
import com.shaoyue.weizhegou.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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

    private InquiryDetailsTwoAdapter mAdapter;
    private InquiryDetailsTwoAdapter mAdapterTwo;
    private String xtshjl;
    private InquiryDetailsTwoAdapter mAdapterThree;

    private InquiryProgressAdapter mAdapterFour;
    private List<InquiryDetailsBean> mList = new ArrayList<>();
    private List<InquiryDetailsBean> mListTwo = new ArrayList<>();
    private List<InquiryDetailsBean> mListThree = new ArrayList<>();

    private List<XcjyZxcxBean.CxjlAndroidBean> mlistFour = new ArrayList<>();

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
        return R.layout.fragment_credit_inquiry;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        myHangBean = new ZxcxListBean.RecordsBean();
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
        DhApi.sxjyZxcx(new BaseCallback<BaseResponse<ZxcxListBean>>() {
            @Override
            public void onSucc(BaseResponse<ZxcxListBean> result) {


                ZxcxListBean.RecordsBean bean = new ZxcxListBean.RecordsBean();
                if ("配偶征信数据".equals(title) && result.data.getTotal() == 2) {
                    myHangBean = result.data.getRecords().get(1);
                    llWhpj.setVisibility(View.GONE);
                }

                if ("申请人征信数据".equals(title)) {
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
                    }
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

                if ("查看详情".equals(SPUtils.getInstance().getString("status"))){
                    sbSave.setVisibility(View.GONE);
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

    @OnClick({R.id.sb_find, R.id.tv_tg, R.id.tv_wtg, R.id.sb_save})
    public void onViewClicked(View view) {
        Drawable drawable = getResources().getDrawable(R.drawable.icon_left_star_black);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        Drawable drawable2 = getResources().getDrawable(R.drawable.icon_left_blue);
        drawable2.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        switch (view.getId()) {
            case R.id.sb_find:
                DhApi.addsqZxcx(new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        EventBus.getDefault().post(new RefreshBean());
                    }
                }, this);
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
                LogUtils.e(myHangBean.getYzxshjl());
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
