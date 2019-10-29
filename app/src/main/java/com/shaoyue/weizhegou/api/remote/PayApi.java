package com.shaoyue.weizhegou.api.remote;


import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.PayListBean;
import com.shaoyue.weizhegou.entity.PayOrderBean;
import com.shaoyue.weizhegou.entity.PayRecordList;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/30 12:50
 */
public class PayApi {

    // 获取付款记录
    private static final String API_FETCH_PAY_RECORD = "api/rtb/order_list";

    //获取付款列表
    private static final String API_RTB_PAK_LIST = "api/rtb/pak_list";


    //统一下单接口
    private static final String API_RTB_ORDER = "api/rtb/order";

    private static final String API_CHK_PAY_SUCCESS = "api/rtb/chk_pay_success";

    private static final String API_CHECK_PAY_PASSWORD = "cart/check_pay_password";

    //提交充值
    private static final String API_PRECHAARGE_ORDER = "user/preRechargeOrder";

    /**
     * 提交充值
     *
     * @param amount
     * @param payment
     * @param callback
     * @param tag
     */
    public static void preRechargeOrder(String amount, String payment, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("amount", amount);
        params.put("payment", payment);
        ApiHttpClient.post(API_PRECHAARGE_ORDER, params, callback, tag);
    }

    /**
     * 检查支付密码
     *
     * @param pay_password
     * @param callback
     * @param tag
     */
    public static void checkPayPassword(String pay_password, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pay_password", pay_password);
        ApiHttpClient.post(API_CHECK_PAY_PASSWORD, params, callback, tag);
    }

    public static void fetchPayRecord(int pageNum, int pageSize,
                                      BaseCallback<BaseResponse<PayRecordList>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("page", pageNum + "");
        params.put("pre_page", pageSize + "");

        ApiHttpClient.post(API_FETCH_PAY_RECORD, params, callback, tag);
    }


    public static void getPayPackageList(BaseCallback<BaseResponse<PayListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("type", "com");
        ApiHttpClient.post(API_RTB_PAK_LIST, params, callback, tag);
    }

    public static void getOrder(String account, String pakSn, String payType, BaseCallback<BaseResponse<PayOrderBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("account", account);
        params.put("pak_sn", pakSn);
        params.put("pay_type", payType);
        ApiHttpClient.post(API_RTB_ORDER, params, callback, tag);
    }

    public static void getOrderSuccess(String id, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> map = new HashMap<>();
        map.put("order_id", id);
        ApiHttpClient.post(API_CHK_PAY_SUCCESS, map, callback, tag);
    }

}
