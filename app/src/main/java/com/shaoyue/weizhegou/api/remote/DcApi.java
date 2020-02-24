package com.shaoyue.weizhegou.api.remote;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.JsonObject;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.diaocha.PjzbBean;
import com.shaoyue.weizhegou.manager.UserMgr;

import java.util.HashMap;
import java.util.Map;

public class DcApi {

    //评级指标
    private static final String PJZB = "jeecg-boot/business/pjmxZbpz/getPjmx";


    // 调查基本信息
    private static final String DC_JBINFO = "jeecg-boot/business/sxdcJbxx/queryById";

    //调查实地调查
    private static final String DC_SDDC = "jeecg-boot/business/sxdcSddc/queryById";

    //种植养殖信息
    private static final String DC_ZZ = "jeecg-boot/business/sxdcZzyzh/queryById";
    //添加基本信息

    private static final String INFO_ADD = "jeecg-boot/business/sxdcJbxx/add";

    //添加实地调查
    private static final String INFO_SDDC_ADD = "jeecg-boot/business/sxdcSddc/add";

    //编辑实地调查
    private static final String INFO_SDDC_EDIT = "jeecg-boot/business/sxdcSddc/edit";

    //编辑基本信息

    private static final String INFO_EDIT = "jeecg-boot/business/sxdcJbxx/edit";


    //暂存信息

    private static final String INFO_SAVE = "jeecg-boot/business/sxdcJbxx/saveOrUpdateHis";


    //添加种植养殖信息
    private static final String INFO_ZZ_ADD = "jeecg-boot/business/sxdcZzyzh/add";

    //编辑种植养殖信息
    private static final String INFO_ZZ_EDIT = "jeecg-boot/business/sxdcZzyzh/edit";

    //获取资产负债
    private static final String INFO_ZCFZ = "jeecg-boot/business/sxdcZcfz/queryById";


    //添加资产负债
    private static final String ZCFZ_ADD = "jeecg-boot/business/sxdcZcfz/add";
    //编辑资产负债
    private static final String ZCFZ_EDIT = "jeecg-boot/business/sxdcZcfz/edit";

    //获取损益简表
    private static final String SYJB_INFO = "jeecg-boot/business/sxdcSyjb/queryById";


    //获取财务简表
//sxdcCwjb
    private static final String CWJB_INFO = "jeecg-boot/business/sxdcCwjb/queryById";
    //添加财务简表
    private static final String CWJB_INFO_ADD = "jeecg-boot/business/sxdcCwjb/add";

    //编辑财务简表
    private static final String CWJB_EDIT = "jeecg-boot/business/sxdcCwjb/edit";

    //添加损益简表
    private static final String SYJB_ADD = "jeecg-boot/business/sxdcSyjb/add";

    //获取年收入
    private static final String NSR_INFO = "jeecg-boot/business/sxdcNsrqk/queryById";

    //添加年收入
    private static final String NSR_ADD = "jeecg-boot/business/sxdcNsrqk/add";

    //编辑年收入
    private static final String NSR_EDIT = "jeecg-boot/business/sxdcNsrqk/edit";


    //编辑资产负债

    private static final String SYJB_EDIT = "jeecg-boot/business/sxdcSyjb/edit";    //获取财务简表
    //sxdcCwjb
    private static final String SXDC_INFO = "jeecg-boot/business/sxdcSxjl/queryById";

    //添加损益简表
    private static final String SXDC_ADD = "jeecg-boot/business/sxdcSxjl/add";

    //编辑资产负债
    private static final String SXDC_EDIT = "jeecg-boot/business/sxdcSxjl/edit";

    //贷后基本信息
    public static final String DH_JBXX = "jeecg-boot/dhjcmb/dhScdhjcJbxx/queryById";

    //编辑贷后基本信息
    private static final String DH_JBXX_EDIT = "jeecg-boot/dhjcmb/dhScdhjcJbxx/edit";

    /**
     * 授信结论
     *
     * @param callback
     * @param tag
     */
    public static void getSxjlInfo(BaseCallback<BaseResponse<JsonObject>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(SXDC_INFO, params, callback, tag);
    }

