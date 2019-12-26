package com.shaoyue.weizhegou.module.sxdc.adapter;


import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.BasicTitle;
import com.shaoyue.weizhegou.module.dhgl.adapter.DhglBasicInformationAdapter;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class SyjbInformationAdapter extends BaseQuickAdapter<BasicTitle, BaseViewHolder> {

    public SyjbInformationAdapter() {
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
        if ("主营业务收入检验".equals(item.getTitle())||"年净利润检验".equals(item.getTitle())) {
            DhglBasicInformationAdapter mAdapter;
            mAdapter = new DhglBasicInformationAdapter();
            mAdapter.setActivity(getActivity());
            RecyclerView mIdJiben = helper.getView(R.id.ry);
            mIdJiben.setLayoutManager(new GridLayoutManager(getActivity(), 2));
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
            SyjbAdapter mAdapter;
            mAdapter = new SyjbAdapter();
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
