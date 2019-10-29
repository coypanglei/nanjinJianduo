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
public class QianzibanAdapter extends BaseQuickAdapter<MainClickBean, BaseViewHolder> {


    public QianzibanAdapter() {
        super(R.layout.item_qianzi);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final MainClickBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        if(item.isSelect()){
            helper.setImageResource(R.id.iv_select,R.drawable.icon_xieziban_dui);
        }else {
            helper.setImageResource(R.id.iv_select,R.drawable.icon_xieziban_budui);
        }
    }
}
