package com.shaoyue.weizhegou.module.address.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.AddressApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.center.AddressListBean;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;

import com.shaoyue.weizhegou.util.XClick.SingleClick;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;


/**
 * 作者：PangLei on 2019/4/12 0012 15:03
 * <p>
 * 邮箱：434604925@qq.com
 */
public class AddAddressFragment extends BaseTitleFragment {

    @BindView(R.id.iv_address_book)
    ImageView mIvAddressBook;
    @BindView(R.id.et_my_name)
    EditText mEtMyName;
    @BindView(R.id.et_my_phone)
    EditText mEtMyPhone;

    public CityConfig.WheelType mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    @BindView(R.id.tv_area_have)
    TextView mTvAreaHave;
    @BindView(R.id.iv_is_default)
    ImageView mIvIsDefault;

    @BindView(R.id.et_detailed_address)
    EditText mEtDetailedAddress;
    @BindView(R.id.tv_save_address)
    TextView mTvSaveAddress;

    private boolean click = true;


    //省
    private String defaultProvinceName = "江苏";
    //市
    private String defaultCityName = "徐州市";
    //区
    private String defaultDistrict = "云龙区";
    //地区id
    private String AreaId;
    //是否编辑或新增
    private String id = "";


    /**
     * 显示的Item
     */
    private int visibleItems = 5;

    private boolean isDefault = false;

    CityPickerView mCityPickerView = new CityPickerView();

    private AddressListBean mAddressListBean;

    public static AddAddressFragment newInstance(AddressListBean id) {

        Bundle args = new Bundle();
        args.putSerializable("id", id);
        AddAddressFragment fragment = new AddAddressFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_new_address;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mAddressListBean = (AddressListBean) bundle.getSerializable("id");
        }
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        if (ObjectUtils.isNotEmpty(mAddressListBean.getId())) {
            setCommonTitle("编辑地址");
            if (ObjectUtils.isNotEmpty(mAddressListBean.getBtnContent())) {
                mTvSaveAddress.setText(mAddressListBean.getBtnContent());
            } else {
                mTvSaveAddress.setText("确认修改");
            }

            setDefaultData();
        } else {
            setCommonTitle("新增地址");
            if (ObjectUtils.isNotEmpty(mAddressListBean.getBtnContent())) {
                mTvSaveAddress.setText(mAddressListBean.getBtnContent());
            }
        }

