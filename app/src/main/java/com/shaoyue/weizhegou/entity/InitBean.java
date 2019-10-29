package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;


/**
 * 初始化信息
 */
public class InitBean extends BaseBean {

    @SerializedName("company_name")
    private String companyName;

    @SerializedName("agreement_url")
    private String agreementUrl;

    @SerializedName("h5_pay_url")
    private String h5PayUrl;

    @SerializedName("chk_enable_url")
    private String checkEnableUrl;

    @SerializedName("free_radius_password")
    private String freeRadiusPassword;

    public String getClientApiUrl() {
        return clientApiUrl;
    }

    public void setClientApiUrl(String clientApiUrl) {
        this.clientApiUrl = clientApiUrl;
    }

    @SerializedName("client_api_url")
    private String clientApiUrl;

    public String getFreeRadiusPassword() {
        return freeRadiusPassword;
    }

    public void setFreeRadiusPassword(String freeRadiusPassword) {
        this.freeRadiusPassword = freeRadiusPassword;
    }

    public String getFreeRadiusAccount() {
        return freeRadiusAccount;
    }

    public void setFreeRadiusAccount(String freeRadiusAccount) {
        this.freeRadiusAccount = freeRadiusAccount;
    }

    @SerializedName("free_radius_account")
    private String freeRadiusAccount;

    public String getCompanyName() {
        return companyName;
    }

    public String getAgreementUrl() {
        return agreementUrl;
    }

    public String getH5PayUrl() {
        return h5PayUrl;
    }

    public String getCheckEnableUrl() {
        return checkEnableUrl;
    }

    @Override
    public String toString() {
        return "InitBean{" +
                "companyName='" + companyName + '\'' +
                ", agreementUrl='" + agreementUrl + '\'' +
                ", h5PayUrl='" + h5PayUrl + '\'' +
                ", checkEnableUrl='" + checkEnableUrl + '\'' +
                ", freeRadiusPassword='" + freeRadiusPassword + '\'' +
                ", freeRadiusAccount='" + freeRadiusAccount + '\'' +
                '}';
    }
}
