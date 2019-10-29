package com.shaoyue.weizhegou.module.main.adapter;


import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.GoodsApi;
import com.shaoyue.weizhegou.entity.goods.ModifyCarBean;
import com.shaoyue.weizhegou.entity.goods.ShopCarBean;

import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;

import com.shaoyue.weizhegou.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class ShoppingCarAdapter extends BaseQuickAdapter<ShopCarBean, BaseViewHolder> {


    private FragmentActivity mActivity;

    public ShoppingCarAdapter(FragmentActivity activity) {
        super(R.layout.item_shopping_car_goods);
        mActivity = activity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShopCarBean item) {


        helper.addOnClickListener(R.id.ll_left);
        helper.addOnClickListener(R.id.tv_products_name);
        helper.addOnClickListener(R.id.iv_goods);
//        helper.addOnClickListener(R.id.tv_change_info);

        ImageView mIvGoods = helper.getView(R.id.iv_goods);
        GlideNewImageLoader.displayImage(mContext, mIvGoods, item.getProducts_img());
        helper.setText(R.id.tv_products_name, item.getProducts_name());
        helper.setText(R.id.tv_specs, item.getSpec_key_name().replaceAll(";", "  "));
        helper.setText(R.id.tv_member_price, "￥" + item.getMember_price());
        TextView mOriginalPrice = helper.getView(R.id.tv_market_price);
        mOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mOriginalPrice.setText("原价: " + item.getMarket_price());
        if (item.getSelected() == 1) {
            helper.setImageResource(R.id.iv_select, R.drawable.icon_goods_select);
        } else if (item.getSelected() == 0) {
            helper.setImageResource(R.id.iv_select, R.drawable.icon_goods_not_select);
        }

    }
}
