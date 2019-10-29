package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

import com.shaoyue.weizhegou.base.BaseBean;


/**
 * 版本信息
 */
public class VersionBean extends BaseBean {

    // "is_update":"1",//是否更新 1为更新
    //        "version_name":"发现新版本",//更新提示/标题
    //        "update_content":"此次为测试更新",//更新内容
    //        "update_size":"0.00",//app大小
    //        "is_force_update":1,//是否强制更新 1为强制更新
    //        "package_url":"https://weizhegou.oss-cn-shanghai.aliyuncs.com/version/weizhegou_pro_android_v1.0.2_2_official.apk"//更新地址

    @SerializedName("is_update")
    private String is_update;


    public String getIs_update() {
        return is_update;
    }

    public void setIs_update(String is_update) {
        this.is_update = is_update;
    }

    @SerializedName("package_url")
    private String downloadUrl;

    @SerializedName("version_name")
    private String versionName;

    @SerializedName("update_content")
    private String desc;

    @SerializedName("update_size")
    private String size;

    @SerializedName("is_force_update")
    private String force;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    @Override
    public String toString() {
        return "VersionBean{" +
                "downloadUrl='" + downloadUrl + '\'' +
                ", versionName='" + versionName + '\'' +
                ", desc='" + desc + '\'' +
                ", size='" + size + '\'' +
                ", force='" + force + '\'' +
                '}';
    }
}
