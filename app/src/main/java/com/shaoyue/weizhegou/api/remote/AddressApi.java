package com.shaoyue.weizhegou.api.remote;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.center.AddressListBean;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：PangLei on 2019/4/16 0016 11:39
 * <p>
 * 邮箱：434604925@qq.com
 */
public class AddressApi {

    private static final String USER_ADDRESS_SAVE = "user_address/save";

    private static final String USER_ADDRESS_LIST = "user_address/list";

    private static final String USER_ADDRESS_DELETE = "user_address/delete";

    private static final String USER_ADDRESS_SET_DEFAULT = "user_address/set_default";

    /**
     * 保存地址
     *
     * @param consignee  收货人
     * @param mobile     手机号码
     * @param area       所在地区（三级区域id）
     * @param address    详细地址
     * @param is_default 是否是默认地址 0：不是默认 1：默认地址
     * @param callback
     * @param tag
     */
    public static void setUserAddressSave(String consignee, String mobile, String area, String address, String id, boolean is_default, BaseCallback<BaseResponse<AddressListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("consignee", consignee);
        params.put("mobile", mobile);
        params.put("area", area);
        params.put("address", address);
        if (is_default) {
            params.put("is_default", "1");
        } else {
            params.put("is_default", "0");
        }
        params.put("id", id);
        ApiHttpClient.post(USER_ADDRESS_SAVE, params, callback, tag);
    }

    /**
     * 获取地址列表
     *
     * @param callback
     * @param tag
     */
    public static void getAddressList(String keywords, BaseCallback<BaseResponse<List<AddressListBean>>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("keywords", keywords);
        ApiHttpClient.post(USER_ADDRESS_LIST, params, callback, tag);
    }

    /**
     * 删除地址
     *
     * @param id
     * @param callback
     * @param tag
     */
    public static void deleteAddress(String id, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);

        ApiHttpClient.post(USER_ADDRESS_DELETE, params, callback, tag);
    }


    /**
     * 删除地址
     *
     * @param id
     * @param callback
     * @param tag
     */
    public static void setDefaultAddress(String id, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        ApiHttpClient.post(USER_ADDRESS_SET_DEFAULT, params, callback, tag);
    }
}
