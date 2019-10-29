package com.shaoyue.weizhegou.entity.goods;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/4/25 0025 10:05
 * <p>
 * 邮箱：434604925@qq.com
 */
public class GoodsSpecAllBean extends BaseBean {

    //选中规格的查询
    @SerializedName("sku_spec")
    private List<SpecInfoBean> mSpecInfoBeanList;


    //规格列表
    @SerializedName("spec")
    private List<GoodsSpecNameBean> goodsSpecNameBeanList;

    public List<GoodsSpecNameBean> getGoodsSpecNameBeanList() {
        return goodsSpecNameBeanList;
    }

    public void setGoodsSpecNameBeanList(List<GoodsSpecNameBean> goodsSpecNameBeanList) {
        this.goodsSpecNameBeanList = goodsSpecNameBeanList;
    }

    public List<SpecInfoBean> getmSpecInfoBeanList() {
        return mSpecInfoBeanList;
    }

    public void setmSpecInfoBeanList(List<SpecInfoBean> mSpecInfoBeanList) {
        this.mSpecInfoBeanList = mSpecInfoBeanList;
    }
}
