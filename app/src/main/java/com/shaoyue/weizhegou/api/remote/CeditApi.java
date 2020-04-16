package com.shaoyue.weizhegou.api.remote;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.JsonObject;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.cedit.ApplicationListBean;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.ChuShenBean;
import com.shaoyue.weizhegou.entity.cedit.DiyaDanBaoListBean;
import com.shaoyue.weizhegou.entity.cedit.FaceBean;
import com.shaoyue.weizhegou.entity.cedit.FamilyListBean;
import com.shaoyue.weizhegou.entity.cedit.GongsiDanbao;
import com.shaoyue.weizhegou.entity.cedit.HfwBean;
import com.shaoyue.weizhegou.entity.cedit.InfoChangeBean;
import com.shaoyue.weizhegou.entity.cedit.MyHangBean;
import com.shaoyue.weizhegou.entity.cedit.ProgressBean;
import com.shaoyue.weizhegou.entity.cedit.QiYeDanBaoBean;
import com.shaoyue.weizhegou.entity.cedit.QianziBean;
import com.shaoyue.weizhegou.entity.cedit.SxykhListBean;
import com.shaoyue.weizhegou.entity.cedit.TiJiaoBean;
import com.shaoyue.weizhegou.entity.cedit.VideoBean;
import com.shaoyue.weizhegou.entity.cedit.VideoMaterialBean;
import com.shaoyue.weizhegou.entity.cedit.XtPerssionBean;
import com.shaoyue.weizhegou.entity.cedit.ZiRanDanBaoListBean;
import com.shaoyue.weizhegou.entity.cedit.applyBean;
import com.shaoyue.weizhegou.entity.dhgl.XjlBean;
import com.shaoyue.weizhegou.entity.diaocha.AddressSelectBean;
import com.shaoyue.weizhegou.entity.user.MenuBean;
import com.shaoyue.weizhegou.entity.user.SxsqDanBean;
import com.shaoyue.weizhegou.manager.UserMgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CeditApi {

    //权限控制
    private static final String USER_ADDRESS_SAVE = "jeecg-boot/sys/permission/getUserPermissionByTokenMobile";

    //获取申请列表
    private static final String GET_APPLICATION_LIST = "jeecg-boot/process/sxsqApprovalList";

    //取消申请
    private static final String DETEL_PROCESS_INSTANCE = "jeecg-boot/process/deleteProcessInstance";

    //获取进度列表数据
    private static final String GET_PROCESS_HISTORT = "jeecg-boot/process/getProcessHistortAndAttachment";

    //授信申请
    private static final String GET_ADD_MOBILE = "jeecg-boot/business/sxsq/addMobile";


    //获取三级列表
    private static final String GET_SANJI_LIST = "jeecg-boot/business/mxglHymx/queryTreeList";


    //家庭信息
    private static final String FAMILY_INFO = "jeecg-boot/business/sxsqJtxx/list";

    //删除家庭信息
    private static final String DETEL_FAMILY_INFO = "jeecg-boot/business/sxsqJtxx/delete";

    //添加家庭信息
    private static final String ADD_FAMILY_INFO = "jeecg-boot/business/sxsqJtxx/add";


    //编辑家庭信息
    private static final String EDIT_FAMILY_INFO = "jeecg-boot/business/sxsqJtxx/edit";

    //查询家庭信息
    private static final String FIND_FAMILY_INFO = "jeecg-boot/business/sxsqJtxx/queryJtxx";

    //担保人家庭信息
    private static final String DB_FIND_FAMILY_INFO = "jeecg-boot/business/sxsqDbrxx/queryJtxx";

    //人脸识别信息查询
    private static final String FACE_FIND = "jeecg-boot/business/sxsqJtxx/authJtxx";

    //基本信息弹框
    private static final String INFO_CHAGE = "jeecg-boot/business/sxsqJbxx/getYsxed";

    //人脸识别信息查询抵押
    private static final String FACE_FIND_DY = "jeecg-boot/business/sxsqDbrxx/authJtxx";

    //人脸识别
    private static final String FACE_OK = "jeecg-boot/business/sxsqRlsb/auth";

    //上传征信授权书签名
    private static final String UPDATE_QIANMING = "jeecg-boot/business/sxsqCxsqs/add";


    //编辑授信人签名
    private static final String EDUT_QINAMING = "jeecg-boot/business/sxsqCxsqs/edit";


    //人脸验证
    private static final String SXSQRLSB_AUTHRLSB = "jeecg-boot/business/sxsqRlsb/authRlsb";

    //影像资料列表
    private static final String VIDEO_DETAILS_LIST = "jeecg-boot/business/sxsqYxzl/listMobile";

    //影像资料添加
    private static final String VIDEO_DETAILS_ADD = "jeecg-boot/business/sxsqYxzl/add";

    //影像资料删除
    private static final String VIDEO_DETAILS_DETELE = "jeecg-boot/business/sxsqYxzl/delete";

    //获取可配置参数
    private static final String INFO_LISTALL = "jeecg-boot/parmedit/sysParmEdit/listall";


    //获取授信基本信息
    private static final String INFO_BY_IDSTRING = "jeecg-boot/business/sxsqJbxx/queryById";


    //暂存基本信息
    private static final String INFO_SAVEORUPDATEHIS = "jeecg-boot/business/sxsqJbxx/saveOrUpdateHis";


    //添加基本信息

    private static final String INFO_ADD = "jeecg-boot/business/sxsqJbxx/add";

    //编辑基本信息

    private static final String INFO_EDIT = "jeecg-boot/business/sxsqJbxx/edit";


    //获取我行数据
    private static final String MY_DATA = "jeecg-boot/business/sxsqWhkhxx/list";
    //修改我行数据
    private static final String MY_DATA_EDIT = "jeecg-boot/business/sxsqWhkhxx/editSys";
    //授信申请单
    private static final String SX_SQ_DAN = "jeecg-boot/business/sxsqSxsqd/queryById";

    //保存征信查询
    private static final String SAVE_ZXCX = "jeecg-boot/business/sxsqZxcx/editPl";


    /**
     * 自然人担保分析
     */
    public static final String DANBAO_FENXI_ZIRAN = "jeecg-boot/business/sxsqDbrxx/list";

    /**
     * 抵押担保分析
     */
    public static final String DANBAO_FENXI_DIYA = "jeecg-boot/business/sxsqDyfx/list";

    /**
     * 公司担保分析
     */
    public static final String DANBAO_GONGSI = "jeecg-boot/business/sxsqDbgsdbfx/list";

    /**
     * 企业担保分析
     */
    public static final String DANBAO_QIYE = "jeecg-boot/business/sxsqQydbfx/list";


    //删除担保人信息
    public static final String DANBAOREN_DETEL = "jeecg-boot/business/sxsqDbrxx/delete";

    //删除担保人信息
    public static final String DANBAOREN_DETEL_SXSQDYFX = "jeecg-boot/business/sxsqDyfx/delete";

    //删除公司担保信息
    public static final String DANBAOREN_DETEL_GONGSI = "jeecg-boot/business/sxsqDbgsdbfx/delete";


    //删除企业担保人信息
    public static final String DANBAOREN_DETEL_QIYE = "jeecg-boot/business/sxsqQydbfx/delete";


    //新增担保人信息
    public static final String DANBAOREN_ADD = "jeecg-boot/business/sxsqDbrxx/add";
    /**
     * 上游客户
     */
    public static final String SXYKH_SY = "jeecg-boot/business/sxdcSygys/list";
    /**
     * 下游客户
     */
    public static final String XYKH_SY = "jeecg-boot/business/sxdcXykh/list";
    //删除下游客户信息
    public static final String XYKH_DELETE = "jeecg-boot/business/sxdcXykh/delete";


    //删除上游客户信息
    public static final String SYKH_DELETE = "jeecg-boot/business/sxdcSygys/delete";

    //新增下游客户信息
    public static final String XYKH_ADD = "jeecg-boot/business/sxdcXykh/add";
    //新增上游游客户信息
    public static final String SYKH_ADD = "jeecg-boot/business/sxdcSygys/add";
    //修改下游客户信息
    public static final String SYKH_EDIT = "jeecg-boot/business/sxdcSygys/edit";
    //修改下游客户
    public static final String XYKH_EDIT = "jeecg-boot/business/sxdcXykh/edit";
    //授信申请单添加
    public static final String SXSQD_ADD = "jeecg-boot/business/sxsqSxsqd/add";

    //授信申请单编辑
    public static final String SXSQD_EDIT = "jeecg-boot/business/sxsqSxsqd/edit";


    //新增公司担保信息
    public static final String DANBAOREN_ADD_GONGSI = "jeecg-boot/business/sxsqDbgsdbfx/add";
    //修改公司担保信息
    public static final String DANBAOREN_EDIT_GONGSI = "jeecg-boot/business/sxsqDbgsdbfx/edit";


    //新增企业担保信息
    public static final String DANBAOREN_ADD_QIYE = "jeecg-boot/business/sxsqQydbfx/add";
    //修改企业担保信息
    public static final String DANBAOREN_EDIT_QIYE = "jeecg-boot/business/sxsqQydbfx/edit";

    //新增抵押人信息
    public static final String DANBAODIYA_ADD = "jeecg-boot/business/sxsqDyfx/add";

    //修改担保人信息
    public static final String DANBAOREN_EDIT = "jeecg-boot/business/sxsqDbrxx/edit";

    //修改质押人信息
    public static final String DANBAOZHIYA_EDIT = "jeecg-boot/business/sxsqDyfx/edit";

    //提交申请判断
    public static final String DANBAOSQ_PANDUAN = "jeecg-boot/process/getConfigurationForAndriod";

    //发起流程
    public static final String INITIATION_PROCESS = "jeecg-boot/process/startProcess";

    //发起流程
    public static final String LCSP_PROCESS = "jeecg-boot/process/approval";

    //权限管理
    private static final String PERMISSION_GET_MENU = "jeecg-boot/sys/permission/getUserShouyeByToken";


    //现金流
    private static final String XCJY_XJL = "jeecg-boot/business/sxdcXxjl/listMobile";
    // 初审结果
    private static final String CSJG_CX = "jeecg-boot/business/sxsqCsjg/list";

    //现金流编辑
    private static final String XJLBJ = "jeecg-boot/business/sxdcXjlpl/saveOrUpdate";


    //担保人影像资料 获取
    private static final String DBR_YXZL = "jeecg-boot/business/dbryxzl/getByDbidForAndriod";

    //担保人影像资料 添加

    private static final String DBR_YXZL_ADD = "jeecg-boot/business/dbryxzl/saveOrUpdate";

    //汇法网获取
    private static final String HFW_CHAXUN_LIST = "jeecg-boot/business/sxsqHfwxx/list";

    //汇法网查询
    private static final String HFW_CHAXUN = "jeecg-boot/business/sxsqHfwxx/chaxun";


    //授信同步
    private static final String SX_TB = "jeecg-boot/xdtb/xdtb/syncById";


    /**
     * 同步
     */
    public static void tbInfo(String sxid, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", sxid);
        ApiHttpClient.post(SX_TB, params, callback, tag);
    }


    /**
     * 汇法网信息-查询汇法网
     */
    public static void cxHfWInfo(BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(HFW_CHAXUN, params, callback, tag);
    }

    /**
     * 汇法网信息-查询汇法网结果
     */
    public static void getHfWInfo(BaseCallback<BaseResponse<HfwBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(HFW_CHAXUN_LIST, params, callback, tag);
    }


    /**
     * 添加音像资料
     *
     * @param callback
     * @param tag
     */
    public static void addVideo(String title, VideoMaterialBean.ListBean listBean, BaseCallback<BaseResponse<Void>> callback, Object tag) {


        Map<String, String> params = new HashMap<>();
        params.put("zllx", listBean.getZllx());
        params.put("sxsfzh", listBean.getSxsfzh());
        params.put("zldz", listBean.getZldz());
        params.put("pid", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("dbid", title);
        ApiHttpClient.postJson(DBR_YXZL_ADD, params, callback, tag);
    }

    /**
     * 查看影音资料列表
     *
     * @param callback
     * @param tag
     */
    public static void getSdVideoDetailsList(String id, BaseCallback<BaseResponse<VideoBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("dbid", id);
        params.put("pageSize", "1000000");
        ApiHttpClient.post(DBR_YXZL, params, callback, tag);
    }

    /**
     * 编辑现金流
     *
     * @param params
     * @param callback
     * @param tag
     */
    public static void editXjl(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(XJLBJ, params, callback, tag);
    }

    /**
     * 初审结果
     *
     * @param callback
     * @param tag
     */
    public static void getCsjg(BaseCallback<BaseResponse<ChuShenBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(CSJG_CX, params, callback, tag);
    }

    /**
     * 获取授信申请单
     *
     * @param callback
     * @param tag
     */
    public static void getSxsqDan(BaseCallback<BaseResponse<SxsqDanBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(SX_SQ_DAN, params, callback, tag);
    }


    /**
     * 获取现金流信息
     *
     * @param callback
     * @param tag
     */
    public static void getXjlInfo(BaseCallback<BaseResponse<XjlBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
//        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("zjhm", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("jcjd", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(XCJY_XJL, params, callback, tag);
    }

    /**
     * 人脸识别验证
     *
     * @param callback
     * @param tag
     */
    public static void putRljy(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(SXSQRLSB_AUTHRLSB, params, callback, tag);
    }

    /**
     * 发起审批流程
     *
     * @param callback
     * @param tag
     */
    public static void putSplc(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("taskId", SPUtils.getInstance().getString(UserMgr.SP_DC_TASKID));
        params.put("instid", SPUtils.getInstance().getString(UserMgr.SP_DC_INSTID));
        params.put("sxsqid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(LCSP_PROCESS, params, callback, tag);


    }

    /**
     * 类型 用于获取权限btn
     *
     * @param callback
     * @param tag
     */
    public static void getPERMISSIONName(BaseCallback<BaseResponse<List<XtPerssionBean>>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("token", UserMgr.getInstance().getSessionId());
        ApiHttpClient.post(PERMISSION_GET_MENU, params, callback, tag);
    }


    /**
     * 获取地址三级列表
     */
    public static void getSanjiList(BaseCallback<BaseResponse<List<AddressSelectBean>>> callback, Object tag) {

        ApiHttpClient.post(GET_SANJI_LIST, callback, tag);
    }

    /**
     * 发起流程
     *
     * @param callback
     * @param tag
     */
    public static void putInitiationProcess(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxsqid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(INITIATION_PROCESS, params, callback, tag);
    }

    /**
     * 提交申请判断
     *
     * @param callback
     * @param tag
     */
    public static void putDanbaoInfo(BaseCallback<BaseResponse<TiJiaoBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxsqid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(DANBAOSQ_PANDUAN, params, callback, tag);
    }


    /**
     * 提交申请判断
     *
     * @param callback
     * @param tag
     */
    public static void putDanbaoInfo(String id, String instid, BaseCallback<BaseResponse<TiJiaoBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("taskid", id);
        params.put("instid", instid);
        params.put("sxsqid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(DANBAOSQ_PANDUAN, params, callback, tag);
    }

    /**
     * 编辑自然人担保
     *
     * @param callback
     * @param tag
     */
    public static void editDanbaoInfo(String address, Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(address, params, callback, tag);
    }

    /**
     * 新增自然人担保分析
     *
     * @param callback
     * @param tag
     */
    public static void addDanbaoInfo(String address, Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {

        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(address, params, callback, tag);
    }

    /**
     * 删除自然人担保分析
     *
     * @param callback
     * @param tag
     */
    public static void detelDanbaoInfo(String address, String id, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
//        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("id", id);
        ApiHttpClient.detel(address, params, callback, tag);
    }

    /**
     * 获取公司担保信息
     *
     * @param callback
     * @param tag
     */
    public static void getQiyeInfo(int pageNum, String pageSize, BaseCallback<BaseResponse<QiYeDanBaoBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        ApiHttpClient.post(DANBAO_QIYE, params, callback, tag);
    }


    /**
     * 获取公司担保信息
     *
     * @param callback
     * @param tag
     */
    public static void getGongsiInfo(int pageNum, String pageSize, BaseCallback<BaseResponse<GongsiDanbao>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        ApiHttpClient.post(DANBAO_GONGSI, params, callback, tag);
    }

    /**
     * 获取抵押担保信息
     *
     * @param callback
     * @param tag
     */
    public static void getDiyaInfo(int pageNum, String pageSize, BaseCallback<BaseResponse<DiyaDanBaoListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        ApiHttpClient.post(DANBAO_FENXI_DIYA, params, callback, tag);
    }


    /**
     * 获取上游信息
     *
     * @param callback
     * @param tag
     */
    public static void getSykhInfo(String address, int pageNum, String pageSize, BaseCallback<BaseResponse<SxykhListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        ApiHttpClient.post(address, params, callback, tag);
    }

    /**
     * 获取自然人担保信息
     *
     * @param callback
     * @param tag
     */
    public static void getZiRanInfo(int pageNum, String pageSize, BaseCallback<BaseResponse<ZiRanDanBaoListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        ApiHttpClient.post(DANBAO_FENXI_ZIRAN, params, callback, tag);
    }

    /**
     * 编辑授信申请单
     *
     * @param callback
     * @param tag
     */
    public static void editSxsqDan(String address, Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(address, params, callback, tag);
    }

    /**
     * 新增授信申请单
     *
     * @param callback
     * @param tag
     */
    public static void addSxsqDan(String address, Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {

        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(address, params, callback, tag);
    }


    /**
     * 保存征信数据
     *
     * @param callback
     * @param tag
     */
    public static void saveZxcx(String js,Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("js",js);
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(SAVE_ZXCX, params, callback, tag);

    }

    /**
     * 编辑我行数据
     *
     * @param callback
     * @param tag
     */
    public static void editMyData(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(MY_DATA_EDIT, params, callback, tag);

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
     * 我行数据
     *
     * @param callback
     * @param tag
     */
    public static void lookInfo(Map<String, String> params, BaseCallback<BaseResponse<MyHangBean>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(MY_DATA, params, callback, tag);

    }

    /**
     * 暂存基本信息
     *
     * @param callback
     * @param tag
     */
    public static void zancunInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(INFO_SAVEORUPDATEHIS, params, callback, tag);

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
     * 查询基本信息通过id
     *
     * @param callback
     * @param tag
     */
    public static void searchById(BaseCallback<BaseResponse<JsonObject>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.post(INFO_BY_IDSTRING, params, callback, tag);
    }

    /**
     * 获取可配置参数
     *
     * @param callback
     * @param tag
     */
    public static void getListAll(String type, String category, BaseCallback<BaseResponse<BasicInformationBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        if (ObjectUtils.isNotEmpty(category)) {
            params.put("category", category);
        }
        ApiHttpClient.post(INFO_LISTALL, params, callback, tag);
    }

    /**
     * 获取可配置参数
     *
     * @param callback
     * @param tag
     */
    public static void getListAll(String type, String category, String title, BaseCallback<BaseResponse<BasicInformationBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        if (ObjectUtils.isNotEmpty(category)) {
            params.put("category", category);
        }
        params.put("title", title);
        ApiHttpClient.post(INFO_LISTALL, params, callback, tag);
    }

    /**
     * 添加音像资料
     *
     * @param callback
     * @param tag
     */
    public static void deteleVideo(String id, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        ApiHttpClient.detel(VIDEO_DETAILS_DETELE, params, callback, tag);
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
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.postJson(VIDEO_DETAILS_ADD, params, callback, tag);
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
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("sxsfzh", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        params.put("pageSize", "1000000");
        ApiHttpClient.post(VIDEO_DETAILS_LIST, params, callback, tag);
    }


    /**
     * 上传征信授权书签名
     *
     * @param callback
     * @param tag
     */
    public static void editQianMing(Map<String, String> params, BaseCallback<BaseResponse<QianziBean>> callback, Object tag) {

        ApiHttpClient.putJson(EDUT_QINAMING, params, callback, tag);
    }

    /**
     * 上传征信授权书签名
     *
     * @param callback
     * @param tag
     */
    public static void updateQianMing(Map<String, String> params, BaseCallback<BaseResponse<QianziBean>> callback, Object tag) {

        ApiHttpClient.postJson(UPDATE_QIANMING, params, callback, tag);
    }


    /**
     * 人脸识别
     *
     * @param callback
     * @param tag
     */
    public static void faceOk(FaceBean faceBean, BaseCallback<BaseResponse<FaceBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("description", faceBean.getDescription());
        params.put("xb", faceBean.getXb());
        params.put("xm", faceBean.getXm());
        params.put("txdz", faceBean.getTxdz());
        params.put("id", faceBean.getId());
        params.put("sfzh", faceBean.getSfzh());
        params.put("jl", faceBean.getJl());
        params.put("js", faceBean.getJs());
        params.put("sxsfzh", faceBean.getSxsfzh());
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        ApiHttpClient.putJson(FACE_OK, params, callback, tag);
    }


    /**
     * 抵押人脸识别
     *
     * @param callback
     * @param tag
     */
    public static void findFaceInfo(String sfzh, BaseCallback<BaseResponse<List<FaceBean>>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
//        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("id", sfzh);
        ApiHttpClient.post(FACE_FIND_DY, params, callback, tag);
    }


    /**
     * 人脸识别
     *
     * @param callback
     * @param tag
     */
    public static void findFaceInfo(BaseCallback<BaseResponse<List<FaceBean>>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
//        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("sxsfzh", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(FACE_FIND, params, callback, tag);
    }

    /**
     * 基本信息修改
     *
     * @param callback
     * @param tag
     */
    public static void infoChange(BaseCallback<BaseResponse<InfoChangeBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
//        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("sfzh", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(INFO_CHAGE, params, callback, tag);
    }
    /**
     * 查询家庭信息
     *
     * @param callback
     * @param tag
     */
    public static void findFamilyInfo(BaseCallback<BaseResponse<List<QianziBean>>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
//        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("sxsfzh", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(FIND_FAMILY_INFO, params, callback, tag);
    }

    /**
     * 查询担保人家庭信息
     *
     * @param callback
     * @param tag
     */
    public static void findDbFamilyInfo(String id, BaseCallback<BaseResponse<List<QianziBean>>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("sxsfzh", SPUtils.getInstance().getString(UserMgr.SP_ID_CARD));
        ApiHttpClient.post(DB_FIND_FAMILY_INFO, params, callback, tag);
    }


    /**
     * 编辑家庭信息
     *
     * @param callback
     * @param tag
     */
    public static void editFamilyInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {

        ApiHttpClient.putJson(EDIT_FAMILY_INFO, params, callback, tag);
    }


    /**
     * 新增家庭信息
     *
     * @param callback
     * @param tag
     */
    public static void addFamilyInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {

        ApiHttpClient.postJson(ADD_FAMILY_INFO, params, callback, tag);
    }

    /**
     * 删除家庭信息
     *
     * @param callback
     * @param tag
     */
    public static void detelFamilyInfo(String id, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
//        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("id", id);
        ApiHttpClient.detel(DETEL_FAMILY_INFO, params, callback, tag);
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
        params.put("sxid", SPUtils.getInstance().getString(UserMgr.SP_APPLY_ID));
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        ApiHttpClient.post(FAMILY_INFO, params, callback, tag);
    }

    public static void getProcessHistort(String instid, BaseCallback<BaseResponse<List<ProgressBean>>> callback, Object tag) {

        Map<String, String> params = new HashMap<>();
//        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("instid", instid);
        ApiHttpClient.post(GET_PROCESS_HISTORT, params, callback, tag);
    }

    /**
     * 授信申请
     */
    public static void creditApplication(String xm, String sfzh, String zjdqr, String zjdz, String sfzm, String sffm, BaseCallback<BaseResponse<applyBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("zmdz", sfzm);
        params.put("fmdz", sffm);
        params.put("sfzh", sfzh);
        params.put("xm", xm);
        params.put("zjdqr", zjdqr);
        params.put("zjdz", zjdz);
        ApiHttpClient.post(GET_ADD_MOBILE, params, callback, tag);
    }


    /**
     * 取消申请
     *
     * @param callback
     * @param tag
     */
    public static void detelProcessInstance(String id, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
//        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("sxid", id);

        ApiHttpClient.detel(DETEL_PROCESS_INSTANCE, params, callback, tag);
    }


    /**
     * 类型 用于获取显示的btn
     *
     * @param sysName
     * @param callback
     * @param tag
     */
    public static void getTitleName(String sysName, BaseCallback<BaseResponse<MenuBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("token", UserMgr.getInstance().getSessionId());
        params.put("sysName", sysName);
        ApiHttpClient.post(USER_ADDRESS_SAVE, params, callback, tag);
    }


    /**
     * 获取申请列表
     */
    public static void getApplicationList(int pageNum, String pageSize, String nameorid, String approvalFlag
            , String lczt
            , BaseCallback<BaseResponse<ApplicationListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        params.put("nameorid", nameorid);
        params.put("approvalFlag", approvalFlag);
        params.put("lczt", lczt);
        ApiHttpClient.post(GET_APPLICATION_LIST, params, callback, tag);
    }


}
