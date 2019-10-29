package com.shaoyue.weizhegou.module.main.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseAppActivity;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.entity.user.MainClickBean;
import com.shaoyue.weizhegou.entity.user.MenuBean;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.CreditNavigationAdapter;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;

import java.util.List;

import butterknife.BindView;


public class DhglNavActivity extends BaseAppActivity {

    @BindView(R.id.rv_features)
    RecyclerView mRvFeatures;
    CreditNavigationAdapter mNavigationAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_navigation_dh;
    }


    @Override
    protected void initView() {
        super.initView();
        CeditApi.getTitleName("dhgl", new BaseCallback<BaseResponse<MenuBean>>() {
            @Override
            public void onSucc(BaseResponse<MenuBean> result) {
                mNavigationAdapter.setNewData(result.data.getClickBean());
            }
        }, this);
        mNavigationAdapter = new CreditNavigationAdapter();
        mRvFeatures.setLayoutManager(new GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false));
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
                        UIHelper.showProfileCommonActivity(DhglNavActivity.this, ContentType.CREDIT_APPLICATION, new profileBean(position, mListClick));
//                        }
                        break;
                }
            }
        });
    }



}
