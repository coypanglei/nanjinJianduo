package com.shaoyue.weizhegou.module.owner.adapter;

import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.coupon.CouponBean;
import com.shaoyue.weizhegou.entity.user.SubordinateOrdererBean;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;

/**
 * 作者：PangLei on 2019/4/12 0012 13:55
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SubordinateOrdererAdapter extends BaseQuickAdapter<SubordinateOrdererBean, BaseViewHolder> {


    public SubordinateOrdererAdapter() {
        super(R.layout.item_orderer);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubordinateOrdererBean item) {
        if (ObjectUtils.isNotEmpty(item.getMobile())) {
            helper.setText(R.id.tv_name, "手机号/微信昵称：" + item.getMobile() + "  " + item.getWechat_nickname());
        } else {
            helper.setText(R.id.tv_name, "手机号/微信昵称：" + item.getWechat_nickname());
        }
        helper.setText(R.id.tv_grade, "等级：" + item.getRank_name());
        helper.setText(R.id.tv_time, "时间：" + item.getCreate_time());
        helper.setText(R.id.tv_nick_name, item.getNickname());
        ImageView imageView = helper.getView(R.id.profile_image);
        if (ObjectUtils.isNotEmpty(item.getHeaderpic())) {
            GlideNewImageLoader.displayImageNoCacheNoDefault(mContext, imageView, item.getHeaderpic());
        }
    }
}
