package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.Map;

public class GoAllSelect extends BaseBean {

    private QiYeDanBaoBean.RecordsBean mQiYeDanBao;
    private GongsiDanbao.RecordsBean mGongsiDanBao;

    private SxykhListBean.RecordsBean mSxykhlistBean;

    public GoAllSelect(boolean isAdd, String title) {
        this.isAdd = isAdd;
        this.title = title;
    }

    private Map jsonObjectmap;


    public Map getJsonObjectmap() {
        return jsonObjectmap;
    }

    public void setJsonObjectmap(Map jsonObjectmap) {
        this.jsonObjectmap = jsonObjectmap;
    }

    public GoAllSelect( boolean isAdd,String title,Map jsonObjectmap) {
        this.jsonObjectmap = jsonObjectmap;
        this.isAdd = isAdd;
        this.title = title;
    }

    public SxykhListBean.RecordsBean getmSxykhlistBean() {
        return mSxykhlistBean;
    }

    public void setmSxykhlistBean(SxykhListBean.RecordsBean mSxykhlistBean) {
        this.mSxykhlistBean = mSxykhlistBean;
    }

    public GoAllSelect(boolean isAdd, String title, SxykhListBean.RecordsBean mSxykhlistBean) {
        this.mSxykhlistBean = mSxykhlistBean;
        this.isAdd = isAdd;
        this.title = title;
    }

    public GoAllSelect(boolean isAdd, String title, QiYeDanBaoBean.RecordsBean mQiYeDanBao) {
        this.mQiYeDanBao = mQiYeDanBao;
        this.isAdd = isAdd;
        this.title = title;
    }

    public QiYeDanBaoBean.RecordsBean getmQiYeDanBao() {
        return mQiYeDanBao;
    }

    public void setmQiYeDanBao(QiYeDanBaoBean.RecordsBean mQiYeDanBao) {
        this.mQiYeDanBao = mQiYeDanBao;
    }

    public GongsiDanbao.RecordsBean getmGongsiDanBao() {
        return mGongsiDanBao;
    }

    public void setmGongsiDanBao(GongsiDanbao.RecordsBean mGongsiDanBao) {
        this.mGongsiDanBao = mGongsiDanBao;
    }

    public GoAllSelect(boolean isAdd, String title, GongsiDanbao.RecordsBean mGongsiDanBao) {
        this.mGongsiDanBao = mGongsiDanBao;
        this.isAdd = isAdd;
        this.title = title;
    }

    public GoAllSelect(boolean isAdd, String title, DiyaDanBaoListBean.RecordsBean mDiyaDanBao) {
        this.isAdd = isAdd;
        this.title = title;
        this.mDiyaDanBao = mDiyaDanBao;
    }

    public DiyaDanBaoListBean.RecordsBean getmDiyaDanBao() {
        return mDiyaDanBao;
    }

    public void setmDiyaDanBao(DiyaDanBaoListBean.RecordsBean mDiyaDanBao) {
        this.mDiyaDanBao = mDiyaDanBao;
    }

    public GoAllSelect(boolean isAdd, String title, ZiRanDanBaoListBean.RecordsBean mZiRanDanBao) {
        this.isAdd = isAdd;
        this.title = title;
        this.mZiRanDanBao = mZiRanDanBao;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    private boolean isAdd;

    private String title;

    private ZiRanDanBaoListBean.RecordsBean mZiRanDanBao;

    private DiyaDanBaoListBean.RecordsBean mDiyaDanBao;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ZiRanDanBaoListBean.RecordsBean getmZiRanDanBao() {
        return mZiRanDanBao;
    }

    public void setmZiRanDanBao(ZiRanDanBaoListBean.RecordsBean mZiRanDanBao) {
        this.mZiRanDanBao = mZiRanDanBao;
    }
}
