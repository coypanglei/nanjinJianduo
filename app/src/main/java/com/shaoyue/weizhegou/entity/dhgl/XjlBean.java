package com.shaoyue.weizhegou.entity.dhgl;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.ArrayList;

public class XjlBean extends BaseBean {


    /**
     * 近一年现金流入 : [0,0,0,0,0,0,0,0,0,0,0,0]
     * 近一年流入月份 : ["-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"]
     * 近一年现金流出 : [0,0,0,0,0,0,0,0,0,0,0,0]
     * max : 0.0
     * 流出总计 : .00
     * xjlpl : {"xjlfx":null,"sxid":null,"id":null,"xjlms":null}
     * 流入总计 : .00
     * 近一年流出月份 : ["-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"]
     * desc : 现金流分析：
     */

    private double max;
    private String 流出总计;

    private String 流入总计;
    private String desc;
    private ArrayList<Double> 近一年现金流入;
    private ArrayList<Double> 近一年现金流出;

    public ArrayList<Double> get近一年现金流入() {
        return 近一年现金流入;
    }



    public void set近一年现金流入(ArrayList<Double> 近一年现金流入) {
        this.近一年现金流入 = 近一年现金流入;
    }

    public ArrayList<Double> get近一年现金流出() {
        return 近一年现金流出;
    }

    public void set近一年现金流出(ArrayList<Double> 近一年现金流出) {
        this.近一年现金流出 = 近一年现金流出;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * xjlfx : null
     * sxid : null
     * id : null
     * xjlms : null
     */

    private String xjlfx;
    private String sxid;
    private String id;
    private String xjlms;

    public String getXjlfx() {
        return xjlfx;
    }

    public void setXjlfx(String xjlfx) {
        this.xjlfx = xjlfx;
    }

    public String getSxid() {
        return sxid;
    }

    public void setSxid(String sxid) {
        this.sxid = sxid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXjlms() {
        return xjlms;
    }

    public void setXjlms(String xjlms) {
        this.xjlms = xjlms;
    }



}
