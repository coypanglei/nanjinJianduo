package com.shaoyue.weizhegou.module.goods.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.GoodsApi;
import com.shaoyue.weizhegou.api.remote.MessageApi;
import com.shaoyue.weizhegou.base.BaseCustomFragment;
import com.shaoyue.weizhegou.entity.TabEntity;
import com.shaoyue.weizhegou.entity.goods.AllGoodsBean;
import com.shaoyue.weizhegou.entity.goods.AllGoodsListBean;
import com.shaoyue.weizhegou.entity.home.HomeInitBean;
import com.shaoyue.weizhegou.entity.home.categoriesBean;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.entity.message.MessageUnreadBean;
import com.shaoyue.weizhegou.event.SearchContentEvent;
import com.shaoyue.weizhegou.event.SwitchTabEvent;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.goods.adapter.AllGoodsAdapter;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.util.ZMCache;
import com.shaoyue.weizhegou.widget.MyScrollView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.shaoyue.weizhegou.common.CommConfig.LIST_PAGE_SIZE;
import static com.shaoyue.weizhegou.router.UIHelper.HOME_INIT_BEAN;

public class AllGoodsFragment extends BaseCustomFragment {


    @BindView(R.id.rv_goods)
    RecyclerView mRvGoods;
    @BindView(R.id.image_mode)
    ImageView mImageMode;
    @BindView(R.id.tl_all_commodity)
    CommonTabLayout mTlAllCommodity;
    @BindView(R.id.tv_search_content)
    TextView mTvSearchContent;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.sv_one_list)
    SpringView mSvOneList;
    @BindView(R.id.msv_home_all)
    MyScrollView mMsvHomeAll;
    @BindView(R.id.image_new_goods)
    ImageView mImageNewGoods;
    @BindView(R.id.iv_sales_volume)
    ImageView mIvSalesVolume;
    @BindView(R.id.iv_price)
    ImageView mIvPrice;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    @BindView(R.id.empty_img)
    ImageView mEmptyImg;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.search_new_goods)
    TextView mSearchNewGoods;
    @BindView(R.id.tv_sales_volume)
    TextView mTvSalesVolume;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.search_default)
    TextView mSearchDefault;
    @BindView(R.id.iv_clear)
    ImageView mIvClear;
    @BindView(R.id.iv_info)
    ImageView mIvInfo;


    //序列 是否默认
    private boolean isDefault = true;

    //序列 是否新品
    private boolean isPosOrNeg = false;
    //类别
    private int mGoodsCategory;

    //sort_order（默认）, is_new（新品）, sales_sum（销量）, shop_price（价格）
    private String mSequence = "sort_order";
    //关键字
    private String mKeywords = "";

    private int page = 1;

    private int total = 0;

    private boolean isPushData;

    private AllGoodsAdapter mAllGoodsAdapter;
    //布局类型
    private int goodsType = 1;

    private int firstVisibleItemPosition = 0;//记录滑动的position值


    private HomeInitBean mHomeInitBean;


    private List<categoriesBean> mCategoryList;

    //滑块的数据
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    public static AllGoodsFragment newInstance() {
        Bundle args = new Bundle();
        AllGoodsFragment fragment = new AllGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goodcomment;
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

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mHomeInitBean = (HomeInitBean) ZMCache.get(getActivity()).getAsObject(HOME_INIT_BEAN);
        initView();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAllGoodsAdapter.getData().size() == 0) {
            startProgressDialog(true);
            initData();
        }
        MessageApi.getMessageUnread(new BaseCallback<BaseResponse<MessageUnreadBean>>() {
            @Override
            public void onSucc(BaseResponse<MessageUnreadBean> result) {
                if (result.data.getCount() == 0) {
                    mIvInfo.setImageResource(R.drawable.icon_info);
                } else {
                    mIvInfo.setImageResource(R.drawable.icon_red_msg);
                }
            }

            @Override
            public void onFail(ApiException apiError) {
                mIvInfo.setImageResource(R.drawable.icon_info);
            }
        }, this);
    }

    private void initView() {
        mEmptyImg.setImageResource(R.drawable.icon_empty_goods);
        mEmptyText.setText("暂无商品信息" + ToastUtil.getToastMsg());
        //商品列表 切换布局
        mRvGoods.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
        mAllGoodsAdapter = new AllGoodsAdapter(getActivity());
        mAllGoodsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);//设置动画效果
        mAllGoodsAdapter.setType(goodsType);//设置布局类型
        mRvGoods.setAdapter(mAllGoodsAdapter);
        mRvGoods.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AllGoodsBean mGoodsBean = (AllGoodsBean) adapter.getData().get(position);
                String goodsId = mGoodsBean.getId() + "";
                UIHelper.showGoodsDetailsActivity(getActivity(), ContentType.GOODS_DETAILS, new profileBean(goodsId));
            }
        });
        mRvGoods.setNestedScrollingEnabled(false);
        //导航条切换
        mCategoryList = mHomeInitBean.getCategoriesBeanList();
        int selectCategory = 0;
        for (int i = 0; i < mCategoryList.size(); i++) {
            mTabEntities.add(new TabEntity(mCategoryList.get(i).getName(), 0, 0));
            if (mCategoryList.get(i).getId() == AppMgr.getInstance().getSpSelectCategory()) {
                AppMgr.getInstance().setSpSelectCategory(-1);
                selectCategory = i;
            }
        }
        mGoodsCategory = mCategoryList.get(0).getId();
        mTlAllCommodity.setTabData(mTabEntities);
        mTlAllCommodity.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mGoodsCategory = mCategoryList.get(position).getId();
                initData();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mTlAllCommodity.setCurrentTab(selectCategory);

        mRvGoods.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//              item数量大于10，显示置顶图标
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
//                    获取第一个可见位置
                    firstVisibleItemPosition = linearManager.findFirstVisibleItemPosition();
                }
            }
        });

        mSvOneList.setType(SpringView.Type.FOLLOW);
        mSvOneList.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }

            @Override
            public void onLoadmore() {
                getMoreData();
            }
        });
        mSvOneList.setHeader(new DefaultHeader(getActivity()));
        mSvOneList.setFooter(new DefaultFooter(getActivity()));
        mMsvHomeAll.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (scrollY / 5 <= mLlTop.getHeight() / 2) {
                    mLlTop.setVisibility(View.VISIBLE);
                    setMargins(mLlTop, 0, -scrollY / 5, 0, -scrollY / 5);
                } else {
                    mLlTop.setVisibility(View.GONE);
                }
            }


        });

    }

    /**
     * 设置Margin
     *
     * @param v view
     * @param l 左
     * @param t 上
     * @param r 右
     * @param b 下
     * @Author: PangLei
     * @Date: 2018/9/12 14:03
     * @Description:工具
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
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
     * 更具现有的排序设置排序
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

    /**
     * 根据条件刷新数据
     */
    private void initData() {
        page = 1;
        GoodsApi.getAllGoodsList(LIST_PAGE_SIZE, page, mKeywords, mGoodsCategory, mSequence, isPosOrNeg, new BaseCallback<BaseResponse<AllGoodsListBean>>() {
            @Override
            public void onSucc(BaseResponse<AllGoodsListBean> result) {
                stopProgressDialog();
                total = result.data.getCount();
                isPushData = false;
                mAllGoodsAdapter.setNewData(result.data.getGoodList());
                mAllGoodsAdapter.setSize(mAllGoodsAdapter.getData().size());
                mAllGoodsAdapter.notifyDataSetChanged();
                mSvOneList.onFinishFreshAndLoad();
                if (total == 0) {
                    mSvOneList.setEnable(false);
                    mEmptyRelative.setVisibility(View.VISIBLE);
                } else {
                    mSvOneList.setEnable(true);
                    mEmptyRelative.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                stopProgressDialog();
                isPushData = false;
                mSvOneList.onFinishFreshAndLoad();
            }
        }, this);

    }

    /**
     * 获取更多数据
     */
    private void getMoreData() {
        int size = (total / 20) + 1;
        if (page == size) {
            ToastUtil.showErrorToast("没有更多的数据了");
            mSvOneList.onFinishFreshAndLoad();
            return;
        }
        GoodsApi.getAllGoodsList(LIST_PAGE_SIZE, page + 1, mKeywords, mGoodsCategory, mSequence, isPosOrNeg, new BaseCallback<BaseResponse<AllGoodsListBean>>() {
            @Override
            public void onSucc(final BaseResponse<AllGoodsListBean> result) {
                total = result.data.getCount();
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {

                        mAllGoodsAdapter.addData(result.data.getGoodList());
                        mAllGoodsAdapter.setSize(mAllGoodsAdapter.getData().size());
                        mAllGoodsAdapter.notifyDataSetChanged();
                        page++;
                        mSvOneList.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onFail(ApiException apiError) {
                mSvOneList.onFinishFreshAndLoad();
                ToastUtil.showErrorToast(apiError.getErrMsg());
            }
        }, this);
    }

    /**
     * 切换布局
     */
    private void switchMode() {
        if (goodsType == 1) {//垂直
            mImageMode.setImageResource(R.drawable.icon_mode_switch_gv);
            //1：设置布局类型
            mAllGoodsAdapter.setType(0);
            //2：设置对应的布局管理器
            mRvGoods.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false));
            //3：刷新adapter
            mRvGoods.scrollToPosition(firstVisibleItemPosition);//切换进入上次item位置
            mAllGoodsAdapter.notifyDataSetChanged();
            goodsType = 0;
        } else if (goodsType == 0) {//网格
            mImageMode.setImageResource(R.drawable.icon_mode_switch_lv);
            mAllGoodsAdapter.setType(1);
            mRvGoods.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
            mRvGoods.scrollToPosition(firstVisibleItemPosition);//切换进入上次item位置
            mAllGoodsAdapter.notifyDataSetChanged();
            goodsType = 1;
        }
    }

    /**
     * 搜索内容
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SearchContentEvent event) {
        if (ObjectUtils.isNotEmpty(event.getContent()) && event.getType() == 0) {
            mIvClear.setVisibility(View.VISIBLE);
            mTvSearchContent.setText(event.getContent());
            mKeywords = event.getContent();
            initData();
        }
    }

    /**
     * 跳转导航页
     *
     * @param switchTabEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void selectTab(SwitchTabEvent switchTabEvent) {
        if (switchTabEvent.getmTabPosition() != -1) {

            //导航条切换
            for (int i = 0; i < mCategoryList.size(); i++) {
                if (mCategoryList.get(i).getId() == switchTabEvent.getmTabPosition()) {
                    final int tabPosition = i;
                    ThreadUtil.runInUIThread(new Runnable() {
                        @Override
                        public void run() {
                            mTlAllCommodity.setCurrentTab(tabPosition);
                            mGoodsCategory = mCategoryList.get(tabPosition).getId();
                            initData();
                        }
                    }, 300);

                }

            }
        }
    }


    @OnClick({R.id.ll_default, R.id.ll_sales_volume, R.id.ll_new_goods, R.id.ll_price, R.id.ll_mode, R.id.ll_search, R.id.iv_clear
            , R.id.iv_info})
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
            case R.id.ll_mode:
                switchMode();
                break;
            case R.id.ll_search:
                UIHelper.showProfileCommonActivity(getActivity(), ContentType.SEARCH_SERVICES, new profileBean(mKeywords, 0));
                break;
            case R.id.iv_clear:
                mKeywords = "";
                mTvSearchContent.setText("search");
                initData();
                mIvClear.setVisibility(View.GONE);
                break;
            case R.id.iv_info:
                if (ObjectUtils.isNotEmpty(UserMgr.getInstance().getSessionId())) {
                    UIHelper.showProfileCommonActivity(getActivity(), ContentType.ACCOUTN_INFO);
                }
                break;
        }
    }


}
