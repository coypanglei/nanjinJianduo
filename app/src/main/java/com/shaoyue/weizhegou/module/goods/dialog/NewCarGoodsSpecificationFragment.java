package com.shaoyue.weizhegou.module.goods.dialog;

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
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.GoodsApi;
import com.shaoyue.weizhegou.entity.goods.AddAndSubtractBean;
import com.shaoyue.weizhegou.entity.goods.GoodsDetialBean;
import com.shaoyue.weizhegou.entity.goods.GoodsSpecNameBean;
import com.shaoyue.weizhegou.entity.goods.GoodsSpecificationBean;
import com.shaoyue.weizhegou.entity.goods.SettlementCenterBean;
import com.shaoyue.weizhegou.entity.goods.SpecInfoBean;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.event.AddOrRemoveEvent;
import com.shaoyue.weizhegou.event.CarNumEvent;
import com.shaoyue.weizhegou.event.SelectSpecEvent;
import com.shaoyue.weizhegou.module.goods.adapter.GoodSpecAdapter;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.util.XClick.SingleClick;
import com.shaoyue.weizhegou.util.ZMStrUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NewCarGoodsSpecificationFragment extends DialogFragment implements DialogInterface.OnKeyListener {
    @BindView(R.id.tv_price_range)
    TextView mTvPriceRange;
    @BindView(R.id.tv_in_stock)
    TextView mTvInStock;
    Unbinder unbinder;
    List<GoodsSpecNameBean> goodsSpecNameBeans = new ArrayList<>();
    List<SpecInfoBean> mSpecInfoList = new ArrayList<>();

    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_all_spec)
    TextView mTvAllSpec;
    //未选中规格的集合
    List<GoodsSpecNameBean> mSeletSpecNames = new ArrayList<>();
    @BindView(R.id.rv_goods_spec)
    RecyclerView mRvGoodsSpec;

    @BindView(R.id.ll_buy)
    LinearLayout mLlBuy;


    //选中规格的id的集合
    private List<String> mSelectSpec = new ArrayList<>();

    //选中的价格的集合
    private int selectPrice = 0;

    //库存
    private int inStock = 0;

    private GoodSpecAdapter mAdapter;
    //商品详情
    private GoodsDetialBean mGoodsBetialBean;
    private String defaultImg = "";
    private String img = "";
    View footerView;
    //选择购买的数量
    private int mBuyNum = 1;
    private TextView mlimitTv;

    //跳转界面
    private int type = 0;

    public static NewCarGoodsSpecificationFragment newInstance(GoodsDetialBean goodsDetialBean) {
        Bundle args = new Bundle();
        args.putSerializable("goodsDetialBean", goodsDetialBean);
        NewCarGoodsSpecificationFragment fragment = new NewCarGoodsSpecificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            mGoodsBetialBean = (GoodsDetialBean) getArguments().getSerializable("goodsDetialBean");
            goodsSpecNameBeans = mGoodsBetialBean.getGoodsSpecNameBeanList();
            mSpecInfoList = mGoodsBetialBean.getmSpecInfoBeanList();
            mSelectSpec = mGoodsBetialBean.getSelectSpecItems();

            mBuyNum = mGoodsBetialBean.getBuyNum();
            type = mGoodsBetialBean.getType();
            //默认图片
            defaultImg = mGoodsBetialBean.getImages().get(0);
            img = defaultImg;
        } else {
            dismiss();
        }


    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_goods_specification_new, null);
        unbinder = ButterKnife.bind(this, view);
        initView(dialog, view);
        GlideNewImageLoader.displayImageNoCache(getActivity(), mIvGoods, img);
        LogUtils.e(type);
        if (type == 0) {
            mLlBuy.setVisibility(View.VISIBLE);
        }
        //价格区间
        mTvPriceRange.setText(mGoodsBetialBean.getPromotePrice());
        //库存
        inStock = mGoodsBetialBean.getStoreCount();
        mTvInStock.setText("库存：" + mGoodsBetialBean.getStoreCount() + "件");
        getSpecDefault();
        mAdapter = new GoodSpecAdapter(goodsSpecNameBeans);
        mAdapter.setmSpecInfoList(mSpecInfoList);
        mAdapter.setmSelectSpecs(mSelectSpec);
        mRvGoodsSpec.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvGoodsSpec.setAdapter(mAdapter);

        footerView = inflater.inflate(R.layout.buy_num_footview, (ViewGroup) mRvGoodsSpec.getParent(), false);
        //可优化 起卖 限购数量不是实时的

        mlimitTv = footerView.findViewById(R.id.tv_limit);

        String start_limit = "";
        String purchase_limit = "";
        if (ObjectUtils.isNotEmpty(mGoodsBetialBean.getStarting_quantity()) && mGoodsBetialBean.getStarting_quantity() != 1) {
            start_limit = mGoodsBetialBean.getStarting_quantity() + "件起卖";
        }
        if (ObjectUtils.isNotEmpty(mGoodsBetialBean.getPurchase_limit()) && mGoodsBetialBean.getPurchase_limit() != 0) {
            purchase_limit = "限购" + mGoodsBetialBean.getPurchase_limit() + "件";
        }
        if (ObjectUtils.isNotEmpty(start_limit) || ObjectUtils.isNotEmpty(purchase_limit)) {
            mlimitTv.setText("(" + start_limit + purchase_limit + ")");
        }


        mAdapter.addFooterView(footerView);
        //循环把所有点击加上
        if (goodsSpecNameBeans.size() > 0) {
            for (GoodsSpecNameBean goodsSpecNameBean : goodsSpecNameBeans) {
                for (GoodsSpecificationBean goodsSpecificationBean : goodsSpecNameBean.getSpecItemList()) {
                    String id = goodsSpecificationBean.getId() + "";
                    if (mSelectSpec.contains(id)) {
                        goodsSpecificationBean.setType(1);
                        getImgOrInfo(goodsSpecificationBean, goodsSpecNameBean);
                    }
                }
            }
        }
        setNumUpperlimit();
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        selectPrice = 0;
        unbinder.unbind();
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


    /**
     * 设置规格默认
     */
    private void getSpecDefault() {
        mSeletSpecNames.addAll(goodsSpecNameBeans);
        appendSpec();
    }


    /**
     * 规格字符串拼接
     */
    private void appendSpec() {
        StringBuilder sb = new StringBuilder();
        for (GoodsSpecNameBean goodsSpecNameBean : goodsSpecNameBeans) {
            if (mSeletSpecNames.contains(goodsSpecNameBean)) {
                sb.append(goodsSpecNameBean.getName() + ",");
            }
        }
        String selectSpecSb = sb.toString();

        selectSpecSb = selectSpecSb.length() == 0 ? "" : "选择 " + selectSpecSb.substring(0, selectSpecSb.length() - 1);
        mTvAllSpec.setText(selectSpecSb);
    }


    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(AddOrRemoveEvent addOrRemoveEvent) {
        GoodsSpecificationBean mGoodsSpecificationBean = addOrRemoveEvent.getmGoodsSpecBean();
        mSelectSpec = addOrRemoveEvent.getmSelectSpecs();
        mAdapter.notifyDataSetChanged();
        if (ObjectUtils.isNotEmpty(mGoodsSpecificationBean)) {
            if (mGoodsSpecificationBean.getType() == 0) {
                setDefaultText(mGoodsSpecificationBean, addOrRemoveEvent.getGoodsSpecNameBean());
            } else if (mGoodsSpecificationBean.getType() == 1) {
                getImgOrInfo(mGoodsSpecificationBean, addOrRemoveEvent.getGoodsSpecNameBean());
            }
        }
    }


    /**
     * 设置默认的文本
     */
    private void setDefaultText(GoodsSpecificationBean goodsSpecBean, GoodsSpecNameBean goodsSpecNameBean) {

        if (ObjectUtils.isNotEmpty(goodsSpecBean.getImg())) {
            GlideNewImageLoader.displayImageNoCache(getActivity(), mIvGoods, defaultImg);
        }

        EventBus.getDefault().post(new SelectSpecEvent(mSelectSpec, mBuyNum));
        //价格区间
        mTvPriceRange.setText(mGoodsBetialBean.getPromotePrice());
        selectPrice = 0;
        for (SpecInfoBean specInfoBean : mSpecInfoList) {
            if (specInfoBean.getKey().containsAll(mSelectSpec)) {
                selectPrice = selectPrice + specInfoBean.getStoreCount();
                //库存
                inStock = selectPrice;
                mTvInStock.setText("库存：" + selectPrice + "件");
            }
        }

        //根据key把当前选中置空
        mSeletSpecNames.add(goodsSpecNameBean);
        appendSpec();


    }

    /**
     * 设置上限
     */
    private void setNumUpperlimit() {

    }

    /**
     * 获取图片，显示过就置回默认
     * 根据所选的规格获取价格
     *
     * @param
     */
    private void getImgOrInfo(GoodsSpecificationBean goodsSpecBean, GoodsSpecNameBean goodsSpecNameBean) {
        img = goodsSpecBean.getImg();

        if (ObjectUtils.isNotEmpty(img)) {
            GlideNewImageLoader.displayImageNoCache(getActivity(), mIvGoods, img);
        } else {
            img = defaultImg;
        }
        //加入选中item的库存

        EventBus.getDefault().post(new SelectSpecEvent(mSelectSpec, mBuyNum));
        //根据key把当前选中置空
        mSeletSpecNames.remove(goodsSpecNameBean);
        appendSpec();

        selectPrice = 0;
        //比较是否所有的都选中否
        for (SpecInfoBean specInfoBean : mSpecInfoList) {
            if (ZMStrUtils.equalList(mSelectSpec, specInfoBean.getKey())) {
                //价格区间
                mTvPriceRange.setText(specInfoBean.getShopPrice());
                //库存
                inStock = specInfoBean.getStoreCount();
                mTvInStock.setText("库存：" + specInfoBean.getStoreCount() + "件");
                mTvAllSpec.setText("已选：" + specInfoBean.getKeyName());




            }
            if (specInfoBean.getKey().containsAll(mSelectSpec)) {
                selectPrice = selectPrice + specInfoBean.getStoreCount();
                //库存
                inStock = selectPrice;
                mTvInStock.setText("库存：" + selectPrice + "件");
            }
        }


    }

    /**
     * 加入购物车
     */
    private void AddCar() {
        //比较是否所有的都选中否
        String id = "";
        for (SpecInfoBean specInfoBean : mSpecInfoList) {
            if (ZMStrUtils.equalList(mSelectSpec, specInfoBean.getKey())) {
                id = specInfoBean.getId() + "";
            }
        }
        if (ObjectUtils.isEmpty(id)) {
            if (ObjectUtils.isNotEmpty(mSpecInfoList)) {
                String toastStr = mTvAllSpec.getText().toString().trim();
                ToastUtil.showBlackToastSucess("请" + toastStr);
                return;
            }
        }
        if (inStock < mBuyNum) {
            ToastUtil.showBlackToastSucess("库存不足");
            return;
        }
        LogUtils.e("sadasdasd");
        GoodsApi.addBuyCar(mGoodsBetialBean.getProducts_id(), mBuyNum + "", id, new BaseCallback<BaseResponse<Integer>>() {
            @Override
            public void onSucc(final BaseResponse<Integer> result) {
                EventBus.getDefault().post(new CarNumEvent(4, result.data + ""));
            }
        }, this);
        dismiss();
    }

    //判断是否是超出界限 弹toast
    private void showAddOrSubtract(int amount, final int type) {
        //比较是否所有的都选中否
        String id = "";
        for (SpecInfoBean specInfoBean : mSpecInfoList) {
            if (ZMStrUtils.equalList(mSelectSpec, specInfoBean.getKey())) {
                id = specInfoBean.getId() + "";
            }
        }
        if (ObjectUtils.isEmpty(id)) {
            if (ObjectUtils.isNotEmpty(mSpecInfoList)) {
                return;
            }
        }
        GoodsApi.addAndSubtract(mGoodsBetialBean.getProducts_id(), id, amount + "", new BaseCallback<BaseResponse<AddAndSubtractBean>>() {
            @Override
            public void onSucc(BaseResponse<AddAndSubtractBean> result) {
                if (type == 0) {
                    ToastUtil.showBlackToastSucess(result.data.getDecMsg());
                } else {
                    ToastUtil.showBlackToastSucess(result.data.getIncMsg());
                }
            }
        }, this);
    }


    @SingleClick(1500)
    @OnClick({R.id.iv_close, R.id.view_dismiss, R.id.tv_add_to_shopping_cart, R.id.tv_ok, R.id.tv_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
            case R.id.view_dismiss:
                dismiss();
                break;
            case R.id.tv_add_to_shopping_cart:

                AddCar();
                break;
            case R.id.tv_ok:
            case R.id.tv_order:

                GoodsApi.carConfirm("", "", "", mGoodsBetialBean.getProducts_id(), mGoodsBetialBean.getProducts_id(), 1 + "", "buy_now", "0", new BaseCallback<BaseResponse<SettlementCenterBean>>() {
                    @Override
                    public void onSucc(BaseResponse<SettlementCenterBean> result) {
                        SettlementCenterBean mSettlementCenterBean = result.data;
                        mSettlementCenterBean.setProducts_id(mGoodsBetialBean.getProducts_id());
                        UIHelper.showSettlementCenterActivity(getActivity(), ContentType.SETTLEMENT_CENTER, new profileBean(result.data));
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