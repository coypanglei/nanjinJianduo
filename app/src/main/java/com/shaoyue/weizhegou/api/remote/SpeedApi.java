package com.shaoyue.weizhegou.api.remote;


import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.ContinentBean;
import com.shaoyue.weizhegou.entity.LineBean;
import com.shaoyue.weizhegou.entity.LineList;
import com.shaoyue.weizhegou.entity.UseRecordList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpeedApi {

    // 大洲
    private static final String API_FETCH_CONTINENT = "api/server/get_continent";

    private static final String API_FETCH_LINE_LIST = "api/server/get_line";

    private static final String API_REPORT_USE = "api/system/report_use";

    private static final String API_RECORD_USE = "api/server/use_record";

    private static final String API_FETCH_RAND_LINE = "api/server/get_rand_line";


    public static void fetchContinent(BaseCallback<BaseResponse<List<ContinentBean>>> callback, Object tag) {
        ApiHttpClient.post(API_FETCH_CONTINENT, callback, tag);
    }

    public static void fetchLineList(int pageNum, int pageSize, BaseCallback<BaseResponse<LineList>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("page", pageNum + "");
        params.put("pre_page", pageSize + "");
        params.put("agreement", "sovpn");
        params.put("type", "new");
        ApiHttpClient.post(API_FETCH_LINE_LIST, params, callback, tag);
    }

    public static void reportUse(String srvName, String lineType, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("srv_name", srvName);
        params.put("line_type", lineType);
        ApiHttpClient.post(API_REPORT_USE, params, callback, tag);
    }

    public static void fetchUseRecord(int pageNum, int pageSize,
                                      BaseCallback<BaseResponse<UseRecordList>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("page", pageNum + "");
        params.put("pre_page", pageSize + "");
        ApiHttpClient.post(API_RECORD_USE, params, callback, tag);
    }


    public static void fetchRandLine(BaseCallback<BaseResponse<LineBean>> callback, Object tag) {
        ApiHttpClient.post(API_FETCH_RAND_LINE, callback, tag);
    }


}
