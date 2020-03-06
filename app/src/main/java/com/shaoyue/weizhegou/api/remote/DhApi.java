package com.shaoyue.weizhegou.api.remote;

import com.blankj.utilcode.util.SPUtils;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.ZxcxListBean;
import com.shaoyue.weizhegou.entity.cedit.FamilyListBean;
import com.shaoyue.weizhegou.entity.cedit.MyHangBean;
import com.shaoyue.weizhegou.entity.cedit.SxspListBean;
import com.shaoyue.weizhegou.entity.cedit.VideoBean;
import com.shaoyue.weizhegou.entity.cedit.VideoMaterialBean;
import com.shaoyue.weizhegou.entity.cedit.XcjyListBean;
import com.shaoyue.weizhegou.entity.dhgl.CwfxListBean;
import com.shaoyue.weizhegou.entity.dhgl.DgInfoListBean;
import com.shaoyue.weizhegou.entity.dhgl.DhglInfoGetBean;
import com.shaoyue.weizhegou.entity.dhgl.GdInfoListBean;
import com.shaoyue.weizhegou.entity.dhgl.SdInfoListBean;
import com.shaoyue.weizhegou.entity.dhgl.XcfxBean;
import com.shaoyue.weizhegou.entity.dhgl.XcjyBBean;
import com.shaoyue.weizhegou.entity.dhgl.XcjyProgressListBean;
import com.shaoyue.weizhegou.entity.dhgl.XcjyZxcxBean;
import com.shaoyue.weizhegou.entity.dhgl.XjlBean;
import com.shaoyue.weizhegou.entity.dhgl.YunSpBean;
import com.shaoyue.weizhegou.manager.UserMgr;

import java.util.HashMap;
import java.util.Map;

public class DhApi {
    //现场检验列表
    private static final String GET_DH_XCJY = "jeecg-boot/business/dhglJjXcjydx/list";
    //现场检验认领列表
    private static final String GET_DH_XCJY_RL = "jeecg-boot/business/dhglJjXcjydx/rllist";

    //贷后管理基本信息
    private static final String INFO_DHGL_BY_ZJHM = "jeecg-boot/business/dhglJjKhjbxx/queryByZjhm";
    //贷后管理基本信息添加
    private static final String INFO_DHGL_ADD = "jeecg-boot/business/dhglJjKhjbxx/add";
    //贷后管理基本信息编辑
    private static final String INFO_DHGL_EDIT = "jeecg-boot/business/dhglJjKhjbxx/edit";

    //贷后管理-现场检验表
    private static final String INFO_BIAO_DHGL_BY_ZJHM = "jeecg-boot/business/dhglJjXcjy/queryByZjhm";

    //贷后管理-分析结论
    private static final String INFO_FXJL_DHGL_BY_ZJHM = "jeecg-boot/business/dhglJjXcfxjl/queryByZjhm";


    //贷后管理-现场检验表
    private static final String IINFO_BIAO_DHGL_ADD = "jeecg-boot/business/dhglJjXcjy/add";


    //贷后管理-现场检验表
    private static final String IINFO_BIAO_DHGL_EDIT = "jeecg-boot/business/dhglJjXcjy/edit";

    //影像资料列表
    private static final String XCJY_VIDEO_LIST = "jeecg-boot/business/dhglJjKhxcyxzl/listMobile";


    //影像资料添加
    private static final String XCJY_VIDEO_DETAILS_ADD = "jeecg-boot/business/dhglJjKhxcyxzl/add";

    //首贷影像资料删除
    public static final String XCJY_VIDEO_DETAILS_DETELE = "jeecg-boot/business/dhglJjKhxcyxzl/delete";
    //个贷影像资料删除
    public static final String GD_VIDEO_DETAILS_DETELE = "jeecg-boot/dhjcmb/dhjcmbYxzl/delete";

    //公贷影像资料删除

    public static final String DG_VIDEO_DETAILS_DETELE = "jeecg-boot/dhjcmb/dhjcmbDgdkyxzl/delete";

    //贷后影像资料删除
    public static final String DHSJ_VIDEO_DETAILS_DETELE = "jeecg-boot/dhjcmb/dhScdhjcYxzl/delete";


    //担保人影像资料删除
    public static final String DBR_VIDEO_DETAILS_DETELE = "jeecg-boot/business/dbryxzl/delete";

    //系统数据
    private static final String XCJY_XTSJ = "jeecg-boot/business/dhglJjKhjbxx/queryXtsjByZjhm";
    //家庭信息
    private static final String XCJY_FAMILYT_INFO = "jeecg-boot/business/dhglJjKhjbxx/queryJtxxByZjhm";

