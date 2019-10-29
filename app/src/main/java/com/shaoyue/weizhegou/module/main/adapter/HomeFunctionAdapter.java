package com.shaoyue.weizhegou.module.main.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.FeaturesBean;

import java.util.List;

public class HomeFunctionAdapter extends BaseQuickAdapter<FeaturesBean, BaseViewHolder> {

    public HomeFunctionAdapter(List<FeaturesBean> list) {
        super(R.layout.item_home_function, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeaturesBean item) {
        //加载背景
        Glide.with(mContext)
                .load(item.getUrl())
                .into((ImageView) helper.getView(R.id.iv_top));
        helper.setText(R.id.tv_name, item.getTitle());

    }

}
