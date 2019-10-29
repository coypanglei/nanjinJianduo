package com.shaoyue.weizhegou.entity.goods;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：PangLei on 2019/4/3 0003 15:18
 * <p>
 * 邮箱：434604925@qq.com
 */
public class GoodsListBean {

    @SerializedName("list")
    private List<GoodsBean> goodList;

    @SerializedName("count")
    private int count;

    public List<GoodsBean> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<GoodsBean> goodList) {
        this.goodList = goodList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
