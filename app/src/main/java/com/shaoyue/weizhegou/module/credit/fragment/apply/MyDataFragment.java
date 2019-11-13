package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.entity.cedit.InquiryDetailsBean;
import com.shaoyue.weizhegou.entity.cedit.MyHangBean;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryDetailsAdapter;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryDetailsTwoAdapter;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.MyDataIconAdapter;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.YStarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyDataFragment extends BaseFragment {
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
    @BindView(R.id.tv_leave_a_message)
    TextView tvLeaveAMessage;
    Unbinder unbinder;
    @BindView(R.id.tv_error)
    TextView tvError;


    private boolean click = true;


    private InquiryDetailsAdapter mAdapter;
    private InquiryDetailsAdapter mAdapterTwo;
    private InquiryDetailsTwoAdapter mAdapterThree;
    private List<InquiryDetailsBean> mList = new ArrayList<>();
    private List<InquiryDetailsBean> mListTwo = new ArrayList<>();
    private List<InquiryDetailsBean> mListThree = new ArrayList<>();

    private List<String> mlistFour = new ArrayList<>();
    private List<String> mlistFive = new ArrayList<>();

    private MyDataIconAdapter myDataIconAdapter;

    private MyDataIconAdapter myDataIconAdapterTwo;

    public static MyDataFragment newInstance() {

        Bundle args = new Bundle();

        MyDataFragment fragment = new MyDataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_data;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);

        starBar.setStarCount(5);//星星总数

        starBar.setChange(false);//设置星星是否可以点击和滑动改变
        mAdapter = new InquiryDetailsAdapter("申请人", "配偶");
        mAdapterTwo = new InquiryDetailsAdapter("申请人", "配偶");
        mAdapterThree = new InquiryDetailsTwoAdapter();

        mRvCredit.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCredit.setAdapter(mAdapter);
        mRvCredit.setNestedScrollingEnabled(false);//禁止滑动
        mRvCreditTwo.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCreditTwo.setAdapter(mAdapterTwo);
        mRvCreditTwo.setNestedScrollingEnabled(false);//禁止滑动
        mRvCreditThree.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCreditThree.setAdapter(mAdapterThree);
        mRvCreditThree.setNestedScrollingEnabled(false);//禁止滑动

        myDataIconAdapter = new MyDataIconAdapter();
        myDataIconAdapterTwo = new MyDataIconAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCreditFour.setLayoutManager(linearLayoutManager);
        rvCreditFour.setAdapter(myDataIconAdapter);
        rvCreditFour.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManagerTwo = new LinearLayoutManager(getActivity());
        linearLayoutManagerTwo.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCreditFive.setLayoutManager(linearLayoutManagerTwo);
        rvCreditFive.setAdapter(myDataIconAdapterTwo);
        rvCreditFive.setNestedScrollingEnabled(false);

    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    /**
     * 刷新数据
     */
    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("sxid", "120");
        CeditApi.lookInfo(map, new BaseCallback<BaseResponse<MyHangBean>>() {
            @Override
            public void onSucc(BaseResponse<MyHangBean> result) {
                llAll.setVisibility(View.VISIBLE);

                mList.clear();
                mListTwo.clear();
                mListThree.clear();
                mlistFour.clear();
                mlistFive.clear();
                if (ObjectUtils.isNotEmpty(result.data)) {
                    if (null != result.data.getRecords()) {
                        if (result.data.getRecords().size() > 0) {
                            MyHangBean.RecordsBean myHangBean = result.data.getRecords().get(0);
                            MyHangBean.RecordsBean myHangPeiOu = new MyHangBean.RecordsBean();

                            //我行评级
                            starBar.setRating(myHangBean.getWhpj());//设置星星亮的颗数
                            etBuliang.setText(myHangBean.getWhblyycs());
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
                                tvError.setText(myHangBean.getDescription());
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
                                myHangPeiOu = result.data.getRecords().get(0);
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
                            mList.add(new InquiryDetailsBean("", "", ""));
                            mList.add(new InquiryDetailsBean("", "", ""));
                            mList.add(new InquiryDetailsBean("", "", ""));


                            mListTwo.add(new InquiryDetailsBean("存款时点余额(万元)", myHangBean.getCksdye() + "", myHangPeiOu.getCksdye() + ""));
                            mListTwo.add(new InquiryDetailsBean("近一年存款日均(万元)", myHangBean.getJynckrj() + "", myHangPeiOu.getJynckrj() + ""));
                            mListTwo.add(new InquiryDetailsBean("活期存款年日均(万元)", myHangBean.getHqcknrj() + "", myHangPeiOu.getHqcknrj() + ""));
                            mListTwo.add(new InquiryDetailsBean("定期存款年日均(万元)", myHangBean.getDqcknrj() + "", myHangPeiOu.getDqcknrj() + ""));
//                            mListTwo.add(new InquiryDetailsBean("理财(万元)", myHangBean.getLc() + "", myHangPeiOu.getLc() + ""));
//                            mListTwo.add(new InquiryDetailsBean("", "", ""));
//                            mListTwo.add(new InquiryDetailsBean("", "", ""));
//                            mListTwo.add(new InquiryDetailsBean("", "", ""));


                            mListThree.add(new InquiryDetailsBean("月均电费(元)", myHangBean.getYjdf(), myHangPeiOu.getYjdf()));
                            mListThree.add(new InquiryDetailsBean("月均水费(元)", myHangBean.getYjsf(), myHangPeiOu.getYjsf()));
                            mListThree.add(new InquiryDetailsBean("", "", ""));
                            mListThree.add(new InquiryDetailsBean("", "", ""));

                            List<String> mSelectList = new ArrayList<>();
                            List<String> unmSelectList = new ArrayList<>();
                            if ("开通".equals(myHangBean.getWx())) {
                                mSelectList.add("wx1.png");
                            } else {
                                unmSelectList.add("wx2.png");
                            }

                            if ("开通".equals(myHangBean.getJdzf())) {
                                mSelectList.add("jd1.png");
                            } else {
                                unmSelectList.add("jd2.png");
                            }


                            if ("开通".equals(myHangBean.getZfb())) {
                                mSelectList.add("zfb1.png");
                            } else {
                                unmSelectList.add("zfb2.png");
                            }


                            if ("开通".equals(myHangBean.getYl())) {
                                mSelectList.add("yl1.png");
                            } else {
                                unmSelectList.add("yl2.png");
                            }


                            if ("开通".equals(myHangBean.getYsf())) {
                                mSelectList.add("ysf1.png");
                            } else {
                                unmSelectList.add("ysf2.png");
                            }

                            if ("开通".equals(myHangBean.getBdqb())) {
                                mSelectList.add("bdqb1.png");
                            } else {
                                unmSelectList.add("bdqb2.png");
                            }

                            if ("开通".equals(myHangBean.getCft())) {
                                mSelectList.add("cft1.png");
                            } else {
                                unmSelectList.add("cft2.png");
                            }

                            if ("开通".equals(myHangBean.getSf())) {
                                mSelectList.add("sf1.png");
                            } else {
                                unmSelectList.add("sf2.png");
                            }

                            if ("开通".equals(myHangBean.getSjyh())) {
                                mSelectList.add("sjyh1.png");
                            } else {
                                unmSelectList.add("sjyh2.png");
                            }

                            if ("开通".equals(myHangBean.getRqf())) {
                                mSelectList.add("rq1.png");
                            } else {
                                unmSelectList.add("rq2.png");
                            }

                            if ("开通".equals(myHangBean.getDf())) {
                                mSelectList.add("df1.png");
                            } else {
                                unmSelectList.add("df2.png");
                            }
                            mlistFour.addAll(mSelectList);
                            mlistFour.addAll(unmSelectList);
                            mSelectList.clear();
                            unmSelectList.clear();
                            myDataIconAdapter.setNewData(mlistFour);
                            myDataIconAdapter.notifyDataSetChanged();
                            if ("开通".equals(myHangPeiOu.getWx())) {
                                mSelectList.add("wx1.png");
                            } else {
                                unmSelectList.add("wx2.png");
                            }

                            if ("开通".equals(myHangPeiOu.getJdzf())) {
                                mSelectList.add("jd1.png");
                            } else {
                                unmSelectList.add("jd2.png");
                            }


                            if ("开通".equals(myHangPeiOu.getZfb())) {
                                mSelectList.add("zfb1.png");
                            } else {
                                unmSelectList.add("zfb2.png");
                            }


                            if ("开通".equals(myHangPeiOu.getYl())) {
                                mSelectList.add("yl1.png");
                            } else {
                                unmSelectList.add("yl2.png");
                            }


                            if ("开通".equals(myHangPeiOu.getYsf())) {
                                mSelectList.add("ysf1.png");
                            } else {
                                unmSelectList.add("ysf2.png");
                            }

                            if ("开通".equals(myHangPeiOu.getBdqb())) {
                                mSelectList.add("bdqb1.png");
                            } else {
                                unmSelectList.add("bdqb2.png");
                            }

                            if ("开通".equals(myHangPeiOu.getCft())) {
                                mSelectList.add("cft1.png");
                            } else {
                                unmSelectList.add("cft2.png");
                            }

                            if ("开通".equals(myHangPeiOu.getSf())) {
                                mSelectList.add("sf1.png");
                            } else {
                                unmSelectList.add("sf2.png");
                            }

                            if ("开通".equals(myHangPeiOu.getSjyh())) {
                                mSelectList.add("sjyh1.png");
                            } else {
                                unmSelectList.add("sjyh2.png");
                            }

                            if ("开通".equals(myHangPeiOu.getRqf())) {
                                mSelectList.add("rq1.png");
                            } else {
                                unmSelectList.add("rq2.png");
                            }

                            if ("开通".equals(myHangPeiOu.getDf())) {
                                mSelectList.add("df1.png");
                            } else {
                                unmSelectList.add("df2.png");
                            }
                            mlistFive.addAll(mSelectList);
                            mlistFive.addAll(unmSelectList);
                            mSelectList.clear();
                            unmSelectList.clear();

                            myDataIconAdapterTwo.setNewData(mlistFive);
                            myDataIconAdapterTwo.notifyDataSetChanged();
                        }


                        mAdapter.setNewData(mList);
                        mAdapterTwo.setNewData(mListTwo);
                        mAdapterThree.setNewData(mListThree);
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

                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText("系统未通过，人工干预已通过!!");
                    tvWtg.setCompoundDrawables(drawable, null, null, null);
                    tvWtg.setTextColor(getResources().getColor(R.color.color_b9b8b8));
                    tvTg.setCompoundDrawables(drawable2, null, null, null);
                    tvTg.setTextColor(getResources().getColor(R.color.color_49a0ed));
                    break;
                case R.id.tv_wtg:
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText("系统已通过，人工干预未通过!!");

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
                    map.put("description", description);
                    map.put("sxid", "120");
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
