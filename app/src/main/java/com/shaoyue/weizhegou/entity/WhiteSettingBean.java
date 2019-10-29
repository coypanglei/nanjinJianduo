package com.shaoyue.weizhegou.entity;

import android.graphics.drawable.Drawable;

import com.blankj.utilcode.util.AppUtils;

import java.io.Serializable;

/**
 * Created by apple on 17/6/6.
 */

public class WhiteSettingBean implements Serializable {

    private String name;
    private Drawable icon;
    private String packageName;
    private String packagePath;
    private String versionName;
    private int versionCode;
    private boolean check;

    public WhiteSettingBean(AppUtils.AppInfo appInfo, boolean check) {
        this.icon = appInfo.getIcon();
        this.name = appInfo.getName();
        this.packageName = appInfo.getPackageName();
        this.check = check;
    }
    public WhiteSettingBean(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
