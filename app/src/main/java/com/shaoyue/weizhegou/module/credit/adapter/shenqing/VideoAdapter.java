package com.shaoyue.weizhegou.module.credit.adapter.shenqing;

import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.VideoMaterialBean;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;


public class VideoAdapter extends BaseQuickAdapter<VideoMaterialBean.ListBean, BaseViewHolder> {


    public VideoAdapter() {
        super(R.layout.item_rv_hor);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final VideoMaterialBean.ListBean item) {

        if ("正".equals(item.getSxsfzh())) {
            helper.setImageResource(R.id.iv_update, R.drawable.icon_id_card_zhen);
            helper.setText(R.id.tv_title, RegexUtils.getReplaceFirst(item.getZllx(), "\\(.*?\\)", "") + "正面");
        } else if ("反".equals(item.getSxsfzh())) {
            helper.setImageResource(R.id.iv_update, R.drawable.icon_id_card_fan);
            helper.setText(R.id.tv_title, RegexUtils.getReplaceFirst(item.getZllx(), "\\(.*?\\)", "") + "反面");
        } else {
            if (item.getZllx().contains("法人客户") || item.getZllx().contains("工薪类客户") || item.getZllx().contains("个体经营户")) {
                helper.setText(R.id.tv_title, item.getZllx());
            } else {
                helper.setText(R.id.tv_title, RegexUtils.getReplaceFirst(item.getZllx(), "\\(.*?\\)", ""));
            }
        }
        ImageView iv = helper.getView(R.id.iv_update);
        if (ObjectUtils.isNotEmpty(item.getZldz())) {
            GlideNewImageLoader.displayImageNoDefault(mContext, iv, DomainMgr.getInstance().getBaseUrlImg() + item.getZldz());
        }

    }
}
