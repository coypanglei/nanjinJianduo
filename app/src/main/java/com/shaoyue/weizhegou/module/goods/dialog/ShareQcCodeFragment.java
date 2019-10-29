package com.shaoyue.weizhegou.module.goods.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.model.Response;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.remote.GoodsApi;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：HQY on 17/7/17 14:54
 * 邮箱：hqy_xz@126.com
 */

public class ShareQcCodeFragment extends DialogFragment implements DialogInterface.OnKeyListener {
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;


    //商品Id
    private String goodsId;
    Unbinder unbinder;

    public static ShareQcCodeFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("id", id);
        ShareQcCodeFragment fragment = new ShareQcCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            goodsId = getArguments().getString("id");

        } else {
            dismiss();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_share_qc_code, null);

        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;
        dialog.setContentView(view);
        dialog.setOnKeyListener(this);
        dialog.setCanceledOnTouchOutside(true);
        unbinder = ButterKnife.bind(this, view);

        GoodsApi.getGoodsShareImg(goodsId, new BitmapCallback() {
            @Override
            public void onSuccess(Response<Bitmap> response) {
                GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), mIvGoods, response.body());
            }
        },this);

        return dialog;
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


}
