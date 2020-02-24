package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.ZxcxListBean;
import com.shaoyue.weizhegou.entity.cedit.FamilyBean;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class zxdbInfoAdapter extends BaseQuickAdapter<ZxcxListBean.RecordsBean.DbqkAndroidBean, BaseViewHolder> {


    public zxdbInfoAdapter() {
        super(R.layout.item_zxdb_info);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ZxcxListBean.RecordsBean.DbqkAndroidBean item) {
        /**
         * 担保五级分类 : 未分类
         * 担保总额 : 700
         * 担保余额 : 100
         * 币种 : 人民币
         * 对外担保机构数 : 1
         */
        helper.setText(R.id.tv_dbwjfl, item.get担保五级分类());
        helper.setText(R.id.tv_dbze, item.get担保总额());
        helper.setText(R.id.tv_dbye,item.get担保余额());
        helper.setText(R.id.tv_bz, item.get币种());
        helper.setText(R.id.tv_dwdbjg, item.get对外担保机构数());


//        if (item.isClick()) {
//            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
//            helper.setTextColor(R.id.tv_sfzh, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_js,  mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_name, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_xb, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_nl, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_hyzk, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_jkzk, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_lxdh, mContext.getResources().getColor(R.color.color_2c4eb6));
//            helper.setTextColor(R.id.tv_zy, mContext.getResources().getColor(R.color.color_2c4eb6));
//        } else {
//            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));
//            helper.setTextColor(R.id.tv_sfzh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_js,  mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_name, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_xb, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_nl, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_hyzk, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_jkzk, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_lxdh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//            helper.setTextColor(R.id.tv_zy, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//
//        }
    }
}
