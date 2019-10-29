package com.shaoyue.weizhegou.module.credit.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.entity.user.MainClickBean;
import com.shaoyue.weizhegou.entity.user.MenuBean;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.CreditNavigationAdapter;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;

import java.util.List;

import butterknife.BindView;


public class FullCreditProcessFragment extends BaseAppFragment {

    @BindView(R.id.rv_features)
    RecyclerView mRvFeatures;
    CreditNavigationAdapter mNavigationAdapter;


    public static FullCreditProcessFragment newInstance() {
        Bundle args = new Bundle();
        FullCreditProcessFragment fragment = new FullCreditProcessFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav_credit;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
//        mlist.add(new MainClickBean("待办任务", R.drawable.icon_credit_nav_1));
//        mlist.add(new MainClickBean("授信申请", R.drawable.icon_credit_nav_2));
//        mlist.add(new MainClickBean("授信调查", R.drawable.icon_credit_nav_3));
//        mlist.add(new MainClickBean("授信审批", R.drawable.icon_credit_nav_4));
//        mlist.add(new MainClickBean("授信记录查询", R.drawable.icon_credit_nav_5));
//        mlist.add(new MainClickBean("阳光信贷", R.drawable.icon_credit_nav_6));
//        mlist.add(new MainClickBean("征信查询申请", R.drawable.icon_credit_nav_7));
//        mlist.add(new MainClickBean("征信查询审批", R.drawable.icon_credit_nav_8));
//        mlist.add(new MainClickBean("贷款利率测算", R.drawable.icon_credit_nav_9));
        CeditApi.getTitleName("ygxt", new BaseCallback<BaseResponse<MenuBean>>() {
            @Override
            public void onSucc(BaseResponse<MenuBean> result) {
                mNavigationAdapter.setNewData(result.data.getClickBean());
            }
        }, this);
        mNavigationAdapter = new CreditNavigationAdapter();
        mRvFeatures.setLayoutManager(new GridLayoutManager(getActivity(), 5, LinearLayoutManager.VERTICAL, false));
        mRvFeatures.setAdapter(mNavigationAdapter);
        mNavigationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MainClickBean data = (MainClickBean) adapter.getData().get(position);
                List<MainClickBean> mListClick = adapter.getData();
                switch (view.getId()) {
                    //去各种系统
                    case R.id.iv_icon:
//                        if ("".equals(data.getTitle())) {
                            UIHelper.showProfileCommonActivity(getActivity(), ContentType.CREDIT_APPLICATION, new profileBean(position, mListClick));
//                        }
                        break;
                }
            }
        });
    }
}
