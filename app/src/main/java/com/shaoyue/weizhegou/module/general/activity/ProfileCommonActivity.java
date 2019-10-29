package com.shaoyue.weizhegou.module.general.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.base.BaseCommonActivity;
import com.shaoyue.weizhegou.base.BaseFragment;
import com.shaoyue.weizhegou.entity.cedit.OcrBean;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.module.account.fragment.InfoFragment;
import com.shaoyue.weizhegou.module.account.fragment.PwdResetFragment;
import com.shaoyue.weizhegou.module.account.fragment.RegisterFragment;
import com.shaoyue.weizhegou.module.address.fragment.ShippingAddressFragment;
import com.shaoyue.weizhegou.module.credit.fragment.CreditApplicationFragment;
import com.shaoyue.weizhegou.module.credit.fragment.MyCouponFragment;
import com.shaoyue.weizhegou.module.general.fragment.AccountSecurityFragment;
import com.shaoyue.weizhegou.module.general.fragment.ChangeMyInfoFragment;
import com.shaoyue.weizhegou.module.goods.fragment.GoodsDetailsFragment;
import com.shaoyue.weizhegou.module.goods.fragment.OutOfStockFragment;
import com.shaoyue.weizhegou.module.goods.fragment.SettlementCenterFragment;
import com.shaoyue.weizhegou.module.order.fragment.AllOrderFragment;
import com.shaoyue.weizhegou.module.order.fragment.LogisticsInfoFragment;
import com.shaoyue.weizhegou.module.owner.fragment.CommissionDetailsFragment;
import com.shaoyue.weizhegou.module.owner.fragment.DistributionProductsFragment;
import com.shaoyue.weizhegou.module.owner.fragment.DistributorClickFragment;
import com.shaoyue.weizhegou.module.owner.fragment.IntergralDetailsFragment;
import com.shaoyue.weizhegou.module.owner.fragment.MemberClickFragment;
import com.shaoyue.weizhegou.module.owner.fragment.OrdererClickFragment;
import com.shaoyue.weizhegou.module.owner.fragment.RechargeMoneyFragment;
import com.shaoyue.weizhegou.module.owner.fragment.WalletDetailsFragment;
import com.shaoyue.weizhegou.module.pay.fragment.PayListFragment;
import com.shaoyue.weizhegou.module.search.fragment.SearchContentFragment;
import com.shaoyue.weizhegou.module.user.fragment.ModifyGesturePasswordFragment;
import com.shaoyue.weizhegou.module.user.fragment.ModifyPasswordFragment;
import com.shaoyue.weizhegou.module.user.fragment.NewsRecordFragment;
import com.shaoyue.weizhegou.module.user.fragment.PayRecordFragment;
import com.shaoyue.weizhegou.module.user.fragment.UseRecordFragment;
import com.shaoyue.weizhegou.router.ContentType;
import com.shaoyue.weizhegou.router.UIHelper;

import org.greenrobot.eventbus.EventBus;


/**
 * 作者：HQY on 17/7/10 14:43
 * 邮箱：hqy_xz@126.com
 */

public class ProfileCommonActivity extends BaseCommonActivity {


    private ContentType mContentType;

    private String mProfileContent;

    private profileBean mProfileBean;


    public static Intent newInstance(Context context, ContentType contentType) {
        Intent intent = new Intent(context, ProfileCommonActivity.class);
        intent.putExtra(UIHelper.CONTENT_TYPE, contentType);
        return intent;
    }

    public static Intent

    newInstance(Context context, ContentType contentType, profileBean searchContent) {
        Intent intent = new Intent(context, ProfileCommonActivity.class);
        intent.putExtra(UIHelper.CONTENT_TYPE, contentType);
        intent.putExtra(UIHelper.PROFILE_CONTENT, searchContent);
        return intent;
    }

    @Override
    protected boolean initBundle(Bundle bundle) {
        mContentType = (ContentType) getIntent().getSerializableExtra(UIHelper.CONTENT_TYPE);
        mProfileBean = (profileBean) getIntent().getSerializableExtra(UIHelper.PROFILE_CONTENT);
        if (ObjectUtils.isNotEmpty(mProfileBean)) {
            mProfileContent = mProfileBean.getContent();
        }
        return true;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        switch (mContentType) {
            case BUY_RECORD:
                return PayRecordFragment.newInstance();
            case USE_RECORD:
                return UseRecordFragment.newInstance();

            case FORGET_PASSWORD:
                return PwdResetFragment.newInstance();
            case REGISTER:
                return RegisterFragment.newInstance();
            case NEWS:
                return NewsRecordFragment.newInstance();
            case PAY_PACKAGE:
                return PayListFragment.newInstance();
            case SEARCH_SERVICES:
                return SearchContentFragment.newInstance(mProfileBean);
            case MY_ORDER:
                return MyCouponFragment.newInstance();
            case ADDRESS_MANAGEMENT:
                return ShippingAddressFragment.newInstance(mProfileContent);
            case GOODS_DETAILS:
                return GoodsDetailsFragment.newInstance(mProfileContent);
            case ACCOUNT_SECRITY:
                return AccountSecurityFragment.newInstance();
            case CHANGE_MY_INFO:
                return ChangeMyInfoFragment.newInstance();
            case COMMISSION_DETAILS:
                return CommissionDetailsFragment.newInstance();
            case ORDERER_DETAILS:
                return OrdererClickFragment.newInstance(mProfileBean);
            case INTERGRAL_DETAILS:
                return IntergralDetailsFragment.newInstance();
            case WALLET_DETAILS:
                return WalletDetailsFragment.newInstance();
            case DISTRIBUTOR_MEMBER:
                return MemberClickFragment.newInstance(mProfileBean);
            case DISTRIBUTOR:
                return DistributorClickFragment.newInstance(mProfileBean);
            case DISTRIBUTOR_PRODUCTS:
                return DistributionProductsFragment.newInstance();
            case SETTLEMENT_CENTER:
                return SettlementCenterFragment.newInstance(mProfileBean.getSettlementCenterBean());
            case ALL_ORDERS:
                return AllOrderFragment.newInstance(mProfileBean);
            case ACCOUNT_RECHARGE:
                return RechargeMoneyFragment.newInstance();
            case ACCOUTN_INFO:
                return InfoFragment.newInstance();
            case EMPTY_GOODS:
                return OutOfStockFragment.newInstance();
            case LOGISTICS_INFORMATION:
                return LogisticsInfoFragment.newInstance(mProfileContent);
            case MODIFY_PASSWORD:
                return ModifyPasswordFragment.newInstance();
            case MODIFY_GESTURE_PASSWORD:
                return ModifyGesturePasswordFragment.newInstance(mProfileContent);
            case CREDIT_APPLICATION:
                return CreditApplicationFragment.newInstance(mProfileBean);
            default:
                return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        switch(requestCode){
            case 1001://该结果码要与Fragment中的一致
                //我这里使用的是根据结果码获取数据，然后加上下面一句代码，其
                //他的什么都不用做

                EventBus.getDefault().post(new OcrBean(data,1001));
                break;
            case 1002:
                EventBus.getDefault().post(new OcrBean(data,1002));
                break;
        }
    }
}
