package com.shaoyue.weizhegou.entity.dhgl;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.ArrayList;

public class XjlBean extends BaseBean {


    /**
     * 近一年现金流入 : [2,5,1,4,1,8.55,10,3,5,2,6,11.22]
     * 近一年现金流出 : [1.43,2.22,3.33,4.44,5.55,6,15.65,2,3,4,5,6]
     * max : 15.65
     * desc : 正常
     */

    private double max;
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


}
