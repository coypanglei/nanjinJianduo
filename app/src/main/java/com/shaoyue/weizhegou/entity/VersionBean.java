package com.shaoyue.weizhegou.entity;

import com.shaoyue.weizhegou.base.BaseBean;


/**
 * 版本信息
 */
public class VersionBean extends BaseBean {
    /**
     * mobileVersion : 2
     * id : 1e0bf1bc25087e578b1a182a801ec7e6
     * versionName : 坚铎科技2.0
     * updateContent : 新发布版本
     * updateSize : 100Mb
     * isForceUpdate : 1
     * packageUrl : files/20190923/weizhegou_pro_android_v1.0.2_2_official_1569221020003.0.2_2_official.apk
     * isUpdate : false
     */

    private String mobileVersion;
    private String id;
    private String versionName;
    private String updateContent;
    private int updateSize;
    private String isForceUpdate;
    private String packageUrl;
    private String isUpdate;

    public String getMobileVersion() {
        return mobileVersion;
    }

    public void setMobileVersion(String mobileVersion) {
        this.mobileVersion = mobileVersion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public int getUpdateSize() {
        return updateSize;
    }

    public void setUpdateSize(int updateSize) {
        this.updateSize = updateSize;
    }

    public String getIsForceUpdate() {
        return isForceUpdate;
    }

    public void setIsForceUpdate(String isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
    }

    public String getPackageUrl() {
        return packageUrl;
    }

    public void setPackageUrl(String packageUrl) {
        this.packageUrl = packageUrl;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    // "is_update":"1",//是否更新 1为更新
    //        "version_name":"发现新版本",//更新提示/标题
    //        "update_content":"此次为测试更新",//更新内容
    //        "update_size":"0.00",//app大小
    //        "is_force_update":1,//是否强制更新 1为强制更新
    //        "package_url":"https://weizhegou.oss-cn-shanghai.aliyuncs.com/version/weizhegou_pro_android_v1.0.2_2_official.apk"//更新地址


}
