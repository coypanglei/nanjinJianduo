package com.shaoyue.weizhegou.api.remote;


import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.home.HomeInitBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by USER on 2018/9/20.
 */

public class HomeApi {
    //初始化主页数据
    private static final String API_RTB_AD = "api/rtb/ad";


    private static final String API_INIT = "index/init";


    /**
     * 获取首页变动数据
     *
     * @param callback
     * @param tag
     */
    public static void fetchDataInit(BaseCallback<BaseResponse<HomeInitBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_INIT, params, callback, tag);
    }


}
