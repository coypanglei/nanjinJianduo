package com.shaoyue.weizhegou.api.remote;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.cedit.ApplicationListBean;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.DiyaDanBaoListBean;
import com.shaoyue.weizhegou.entity.cedit.FaceBean;
import com.shaoyue.weizhegou.entity.cedit.FamilyListBean;
import com.shaoyue.weizhegou.entity.cedit.GongsiDanbao;
import com.shaoyue.weizhegou.entity.cedit.InfoGetBean;
import com.shaoyue.weizhegou.entity.cedit.MyHangBean;
import com.shaoyue.weizhegou.entity.cedit.ProgressBean;
import com.shaoyue.weizhegou.entity.cedit.QiYeDanBaoBean;
import com.shaoyue.weizhegou.entity.cedit.QianziBean;
import com.shaoyue.weizhegou.entity.cedit.TiJiaoBean;
import com.shaoyue.weizhegou.entity.cedit.VideoBean;
import com.shaoyue.weizhegou.entity.cedit.VideoMaterialBean;
import com.shaoyue.weizhegou.entity.cedit.ZiRanDanBaoListBean;
import com.shaoyue.weizhegou.entity.cedit.applyBean;
import com.shaoyue.weizhegou.entity.diaocha.AddressSelectBean;
import com.shaoyue.weizhegou.entity.user.MenuBean;
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

    //人脸识别信息查询
    private static final String FACE_FIND = "jeecg-boot/business/sxsqJtxx/authJtxx";

    //人脸识别信息查询抵押
    private static final String FACE_FIND_DY = "jeecg-boot/business/sxsqDbrxx/authJtxx";

    //人脸识别
    private static final String FACE_OK = "jeecg-boot/business/sxsqRlsb/auth";

    //上传征信授权书签名
    private static final String UPDATE_QIANMING = "jeecg-boot/business/sxsqCxsqs/add";


    //编辑授信人签名
    private static final String EDUT_QINAMING = "jeecg-boot/business/sxsqCxsqs/edit";


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

//        params.put("token", UserMgr.getInstance().getSessionId());
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
     * 编辑我行数据
     *
     * @param callback
     * @param tag
     */
    public static void editMyData(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        ApiHttpClient.putJson(MY_DATA_EDIT, params, callback, tag);

    }

    /**
     * 添加基本信息
     *
     * @param callback
     * @param tag
     */
    public static void addInfo(Map<String, String> params, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        ApiHttpClient.post(INFO_ADD, params, callback, tag);
    }

    /**
     * 我行数据
     *
     * @param callback
     * @param tag
     */
    public static void lookInfo(Map<String, String> params, BaseCallback<BaseResponse<MyHangBean>> callback, Object tag) {
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
        ApiHttpClient.putJson(INFO_EDIT, params, callback, tag);

    }

    /**
     * 查询基本信息通过id
     *
     * @param callback
     * @param tag
     */
    public static void searchById(BaseCallback<BaseResponse<InfoGetBean>> callback, Object tag) {
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
    public static void creditApplication(String xm, String sfzh, String zjdqr, String zjdz, BaseCallback<BaseResponse<applyBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
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
        params.put("id", id);
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
