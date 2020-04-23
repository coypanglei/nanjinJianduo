package com.shaoyue.weizhegou.module.credit.fragment.diaocha;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.InquiryDetailsBean;
import com.shaoyue.weizhegou.entity.cedit.MyHangBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryDetailsAdapter;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.MyDataIconAdapter;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.DropDownView;
import com.shaoyue.weizhegou.widget.YStarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyDataDcFragment extends BaseFragment {
    @BindView(R.id.rv_credit)
    RecyclerView mRvCredit;
    @BindView(R.id.rv_credit_two)
    RecyclerView mRvCreditTwo;
    @BindView(R.id.rv_credit_three)
    RecyclerView mRvCreditThree;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.rv_credit_four)
    RecyclerView rvCreditFour;
    @BindView(R.id.rv_credit_five)
    RecyclerView rvCreditFive;

    @BindView(R.id.starBar)
    YStarView starBar;
    @BindView(R.id.tv_tg)
    TextView tvTg;
    @BindView(R.id.tv_wtg)
    TextView tvWtg;
    @BindView(R.id.et_buliang)
    EditText etBuliang;
    @BindView(R.id.sb_edit)
    StateButton sbEdit;

    Unbinder unbinder;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.ll_peiou_cp)
    LinearLayout llPeiouCp;
    @BindView(R.id.ll_peiou_zj)
    LinearLayout llPeiouZj;

    @BindView(R.id.rv_credit_six)
    RecyclerView rvCreditSix;
    @BindView(R.id.et_cs)
    EditText etCs;
    @BindView(R.id.ll_cs)
    RelativeLayout llCs;
    @BindView(R.id.ddv_xb)
    DropDownView ddvXb;


    private boolean click = true;


    private InquiryDetailsAdapter mAdapter;
    private InquiryDetailsAdapter mAdapterTwo;
    private MyDataIconAdapter mAdapterThree;
    private List<InquiryDetailsBean> mList = new ArrayList<>();
    private List<InquiryDetailsBean> mListTwo = new ArrayList<>();

    private List<String> mlistThree = new ArrayList<>();
    private List<String> mlistFour = new ArrayList<>();
    private List<String> mlistFive = new ArrayList<>();
    private List<String> mlistSix = new ArrayList<>();
    private String xtshjl;
    private MyDataIconAdapter myDataIconAdapter;

    private MyDataIconAdapter myDataIconAdapterTwo;
    private MyDataIconAdapter myDataIconAdapterThree;


    public static MyDataDcFragment newInstance() {

        Bundle args = new Bundle();

        MyDataDcFragment fragment = new MyDataDcFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_dc_data;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        initData();
        if ("查看详情".equals(SPUtils.getInstance().getString("status")) || "调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
            sbEdit.setVisibility(View.GONE);
        }


        starBar.setStarCount(5);//星星总数
        starBar.setHalf(true);
        starBar.setChange(false);//设置星星是否可以点击和滑动改变
        mAdapter = new InquiryDetailsAdapter("申请人", "配偶");
        mAdapterTwo = new InquiryDetailsAdapter("申请人", "配偶");
        mAdapterThree = new MyDataIconAdapter();

        mRvCredit.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCredit.setAdapter(mAdapter);
        mRvCredit.setNestedScrollingEnabled(false);//禁止滑动
        mRvCreditTwo.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCreditTwo.setAdapter(mAdapterTwo);
        mRvCreditTwo.setNestedScrollingEnabled(false);//禁止滑动


        myDataIconAdapter = new MyDataIconAdapter();
        myDataIconAdapterTwo = new MyDataIconAdapter();
        myDataIconAdapterThree = new MyDataIconAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCreditFour.setLayoutManager(linearLayoutManager);
        rvCreditFour.setAdapter(myDataIconAdapter);
        rvCreditFour.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManagerTwo = new LinearLayoutManager(getActivity());
        linearLayoutManagerTwo.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvCreditThree.setLayoutManager(linearLayoutManagerTwo);
        mRvCreditThree.setAdapter(mAdapterThree);
        mRvCreditThree.setNestedScrollingEnabled(false);//禁止滑动
        LinearLayoutManager linearLayoutManagerThree = new LinearLayoutManager(getActivity());
        linearLayoutManagerThree.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCreditFive.setLayoutManager(linearLayoutManagerThree);
        rvCreditFive.setAdapter(myDataIconAdapterTwo);
        rvCreditFive.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManagerFour = new LinearLayoutManager(getActivity());
        linearLayoutManagerFour.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCreditSix.setLayoutManager(linearLayoutManagerFour);
        rvCreditSix.setAdapter(myDataIconAdapterThree);
        rvCreditSix.setNestedScrollingEnabled(false);//禁止滑动
        CeditApi.getListAll("系统数据", null, "" +
                "合作关系", new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                if (ObjectUtils.isNotEmpty(result.data.getRecords())) {
                    BasicInformationBean.RecordsBean item = result.data.getRecords().get(0);
                    List<Map<String, Object>> dataList = new ArrayList<>();
                    if (ObjectUtils.isNotEmpty(item.getOptionlist())) {
                        for (int i = 0; i < item.getOptionlist().size(); i++) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("name", item.getOptionlist().get(i).getName());
                            map.put("key", item.getOptionlist().get(i).getName());
                            dataList.add(map);
                        }
                        ddvXb.setupDataList(dataList);


                    }

                }


            }

            @Override
            public void onFail(ApiException apiError) {


            }
        }, this);
    }

    private boolean mIsVisibleToUser = false;
    @Override
    public void onResume() {
        super.onResume();
        if (mIsVisibleToUser) {//在这里进行一下判断
            initData();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        mIsVisibleToUser = isVisibleToUser;//被调用时记录下状态
        if (isVisibleToUser) {
            initData();
        }
    }

    private MyHangBean.RecordsBean myHangBean;

    /**
     * 刷新数据
     */
    private void initData() {
        Map<String, String> map = new HashMap<>();
        CeditApi.lookInfo(map, new BaseCallback<BaseResponse<MyHangBean>>() {
            @Override
            public void onSucc(BaseResponse<MyHangBean> result) {
                llAll.setVisibility(View.VISIBLE);

                mList.clear();
                mListTwo.clear();
                mlistThree.clear();
                mlistFour.clear();
                mlistFive.clear();
                if (ObjectUtils.isNotEmpty(result.data)) {
                    if (null != result.data.getRecords()) {
                        if (result.data.getRecords().size() > 0) {
                            myHangBean = result.data.getRecords().get(0);

                            MyHangBean.RecordsBean myHangPeiOu = new MyHangBean.RecordsBean();
                            xtshjl = myHangBean.getXtshjl();
                            //我行评级
                            starBar.setRating(myHangBean.getWhpj() / 2);//设置星星亮的颗数
                            etBuliang.setText(myHangBean.getWhblyycs());
                            etCs.setText(myHangBean.getCs());
                            ddvXb.setSelectName(myHangBean.getHzgx());
//                            if (ObjectUtils.isNotEmpty(myHangBean.getId())) {

                            
//                                click = false;
//                            } else {
//                                click = true;
//                            }
//                            if (!click) {
//                                etBuliang.setEnabled(false);
//                                sbEdit.setVisibility(View.INVISIBLE);
//                            } else {
//                                etBuliang.setEnabled(true);
//                                sbEdit.setVisibility(View.VISIBLE);
//                            }

                            if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
                                sbEdit.setVisibility(View.GONE);
                            }
                            //提示信息
                            if (ObjectUtils.isNotEmpty(myHangBean.getDescription())) {
                                tvError.setVisibility(View.VISIBLE);
                                llCs.setVisibility(View.VISIBLE);
                                tvError.setText(myHangBean.getDescription());
                            }else {
                                tvError.setVisibility(View.GONE);
                            }
                            //通过未通过
                            if ("未通过".equals(myHangBean.getXtshjl())) {
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
                            if (result.data.getRecords().size() > 1) {
                                myHangPeiOu = result.data.getRecords().get(1);
                                llPeiouCp.setVisibility(View.VISIBLE);
                                llPeiouZj.setVisibility(View.VISIBLE);

                            }
                            mList.add(new InquiryDetailsBean("原授信额度(万元)", myHangBean.getYsxje() + "", myHangPeiOu.getYsxje() + ""));
                            mList.add(new InquiryDetailsBean("用信余额(万元)", myHangBean.getYxye() + "", myHangPeiOu.getYxye() + ""));
                            mList.add(new InquiryDetailsBean("授信次数", myHangBean.getSxcs() + "", myHangPeiOu.getSxcs() + ""));
                            mList.add(new InquiryDetailsBean("不良笔数", myHangBean.getBlcs() + "", myHangPeiOu.getBlcs() + ""));
                            mList.add(new InquiryDetailsBean("表内不良余额(万元)", myHangBean.getBnbldk() + "", myHangPeiOu.getBnbldk() + ""));
                            mList.add(new InquiryDetailsBean("最后一笔信用日期", myHangBean.getZhybyxrq(), myHangPeiOu.getZhybyxrq()));
                            mList.add(new InquiryDetailsBean("最后一笔本息结清时间", myHangBean.getZhybdkjqsj(), myHangPeiOu.getZhybdkjqsj()));
                            mList.add(new InquiryDetailsBean("五级分类", myHangBean.getSqwjfl(), myHangPeiOu.getSqwjfl()));
                            mList.add(new InquiryDetailsBean("欠款欠息次数", myHangBean.getQkqxcs() + "", myHangPeiOu.getQkqxcs() + ""));
                            mList.add(new InquiryDetailsBean("担保笔数", myHangBean.getDbbs(), myHangPeiOu.getDbbs()));
                            mList.add(new InquiryDetailsBean("", "", ""));
                            mList.add(new InquiryDetailsBean("", "", ""));


                            mListTwo.add(new InquiryDetailsBean("存款时点余额(万元)", myHangBean.getCksdye() + "", myHangPeiOu.getCksdye() + ""));
                            mListTwo.add(new InquiryDetailsBean("近一年存款日均(万元)", myHangBean.getJynckrj() + "", myHangPeiOu.getJynckrj() + ""));
                            mListTwo.add(new InquiryDetailsBean("活期存款年日均(万元)", myHangBean.getHqcknrj() + "", myHangPeiOu.getHqcknrj() + ""));
                            mListTwo.add(new InquiryDetailsBean("定期存款年日均(万元)", myHangBean.getDqcknrj() + "", myHangPeiOu.getDqcknrj() + ""));
                            mListTwo.add(new InquiryDetailsBean("理财(万元)", myHangBean.getLc() + "", myHangPeiOu.getLc() + ""));
                            mListTwo.add(new InquiryDetailsBean("", "", ""));
                            mListTwo.add(new InquiryDetailsBean("", "", ""));
                            mListTwo.add(new InquiryDetailsBean("", "", ""));


                            List<String> mSelectList = new ArrayList<>();
                            List<String> unmSelectList = new ArrayList<>();
                            if ("1".equals(myHangBean.getWx())) {
                                mSelectList.add("wx1.png");
                            } else {
                                unmSelectList.add("wx2.png");
                            }
                            if ("1".equals(myHangPeiOu.getMt())) {
                                mSelectList.add("mt1.png");
                            } else {
                                unmSelectList.add("mt2.png");
                            }

                            if ("1".equals(myHangBean.getJdzf())) {
                                mSelectList.add("jd1.png");
                            } else {
                                unmSelectList.add("jd2.png");
                            }


                            if ("1".equals(myHangBean.getZfb())) {
                                mSelectList.add("zfb1.png");
                            } else {
                                unmSelectList.add("zfb2.png");
                            }


                            if ("1".equals(myHangBean.getYl())) {
                                mSelectList.add("yl1.png");
                            } else {
                                unmSelectList.add("yl2.png");
                            }


                            if ("1".equals(myHangBean.getYsf())) {
                                mSelectList.add("ysf1.png");
                            } else {
                                unmSelectList.add("ysf2.png");
                            }

                            if ("1".equals(myHangBean.getBdqb())) {
                                mSelectList.add("bdqb1.png");
                            } else {
                                unmSelectList.add("bdqb2.png");
                            }

                            if ("1".equals(myHangBean.getCft())) {
                                mSelectList.add("cft1.png");
                            } else {
                                unmSelectList.add("cft2.png");
                            }


                            if ("1".equals(myHangBean.getSjyh())) {
                                mSelectList.add("sjyh1.png");
                            } else {
                                unmSelectList.add("sjyh2.png");
                            }

                            mlistFour.addAll(mSelectList);
                            mlistFour.addAll(unmSelectList);
                            mSelectList.clear();
                            unmSelectList.clear();
                            myDataIconAdapter.setNewData(mlistFour);
                            myDataIconAdapter.notifyDataSetChanged();
                            if ("1".equals(myHangBean.getSf())) {
                                mSelectList.add("sf1.png");
                            } else {
                                unmSelectList.add("sf2.png");
                            }
//                            if ("1".equals(myHangBean.getRqf())) {
//                                mSelectList.add("rq1.png");
//                            } else {
//                                unmSelectList.add("rq2.png");
//                            }

                            if ("1".equals(myHangBean.getDf())) {
                                mSelectList.add("df1.png");
                            } else {
                                unmSelectList.add("df2.png");
                            }
                            mlistThree.addAll(mSelectList);
                            mlistThree.addAll(unmSelectList);
                            mSelectList.clear();
                            unmSelectList.clear();
                            mAdapterThree.setNewData(mlistThree);
                            mAdapterThree.notifyDataSetChanged();


                            if ("1".equals(myHangPeiOu.getWx())) {
                                mSelectList.add("wx1.png");
                            } else {
                                unmSelectList.add("wx2.png");
                            }
                            if ("1".equals(myHangPeiOu.getMt())) {
                                mSelectList.add("mt1.png");
                            } else {
                                unmSelectList.add("mt2.png");
                            }
                            if ("1".equals(myHangPeiOu.getJdzf())) {
                                mSelectList.add("jd1.png");
                            } else {
                                unmSelectList.add("jd2.png");
                            }


                            if ("1".equals(myHangPeiOu.getZfb())) {
                                mSelectList.add("zfb1.png");
                            } else {
                                unmSelectList.add("zfb2.png");
                            }


                            if ("1".equals(myHangPeiOu.getYl())) {
                                mSelectList.add("yl1.png");
                            } else {
                                unmSelectList.add("yl2.png");
                            }


                            if ("1".equals(myHangPeiOu.getYsf())) {
                                mSelectList.add("ysf1.png");
                            } else {
                                unmSelectList.add("ysf2.png");
                            }

                            if ("1".equals(myHangPeiOu.getBdqb())) {
                                mSelectList.add("bdqb1.png");
                            } else {
                                unmSelectList.add("bdqb2.png");
                            }

                            if ("1".equals(myHangPeiOu.getCft())) {
                                mSelectList.add("cft1.png");
                            } else {
                                unmSelectList.add("cft2.png");
                            }


                            if ("1".equals(myHangPeiOu.getSjyh())) {
                                mSelectList.add("sjyh1.png");
                            } else {
                                unmSelectList.add("sjyh2.png");
                            }

                            mlistFive.addAll(mSelectList);
                            mlistFive.addAll(unmSelectList);
                            mSelectList.clear();
                            unmSelectList.clear();

                            myDataIconAdapterTwo.setNewData(mlistFive);
                            myDataIconAdapterTwo.notifyDataSetChanged();

                            if ("1".equals(myHangPeiOu.getSf())) {
                                mSelectList.add("sf1.png");
                            } else {
                                unmSelectList.add("sf2.png");
                            }

//                            if ("1".equals(myHangPeiOu.getRqf())) {
//                                mSelectList.add("rq1.png");
//                            } else {
//                                unmSelectList.add("rq2.png");
//                            }

                            if ("1".equals(myHangPeiOu.getDf())) {
                                mSelectList.add("df1.png");
                            } else {
                                unmSelectList.add("df2.png");
                            }
                            mlistSix.addAll(mSelectList);
                            mlistSix.addAll(unmSelectList);
                            mSelectList.clear();
                            unmSelectList.clear();

                            myDataIconAdapterThree.setNewData(mlistSix);
                            myDataIconAdapterThree.notifyDataSetChanged();

                        }


                        mAdapter.setNewData(mList);
                        mAdapterTwo.setNewData(mListTwo);

                    }

                }
            }


        }, this);
    }


    @OnClick({R.id.tv_tg, R.id.tv_wtg, R.id.sb_edit})
    public void onViewClicked(View view) {

        //不能点击 id有时不能点击
        if (click) {
            Drawable drawable = getResources().getDrawable(R.drawable.icon_left_star_black);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            Drawable drawable2 = getResources().getDrawable(R.drawable.icon_left_blue);
            drawable2.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            switch (view.getId()) {
                case R.id.tv_tg:
                    xtshjl = "通过";
                    if (!"通过".equals(myHangBean.getYxtshjl())) {
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
                    if ("通过".equals(myHangBean.getYxtshjl())) {
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
                case R.id.sb_edit:
                    //保存
                    //description: "系统审核结论为通过，人工修改为未通过"
                    //id: null
                    //sxid: "120"
                    //whblyycs: "asdad"
                    //xtshjl: "未通过"
                    String whblyycs = etBuliang.getText().toString().trim();
                    String description = tvError.getText().toString();
                    Map<String, String> map = new HashMap<>();
                    if (ObjectUtils.isEmpty(ddvXb.getSelectKey())) {
                        ToastUtil.showBlackToastSucess("请填写合作关系");
                        return;
                    } else {
                        map.put("hzgx", ddvXb.getSelectKey());
                    }

                    map.put("xtshjl", xtshjl);
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

                    map.put("whblyycs", whblyycs);


                    CeditApi.editMyData(map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            ToastUtil.showBlackToastSucess("保存成功");
                            initData();
                        }
                    }, this);
                    break;
            }

        }
    }


}
