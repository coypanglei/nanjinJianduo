package com.shaoyue.weizhegou.util;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shaoyue.weizhegou.AppContext;
import com.shaoyue.weizhegou.R;

/**
 * Created by librabin on 16/9/12.
 */
public class ToastUtil {

    private static String lastToast = "";
    private static long lastToastTime;

    private static String toastMsg = "哦！";

    public static String getToastMsg() {
        return toastMsg;
    }

    public static void setToastMsg(String toastMsg) {
        ToastUtil.toastMsg = toastMsg;
    }

    public static void showSuccToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT, Gravity.TOP | Gravity.FILL_HORIZONTAL,
                com.shaoyue.weizhegou.R.color.color_5fbfb1);
    }

    public static void showSuccessToast(String msg) {
        showToastSucess(msg, Toast.LENGTH_SHORT, Gravity.CENTER | Gravity.FILL_HORIZONTAL);
    }

    public static void showErrorToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT, Gravity.TOP | Gravity.FILL_HORIZONTAL,
                com.shaoyue.weizhegou.R.color.color_feda11);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT, Gravity.TOP | Gravity.FILL_HORIZONTAL,
                com.shaoyue.weizhegou.R.color.default_theme_color);
    }


    public static void showToast(String message, int duration,
                                 int gravity, int backcolor) {
        if (message != null && !message.equalsIgnoreCase("")) {
//            message = message + toastMsg ;
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast)
                    || Math.abs(time - lastToastTime) > 2000) {
                View view = LayoutInflater.from(AppContext.getInstance()).inflate(
                        com.shaoyue.weizhegou.R.layout.base_toast, null);
                ((TextView) view.findViewById(com.shaoyue.weizhegou.R.id.tv_toast)).setText(message);
                (view.findViewById(com.shaoyue.weizhegou.R.id.ll_toast_back)).setBackgroundResource(backcolor);
                Toast toast = new Toast(AppContext.getInstance());
                toast.setView(view);
                toast.setGravity(gravity, 0, 0);
                toast.setDuration(duration);
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }

    public static void showToastSucess(String message, int duration,
                                       int gravity) {
        if (message != null && !message.equalsIgnoreCase("")) {
//            message = message + toastMsg ;
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast)
                    || Math.abs(time - lastToastTime) > 1000) {
                View view = LayoutInflater.from(AppContext.getInstance()).inflate(
                        R.layout.toast_success, null);
                ((TextView) view.findViewById(com.shaoyue.weizhegou.R.id.tv_msg)).setText(message);
                final Toast toast = new Toast(AppContext.getInstance());
                toast.setView(view);
                toast.setGravity(gravity, 0, 0);
                toast.setDuration(duration);
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, 1000);
            }
        }
    }

    public static void showBlackToastSucess(String message) {
        if (message != null && !message.equalsIgnoreCase("")) {
//            message = message + toastMsg ;
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast)
                    || Math.abs(time - lastToastTime) > 1000) {
                View view = LayoutInflater.from(AppContext.getInstance()).inflate(
                        R.layout.toast_black_success, null);
                ((TextView) view.findViewById(com.shaoyue.weizhegou.R.id.tv_msg)).setText(message);
                final Toast toast = new Toast(AppContext.getInstance());
                toast.setView(view);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, 1000);
            }
        }
    }


}