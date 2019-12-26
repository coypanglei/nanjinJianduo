package com.shaoyue.weizhegou.module.credit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.module.credit.fragment.apply.DbShouQuanShuFragment;
import com.shaoyue.weizhegou.router.UIHelper;

public class DyZxsqActivity extends BaseCommonActivity {

    private String mContentType;

    public static Intent newInstance(Context context, String contentType) {
        Intent intent = new Intent(context, DyZxsqActivity.class);
        intent.putExtra(UIHelper.CONTENT_TYPE, contentType);
        return intent;
    }

    @Override
    protected boolean initBundle(Bundle bundle) {
        mContentType = (String) getIntent().getSerializableExtra(UIHelper.CONTENT_TYPE);
        return true;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return DbShouQuanShuFragment.newInstance(mContentType);
    }



}
