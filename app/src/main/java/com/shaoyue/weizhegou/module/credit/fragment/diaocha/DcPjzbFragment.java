package com.shaoyue.weizhegou.module.credit.fragment.diaocha;

import android.os.Bundle;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseAppFragment;

public class DcPjzbFragment extends BaseAppFragment {

    public static DcPjzbFragment newInstance() {

        Bundle args = new Bundle();

        DcPjzbFragment fragment = new DcPjzbFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dcpj;
    }
}
