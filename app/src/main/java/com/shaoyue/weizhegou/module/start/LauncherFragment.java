package com.shaoyue.weizhegou.module.start;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat.CryptoObject;
import android.support.v4.os.CancellationSignal;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.OkGo;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.home.HomeInitBean;
import com.shaoyue.weizhegou.interfac.CommCallBack;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.util.ToolUtils;
import com.shaoyue.weizhegou.widget.gesture.GestureLockLayout;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import butterknife.BindView;
import butterknife.OnClick;

import static com.shaoyue.weizhegou.manager.UserMgr.GESTURELOCK_KEY;


/**
 * m
 * Created by librabin on 16/10/31.
 */

public class LauncherFragment extends BaseAppFragment implements StartListener {

    @BindView(R.id.ll_password)
    LinearLayout mLlPassword;
    @BindView(R.id.layout_fingerprint)
    LinearLayout mLayoutFingerprint;
    @BindView(R.id.error_msg)
    TextView mErrorMsg;
    @BindView(R.id.layout_gesture_lock)
    ConstraintLayout mLayoutGestureLock;
    @BindView(R.id.tv_login_type)
    TextView mTvLoginType;
    @BindView(R.id.et_login_name)
    EditText mEtLoginName;
    @BindView(R.id.et_login_password)
    EditText mEtLoginPassword;
    @BindView(R.id.iv_gesture)
    ImageView mIvGesture;
    @BindView(R.id.ll_login)
    LinearLayout mLlLogin;
    @BindView(R.id.gestureLock)
    GestureLockLayout mGestureLockLayout;
    @BindView(R.id.hintTV)
    TextView hintTV;
    @BindView(R.id.tv_accout)
    TextView mTvAccout;
    private Boolean isFingerprint, isGesture;
    /**
     * 最大解锁次数
     */
    private int mNumber = 5;
    private FingerprintManagerCompat fingerprintManagerCompat;

    private CancellationSignal mCancellationSignal;
    private Cipher cipher;
    private Animation animation;

    /**
     * 标识是否是用户主动取消的认证。
     */
    private boolean isSelfCancelled;
    private StartStep mStartStep;
    private HomeInitBean mHomeInitBean;
    //1密码登录 2 手势登陆 3指纹登录
    private int loginType = 1;

    public static LauncherFragment newInstance() {
        return new LauncherFragment();
    }





    @Override
    protected int getLayoutId() {
        return R.layout.fragment_start;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setLoginType();
        fingerprintManagerCompat = FingerprintManagerCompat.from(mActivity);
        mTvAccout.setText("账号:" + SPUtils.getInstance().getString(UserMgr.getInstance().SP_LOGIN_NAME, ""));
        isFingerprint = SPUtils.getInstance().getBoolean(UserMgr.ISFINGERPRINT_KEY, false);
        isGesture = SPUtils.getInstance().getBoolean(UserMgr.ISGESTURELOCK_KEY, false);
        //手势
        if (!isGesture) {

            mLayoutGestureLock.setVisibility(View.GONE);
            mTvLoginType.setText("指纹登录");
            mIvGesture.setImageResource(R.drawable.icon_fingerprint);
        } else {
            loginType = 3;
            setGestureListener();
        }
        //指纹
        if (!isFingerprint) {
            mLayoutFingerprint.setVisibility(View.GONE);
            mTvLoginType.setText("手势登录");
            mIvGesture.setImageResource(R.drawable.login_gesture);
        } else {
            loginType = 2;
            setFingerprint();
        }
        //既不能手势又不能指纹
        if (!isGesture && !isFingerprint) {
            mLlLogin.setVisibility(View.GONE);
            mTvAccout.setVisibility(View.INVISIBLE);
        }

        setLoginType();

    }

    @Override
    protected void loadData() {
        AppMgr.getInstance().handleStart();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppMgr.getInstance().setStartListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        OkGo.getInstance().cancelTag(this);
    }


