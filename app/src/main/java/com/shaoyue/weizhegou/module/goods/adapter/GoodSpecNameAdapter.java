package com.shaoyue.weizhegou.module.goods.adapter;


import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;

import com.shaoyue.weizhegou.entity.goods.GoodsSpecificationBean;

import java.util.List;


public class GoodSpecNameAdapter extends BaseQuickAdapter<GoodsSpecificationBean, BaseViewHolder> {

    public GoodSpecNameAdapter(List< GoodsSpecificationBean> list) {
        super(R.layout.specification_tv,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsSpecificationBean item) {
        TextView mTvContent = helper.getView(R.id.tv_content);
        mTvContent.setText(item.getText());
        if(item.getStoreCount()==0){
            mTvContent.setTextColor(mContext.getResources().getColor(R.color.color_a4a4a4));
        }
        if (item.getType() == 0) {
            mTvContent.setEnabled(false);
        } else if (item.getType() == 1) {
            mTvContent.setEnabled(true);
        }else {
            mTvContent.setTextColor(mContext.getResources().getColor(R.color.color_a4a4a4));
        }

    }

}
