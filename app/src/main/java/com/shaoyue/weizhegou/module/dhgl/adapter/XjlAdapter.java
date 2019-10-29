package com.shaoyue.weizhegou.module.dhgl.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class XjlAdapter extends BaseQuickAdapter<Double, BaseViewHolder> {


    public XjlAdapter() {
        super(R.layout.item_xjl);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final Double item) {
        helper.setText(R.id.tv_text, item + "");

    }
}
