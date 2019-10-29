package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.TiJiaoBean;
import com.shaoyue.weizhegou.router.UIHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class ChuShenFragment extends BaseAppFragment {


    @BindView(R.id.sb_edit)
    StateButton sbEdit;


    public static ChuShenFragment newInstance() {

        Bundle args = new Bundle();

        ChuShenFragment fragment = new ChuShenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            sbEdit.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chushen;
    }


    @OnClick(R.id.sb_edit)
    public void onViewClicked() {
        CeditApi.putDanbaoInfo(new BaseCallback<BaseResponse<TiJiaoBean>>() {
            @Override
            public void onSucc(BaseResponse<TiJiaoBean> result) {
                UIHelper.showTijiaoDialog(getActivity(), result.data);
            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);

            }
        }, this);
    }


}