    //现金流
    private static final String XCJY_XJL = "jeecg-boot/business/sxdcXxjl/listMobile";

    //贷后-季检-现场分析结论-添加
    private static final String DHGL_FXJL_ADD = "jeecg-boot/business/dhglJjXcfxjl/add";

    //贷后-数据采集-添加
    private static final String DHGL_FXJL_SJCJ_ADD = "jeecg-boot/business/dhglJjCjfxjl/add";

    //贷后-数据采集-编辑
    private static final String DHGL_FXJL_SJCJ_EDIT = "jeecg-boot/business/dhglJjCjfxjl/edit";

    //贷后-季检-现场分析结论-编辑
    private static final String DHGL_FXJL_EDIT = "jeecg-boot/business/dhglJjXcfxjl/edit";

    //现场检验认领
    private static final String XCJY_RL = "jeecg-boot/business/dhglJjXcjydx/rl";

    private static final String XCJY_CKJD = "jeecg-boot/business/dhglJjLcls/list";
    //征信查询
    private static final String XCJY_ZXCX = "jeecg-boot/business/dhglJjZxjx/queryById";

    //授信征信查询
    private static final String XCJY_SXCX = "jeecg-boot/business/sxsqZxcx/list";


    //审批列表查询
    private static final String SP_LIST_CX = "jeecg-boot/business/dhglJjXcjydx/splist";


    //授信审批列表查询
    private static final String SX_SP_LIST_CX = "jeecg-boot/process/sxspApprovalList";
    //预审批
    private static final String SP_YUN = "jeecg-boot/business/dhglJjXcjydx/presp";


    //审批
    private static final String GO_SP = "jeecg-boot/business/dhglJjXcjydx/sp";

    //数据采集
    private static final String SJCJ_LIST = "jeecg-boot/business/dhglJjCjdx/list";

    //征信查询任务添加
    private static final String ZXCC_ADD = "jeecg-boot/zx/dhglJjZx/add";

    //征信查询任务添加
    private static final String SXCC_ADD = "jeecg-boot/business/sxsqZx/add";
    //征信查询任务重试
    private static final String SXCC_CS = "jeecg-boot/business/sxsqZx/reset";

    //征信查询任务添加
    private static final String DB_SXCC_ADD = "jeecg-boot/business/sxsqZx/dbAdd";
    //数据采集分析结论
    private static final String SJCJ_FXJL = "jeecg-boot/business/dhglJjCjfxjl/queryByZjhm";
    //取消认领
    private static final String CANCEL_RL = "jeecg-boot/business/dhglJjXcjydx/qxrlpl";

    //首次贷后检查基本信息-分页列表查询
    private static final String SDJC_FY = "jeecg-boot/dhjcmb/dhScdhjcJbxx/list";
    //个贷后检查基本信息-分页列表查询
    private static final String GDJC_FY = "jeecg-boot/dhjcmb/dhjcmbGrjyjbxx/list";
    //对公贷款检查基本信息-分页列表查询
    private static final String DGDK_FY = "jeecg-boot/dhjcmb/dhjcmbDgdk/list";

    //财务分析获取
    private static final String CWFX_GET = "jeecg-boot/dhjcmb/dhjcmbCwfx/queryByPId";





    /**
     * 对公贷款信息-财务分析
     */
    public static void getCwfxList(BaseCallback<BaseResponse<CwfxListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pid", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(CWFX_GET, params, callback, tag);
    }


    /**
     * 对公贷款信息-分页列表查询
     */
    public static void getDgJbList(int pageNum, String pageSize, String khmc
            , BaseCallback<BaseResponse<DgInfoListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        params.put("khmcOrzjhm", khmc);
        ApiHttpClient.post(DGDK_FY, params, callback, tag);
    }

    /**
     * 个贷信息-分页列表查询
     */
    public static void getGdJbList(int pageNum, String pageSize, String khmc
            , BaseCallback<BaseResponse<GdInfoListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        params.put("khmcOrzjhm", khmc);
        ApiHttpClient.post(GDJC_FY, params, callback, tag);
    }

    /**
     * 首次贷后检查基本信息-分页列表查询
     */
    public static void getSDJCSpList(int pageNum, String pageSize, String khmc
            , BaseCallback<BaseResponse<SdInfoListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        params.put("khmcOrzjhm", khmc);
        ApiHttpClient.post(SDJC_FY, params, callback, tag);
    }


