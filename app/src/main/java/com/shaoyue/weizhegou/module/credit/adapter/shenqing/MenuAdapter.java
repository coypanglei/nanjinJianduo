package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.user.MainClickBean;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class MenuAdapter extends BaseQuickAdapter<MainClickBean, BaseViewHolder> {


    public MenuAdapter() {
        super(R.layout.item_menu_text);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final MainClickBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        if(item.isSelect()){
            helper.setBackgroundColor(R.id.tv_title,mContext.getResources().getColor(R.color.color_23a7f0));
        }else {
            helper.setBackgroundColor(R.id.tv_title,mContext.getResources().getColor(R.color.color_002140));
        }
    }
}
