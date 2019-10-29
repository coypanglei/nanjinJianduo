package com.shaoyue.weizhegou.module.address.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.center.AddressListBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/4/12 0012 13:55
 * <p>
 * 邮箱：434604925@qq.com
 */
public class ShippingAdapter extends BaseQuickAdapter<AddressListBean, BaseViewHolder> {

    private String type;

    public ShippingAdapter(String type) {
        super(R.layout.item_shipping_address);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressListBean item) {
        helper.setText(R.id.tv_name, item.getConsignee());
        helper.setText(R.id.tv_phone, item.getMobile());
        helper.setText(R.id.tv_area, item.getArea().replaceAll(",", " ") + " " + item.getAddress());
        TextView textView = helper.getView(R.id.tv_set_default);
        if (type.equals("1")) {
            helper.setGone(R.id.tv_delete, false);
        } else {
            helper.setVisible(R.id.tv_delete, true);
        }

        if (item.getIs_default() == 0) {
            textView.setText("默认地址");
            textView.setTextColor(mContext.getResources().getColor(R.color.color_4a4a4a));
            helper.setImageResource(R.id.iv_select, R.drawable.icon_select_cancel);
        } else {
            textView.setText("已设置为默认地址");
            textView.setTextColor(mContext.getResources().getColor(R.color.color_cd0946));
            helper.setImageResource(R.id.iv_select, R.drawable.icon_select_ok);
        }
        helper.addOnClickListener(R.id.tv_delete);
        helper.addOnClickListener(R.id.tv_edit);
        helper.addOnClickListener(R.id.iv_select);
        helper.addOnClickListener(R.id.tv_set_default);
    }
}
