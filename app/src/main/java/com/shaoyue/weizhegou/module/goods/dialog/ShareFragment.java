package com.shaoyue.weizhegou.module.goods.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.L;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.goods.GoodsDetialBean;
import com.shaoyue.weizhegou.entity.goods.GoodsSpecNameBean;
import com.shaoyue.weizhegou.entity.goods.GoodsSpecificationBean;
import com.shaoyue.weizhegou.entity.goods.SpecInfoBean;
import com.shaoyue.weizhegou.event.AddOrRemoveEvent;
import com.shaoyue.weizhegou.event.SelectSpecEvent;
import com.shaoyue.weizhegou.module.goods.adapter.GoodSpecAdapter;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.util.ZMStrUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShareFragment extends DialogFragment implements DialogInterface.OnKeyListener {

    Unbinder unbinder;
    private ShareAction mShareAction;

    public static ShareFragment newInstance() {
        Bundle args = new Bundle();
//        args.putSerializable("goodsDetialBean", goodsDetialBean);
        ShareFragment fragment = new ShareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (ObjectUtils.isNotEmpty(getArguments())) {
////            mGoodsBetialBean = (GoodsDetialBean) getArguments().getSerializable("goodsDetialBean");
////            goodsSpecNameBeans = mGoodsBetialBean.getGoodsSpecNameBeanList();
////            mSpecInfoList = mGoodsBetialBean.getmSpecInfoBeanList();
////            mSelectSpec = mGoodsBetialBean.getSelectSpecItems();
////            mBuyNum = mGoodsBetialBean.getBuyNum();
////            //默认图片
////            defaultImg = mGoodsBetialBean.getImages().get(0);
////            img = defaultImg;
//        } else {
//            dismiss();
//        }


    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_share, null);
        unbinder = ButterKnife.bind(this, view);
        initView(dialog, view);

        return dialog;
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
// 把 DecorView 的默认 padding 取消，同时 DecorView 的默认大小也会取消
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
// 设置宽度
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
// 给 DecorView 设置背景颜色，很重要，不然导致 Dialog 内容显示不全，有一部分内容会充当 padding，上面例子有举出
        dialog.setContentView(view, layoutParams);
        dialog.setOnKeyListener(this);
        dialog.setCanceledOnTouchOutside(true);
    }

    @Override
    public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.view_dismiss, R.id.iv_wechat, R.id.iv_friends, R.id.iv_copy_link})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_dismiss:
                dismiss();
                break;
            case R.id.iv_wechat:
                setShareType();
                mShareAction.setPlatform(SHARE_MEDIA.WEIXIN);
                mShareAction.share();
                break;
            case R.id.iv_friends:
                setShareType();
                mShareAction.setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE);
                mShareAction.share();

                break;
            case R.id.iv_copy_link:
                LogUtils.e("sadas");
                break;
        }
    }

    /**
     * 设置分享类型
     */
    private void setShareType() {
        mShareAction = new ShareAction(getActivity());
        UMWeb umWeb = new UMWeb("https://www.baidu.com/");
        UMImage thumb = new UMImage(getActivity(), R.drawable.app_logo);
        umWeb.setTitle("sasasa");
        umWeb.setDescription("121314");
        umWeb.setThumb(thumb);
        mShareAction.withMedia(umWeb);
        mShareAction.setCallback(mShareListener);
    }


    private UMShareListener mShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtil.showSuccessToast("成功");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtil.showSuccessToast("失败");
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtil.showSuccessToast("取消");
        }
    };


}