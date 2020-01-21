package com.shaoyue.weizhegou.base;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.shaoyue.weizhegou.BuildConfig;
import com.shaoyue.weizhegou.common.CommConfig;
import com.shaoyue.weizhegou.common.CommParam;
import com.umeng.commonsdk.UMConfigure;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

public class BaseApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        initVendor();
    }


    public static Activity getTopActivity() {
        return ActivityUtils.getTopActivity();
    }


    /**
     * 初始化一些三方的工具
     */
    private void initVendor() {
        // 工具初始化
        Utils.init(this);
        // 初始化日志
        initLog();
        // http模块初始化
        initHttpModule();
        // 友盟初始化
        initUmeng();


    }

    /**
     * 日志模块初始化
     */
    private void initLog() {
        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG);
    }



    /**
     * 初始化HTTP模块
     */
    private void initHttpModule() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 打印日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("PROXY");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);

        // 全局读取超时时间
        builder.readTimeout(CommConfig.DEFAULT_READ_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.writeTimeout(CommConfig.DEFAULT_WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.connectTimeout(CommConfig.DEFAULT_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);

        // https配置
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
        builder.hostnameVerifier(new SafeHostnameVerifier());

        HttpHeaders headers = new HttpHeaders();
        Map<String, String> postParamsHead = CommParam.getCommonParams();

        for (Map.Entry<String, String> entry : postParamsHead.entrySet()) {
            headers.put(entry.getKey(), entry.getValue());
        }

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .addCommonHeaders(headers);


    }


    /**
     * 友盟初始化
     */
    private void initUmeng() {
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
        // 日志开关
        UMConfigure.setLogEnabled(true);
        // 日志加密
        UMConfigure.setEncryptEnabled(true);
//        PlatformConfig.setWeixin(BuildConfig.WX_APP_ID, "8e6948e866422cec73cda41c22e1efa5");
//        PushAgent mPushAgent = PushAgent.getInstance(this);
////注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//            @Override
//            public void onSuccess(String deviceToken) {
//                LogUtils.e(deviceToken);
//                UserMgr.getInstance().setmDeviceToken(deviceToken);
//                //注册成功会返回device token
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//
//            }
//        });

    }


    /**
     * 这里只是我谁便写的认证规则，具体每个业务是否需要验证，以及验证规则是什么，请与服务端或者leader确定
     * 重要的事情说三遍，以下代码不要直接使用
     */
    private class SafeHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            //验证主机名是否匹配
            //return hostname.equals("server.jeasonlzy.com");
            return true;
        }
    }


}
