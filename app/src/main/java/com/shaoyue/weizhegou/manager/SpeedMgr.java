package com.shaoyue.weizhegou.manager;

import android.text.TextUtils;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.SpeedApi;
import com.shaoyue.weizhegou.entity.LineBean;

public class SpeedMgr {

    private static final String SPEED_FILE_NAME = "speed";

    private static final String SP_LINE_INFO = "sp_cur_line_info";

    private static final String SP_SPEED_TYPE = "sp_speed_type";

    private static final String SP_SPEED_ALLOWED_PACKAGES = "sp_speed_allowed_packages";

    private long speedSessionId;

    private static SpeedMgr instance;

    private LineBean mLineInfo;

    private Integer mSpeedType;


    private SpeedMgr() {

    }

    public static SpeedMgr getInstance() {
        if (instance == null) {
            synchronized (SpeedMgr.class) {
                instance = new SpeedMgr();
            }
        }

        return instance;
    }


    public void fetchSpeedLines() {
//        SpeedApi.fetchContinent(new BaseCallback<BaseResponse<List<ContinentBean>>>() {
//            @Override
//            public void onSucc(BaseResponse<List<ContinentBean>> result) {
//                LogUtils.e(result.data);
//            }
//        }, this);

    }

    public void doConnectReport() {
        LineBean lineInfo = SpeedMgr.getInstance().getLineInfo();
        if (ObjectUtils.isEmpty(lineInfo)) {
            return;
        }
        SpeedApi.reportUse(lineInfo.getSn(), lineInfo.getSrvCode(), new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {

            }

            @Override
            public void onFail(ApiException apiError) {

            }
        }, this);
    }

    public void doDiconnectReport() {

    }


    public boolean setLineInfo(LineBean lineInfo) {
        if (lineInfo == null) {
            mLineInfo = null;
            SPUtils.getInstance().put(SP_LINE_INFO, "");
            return false;
        }
        if (TextUtils.isEmpty(lineInfo.getSrvPort())) {
            return false;
        }
        String[] portArray = lineInfo.getSrvPort().split(",");
        if (portArray.length == 0) {
            return false;
        }
        int soPort = 0;
        for (String item : portArray) {
            if (item.startsWith("so_vpn_udp:")) {
                int startIndex = item.indexOf(":");
                String portStr = item.substring(startIndex + 1);
                try {
                    soPort = Integer.parseInt(portStr);
                } catch (Exception e) {
                    return false;
                }
            }
        }

        if (soPort == 0) {
            return false;
        } else {
            lineInfo.setSoUdpPort(soPort);
        }

        mLineInfo = lineInfo;
        Gson gson = new Gson();
        String lineStr = gson.toJson(lineInfo);
        SPUtils.getInstance().put(SP_LINE_INFO, lineStr);
        return true;
    }

    public LineBean getLineInfo() {
        if (mLineInfo == null) {
            Gson gson = new Gson();
            String lineStr = SPUtils.getInstance().getString(SP_LINE_INFO);
            if (TextUtils.isEmpty(lineStr)) {
                return null;
            }
            mLineInfo = gson.fromJson(lineStr, LineBean.class);
        }

        return mLineInfo;
    }


    public void setSpeedType(int speedType) {
        mSpeedType = speedType;
        SPUtils.getInstance(SPEED_FILE_NAME).put(SP_SPEED_TYPE, speedType);
    }

    public int getSpeedType() {
        if (mSpeedType == null) {
            mSpeedType = SPUtils.getInstance(SPEED_FILE_NAME).getInt(SP_SPEED_TYPE);
        }
        return mSpeedType;
    }

    public String getmAllowedPackages() {
        return SPUtils.getInstance().getString(SP_SPEED_ALLOWED_PACKAGES);
    }

    public void setmAllowedPackages(String mAllowedPackages) {
        SPUtils.getInstance().put(SP_SPEED_ALLOWED_PACKAGES, mAllowedPackages);
    }
}