    private void setLoginType() {
        if (loginType == 1) {
            mLlPassword.setVisibility(View.VISIBLE);
            mTvAccout.setVisibility(View.INVISIBLE);
            mLayoutFingerprint.setVisibility(View.GONE);
            mLayoutGestureLock.setVisibility(View.GONE);
        } else if (loginType == 2) {
            mLlPassword.setVisibility(View.GONE);
            mTvAccout.setVisibility(View.VISIBLE);
            mLayoutFingerprint.setVisibility(View.VISIBLE);
            mLayoutGestureLock.setVisibility(View.GONE);
        } else if (loginType == 3) {
            mTvAccout.setVisibility(View.VISIBLE);
            mLayoutGestureLock.setVisibility(View.VISIBLE);
            mLlPassword.setVisibility(View.GONE);
            mLayoutFingerprint.setVisibility(View.GONE);
        }
    }


    /**
     * 失败提示对话框
     */
    private void showFailDialog(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false); // 点击背景窗口不能自动隐藏
        builder.setTitle(title).
                setMessage(msg)
                .setNegativeButton(R.string.content_retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AppMgr.getInstance().handleStart();
                    }
                }).setPositiveButton(R.string.content_sign_out, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
            }
        });
        builder.create().show();
    }


    @Override
    public void commplete(StartStep startStep, int code, String msg, HomeInitBean homeInitBean) {
        if (startStep == StartStep.FINISH_LOGIN || startStep == StartStep.FINISH_MAIN) { // 进入登录界面
            mStartStep = startStep;
            mHomeInitBean = homeInitBean;
//            finishInit();
        } else if (startStep == StartStep.FIRST_START) {
//            if (code == -1) {
//                UIHelper.showGuide(getActivity());
//            }
        } else {
//            if (code != 0) {
//                showFailDialog("友情提示", msg);
//            }


        }
    }


    @OnClick({R.id.tv_phone_login, R.id.ll_password_login, R.id.ll_gesture_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_phone_login:
                loginVoid();
                break;
            case R.id.ll_password_login:
                loginType = 1;
                setLoginType();
                stopListening();
                break;
            case R.id.ll_gesture_login:
                //手势 指纹都开启
                if (isFingerprint && isGesture) {
                    if (mTvLoginType.getText().toString().equals("手势登录")) {
                        loginType = 3;
                        mTvLoginType.setText("指纹登录");
                        mIvGesture.setImageResource(R.drawable.icon_fingerprint);
                        stopListening();
                    } else {
                        loginType = 2;
                        mTvLoginType.setText("手势登录");
                        mIvGesture.setImageResource(R.drawable.login_gesture);
                        setFingerprint();
                    }
                    setLoginType();

                    mErrorMsg.setText("");
                    //指纹开启 手势没开启
                } else if (isFingerprint && !isGesture) {
                    if (loginType != 2) {
                        setFingerprint();
                    }
                    loginType = 2;
                    setLoginType();
                    //指纹没开启 手势开启
                } else if (isGesture && !isFingerprint) {
                    loginType = 3;
                    setLoginType();
                }

                break;
        }
    }

    private void setFingerprint() {
        if (ToolUtils.supportFingerprint(getActivity())) {
            ToolUtils.initKey(); //生成一个对称加密的key
            //生成一个Cipher对象
            cipher = ToolUtils.initCipher();
        }
        if (cipher != null) {
            startListening(cipher);
        }
    }

    /**
     * 密码登录
     */
    private void loginVoid() {
        String mAccountEdit = mEtLoginName.getText().toString().trim();
        String mPasswordEdit = mEtLoginPassword.getText().toString().trim();
        UserMgr.getInstance().doLoginByPhone(mAccountEdit, mPasswordEdit, new CommCallBack() {
            @Override
            public void complete(int code, String msg) {
                if (code == 0) {
                    UIHelper.showMainPage(getActivity(), mHomeInitBean);
                } else {
                    ToastUtil.showBlackToastSucess(msg);
                }
            }
        },this);
    }

    /**
     * 手势登录
     *
     * @param cipher
     */
    private void startListening(Cipher cipher) {
        isSelfCancelled = false;
        mCancellationSignal = new CancellationSignal();
        CryptoObject cryptoObject = new CryptoObject(cipher);
        fingerprintManagerCompat.authenticate(cryptoObject, 0, mCancellationSignal, new MyCallBack(), null);

    }

    /**
     * 指纹回调
     */
    public class MyCallBack extends FingerprintManagerCompat.AuthenticationCallback {

        // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            if (!isSelfCancelled) {
                mErrorMsg.setText(errString);
                if (errMsgId == FingerprintManager.FINGERPRINT_ERROR_LOCKOUT) {
//                    Log.e("TAG", "" + errString);
                }
            }
        }

        // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
        @Override
        public void onAuthenticationFailed() {
            mErrorMsg.setText("指纹认证失败，请再试一次");
        }

        //错误时提示帮助，比如说指纹错误，我们将显示在界面上 让用户知道情况
        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            mErrorMsg.setText(helpString);
            Log.e("TAG", "helpString=" + helpString);
        }

        // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            CryptoObject cryptoObject = result.getCryptoObject();
            try {
                //指纹登录
                byte[] bytes = cryptoObject.getCipher().doFinal();
                String mAccount = SPUtils.getInstance().getString(UserMgr.getInstance().SP_LOGIN_NAME, "");
                String mPassword = SPUtils.getInstance().getString(UserMgr.getInstance().SP_LOGIN_PASSWORD, "");
                UserMgr.getInstance().doLoginByPhone(mAccount, mPassword, new CommCallBack() {
                    @Override
                    public void complete(int code, String msg) {
                        if (code == 0) {
                            stopListening();
                            UIHelper.showMainPage(getActivity(), mHomeInitBean);
                        } else {

                            ToastUtil.showBlackToastSucess(msg);
                        }

                    }
                },this);
            } catch (BadPaddingException e) {

                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();

            }



        }
    }

    /**
     * 停止指纹监听
     */
    private void stopListening() {
        if (mCancellationSignal != null) {
            mCancellationSignal.cancel();
            mCancellationSignal = null;
            isSelfCancelled = true;
        }
    }

    private void setGestureListener() {
        String gestureLockPwd = SPUtils.getInstance().getString(GESTURELOCK_KEY, "");
        if (!TextUtils.isEmpty(gestureLockPwd)) {
            mGestureLockLayout.setAnswer(gestureLockPwd);
        } else {

//            MyToast.showToast("没有设置过手势密码");
        }
        mGestureLockLayout.setDotCount(3);
        mGestureLockLayout.setMode(GestureLockLayout.VERIFY_MODE);
        //设置手势解锁最大尝试次数 默认 5
        mGestureLockLayout.setTryTimes(5);
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        mGestureLockLayout.setOnLockVerifyListener(new GestureLockLayout.OnLockVerifyListener() {
            @Override
            public void onGestureSelected(int id) {
                //每选中一个点时调用
            }

            @Override
            public void onGestureFinished(boolean isMatched) {
                //绘制手势解锁完成时调用
                if (isMatched) {
                    String mAccount = SPUtils.getInstance().getString(UserMgr.getInstance().SP_LOGIN_NAME, "");
                    String mPassword = SPUtils.getInstance().getString(UserMgr.getInstance().SP_LOGIN_PASSWORD, "");
                    UserMgr.getInstance().doLoginByPhone(mAccount, mPassword, new CommCallBack() {
                        @Override
                        public void complete(int code, String msg) {
                            if (code == 0) {
                                UIHelper.showMainPage(getActivity(), mHomeInitBean);
                            } else {

                                ToastUtil.showBlackToastSucess(msg);
                            }
                        }
                    },this);
                } else {
                    hintTV.setVisibility(View.VISIBLE);
                    mNumber = --mNumber;
                    hintTV.setText("你还有" + mNumber + "次机会");
                    hintTV.startAnimation(animation);
                    mGestureLockLayout.startAnimation(animation);
                    ToolUtils.setVibrate(getActivity());
                }
                resetGesture();
            }

            @Override
            public void onGestureTryTimesBoundary() {
                //超出最大尝试次数时调用
                mGestureLockLayout.setTouchable(false);
            }
        });
    }

    /**
     * 重置手势布局（只是布局）
     */
    private void resetGesture() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mGestureLockLayout.resetGesture();
            }
        }, 300);
    }



}
