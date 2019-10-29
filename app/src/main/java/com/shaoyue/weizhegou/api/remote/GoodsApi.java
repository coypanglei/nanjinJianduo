package com.shaoyue.weizhegou.api.remote;

import com.blankj.utilcode.util.ObjectUtils;
import com.lzy.okgo.callback.BitmapCallback;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.coupon.CouponBean;
import com.shaoyue.weizhegou.entity.coupon.SettlementCouponBean;
import com.shaoyue.weizhegou.entity.goods.AddAndSubtractBean;
import com.shaoyue.weizhegou.entity.goods.AllGoodsListBean;
import com.shaoyue.weizhegou.entity.goods.GoodsBean;
import com.shaoyue.weizhegou.entity.goods.GoodsDetialBean;
import com.shaoyue.weizhegou.entity.goods.GoodsListBean;
import com.shaoyue.weizhegou.entity.goods.GoodsSpecAllBean;
import com.shaoyue.weizhegou.entity.goods.ModifyCarBean;
import com.shaoyue.weizhegou.entity.goods.SettlementCenterBean;
import com.shaoyue.weizhegou.entity.goods.ShopCarListBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：PangLei on 2019/4/3 0003 11:19
 * <p>
 * 邮箱：434604925@qq.com
 */
public class GoodsApi {
    //整点秒杀
    private static final String API_GOODS_GET_SPIKE_LIST = "index/spike_list";

    //今日团购
    private static final String API_GOODS_GET_TODAY_LIST = "index/today_list";

    //全部商品
    private static final String API_PRODUCTS_INDEX = "products/index";

    //商品详情页
    private static final String API_PRODUCTS_INFO = "products/info";

    //商品规格
    private static final String API_PRODUCTS_SPEC = "products/spec";

    //获取商品
    private static final String API_PRODUCTS_PNG = "products/share_png";

    //加入购物车
    private static final String API_ADD_BUY_CAR = "cart/add";

    //购物车数量
    private static final String API_BUY_NUM = "cart/cart_count";

    //购物车列表
    private static final String API_BUY_CAR = "cart/index";


    private static final String API_EMPTY_INVALID = "cart/empty_invalid";

    private static final String API_CAR_DELETE = "cart/delete";

    //修改购物车
    private static final String API_CART_MODIFY = "cart/modify";

    //全选全取消
    private static final String API_SELECT_CART = "cart/select_cart";

    //加减提示
    private static final String API_CHECK_ITEM = "products/check_item";

    //购物车结算
    private static final String API_CAR_CONFIRM = "cart/confirm";

    //订单可用优惠券列表
    private static final String API_CART_CONPON_LIST = "cart/coupon_list";


    /**
     * //获取优惠券
     *
     * @param callback
     * @param tag
     */
    public static void getCartConponList(String products_id, String scenes, BaseCallback<BaseResponse<List<SettlementCouponBean>>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("scenes", scenes);
        params.put("products_id", products_id);
        ApiHttpClient.post(API_CART_CONPON_LIST, params, callback, tag);
    }


    /**
     * 购物车接口
     */
    public static void carConfirm(String user_coupon_id, String address_id, String ids, String products_id, String item_id, String products_num, String action, String is_refresh, BaseCallback<BaseResponse<SettlementCenterBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("user_coupon_id", user_coupon_id);
        params.put("address_id", address_id);
        params.put("ids", ids);
        params.put("products_id", products_id);
        params.put("item_id", item_id);
        params.put("products_num", products_num);
        params.put("action", action);
        params.put("is_refresh", is_refresh);
        ApiHttpClient.post(API_CAR_CONFIRM, params, callback, tag);
    }


    /**
     * 加减提示
     */
    public static void addAndSubtract(String id, String item_id, String nums, BaseCallback<BaseResponse<AddAndSubtractBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("item_id", item_id);
        params.put("nums", nums);
        ApiHttpClient.post(API_CHECK_ITEM, params, callback, tag);
    }


    /**
     * 全部选中或全部取消
     */
    public static void allSelectOrCancel(String selected, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("selected", selected);
        ApiHttpClient.post(API_SELECT_CART, params, callback, tag);
    }

