package com.shaoyue.weizhegou.module.credit.adapter.diaocha;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.dhgl.SdInfoListBean;
import com.shaoyue.weizhegou.entity.diaocha.sxDiaoChaBean;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class sdjcAdapter extends BaseQuickAdapter<SdInfoListBean.RecordsBean, BaseViewHolder> {


    public sdjcAdapter() {
        super(R.layout.item_sdjc);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final SdInfoListBean.RecordsBean item) {

        /**
         * 	"dbfs": "",
         * 				"dkje": "",
         * 				"dkye": "",
         * 				"dkyt": "",
         * 				"id": "",
         * 				"khmc": "",
         * 				"qsrq": "",
         * 				"zffs": "",
         * 				"zjhm": ""
         *
         */
        helper.setText(R.id.tv_dbfs, item.getDbfs());
        helper.setText(R.id.tv_dkje,item.getDkje());
        helper.setText(R.id.tv_xh, helper.getAdapterPosition() + 1 + "");
        helper.setText(R.id.tv_dkyt, item.getDkyt());
        helper.setText(R.id.tv_khxm, item.getKhmc());
        helper.setText(R.id.tv_qsrq, item.getQsrq());
        helper.setText(R.id.tv_zffs, item.getZffs());
        helper.setText(R.id.tv_zjhm, item.getZjhm());
        if (item.isClick()) {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
            helper.setTextColor(R.id.tv_dkje, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_xh, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dkyt, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_qsrq, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_zffs, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_zjhm, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dbfs, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_khxm,mContext.getResources().getColor(R.color.color_2c4eb6));
        } else {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));
//            helper.setTextColor(R.id.tv_id_card, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dkje, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_xh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dkyt, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_qsrq, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_zffs, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_zjhm, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dbfs, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_khxm,mContext.getResources().getColor(R.color.color_fc1c1a1d));
        }
    }
}
