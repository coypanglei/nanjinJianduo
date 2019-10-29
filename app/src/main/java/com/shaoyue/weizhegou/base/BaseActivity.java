package com.shaoyue.weizhegou.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import butterknife.ButterKnife;
import me.jessyan.autosize.AutoSizeConfig;

/**
 * Created by librabin on 16/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    // 防抖动 是否可以点击
    private boolean mFragmentClickable = true;


    protected abstract int getContentViewId();

    protected void initView() {

    }

    protected void loadData() {
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (initBundle(getIntent().getExtras())) {
            setContentView(getContentViewId());
            ButterKnife.bind(this);
            initView();
            loadData();
        } else {
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected boolean initBundle(Bundle bundle) {
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            // 这里是防止动画过程中，按返回键取消加载Fragment
            if (!mFragmentClickable) {
                setFragmentClickable(true);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 防抖动(防止点击速度过快)
     */
    void setFragmentClickable(boolean clickable) {
        mFragmentClickable = clickable;
    }


    @SuppressWarnings("unchecked")
    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

}
