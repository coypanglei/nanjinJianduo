package com.shaoyue.weizhegou.module.credit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.entity.cedit.OcrBean;
import com.shaoyue.weizhegou.module.credit.fragment.diaocha.DcInfoFragment;
import com.shaoyue.weizhegou.router.UIHelper;

import org.greenrobot.eventbus.EventBus;

public class DcInfoActivity extends BaseCommonActivity {

    private String mContentType;

    public static Intent newInstance(Context context, String contentType) {
        Intent intent = new Intent(context, DcInfoActivity.class);
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
        return DcInfoFragment.newInstance(mContentType);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1004://该结果码要与Fragment中的一致
            case 1005:
                //音像资料
            case 1007:
                //我这里使用的是根据结果码获取数据，然后加上下面一句代码，其
                //他的什么都不用做
                EventBus.getDefault().post(new OcrBean(data, requestCode));
                break;

        }
    }
}
