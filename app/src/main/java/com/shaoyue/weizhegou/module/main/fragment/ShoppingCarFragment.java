package com.shaoyue.weizhegou.module.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AddressApi;
import com.shaoyue.weizhegou.api.remote.GoodsApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.goods.GoodsDetialBean;
import com.shaoyue.weizhegou.entity.goods.GoodsSpecAllBean;
import com.shaoyue.weizhegou.entity.goods.ModifyCarBean;
import com.shaoyue.weizhegou.entity.goods.SettlementCenterBean;
import com.shaoyue.weizhegou.entity.goods.ShopCarBean;
import com.shaoyue.weizhegou.entity.goods.ShopCarListBean;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.module.goods.fragment.SettlementCenterFragment;
import com.shaoyue.weizhegou.module.main.adapter.ShoppingCarAdapter;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.util.XClick.SingleClick;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 作者：PangLei on 2019/3/21 0021 11:06
 * <p>
 * 邮箱：434604925@qq.com
 */
public class ShoppingCarFragment extends BaseTitleFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.rv_goods)
    RecyclerView mRvGoods;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.empty_img)
    ImageView mEmptyImg;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    @BindView(R.id.rl_bottom)
    RelativeLayout mRlBottom;
    @BindView(R.id.tv_settlement)
    TextView mTvSettlement;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @BindView(R.id.tv_coupon_info)
    TextView mTvCouponInfo;
    @BindView(R.id.iv_cancel_or_select)
    ImageView mIvCancelOrSelect;


    //尾部
    private View footerView;

    private int per_page = 10;
    private int page = 1;
    private int total = 0;
    private int type;
    private GoodsDetialBean mGoodsDetiaBean;

    private int isAllSelect = 0;

    private ShoppingCarAdapter mShoppingCarAdapter;


    public static ShoppingCarFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        ShoppingCarFragment fragment = new ShoppingCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ObjectUtils.isNotEmpty(getArguments())) {
            type = getArguments().getInt("type");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_shopping_car;
    }


    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void ModifyCar(final ModifyCarBean event) {
        if (event.getIsRefresh() == 1) {
            initCarList();
        } else {
            for (int i = 0; i < mShoppingCarAdapter.getData().size(); i++) {
                if (mShoppingCarAdapter.getData().get(i).getId().equals(event.getmShopCarBean().getId())) {
                    mShoppingCarAdapter.getData().remove(i);
                    mShoppingCarAdapter.getData().add(i, event.getmShopCarBean());
                    break;
                }
            }

            mShoppingCarAdapter.notifyDataSetChanged();
            if (mRlBottom.getVisibility() == View.VISIBLE) {
                mTvSettlement.setText("结算(" + event.getOrder_count() + ")");
                mTvTotalPrice.setText(event.getTotal_price());
                if (ObjectUtils.isNotEmpty(event.getMax_offer())) {
                    mTvCouponInfo.setVisibility(View.VISIBLE);
                    mTvCouponInfo.setText(event.getMax_offer());
                } else {
                    mTvCouponInfo.setVisibility(View.GONE);
                }
            }


        }

    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("购物车").setRightBtnV3(R.drawable.icon_delete_history, new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View v) {
                List<ShopCarBean> mData = mShoppingCarAdapter.getData();
                if (ObjectUtils.isNotEmpty(mData) && mData.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (ShopCarBean mShopCarBean : mData) {
                        if (mShopCarBean.getSelected() == 1) {
                            sb.append(mShopCarBean.getId());
                            sb.append(",");
                        }
                    }
                    String selectSb = sb.toString();
                    LogUtils.e(selectSb);
                    if (ObjectUtils.isEmpty(selectSb) && selectSb.length() == 0) {

                        ToastUtil.showBlackToastSucess("没有选中");
                        return;
                    }

                    //删除地址
                    UIHelper.showOkClearDialog(getActivity(), "确定删除商品");
                }
            }
        });
        if (type != 2) {
            hideLeftButtonV2();
        }
        initView();
        initCarList();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mShoppingCarAdapter.getData().size() == 0) {
            startProgressDialog(true);
            initCarList();
        } else {
            initCarList();
        }
    }

    /**
     * 清除历史记录
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("确定删除商品")) {
            List<ShopCarBean> mData = mShoppingCarAdapter.getData();
            if (ObjectUtils.isNotEmpty(mData) && mData.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (ShopCarBean mShopCarBean : mData) {
                    if (mShopCarBean.getSelected() == 1) {
                        sb.append(mShopCarBean.getId());
                        sb.append(",");
                    }
                }
                String selectSb = sb.toString();

                if (ObjectUtils.isEmpty(selectSb) && selectSb.length() == 0) {

                    return;
                }

                String ids = selectSb.substring(0, sb.length() - 1);
                GoodsApi.deleteInvalid(ids, new BaseCallback<BaseResponse<ShopCarListBean>>() {
                    @Override
                    public void onSucc(BaseResponse<ShopCarListBean> result) {
                        ToastUtil.showBlackToastSucess("已删除");
                        initCarList();
                    }

                    @Override
                    public void onFail(ApiException apiError) {

                        ToastUtil.showBlackToastSucess(apiError.getMessage());
                    }
                }, this);

            }
        }
    }

    /**
     * 获得购物车数量
     */
    private void setmCarNum(int num) {
        if (num > 99) {
            setCommonTitle("购物车 (" + "99+" + ")");
        } else if (num <= 0) {
            setCommonTitle("购物车");
        } else {
            setCommonTitle("购物车 (" + num + ")");
        }
    }

    /**
     * 刷新数据
     *
     * @param listBean
     */
    private void reshData(ShopCarListBean listBean) {
        mTvSettlement.setText("结算(" + listBean.getOrder_count() + ")");
        mTvTotalPrice.setText(listBean.getTotal_price());
        if (ObjectUtils.isNotEmpty(listBean.getMax_offer())) {
            mTvCouponInfo.setVisibility(View.VISIBLE);
            mTvCouponInfo.setText(listBean.getMax_offer());
        } else {
            mTvCouponInfo.setVisibility(View.GONE);
        }
    }


    private void initCarList() {
        page = 1;
        GoodsApi.getBuyCarList(page, new BaseCallback<BaseResponse<ShopCarListBean>>() {
            @Override
            public void onSucc(BaseResponse<ShopCarListBean> result) {
                stopProgressDialog();
                isAllSelect = result.data.getIs_all_selected();
                total = result.data.getTotal();
                per_page = result.data.getPer_page();
                setmCarNum(result.data.getProducts_count());
                mShoppingCarAdapter.setNewData(result.data.getData());

                if (isAllSelect == 1) {
                    mIvCancelOrSelect.setImageResource(R.drawable.icon_goods_select);
                } else if (isAllSelect == 0) {
                    mIvCancelOrSelect.setImageResource(R.drawable.icon_goods_not_select);
                }

                //有失效
                if (result.data.getIs_invalid_count() > 0) {
                    if (mShoppingCarAdapter.getFooterLayoutCount() == 0) {
                        mShoppingCarAdapter.addFooterView(footerView);
                        footerView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //清空失效
                                GoodsApi.clearInvalid(new BaseCallback<BaseResponse<ShopCarListBean>>() {
                                    @Override
                                    public void onSucc(BaseResponse<ShopCarListBean> result) {
                                        ToastUtil.showBlackToastSucess("清空成功");
                                        initCarList();
                                    }
                                }, this);
                            }
                        });
                    }
                } else {
                    if (mShoppingCarAdapter.getFooterLayoutCount() > 0) {
                        mShoppingCarAdapter.removeAllFooterView();
                    }
                }
                if (result.data.getData().size() > 0) {
                    if (result.data.getIsAllInvalid() == 1) {
                        mRlBottom.setVisibility(View.GONE);
                    } else {
                        mRlBottom.setVisibility(View.VISIBLE);
                        reshData(result.data);
                    }
                    mEmptyRelative.setVisibility(View.GONE);

                } else {
                    mRlBottom.setVisibility(View.GONE);
                    mEmptyRelative.setVisibility(View.VISIBLE);
                    mEmptyText.setText("空空如也的购物车~");
                    mEmptyImg.setImageResource(R.drawable.icon_empty_goods);
                }
            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                stopProgressDialog();
            }
        }, this);
    }


    /**
     * 初始化View
     */
    private void initView() {
        mGoodsDetiaBean = new GoodsDetialBean();
        mShoppingCarAdapter = new ShoppingCarAdapter(getActivity());
        mRvGoods.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvGoods.setAdapter(mShoppingCarAdapter);
        mShoppingCarAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.ll_left:
                        //可选中
                        ShopCarBean mShopCarBean = mShoppingCarAdapter.getData().get(position);
                        if (mShopCarBean.getCart_status() == 0) {
                            String select = "";
                            if (mShopCarBean.getSelected() == 1) {
                                select = "0";
                            } else {
                                select = "1";
                            }
                            GoodsApi.changeCar(mShopCarBean.getId(), "", select, "", new BaseCallback<BaseResponse<ModifyCarBean>>() {
                                @Override
                                public void onSucc(BaseResponse<ModifyCarBean> result) {

                                    //刷新列表
                                    if (result.data.getIsRefresh() == 1) {
                                        initCarList();
                                    } else {
                                        mShoppingCarAdapter.getData().remove(position);
                                        mShoppingCarAdapter.getData().add(position, result.data.getmShopCarBean());
                                        mShoppingCarAdapter.notifyDataSetChanged();
                                        if (mRlBottom.getVisibility() == View.VISIBLE) {
                                            mTvSettlement.setText("结算(" + result.data.getOrder_count() + ")");
                                            mTvTotalPrice.setText(result.data.getTotal_price());
                                            if (ObjectUtils.isNotEmpty(result.data.getMax_offer())) {
                                                mTvCouponInfo.setVisibility(View.VISIBLE);
                                                mTvCouponInfo.setText(result.data.getMax_offer());
                                            } else {
                                                mTvCouponInfo.setVisibility(View.GONE);
                                            }
                                        }
                                        boolean allSelect = true;
                                        for (ShopCarBean mShopCarBean : mShoppingCarAdapter.getData()) {
                                            if (mShopCarBean.getCart_status() == 0) {
                                                if (mShopCarBean.getSelected() == 0) {
                                                    allSelect = false;
                                                }
                                            }
                                        }
                                        if (allSelect) {
                                            mIvCancelOrSelect.setImageResource(R.drawable.icon_goods_select);
                                        } else {
                                            mIvCancelOrSelect.setImageResource(R.drawable.icon_goods_not_select);
                                        }
                                    }
                                }

                                @Override
                                public void onFail(ApiException apiError) {
                                    ToastUtil.showBlackToastSucess(apiError.getMessage());
                                }
                            }, this);
                        }
                        break;
                    case R.id.iv_goods:
                    case R.id.tv_products_name:
                        //只有正常的商品可以点击
                        //商品详情
                        if (type != 2) {
                            ShopCarBean mShopCarBeanNew = mShoppingCarAdapter.getData().get(position);
                            if (mShopCarBeanNew.getCart_status() == 0 || mShopCarBeanNew.getCart_status() == 1 || mShopCarBeanNew.getCart_status() == 3) {
                                String goodsId = mShopCarBeanNew.getProducts_id() + "";
                                UIHelper.showGoodsDetailsActivity(getActivity(), ContentType.GOODS_DETAILS, new profileBean(goodsId));
                            }

                        }
                        break;
                    //修改规格
                    case R.id.tv_change_info:
                        String[] mSpecKey = mShoppingCarAdapter.getData().get(position).getSpec_key().split(";");

                        List<String> mSpecList = Arrays.asList(mSpecKey);


                        getGoodsSpec(mShoppingCarAdapter.getData().get(position).getProducts_id(), mShoppingCarAdapter.getData().get(position).getProducts_img(), new ArrayList(mSpecList));
                        break;
                }
            }
        });
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), false));
//        mRefreshLayout.setIsShowLoadingMoreView(true);
        footerView = LayoutInflater.from(mActivity).inflate(R.layout.shop_car_footview, (ViewGroup) mRvGoods.getParent(), false);
    }


    /**
     * 获取商品规格
     */
    private void getGoodsSpec(String goodsId, final String img, final List<String> mSpecList) {
        GoodsApi.getSpecInfo(goodsId, new BaseCallback<BaseResponse<GoodsSpecAllBean>>() {
            @Override
            public void onSucc(BaseResponse<GoodsSpecAllBean> result) {
                mGoodsDetiaBean.setGoodsSpecNameBeanList(result.data.getGoodsSpecNameBeanList());
                mGoodsDetiaBean.setmSpecInfoBeanList(result.data.getmSpecInfoBeanList());
                //改变的规格
                mGoodsDetiaBean.setSelectSpecItems(mSpecList);
                //跳转的位置
                mGoodsDetiaBean.setType(type);
                List<String> images = new ArrayList<>();
                images.add(img);
                mGoodsDetiaBean.setImages(images);
                UIHelper.showCarGoodsSpecificationDialog(getActivity(), mGoodsDetiaBean);
            }

            @Override
            public void onFail(ApiException apiError) {
                ToastUtil.showBlackToastSucess(apiError.getMessage());
            }
        }, this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
                initCarList();
            }
        }, 1000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        int size = (total / per_page) + 1;
        if (page == size) {
            mRefreshLayout.endLoadingMore();
            ToastUtil.showBlackToastSucess("没有更多的数据了");
            return false;
        }

        GoodsApi.getBuyCarList(page + 1, new BaseCallback<BaseResponse<ShopCarListBean>>() {
            @Override
            public void onSucc(final BaseResponse<ShopCarListBean> result) {
                isAllSelect = result.data.getIs_all_selected();
                total = result.data.getTotal();
                per_page = result.data.getPer_page();
                setmCarNum(result.data.getProducts_count());
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mRefreshLayout == null) {
                            return;
                        }
                        mRefreshLayout.endLoadingMore();
                        mShoppingCarAdapter.addData(result.data.getData());
                        page++;
                    }
                }, 1000);
            }
        }, this);

        return true;
    }

    @SingleClick
    @OnClick({R.id.ll_cancel_or_select, R.id.tv_settlement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_cancel_or_select:
                //全选全取消 根据列表返回的参数判断
                String selected = "";
                if (isAllSelect == 0) {
                    selected = "1";
                } else {
                    selected = "0";
                }
                GoodsApi.allSelectOrCancel(selected, new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        initCarList();
                    }
                }, this);
                break;
            case R.id.tv_settlement:
                StringBuilder sb = new StringBuilder();
                for (ShopCarBean mShopCarBean : mShoppingCarAdapter.getData()) {
                    if (mShopCarBean.getSelected() == 1) {
                        sb.append(mShopCarBean.getId());
                        sb.append(",");
                    }
                }
                String selectSb = sb.toString();

                if (ObjectUtils.isEmpty(selectSb) && selectSb.length() == 0) {
                    ToastUtil.showBlackToastSucess("没有选中");
                    return;
                }
                final String select = selectSb.substring(0, selectSb.length() - 1);
                GoodsApi.carConfirm("", "", select, "", "", "", "", "0", new BaseCallback<BaseResponse<SettlementCenterBean>>() {
                    @Override
                    public void onSucc(BaseResponse<SettlementCenterBean> result) {
                        SettlementCenterBean mSettlementCenterBean = result.data;
                        mSettlementCenterBean.setIds(select);
                        if (type != 2) {
                            UIHelper.showSettlementCenterActivity(getActivity(), ContentType.SETTLEMENT_CENTER, new profileBean(result.data));
                        } else {
                            addFragment(SettlementCenterFragment.newInstance(result.data));
                        }
                    }

                    @Override
                    public void onFail(ApiException apiError) {
                        ToastUtil.showBlackToastSucess(apiError.getMessage());
                    }
                }, this);
                break;
        }
    }
}




