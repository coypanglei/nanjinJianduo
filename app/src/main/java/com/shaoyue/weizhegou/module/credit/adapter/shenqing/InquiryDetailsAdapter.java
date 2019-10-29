package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.InquiryDetailsBean;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class InquiryDetailsAdapter extends BaseQuickAdapter<InquiryDetailsBean, BaseViewHolder> {

    private String mTitle;
    private String mTitle2;

    public InquiryDetailsAdapter(String title, String title2) {
        super(R.layout.item_credit_1);
        mTitle = title;
        mTitle2 = title2;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final InquiryDetailsBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_one, item.getContent1());
        helper.setText(R.id.tv_two, item.getContent2());
        helper.setText(R.id.tv_jinying, mTitle);
        helper.setText(R.id.tv_jinying_two, mTitle2);
        if (ObjectUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.tv_jinying, "");
            helper.setText(R.id.tv_jinying_two, "");
        }
    }
}
