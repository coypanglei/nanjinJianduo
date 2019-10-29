package com.shaoyue.weizhegou.module.goods.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.goods.AllGoodsBean;

import com.shaoyue.weizhegou.util.GlideNewImageLoader;

import java.util.List;


public class AllGoodsAdapter extends BaseQuickAdapter<AllGoodsBean, BaseViewHolder> {
    private Context content;
    private int mTypeView; //商品排列的方式0：网格；1：垂直列表排列
    private int size = 0;

    public AllGoodsAdapter(Context context) {
        super(R.layout.item_group_buy);
        this.content = context;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setType(int type) {
        this.mTypeView = type;
    }

    @Override
    public int getItemViewType(int position) {
        return mTypeView;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final AllGoodsBean item) {
        if (ObjectUtils.isNotEmpty(item)) {
            helper.setText(R.id.tv_goods_name, item.getProducts_name());
            TextView mOriginalPrice = helper.getView(R.id.tv_original_price);
            mOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            mOriginalPrice.setText("原价: " + item.getMarket_price());
            helper.setText(R.id.tv_current_price, "￥" + item.getShop_price());
            ImageView mIvGoods = helper.getView(R.id.iv_goods);
            GlideNewImageLoader.displayImage(content, mIvGoods, item.getImages());
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

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        //拿到之前获取的父布局
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(holder.getView(R.id.rl_pay_bg).getLayoutParams());

        if (position == (size - 1)) {
            lp.setMargins(0, 0, 0, 20);
            holder.getView(R.id.rl_pay_bg).setLayoutParams(lp);
        } else {
            lp.setMargins(0, 0, 0, 0);
            holder.getView(R.id.rl_pay_bg).setLayoutParams(lp);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 1) {//垂直
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_buy, parent, false));
        } else if (viewType == 0) {//网格
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spike_goods, parent, false));
        }
        return null;
    }
}
