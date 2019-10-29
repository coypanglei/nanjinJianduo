package com.shaoyue.weizhegou.module.account.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.entity.order.OrderDetailsBean;
import com.shaoyue.weizhegou.module.general.activity.ProfileCommonActivity;
import com.shaoyue.weizhegou.module.goods.fragment.GoodsDetailsFragment;
import com.shaoyue.weizhegou.module.order.fragment.OrderDetailsFragment;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;

/**
 * 作者：HQY on 17/7/12 10:16
 * 邮箱：hqy_xz@126.com
 */

public class OrderDetailsActivity extends BaseCommonActivity {


    private ContentType mContentType;

    private String mProfileContent;

    private OrderDetailsBean mOrderBean;

    public static Intent newInstance(Context context, ContentType contentType,OrderDetailsBean mOrderBean) {
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra(UIHelper.CONTENT_TYPE, contentType);
        intent.putExtra("order", mOrderBean);
        return intent;
    }

    @Override
    protected boolean initBundle(Bundle bundle) {
        mContentType = (ContentType) getIntent().getSerializableExtra(UIHelper.CONTENT_TYPE);
        mOrderBean = (OrderDetailsBean) getIntent().getSerializableExtra("order");
        return true;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        switch (mContentType) {
            case ORDER_DETAILS:
                return OrderDetailsFragment.newInstance(mOrderBean);
            default:
                return null;
        }
    }


    @Override
    protected void initView() {
        super.initView();
    }


}
