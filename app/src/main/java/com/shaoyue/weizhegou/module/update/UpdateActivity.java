package com.shaoyue.weizhegou.module.update;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.libracore.lib.widget.NumberProgressBar;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.VersionBean;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.module.start.StartStep;
import com.shaoyue.weizhegou.service.DownloadService;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bin on 17/7/7.
 */

public class UpdateActivity extends AppCompatActivity {

    public static final String EXTRA_VERSION = "extra_version";


    @BindView(R.id.progress_bar)
    NumberProgressBar mNumberProgressBar;

    @BindView(R.id.btn_update)
    TextView mBtnUpdate;

    @BindView(R.id.tv_title)
    TextView mTextTitle;

    @BindView(R.id.tv_target_size)
    TextView mTextTargetSize;

    @BindView(R.id.tv_update_info)
    TextView mTextUpdateInfo;

    @BindView(R.id.ll_close)
    LinearLayout mLayoutClose;

    public static boolean isShow = false;


    private VersionBean mVersion;


    /**
     * 回调
     */
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            startDownloadApp((DownloadService.DownloadBinder) service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);
        mVersion = (VersionBean) getIntent().getSerializableExtra(EXTRA_VERSION);
        if (mVersion == null) {
            finish();
        }

        initView();

    }

    private void initView() {
        if (mVersion.getIsForceUpdate().equals("1")) {
            mLayoutClose.setVisibility(View.GONE);
        }

        String versionName = mVersion.getVersionName();
        if (!TextUtils.isEmpty(versionName)) {
            mTextTitle.setText("发现新版本" + versionName);
        } else {
            mTextTitle.setText("发现新版本");
        }

        mNumberProgressBar.setMax(mVersion.getUpdateSize());

        String updateLog = mVersion.getUpdateContent();
        if (!TextUtils.isEmpty(updateLog)) {
            mTextUpdateInfo.setText(updateLog);
        }

//        String targetSize = mVersion.getUpdateSize() + "";
//        if (!TextUtils.isEmpty(targetSize)) {
//            mTextTargetSize.setText("新版本大小: " + targetSize);
//            mTextTargetSize.setVisibility(View.VISIBLE);
//        }


        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();
//                downloadApp();
//                mBtnUpdate.setVisibility(View.GONE);
            }
        });

        mLayoutClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                AppMgr.getInstance().setStartStep(StartStep.HOME_PAGE_SWITCH);
                AppMgr.getInstance().handleStart();
            }
        });
    }


    private void loadData() {

    }


    /**
     * 回调监听下载
     */
    private void startDownloadApp(DownloadService.DownloadBinder binder) {
        // 开始下载，监听下载进度，可以用对话框显示
        if (mVersion != null) {

            binder.start(mVersion, new DownloadService.DownloadCallback() {
                @Override
                public void onStart() {
                    if (!UpdateActivity.this.isFinishing()) {
                        LogUtils.e("start");
                        mNumberProgressBar.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onProgress(float progress) {

                    if (!UpdateActivity.this.isFinishing()) {
                        mNumberProgressBar.setProgress((int) progress);
                    }
                }

                @Override
                public void setMax(float total) {
                    if (!UpdateActivity.this.isFinishing()) {


                    }
                }

                @Override
                public void onFinish() {
                    if (!UpdateActivity.this.isFinishing()) {
                        UpdateActivity.this.finish();
                    }
                }

                @Override
                public void onError(String msg) {
                    if (!UpdateActivity.this.isFinishing()) {
                        UpdateActivity.this.finish();
                    }
                }
            });
        }
    }


    /**
     * 开启后台服务下载
     */
    private void downloadApp() {
        //使用ApplicationContext延长他的生命周期
        DownloadService.bindService(getApplicationContext(), conn);
    }

    @Override
    public void onBackPressed() {
//        //禁用
//        if (mVersion != null && mVersion.isConstraint()) {
////          ActManager.getInstance().finishAllActivity();
//            android.os.Process.killProcess(android.os.Process.myPid());
//        }
//        super.onBackPressed();

//        if (mVersion != null && mVersion.isConstraint()) {
//            super.onBackPressed();
//        }
    }


    @Override
    protected void onDestroy() {
        isShow = false;
        super.onDestroy();
    }

    private void checkPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        downloadApp();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        ToastUtil.showErrorToast(getResources().getString(R.string.t_permission_denied));
                    }
                })
                .start();

    }
}
