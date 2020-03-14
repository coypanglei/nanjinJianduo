package com.shaoyue.weizhegou.module.pay.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.coupon.SettlementCouponBean;
import com.shaoyue.weizhegou.entity.goods.SettlementCoupon;
import com.shaoyue.weizhegou.module.pay.adapter.ShopPromotionsAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShopPromotionsFragment extends DialogFragment implements DialogInterface.OnKeyListener {
    @BindView(R.id.rv_coupon)
    RecyclerView mRvCoupon;
    private Unbinder unbinder;
    private ShopPromotionsAdapter mShopPromotionsAdapter;

    private SettlementCoupon mSettlementCoupon;


    public static ShopPromotionsFragment newInstance(SettlementCoupon settlementCoupon) {
        Bundle args = new Bundle();
        args.putSerializable("settlement_coupon", settlementCoupon);
        ShopPromotionsFragment fragment = new ShopPromotionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ObjectUtils.isNotEmpty(getArguments())) {
            mSettlementCoupon = (SettlementCoupon) getArguments().getSerializable("settlement_coupon");
        } else {
            dismiss();
        }


    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_shop_promotions, null);
        unbinder = ButterKnife.bind(this, view);
        mShopPromotionsAdapter = new ShopPromotionsAdapter(mSettlementCoupon.getmCouponList());
        mRvCoupon.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvCoupon.setAdapter(mShopPromotionsAdapter);
        mRvCoupon.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<SettlementCouponBean> mlist = (List<SettlementCouponBean>) adapter.getData();
                SettlementCouponBean select = (SettlementCouponBean) adapter.getData().get(position);
                for (SettlementCouponBean settlementCoupon : mlist) {
                    settlementCoupon.setSelect(false);
                    if (settlementCoupon.getId() == select.getId()) {
                        settlementCoupon.setSelect(true);
                    }
                }
                adapter.notifyDataSetChanged();
                EventBus.getDefault().post(select);
                dismiss();
            }
        });
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
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimBottom;
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
        dialog.setCanceledOnTouchOutside(true);
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.view_top, R.id.tv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_top:
            case R.id.tv_close:
                dismiss();
                break;
//            case R.id.tv_add_to_shopping_cart:
//                AddCar();
//                break;
//            case R.id.tv_ok:
//                if (type == 1) {
//                    AddCar();
//                }
//                break;
        }
    }


}