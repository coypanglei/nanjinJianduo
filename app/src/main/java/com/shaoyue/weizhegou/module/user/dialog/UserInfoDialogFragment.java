package com.shaoyue.weizhegou.module.user.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.entity.user.UserInfoBean;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.wildma.pictureselector.PictureSelector;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：PangLei on 2019/5/15 0015 10:25
 * <p>
 * 邮箱：434604925@qq.com
 */
public class UserInfoDialogFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.tv_name)
    EditText mTvName;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.profile_image_lager)
    CircleImageView mProfileImageLager;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_change_name)
    TextView mTvChangeName;
    @BindView(R.id.et_phone_num)
    EditText mEtPhoneNum;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.tv_cancel_name)
    TextView mTvCancelName;
    @BindView(R.id.tv_cancel_phone_num)
    TextView mTvCancelPhoneNum;
    @BindView(R.id.tv_change_phone_num)
    TextView mTvChangePhoneNum;
    @BindView(R.id.tv_cancel_email)
    TextView mTvCancelEmail;
    @BindView(R.id.tv_change_email)
    TextView mTvChangeEmail;
    @BindView(R.id.et_branch)
    EditText mEtBranch;
    @BindView(R.id.et_position)
    EditText mEtPosition;



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_personal_information, null);

        unbinder = ButterKnife.bind(this, view);
        initView(dialog, view);
        fetchUserInfo();
        mEtBranch.setText(SPUtils.getInstance().getString(UserMgr.SP_LOGIN_POSITION));
        mEtPosition.setText(SPUtils.getInstance().getString(UserMgr.SP_LOGIN_BRANCH));
        return dialog;
    }


    public static UserInfoDialogFragment newInstance() {
        Bundle args = new Bundle();
        UserInfoDialogFragment fragment = new UserInfoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 初始化View
     *
     * @param dialog
     * @param view
     */
    private void initView(Dialog dialog, View view) {

        //动画
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;

        Window window = dialog.getWindow();
//// 把 DecorView 的默认 padding 取消，同时 DecorView 的默认大小也会取消
        WindowManager.LayoutParams layoutParams = window.getAttributes();
// 设置宽度
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
// 给 DecorView 设置背景颜色，很重要，不然导致 Dialog 内容显示不全，有一部分内容会充当 padding，上面例子有举出
        dialog.setContentView(view, layoutParams);
        dialog.setCanceledOnTouchOutside(true);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 刷新用户信息
     */
    private void fetchUserInfo() {
        UserApi.getUserInfo(new BaseCallback<BaseResponse<UserInfoBean>>() {
            @Override
            public void onSucc(BaseResponse<UserInfoBean> result) {
                mTvName.setText(result.data.getRealname());
                mTvTitle.setText(result.data.getRealname() + " | " + result.data.getUsername());
                if (ObjectUtils.isEmpty(result.data.getAvatar())) {
                    //  1男 2女
                    if ("1".equals(result.data.getSex())) {
                        mProfileImage.setImageResource(R.drawable.icon_sex_man);
                        mProfileImageLager.setImageResource(R.drawable.icon_sex_man);
                    } else {
                        mProfileImage.setImageResource(R.drawable.icon_sex_woman);
                        mProfileImageLager.setImageResource(R.drawable.icon_sex_woman);
                    }
                } else {
                    String imgUrl = DomainMgr.getInstance().getBaseUrlImg() + result.data.getAvatar();
                    GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), mProfileImage, imgUrl);
                    GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), mProfileImageLager, imgUrl);
                }
                mEtPhoneNum.setText(result.data.getPhone());
                mEtEmail.setText(result.data.getEmail());
            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
            }
        }, this);
    }

    @OnClick({R.id.iv_close, R.id.tv_change_profile, R.id.tv_change_name, R.id.tv_cancel_name, R.id.profile_image,
            R.id.tv_change_phone_num, R.id.tv_cancel_phone_num, R.id.tv_cancel_email, R.id.tv_change_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            //修改头像
            case R.id.tv_change_profile:
            case R.id.profile_image:
                /**
                 * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
                 * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
                 */
                PictureSelector
                        .create(UserInfoDialogFragment.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.tv_cancel_name:
                mTvName.setEnabled(false);
                //取消
                mTvChangeName.setTag("change");
                mTvChangeName.setText("修改");
                mTvCancelName.setVisibility(View.GONE);
                break;
            case R.id.tv_change_name:
                if (view.getTag().equals("change")) {

                    mTvName.setEnabled(true);
                    mTvChangeName.setText("保存");
                    view.setTag("save");
                    mTvCancelName.setVisibility(View.VISIBLE);
                } else {
                    String mName = mTvName.getText().toString().trim();
                    if (ObjectUtils.isEmpty(mName)) {
                        ToastUtil.showBlackToastSucess("姓名不能为空");
                        return;
                    }
                    //保存
                    UserApi.changeUserInfo(mName, "", "", "", new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            fetchUserInfo();
                            //保存
                            mTvChangeName.setTag("change");
                            mTvChangeName.setText("修改");
                            mTvName.setEnabled(false);
                            mTvCancelName.setVisibility(View.GONE);
                            ToastUtil.showBlackToastSucess("保存成功");
                        }
                    }, this);

                }
                break;
            case R.id.tv_change_phone_num:
                if (view.getTag().equals("change")) {

                    mEtPhoneNum.setEnabled(true);
                    mTvChangePhoneNum.setText("保存");
                    view.setTag("save");
                    mTvCancelPhoneNum.setVisibility(View.VISIBLE);
                } else {
                    String mPhone = mEtPhoneNum.getText().toString().trim();
                    if (ObjectUtils.isEmpty(mPhone)) {
                        ToastUtil.showBlackToastSucess("手机号不能为空");
                        return;
                    }
                    //保存
                    UserApi.changeUserInfo("", "", "", mPhone, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            fetchUserInfo();
                            //保存
                            mTvChangePhoneNum.setTag("change");
                            mTvChangePhoneNum.setText("修改");
                            mEtPhoneNum.setEnabled(false);
                            mTvCancelPhoneNum.setVisibility(View.GONE);
                            ToastUtil.showBlackToastSucess("保存成功");
                        }
                    }, this);

                }
                break;
            case R.id.tv_cancel_phone_num:
                //取消
                mEtPhoneNum.setEnabled(false);
                mTvChangePhoneNum.setTag("change");
                mTvChangePhoneNum.setText("修改");
                mTvCancelPhoneNum.setVisibility(View.GONE);
                break;
            case R.id.tv_change_email:
                if (view.getTag().equals("change")) {
                    mEtEmail.setEnabled(true);
                    mTvChangeEmail.setText("保存");
                    view.setTag("save");
                    mTvCancelEmail.setVisibility(View.VISIBLE);
                } else {


                    String mEmail = mEtEmail.getText().toString().trim();
                    if (ObjectUtils.isEmpty(mEmail)) {
                        ToastUtil.showBlackToastSucess("邮箱不能为空");
                        return;
                    }
                    //保存

                    UserApi.changeUserInfo("", "", mEmail, "", new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            fetchUserInfo();
                            mTvChangeEmail.setTag("change");
                            mTvChangeEmail.setText("修改");
                            mEtEmail.setEnabled(false);
                            mTvCancelEmail.setVisibility(View.GONE);
                            ToastUtil.showBlackToastSucess("保存成功");
                        }
                    }, this);


                }
                break;
            case R.id.tv_cancel_email:
                //取消
                mEtEmail.setEnabled(false);
                mTvChangeEmail.setTag("change");
                mTvChangeEmail.setText("修改");
                mTvCancelEmail.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                File mFile = new File(picturePath);
                UserApi.updatePic(mFile, new BaseCallback<BaseResponse<String>>() {
                    @Override
                    public void onSucc(BaseResponse<String> result) {
                        UserApi.changeUserInfo("", result.msg, "", "", new BaseCallback<BaseResponse<Void>>() {
                            @Override
                            public void onSucc(BaseResponse<Void> result) {
                                fetchUserInfo();
                                ToastUtil.showBlackToastSucess("保存成功");
                            }
                        }, getActivity());
                    }
                }, this);
            }
        }
    }



}
