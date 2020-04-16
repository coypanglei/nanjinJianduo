package com.shaoyue.weizhegou.module.general.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.PictureSelector;
import com.shaoyue.weizhegou.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;

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
                EasyPhotos.createCamera(getActivity())//参数说明：上下文，是否显示相机按钮，[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                        .setFileProviderAuthority("com.shaoyue.weizhegou.fileprovider")//参数说明：见下方`FileProvider的配置`
                        .setCount(1)//参数说明：最大可选数，默认1
                        .start(PictureSelector.SELECT_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                final ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
                if (ObjectUtils.isEmpty(resultPhotos) || resultPhotos.size() == 0) {
                    return;
                }
                String picturePath = resultPhotos.get(0).path;
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
