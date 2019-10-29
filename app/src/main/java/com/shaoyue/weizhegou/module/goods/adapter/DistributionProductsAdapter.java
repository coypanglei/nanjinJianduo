package com.shaoyue.weizhegou.module.goods.adapter;


import android.graphics.Paint;

import android.widget.ImageView;

import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.goods.AllGoodsBean;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;


public class DistributionProductsAdapter extends BaseQuickAdapter<AllGoodsBean, BaseViewHolder> {


    public DistributionProductsAdapter() {
        super(R.layout.item_distribution_products);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final AllGoodsBean item) {

        if (ObjectUtils.isNotEmpty(item)) {
            helper.setText(R.id.tv_goods_name, item.getProducts_name());
            helper.setText(R.id.tv_commission, item.getMayEarn());
            TextView mOriginalPrice = helper.getView(R.id.tv_original_price);
            mOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            mOriginalPrice.setText("原价: " + item.getMarket_price());
            helper.setText(R.id.tv_current_price, "￥" + item.getShop_price());
            ImageView mIvGoods = helper.getView(R.id.iv_goods);
            GlideNewImageLoader.displayImage(mContext, mIvGoods, item.getImages());
            TextView mGoDetails = helper.getView(R.id.tv_go_details);
            if (item.getCanBuy() == 1) {
                helper.addOnClickListener(R.id.tv_go_details);
                mGoDetails.setBackgroundResource(R.drawable.icon_red_jbshape_20);
                helper.setVisible(R.id.iv_sold_out, false);
            } else if (item.getCanBuy() == 0) {
                mGoDetails.setBackgroundResource(R.drawable.ic_tab_bottom_bg);
                helper.setVisible(R.id.iv_sold_out, true);
            }

        }
    }
}
