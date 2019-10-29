package com.shaoyue.weizhegou.entity.goods;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/4/24 0024 09:17
 * <p>
 * 邮箱：434604925@qq.com
 */
public class GoodsSpecNameBean extends BaseBean {
    @SerializedName("id")
    private String id;

    //    规格id	展开
//    name  规格名称

    @SerializedName("name")
    private String name;

    @SerializedName("spec_item")
    private List<GoodsSpecificationBean> specItemList;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GoodsSpecificationBean> getSpecItemList() {
        return specItemList;
    }

    public void setSpecItemList(List<GoodsSpecificationBean> specItemList) {
        this.specItemList = specItemList;
    }
}

