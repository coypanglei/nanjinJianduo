package com.shaoyue.weizhegou.module.goods.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 作者：PangLei on 2019/7/12 0012 10:01
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OutOfStockFragment extends BaseTitleFragment {

    @BindView(R.id.empty_img)
    ImageView mEmptyImg;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;


    public static OutOfStockFragment newInstance() {

        Bundle args = new Bundle();

        OutOfStockFragment fragment = new OutOfStockFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_out_of_stock;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("商品详情");
        mEmptyImg.setImageResource(R.drawable.icon_empty_goods);
        mEmptyText.setText("该商品已下架" + ToastUtil.getToastMsg());
        mEmptyRelative.setVisibility(View.VISIBLE);
    }


}
