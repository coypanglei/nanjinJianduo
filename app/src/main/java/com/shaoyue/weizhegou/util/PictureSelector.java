package com.shaoyue.weizhegou.util;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.io.File;
import java.lang.ref.WeakReference;

import static anet.channel.strategy.dispatch.DispatchConstants.APP_NAME;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/6/24
 * Desc	        ${图片选择器}
 */
public class PictureSelector {
    public static final String BASE_DIR   =  APP_NAME + File.separator;//PictureSelector/
    public static final String DIR_ROOT   = FileUtils.getRootPath() + File.separator + BASE_DIR;//文件夹根目录 /storage/emulated/0/PictureSelector/
    public static final int    SELECT_REQUEST_CODE = 0x15;//选择图片请求码
    public static final String PICTURE_PATH        = "image_Path";//图片路劲标记
    private       int                     mRequestCode;
    private final WeakReference<Activity> mActivity;
    private final WeakReference<Fragment> mFragment;

    public static PictureSelector create(Activity activity, int requestCode) {
        return new PictureSelector(activity, requestCode);
    }

    public static PictureSelector create(Fragment fragment, int requestCode) {
        return new PictureSelector(fragment, requestCode);
    }

    private PictureSelector(Activity activity, int requestCode) {
        this(activity, (Fragment) null, requestCode);
    }

    private PictureSelector(Fragment fragment, int requestCode) {
        this(fragment.getActivity(), fragment, requestCode);
    }

    private PictureSelector(Activity activity, Fragment fragment, int requestCode) {
        this.mActivity = new WeakReference(activity);
        this.mFragment = new WeakReference(fragment);
        this.mRequestCode = requestCode;
    }



}

