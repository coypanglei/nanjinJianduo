package com.shaoyue.weizhegou.entity.order;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/7/10 0010 10:32
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrderListBean extends BaseBean {

    @SerializedName("total")
    private int total;

    @SerializedName("per_page")
    private int preNum;

    @SerializedName("current_page")
    private int nowPage;

    @SerializedName("data")
    private List<OrderIdBean> mData;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPreNum() {
        return preNum;
    }

    public void setPreNum(int preNum) {
        this.preNum = preNum;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public List<OrderIdBean> getmData() {
        return mData;
    }

    public void setmData(List<OrderIdBean> mData) {
        this.mData = mData;
    }
}
