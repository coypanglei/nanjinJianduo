package com.shaoyue.weizhegou.module.sxdc.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.SxspListBean;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class SxspAdapter extends BaseQuickAdapter<SxspListBean.RecordsBean, BaseViewHolder> {


    public SxspAdapter() {
        super(R.layout.item_sxsp);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final SxspListBean.RecordsBean item) {
        helper.setText(R.id.tv_xh, helper.getAdapterPosition() + 1 + "");
        helper.setText(R.id.tv_xyhj, item.getXyhj());
        helper.setText(R.id.tv_clsj, item.getClsj());
        helper.setText(R.id.tv_clsj_t, item.getCzsj());
        helper.setText(R.id.tv_clr, item.getClr());
        helper.setText(R.id.tv_status, item.getLczt());
        helper.setText(R.id.tv_dqhj, item.getDqhj());
        helper.setText(R.id.tv_sxmx, item.getSxmx());
        helper.setText(R.id.tv_khxm, item.getKhxm());

        if (item.isClick()) {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.color_33f0f0f0));
            helper.setTextColor(R.id.tv_xh, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_xyhj, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_clsj, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_clsj_t, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_clr, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_khxm, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_sxmx, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_dqhj, mContext.getResources().getColor(R.color.color_2c4eb6));
            helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.color_2c4eb6));

        } else {
            helper.setBackgroundColor(R.id.ll_top, mContext.getResources().getColor(R.color.white));

            helper.setTextColor(R.id.tv_xh, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_xyhj, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_clsj, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_clsj_t, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_clr, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_khxm, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_sxmx, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_dqhj, mContext.getResources().getColor(R.color.color_fc1c1a1d));
            helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.color_fc1c1a1d));
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
                return "待认领";
            case "1":
                return "待现场检验";
            case "2":
                return "待协查";
            case "3":
                return "待小组组长检查";
            case "4":
                return "待信贷部总经理审核";
            case "6":
                return "待授信部总经理审核";
            case "5":
                return "待授信部审批岗审核";
            case "200":
                return "完成";
            case "500":
                return "终止";

            default:
                break;
        }
        return "";
    }
}
