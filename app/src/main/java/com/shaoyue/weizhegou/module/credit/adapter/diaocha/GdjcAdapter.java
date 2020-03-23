package com.shaoyue.weizhegou.module.credit.adapter.diaocha;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.dhgl.GdInfoListBean;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class GdjcAdapter extends BaseQuickAdapter<GdInfoListBean.RecordsBean, BaseViewHolder> {


    public GdjcAdapter() {
        super(R.layout.item_sdgd);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final GdInfoListBean.RecordsBean item) {
/**
 * khmc : 张三
 * khdz : XXXX小区
 * dbfs : 抵押
 * fxfl : 抵挡
 * lxfs : 15632565665
 * zjhm : 62010219900307369X
 * dkje : 34
 * dkyt : 买房
 * bqsfcxyqqx : 是
 * qsrq : 2020-02-17
 * id : 1
 * dkye : 12
 * sfyqhbl : 否
 */


        helper.setText(R.id.tv_xh, helper.getAdapterPosition() + 1 + "");
        helper.setText(R.id.tv_khmc,item.getKhmc());
        helper.setText(R.id.tv_khdz,item.getKhdz());
        helper.setText(R.id.tv_dbfs,item.getDbfs());
        helper.setText(R.id.tv_fxfl,item.getFxfl());
        helper.setText(R.id.tv_lxfs,item.getLxfs());
        helper.setText(R.id.tv_zjhm,item.getZjhm());
        helper.setText(R.id.tv_dkje,item.getDkje());
        helper.setText(R.id.tv_dkyt,item.getDkyt());
        helper.setText(R.id.tv_bqsfcxyqqx,item.getBqsfcxyqqx());
        helper.setText(R.id.tv_qsrq,item.getQsrq());

        helper.setText(R.id.tv_dkye,item.getDkye());
        helper.setText(R.id.tv_sfbl,item.getSfyqhbl());
        helper.setText(R.id.tv_spnr,item.getSpnr());
        helper.setText(R.id.tv_zt,item.getZt());
        if (item.isClick()) {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
            helper.setTextColor(R.id.tv_khmc, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_khdz, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dbfs, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_fxfl, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_lxfs, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_zjhm, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dkje, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dkyt, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_bqsfcxyqqx, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_qsrq, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_spnr, mContext.getResources().getColor(R.color.color_2c4eb6));

            helper.setTextColor(R.id.tv_zt, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dkye, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_sfbl, mContext.getResources().getColor(R.color.color_2c4eb6));
        } else {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));
//            helper.setTextColor(R.id.tv_id_card, mContext.getResources().getColor(R.color.color_2c4eb6));

            helper.setTextColor(R.id.tv_khmc, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_khdz, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dbfs, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_fxfl, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_lxfs, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_zjhm, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dkje, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dkyt, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_bqsfcxyqqx, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_qsrq, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_spnr, mContext.getResources().getColor(R.color.color_fc1c1a1d));

            helper.setTextColor(R.id.tv_zt, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dkye, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_sfbl, mContext.getResources().getColor(R.color.color_fc1c1a1d));
        }
    }
}
