package com.shaoyue.weizhegou.module.dhgl.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.dhgl.DgZxcxBean;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class DgdbInfoAdapter extends BaseQuickAdapter<DgZxcxBean.RecordsBean.JkBean, BaseViewHolder> {


    public DgdbInfoAdapter() {
        super(R.layout.item_dg_zxdb_info);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final DgZxcxBean.RecordsBean.JkBean item) {
        helper.setText(R.id.tv_lx, item.getLx());
        helper.setText(R.id.tv_gzzhs, item.getGzlzhs());
        helper.setText(R.id.tv_gzye, item.getGzlye());
        helper.setText(R.id.tv_zczhs, item.getZclzhs());
        helper.setText(R.id.tv_zcye, item.getZclye());
        helper.setText(R.id.tv_blzhs, item.getBllzhs());
        helper.setText(R.id.tv_blye, item.getBllye());
        helper.setText(R.id.tv_hjzhs, item.getHjzhs());
        helper.setText(R.id.tv_hjye, item.getHjye());


    }
}
