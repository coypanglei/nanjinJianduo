package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class MenuBean extends BaseBean {

    @SerializedName("menu")
    private List<MainClickBean> clickBean;

    public List<MainClickBean> getClickBean() {
        return clickBean;
    }

    public void setClickBean(List<MainClickBean> clickBean) {
        this.clickBean = clickBean;
    }
}
