package com.shaoyue.weizhegou.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.util.Util;
import com.shaoyue.weizhegou.R;


/**
 * ================================================
 * 作    者：dd
 * 版    本：1.0
 * 创建日期：18/4/25
 * 描    述：给imageview设置glide显示网络url
 * 修订历史：
 * ================================================
 */
public class GlideNewImageLoader {

    public static void displayImage2(Activity context, final ImageView imageView, String imagePath) {
//        //获取屏幕宽度
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        WindowManager manager = context.getWindowManager();
//        manager.getDefaultDisplay().getMetrics(outMetrics);
//        int width = outMetrics.widthPixels / 2;
//
//        //按宽度等比例缩放，不然会OOM
//        int[] width_height = getImageWidthHeight(imagePath);
//        float ratio = (float) ((width_height[0] * 1.0) / (width * 1.0));
//        int height = (int) (width_height[1] * 1.0 / ratio);
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .dontAnimate()
                .placeholder(R.drawable.icon_default_placeholder)
                .error(R.drawable.icon_default_placeholder)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        if (Util.isOnMainThread()) {
            if (!context.isFinishing()) {
                Glide.with(context)
                        .asDrawable()
                        .load(imagePath)
                        .apply(options)
                        .into(imageView);
            }
        }
    }


    /**
     * @param context
     * @param imageView
     * @param imagePath
     */
    public static void displayImage(Context context, ImageView imageView, String imagePath) {
        if (context != null) {
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.icon_default_placeholder)//
                    .error(R.drawable.icon_default_placeholder)//
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context)
                    .load(imagePath)
                    .apply(options)
                    .into(imageView);
        }
    }

    /**
     * 不使用缓存
     *
     * @param context
     * @param imageView
     * @param imagePath
     */

    public static void displayImageNoCache(Context context, ImageView imageView, String imagePath) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_default_placeholder)//
                .error(R.drawable.icon_default_placeholder)//
                .dontAnimate()
//                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context)
                .load(imagePath)
                .apply(options)
                .into(imageView);
    }

    /**
     * 不使用缓存 也没有默认
     *
     * @param context
     * @param imageView
     * @param imagePath
     */

    public static void displayImageNoCacheNoDefault(Context context, ImageView imageView, Object imagePath) {
        if (context != null) {
            RequestOptions options = new RequestOptions()
                    .dontAnimate()
//                .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE);
            Glide.with(context)
                    .load(imagePath)
                    .apply(options)
                    .into(imageView);
        }
    }

    /**
     * 不使用缓存 默认头像
     *
     * @param context
     * @param imageView
     * @param imagePath
     */
    public static void displayImageHeadNoCache(Context context, ImageView imageView, String imagePath) {
        if (context != null) {
            if (ObjectUtils.isNotEmpty(imagePath)) {
                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.icon_head_default)//
                        .error(R.drawable.icon_head_default)//
                        .dontAnimate()
//                .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE);
                Glide.with(context)
                        .load(imagePath)
                        .apply(options)
                        .into(imageView);
            } else {
                imageView.setImageResource(R.drawable.icon_head_default);
            }
        }
    }

    /**
     * 不使用缓存 默认店主头像
     *
     * @param context
     * @param imageView
     * @param imagePath
     */
    public static void displayOwnerImageHeadNoCache(Context context, ImageView imageView, String imagePath, int error) {
        if (context != null) {
            if (ObjectUtils.isNotEmpty(imagePath)) {
                RequestOptions options = new RequestOptions()
                        .placeholder(error)//
                        .error(error)//
                        .dontAnimate()
//                .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE);
                Glide.with(context)
                        .load(imagePath)
                        .apply(options)
                        .into(imageView);
            } else {
                imageView.setImageResource(error);
            }
        }
    }

    /**
     * @param context
     * @param imageView
     * @param imagePath
     */
    public static void displayImageNoDefault(Context context, ImageView imageView, String imagePath) {
        if (context != null) {
            RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.ico_default_icon)//
//                .error(R.mipmap.ico_default_icon)//
                    .dontAnimate()
//                .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context)
                    .load(imagePath)
                    .apply(options)
                    .into(imageView);
        }
    }

    /**
     * @param context
     * @param imageView
     * @param outputUri
     */
    public static void displayImage(Context context, ImageView imageView, Uri outputUri) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_default_placeholder)//
                .error(R.drawable.icon_default_placeholder)//
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(outputUri)
                .apply(options)
                .into(imageView);
    }


    //在不加载图片情况下获取图片大小
    public static int[] getImageWidthHeight(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null
        /**
         *options.outHeight为原始图片的高
         */
        return new int[]{options.outWidth, options.outHeight};
    }
}
