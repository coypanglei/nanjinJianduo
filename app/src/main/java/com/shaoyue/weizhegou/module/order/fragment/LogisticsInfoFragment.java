package com.shaoyue.weizhegou.module.order.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.OrderApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.order.LogisticsInfoListBean;
import com.shaoyue.weizhegou.module.order.adapter.LogisticsInfoAdapter;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 作者：PangLei on 2019/7/12 0012 14:15
 * <p>
 * 邮箱：434604925@qq.com
 */
public class LogisticsInfoFragment extends BaseTitleFragment {

    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.empty_img)
    ImageView mEmptyImg;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_express_name)
    TextView mTvExpressName;
    @BindView(R.id.tv_waybill_number)
    TextView mTvWaybillNumber;

    @BindView(R.id.ll_visible)
    LinearLayout mLlVisible;
    private String id;

    private LogisticsInfoAdapter mLogisticsInfoAdapter;

    public static LogisticsInfoFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id", id);

        LogisticsInfoFragment fragment = new LogisticsInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            id =getArguments().getString("id");
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_logistics_information;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("物流信息");
        mEmptyImg.setImageResource(R.drawable.icon_empty_logistics);
        mEmptyText.setText("暂无物流信息" + ToastUtil.getToastMsg());
        mLogisticsInfoAdapter = new LogisticsInfoAdapter();
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContent.setAdapter(mLogisticsInfoAdapter);
        OrderApi.getLogisticsInfo(id, new BaseCallback<BaseResponse<LogisticsInfoListBean>>() {
            @Override
            public void onSucc(BaseResponse<LogisticsInfoListBean> result) {
                if (ObjectUtils.isNotEmpty(result.data.getExpress_info()) && result.data.getExpress_info().size() > 0) {
                    mLlVisible.setVisibility(View.VISIBLE);
                    mEmptyRelative.setVisibility(View.GONE);
                    mLogisticsInfoAdapter.setNewData(result.data.getExpress_info());
                    mLogisticsInfoAdapter.notifyDataSetChanged();
                    GlideNewImageLoader.displayImageNoCacheNoDefault(getContext(), mIvGoods, result.data.getProducts_img());
                    mTvContent.setText(result.data.getProducts_name());
                    mTvExpressName.setText(result.data.getShipping_name());
                    mTvWaybillNumber.setText("运单号：" + result.data.getInvoice_no());
                    mTvStatus.setText(result.data.getShipping_note());

                } else {
                    mLlVisible.setVisibility(View.INVISIBLE);
                    mEmptyRelative.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                mEmptyRelative.setVisibility(View.VISIBLE);
            }
        }, this);

    }


}

