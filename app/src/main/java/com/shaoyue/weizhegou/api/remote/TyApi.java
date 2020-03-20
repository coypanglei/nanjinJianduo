package com.shaoyue.weizhegou.api.remote;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.JsonObject;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.cedit.HfwBean;
import com.shaoyue.weizhegou.entity.cedit.VideoBean;
import com.shaoyue.weizhegou.entity.cedit.VideoMaterialBean;
import com.shaoyue.weizhegou.manager.UserMgr;

import java.util.HashMap;
import java.util.Map;

public class TyApi {
    //贷后基本信息
    public static final String DH_JBXX = "jeecg-boot/dhjcmb/dhScdhjcJbxx/queryById";

    //编辑贷后基本信息
    private static final String DH_JBXX_EDIT = "jeecg-boot/dhjcmb/dhScdhjcJbxx/edit";

    //首贷结论
    private static final String DH_SD_JL = "jeecg-boot/dhjcmb/dhScdhjcJl/queryByPid";

    //首贷结论编辑
    private static final String DH_SD_EDIT = "jeecg-boot/dhjcmb/dhScdhjcJl/saveOrUpdate";

    //首贷添加影像资料
    private static final String SD_ADD_VIDEO = "jeecg-boot/dhjcmb/dhScdhjcYxzl/add";

    //个人贷款基本信息
    private static final String GD_JBXX = "jeecg-boot/dhjcmb/dhjcmbGrjyjbxx/queryById";

    //个贷基本信息编辑
    private static final String GD_JBXX_EDIT = "jeecg-boot/dhjcmb/dhjcmbGrjyjbxx/edit";

    //个人经营检查结果
    private static final String GD_JCJG = "jeecg-boot/dhjcmb/dhjcmbGrjyjcjg/queryByPId";

    //个人经营检查结果编辑
    private static final String GD_JCJG_EDIT = "jeecg-boot/dhjcmb/dhjcmbGrjyjcjg/saveOrUpdateForAndriod";


    //个贷汇法网编辑
    private static final String GD_HFW_EDIT = "jeecg-boot/dhjcmb/dhjcmbGrjyHfw/saveOrUpdate";

    //个贷汇法网
    private static final String GD_HFW = "jeecg-boot/dhjcmb/dhjcmbGrjyHfw/queryByPid";

    //个贷汇法网查询
    private static final String GD_HFW_CX = "jeecg-boot/dhjcmb/dhjcmbGrjyHfw/chaxun";

    //首贷影像资料列表
    private static final String SD_VIDEO_LIST = "jeecg-boot/dhjcmb/dhScdhjcYxzl/queryByPidForAndroid";

    //个贷影像资料
    private static final String GD_VIDEO_LIST = "jeecg-boot/dhjcmb/dhjcmbYxzl/queryByPidForAndroid";

    //个贷影像资料添加
    private static final String GD_VIDEO_ADD = "jeecg-boot/dhjcmb/dhjcmbYxzl/saveOrUpdate";


    //公贷贷影像资料
    private static final String DG_VIDEO_LIST = "jeecg-boot/dhjcmb/dhjcmbDgdkyxzl/queryByPidForAndroid";

    //个贷影像资料添加
    private static final String DG_VIDEO_ADD = "jeecg-boot/dhjcmb/dhjcmbDgdkyxzl/edit";

    //对公汇法网查询
    private static final String DG_HFW = "jeecg-boot/dhjcmb/dhjcmbDgdkHfw/queryByPid";

    //个贷汇法网查询
    private static final String DG_HFW_CX = "jeecg-boot/dhjcmb/dhjcmbDgdkHfw/chaxun";
    //对公汇法网编辑
    private static final String DG_HFW_EDIT = "jeecg-boot/dhjcmb/dhjcmbDgdkHfw/saveOrUpdate";

    //对公贷款基本信息
    private static final String DG_DK_JBXX = "jeecg-boot/dhjcmb/dhjcmbDgdk/queryById";

    //对公贷款基本信息修改
    private static final String DG_DK_EDIT = "jeecg-boot/dhjcmb/dhjcmbDgdk/edit";

    //对公贷款检查结果
    private static final String DG_DK_JL = "jeecg-boot/dhjcmb/dhjcmbDgdkjcjl/queryByPId";

    //对公贷款检查结果编辑
    private static final String DG_DK_JL_EDIT = "jeecg-boot/dhjcmb/dhjcmbDgdkjcjl/saveOrUpdate";

    //非财务分析
    private static final String DG_FCWFX = "jeecg-boot/dhjcmb/dhjcmbFcwfx/queryByPId";


    //非财务分析
    private static final String DG_FCWFX_EDIT = "jeecg-boot/dhjcmb/dhjcmbFcwfx/add";

    //财务分析添加
    public static final String CWFX_ADD = "jeecg-boot/dhjcmb/dhjcmbCwfx/add";

    //财务分析编辑
    public static final String CWFX_EDIT = "jeecg-boot/dhjcmb/dhjcmbCwfx/edit";


    //上传地址接口
    private static final String API_HAEDER_PIC = "jeecg-boot/sys/common/upload";




