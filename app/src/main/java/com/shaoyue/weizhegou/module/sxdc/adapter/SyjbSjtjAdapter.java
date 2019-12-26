package com.shaoyue.weizhegou.module.sxdc.adapter;


import android.support.v4.app.FragmentActivity;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class SyjbSjtjAdapter extends BaseQuickAdapter<BasicInformationBean.RecordsBean, BaseViewHolder> {

    public SyjbSjtjAdapter() {
        super(R.layout.item_sjtj_3);
    }

    public FragmentActivity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    private FragmentActivity activity;


    @Override
    protected void convert(final BaseViewHolder helper, final BasicInformationBean.RecordsBean item) {
        helper.setText(R.id.tv_title, item.getTitile());
        if (ObjectUtils.isNotEmpty(item.getList())) {
            if (ObjectUtils.isNotEmpty(item.getList().get(0))) {
                helper.setText(R.id.tv_title_one, item.getList().get(0).getTitile());
                helper.setText(R.id.tv_one, item.getList().get(0).getDefaultvalue());
            }
            if (item.getList().size() > 1) {
                if (ObjectUtils.isNotEmpty(item.getList().get(1))) {
                    helper.setText(R.id.tv_title_two, item.getList().get(1).getTitile());
                    helper.setText(R.id.tv_two, item.getList().get(1).getDefaultvalue());
                }
            }
            if (item.getList().size() > 2) {

                if (ObjectUtils.isNotEmpty(item.getList().get(2))) {
                    helper.setText(R.id.tv_title_three, item.getList().get(2).getTitile());
                    helper.setText(R.id.tv_three, item.getList().get(2).getDefaultvalue());
                }
            } else {
                helper.setGone(R.id.ll_three, false);
            }
        }
    }


}


