package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.dhgl.XcjyProgressBean;


/**
 * 作者：PangLei on 2019/7/12 0012 14:36
 * <p>
 * 邮箱：434604925@qq.com
 */
public class XcjyProgressAdapter extends BaseQuickAdapter<XcjyProgressBean, BaseViewHolder> {


    public XcjyProgressAdapter() {
        super(R.layout.item_progress_recycle);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final XcjyProgressBean item) {
        try {
            if (item.isOne()) {
                holder.setVisible(R.id.iv_top_line, false);
            } else {
                holder.setVisible(R.id.iv_top_line, true);
            }

            holder.setVisible(R.id.iv_bottom_line, true);
            holder.setImageResource(R.id.iv_status, R.drawable.icon_blue_yuan);



            if (ObjectUtils.isNotEmpty(item.getId())) {
                holder.setText(R.id.tv_name,getString(item.getDqhj()));
                holder.setText(R.id.tv_spr, "审核人:       " + item.getClrMc());


                holder.setText(R.id.tv_spjg, "审批意见:  " + item.getShyj());

//
//                holder.setText(R.id.tv_spyj, "审批意见:  " + item.getComment());
                holder.setText(R.id.tv_time,  item.getClsj());
            } else {
                holder.setText(R.id.tv_spr, "审核人:       " + item.getClrMc());
                holder.setVisible(R.id.tv_spyj, false);
                holder.setVisible(R.id.tv_spjg, false);
//                holder.setGone(R.id.tv_start_time, false);
                holder.setVisible(R.id.iv_bottom_line, false);
                holder.setImageResource(R.id.iv_status, R.drawable.icon_hui_yuan);
            }
//            ImageView iv = holder.getView(R.id.iv_url);
//            if (item.getList().size() > 0 && ObjectUtils.isNotEmpty(item.getList().get(0).getUrl())) {
//                GlideNewImageLoader.displayImageNoCacheNoDefault(mContext, iv, DomainMgr.getInstance().getBaseUrlImg() + item.getList().get(0).getUrl());
//            }
        } catch (Exception e) {
            e.printStackTrace();
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
