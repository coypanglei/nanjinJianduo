package com.shaoyue.weizhegou.module.main.adapter;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.goods.GoodsBean;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;

import java.util.List;

public class HomeSpikeGoodsAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {

    public HomeSpikeGoodsAdapter(List<GoodsBean> list) {
        super(R.layout.item_spike_goods, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodsBean item) {
        helper.setText(R.id.tv_goods_name, item.getProducts_name());
        TextView mOriginalPrice = helper.getView(R.id.tv_original_price);
        mOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mOriginalPrice.setText("原价:" + item.getMarket_price());
        helper.setText(R.id.tv_current_price, "￥" + item.getShop_price());
        ImageView mIvGoods = helper.getView(R.id.iv_goods);
        GlideNewImageLoader.displayImage(mContext, mIvGoods, item.getImages());
        ImageView imageView = helper.getView(R.id.iv_buy);
        if (item.getCanBuy() == 1) {
            imageView.setVisibility(View.INVISIBLE);
        } else if (item.getCanBuy() == 0) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.drawable.icon_sold_out_white);
        }
        if (ObjectUtils.isNotEmpty(item.getActivity_name())) {
            helper.setVisible(R.id.tv_activity_name, true);
            helper.setText(R.id.tv_activity_name, item.getActivity_name());
        } else {
            helper.setGone(R.id.tv_activity_name, false);
        }
        if (ObjectUtils.isNotEmpty(item.getDiscount())) {
            helper.setVisible(R.id.tv_discount, true);
            helper.setText(R.id.tv_discount, item.getDiscount());
        } else {
            helper.setGone(R.id.tv_discount, false);
        }
    }

}
