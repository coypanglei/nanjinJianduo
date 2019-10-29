package com.shaoyue.weizhegou.module.start;


import com.shaoyue.weizhegou.entity.home.HomeInitBean;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/29 07:25
 */
public interface StartListener {

    void commplete(StartStep startStep, int code, String msg, HomeInitBean homeInitBean);

}
