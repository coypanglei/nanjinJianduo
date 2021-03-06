package com.shaoyue.weizhegou.module.dhgl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.entity.cedit.OcrBean;
import com.shaoyue.weizhegou.module.credit.fragment.diaocha.DcInfoFragment;
import com.shaoyue.weizhegou.module.dhgl.fragment.DgdkInfoFragment;
import com.shaoyue.weizhegou.module.dhgl.fragment.GrdkInfoFragment;
import com.shaoyue.weizhegou.module.dhgl.fragment.SjcjInfoFragment;
import com.shaoyue.weizhegou.module.dhgl.fragment.XcjyInfoFragment;
import com.shaoyue.weizhegou.module.dhgl.fragment.dgdkjcFragment;
import com.shaoyue.weizhegou.router.UIHelper;

import org.greenrobot.eventbus.EventBus;

public class XcjyActivity extends BaseCommonActivity {

    private String mContentType;

    private String type;

    //是否两百万以上
    private String is2;

    //
    private String isbws;



    public static Intent newInstance(Context context, String contentType, String type) {
        Intent intent = new Intent(context, XcjyActivity.class);
        intent.putExtra(UIHelper.CONTENT_TYPE, contentType);
        intent.putExtra(UIHelper.TYPE, type);
        return intent;
    }


    public static Intent newInstance(Context context, String contentType, String type,String isbws,String is2) {
        Intent intent = new Intent(context, XcjyActivity.class);
        intent.putExtra(UIHelper.CONTENT_TYPE, contentType);
        intent.putExtra(UIHelper.TYPE, type);
        intent.putExtra("isbws",isbws);
        intent.putExtra("is2",is2);
        return intent;
    }

    @Override
    protected boolean initBundle(Bundle bundle) {
        mContentType = (String) getIntent().getSerializableExtra(UIHelper.CONTENT_TYPE);
        type = getIntent().getStringExtra(UIHelper.TYPE);
        isbws =getIntent().getStringExtra("isbws");
        is2 =getIntent().getStringExtra("is2");
        return true;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        switch (type) {
            case "调查":
                return DcInfoFragment.newInstance(mContentType);
            case "现场检验":
                return XcjyInfoFragment.newInstance(mContentType);
            case "数据采集":
                return SjcjInfoFragment.newInstance(mContentType);
            case "首贷":
                return dgdkjcFragment.newInstance(mContentType);
            case "个贷":
                return GrdkInfoFragment.newInstance(mContentType);
            case "对公":
                return DgdkInfoFragment.newInstance(mContentType,isbws,is2);

            default:
                return null;
        }


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
