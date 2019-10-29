package com.shaoyue.weizhegou.module.address.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AddressApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.center.AddressListBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;

import com.shaoyue.weizhegou.module.address.adapter.ShippingAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;


/**
 * 作者：PangLei on 2019/4/11 0011 16:19
 * <p>
 * 邮箱：434604925@qq.com
 */
public class ShippingAddressFragment extends BaseTitleFragment {


    @BindView(R.id.ry_address)
    RecyclerView mRyAddress;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    @BindView(R.id.empty_img)
    ImageView mEmptyImg;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.search_edtv)
    EditText mSearchEdtv;
    @BindView(R.id.serch_delete)
    ImageView mSerchDelete;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    private ShippingAdapter mShippingAdapter;

    private List<AddressListBean> mListBean = new ArrayList<>();

    private String type;

    public static ShippingAddressFragment newInstance(String type) {

        Bundle args = new Bundle();
        args.putString("add_address", type);
        ShippingAddressFragment fragment = new ShippingAddressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragmnet_shipping_address;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("收货地址").setRightBtn("添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddressListBean mAddressListBean = new AddressListBean();
                if (type.equals("1")) {
                    mAddressListBean.setBtnContent("保存并使用");
                }
                addFragment(AddAddressFragment.newInstance(mAddressListBean));

            }
        });
        initView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Bundle bundle = getArguments();
        if (ObjectUtils.isNotEmpty(bundle)) {
            type = bundle.getString("add_address");
        } else {
            removeFragment();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void loadData() {
        super.loadData();
        getAddressList();
    }

    @Override
    public void onResume() {
        super.onResume();
        getAddressList();
    }

    /**
     * 初始化
     */
    private void initView() {
        mSearchEdtv.setHint("输入姓名或者手机号");
        mEmptyRelative.setBackgroundResource(R.color.color_f8f8f9);
        mEmptyImg.setImageResource(R.drawable.icon_address_null);
        mEmptyText.setText("还没有收货地址" + ToastUtil.getToastMsg());
        mShippingAdapter = new ShippingAdapter(type);
        mRyAddress.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRyAddress.setAdapter(mShippingAdapter);
        mRyAddress.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (type.equals("1")) {
                    EventBus.getDefault().post(mShippingAdapter.getData().get(position));
                    removeFragment();
                }
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                switch (view.getId()) {
                    case R.id.tv_delete:
                        delete(position);
                        break;
                    case R.id.tv_edit:
                        AddressListBean mAddressListBean = mListBean.get(position);
                        if (type.equals("1")) {
                            mAddressListBean.setBtnContent("保存并使用");
                        }
                        addFragment(AddAddressFragment.newInstance(mAddressListBean));
                        break;
                    case R.id.tv_set_default:
                    case R.id.iv_select:
                        setDefaultAddress(position);
                        break;
                }
            }
        });
        mSearchEdtv.setOnKeyListener(
                new View.OnKeyListener() {// 输入完后按键盘上的搜索键
                    // 修改回车键功能
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                            // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                            getAddressList();
                            return false;
                        }
                        return false;
                    }
                }

        );
    }

    /**
     * 设置默认地址
     *
     * @param position
     */
    private void setDefaultAddress(int position) {
        AddressApi.setDefaultAddress(mListBean.get(position).getId(), new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                getAddressList();
            }
        }, this);
    }

    private int deletePosition = -1;

    /**
     * 删除地址
     *
     * @param position
     */
    private void delete(int position) {
        deletePosition = position;
        //删除地址
        UIHelper.showOkClearDialog(getActivity(), "确定删除地址");
    }

    /**
     * 清除历史记录
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("确定删除地址")) {
            if (deletePosition != -1) {
                AddressApi.deleteAddress(mListBean.get(deletePosition).getId(), new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        deletePosition = -1;
                        getAddressList();
                    }

                    @Override
                    public void onFail(ApiException apiError) {
                        super.onFail(apiError);
                        deletePosition = -1;
                    }
                }, this);
            }
        }
    }

    /**
     * 获取地址列表
     */
    private void getAddressList() {
        String mKeywords = mSearchEdtv.getText().toString().trim();
        AddressApi.getAddressList(mKeywords, new BaseCallback<BaseResponse<List<AddressListBean>>>() {
            @Override
            public void onSucc(BaseResponse<List<AddressListBean>> result) {

                mListBean = result.data;
                mShippingAdapter.setNewData(result.data);
                if (mListBean.size() == 0) {
                    mEmptyRelative.setVisibility(View.VISIBLE);
                } else {
                    mEmptyRelative.setVisibility(View.GONE);
                }
            }
        }, this);
    }


    @OnClick({R.id.serch_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.serch_delete:
                mSearchEdtv.setText("");
                getAddressList();
                break;

        }
    }


}


