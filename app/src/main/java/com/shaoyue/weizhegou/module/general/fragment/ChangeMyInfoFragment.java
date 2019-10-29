package com.shaoyue.weizhegou.module.general.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;

import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.wildma.pictureselector.PictureSelector;


import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：PangLei on 2019/5/8 0008 15:55
 * <p>
 * 邮箱：434604925@qq.com
 */
public class ChangeMyInfoFragment extends BaseTitleFragment {


    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.rl_go_png)
    RelativeLayout mRlGoPng;
    @BindView(R.id.tv_id)
    TextView mTvId;
    @BindView(R.id.tv_nickname)
    TextView mTvNickName;


    public static ChangeMyInfoFragment newInstance() {

        Bundle args = new Bundle();

        ChangeMyInfoFragment fragment = new ChangeMyInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_change_account;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("修改个人资料");
        mTvId.setText(UserMgr.getInstance().getUid());
        mTvNickName.setText(UserMgr.getInstance().getUsername());
        GlideNewImageLoader.displayImageHeadNoCache(getActivity(), mProfileImage, UserMgr.getInstance().getHeaderpic());
    }

    @Override
    public void onResume() {
        super.onResume();
        mTvNickName.setText(UserMgr.getInstance().getUsername());
    }

    @OnClick({R.id.rl_go_png, R.id.rl_nick_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_nick_name:
                addFragment(new ChangeNickNameFragment());
                break;
            case R.id.rl_go_png:
                /**
                 * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
                 * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
                 */
                PictureSelector
                        .create(getActivity(), PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
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
                        GlideNewImageLoader.displayImageHeadNoCache(getActivity(), mProfileImage, result.data);
                        ToastUtil.showSuccessToast("保存成功");
                    }
                }, this);
            }
        }
    }
}
