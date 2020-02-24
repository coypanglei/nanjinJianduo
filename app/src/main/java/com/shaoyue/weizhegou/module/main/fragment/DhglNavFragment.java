package com.shaoyue.weizhegou.module.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ObjectUtils;
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


public class DhglNavFragment extends BaseAppFragment {

    @BindView(R.id.rv_features)
    RecyclerView mRvFeatures;
    CreditNavigationAdapter mNavigationAdapter;

    private String title;

    public static DhglNavFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        DhglNavFragment fragment = new DhglNavFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            title = getArguments().getString("title");
        }
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        CeditApi.getTitleName(title, new BaseCallback<BaseResponse<MenuBean>>() {
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
                        UIHelper.showProfileCommonActivity(getActivity(), ContentType.CREDIT_APPLICATION, new profileBean(position, mListClick));
                        break;
                }
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav_credit;
    }
}
