package com.shaoyue.weizhegou.entity;

public class ContinentBean {

    private String name;

    private String code;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ContinentBean{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
