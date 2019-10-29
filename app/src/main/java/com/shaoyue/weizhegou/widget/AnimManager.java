package com.shaoyue.weizhegou.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.shaoyue.weizhegou.R;


import java.lang.ref.WeakReference;

/**
 * 作者：PangLei on 2019/6/4 0004 13:55
 * <p>
 * 邮箱：434604925@qq.com
 */
public class AnimManager {
    private WeakReference<Activity> mActivity;
    private AnimListener mListener;
    private long time;
    private final View startView;
    private final View endView;
    private final String imageUrl;
    private View animView;
    private double scale;
    private int animWidth;
    private int animHeight;
    private ViewGroup animMaskLayout;

    private AnimManager() {
        this(new Builder());
    }

    AnimManager(Builder builder) {
        this.mActivity = builder.activity;
        this.startView = builder.startView;
        this.endView = builder.endView;
        this.time = builder.time;
        this.mListener = builder.listener;
        this.animView = builder.animView;
        this.imageUrl = builder.imageUrl;
        this.scale = builder.scale;
        this.animWidth = builder.animWidth;
        this.animHeight = builder.animHeight;
    }


    /**
     * 开始动画
     */
    public void startAnim() {
        if (startView == null || endView == null) {
            throw new NullPointerException("startView or endView must not null");
        }
        int[] startLocation = new int[2];
        int[] endLocation = new int[2];
        startView.getLocationInWindow(startLocation);
        endView.getLocationInWindow(endLocation);
        if (animView != null) {
            setAnim(startLocation, endLocation);
        } else if (ObjectUtils.isNotEmpty(imageUrl)) {
            createImageAndAnim(startLocation, endLocation);
        }
    }

    private void createImageAndAnim(final int[] startLocation, final int[] endLocation) {
        final ImageView animImageView = new ImageView(getActivity());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(animWidth,
                animHeight);
        animImageView.setLayoutParams(layoutParams);
        Glide.with(getActivity()).load(imageUrl)

                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        setAnim(animImageView, startLocation, endLocation);
                        return false;
                    }
                })
                .into(animImageView);
    }


    private void setAnim(int[] startLocation, int[] endLocation) {
        setAnim(animView, startLocation, endLocation);
    }

    private void setAnim(final View v, int[] startLocation, int[] endLocation) {
        try {
            animMaskLayout = createAnimLayout(getActivity());
            // 把动画小球添加到动画层
            animMaskLayout.addView(v);
            final View view = addViewToAnimLayout(v, startLocation);
            //终点位置
            int endX = endLocation[0] - startLocation[0] - 30;
            // 动画位移的y坐标
            int endY = endLocation[1] - startLocation[1];
//        AnimatorSet animatorSet1 = new AnimatorSet();//组合动画
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 3f, 1f);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 3f, 1f);
//
//        animatorSet1.setDuration(500);
//        animatorSet1.setInterpolator(new DecelerateInterpolator());
//        animatorSet1.play(scaleX).with(scaleY);//两个动画同时开始
//
//        animatorSet1.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                view.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//            }
//        });
//
//
//        animatorSet1.start();

            ObjectAnimator translateAnimationX = ObjectAnimator.ofFloat(view, "translationX", ScreenUtils.getScreenWidth() / 2 - view.getWidth() / 2, endX);
            translateAnimationX.setInterpolator(new LinearInterpolator());
            // 动画重复执行的次数


            ObjectAnimator translateAnimationY = ObjectAnimator.ofFloat(view, "translationY", ScreenUtils.getScreenHeight() / 2 - view.getHeight() / 2, endY - endView.getHeight());
            translateAnimationY.setInterpolator(new AccelerateInterpolator());


            ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.3f);
            ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.3f);

            AnimatorSet animatorSet = new AnimatorSet();


            animatorSet.play(scaleX2).with(scaleY2).with(translateAnimationX).with(translateAnimationY);


            // 动画的执行时间,计算出的时间小于300ms默认为300ms
            animatorSet.setDuration(300);

            scaleX2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                }

            });
//        LogUtils.e("as");
//        v.setVisibility(View.VISIBLE);
//        if (mListener != null) {
//            mListener.setAnimBegin(AnimManager.this);
//        }
//        LogUtils.e("END");
//        v.setVisibility(View.GONE);
//        animMaskLayout.removeAllViews();
//        if (mListener != null) {
//            mListener.setAnimEnd(AnimManager.this);
//        }

            // 动画监听事件
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (mListener != null) {
                        mListener.setAnimBegin(AnimManager.this);
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {


                    v.setVisibility(View.GONE);
                    animMaskLayout.removeAllViews();
                    if (mListener != null) {
                        mListener.setAnimEnd(AnimManager.this);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animatorSet.start();
        } catch (NullPointerException e) {
            LogUtils.e(e);
        }


    }

    public void stopAnim() {
        if (animMaskLayout != null && animMaskLayout.getChildCount() > 0) {
            animMaskLayout.removeAllViews();
        }
    }

    private ViewGroup createAnimLayout(Activity mainActivity) {

        ViewGroup rootView = (ViewGroup) mainActivity.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(mainActivity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(R.id.anim_icon);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final View view, int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    /**
     * 自定义时间
     *
     * @param time
     * @return
     */
    public long setTime(long time) {
        this.time = time;
        return time;
    }

    private Activity getActivity() {
        return mActivity.get();
    }

    public void setOnAnimListener(AnimListener listener) {
        mListener = listener;
    }

    //回调监听
    public interface AnimListener {

        void setAnimBegin(AnimManager a);

        void setAnimEnd(AnimManager a);

    }

    public static final class Builder {
        WeakReference<Activity> activity;
        View startView;
        View endView;
        View animView;
        String imageUrl;
        long time;
        double scale;
        int animWidth;
        int animHeight;
        AnimListener listener;

        public Builder() {
            this.time = 1000;
            this.scale = 1;
            this.animHeight = 25;
            this.animWidth = 25;
        }

        public Builder with(Activity activity) {
            this.activity = new WeakReference<>(activity);
            return this;
        }

        public Builder startView(View startView) {
            if (startView == null) {
                throw new NullPointerException("startView is null");
            }
            this.startView = startView;
            return this;
        }

        public Builder endView(View endView) {
            if (endView == null) {
                throw new NullPointerException("endView is null");
            }
            this.endView = endView;
            return this;
        }

        public Builder animView(View animView) {
            if (animView == null) {
                throw new NullPointerException("animView is null");
            }
            this.animView = animView;
            return this;
        }

        public Builder listener(AnimListener listener) {
            if (listener == null) {
                throw new NullPointerException("listener is null");
            }
            this.listener = listener;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder time(long time) {
            if (time <= 0) {
                throw new IllegalArgumentException("time must be greater than zero");
            }
            this.time = time;
            return this;
        }

        public Builder scale(double scale) {
            this.scale = scale;
            return this;
        }

        public Builder animWidth(int width) {
            if (width <= 0) {
                throw new IllegalArgumentException("width must be greater than zero");
            }
            this.animWidth = width;
            return this;
        }

        public Builder animHeight(int height) {
            if (height <= 0) {
                throw new IllegalArgumentException("height must be greater than zero");
            }
            this.animHeight = height;
            return this;
        }

        public AnimManager build() {
            return new AnimManager(this);
        }
    }
}

