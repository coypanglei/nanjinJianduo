package com.shaoyue.weizhegou.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.PayApi;
import com.shaoyue.weizhegou.event.PayPasswordEvent;
import com.shaoyue.weizhegou.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;


/**
 * Description:
 * Data：7/10/2018-10:18 AM
 *
 * @author yanzhiwen
 */

public class PayPasswordView extends LinearLayout implements View.OnClickListener, PasswordEditText.PasswordFullListener {
    private LinearLayout mKeyBoardView;
    private PasswordEditText mPasswordEditText;
    private TextView mPriceText;
    private Context mContext;


    public PayPasswordView(Context context) {
        this(context, null);
    }

    public PayPasswordView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayPasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        inflate(context, R.layout.pay_password_layout, this);
        mKeyBoardView = findViewById(R.id.keyboard);
        mPasswordEditText = findViewById(R.id.passwordEdt);
        mPasswordEditText.setPasswordFullListener(this);
        mPriceText = findViewById(R.id.tv_price);
        setItemClickListener(mKeyBoardView);
    }

    public void setPriceText(String text) {
        mPriceText.setText(text);
    }


    public void setmPasswordEditText() {
        mPasswordEditText.setText("");
    }

    /**
     * 给每一个自定义数字键盘上的View 设置点击事件
     *
     * @param view
     */
    private void setItemClickListener(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                //不断的给里面所有的View设置setOnClickListener
                View childView = ((ViewGroup) view).getChildAt(i);
                setItemClickListener(childView);
            }
        } else {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            String number = ((TextView) v).getText().toString().trim();
            mPasswordEditText.addPassword(number);
        }
        if (v instanceof ImageView) {
            mPasswordEditText.deletePassword();
        }
    }

    @Override
    public void passwordFull(final String password) {
        if (ObjectUtils.isNotEmpty(mPasswordFullListener)) {
            mPasswordFullListener.passwordFull(password);
        }
    }

    private PasswordFullListener mPasswordFullListener;

    public interface PasswordFullListener {
        void passwordFull(String password);
    }

    public void setPasswordFullListener(PasswordFullListener passwordFullListener) {
        this.mPasswordFullListener = passwordFullListener;
    }
}
