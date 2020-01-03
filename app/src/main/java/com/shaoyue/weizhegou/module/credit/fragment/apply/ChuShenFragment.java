package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.ChuShenBean;
import com.shaoyue.weizhegou.entity.cedit.TiJiaoBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.XClick.SingleClick;

import butterknife.BindView;
import butterknife.OnClick;

public class ChuShenFragment extends BaseAppFragment {


    @BindView(R.id.sb_edit)
    StateButton sbEdit;
    @BindView(R.id.tv_sqr_blcs)
    TextView tvSqrBlcs;
    @BindView(R.id.tv_sqr_sj_blcs)
    TextView tvSqrSjBlcs;
    @BindView(R.id.tv_sqr_wh_sjcs)
    TextView tvSqrWhSjcs;
    @BindView(R.id.tv_sqr_zx_sjcs)
    TextView tvSqrZxSjcs;
    @BindView(R.id.iv_whsj)
    ImageView ivWhsj;
    @BindView(R.id.iv_zxsj)
    ImageView ivZxsj;


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
    public void onResume() {
        super.onResume();
        initData();
    }

    //    初审结果
    private void initData() {
        CeditApi.getCsjg(new BaseCallback<BaseResponse<ChuShenBean>>() {
            @Override
            public void onSucc(BaseResponse<ChuShenBean> result) {
                if (ObjectUtils.isNotEmpty(result.data.getWh())) {
                    tvSqrBlcs.setMovementMethod(ScrollingMovementMethod.getInstance());
                    tvSqrBlcs.setOnTouchListener(onTouchListener);
                    tvSqrBlcs.setText(result.data.getWh().get(0).getWhsjshwtgyy());
                    tvSqrWhSjcs.setMovementMethod(ScrollingMovementMethod.getInstance());
                    tvSqrWhSjcs.setOnTouchListener(onTouchListener);
                    tvSqrWhSjcs.setText(result.data.getWh().get(0).getWhsjcs());
                    if ("通过".equals(result.data.getWh().get(0).getWhsjshjl())) {
                        ivWhsj.setImageResource(R.drawable.icon_chushen_pass);
                    } else {
                        ivWhsj.setImageResource(R.drawable.icon_chushen_no_pass);
                    }

                }
                if (ObjectUtils.isNotEmpty(result.data.getZx())) {
                    tvSqrSjBlcs.setMovementMethod(ScrollingMovementMethod.getInstance());
                    tvSqrSjBlcs.setOnTouchListener(onTouchListener);
                    tvSqrSjBlcs.setText(result.data.getZx().get(0).getZxshwtgyy());
                    tvSqrZxSjcs.setMovementMethod(ScrollingMovementMethod.getInstance());
                    tvSqrZxSjcs.setOnTouchListener(onTouchListener);
                    tvSqrZxSjcs.setText(result.data.getZx().get(0).getZxsjcs());
                    if ("通过".equals(result.data.getZx().get(0).getZxsjshjl())) {
                        ivZxsj.setImageResource(R.drawable.icon_chushen_pass);
                    } else {
                        ivZxsj.setImageResource(R.drawable.icon_chushen_no_pass);
                    }
                }
            }
        }, this);
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                //父节点不拦截子节点
                v.getParent().requestDisallowInterceptTouchEvent(true);
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                //父节点不拦截子节点
                v.getParent().requestDisallowInterceptTouchEvent(true);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                //父节点拦截子节点
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
            return false;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chushen;
    }

    @SingleClick(3000)
    @OnClick({R.id.sb_edit})
    public void onViewClicked() {

        String taskid = SPUtils.getInstance().getString(UserMgr.SP_DC_TASKID);
        String instid = SPUtils.getInstance().getString(UserMgr.SP_DC_INSTID);
        sbEdit.setVisibility(View.INVISIBLE);
        CeditApi.putDanbaoInfo(taskid, instid, new BaseCallback<BaseResponse<TiJiaoBean>>() {
            @Override
            public void onSucc(BaseResponse<TiJiaoBean> result) {
                UIHelper.showTijiaoDialog(getActivity(), result.data);
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        sbEdit.setVisibility(View.VISIBLE);
                    }
                }, 2000);

            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                sbEdit.setVisibility(View.VISIBLE);

            }
        }, this);
    }


}
