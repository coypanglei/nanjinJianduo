package com.shaoyue.weizhegou.entity.cedit;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class VideoBean extends BaseBean {

    @SerializedName("list")
    private List<VideoMaterialBean> mlist;

    public List<VideoMaterialBean> getMlist() {
        return mlist;
    }

    public void setMlist(List<VideoMaterialBean> mlist) {
        this.mlist = mlist;
    }
}
