package com.shaoyue.weizhegou.entity.sxdc;

import com.shaoyue.weizhegou.base.BaseBean;

public class DbBean extends BaseBean {
    public DbBean(String jkr, String jkbz, String jkje, String wjfl) {
        this.jkr = jkr;
        this.jkbz = jkbz;
        this.jkje = jkje;
        this.wjfl = wjfl;
    }

    /**
     * jkr :
     * jkbz :
     * jkje :
     * wjfl :
     */

    private String jkr;
    private String jkbz;
    private String jkje;
    private String wjfl;

    public String getJkr() {
        return jkr;
    }

    public void setJkr(String jkr) {
        this.jkr = jkr;
    }

    public String getJkbz() {
        return jkbz;
    }

    public void setJkbz(String jkbz) {
        this.jkbz = jkbz;
    }

    public String getJkje() {
        return jkje;
    }

    public void setJkje(String jkje) {
        this.jkje = jkje;
    }

    public String getWjfl() {
        return wjfl;
    }

    public void setWjfl(String wjfl) {
        this.wjfl = wjfl;
    }
}