    /**
     * 购物车修改
     *
     * @param callback
     * @param tag
     */
    public static void changeCar(String id, String products_num, String selected, String item_id, BaseCallback<BaseResponse<ModifyCarBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("products_num", products_num);
        params.put("selected", selected);
        params.put("item_id", item_id);
        ApiHttpClient.post(API_CART_MODIFY, params, callback, tag);
    }


    /**
     * 删除选中商品
     *
     * @param callback
     * @param tag
     */
    public static void deleteInvalid(String ids, BaseCallback<BaseResponse<ShopCarListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("ids", ids);
        ApiHttpClient.post(API_CAR_DELETE, params, callback, tag);
    }


    /**
     * 清空失效产品列表
     *
     * @param callback
     * @param tag
     */
    public static void clearInvalid(BaseCallback<BaseResponse<ShopCarListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_EMPTY_INVALID, params, callback, tag);
    }


    /**
     * 获取购物车列表
     *
     * @param callback
     * @param tag
     */
    public static void getBuyCarList(int page, BaseCallback<BaseResponse<ShopCarListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        ApiHttpClient.post(API_BUY_CAR, params, callback, tag);
    }


    /**
     * 获取购物车数量
     *
     * @param callback
     * @param tag
     */
    public static void getCarNum(BaseCallback<BaseResponse<Integer>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_BUY_NUM, params, callback, tag);
    }


    /**
     * 加入购物车
     *
     * @param products_id
     * @param products_num
     * @param item_id
     * @param callback
     * @param tag
     */
    public static void addBuyCar(String products_id, String products_num, String item_id, BaseCallback<BaseResponse<Integer>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("products_id", products_id);
        params.put("products_num", products_num);
        params.put("item_id", item_id);
        ApiHttpClient.post(API_ADD_BUY_CAR, params, callback, tag);
    }


    /**
     * 获取秒杀商品列表
     *
     * @param callback
     * @param tag
     */
    public static void getSpikeList(BaseCallback<BaseResponse<List<GoodsBean>>> callback, Object tag) {
        ApiHttpClient.post(API_GOODS_GET_SPIKE_LIST, callback, tag);
    }

    /**
     * 获取今日团购列表
     *
     * @param row      每页显示条数
     * @param page     页码
     * @param callback
     * @param tag
     */
    public static void getTodayList(int row, int page, BaseCallback<BaseResponse<GoodsListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("row", row + "");
        params.put("page", page + "");
        ApiHttpClient.post(API_GOODS_GET_TODAY_LIST, params, callback, tag);
    }

    //row
    //[string]		每页显示的条数	展开
    //page
    //[string]		页数	展开
    //keywords
    //[string]		搜索关键字	展开
    //cat_id
    //[string]		分类id	展开
    //sort
    //[string]		排序	展开
    //sort_asc
    //[string]		排序顺序

    /**
     * 获取全部的商品
     *
     * @param callback
     * @param tag
     */
    public static void getAllGoodsList(int row, int page, String keywords, int cat_id, String sort, boolean isPosOrNeg, BaseCallback<BaseResponse<AllGoodsListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("row", row + "");
        params.put("page", page + "");
        params.put("keywords", keywords);
        params.put("cat_id", cat_id + "");
        params.put("sort", sort);
        if (isPosOrNeg) {
            params.put("sort_asc", "asc");
        } else {
            params.put("sort_asc", "desc");
        }
        ApiHttpClient.post(API_PRODUCTS_INDEX, params, callback, tag);
    }

    /**
     * 获取商品详情页
     */
    public static void getProductsInfo(String id, BaseCallback<BaseResponse<GoodsDetialBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        ApiHttpClient.post(API_PRODUCTS_INFO, params, callback, tag);
    }


    /**
     * 获取商品规格页
     */
    public static void getSpecInfo(String id, BaseCallback<BaseResponse<GoodsSpecAllBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        ApiHttpClient.post(API_PRODUCTS_SPEC, params, callback, tag);
    }

    public static void getGoodsShareImg(String id, BitmapCallback callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        ApiHttpClient.getImg(API_PRODUCTS_PNG, params, callback, tag);
    }

}
