package com.shaoyue.weizhegou.module.dhgl.adapter;


import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.HfwBean;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class

HfwInformationAdapter extends BaseQuickAdapter<HfwBean.ListBeanX.汇法网信息Bean.ListBean, BaseViewHolder> {

    public FragmentActivity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    private FragmentActivity activity;


    public HfwInformationAdapter() {
        super(null);
        setMultiTypeDelegate(new MultiTypeDelegate<HfwBean.ListBeanX.汇法网信息Bean.ListBean>() {
            @Override
            protected int getItemType(HfwBean.ListBeanX.汇法网信息Bean.ListBean entity) {
                //根据你的实体类来判断布局类型

                if (entity.getValue().length() > 50) {
                    return BasicInformationBean.EDIT_FULL;
                } else {
                    return BasicInformationBean.EDIT;
                }
            }
        });
        //Step.2
        getMultiTypeDelegate()

                .registerItemType(BasicInformationBean.EDIT, R.layout.item_edit_kuang)
                .registerItemType(BasicInformationBean.EDIT_LARGE, R.layout.item_edit_kuang_large)
                .registerItemType(BasicInformationBean.EDIT_FULL, R.layout.item_edit_full_new)
                .registerItemType(BasicInformationBean.SELECT_CHANGE, R.layout.item_select_chang_kuang);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final HfwBean.ListBeanX.汇法网信息Bean.ListBean item) {
        helper.setIsRecyclable(false);

        helper.setText(R.id.tv_name_title, Html.fromHtml(item.getTitle() + ":"));
        final EditText mDdvXB = helper.getView(R.id.et_name);
        mDdvXB.setText(item.getValue());
        mDdvXB.setEnabled(false);


    }


}