        /**
         * 预先加载仿iOS滚轮实现的全部数据
         */
        mCityPickerView.init(getActivity());

    }

    /**
     * 预先加载数据
     */
    private void setDefaultData() {
        mEtMyName.setText(mAddressListBean.getConsignee());
        mEtMyPhone.setText(mAddressListBean.getMobile());
        String[] mAreaString = mAddressListBean.getArea().split(",");
        defaultProvinceName = mAreaString[0];
        defaultCityName = mAreaString[1];
        defaultDistrict = mAreaString[2];
        String mArea = mAddressListBean.getArea().replaceAll(",", "\n");
        mTvArea.setVisibility(View.INVISIBLE);
        mTvAreaHave.setVisibility(View.VISIBLE);
        mTvAreaHave.setText(mArea);
        mEtDetailedAddress.setText(mAddressListBean.getAddress());
        AreaId = mAddressListBean.getRegionId() + "";
        id = mAddressListBean.getId();
        if (mAddressListBean.getIs_default() == 0) {
            isDefault = false;
            mIvIsDefault.setImageResource(R.drawable.icon_select_cancel);
        } else {
            isDefault = true;
            mIvIsDefault.setImageResource(R.drawable.icon_select_ok);
        }
    }

    /**
     * 跳转联系人
     */
    private void intentToContact() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("vnd.android.cursor.dir/phone_v2");
        getActivity().startActivityForResult(intent, 10003);
    }


    /**
     * 弹出选择器
     */
    private void wheel() {
        CityConfig cityConfig = new CityConfig.Builder()
                .title("选择城市")
                .province(defaultProvinceName)
                .city(defaultCityName)
                .district(defaultDistrict)
                .visibleItemsCount(visibleItems)
                .setCityWheelType(mWheelType)
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_city_name_tv)
                .setShowGAT(false)
                .build();

        mCityPickerView.setConfig(cityConfig);
        mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                StringBuilder sb = new StringBuilder();
                if (province != null) {
                    sb.append(province.getName() + "\n");
                    defaultProvinceName = province.getName();
                }
                if (city != null) {
                    sb.append(city.getName() + ("\n"));
                    defaultCityName = city.getName();
                }
                if (district != null) {
                    sb.append(district.getName() + ("\n"));
                    defaultDistrict = district.getName();
                }
                AreaId = district.getId();
                mTvArea.setVisibility(View.INVISIBLE);
                mTvAreaHave.setVisibility(View.VISIBLE);
                mTvAreaHave.setText(sb);
            }

            @Override
            public void onCancel() {
            }
        });
        mCityPickerView.showCityPicker();
    }

    /**
     * 保存地址
     */
    private void saveAddress() {
        String mMyName = mEtMyName.getText().toString().trim();
        String mPhone = mEtMyPhone.getText().toString().trim();
        final String mArea = mTvAreaHave.getText().toString().trim();
        String mDetailedAddress = mEtDetailedAddress.getText().toString().trim();
        if (ObjectUtils.isEmpty(mMyName)) {
            ToastUtil.showErrorToast("名字不能为空");
            return;
        }
        if (ObjectUtils.isEmpty(mPhone)) {
            ToastUtil.showErrorToast("手机号不能为空");
            return;
        }
        if (ObjectUtils.isEmpty(mArea)) {
            ToastUtil.showErrorToast("所在地区为空");
            return;
        }
        if (ObjectUtils.isEmpty(mDetailedAddress)) {
            ToastUtil.showErrorToast("详细地址为空");
            return;
        }
        if (click) {
            AddressApi.setUserAddressSave(mMyName, mPhone, AreaId, mDetailedAddress, id, isDefault, new BaseCallback<BaseResponse<AddressListBean>>() {
                @Override
                public void onSucc(final BaseResponse<AddressListBean> result) {
                    if (mTvSaveAddress.getText().equals("确认修改")) {
                        ToastUtil.showSuccessToast("修改成功");
                    } else {
                        ToastUtil.showSuccessToast("保存成功");
                    }
                    click = false;
                    if (mTvSaveAddress.getText().toString().equals("保存并使用")) {
                        ThreadUtil.runInUIThread(new Runnable() {

                            @Override
                            public void run() {
                                EventBus.getDefault().post(result.data);
                                getActivity().finish();
                            }
                        }, 2000);
                    } else {
                        ThreadUtil.runInUIThread(new Runnable() {

                            @Override
                            public void run() {

                                removeFragment();
                            }
                        }, 2000);
                    }

                }

                @Override
                public void onFail(ApiException apiError) {
                    super.onFail(apiError);
                }

            }, this);
        }
    }

    /**
     * 设置是否默认
     */
    private void setIsDefault() {
        if (isDefault) {
            isDefault = false;
            mIvIsDefault.setImageResource(R.drawable.icon_select_cancel);
        } else {
            isDefault = true;
            mIvIsDefault.setImageResource(R.drawable.icon_select_ok);
        }
    }

    @SingleClick(2000)
    @OnClick({R.id.iv_address_book, R.id.rl_select_area, R.id.rl_set_default, R.id.tv_save_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_address_book:
                AndPermission.with(this)
                        .runtime()
                        .permission(Permission.READ_CONTACTS)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                intentToContact();
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                ToastUtil.showErrorToast(getResources().getString(R.string.t_permission_denied));
                            }
                        })
                        .start();
                break;
            case R.id.rl_select_area:
                if (KeyboardUtils.isSoftInputVisible(getActivity())) {
                    KeyboardUtils.hideSoftInput(getActivity());
                }
                wheel();
                break;
            case R.id.rl_set_default:
                if (KeyboardUtils.isSoftInputVisible(getActivity())) {
                    KeyboardUtils.hideSoftInput(getActivity());
                }
                setIsDefault();
                break;
            case R.id.tv_save_address:
                //保存地址
                saveAddress();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10003) {
            if (data != null) {
                Uri uri = data.getData();
                String phoneNum = null;
                String contactName = null;
                // 创建内容解析者
                ContentResolver contentResolver = getActivity().getContentResolver();
                Cursor cursor = null;
                if (uri != null) {
                    cursor = contentResolver.query(uri,
                            new String[]{"display_name", "data1"}, null, null, null);
                }
                while (cursor.moveToNext()) {
                    contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    phoneNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                cursor.close();
                //  把电话号码中的  -  符号 替换成空格
                if (phoneNum != null) {
                    phoneNum = phoneNum.replaceAll("-", " ");
                    // 空格去掉  为什么不直接-替换成"" 因为测试的时候发现还是会有空格 只能这么处理
                    phoneNum = phoneNum.replaceAll(" ", "");
                }
                //设置名称
                mEtMyName.setText(contactName);
                //设置phone
                mEtMyPhone.setText(phoneNum);
            }
        }
    }


}
