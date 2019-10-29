package com.shaoyue.weizhegou.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.blankj.utilcode.util.LogUtils;
import com.shaoyue.weizhegou.R;


/**
 * Created by librabin on 16/11/16.
 */

public abstract class BaseCommonActivity extends BaseAppActivity {

    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fragment;
    }


    //布局中Fragment的ID
    protected int getFragmentContentId() {
        return R.id.fragment_container;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments() || getSupportFragmentManager().getFragments().size() == 0) {
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment, BaseFragment.ANIMA_TYPE_NONE);
            } else {
                throw new NullPointerException("getFirstFragment() cannot be null");
            }
        }

    }


    protected void addFragment(BaseFragment fragment) {
        addFragment(fragment, BaseFragment.ANIMA_TYPE_DEFAULT);
    }


    //添加fragment
    protected void addFragment(BaseFragment fragment, int animType) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            if (animationAvailable) {
//                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            }
            if (animType == BaseFragment.ANIMA_TYPE_DEFAULT) {
                transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out,
                        R.anim.slide_left_in, R.anim.slide_right_out);
            } else if (animType == BaseFragment.ADIMA_TYPE_BOTTOM_IN_BOTTOM_OUT) {
                transaction.setCustomAnimations(R.anim.slide_bottom_in, R.anim.slide_bottom_out,
                        R.anim.slide_bottom_in, R.anim.slide_bottom_out);
            }

            transaction.replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();

//            FragmentUtils.replace(getSupportFragmentManager(), fragment, getFragmentContentId(),
//                    true,
//                    R.anim.slide_right_in, R.anim.slide_left_out,
//                    R.anim.slide_left_in, R.anim.slide_right_out);
        }
    }

    //移除fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager()
                    .popBackStack();
        } else {
            finish();
        }
    }

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            } else {
                removeFragment();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null && fragment instanceof BaseFragment) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
