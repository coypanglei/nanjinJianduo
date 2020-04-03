package com.shaoyue.weizhegou.module.credit.adapter.diaocha;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.dhgl.SdxjlBean;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class SdXjlAdapter extends BaseQuickAdapter<SdxjlBean.RecordsBean, BaseViewHolder> {


    public SdXjlAdapter() {
        super(R.layout.item_sd_xjl);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final SdxjlBean.RecordsBean item) {
/**
 * khmc : 移动客户1
 * sjrq : 2020-03-31
 * jysj : 09:54:23
 * jyje : 110.1
 * dfxm : 对方123
 * bz : 123
 * jylx : 0
 * id : 19
 * zjhm : 320304198804012414
 * jyrq : 2020-04-22
 * zh : 1112223334444411
 * dfzh : 1112223334444411
 */


        helper.setText(R.id.tv_xh, helper.getAdapterPosition() + 1 + "");
        helper.setText(R.id.tv_khmc, item.getKhmc());
        helper.setText(R.id.tv_jyrq, item.getJyrq());
        helper.setText(R.id.tv_zjhm, item.getZjhm());
        helper.setText(R.id.tv_dfxm, item.getDfxm());
        helper.setText(R.id.tv_bz, item.getBz());
        helper.setText(R.id.tv_jylx, getJylx(item.getJylx()));
        helper.setText(R.id.tv_jyje, item.getJyje() + "");
        helper.setText(R.id.tv_jysj, item.getJysj());
        helper.setText(R.id.tv_zh, item.getZh());
        helper.setText(R.id.tv_dfzh, item.getDfzh());

//        if (item.isClick()) {
//            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
//            helper.setTextColor(R.id.tv_khdz, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_dbfs, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_sshy, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_qyzjhm, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_fxfl, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_qymc, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_lxfs, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_frzjhm, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_dkje, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_dkyt, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_qsrq, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_frdb, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_zt, mContext.getResources().getColor(R.color.color_2c4eb6));
//
//            helper.setTextColor(R.id.tv_spnr, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_dkye, mContext.getResources().getColor(R.color.color_2c4eb6));
//        } else {
//            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));
////            helper.setTextColor(R.id.tv_id_card, mContext.getResources().getColor(R.color.color_2c4eb6));
//
//            helper.setTextColor(R.id.tv_khdz, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_dbfs, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_sshy, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_qyzjhm, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_fxfl, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_qymc, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_lxfs, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_frzjhm, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_dkje, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_dkyt, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_qsrq, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_frdb, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_zt, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//
//            helper.setTextColor(R.id.tv_spnr, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_dkye, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//        }
    }


    private String getJylx(String jylx) {
        if (jylx.equals("1")) {
            return "流入";
        } else if (jylx.equals("0")) {
            return "流出";
        }
        return "";
    }
}
