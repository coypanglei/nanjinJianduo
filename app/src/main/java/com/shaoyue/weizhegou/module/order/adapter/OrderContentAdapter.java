package com.shaoyue.weizhegou.module.order.adapter;


import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.coupon.CouponBean;
import com.shaoyue.weizhegou.entity.goods.ShopCarBean;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;

/**
 * 作者：PangLei on 2019/4/12 0012 13:55
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrderContentAdapter extends BaseQuickAdapter<ShopCarBean, BaseViewHolder> {


    public OrderContentAdapter() {
        super(R.layout.item_order_inside);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCarBean item) {
        helper.addOnClickListener(R.id.rl_click);
        ImageView mIvGoods = helper.getView(R.id.iv_goods);
        GlideNewImageLoader.displayImage(mContext, mIvGoods, item.getProducts_img());
        helper.setText(R.id.tv_products_name, item.getProducts_name());
        // 订单状态        helper.setText(R.id.tv_status,)
        if (ObjectUtils.isNotEmpty(item.getSpec_key_name())) {
            helper.setText(R.id.tv_specs, item.getSpec_key_name().replaceAll(";", "  "));
        }
        helper.setText(R.id.tv_member_price, "￥" + item.getMember_price());
        helper.setText(R.id.tv_products_num, "x" + item.getProducts_num());
    }
}
