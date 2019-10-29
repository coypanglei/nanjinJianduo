package com.shaoyue.weizhegou.module.goods.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.shaoyue.weizhegou.R;

import com.shaoyue.weizhegou.entity.goods.GoodsSpecNameBean;
import com.shaoyue.weizhegou.entity.goods.GoodsSpecificationBean;
import com.shaoyue.weizhegou.entity.goods.SpecInfoBean;
import com.shaoyue.weizhegou.event.AddOrRemoveEvent;


import org.greenrobot.eventbus.EventBus;


import java.util.ArrayList;
import java.util.List;

public class GoodSpecAdapter extends BaseQuickAdapter<GoodsSpecNameBean, BaseViewHolder> {

    private List<String> mSelectSpecs = new ArrayList<>();

    private List<SpecInfoBean> mSpecInfoList = new ArrayList<>();

    private List<String> selectSpec = new ArrayList<>();

    private List<String> mSelectSpecClick = new ArrayList<>();

    private List<String> selectSpecNew = new ArrayList<>();
    private long unSelectPrice = 0;
    private long unSelectPriceNew = 0;

    public GoodSpecAdapter(List<GoodsSpecNameBean> list) {
        super(R.layout.item_spec_type, list);

    }

    public void setmSelectSpecs(List<String> mSelectSpecs) {
        this.mSelectSpecs = mSelectSpecs;
    }

    public void setmSpecInfoList(List<SpecInfoBean> mSpecInfoList) {
        this.mSpecInfoList = mSpecInfoList;
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodsSpecNameBean item) {
        final List<GoodsSpecificationBean> specItemList = item.getSpecItemList();
        helper.setText(R.id.tv_title, item.getName());
        final GoodSpecNameAdapter mGoodsSpecNameAdapter = new GoodSpecNameAdapter(specItemList);
        final FlexboxLayoutManager mLayoutManager = new FlexboxLayoutManager(mContext);
        final RecyclerView mRvItem = helper.getView(R.id.rv_goods_spec_name);
        mLayoutManager.setFlexWrap(FlexWrap.WRAP);
        mLayoutManager.setFlexDirection(FlexDirection.ROW);
        mLayoutManager.setAlignItems(AlignItems.STRETCH);
        mLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        mRvItem.setLayoutManager(mLayoutManager);
        mRvItem.setAdapter(mGoodsSpecNameAdapter);
        mGoodsSpecNameAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


                if (specItemList.get(position).getType() == 1) {
                    mSelectSpecs.remove(specItemList.get(position).getId() + "");

                    specItemList.get(position).setType(0);

                    setmSpecInfoList();
                    EventBus.getDefault().post(new AddOrRemoveEvent(mSelectSpecs, item, specItemList.get(position)));


                } else {

                    if (specItemList.get(position).getType() != 2) {
                        mSelectSpecClick.clear();
                        mSelectSpecClick.addAll(mSelectSpecs);

                        //移除所有的选中
                        for (GoodsSpecificationBean mGoodSpecInfo : specItemList) {
                            if (mGoodSpecInfo.getType() != 2) {
                                mGoodSpecInfo.setType(0);
                            }
                            mSelectSpecs.remove(mGoodSpecInfo.getId() + "");
                        }
                        //添加选中
                        mSelectSpecs.add(specItemList.get(position).getId() + "");

                        if (getInfoClick(specItemList.get(position).getId() + "")) {
                            specItemList.get(position).setType(1);
                            setmSpecInfoList();
                            EventBus.getDefault().post(new AddOrRemoveEvent(mSelectSpecs, item, specItemList.get(position)));

                        } else {

                            mSelectSpecs.clear();
                            mSelectSpecs.addAll(mSelectSpecClick);

                        }
                    }
//
                }


            }
        });


    }

    /**
     * 设置是否灰色
     */
    private void setmSpecInfoList() {

        for (GoodsSpecNameBean goodsSpecNameBean : mData) {
            for (GoodsSpecificationBean goodsSpecificationBean : goodsSpecNameBean.getSpecItemList()) {
                unSelectPrice = 0;
                unSelectPriceNew = 0;
                for (SpecInfoBean specInfoBean : mSpecInfoList) {

                    selectSpec.addAll(mSelectSpecs);
                    selectSpecNew.addAll(mSelectSpecs);
                    if (mSelectSpecs.size() > 0) {
                        if (!selectSpec.contains(goodsSpecificationBean.getId() + "")) {
                            selectSpec.add(goodsSpecificationBean.getId() + "");

                            if (selectSpec.size() <= specInfoBean.getKey().size()) {

                                if (specInfoBean.getKey().containsAll(selectSpec)) {


                                    unSelectPrice = unSelectPrice + specInfoBean.getStoreCount();

                                    //库存为零
                                    if (unSelectPrice == 0) {
                                        goodsSpecificationBean.setType(2);
                                    } else {
                                        if (goodsSpecificationBean.getType() != 1) {
                                            goodsSpecificationBean.setType(0);
                                        }
                                    }
                                }
                            }
                            selectSpec.remove(goodsSpecificationBean.getId() + "");


                        }
                        if (selectSpecNew.size() == specInfoBean.getKey().size()) {
                            selectSpecNew.remove(goodsSpecificationBean.getId() + "");
                            if (specInfoBean.getKey().containsAll(selectSpecNew)) {


                                unSelectPriceNew = unSelectPriceNew + specInfoBean.getStoreCount();

                                //库存为零
                                if (unSelectPriceNew != 0) {
                                    if (goodsSpecificationBean.getType() != 1) {
                                        goodsSpecificationBean.setType(0);
                                    }
                                }
                            }
                        }

                    } else {
                        if (goodsSpecificationBean.getType() != 1) {
                            goodsSpecificationBean.setType(0);
                        }
                    }
                    selectSpecNew.clear();
                    selectSpec.clear();
                }
            }
        }

    }

    long unSelectPinPrice = 0;

    /**
     * 获取库存量 不能点击
     *
     * @param
     */
    private boolean getInfoClick(String id) {

        //比较是否所有的都选中否
        unSelectPinPrice = 0;
        for (SpecInfoBean specInfoBean : mSpecInfoList) {
            if (specInfoBean.getKey().containsAll(mSelectSpecs)) {
                unSelectPinPrice = unSelectPinPrice + specInfoBean.getStoreCount();
            }
        }
        if (unSelectPinPrice == 0) {
            mSelectSpecs.remove(id);
            return false;
        }
        return true;


    }

}