    /**
     * 编辑授信结论
     *
     * @param callback
     * @param tag
     */
    public static void editSxjlInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(SXDC_EDIT, params, callback, tag);

    }

    /**
     * 添加授信结论
     *
     * @param callback
     * @param tag
     */
    public static void addSxjlInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(SXDC_ADD, params, callback, tag);

    }

    /**
     * 财务简表
     *
     * @param callback
     * @param tag
     */
    public static void getCwjbInfo(BaseCallback<BaseResponse<JsonObject>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(CWJB_INFO, params, callback, tag);
    }

    /**
     * 编辑财务简表
     *
     * @param callback
     * @param tag
     */
    public static void editCwInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(CWJB_EDIT, params, callback, tag);

    }

    /**
     * 添加财务简表
     *
     * @param callback
     * @param tag
     */
    public static void addCwjbInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(CWJB_INFO_ADD, params, callback, tag);

    }

    /**
     * 编辑种植信息
     *
     * @param callback
     * @param tag
     */
    public static void editZcfzInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(ZCFZ_EDIT, params, callback, tag);

    }

    /**
     * 添加资产负债
     *
     * @param callback
     * @param tag
     */
    public static void addZcfz(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(ZCFZ_ADD, params, callback, tag);
    }

    /**
     * 调查实地调查
     *
     * @param callback
     * @param tag
     */
    public static void getZcfzInfo(BaseCallback<BaseResponse<JsonObject>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(INFO_ZCFZ, params, callback, tag);
    }

    /**
     * 损益简表
     *
     * @param callback
     * @param tag
     */
    public static void getSyjbInfo(BaseCallback<BaseResponse<JsonObject>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(SYJB_INFO, params, callback, tag);
    }


    /**
     * 获取年收入
     *
     * @param callback
     * @param tag
     */
    public static void getNsrInfo(BaseCallback<BaseResponse<JsonObject>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(NSR_INFO, params, callback, tag);
    }


    /**
     * 添加年收入
     *
     * @param callback
     * @param tag
     */
    public static void addnsrINfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(NSR_ADD, params, callback, tag);
    }


    /**
     * 编辑年收入
     *
     * @param callback
     * @param tag
     */
    public static void editnsrINfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(NSR_EDIT, params, callback, tag);

    }

    /**
     * 添加损益简表
     *
     * @param callback
     * @param tag
     */
    public static void addSyjbINfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(SYJB_ADD, params, callback, tag);

    }

    /**
     * 编辑种植信息
     *
     * @param callback
     * @param tag
     */
    public static void editSyjbINfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(SYJB_EDIT, params, callback, tag);

    }

    /**
     * 编辑种植信息
     *
     * @param callback
     * @param tag
     */
    public static void editzzINfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(INFO_ZZ_EDIT, params, callback, tag);

    }

    /**
     * 添加种植信息
     *
     * @param callback
     * @param tag
     */
    public static void addzzInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(INFO_ZZ_ADD, params, callback, tag);
    }

    /**
     * 调查实地调查
     *
     * @param callback
     * @param tag
     */
    public static void getzzinfo(BaseCallback<BaseResponse<JsonObject>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(DC_ZZ, params, callback, tag);
    }

    /**
     * 暂存基本信息
     *
     * @param callback
     * @param tag
     */
    public static void zancunInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(INFO_SAVE, params, callback, tag);

    }

    /**
     * 编辑实地调查信息
     *
     * @param callback
     * @param tag
     */
    public static void editsddcINfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(INFO_SDDC_EDIT, params, callback, tag);

    }

    /**
     * 添加实地调查信息
     *
     * @param callback
     * @param tag
     */
    public static void addsddcInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(INFO_SDDC_ADD, params, callback, tag);
    }


    /**
     * 编辑基本信息
     *
     * @param callback
     * @param tag
     */
    public static void editINfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(INFO_EDIT, params, callback, tag);

    }


    /**
     * 编辑贷后基本信息
     *
     * @param callback
     * @param tag
     */
    public static void editdhINfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
//        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(DH_JBXX_EDIT, params, callback, tag);

    }

    /**
     * 添加基本信息
     *
     * @param callback
     * @param tag
     */
    public static void addInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(INFO_ADD, params, callback, tag);
    }

    /**
     * 调查基本信息
     *
     * @param callback
     * @param tag
     */
    public static void getJbinfo(BaseCallback<BaseResponse<JsonObject>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(DC_JBINFO, params, callback, tag);
    }

    /**
     * 统一基本信息
     *
     * @param callback
     * @param tag
     */
    public static void getTyJbinfo(String addresss, BaseCallback<BaseResponse<JsonObject>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(addresss, params, callback, tag);
    }


    /**
     * 调查实地调查
     *
     * @param callback
     * @param tag
     */
    public static void getdcSddcinfo(BaseCallback<BaseResponse<JsonObject>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(DC_SDDC, params, callback, tag);
    }

    /**
     * 评级指标
     *
     * @param callback
     * @param tag
     */
    public static void getPjzb(BaseCallback<BaseResponse<PjzbBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(PJZB, params, callback, tag);
    }

}
