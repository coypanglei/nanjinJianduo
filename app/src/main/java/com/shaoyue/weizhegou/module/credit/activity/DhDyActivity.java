package com.shaoyue.weizhegou.module.credit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.entity.cedit.GoAllSelect;
import com.shaoyue.weizhegou.entity.cedit.OcrBean;
import com.shaoyue.weizhegou.module.credit.fragment.diaocha.DcDbQyfxFragment;
import com.shaoyue.weizhegou.module.dhgl.dialog.DcAddOrChangeDialogFragment;

import org.greenrobot.eventbus.EventBus;

public class DhDyActivity extends BaseCommonActivity {



    private GoAllSelect goAllSelect;
    public static Intent newInstance(Context context, GoAllSelect goAllSelect) {
        Intent intent = new Intent(context, DhDyActivity.class);

        intent.putExtra("GoAllSelect",goAllSelect);
        return intent;
    }

    @Override
    protected boolean initBundle(Bundle bundle) {

        goAllSelect =(GoAllSelect) getIntent().getSerializableExtra("GoAllSelect");
        return true;
    }

    @Override
    protected BaseFragment getFirstFragment() {

        switch (goAllSelect.getTitle()) {
            case "授信调查(自然人)担保分析":
                return DcAddOrChangeDialogFragment.newInstance(goAllSelect);
            case "新增授信调查-抵(质)押分析":
                return DcAddOrChangeDialogFragment.newInstance(goAllSelect);
            case "授信调查-企业担保分析":
                return DcDbQyfxFragment.newInstance(goAllSelect);
            default:
                return null;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1101://该结果码要与Fragment中的一致
            case 1102:
//                //音像资料
//            case 1007:
                //我这里使用的是根据结果码获取数据，然后加上下面一句代码，其
                //他的什么都不用做
                EventBus.getDefault().post(new OcrBean(data, requestCode));
                break;

        }
    }
}
