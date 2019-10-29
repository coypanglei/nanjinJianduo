package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.QianziBean;
import com.shaoyue.weizhegou.entity.user.MainClickBean;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.QianzibanAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class QianZiBanFragment extends BaseAppFragment {


    @BindView(R.id.rv_click)
    RecyclerView mRvClick;

    QianzibanAdapter mAdapter;
    @BindView(R.id.iv_geren_qian)
    ImageView mIvGerenQian;
    @BindView(R.id.iv_yh)
    ImageView mIvGzQian;
    @BindView(R.id.tv_time)
    TextView mTvTime;

    @BindView(R.id.tv_sfzh)
    TextView mTvSfzh;

    private List<MainClickBean> mlist = new ArrayList<>();

    private QianziBean qianziBean;

    public static QianZiBanFragment newInstance(QianziBean mQianziBean) {
        Bundle args = new Bundle();
        args.putSerializable("qianziban", mQianziBean);
        QianZiBanFragment fragment = new QianZiBanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_qianzi_ban;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            qianziBean = (QianziBean) getArguments().getSerializable("qianziban");
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
        if (ObjectUtils.isNotEmpty(event)) {
            if (qianziBean.getJs().equals(event.getJs())) {
                qianziBean = event;
                initData();
            }
        }
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mAdapter = new QianzibanAdapter();
        mlist.add(new MainClickBean("审核个人信贷业务申请;", true));
        mlist.add(new MainClickBean("审核货记卡、准货记卡申请:", false));
        mlist.add(new MainClickBean("审核本人作为担保人;", false));
        mlist.add(new MainClickBean("受理法人或其他组织的信贷申请或其作为担保人,需要查询其法定代表人、出资人及关联人信用状况;", false));
        mlist.add(new MainClickBean("对公业务贷后管理需查询法定代表人出资人及关联人信用状况;", false));
        mlist.add(new MainClickBean("特约商户实名审查;", false));
        mlist.add(new MainClickBean("资信审查;", false));
        mlist.add(new MainClickBean("客户准入资格审查;", false));
        mlist.add(new MainClickBean("提取本人或者配偶公积金;", false));
        mRvClick.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvClick.setNestedScrollingEnabled(false);//禁止滑动
        mRvClick.setAdapter(mAdapter);
        mAdapter.setNewData(mlist);
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<MainClickBean> mlist = adapter.getData();
                for (MainClickBean mainClickBean : mlist) {
                    mainClickBean.setSelect(false);
                }
                mlist.get(position).setSelect(true);
                String cxlx = (position + 1) + "";
                qianziBean.setCxlx(cxlx);
                adapter.setNewData(mlist);
                adapter.notifyDataSetChanged();
            }
        });
        initData();
    }

    /**
     * 刷新数据
     */
    private void initData() {
        if (ObjectUtils.isNotEmpty(qianziBean)) {
            mTvSfzh.setText("证件号码:" + qianziBean.getZjhm());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
//获取当前时间
            Date date = new Date(System.currentTimeMillis());

            mTvTime.setText("授权日期:" + simpleDateFormat.format(date));
            //个人签名文件
            if (ObjectUtils.isNotEmpty(qianziBean.getSqrqm())) {
                GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), mIvGerenQian, DomainMgr.getInstance().getBaseUrlImg() + qianziBean.getSqrqm());
            }
            //银行签名
            if (ObjectUtils.isNotEmpty(qianziBean.getSqrjbkhjlqm())) {
                GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), mIvGzQian, DomainMgr.getInstance().getBaseUrlImg() + qianziBean.getSqrjbkhjlqm());
            }

            if (ObjectUtils.isNotEmpty(qianziBean.getCxlx())) {
                int cxlx = Integer.parseInt(qianziBean.getCxlx());
                List<MainClickBean> mlist = mAdapter.getData();
                for (MainClickBean mainClickBean : mlist) {
                    mainClickBean.setSelect(false);
                }
                mlist.get(cxlx - 1).setSelect(true);
                mAdapter.setNewData(mlist);
                mAdapter.notifyDataSetChanged();
            }

        }
    }


    @OnClick({R.id.iv_geren_qian, R.id.iv_yh})
    public void onViewClicked(View view) {
        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_geren_qian:
                qianziBean.setType(true);
                UIHelper.showXezibanDialog(getActivity(), qianziBean);
                break;
            case R.id.iv_yh:
                qianziBean.setType(false);
                UIHelper.showXezibanDialog(getActivity(), qianziBean);
                break;
        }
    }


}
