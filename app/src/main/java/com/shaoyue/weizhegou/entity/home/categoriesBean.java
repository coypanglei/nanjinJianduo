package com.shaoyue.weizhegou.entity.home;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/4/10 0010 16:21
 * <p>
 * 邮箱：434604925@qq.com
 */
public class categoriesBean extends BaseBean {
    //{
    //                "id":"0",
    //                "name":"全部商品"
    //            }

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
