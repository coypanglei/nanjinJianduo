package com.shaoyue.weizhegou.module.credit.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.VideoMaterialBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.module.main.widget.GlideImageLoader;
import com.shaoyue.weizhegou.router.UIHelper;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：PangLei on 2019/5/15 0015 10:25
 * <p>
 * 邮箱：434604925@qq.com
 */
public class VideoPicDialogFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.goods_banner)
    Banner mGoodsBanner;

    private VideoMaterialBean videoMaterialBean;


    @BindView(R.id.tv_my_info)
    TextView mTvInfo;

    private String id;
    private String url;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_video, null);
        unbinder = ButterKnife.bind(this, view);
//        if ("查看详情".equals(SPUtils.getInstance().getString("status")) || ("invisable".equals(videoMaterialBean.getVisable()))) {
//            ivDetele.setVisibility(View.GONE);
//        }
        List<String> mPicList = new ArrayList<>();
        List<String> mPicList2 = new ArrayList<>();
        for (VideoMaterialBean.ListBean bean : videoMaterialBean.getList()) {
            mPicList.add(DomainMgr.getInstance().getBaseUrlImg() + bean.getZldz());
        }

        List<String> newpIc = mPicList.subList(videoMaterialBean.getSelect(), mPicList.size());
        List<String> newPic2 = mPicList.subList(0, videoMaterialBean.getSelect());
        mPicList2.addAll(newpIc);
        mPicList2.addAll(newPic2);

        List<VideoMaterialBean.ListBean> listBeans = new ArrayList<>();
        List<VideoMaterialBean.ListBean> list = videoMaterialBean.getList().subList(videoMaterialBean.getSelect(), videoMaterialBean.getList().size());
        List<VideoMaterialBean.ListBean> list2 = videoMaterialBean.getList().subList(0, videoMaterialBean.getSelect());
        listBeans.addAll(list);
        listBeans.addAll(list2);
        videoMaterialBean.setList(listBeans);
        mGoodsBanner.setImages(mPicList2);

        mGoodsBanner.setImageLoader(new GlideImageLoader(R.drawable.icon_default_placeholder, true));
        mGoodsBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        mGoodsBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (ObjectUtils.isNotEmpty(videoMaterialBean.getList().get(i).getZllx())) {
                    mTvInfo.setText(videoMaterialBean.getList().get(i).getZllx());
                }
                id = videoMaterialBean.getList().get(i).getId();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mGoodsBanner.start();

        initView(dialog, view);

        return dialog;
    }


    public static VideoPicDialogFragment newInstance(VideoMaterialBean data) {

        Bundle args = new Bundle();
        args.putSerializable("picList", data);
        VideoPicDialogFragment fragment = new VideoPicDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            videoMaterialBean = (VideoMaterialBean) getArguments().getSerializable("picList");
            url = videoMaterialBean.getUrl();
        }
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
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }


    @OnClick({R.id.iv_close, R.id.iv_detele})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.iv_detele:

                UIHelper.showOkNewClearDialog(getActivity(), new OkOrCancelEvent("是否删除所选图片", id, url));
                dismiss();
                break;
        }
    }

}