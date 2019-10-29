package com.shaoyue.weizhegou.event;

import com.shaoyue.weizhegou.base.BaseBean;
import com.shaoyue.weizhegou.entity.goods.GoodsSpecNameBean;
import com.shaoyue.weizhegou.entity.goods.GoodsSpecificationBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/4/30 0030 11:55
 * <p>
 * 邮箱：434604925@qq.com
 */
public class AddOrRemoveEvent extends BaseBean {

    private List<String> mSelectSpecs;

    public AddOrRemoveEvent(List<String> mSelectSpecs, GoodsSpecNameBean goodsSpecNameBean, GoodsSpecificationBean mGoodsSpecBean) {
        this.mSelectSpecs = mSelectSpecs;
        this.goodsSpecNameBean = goodsSpecNameBean;
        this.mGoodsSpecBean = mGoodsSpecBean;
    }


    public List<String> getmSelectSpecs() {
        return mSelectSpecs;
    }

    public void setmSelectSpecs(List<String> mSelectSpecs) {
        this.mSelectSpecs = mSelectSpecs;
    }

    public GoodsSpecNameBean getGoodsSpecNameBean() {
        return goodsSpecNameBean;
    }

    public void setGoodsSpecNameBean(GoodsSpecNameBean goodsSpecNameBean) {
        this.goodsSpecNameBean = goodsSpecNameBean;
    }

    private GoodsSpecNameBean goodsSpecNameBean;

    private GoodsSpecificationBean mGoodsSpecBean;


    public GoodsSpecificationBean getmGoodsSpecBean() {
        return mGoodsSpecBean;
    }

    public void setmGoodsSpecBean(GoodsSpecificationBean mGoodsSpecBean) {
        this.mGoodsSpecBean = mGoodsSpecBean;
    }
}
