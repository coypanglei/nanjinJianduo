package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.InquiryDetailsBean;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class InquiryDetailsTwoAdapter extends BaseQuickAdapter<InquiryDetailsBean, BaseViewHolder> {


    public InquiryDetailsTwoAdapter() {
        super(R.layout.item_credit_2);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final InquiryDetailsBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_one, item.getContent1());

    }
}
