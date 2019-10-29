package com.shaoyue.weizhegou.entity.center;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/4/16 0016 15:35
 * <p>
 * 邮箱：434604925@qq.com
 */
public class AddressListBean extends BaseBean {

    // "id":3,
//         "consignee":"朱琳",
//         "mobile":"18000111553",
//         "area":"天津,天津市,河东区",
//         "address":"测试地址",
//         "is_default":0
    //region_id=320303
    @SerializedName("id")
    private String id;

    @SerializedName("consignee")
    private String consignee;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("area")
    private String area;

    @SerializedName("address")
    private String address;

    @SerializedName("is_default")
    private int is_default;


    @SerializedName("region_id")
    private int regionId;

    private String btnContent;

    public String getBtnContent() {
        return btnContent;
    }

    public void setBtnContent(String btnContent) {
        this.btnContent = btnContent;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    @Override
    public String toString() {
        return "AddressListBean{" +
                "id='" + id + '\'' +
                ", consignee='" + consignee + '\'' +
                ", mobile='" + mobile + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", is_default=" + is_default +
                ", regionId=" + regionId +
                '}';
    }
}