    /**
     * 统一基本信息
     *
     * @param callback
     * @param tag
     * @param type     不同类型调用不同的地址
     */
    public static void getTyJbinfo(String type, BaseCallback<BaseResponse<JsonObject>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        String address = "";
        switch (type) {
            case "个贷检查结果":
                address = GD_JCJG;
                break;
            case "贷后基本信息":
                address = DH_JBXX;
                break;
            case "首贷结论":
                address = DH_SD_JL;
                break;
            case "个贷基本信息":
                address = GD_JBXX;
                break;
            case "个贷汇法网查询":
                address = GD_HFW;
                break;
            case "对公贷款汇法网查询":
                address = DG_HFW;
                break;
            case "对公基本信息":
                address = DG_DK_JBXX;
                break;
            case "对公贷款检查结果":
                address = DG_DK_JL;
                break;
            case "对公非财务分析":
                address = DG_FCWFX;
            default:
                break;
        }
        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("pid", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        if (ObjectUtils.isNotEmpty(address)) {
            ApiHttpClient.post(address, params, callback, tag);
        }
    }


    /**
     * 编辑贷后基本信息
     *
     * @param callback
     * @param tag
     */
    public static void editTyINfo(String type, Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        String address = "";
        switch (type) {
            case "个贷检查结果":
                address = GD_JCJG_EDIT;
                break;
            case "贷后基本信息":
                address = DH_JBXX_EDIT;
                break;
            case "首贷结论":
                address = DH_SD_EDIT;
                break;
            case "个贷基本信息":
                address = GD_JBXX_EDIT;
                break;
            case "个贷汇法网查询":
                address = GD_HFW_EDIT;
                break;
            case "对公贷款汇法网查询":
                address = DG_HFW_EDIT;
                break;
            case "对公基本信息":
                address = DG_DK_EDIT;
                break;
            case "对公贷款检查结果":
                address = DG_DK_JL_EDIT;
                break;
            case "对公非财务分析":
                address = DG_FCWFX_EDIT;
                break;
            case "对公财务分析":
                address = CWFX_EDIT;
                break;
            case "抵质(押)分析":
                address ="jeecg-boot/business/sxsqDyfx/editAll";
                params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
                break;
            default:
                break;
        }

        params.put("pid", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        if (ObjectUtils.isNotEmpty(address)) {
            ApiHttpClient.putJson(address, params, callback, tag);
        }
    }

    /**
     * 汇法网信息-查询汇法网
     */
    public static void getHfWInfo(String type,BaseCallback<BaseResponse<HfwBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pid", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        String address = "";
        switch (type) {
            case "个贷汇法网查询":
                address = GD_HFW;
                break;
            case "对公贷款汇法网查询":
                address = DG_HFW;
                break;
            default:
                break;
        }
        ApiHttpClient.post(address, params, callback, tag);
    }

    /**
     * 汇法网信息-查询汇法网结果
     */
    public static void cxHfWInfo(String type,BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pid", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        String address = "";
        switch (type) {
            case "个贷汇法网查询":
                address = GD_HFW_CX;
                break;
            case "对公贷款汇法网查询":
                address = DG_HFW_CX;
                break;
            default:
                break;
        }
        ApiHttpClient.post(address, params, callback, tag);
    }
    /**
     * 编辑贷后基本信息
     *
     * @param callback
     * @param tag
     */
    public static void addTyINfo(String type, Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        String address = "";
        switch (type) {

            case "对公财务分析":
                address = CWFX_ADD;
                break;
            default:
                break;
        }
        params.put("pid", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        if (ObjectUtils.isNotEmpty(address)) {
            ApiHttpClient.postJson(address, params, callback, tag);
        }
    }

    /**
     * 添加音像资料
     *
     * @param callback
     * @param tag
     */
    public static void addVideo(String title, VideoMaterialBean.ListBean listBean, BaseCallback<BaseResponse<Void>> callback, Object tag) {

        String address = "";
        switch (title) {
            case "首贷检查":
                //更换接口
                address = SD_ADD_VIDEO;
                break;
            case "个贷检查":
                //更换接口
                address = GD_VIDEO_ADD;
                break;
            case "对公贷款检查":
                address = DG_VIDEO_ADD;
                break;
        }
        Map<String, String> params = new HashMap<>();
        params.put("zllx", listBean.getZllx());
        params.put("sxsfzh", listBean.getSxsfzh());
        params.put("zldz", listBean.getZldz());
        params.put("pid", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(address, params, callback, tag);
    }

    /**
     * 查看影音资料列表
     *
     * @param callback
     * @param tag
     */
    public static void getSdVideoDetailsList(String title, BaseCallback<BaseResponse<VideoBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();

        String address = "";
        switch (title) {
            case "首贷检查":
                //更换接口
                address = SD_VIDEO_LIST;
                break;
            case "个贷检查":
                //更换接口
                address = GD_VIDEO_LIST;
                break;
            case "对公贷款检查":
                address = DG_VIDEO_LIST;
                break;

        }
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("pid", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("pageSize", "1000000");
        ApiHttpClient.post(address, params, callback, tag);
    }

}
