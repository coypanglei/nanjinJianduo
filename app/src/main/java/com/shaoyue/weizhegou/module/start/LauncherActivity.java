package com.shaoyue.weizhegou.module.start;

import android.view.KeyEvent;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.util.ToastUtil;


/**
 * Created by librabin on 16/10/15.
 */

public class LauncherActivity extends BaseCommonActivity {
    private long exitTime = 0;
    @Override
    protected BaseFragment getFirstFragment() {
        return LauncherFragment.newInstance();
    }
    /**
     * 开屏页一定要禁止用户对返回按钮的控制，否则将可能导致用户手动退出了App而广告无法正常曝光和计费
     */
    @Override


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showSuccToast(getResources().getString(R.string.tips_exit_out));
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }


}
