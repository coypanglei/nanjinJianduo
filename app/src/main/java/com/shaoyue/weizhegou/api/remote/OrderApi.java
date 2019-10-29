package com.shaoyue.weizhegou.api.remote;

import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.order.LogisticsInfoListBean;
import com.shaoyue.weizhegou.entity.order.OrderDetailsBean;
import com.shaoyue.weizhegou.entity.order.OrderIdBean;
import com.shaoyue.weizhegou.entity.order.OrderListBean;
import com.shaoyue.weizhegou.entity.order.TopUpofferBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：PangLei on 2019/7/4 0004 16:17
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrderApi {

    private static final String API_CART_SUBMIT = "cart/submit";

    private static final String API_GET_RECHARGE = "user/getRechargeExplain";

    private static final String API_ORDER_INFO = "order/info";

    private static final String API_ORDER_LIST = "order/list";

    private static final String API_ORDER_GETEXPRESS_INFO = "order/getExpressInfo";

    private static final String API_CHECK_ID = "user_address/check_id";
    //订单删除/确认收货
    private static final String API_ORDER_OPERATE = "order/operate";

    /**
     * 订单删除/确认收货
     *
     * @param tag
     */
    public static void orderOperate(String order_id, String action, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("order_id", order_id);
        params.put("action", action);
        ApiHttpClient.post(API_ORDER_OPERATE, params, callback, tag);
    }


    /**
     * 检查证件是否正确
     *
     * @param tag
     */
    public static void checkId(String id, String id_card, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("id_card", id_card);
        ApiHttpClient.post(API_CHECK_ID, params, callback, tag);
    }


    /**
     * 获取物流信息
     *
     * @param order_products_id
     * @param tag
     */
    public static void getLogisticsInfo(String order_products_id, BaseCallback<BaseResponse<LogisticsInfoListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("order_products_id", order_products_id);

        ApiHttpClient.post(API_ORDER_GETEXPRESS_INFO, params, callback, tag);
    }


    /**
     * 获取订单列表
     *
     * @param callback
     * @param tag
     */
    public static void getOrderList(String composite_status, String page, BaseCallback<BaseResponse<OrderListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("composite_status", composite_status);
        params.put("page", page);
        ApiHttpClient.post(API_ORDER_LIST, params, callback, tag);
    }


    /**
     * 获取订单详情
     *
     * @param orderId
     * @param callback
     * @param tag
     */

    public static void getOrderInfo(String orderId, BaseCallback<BaseResponse<OrderDetailsBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("order_id", orderId);
        ApiHttpClient.post(API_ORDER_INFO, params, callback, tag);
    }

    //address_id复制
    //[int]	是	收货地址ID
    //delivery_id
    //[int]	是	配送方式
    //coupon_id
    //[int]		优惠券ID
    //pay_name
    //[string]		支付方式：alipay(支付宝) wechat(微信)
    //pay_password
    //[string]		支付密码
    //ids
    //[string]		购物车ID，立即购买不需要传，购物车提交需传
    //use_balance
    //[string]		是否使用余额1:使用 0:不使用
    //action
    //[string]		buy_now,代表提交立即购买的商品，购物车不传

    /**
     * 提交订单
     *
     * @param pay_password
     * @param callback
     * @param tag
     */
    public static void cartSubmit(String action, String address_id, String pay_password, String coupon_id, String pay_name, String ids, String use_balance, String id_card, BaseCallback<BaseResponse<OrderIdBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pay_password", pay_password);
        params.put("address_id", address_id);
        params.put("coupon_id", coupon_id);
        params.put("pay_name", pay_name);
        params.put("ids", ids);
        params.put("use_balance", use_balance);
        params.put("action", action);
        params.put("id_card", id_card);
        ApiHttpClient.post(API_CART_SUBMIT, params, callback, tag);
    }

    /**
     * 获取充值优惠
     *
     * @param callback
     * @param tag
     */
    public static void getRechargeExplain(BaseCallback<BaseResponse<List<TopUpofferBean>>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_GET_RECHARGE, params, callback, tag);
    }

}
