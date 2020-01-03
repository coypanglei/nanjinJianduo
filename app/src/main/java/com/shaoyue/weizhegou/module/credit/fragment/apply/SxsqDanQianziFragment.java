package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.QianziBean;
import com.shaoyue.weizhegou.entity.user.QianzibanDanBean;
import com.shaoyue.weizhegou.entity.user.SxsqDanBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.QianzibanDanAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.ObjectToMapUtils;
import com.shaoyue.weizhegou.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


public class SxsqDanQianziFragment extends BaseAppFragment {


    @BindView(R.id.rv_click)
    RecyclerView mRvClick;

    QianzibanDanAdapter mAdapter;
    @BindView(R.id.iv_geren_qian)
    ImageView mIvGerenQian;

    @BindView(R.id.tv_time)
    TextView mTvTime;

    @BindView(R.id.tv_sfzh)
    TextView mTvSfzh;

    @BindView(R.id.sc_all)
    ScrollView scAll;
    @BindView(R.id.tv_rq)
    TextView tvRq;
    @BindView(R.id.tv_khjl)
    TextView tvKhjl;
    @BindView(R.id.tv_khrq)
    TextView tvKhrq;
    @BindView(R.id.sb_edit)
    StateButton sbEdit;


    private List<QianzibanDanBean> mlist = new ArrayList<>();

    private QianziBean qianziBean;
    private SxsqDanBean sxsqDanBean;

