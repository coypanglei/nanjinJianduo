package com.shaoyue.weizhegou.module.goods.adapter;


import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.goods.ShopCarBean;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class SettlementOrderAdapter extends BaseQuickAdapter<ShopCarBean, BaseViewHolder> {


    private FragmentActivity mActivity;

    public SettlementOrderAdapter(FragmentActivity activity) {
        super(R.layout.item_settlement_center);
        mActivity = activity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShopCarBean item) {
        helper.addOnClickListener(R.id.ll_left);
        helper.addOnClickListener(R.id.tv_products_name);
        helper.addOnClickListener(R.id.iv_goods);
        ImageView mIvGoods = helper.getView(R.id.iv_goods);
        GlideNewImageLoader.displayImage(mContext, mIvGoods, item.getProducts_img());
        helper.setText(R.id.tv_products_name, item.getProducts_name());
        if (ObjectUtils.isNotEmpty(item.getSpec_key_name())) {
            helper.setText(R.id.tv_specs, item.getSpec_key_name().replaceAll(";", "  "));
        }
        helper.setText(R.id.tv_member_price, "￥" + item.getMember_price());
        helper.setText(R.id.tv_products_num, "x" + item.getProducts_num());
        if (item.getCart_status() == 1) {
            //售罄
            helper.setVisible(R.id.iv_expired, true);
            helper.setImageResource(R.id.iv_expired, R.drawable.icon_sold_out_white);
            helper.setVisible(R.id.tv_label, true);
            helper.setText(R.id.tv_label, "该商品没有库存，不能结算");
        } else if (item.getCart_status() == 2) {
            //失效
            helper.setVisible(R.id.tv_label, true);
            helper.setVisible(R.id.iv_expired, true);
            helper.setImageResource(R.id.iv_expired, R.drawable.icon_invalid_white);
            helper.setText(R.id.tv_label, "该商品已失效，不能结算");
        } else {
            //正常
            helper.setVisible(R.id.tv_label, false);
            helper.setVisible(R.id.iv_expired, false);
        }

    }
}
