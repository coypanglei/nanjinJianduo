package com.shaoyue.weizhegou.module.goods.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.GoodsApi;
import com.shaoyue.weizhegou.base.BaseCustomFragment;
import com.shaoyue.weizhegou.entity.goods.GoodsDetialBean;
import com.shaoyue.weizhegou.entity.goods.GoodsSpecAllBean;
import com.shaoyue.weizhegou.entity.goods.GoodsSpecNameBean;
import com.shaoyue.weizhegou.entity.goods.SpecInfoBean;
import com.shaoyue.weizhegou.entity.home.HomeInitBean;
import com.shaoyue.weizhegou.event.CarNumEvent;
import com.shaoyue.weizhegou.event.SelectSpecEvent;
import com.shaoyue.weizhegou.module.main.fragment.ShoppingCarFragment;
import com.shaoyue.weizhegou.module.main.widget.GlideImageLoader;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.WebViewUtils;
import com.shaoyue.weizhegou.util.XClick.SingleClick;
import com.shaoyue.weizhegou.util.ZMCache;
import com.shaoyue.weizhegou.util.ZMStrUtils;
import com.shaoyue.weizhegou.widget.AnimManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.shaoyue.weizhegou.router.UIHelper.HOME_INIT_BEAN;

/**
 * 作者：PangLei on 2019/4/18 0018 13:38
 * <p>
 * 邮箱：434604925@qq.com
 */
