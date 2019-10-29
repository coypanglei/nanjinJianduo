package com.shaoyue.weizhegou.util;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;

public class CheckUtils {


    public static boolean checkOldNewConfirmPwd(String newPwd, String confirmPwd, String verifyCode) {
        if (ObjectUtils.isEmpty(verifyCode)) {
            ToastUtil.showErrorToast("验证码不能为空");
            return false;
        }


        if (ObjectUtils.isEmpty(newPwd)) {
            ToastUtil.showErrorToast("新密码不能为空");
            return false;
        }


        if (ObjectUtils.isEmpty(confirmPwd)) {
            ToastUtil.showErrorToast("确认密码不能为空");
            return false;
        }


        if (newPwd.length() < 6) {
            ToastUtil.showErrorToast("密码长度不能小于6位");
            return false;
        }

        if (!newPwd.equals(confirmPwd)) {
            ToastUtil.showErrorToast("两次密码输入不一致");
            return false;
        }

        return true;
    }


    public static boolean emailCodePwdConfirm(String email, String verifyCode, String password,
                                              String confirmPwd) {
        if (ObjectUtils.isEmpty(email)) {
            ToastUtil.showErrorToast("邮箱不能为空");
            return false;
        }

        if (!RegexUtils.isEmail(email)) {
            ToastUtil.showErrorToast("邮箱格式不正确");
            return false;
        }

        if (ObjectUtils.isEmpty(password)) {
            ToastUtil.showErrorToast("新密码不能为空");
            return false;
        }

        if (password.length() < 6) {
            ToastUtil.showErrorToast("密码长度不能小于6位");
            return false;
        }

        if (!password.equals(confirmPwd)) {
            ToastUtil.showErrorToast("两次密码输入不一致");
            return false;
        }

        if (ObjectUtils.isEmpty(verifyCode)) {
            ToastUtil.showErrorToast("邮箱验证码不能为空");
            return false;
        }


        return true;
    }

    /**
     * 手机号密码验证码 验证
     *
     * @param phone
     * @param password
     * @param verifyCode
     * @return
     */
    public static boolean phonePwdCode(String phone, String password, String verifyCode) {
        if (!phoneCodeCheck(phone, verifyCode)) {
            return false;
        }


        if (ObjectUtils.isEmpty(password)) {
            ToastUtil.showErrorToast("密码不能为空");
            return false;
        }

        if (password.length() < 6) {
            ToastUtil.showErrorToast("密码长度不能小于6位");
            return false;
        }
        if (password.length() > 30) {
            ToastUtil.showErrorToast("密码长度过长");
        }

        return true;
    }

    /**
     * 验证手机号和验证码
     *
     * @param phone
     * @param verifyCode
     * @return
     */
    public static boolean phoneCodeCheck(String phone, String verifyCode) {
        if (ObjectUtils.isEmpty(phone)) {
            ToastUtil.showErrorToast("手机号不能为空");
            return false;
        }

        if (ObjectUtils.isEmpty(verifyCode)) {
            ToastUtil.showErrorToast("手机验证码不能为空");
            return false;
        }


        if (!RegexUtils.isMobileSimple(phone)) {
            ToastUtil.showErrorToast("手机号格式不正确");
            return false;
        }


        return true;
    }


    /**
     * 验证手机号和验证码和密码
     *
     * @param phone
     * @param verifyCode
     * @return
     */
    public static boolean phoneCodeCheck(String phone, String verifyCode, String password) {
        if (ObjectUtils.isEmpty(phone)) {
            ToastUtil.showErrorToast("手机号不能为空");
            return false;
        }
        if (ObjectUtils.isEmpty(password)) {
            ToastUtil.showErrorToast("密码不能为空");
            return false;
        }

        if (ObjectUtils.isEmpty(verifyCode)) {
            ToastUtil.showErrorToast("手机验证码不能为空");
            return false;
        }


        if (!RegexUtils.isMobileSimple(phone)) {
            ToastUtil.showErrorToast("手机号格式不正确");
            return false;
        }


        return true;
    }


    /**
     * 验证手机号和验证码和密码和id
     *
     * @param phone
     * @param verifyCode
     * @return
     */
    public static boolean phoneCodeIDCheck(String phone, String verifyCode, String password) {
        if (ObjectUtils.isEmpty(phone)) {
            ToastUtil.showBlackToastSucess("手机号不能为空");
            return false;
        }
        if (ObjectUtils.isEmpty(password)) {
            ToastUtil.showBlackToastSucess("登录密码不能为空");
            return false;
        }

        if (ObjectUtils.isEmpty(verifyCode)) {
            ToastUtil.showBlackToastSucess("手机验证码不能为空");
            return false;
        }


        if (!RegexUtils.isMobileSimple(phone)) {
            ToastUtil.showBlackToastSucess("手机号格式不正确");
            return false;
        }


        return true;
    }


}
