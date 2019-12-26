package com.shaoyue.weizhegou.api.helper;

import android.graphics.Bitmap;

import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.request.DeleteRequest;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.PutRequest;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.manager.UserMgr;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bin on 17/6/4.
 */

public class ApiHttpClient {


    public static void post(String partUrl, Map<String, String> params, BaseCallback callback,
                            Object tag) {

        GetRequest postRequest = OkGo.get(getAbsoulteApiUrl(partUrl));


        postRequest.tag(tag);
        Map<String, String> postParamsHead = new HashMap<>();
        //        if (UserMgr.getInstance().isLogin()) {
//        LogUtils.e(UserMgr.getInstance().getSessionId());

        postParamsHead.put("X-Access-Token", UserMgr.getInstance().getSessionId());
        for (Map.Entry<String, String> entry : postParamsHead.entrySet()) {
            postRequest.headers(entry.getKey(), entry.getValue());
        }
        Map<String, String> postParams = getPostData(params);
        for (Map.Entry<String, String> entry : postParams.entrySet()) {
            postRequest.params(entry.getKey(), entry.getValue());
        }

        postRequest.execute(callback);
    }

    public static void detel(String partUrl, Map<String, String> params, BaseCallback callback,
                             Object tag) {

        DeleteRequest postRequest = OkGo.delete(getAbsoulteApiUrl(partUrl));


        postRequest.tag(tag);
        Map<String, String> postParamsHead = new HashMap<>();
        //        if (UserMgr.getInstance().isLogin()) {
//        LogUtils.e(UserMgr.getInstance().getSessionId());
        LogUtils.e(UserMgr.getInstance().getSessionId());
        postParamsHead.put("X-Access-Token", UserMgr.getInstance().getSessionId());
        for (Map.Entry<String, String> entry : postParamsHead.entrySet()) {
            postRequest.headers(entry.getKey(), entry.getValue());
        }
        Map<String, String> postParams = getPostData(params);
        for (Map.Entry<String, String> entry : postParams.entrySet()) {
            postRequest.params(entry.getKey(), entry.getValue());
        }

        postRequest.execute(callback);
    }

    public static void postJson(String partUrl, Map<String, String> params, BaseCallback callback,
                                Object tag) {

        PostRequest postRequest = OkGo.post(getAbsoulteApiUrl(partUrl));


        postRequest.tag(tag);
        Map<String, String> postParamsHead = new HashMap<>();
        //        if (UserMgr.getInstance().isLogin()) {
//        LogUtils.e(UserMgr.getInstance().getSessionId());
        postParamsHead.put("X-Access-Token", UserMgr.getInstance().getSessionId());
        for (Map.Entry<String, String> entry : postParamsHead.entrySet()) {
            postRequest.headers(entry.getKey(), entry.getValue());
        }
        Map<String, String> postParams = getPostData(params);
        JSONObject jsonObject = new JSONObject(postParams);
        postRequest.upJson(jsonObject);
        postRequest.execute(callback);
    }

    public static void putJson(String partUrl, Map<String, String> params, BaseCallback callback,
                               Object tag) {

        PutRequest postRequest = OkGo.put(getAbsoulteApiUrl(partUrl));


        postRequest.tag(tag);
        Map<String, String> postParamsHead = new HashMap<>();
        //        if (UserMgr.getInstance().isLogin()) {
//        LogUtils.e(UserMgr.getInstance().getSessionId());
        postParamsHead.put("X-Access-Token", UserMgr.getInstance().getSessionId());
        for (Map.Entry<String, String> entry : postParamsHead.entrySet()) {
            postRequest.headers(entry.getKey(), entry.getValue());
        }
        Map<String, String> postParams = getPostData(params);
        JSONObject jsonObject = new JSONObject(postParams);
        postRequest.upJson(jsonObject);
        postRequest.execute(callback);
    }


    /**
     * 获取图片
     *
     * @param partUrl
     * @param params
     * @param callback
     * @param tag
     */
    public static void getImg(String partUrl, Map<String, String> params, BitmapCallback callback, Object tag) {
        GetRequest getRequest = OkGo.<Bitmap>get(getAbsoulteApiUrl(partUrl));
        getRequest.tag(tag);
        Map<String, String> postParams = getPostData(params);
        for (Map.Entry<String, String> entry : postParams.entrySet()) {
            getRequest.params(entry.getKey(), entry.getValue());
        }
        getRequest.headers("X-Access-Token", UserMgr.getInstance().getSessionId());
        getRequest.execute(callback);
    }


    /**
     * 上传图片
     *
     * @param partUrl
     * @param callback
     * @param tag
     */
    public static void updateImg(String partUrl, Map<String, String> params, File mFile, BaseCallback callback, Object tag) {
        PostRequest getRequest = OkGo.<Bitmap>post(getAbsoulteApiUrl(partUrl));
        getRequest.tag(tag);

        Map<String, String> postParams = getPostData(params);
        for (Map.Entry<String, String> entry : postParams.entrySet()) {
            getRequest.params(entry.getKey(), entry.getValue());
        }
        getRequest.params("file", mFile);
        getRequest.headers("X-Access-Token", UserMgr.getInstance().getSessionId());
        getRequest.execute(callback);
    }

    public static void post(String partUrl, BaseCallback callback,
                            Object tag) {
        post(partUrl, null, callback, tag);
    }


    private static Map<String, String> getPostData(Map<String, String> params) {


        Map<String, String> postParams = new HashMap<>();

        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                postParams.put(entry.getKey(), entry.getValue());
            }
        }

//        StringBuilder sb = new StringBuilder();
//        Map<String, String> resultMap = ZMCommUtil.sortMapByKey(postParams, true);
//        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
//            if (!TextUtils.isEmpty(entry.getValue())) {
//                sb.append(entry.getKey() + "=" + EncodeUtils.urlEncode(entry.getValue()) + "&");
//            }
//        }
//        String paramStr = sb.toString();
//        paramStr = paramStr.substring(0, paramStr.length() - 1);


//        paramStr = "_device_os=iphone&_language=zh&_oem=default&_os_version=11.4&_prj_name=ferrari&_salt=2D9030CC57668B506581D2FF33795F5B&_session=9cb0e0c120528182073b88dd9ae7ef09&_sn=e75564c8acf2f7a4ab37d2d0f4443f01&_timestamp=1535959803&_uid=29&_username=e9cfcf618f1e5c79&_version=1&page=1&pre_page=20";

//
//        String signStr = EncryptUtils.encryptMD5ToString(paramStr + CommConfig.URL_DECODE_KEY).toLowerCase();
//        LogUtils.e("signStr: " + signStr);
//        postParams.put("_sign", signStr);
//        String parmData = new Gson().toJson(postParams);
//        LogUtils.e("param_data: " + parmData);
//        String postData = ZMCommUtil.mdEncrypt(parmData, CommConfig.URL_DECODE_KEY);
//        LogUtils.e("postData: " + postData);
//        postParams.put("_sign", signStr);
        return postParams;
    }


    /**
     * 根据相对URL获取绝对url
     *
     * @param partUrl
     * @return
     */
    private static String getAbsoulteApiUrl(String partUrl) {
        String allUrl = partUrl.startsWith("http") ? partUrl : DomainMgr.getInstance().getBaseUrl() + partUrl;
//        LogUtils.e(allUrl);
        return allUrl;
    }


}
