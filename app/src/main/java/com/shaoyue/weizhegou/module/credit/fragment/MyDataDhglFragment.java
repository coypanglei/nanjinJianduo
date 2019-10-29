package com.shaoyue.weizhegou.module.credit.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.entity.cedit.InquiryDetailsBean;
import com.shaoyue.weizhegou.entity.cedit.MyHangBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.InquiryDetailsTwoAdapter;
import com.shaoyue.weizhegou.widget.YStarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MyDataDhglFragment extends BaseFragment {
    @BindView(R.id.rv_credit)
    RecyclerView mRvCredit;
    @BindView(R.id.rv_credit_two)
    RecyclerView mRvCreditTwo;

    @BindView(R.id.ll_all)
    LinearLayout llAll;


    @BindView(R.id.starBar)
    YStarView starBar;
    @BindView(R.id.tv_tg)
    TextView tvTg;


    @BindView(R.id.sb_edit)
    StateButton sbEdit;


    private InquiryDetailsTwoAdapter mAdapter;
    private InquiryDetailsTwoAdapter mAdapterTwo;

    private List<InquiryDetailsBean> mList = new ArrayList<>();
    private List<InquiryDetailsBean> mListTwo = new ArrayList<>();


    public static MyDataDhglFragment newInstance() {

        Bundle args = new Bundle();

        MyDataDhglFragment fragment = new MyDataDhglFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dhgl_my_data;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);

        starBar.setStarCount(5);//星星总数

        starBar.setChange(false);//设置星星是否可以点击和滑动改变
        mAdapter = new InquiryDetailsTwoAdapter();
        mAdapterTwo = new InquiryDetailsTwoAdapter();


        mRvCredit.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCredit.setAdapter(mAdapter);
        mRvCredit.setNestedScrollingEnabled(false);//禁止滑动
        mRvCreditTwo.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCreditTwo.setAdapter(mAdapterTwo);
        mRvCreditTwo.setNestedScrollingEnabled(false);//禁止滑动


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
        map.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        DhApi.lookInfo(map, new BaseCallback<BaseResponse<MyHangBean>>() {
            @Override
            public void onSucc(BaseResponse<MyHangBean> result) {
                llAll.setVisibility(View.VISIBLE);

                mList.clear();
                mListTwo.clear();

                if (ObjectUtils.isNotEmpty(result.data)) {
                    if (null != result.data.getRecords()) {
                        if (result.data.getRecords().size() > 0) {
                            MyHangBean.RecordsBean myHangBean = result.data.getRecords().get(0);
                            MyHangBean.RecordsBean myHangPeiOu = new MyHangBean.RecordsBean();

                            //我行评级
                            starBar.setRating(myHangBean.getWhpj());//设置星星亮的颗数


                            if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
                                sbEdit.setVisibility(View.GONE);
                            }
//                            //提示信息
//                            if (ObjectUtils.isNotEmpty(myHangBean.getDescription())) {
//                                tvError.setVisibility(View.VISIBLE);
//                                tvError.setText(myHangBean.getDescription());
//                            }
                            try {
                                //通过未通过
                                if ("未通过".equals(myHangBean.getXtshjl())) {
                                    tvTg.setText(myHangBean.getXtshjl());

                                    tvTg.setTextColor(getResources().getColor(R.color.color_b9b8b8));


                                } else {
                                    tvTg.setText(myHangBean.getXtshjl());
                                    tvTg.setTextColor(getResources().getColor(R.color.color_49a0ed));
                                }
                            } catch (IllegalStateException e) {

                            }

                            if (result.data.getRecords().size() > 1) {
                                myHangPeiOu = result.data.getRecords().get(0);
                            }
                            mList.add(new InquiryDetailsBean("授信额度(万元)", myHangBean.getYsxje() + "", ""));
                            mList.add(new InquiryDetailsBean("用信余额(万元)", myHangBean.getYxye() + "", ""));
                            mList.add(new InquiryDetailsBean("不良贷款余额(万元)", myHangBean.getBnbldk() + "", ""));
                            mList.add(new InquiryDetailsBean("担保不良(万元)", myHangBean.getDbbldk(), ""));
                            mList.add(new InquiryDetailsBean("五级分类", myHangBean.getSqwjfl(), ""));
                            mList.add(new InquiryDetailsBean("本期授信日期", myHangBean.getSxrq(), ""));
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


                        }

                        mAdapter.setNewData(mList);
                        mAdapterTwo.setNewData(mListTwo);

                    }

                }
            }
        }, this);
    }


//    @OnClick({R.id.tv_tg, R.id.tv_wtg})
//    public void onViewClicked(View view) {
//
//        //不能点击 id有时不能点击
//        if (click) {
//            Drawable drawable = getResources().getDrawable(R.drawable.icon_left_star_black);
//            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//            Drawable drawable2 = getResources().getDrawable(R.drawable.icon_left_blue);
//            drawable2.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//            switch (view.getId()) {
//                case R.id.tv_tg:
//
//                    tvError.setVisibility(View.VISIBLE);
//                    tvError.setText("系统未通过，人工干预已通过!!");
//                    tvWtg.setCompoundDrawables(drawable, null, null, null);
//                    tvWtg.setTextColor(getResources().getColor(R.color.color_b9b8b8));
//                    tvTg.setCompoundDrawables(drawable2, null, null, null);
//                    tvTg.setTextColor(getResources().getColor(R.color.color_49a0ed));
//                    break;
//
//
//            }
//
//        }
//    }


}
