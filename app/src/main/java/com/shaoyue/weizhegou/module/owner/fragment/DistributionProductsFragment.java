package com.shaoyue.weizhegou.module.owner.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.GoodsApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.goods.AllGoodsBean;
import com.shaoyue.weizhegou.entity.goods.AllGoodsListBean;
import com.shaoyue.weizhegou.entity.goods.SettlementCenterBean;
import com.shaoyue.weizhegou.entity.home.HomeInitBean;
import com.shaoyue.weizhegou.entity.home.categoriesBean;
import com.shaoyue.weizhegou.entity.home.profileBean;

import com.shaoyue.weizhegou.event.SearchContentEvent;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.goods.adapter.DistributionProductsAdapter;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.util.ZMCache;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static com.shaoyue.weizhegou.router.UIHelper.HOME_INIT_BEAN;

/**
 * 作者：PangLei on 2019/5/30 0030 14:09
 * <p>
 * 邮箱：434604925@qq.com
 */
public class DistributionProductsFragment extends BaseTitleFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.tv_commission)
    TextView mTvCommission;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.iv_price)
    ImageView mIvPrice;
    @BindView(R.id.ll_price)
    LinearLayout mLlPrice;
    @BindView(R.id.image_mode)
    ImageView mImageMode;
    @BindView(R.id.ll_mode)
    LinearLayout mLlMode;
    @BindView(R.id.select_data_linear)
    LinearLayout mSelectDataLinear;
    @BindView(R.id.line_dropmenu)
    LinearLayout mLineDropmenu;
    @BindView(R.id.empty_img)
    ImageView mEmptyImg;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.search_default)
    TextView mSearchDefault;
    @BindView(R.id.ll_default)
    LinearLayout mLlDefault;
    @BindView(R.id.search_new_goods)
    TextView mSearchNewGoods;
    @BindView(R.id.image_new_goods)
    ImageView mImageNewGoods;
    @BindView(R.id.ll_new_goods)
    LinearLayout mLlNewGoods;
    @BindView(R.id.tv_sales_volume)
    TextView mTvSalesVolume;
    @BindView(R.id.iv_sales_volume)
    ImageView mIvSalesVolume;
    @BindView(R.id.ll_sales_volume)
    LinearLayout mLlSalesVolume;
    @BindView(R.id.view_line)
    View lineView;

    private List<categoriesBean> mCategoryList;
    private HomeInitBean mHomeInitBean;
    //sort_order（默认）, is_new（新品）, sales_sum（销量）, shop_price（价格）
    private String mSequence = "sort_order";

    //序列 是否默认
    private boolean isDefault = true;
    //关键字
    private String mKeywords = "";
    //类别
    private int mGoodsCategory;

    private boolean isPushData;
    //序列 是否新品
    private boolean isPosOrNeg = false;
    DistributionProductsAdapter mDistributionAdapter;
    private int page = 1;
    private int total = 0;
    private int per_page = 20;


    public static DistributionProductsFragment newInstance() {
        Bundle args = new Bundle();
        DistributionProductsFragment fragment = new DistributionProductsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_distribution_products;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("分销产品").setRightBtnV3(R.drawable.icon_search, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.SEARCH_SERVICES, new profileBean(mKeywords, 1));
            }
        });
        mLlMode.setVisibility(View.INVISIBLE);
        lineView.setVisibility(View.INVISIBLE);
        mTvCommission.setText("我的佣金：￥" + UserMgr.getInstance().getSP_COMMISSION_BAlANCE());
        initView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    /**
     * 初始化View
     */
    private void initView() {
        mEmptyImg.setImageResource(R.drawable.icon_empty_goods);
        mEmptyText.setText("暂无商品信息"+ToastUtil.getToastMsg());
        mHomeInitBean = (HomeInitBean) ZMCache.get(getActivity()).getAsObject(HOME_INIT_BEAN);
        mDistributionAdapter = new DistributionProductsAdapter();
        mDistributionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AllGoodsBean mGoodsBean = (AllGoodsBean) adapter.getData().get(position);
                String goodsId = mGoodsBean.getId() + "";
                UIHelper.showGoodsDetailsActivity(getActivity(), ContentType.GOODS_DETAILS, new profileBean(goodsId));
            }
        });
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContent.setAdapter(mDistributionAdapter);
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        mRefreshLayout.setIsShowLoadingMoreView(true);
        mCategoryList = mHomeInitBean.getCategoriesBeanList();
        mGoodsCategory = mCategoryList.get(0).getId();
    }

    /**
     * 搜索内容
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SearchContentEvent event) {
        if (ObjectUtils.isNotEmpty(event.getContent()) && event.getType() == 1) {
            mKeywords = event.getContent();
            initData();
        }
    }

    /**
     * 图片默认
     */
    private void setDefault() {
        isPushData = true;
        mIvSalesVolume.setImageResource(R.drawable.icon_screening_default);
        mImageNewGoods.setImageResource(R.drawable.icon_screening_default);
        mIvPrice.setImageResource(R.drawable.icon_screening_default);
    }


    /**
     * 根据现有的排序设置排序
     */
    private void ivSetIvSort(ImageView mImageView) {
        if (isPosOrNeg) {
            isPosOrNeg = false;
            mImageView.setImageResource(R.drawable.icon_screening_flashback);
        } else {
            isPosOrNeg = true;
            mImageView.setImageResource(R.drawable.icon_screening_positive);
        }

    }

    @Override
    protected void loadData() {
        super.loadData();
        startProgressDialog(true);
        initData();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
                initData();
            }
        }, 1000);
    }


    /**
     * 根据条件刷新数据
     */
    private void initData() {
        page = 1;
        GoodsApi.getAllGoodsList(per_page, page, mKeywords, mGoodsCategory, mSequence, isPosOrNeg, new BaseCallback<BaseResponse<AllGoodsListBean>>() {
            @Override
            public void onSucc(BaseResponse<AllGoodsListBean> result) {
                total = result.data.getCount();
                isPushData = false;
                mDistributionAdapter.setNewData(result.data.getGoodList());
                mDistributionAdapter.notifyDataSetChanged();
                stopProgressDialog();
                if (total == 0) {
                    mEmptyRelative.setVisibility(View.VISIBLE);
                } else {
                    mEmptyRelative.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                isPushData = false;
                stopProgressDialog();
                mEmptyRelative.setVisibility(View.VISIBLE);
            }
        }, this);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        int size = (total / per_page) + 1;

        if (page == size) {
            mRefreshLayout.endLoadingMore();
            ToastUtil.showErrorToast("没有更多的数据了");
            return false;
        }

        GoodsApi.getAllGoodsList(per_page, page + 1, mKeywords, mGoodsCategory, mSequence, isPosOrNeg, new BaseCallback<BaseResponse<AllGoodsListBean>>() {
            @Override
            public void onSucc(final BaseResponse<AllGoodsListBean> result) {
                LogUtils.e(total);
                total = result.data.getCount();
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mRefreshLayout == null) {
                            return;
                        }
                        mRefreshLayout.endLoadingMore();
                        mDistributionAdapter.addData(result.data.getGoodList());
                        page++;
                    }
                }, 1000);
            }
        }, this);

        return true;
    }

    @OnClick({R.id.ll_default, R.id.ll_sales_volume, R.id.ll_new_goods, R.id.ll_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //默认
            case R.id.ll_default:
                if (isPushData) {
                    return;
                }
                mSearchDefault.setTextColor(getResources().getColor(R.color.color_4a90e2));
                mTvSalesVolume.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                mSearchNewGoods.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                mTvPrice.setTextColor(getResources().getColor(R.color.color_4a4a4a));

                setDefault();
                mSequence = "sort_order";
                isDefault = true;
                isPosOrNeg = false;
                initData();
                break;
            //新品
            case R.id.ll_new_goods:
                if (isPushData) {
                    return;
                }
                mSearchDefault.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                mTvSalesVolume.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                mSearchNewGoods.setTextColor(getResources().getColor(R.color.color_4a90e2));
                mTvPrice.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                setDefault();
                if (!mSequence.equals("is_new")) {
                    isPosOrNeg = true;
                }
                mSequence = "is_new";

                if (isDefault) {
                    mImageNewGoods.setImageResource(R.drawable.icon_screening_flashback);
                    isPosOrNeg = false;
                    isDefault = false;
                    initData();
                    return;
                }
                ivSetIvSort(mImageNewGoods);
                initData();
                break;
            //销量
            case R.id.ll_sales_volume:
                if (isPushData) {
                    return;
                }
                mSearchDefault.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                mTvSalesVolume.setTextColor(getResources().getColor(R.color.color_4a90e2));
                mSearchNewGoods.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                mTvPrice.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                setDefault();
                if (!mSequence.equals("sales_sum")) {
                    isPosOrNeg = true;
                }
                mSequence = "sales_sum";
                if (isDefault) {
                    mIvSalesVolume.setImageResource(R.drawable.icon_screening_flashback);
                    isPosOrNeg = false;
                    isDefault = false;
                    initData();
                    return;
                }
                ivSetIvSort(mIvSalesVolume);
                initData();
                break;
            //价格
            case R.id.ll_price:
                if (isPushData) {
                    return;
                }
                mSearchDefault.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                mTvSalesVolume.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                mSearchNewGoods.setTextColor(getResources().getColor(R.color.color_4a4a4a));
                mTvPrice.setTextColor(getResources().getColor(R.color.color_4a90e2));
                setDefault();
                if (!mSequence.equals("shop_price")) {
                    isPosOrNeg = true;
                }
                mSequence = "shop_price";
                if (isDefault) {
                    mIvPrice.setImageResource(R.drawable.icon_screening_flashback);
                    isPosOrNeg = false;
                    isDefault = false;
                    initData();
                    return;
                }
                ivSetIvSort(mIvPrice);
                initData();
                break;

        }
    }


}