public class GoodsDetailsFragment extends BaseCustomFragment {
    @BindView(R.id.goods_banner)
    Banner mGoodsBanner;
    @BindView(R.id.tv_limited_time)
    TextView mTvLimitedTime;
    @BindView(R.id.tv_countdown_title)
    TextView mTvCountdownTitle;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.tv_price_range)
    TextView mTvPriceRange;
    @BindView(R.id.tv_original_price)
    TextView mTvOriginalPrice;
    @BindView(R.id.tv_current_price)
    TextView mTvCurrentPrice;
    @BindView(R.id.tv_in_stock)
    TextView mTvInStock;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_integral)
    TextView mTvIntegral;
    @BindView(R.id.tv_specification_one)
    TextView mTvSpecificationOne;
    @BindView(R.id.tv_specification_two)
    TextView mTvSpecificationTwo;

    @BindView(R.id.tv_specification_three)
    TextView mTvSpecificationThree;
    @BindView(R.id.tv_specification)
    TextView mTvSpecification;
    @BindView(R.id.iv_shopping_notes)
    ImageView mIvShoppingNotes;
    @BindView(R.id.ll_buy)
    LinearLayout mLlBuy;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_shopping_cart)
    TextView mTvShoppingCart;
    @BindView(R.id.rl_specification)
    RelativeLayout mRlSpecification;
    @BindView(R.id.rl_all)
    RelativeLayout mRlAll;
    @BindView(R.id.tv_buy_car_num)
    TextView mTvBuyCarNum;
    @BindView(R.id.tv_invalid)
    TextView mTvInvalid;
    @BindView(R.id.tv_activity_name)
    TextView mTvActivityName;
    @BindView(R.id.tv_discount)
    TextView mTvDiscount;
    @BindView(R.id.rl_start_time)
    RelativeLayout mRlStartTime;
    @BindView(R.id.tv_order)
    TextView mTvOrder;


    private String goodsId;
    //购买数量
    private int buyNum = 1;

    private CountDownTimer mCountdownTimer;
    private boolean isStart = false;
    private List<String> mSpecList = new ArrayList<>();

    private GoodsDetialBean mGoodsDetiaBean;
    private List<GoodsSpecNameBean> goodsSpecNameBeans;
    private List<SpecInfoBean> mSpecInfoBeanList;
    private View mBuyImg;
    //是否在进行动画
    private boolean isBuyCarAnim = true;
    //是否点击
    private boolean isClick = false;

    public static GoodsDetailsFragment newInstance(String goodsId) {
        Bundle args = new Bundle();
        args.putString(UIHelper.GOODS_ID, goodsId);
        GoodsDetailsFragment fragment = new GoodsDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoodsBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoodsBanner.stopAutoPlay();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Bundle bundle = getArguments();
        if (ObjectUtils.isNotEmpty(bundle)) {
            goodsId = bundle.getString(UIHelper.GOODS_ID);
        } else {
            removeFragment();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_details;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        initView();
    }

    private void initView() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (ObjectUtils.isNotEmpty(mCountdownTimer)) {
            mCountdownTimer.cancel();
            mCountdownTimer = null;
        }

    }

    @Override
    protected void loadData() {
        super.loadData();
        getGoodsInfo();
        getGoodsSpec();
    }

    @Override
    public void onResume() {
        super.onResume();
        getGoodsInfo();
        getCarNum();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(SelectSpecEvent event) {
        mSpecList = event.getmLsit();
        buyNum = event.getNum();
        for (SpecInfoBean specInfoBean : mSpecInfoBeanList) {
            if (ZMStrUtils.equalList(mSpecList, specInfoBean.getKey())) {
                mTvSpecification.setVisibility(View.VISIBLE);
                mTvSpecification.setText("  已选 " + specInfoBean.getKeyName());
                mTvSpecificationOne.setVisibility(View.GONE);
                mTvSpecificationTwo.setVisibility(View.GONE);
                mTvSpecificationThree.setVisibility(View.GONE);
            } else {
                mTvSpecification.setVisibility(View.GONE);
                mTvSpecificationOne.setVisibility(View.VISIBLE);
                mTvSpecificationTwo.setVisibility(View.VISIBLE);
                mTvSpecificationThree.setVisibility(View.VISIBLE);
            }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void addCar(final CarNumEvent event) {
        if (event.getType() == 4) {
            //定义一个RelativeLayout组件
            if (isBuyCarAnim) {
                isBuyCarAnim = false;
                RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //与父组件顶部对齐
                lp1.addRule(RelativeLayout.CENTER_IN_PARENT);
                //横向居中，是
                // btn1 位于父 View 的顶部，在父 View 中水平居中
                LayoutInflater mInflater = LayoutInflater.from(getActivity());
                mBuyImg = mInflater.inflate(R.layout.fragment_shop_car, null);

                mBuyImg.setVisibility(View.INVISIBLE);
                mRlAll.addView(mBuyImg, lp1);
                final AnimatorSet animatorSet = new AnimatorSet();//组合动画
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(mBuyImg, "scaleX", 3f, 1f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(mBuyImg, "scaleY", 3f, 1f);

                animatorSet.setDuration(300);
                animatorSet.setInterpolator(new DecelerateInterpolator());
                animatorSet.play(scaleX).with(scaleY);//两个动画同时开始
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mBuyImg.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        mRlAll.removeView(mBuyImg);
                        AnimManager animManager = new AnimManager.Builder()
                                .with(getActivity())
                                .startView(mBuyImg)
                                .time(500)
                                .endView(mTvShoppingCart)
                                .animView(mBuyImg)
                                .listener(new AnimManager.AnimListener() {
                                    @Override
                                    public void setAnimBegin(AnimManager a) {

                                    }

                                    @Override
                                    public void setAnimEnd(AnimManager a) {
                                        isBuyCarAnim = true;
                                        mTvBuyCarNum.setVisibility(View.VISIBLE);
                                        mTvBuyCarNum.setText(event.getNum());


                                    }
                                })
                                .build();

                        animManager.startAnim();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                if (ObjectUtils.isNotEmpty(event.getNum())) {
                    ThreadUtil.runInUIThread(new Runnable() {
                        @Override
                        public void run() {
                            animatorSet.start();
                        }
                    }, 500);
                } else {
                    animatorSet.start();
                }
            }
        }
    }


    /**
     * 获取商品规格
     */
    private void getGoodsSpec() {
        GoodsApi.getSpecInfo(goodsId, new BaseCallback<BaseResponse<GoodsSpecAllBean>>() {
            @Override
            public void onSucc(BaseResponse<GoodsSpecAllBean> result) {
                isClick = true;
                goodsSpecNameBeans = result.data.getGoodsSpecNameBeanList();
                mSpecInfoBeanList = result.data.getmSpecInfoBeanList();
                //如果规格为空不能点击弹窗
                if (goodsSpecNameBeans.size() > 0) {
                    //设置前两个规格，后一个显示数量
                    mTvSpecificationOne.setVisibility(View.VISIBLE);
                    mTvSpecificationOne.setText(goodsSpecNameBeans.get(0).getName());
                    if (goodsSpecNameBeans.size() > 1) {
                        mTvSpecificationTwo.setVisibility(View.VISIBLE);
                        mTvSpecificationTwo.setText(goodsSpecNameBeans.get(1).getName());
                        if (goodsSpecNameBeans.size() > 2) {
                            mTvSpecificationThree.setVisibility(View.VISIBLE);
                            mTvSpecificationThree.setText(goodsSpecNameBeans.get(2).getName());
                        }
                    }
                } else {
                    mTvSpecification.setText("   已选 1 件商品");
                }


            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                isClick = false;
            }
        }, this);
    }

    /**
     * 获取商品信息
     */
    private void getGoodsInfo() {
        GoodsApi.getProductsInfo(goodsId, new BaseCallback<BaseResponse<GoodsDetialBean>>() {
            @Override
            public void onSucc(final BaseResponse<GoodsDetialBean> result) {
                try {
                    mGoodsDetiaBean = result.data;
                    //设置Banner
                    if (mGoodsDetiaBean.getImages().size() > 0) {
                        mGoodsBanner.setImages(result.data.getImages());
                        mGoodsBanner.setImageLoader(new GlideImageLoader(R.drawable.icon_default_placeholder, true));
                        mGoodsBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                        mGoodsBanner.setDelayTime(5000);
                        mGoodsBanner.start();
                    }
                    //界面固定详情
                    //价格区间
                    mTvPriceRange.setText(mGoodsDetiaBean.getPromotePrice());
                    //现价
                    mTvCurrentPrice.setText("现价：" + mGoodsDetiaBean.getShopPrice());
                    mTvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    //原价
                    mTvOriginalPrice.setText("原价：" + mGoodsDetiaBean.getMarketPrice());
                    //标题
                    mTvTitle.setText(mGoodsDetiaBean.getProductsName());
                    //库存
                    mTvInStock.setText("库存：" + mGoodsDetiaBean.getStoreCount() + "件");
                    //赠送的积分
                    mTvIntegral.setText("积分：" + mGoodsDetiaBean.getGiveIntegral());
                    if (mGoodsDetiaBean.getIs_time_purchase() == 1) {
                        //设置定时器
                        mRlStartTime.setVisibility(View.VISIBLE);
                        setTime(result);
                    }
                    //展示webview的详情
                    if (ObjectUtils.isNotEmpty(mGoodsDetiaBean.getGoodsContent())) {
                        initWebView();
                        mWebview.setVerticalScrollBarEnabled(false);
                        mWebview.loadDataWithBaseURL("http://webhost.net", WebViewUtils.getNewContent(result.data.getGoodsContent()), "text/html", "UTF-8", null);
                    }

                    HomeInitBean homeInitBean = (HomeInitBean) ZMCache.get(getActivity()).getAsObject(HOME_INIT_BEAN);
                    if (ObjectUtils.isNotEmpty(homeInitBean.getShoppingNotes())) {
                        mIvShoppingNotes.setVisibility(View.VISIBLE);
                        GlideNewImageLoader.displayImageNoDefault(getActivity(), mIvShoppingNotes, homeInitBean.getShoppingNotes());
                    }
                    //购物车显示
                    mLlBottom.setVisibility(View.VISIBLE);
                    if (mGoodsDetiaBean.getStoreCount() == 0) {
                        mLlBuy.setVisibility(View.GONE);
                    }
                    if (ObjectUtils.isNotEmpty(mGoodsDetiaBean.getActivityName())) {
                        mTvActivityName.setVisibility(View.VISIBLE);
                        mTvActivityName.setText(mGoodsDetiaBean.getActivityName());
                    }
                    if (ObjectUtils.isNotEmpty(mGoodsDetiaBean.getDiscount())) {
                        mTvDiscount.setVisibility(View.VISIBLE);
                        mTvDiscount.setText(mGoodsDetiaBean.getDiscount());
                    }

                } catch (Exception e) {

                }
            }
        }, this);
    }

    /**
     * 获得购物车数量
     */
    private void getCarNum() {
        GoodsApi.getCarNum(new BaseCallback<BaseResponse<Integer>>() {
            @Override
            public void onSucc(BaseResponse<Integer> result) {
                if (ObjectUtils.isNotEmpty(mTvBuyCarNum)) {
                    if (result.data != 0) {
                        mTvBuyCarNum.setVisibility(View.VISIBLE);
                        if (result.data > 99) {
                            mTvBuyCarNum.setText("99+");
                        } else {
                            mTvBuyCarNum.setText(result.data + "");
                        }
                    } else {
                        mTvBuyCarNum.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }, this);
    }


    private void setTime(final BaseResponse<GoodsDetialBean> result) {
        long time = 0;
        if (result.timeStamp - result.data.getPurchase_start_time() < 0) {
            time = (result.data.getPurchase_start_time() - result.timeStamp) * 1000;
            mTvCountdownTitle.setText("限时购开始时间仅剩");
            mTvOrder.setVisibility(View.GONE);
            isStart = true;
        } else {
            mTvOrder.setVisibility(View.VISIBLE);

            mTvCountdownTitle.setText("限时购结束时间仅剩");
            time = (result.data.getPurchase_end_time() - result.timeStamp) * 1000;
            isStart = false;
        }


        mCountdownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvLimitedTime.setText(WebViewUtils.generateTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                mTvOrder.setVisibility(View.VISIBLE);
                if (isStart) {
                    mTvCountdownTitle.setText("限时购结束时间仅剩");
                    mCountdownTimer = new CountDownTimer((result.data.getPurchase_end_time() - result.timeStamp) * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            mTvLimitedTime.setText(WebViewUtils.generateTime(millisUntilFinished));
                        }

                        @Override
                        public void onFinish() {
                            mTvInvalid.setText("商品已下架");
                            mLlBuy.setVisibility(View.GONE);
                            if (ObjectUtils.isNotEmpty(mCountdownTimer)) {
                                mCountdownTimer.cancel();
                                mCountdownTimer = null;
                            }
                        }
                    };
                    mCountdownTimer.start();
                } else {
                    mTvInvalid.setText("商品已下架");
                    mLlBuy.setVisibility(View.GONE);
                    if (ObjectUtils.isNotEmpty(mCountdownTimer)) {
                        mCountdownTimer.cancel();
                        mCountdownTimer = null;
                    }
                }
            }
        };
        mCountdownTimer.start();
    }

    private void initWebView() {
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true); // 启用支持javascript
        webSettings.setAllowFileAccess(true); // 可以访问文件
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSettings.setTextZoom(200);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        String ua = webSettings.getUserAgentString();
        webSettings.setUserAgentString(ua + "(MDAPP)");
        // 先加载网页再加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            mWebview.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWebview.getSettings().setLoadsImagesAutomatically(false);
        }

        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 得到 URL 可以传给应用中的某个 WebView 页面加载显示
                return true;
            }
        });
    }


    /**
     * 展示规格弹窗
     */
    private void showSpecDaliog(int type) {

        //库存位零
        if (mGoodsDetiaBean.getStoreCount() == 0) {
            return;
        }
        //如果有规格
        if (ObjectUtils.isNotEmpty(mSpecInfoBeanList) && mSpecInfoBeanList.size() > 0) {
            mGoodsDetiaBean.setGoodsSpecNameBeanList(goodsSpecNameBeans);
            mGoodsDetiaBean.setmSpecInfoBeanList(mSpecInfoBeanList);
            //改变的购买数量
            mGoodsDetiaBean.setBuyNum(buyNum);
            //改变的规格
            mGoodsDetiaBean.setSelectSpecItems(mSpecList);
            //跳转的位置
            mGoodsDetiaBean.setType(type);
            UIHelper.showGoodsSpecificationDialog(getActivity(), mGoodsDetiaBean);
        } else {
            if (type == 1) {
//                AddCar();
            } else if (type == 0 || type == 2) {
                if (isClick) {
                    mGoodsDetiaBean.setGoodsSpecNameBeanList(goodsSpecNameBeans);
                    mGoodsDetiaBean.setmSpecInfoBeanList(mSpecInfoBeanList);
                    //改变的购买数量
                    mGoodsDetiaBean.setBuyNum(buyNum);
                    //改变的规格
                    mGoodsDetiaBean.setSelectSpecItems(mSpecList);
                    //跳转的位置
                    mGoodsDetiaBean.setType(type);
                    UIHelper.showGoodsSpecificationDialog(getActivity(), mGoodsDetiaBean);
                }
            }
        }
    }


    @SingleClick(2000)
    @OnClick({R.id.tv_gift, R.id.rl_specification, R.id.iv_back, R.id.iv_go_share, R.id.iv_qr_code, R.id.tv_home
            , R.id.tv_add_to_shopping_cart, R.id.tv_order, R.id.rl_go_buy_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_gift:
                break;
            case R.id.rl_specification:
                showSpecDaliog(0);
                break;
            case R.id.iv_back:
                removeFragment();
                break;
            case R.id.iv_go_share:
                UIHelper.showShareDialog(getActivity(), goodsId);
                break;
            case R.id.iv_qr_code:
                UIHelper.showGoodsShareQcDialog(getActivity(), goodsId);
                break;
            case R.id.tv_home:
                UIHelper.showMainPage(getActivity());
                break;
            case R.id.tv_add_to_shopping_cart:
                showSpecDaliog(0);
                break;
            case R.id.tv_order:
                showSpecDaliog(2);
                break;
            case R.id.rl_go_buy_car:
                addFragment(ShoppingCarFragment.newInstance(2));
                break;

        }
    }


}