    /**
     * 获取授信审批列表
     */
    public static void getSxSpList(int pageNum, String pageSize, String khmc
            , String lczt
            , BaseCallback<BaseResponse<SxspListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        params.put("khxm", khmc);
        params.put("lczt", lczt);
        ApiHttpClient.post(SX_SP_LIST_CX, params, callback, tag);
    }


    /**
     * 取消认领
     */


    public static void cancelRl(String id, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("ids", id);
        ApiHttpClient.post(CANCEL_RL, params, callback, tag);
    }


    /**
     * 添加分析结论
     *
     * @param callback
     * @param tag
     */
    public static void SjcjaddFxjl(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.postJson(DHGL_FXJL_SJCJ_ADD, params, callback, tag);
    }

    /**
     * 编辑分析结论
     *
     * @param callback
     * @param tag
     */
    public static void SjcjeditFxjl(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.putJson(DHGL_FXJL_SJCJ_EDIT, params, callback, tag);

    }

    /**
     * 查询分析结论通过zjhm(数据采集)
     *
     * @param callback
     * @param tag
     */
    public static void sjcjSearchFxjlByZjhm(BaseCallback<BaseResponse<XcfxBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(SJCJ_FXJL, params, callback, tag);
    }


    /**
     * 数据采集征信任务添加
     *
     * @param callback
     * @param tag
     */
    public static void xcjyZxcxAdd(BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(ZXCC_ADD, params, callback, tag);
    }


    /**
     * 获取现场检验列表
     */
    public static void getSjcjList(int pageNum, String pageSize, String khmc
            , String lczt
            , BaseCallback<BaseResponse<XcjyListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        params.put("khmc", khmc);
        params.put("zt", lczt);
        ApiHttpClient.post(SJCJ_LIST, params, callback, tag);
    }


    /**
     * 审批
     */

    public static void goSp(String id, String cjjg, String clyj, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("cljg", cjjg);
        params.put("clyj", clyj);
        ApiHttpClient.post(GO_SP, params, callback, tag);
    }


    /**
     * 预审批
     */

    public static void yuSp(String id, BaseCallback<BaseResponse<YunSpBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        ApiHttpClient.post(SP_YUN, params, callback, tag);
    }

    /**
     * 获取审批列表
     */
    public static void getSpList(int pageNum, String pageSize, String khmc
            , String lczt
            , BaseCallback<BaseResponse<XcjyListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        params.put("khhOrkhmc", khmc);
        params.put("lczt", lczt);
        ApiHttpClient.post(SP_LIST_CX, params, callback, tag);
    }

    /**
     * 授信征信查询
     *
     * @param callback
     * @param tag
     */
    public static void sxjyZxcx(String id, BaseCallback<BaseResponse<ZxcxListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("description", id);
        ApiHttpClient.post(XCJY_SXCX, params, callback, tag);

    }

    /**
     * 授信征信查询
     *
     * @param callback
     * @param tag
     */
    public static void sxjyZxcx(BaseCallback<BaseResponse<ZxcxListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(XCJY_SXCX, params, callback, tag);

    }

    /**
     * 添加征信查询按钮
     *
     * @param callback
     * @param tag
     */
    public static void addsqZxcx(BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(SXCC_ADD, params, callback, tag);

    }



    /**
     * 添加征信查询按钮
     *
     * @param callback
     * @param tag
     */
    public static void cssqZxcx(BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(SXCC_CS, params, callback, tag);

    }

    /**
     * 添加抵押担保征信查询按钮
     *
     * @param callback
     * @param tag
     */
    public static void adddbsqZxcx(String id, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("id", id);
        ApiHttpClient.post(DB_SXCC_ADD, params, callback, tag);

    }

    /**
     * 添加抵押担保征信查询按钮
     *
     * @param callback
     * @param tag
     */
    public static void csdbbsqZxcx(String id, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("dbid", id);
        ApiHttpClient.post(SXCC_CS, params, callback, tag);

    }

    /**
     * 现场征信查询
     *
     * @param callback
     * @param tag
     */
    public static void xcjyZxcx(BaseCallback<BaseResponse<XcjyZxcxBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(XCJY_ZXCX, params, callback, tag);
    }

    public static void xcjyRl(String ids, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("ids", ids);
        ApiHttpClient.post(XCJY_RL, params, callback, tag);
    }


    public static void xcjyProgress(BaseCallback<BaseResponse<XcjyProgressListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(XCJY_CKJD, params, callback, tag);
    }


