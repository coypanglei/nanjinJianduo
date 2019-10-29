package com.shaoyue.weizhegou.api.remote;

import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.diaocha.StartDiaochaBean;
import com.shaoyue.weizhegou.entity.diaocha.sxDiaoChaBean;

import java.util.HashMap;
import java.util.Map;

public class DiaoChaApi {

    //获取调查列表
    private static final String GET_DIAODCHA_LIST = "jeecg-boot/process/sxdcApprovalList";

    //授信调查-修改授信模型
    private static final String UPDATE_SXMX = "jeecg-boot/business/sxdc/updateSxmx";


    //获取模型的数据
    private static final String GET_MX_DATA = "jeecg-boot/business/sxdc/queryBasicBySxId";

    /**
     * 第一次开始查看模型数据
     */
    public static void getMxData(String sxid,BaseCallback<BaseResponse<StartDiaochaBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("sxid",sxid);
        ApiHttpClient.post(GET_MX_DATA, params, callback, tag);
    }


    /**
     * 授信调查-修改授信模型
     */
    public static void getUpdateMx(String id, String sxmx, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("sxmx", sxmx);
        ApiHttpClient.post(UPDATE_SXMX, params, callback, tag);
    }

    /**
     * 获取调查列表
     */
    public static void getDiaoChaList(int pageNum, String pageSize, String nameorid, String approvalFlag, String dczt
            , String lczt
            , BaseCallback<BaseResponse<sxDiaoChaBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNum + "");
        params.put("pageSize", pageSize);
        params.put("nameorid", nameorid);
        params.put("approvalFlag", approvalFlag);
        params.put("lckz", lczt);
        params.put("dczt", dczt);
        ApiHttpClient.post(GET_DIAODCHA_LIST, params, callback, tag);
    }

}
