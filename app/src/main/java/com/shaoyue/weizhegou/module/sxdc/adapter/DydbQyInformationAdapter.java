package com.shaoyue.weizhegou.module.sxdc.adapter;


import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.BasicTitle;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.module.dhgl.adapter.DhglBasicInformationAdapter;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class DydbQyInformationAdapter extends BaseQuickAdapter<BasicTitle, BaseViewHolder> {

    public DydbQyInformationAdapter() {
        super(R.layout.item_base_info);
    }

    public FragmentActivity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    private FragmentActivity activity;


    @Override
    protected void convert(final BaseViewHolder helper, final BasicTitle item) {
        helper.setText(R.id.tv_my_info, item.getTitle());
        if ("企业信息".equals(item.getTitle())||"情况说明".equals(item.getTitle())) {
            RecyclerView mIdJiben = helper.getView(R.id.ry);
            DhglBasicInformationAdapter mAdapter;
            mAdapter = new DhglBasicInformationAdapter();
            mAdapter.setActivity(getActivity());
            GridLayoutManager layoutManage = new GridLayoutManager(getActivity(), 3);


            mIdJiben.setLayoutManager(layoutManage);
            mIdJiben.setAdapter(mAdapter);
            layoutManage.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    BasicInformationBean.RecordsBean bean = item.getMlist().get(position);
                    int count = 1;


                    if ("full".equals(bean.getParamtype())) {

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

            mAdapter.setNewData(item.getMlist());
        } else if ("数据统计".equals(item.getTitle())) {
            SyjbSjtjAdapter mAdapter;
            mAdapter = new SyjbSjtjAdapter();
            RecyclerView mIdJiben = helper.getView(R.id.ry);
            mIdJiben.setLayoutManager(new GridLayoutManager(getActivity(),4));
            mIdJiben.setAdapter(mAdapter);
            mIdJiben.setNestedScrollingEnabled(false);//禁止滑动
            //优化嵌套卡顿
            mIdJiben.setHasFixedSize(true);
            mIdJiben.setNestedScrollingEnabled(false);
            mIdJiben.setItemViewCacheSize(600);
            RecyclerView.RecycledViewPool recycledViewPool = new
                    RecyclerView.RecycledViewPool();
            mIdJiben.setRecycledViewPool(recycledViewPool);
            mAdapter.setNewData(item.getMlist());
        } else {
            DydbQyAdapter mAdapter;
            mAdapter = new DydbQyAdapter();
            mAdapter.setActivity(getActivity());
            RecyclerView mIdJiben = helper.getView(R.id.ry);
            mIdJiben.setLayoutManager(new LinearLayoutManager(getActivity()));
            mIdJiben.setAdapter(mAdapter);
            mIdJiben.setNestedScrollingEnabled(false);//禁止滑动
            //优化嵌套卡顿
            mIdJiben.setHasFixedSize(true);
            mIdJiben.setNestedScrollingEnabled(false);
            mIdJiben.setItemViewCacheSize(600);
            RecyclerView.RecycledViewPool recycledViewPool = new
                    RecyclerView.RecycledViewPool();
            mIdJiben.setRecycledViewPool(recycledViewPool);
            mAdapter.setNewData(item.getMlist());
        }

    }


}
