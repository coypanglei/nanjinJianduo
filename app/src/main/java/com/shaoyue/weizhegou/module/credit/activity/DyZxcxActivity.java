package com.shaoyue.weizhegou.module.credit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.module.credit.fragment.apply.DyDbCreditInquiryFragment;
import com.shaoyue.weizhegou.router.UIHelper;

public class DyZxcxActivity extends BaseCommonActivity {

    private String mContentType;

    public static Intent newInstance(Context context, String contentType) {
        Intent intent = new Intent(context, DyZxcxActivity.class);
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
        return DyDbCreditInquiryFragment.newInstance(mContentType);
    }



}
