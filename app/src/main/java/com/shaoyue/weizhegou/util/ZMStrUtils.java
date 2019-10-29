package com.shaoyue.weizhegou.util;


import android.content.Context;

import android.net.wifi.WifiManager;


import com.blankj.utilcode.util.DeviceUtils;

import com.blankj.utilcode.util.Utils;

import java.util.List;


/**
 * Created by USER on 2018/9/13.
 */

public class ZMStrUtils {


    // 优化方法3，减少put次数
    public static boolean equalList(List list1, List list2) {
        return (list1.size() == list2.size()) && list1.containsAll(list2);
    }


    public static String getMacAddress() {


        return DeviceUtils.getMacAddress();
    }

}

