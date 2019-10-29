package com.shaoyue.weizhegou.event;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/4/27 0027 11:14
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SelectSpecEvent extends BaseBean {

    private List<String> mLsit;

    private int num;

    public SelectSpecEvent(List<String> mLsit, int num) {
        this.mLsit = mLsit;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public List<String> getmLsit() {
        return mLsit;
    }

    public void setmLsit(List<String> mLsit) {
        this.mLsit = mLsit;
    }
}
