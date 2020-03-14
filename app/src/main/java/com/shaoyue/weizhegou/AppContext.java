package com.shaoyue.weizhegou;


import com.shaoyue.weizhegou.base.BaseApplication;
import com.shaoyue.weizhegou.manager.SpeedMgr;


public class AppContext extends BaseApplication {


    private static AppContext instance;


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
        SpeedMgr.getInstance().setLineInfo(null);


    }


    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }


}
