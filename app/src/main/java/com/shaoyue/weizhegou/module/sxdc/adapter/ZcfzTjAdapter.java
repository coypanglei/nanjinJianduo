package com.shaoyue.weizhegou.module.sxdc.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class ZcfzTjAdapter extends BaseQuickAdapter<BasicInformationBean.RecordsBean, BaseViewHolder> {


    public ZcfzTjAdapter() {
        super(R.layout.item_credit_2);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final BasicInformationBean.RecordsBean item) {
        helper.setText(R.id.tv_title, item.getTitile());
        helper.setText(R.id.tv_one, item.getDefaultvalue());

    }
}
