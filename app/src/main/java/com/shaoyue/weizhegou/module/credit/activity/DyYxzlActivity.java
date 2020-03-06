package com.shaoyue.weizhegou.module.credit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.entity.cedit.OcrBean;
import com.shaoyue.weizhegou.module.credit.fragment.apply.diyao.DbyxziFragment;
import com.shaoyue.weizhegou.router.UIHelper;

import org.greenrobot.eventbus.EventBus;

public class DyYxzlActivity extends BaseCommonActivity {

    private String mContentType;

    public static Intent newInstance(Context context, String contentType) {
        Intent intent = new Intent(context, DyYxzlActivity.class);
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
        return DbyxziFragment.newInstance(mContentType);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1104://该结果码要与Fragment中的一致
            case 1105:
//                //音像资料
            case 1007:

                EventBus.getDefault().post(new OcrBean(data, requestCode));
                break;

        }
    }
}
