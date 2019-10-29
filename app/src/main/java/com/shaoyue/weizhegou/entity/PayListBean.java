package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;


import java.util.List;

/**
 * Created by USER on 2018/12/21.
 */

public class PayListBean extends BaseBean {
    @SerializedName("list")
    private List<PayBean> mPayList;

    public List<PayBean> getmPayList() {
        return mPayList;
    }

    public void setmPayList(List<PayBean> mPayList) {
        this.mPayList = mPayList;
    }
}
