package com.shaoyue.weizhegou.module.sxdc.adapter;


import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.HfwBean;
import com.shaoyue.weizhegou.module.dhgl.adapter.HfwInformationAdapter;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class HfwInfoAdapter extends BaseQuickAdapter<HfwBean.ListBeanX.汇法网信息Bean, BaseViewHolder> {

    public HfwInfoAdapter() {
        super(null);
        setMultiTypeDelegate(new MultiTypeDelegate<HfwBean.ListBeanX.汇法网信息Bean>() {
            @Override
            protected int getItemType(HfwBean.ListBeanX.汇法网信息Bean entity) {
                //根据你的实体类来判断布局类型

                if ("配偶信息".equals(entity.getTitle())||"本人信息".equals(entity.getTitle())) {
                    return BasicInformationBean.EDIT_FULL;
                } else {
                    return BasicInformationBean.EDIT;
                }
            }
        });
        //Step.2
        getMultiTypeDelegate()
                .registerItemType(BasicInformationBean.EDIT, R.layout.item_base_info)
                .registerItemType(BasicInformationBean.EDIT_FULL, R.layout.item_peiou_title);

    }

    public FragmentActivity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    private FragmentActivity activity;


    @Override
    protected void convert(final BaseViewHolder helper, final HfwBean.ListBeanX.汇法网信息Bean item) {
//        helper.setIsRecyclable(false);
        helper.setText(R.id.tv_my_info, item.getTitle());
        if (!("配偶信息".equals(item.getTitle())||"本人信息".equals(item.getTitle()))) {

            HfwInformationAdapter mAdapter;
            mAdapter = new HfwInformationAdapter();
            mAdapter.setActivity(getActivity());
            RecyclerView mIdJiben = helper.getView(R.id.ry);
            GridLayoutManager layoutManage = new GridLayoutManager(getActivity(), 3);


            mIdJiben.setLayoutManager(layoutManage);
            mIdJiben.setAdapter(mAdapter);
            layoutManage.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    HfwBean.ListBeanX.汇法网信息Bean.ListBean bean = item.getList().get(position);
                    int count = 1;

                    if (bean.getValue().length() >= 50) {

                        count = 3;
                    } else {
                        count = 1;
                    }
                    return count;


                }
            });
            mIdJiben.setNestedScrollingEnabled(false);//禁止滑动
            //优化嵌套卡顿
            mIdJiben.setHasFixedSize(true);
            mIdJiben.setItemViewCacheSize(600);
            RecyclerView.RecycledViewPool recycledViewPool = new
                    RecyclerView.RecycledViewPool();
            mIdJiben.setRecycledViewPool(recycledViewPool);

            mAdapter.setNewData(item.getList());
        }

    }


}
