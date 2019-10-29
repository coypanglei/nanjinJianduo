package com.libracore.lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.libracore.lib.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description:
 * @Author: librabin
 * @Time: 2018/8/27 14:37
 */
public class TimerButton extends AppCompatButton implements View.OnClickListener {

    // 倒计时长
    private long mCountTime;

    private long mInitCountTime;

    // 点击按钮前显示的文字
    private String mBeforeText = "获取验证码";
    // 开始计时后，显示的单位
    private String mAfterText = "s";


    private Timer mTimer;

    private TimerTask mTimerTask;

    private OnClickListener mOnClickListener;



    public TimerButton(Context context) {
        this(context, null);
    }

    public TimerButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
    }

    public TimerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }


    private void setup(Context context, AttributeSet attrs) {
        setOnClickListener(this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TimerButton);
        String beforeText = a.getString(R.styleable.TimerButton_beforeText);
        if (!TextUtils.isEmpty(beforeText)) {
            mBeforeText = beforeText;

        } else {
            String curText = getText().toString().trim();
            if (!TextUtils.isEmpty(curText)) {
                this.setText(curText);
                mBeforeText = curText;
            }
        }
        this.setText(mBeforeText);
        String afterText = a.getString(R.styleable.TimerButton_afterText);
        if (!TextUtils.isEmpty(afterText)) {
            mAfterText = afterText;
        }

        mInitCountTime = a.getInteger(R.styleable.TimerButton_countTime, 30) * 1000;

        a.recycle();
    }




    private void initTimer() {
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(1);
            }
        };
    }



    public void start() {
        initTimer();
        this.setText(mCountTime / 1000 + mAfterText);
        this.setEnabled(false);
        mTimer.schedule(mTimerTask, 0, 1000);
    }


    public void stop() {
        TimerButton.this.setEnabled(true);
        TimerButton.this.setText(mBeforeText);
        clearTimer();
        mCountTime = mInitCountTime;
    }





    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            TimerButton.this.setText(mCountTime / 1000 + mAfterText);
            mCountTime -= 1000;
            if (mCountTime < 0) {
                stop();
            }
        }
    };

    /**
     * 清楚倒计时
     */
    private void clearTimer() {
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }


    @Override
    public void setOnClickListener(@Nullable OnClickListener listener) {
        if (listener instanceof TimerButton) {
            super.setOnClickListener(listener);
        } else {
            mOnClickListener = listener;
        }
    }

    @Override
    public void onClick(View view) {
        mCountTime = mInitCountTime;
        if (mOnClickListener != null) {
            mOnClickListener.onClick(view);
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        clearTimer();
        super.onDetachedFromWindow();
    }




}
