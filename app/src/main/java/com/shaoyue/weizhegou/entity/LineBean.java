package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

public class LineBean {

    //
//    			"id": 5,
//                        "sn": "f906232711841937c8a61be58d563e1f",
//                        "name": "智能线路3",
//                        "country": "中国",
//                        "country_id": "CHN",
//                        "city": "深圳",
//                        "city_id": "CHN044003",
//                        "srv_code": "chn_zhenjiang_ct_3",
//                        "agreement": ",ikev2,l2tp,open,pptp,sovpn,",
//                        "ip": "47.244.108.79",
//                        "srv_port": ",so_vpn_udp:30086,",
//                        "remark": "",
//                        "score": 10,
//                        "load": 100,
//                        "be_node": "turtle_free_node",
//                        "delay_time": 10

    private String sn;

    private String name;

    private String country;



    @SerializedName("country_id")
    private String countryId;

    private String city;

    @SerializedName("city_id")
    private String cityId;

    @SerializedName("srv_code")
    private String srvCode;

    private String agreement;

    private String ip;

    @SerializedName("srv_port")
    private String srvPort;

    private String remark;

    private boolean select;

    private String isVip;

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    private int soUdpPort;
    @SerializedName("delay_time")
    private int delay_time;

    public int getDelay_time() {
        return delay_time;
    }

    public void setDelay_time(int delay_time) {
        this.delay_time = delay_time;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getSrvCode() {
        return srvCode;
    }

    public void setSrvCode(String srvCode) {
        this.srvCode = srvCode;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSrvPort() {
        return srvPort;
    }

    public void setSrvPort(String srvPort) {
        this.srvPort = srvPort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getSoUdpPort() {
        return soUdpPort;
    }

    public void setSoUdpPort(int soUdpPort) {
        this.soUdpPort = soUdpPort;
    }

    @Override
    public String toString() {
        return "LineBean{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", countryId='" + countryId + '\'' +
                ", city='" + city + '\'' +
                ", cityId='" + cityId + '\'' +
                ", srvCode='" + srvCode + '\'' +
                ", agreement='" + agreement + '\'' +
                ", ip='" + ip + '\'' +
                ", srvPort='" + srvPort + '\'' +
                ", remark='" + remark + '\'' +
                ", select=" + select +
                '}';
    }
}
