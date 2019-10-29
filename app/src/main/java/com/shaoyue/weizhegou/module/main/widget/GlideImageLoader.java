package com.shaoyue.weizhegou.module.main.widget;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.youth.banner.loader.ImageLoader;


/**
 * Created by USER on 2018/9/14.
 */

public class GlideImageLoader extends ImageLoader {

    private int defaultImg = 0;

    private boolean noChache = false;

    public GlideImageLoader(int defaultImg, boolean noChache) {
        this.defaultImg = defaultImg;
        this.noChache = noChache;
    }

    public GlideImageLoader() {
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        if (noChache) {
            //设置头像
            RequestOptions    options = new RequestOptions().placeholder(defaultImg).error(defaultImg).fitCenter();
            Glide.with(context).load(path).apply(options).into(imageView);
            return;
        }
        RequestOptions options;
        if (defaultImg == 0) {
            defaultImg = R.drawable.icon_banner_default_placeholder;
        }
        //设置头像
        options = new RequestOptions().placeholder(defaultImg).error(defaultImg);


        //Glide 加载图片简单用法
        Glide.with(context).load(path).apply(options).into(imageView);


    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建

}