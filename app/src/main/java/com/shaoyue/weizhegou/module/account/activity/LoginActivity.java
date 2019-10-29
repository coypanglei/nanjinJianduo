package com.shaoyue.weizhegou.module.account.activity;

import android.content.Intent;
import android.view.KeyEvent;

import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.module.account.fragment.LoginFragment;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.umeng.socialize.UMShareAPI;

import butterknife.OnClick;

/**
 * 作者：HQY on 17/7/12 10:16
 * 邮箱：hqy_xz@126.com
 */

public class LoginActivity extends BaseCommonActivity {


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            //两秒之内按返回键就会退出
//            if ((System.currentTimeMillis() - exitTime) > 2000) {
//                ToastUtil.showSuccToast(getResources().getString(com.shaoyue.weizhegou.R.string.tips_exit_out));
//                exitTime = System.currentTimeMillis();
//            } else {
//
//                finish();
//                System.exit(0);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//
//    }

    @Override
    protected BaseFragment getFirstFragment() {
        return LoginFragment.newInstance();
    }


    @Override
    protected void initView() {
        super.initView();


    }

    private long exitTime = 0;

//    @OnClick(com.shaoyue.weizhegou.R.id.tv_agreement)
//    public void onViewClicked() {
//        UIHelper.showWebPage(this, getResources().getString(com.shaoyue.weizhegou.R.string.content_user_service_protocol), AppMgr.getInstance().getAgreementUrl());
//    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
