package com.shaoyue.weizhegou.event;

public class SwitchTabEvent {

    private int mTabPosition = -1;//tab 切换的id 0 首页 1全部商品 2 最新商品

    public SwitchTabEvent(int mTabPosition) {
        this.mTabPosition = mTabPosition;
    }

    public int getmTabPosition() {
        return mTabPosition;
    }

    public void setmTabPosition(int mTabPosition) {
        this.mTabPosition = mTabPosition;
    }
}
