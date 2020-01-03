package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.user.QianzibanDanBean;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class QianzibanDanAdapter extends BaseQuickAdapter<QianzibanDanBean, BaseViewHolder> {


    public QianzibanDanAdapter() {
        super(R.layout.item_sx_dan_qianzi);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final QianzibanDanBean item) {
        helper.setText(R.id.tv_one, item.getTitle());
        helper.setText(R.id.tv_two,item.getContent());

    }
}
