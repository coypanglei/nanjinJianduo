package com.shaoyue.weizhegou.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.blankj.utilcode.util.ImageUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import com.shaoyue.weizhegou.entity.VersionBean;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;


/**
 * 后台下载
 */
public class DownloadService extends Service {

    private static final int NOTIFY_ID = 0;
    private static final String TAG = DownloadService.class.getSimpleName();
    public static boolean isRunning = false;
    private NotificationManager mNotificationManager;
    private DownloadBinder binder = new DownloadBinder();
    private NotificationCompat.Builder mBuilder;
//    /**
//     * 开启服务方法
//     *
//     * @param context
//     */
//    public static void startService(Context context) {
//        Intent intent = new Intent(context, DownloadService.class);
//        context.startService(intent);
//    }

    public static void bindService(Context context, ServiceConnection connection) {
        Intent intent = new Intent(context, DownloadService.class);
        context.startService(intent);
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        isRunning = true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // 返回自定义的DownloadBinder实例
        return binder;
    }

    @Override
    public void onDestroy() {
        mNotificationManager = null;
        super.onDestroy();
    }

    /**
     * 创建通知
     */
    private void setUpNotification() {
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("开始下载")
                .setContentText("正在连接服务器")
                .setSmallIcon(com.shaoyue.weizhegou.R.drawable.lib_update_app_update_icon)
                .setLargeIcon(ImageUtils.drawable2Bitmap(getResources().getDrawable(com.shaoyue.weizhegou.R.drawable.app_logo)))
                .setOngoing(true)
                .setWhen(System.currentTimeMillis());
        mNotificationManager.notify(NOTIFY_ID, mBuilder.build());
    }

    /**
     * 下载模块
     */
    private void startDownload(VersionBean updateBean, final DownloadCallback callback) {
        String apkUrl = updateBean.getPackageUrl();
        if (TextUtils.isEmpty(apkUrl)) {
            String contentText = "新版本下载路径错误";
            stop(contentText);
            return;
        }
        final String appName = apkUrl.substring(apkUrl.lastIndexOf("/") + 1, apkUrl.length());

        if (!appName.endsWith(".apk")) {
            String contentText = "下载包有错误";
            stop(contentText);
            return;
        }
        File appDir = new File(AppMgr.getInstance().getApkPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }

//        //可以利用md5验证是否重复下载
//        updateApp.getHttpManager().download(apkUrl, target, appName, new FileDownloadCallBack(callback));

        starDownloadApk(apkUrl, appDir.getAbsolutePath(), appName, callback);


    }

    private void stop(String contentText) {
        mBuilder.setContentTitle(getResources().getString(com.shaoyue.weizhegou.R.string.app_name)).setContentText(contentText);
        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(NOTIFY_ID, notification);
        close();
    }

    private void close() {
        stopSelf();
        isRunning = false;
    }

    /**
     * 进度条回调接口
     */
    public interface DownloadCallback {
        void onStart();

        void onProgress(float progress);

        void setMax(float total);

        void onFinish();

        void onError(String msg);
    }

    /**
     * DownloadBinder中定义了一些实用的方法
     *
     * @author user
     */
    public class DownloadBinder extends Binder {
        /**
         * 开始下载
         */
        public void start(VersionBean updateApp, DownloadCallback callback) {
            //初始化通知栏
            setUpNotification();
            //下载
            startDownload(updateApp, callback);
        }
    }


    private void starDownloadApk(String url, String apkPath, String fileName, final DownloadCallback callback) {
        OkGo.<File>get(url)
                .tag(this)
                .execute(new FileCallback(apkPath, fileName) {

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        if (callback != null) {
                            callback.onStart();
                        }
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        if (callback != null) {
                            callback.onFinish();
                        }


//
//
//                        if (AppUtils.isAppForeground()) {
//                            //App前台运行
//                            mNotificationManager.cancel(NOTIFY_ID);
//                            Intent intent = new Intent(Intent.ACTION_VIEW);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                                intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
//                            } else {
//                                intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
//                            }
//                            if (getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
//                                startActivity(intent);
//                            }
//                        } else {
//                            //App后台运行
//                            Intent intent = new Intent(Intent.ACTION_VIEW);
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                                intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
//                            } else {
//                                intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
//                            }
//                            //更新参数,注意flags要使用FLAG_UPDATE_CURRENT
//                            PendingIntent contentIntent = PendingIntent.getActivity(DownloadService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                            mBuilder.setContentIntent(contentIntent)
//                                    .setContentTitle(getResources().getString(com.shaoyue.weizhegou.R.string.app_name))
//                                    .setContentText("下载完成，请点击安装")
//                                    .setProgress(0, 0, false)
//                                    .setAutoCancel(true)
//                                    .setDefaults((Notification.DEFAULT_ALL));
//                            mNotificationManager.notify(NOTIFY_ID, mBuilder.build());
//                        }
//=======
                        File apkFile = response.body();

                        AndPermission.with(DownloadService.this)
                                .install()
                                .file(apkFile)
                                .onGranted(new Action<File>() {
                                    @Override
                                    public void onAction(File data) {
                                        ToastUtil.showSuccToast("同意了安装权限");
                                    }
                                })
                                .onDenied(new Action<File>() {
                                    @Override
                                    public void onAction(File data) {
                                        ToastUtil.showErrorToast("拒绝了安装权限");
                                    }
                                })
                                .start();

                        //下载完自杀
                        close();
                    }


                    @Override
                    public void downloadProgress(Progress progress) {
                        int rate = Math.round(progress.fraction * 100);
                        if (callback != null) {
                            callback.setMax(progress.totalSize);
                            callback.onProgress(progress.fraction * progress.totalSize);
                        }
                        mBuilder.setContentTitle("正在下载：" + getResources().getString(com.shaoyue.weizhegou.R.string.app_name))
                                .setContentText(rate + "%")
                                .setProgress(100, rate, false)
                                .setWhen(System.currentTimeMillis());
                        mNotificationManager.notify(NOTIFY_ID, mBuilder.build());
                    }

                    @Override
                    public void onError(Response<File> response) {
                        ToastUtil.showToast("更新版本出错");
                        //App前台运行
                        if (callback != null) {
                            callback.onError(response.getException().getMessage());
                        }
                        try {
                            mNotificationManager.cancel(NOTIFY_ID);
                            close();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });


    }
}