    public static SxsqDanQianziFragment newInstance() {
        Bundle args = new Bundle();


        SxsqDanQianziFragment fragment = new SxsqDanQianziFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sxsq_dan;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            qianziBean = new QianziBean();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 刷新界面
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(QianziBean event) {
        LogUtils.e(event);
        if (ObjectUtils.isNotEmpty(event)) {
            if (ObjectUtils.isEmpty(qianziBean.getId())) {
                qianziBean = event;
                sxsqDanBean.setSqrqm(qianziBean.getSqrqm());
                initData();
            }
        }
    }

    //删除图片
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("是否删除所选图片")) {
            if (event.getId().equals(qianziBean.getUploadImg())) {
                qianziBean.setUploadImg("");
                sxsqDanBean.setSqrqm("");
                initData();
            }

        }
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);


        mAdapter = new QianzibanDanAdapter();

        CeditApi.getSxsqDan(new BaseCallback<BaseResponse<SxsqDanBean>>() {
            @Override
            public void onSucc(BaseResponse<SxsqDanBean> result) {
                mlist.add(new QianzibanDanBean("支行:  ", result.data.getZh()));
                mlist.add(new QianzibanDanBean("申请人名称:  ", result.data.getSqrxm()));
                mlist.add(new QianzibanDanBean("身份证号码:  ", result.data.getSfzh()));
                mlist.add(new QianzibanDanBean("婚姻状况:  ", result.data.getHyzk()));
                mlist.add(new QianzibanDanBean("联系方式:  ", result.data.getLxfs()));
                mlist.add(new QianzibanDanBean("常住地址:  ", result.data.getCzdz()));
                mlist.add(new QianzibanDanBean("授信申请额度:  ", result.data.getEd()));
                mlist.add(new QianzibanDanBean("授信担保方式:  ", result.data.getZydbfs()));
                mlist.add(new QianzibanDanBean("授信期限:  ", result.data.getQx()));
                mlist.add(new QianzibanDanBean("授信用途:  ", result.data.getZydbfs()));
                qianziBean.setZjhm(result.data.getSfzh());
                sxsqDanBean = result.data;
                tvRq.setText("日期: " + result.data.getSqsj());
                tvKhjl.setText("客户经理: " + result.data.getKhjl());
                tvKhrq.setText("日期: " + result.data.getSqsj());
                qianziBean.setSqrqm(result.data.getSqrqm());
                mRvClick.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRvClick.setNestedScrollingEnabled(false);//禁止滑动
                mRvClick.setAdapter(mAdapter);
                mAdapter.setNewData(mlist);
                mAdapter.notifyDataSetChanged();
                //个人签名文件
                if (ObjectUtils.isNotEmpty(qianziBean.getSqrqm())) {
                    mIvGerenQian.setBackgroundResource(R.color.white);
                    GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), mIvGerenQian, DomainMgr.getInstance().getBaseUrlImg() + qianziBean.getSqrqm());
                }
                if ("查看详情".equals(SPUtils.getInstance().getString("status")) || ObjectUtils.isNotEmpty(sxsqDanBean.getId())) {
                    sbEdit.setVisibility(View.GONE);
                }
            }
        }, this);

        initData();
    }

    /**
     * 刷新数据
     */
    private void initData() {
        //签字版上传图片

        if (ObjectUtils.isNotEmpty(qianziBean)) {

            //个人签名文件
            if (ObjectUtils.isNotEmpty(qianziBean.getSqrqm())) {
                mIvGerenQian.setBackgroundResource(R.color.white);
                GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), mIvGerenQian, DomainMgr.getInstance().getBaseUrlImg() + qianziBean.getSqrqm());
            }


        }
    }


    private void reshData() {
        CeditApi.getSxsqDan(new BaseCallback<BaseResponse<SxsqDanBean>>() {
            @Override
            public void onSucc(BaseResponse<SxsqDanBean> result) {
                mlist.add(new QianzibanDanBean("支行:  ", result.data.getZh()));
                mlist.add(new QianzibanDanBean("申请人名称:  ", result.data.getSqrxm()));
                mlist.add(new QianzibanDanBean("身份证号码:  ", result.data.getSfzh()));
                mlist.add(new QianzibanDanBean("婚姻状况:  ", result.data.getHyzk()));
                mlist.add(new QianzibanDanBean("联系方式:  ", result.data.getLxfs()));
                mlist.add(new QianzibanDanBean("常住地址:  ", result.data.getCzdz()));
                mlist.add(new QianzibanDanBean("授信申请额度:  ", result.data.getEd()));
                mlist.add(new QianzibanDanBean("授信担保方式:  ", result.data.getZydbfs()));
                mlist.add(new QianzibanDanBean("授信期限:  ", result.data.getQx()));
                mlist.add(new QianzibanDanBean("授信用途:  ", result.data.getZydbfs()));
                qianziBean.setZjhm(result.data.getSfzh());
                sxsqDanBean = result.data;
                tvRq.setText("日期: " + result.data.getSqsj());
                tvKhjl.setText("客户经理: " + result.data.getKhjl());
                tvKhrq.setText("日期: " + result.data.getSqsj());
                qianziBean.setSqrqm(result.data.getSqrqm());
                mRvClick.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRvClick.setNestedScrollingEnabled(false);//禁止滑动
                mRvClick.setAdapter(mAdapter);
                mAdapter.setNewData(mlist);
                mAdapter.notifyDataSetChanged();
                //个人签名文件
                if (ObjectUtils.isNotEmpty(sxsqDanBean.getSqrqm())) {
                    mIvGerenQian.setBackgroundResource(R.color.white);
                    GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), mIvGerenQian, DomainMgr.getInstance().getBaseUrlImg() + sxsqDanBean.getSqrqm());
                }
                if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
                    sbEdit.setVisibility(View.GONE);
                }
            }
        }, this);
    }

    @OnClick({R.id.iv_geren_qian, R.id.sb_edit})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.iv_geren_qian:
                if ("查看详情".equals(SPUtils.getInstance().getString("status")) || (ObjectUtils.isNotEmpty(qianziBean.getId()))) {
                    return;
                }
                qianziBean.setType(true);
                UIHelper.showXezibanDialog(getActivity(), qianziBean);
                break;
            case R.id.sb_edit:
                Map<String, String> map = ObjectToMapUtils.str2Map(sxsqDanBean);
                if (ObjectUtils.isEmpty(sxsqDanBean.getId())) {
                    CeditApi.addSxsqDan(CeditApi.SXSQD_ADD, map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {

                            ToastUtil.showBlackToastSucess("保存成功");
                            reshData();
                        }
                    }, this);
                } else {
                    CeditApi.editSxsqDan(CeditApi.SXSQD_EDIT, map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            ToastUtil.showBlackToastSucess("保存成功");
                            reshData();
                        }
                    }, this);
                }
                break;

        }
    }


}
