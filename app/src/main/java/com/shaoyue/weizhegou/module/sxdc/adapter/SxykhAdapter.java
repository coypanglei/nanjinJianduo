package com.shaoyue.weizhegou.module.sxdc.adapter;


import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.SxykhListBean;

import butterknife.BindView;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class SxykhAdapter extends BaseQuickAdapter<SxykhListBean.RecordsBean, BaseViewHolder> {



    @BindView(R.id.tv_xsbl)
    TextView tvXsbl;
    @BindView(R.id.tv_jsqd)
    TextView tvJsqd;
    @BindView(R.id.tv_jszq)
    TextView tvJszq;
    @BindView(R.id.tv_wlsj)
    TextView tvWlsj;
    @BindView(R.id.tv_sfwhkh)
    TextView tvSfwhkh;
    @BindView(R.id.tv_jydz)
    TextView tvJydz;
    @BindView(R.id.tv_jyxm)
    TextView tvJyxm;
    @BindView(R.id.tv_jzc)
    TextView tvJzc;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;


    public SxykhAdapter() {
        super(R.layout.item_sxykh);

    }


    @Override
    protected void convert(final BaseViewHolder helper, final SxykhListBean.RecordsBean item) {
        helper.setText(R.id.tv_mc, item.getMc());
        helper.setText(R.id.tv_sfzh,item.getSfzhm());
        helper.setText(R.id.tv_ms, item.getDescription());
        helper.setText(R.id.tv_xsbl,item.getCgbl());
        helper.setText(R.id.tv_jsqd,item.getJsqd());
        helper.setText(R.id.tv_jszq,item.getJszq());
        helper.setText(R.id.tv_wlsj,item.getWlsj());
        helper.setText(R.id.tv_sfwhkh,item.getSfwhkh());
        helper.setText(R.id.tv_jydz,item.getJydz());
        helper.setText(R.id.tv_jyxm,item.getJyxm());
        helper.setText(R.id.tv_jzc,item.getJzc());
        helper.setText(R.id.tv_phone_num,item.getLxdh());



        if (item.isClick()) {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
            helper.setTextColor(R.id.tv_sfzh, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_ms, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_xsbl, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_jsqd, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_jszq, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_wlsj, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_sfwhkh, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_jydz, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_jzc, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_jyxm, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_jszq, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_phone_num, mContext.getResources().getColor(R.color.color_2c4eb6));
        } else {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));
            helper.setTextColor(R.id.tv_sfzh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_sfzh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_ms, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_xsbl, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_jsqd, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_jszq, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_wlsj, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_sfwhkh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_jydz, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_jzc, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_jyxm, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_jszq, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_phone_num, mContext.getResources().getColor(R.color.color_fc1c1a1d));
        }
    }
}
