package com.shaoyue.weizhegou.module.main.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.user.MainClickBean;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class NavigationAdapter extends BaseQuickAdapter<MainClickBean, BaseViewHolder> {


    public NavigationAdapter() {
        super(R.layout.item_navigation);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final MainClickBean item) {
        helper.addOnClickListener(R.id.tv_msg);
        helper.addOnClickListener(R.id.iv_icon);
        helper.setImageResource(R.id.iv_icon, item.getImg());
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_msg, "5");

        if (helper.getAdapterPosition() == 1) {
            helper.setGone(R.id.tv_msg, true);
        }
    }
}
