package com.shaoyue.weizhegou.module.start;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/29 07:19
 */
public enum StartStep {
    FETCH_DOMAIN, // 启动第一步，获取域名
    FETCH_INIT,   // 启动第二步，获取初始化参数
    CHECK_APP_ENBALE, // 检测应用是否可用
    CHECK_VERSION, // 启动第三步，检测版本
    HOME_PAGE_SWITCH,//首页开关
    FIRST_START,   // 启动第四步，是否第一次启动
    AUTO_LOGIN,     // 启动第五步，自动登录处理
    FINISH_LOGIN,   // 完成进入登录界面
    FINISH_MAIN,    // 完成进入主界面


}
