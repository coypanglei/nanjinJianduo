package com.shaoyue.weizhegou.module.dhgl.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.XcjyBean;
import com.shaoyue.weizhegou.util.McUtils;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class SjcjAdapter extends BaseQuickAdapter<XcjyBean, BaseViewHolder> {


    public SjcjAdapter() {
        super(R.layout.item_sjcj_list);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final XcjyBean item) {
        helper.setGone(R.id.tv_xyhj, false);
        helper.setText(R.id.tv_xuhao, helper.getAdapterPosition() + 1 + "");
        helper.setText(R.id.tv_nfcs, item.getJcjd());
        helper.setText(R.id.tv_lczt, getString(item.getZt()));
        helper.setText(R.id.tv_dqhj, item.getDqhj());
        helper.setText(R.id.tv_khh, item.getKhh());
        helper.setText(R.id.tv_khxm, item.getKhmc());
        helper.setText(R.id.tv_spzt, item.getSpzt());
        helper.setText(R.id.tv_clr, item.getClrMc());
        helper.setText(R.id.tv_zjlx, item.getZjlx());
        helper.setText(R.id.tv_zjhm, item.getZjhm());
        helper.setText(R.id.tv_ghrmc, item.getGhrXm());
        helper.setText(R.id.tv_sxje, item.getSxje());
        helper.setText(R.id.tv_dqhj, McUtils.getString(item.getDqschj()));
//        helper.setText(R.id.tv_xyhj, getString(item.getXyhj()));
        helper.setText(R.id.tv_jgmc, item.getJgmc());
        if (item.isClick()) {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_nfcs, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_lczt, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dqhj, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_khh, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_khxm, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_clr, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_zjlx, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_zjhm, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_ghrmc, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_sxje, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dqhj, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_xyhj, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_jgmc, mContext.getResources().getColor(R.color.color_2c4eb6));

        } else {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));

            helper.setTextColor(R.id.tv_xuhao, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_nfcs, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_lczt, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dqhj, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_khh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_khxm, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_clr, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_zjlx, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_zjhm, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_ghrmc, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_sxje, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dqhj, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_xyhj, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_jgmc, mContext.getResources().getColor(R.color.color_fc1c1a1d));
//
        }
    }

    private String getString(String status) {
        //-1 待采集
        //0  待认领
        //1  待现场检验
        //2  待协查
        //3  待小组组长检查
        //4  待授信部检查
        //200 完成
        //500 终止
        switch (status) {
            case "-1":
                return "待采集";
            case "0":
                return "已采集";

            default:
                break;
        }
        return "";
    }
}