    /**
     * 添加分析结论
     *
     * @param callback
     * @param tag
     */
    public static void addFxjl(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.postJson(DHGL_FXJL_ADD, params, callback, tag);
    }

    /**
     * 编辑分析结论
     *
     * @param callback
     * @param tag
     */
    public static void editFxjl(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.putJson(DHGL_FXJL_EDIT, params, callback, tag);

    }

    /**
     * 获取 信息
     *
     * @param callback
     * @param tag
     */
    public static void getXjlInfo(BaseCallback<BaseResponse<XjlBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
//        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(XCJY_XJL, params, callback, tag);
    }


    /**
     * 获取家庭信息
     *
     * @param callback
     * @param tag
     */
    public static void getFamilyInfo(int pageNum, String pageSize, BaseCallback<BaseResponse<FamilyListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
//        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        ApiHttpClient.post(XCJY_FAMILYT_INFO, params, callback, tag);
    }


    /**
     * 系统数据
     *
     * @param callback
     * @param tag
     */
    public static void lookInfo(Map<String, String> params, BaseCallback<BaseResponse<MyHangBean>> callback, Object tag) {
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(XCJY_XTSJ, params, callback, tag);

    }

    /**
     * 添加音像资料
     *
     * @param callback
     * @param tag
     */
    public static void deteleVideo(String address, String id, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.detel(address, params, callback, tag);
    }


    /**
     * 添加音像资料
     *
     * @param callback
     * @param tag
     */
    public static void addVideo(VideoMaterialBean.ListBean listBean, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("zllx", listBean.getZllx());
        params.put("sxsfzh", listBean.getSxsfzh());
        params.put("zldz", listBean.getZldz());
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(XCJY_VIDEO_DETAILS_ADD, params, callback, tag);
    }


    /**
     * 查看影音资料列表
     *
     * @param callback
     * @param tag
     */
    public static void getVideoDetailsList(BaseCallback<BaseResponse<VideoBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
//        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("pageSize", "1000000");
        ApiHttpClient.post(XCJY_VIDEO_LIST, params, callback, tag);
    }


    /**
     * 添加现场检验表信息
     *
     * @param callback
     * @param tag
     */
    public static void addXCJYB(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        ApiHttpClient.post(IINFO_BIAO_DHGL_ADD, params, callback, tag);

    }

    /**
     * 编辑现场检验表
     *
     * @param callback
     * @param tag
     */
    public static void editXCJYB(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        ApiHttpClient.putJson(IINFO_BIAO_DHGL_EDIT, params, callback, tag);

    }

    /**
     * 查询分析结论通过zjhm
     *
     * @param callback
     * @param tag
     */
    public static void searchFxjlByZjhm(BaseCallback<BaseResponse<XcfxBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(INFO_FXJL_DHGL_BY_ZJHM, params, callback, tag);
    }

    /**
     * 查询现场检验表通过zjhm
     *
     * @param callback
     * @param tag
     */
    public static void searchXcjyBByZjhm(BaseCallback<BaseResponse<XcjyBBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(INFO_BIAO_DHGL_BY_ZJHM, params, callback, tag);
    }


    /**
     * 添加基本信息
     *
     * @param callback
     * @param tag
     */
    public static void addInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(INFO_DHGL_ADD, params, callback, tag);
    }

    /**
     * 编辑我行数据
     *
     * @param callback
     * @param tag
     */
    public static void editMyData(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.putJson(INFO_DHGL_EDIT, params, callback, tag);

    }


    /**
     * 查询基本信息通过id
     *
     * @param callback
     * @param tag
     */
    public static void searchById(BaseCallback<BaseResponse<DhglInfoGetBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(INFO_DHGL_BY_ZJHM, params, callback, tag);
    }


    /**
     * 获取现场小组认领
     */
    public static void getXcjyRlList(int pageNum, String pageSize, String khmc
            , String lczt
            , BaseCallback<BaseResponse<XcjyListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        params.put("khhOrkhmc", khmc);
        params.put("lczt", lczt);
        ApiHttpClient.post(GET_DH_XCJY_RL, params, callback, tag);
    }

    /**
     * 获取现场检验列表
     */
    public static void getXcjyList(int pageNum, String pageSize, String khmc
            , String lczt
            , BaseCallback<BaseResponse<XcjyListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        params.put("khhOrkhmc", khmc);
        params.put("lczt", lczt);
        ApiHttpClient.post(GET_DH_XCJY, params, callback, tag);
    }
}
