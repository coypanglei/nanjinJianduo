package com.shaoyue.weizhegou.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.base.BaseAppActivity;
import com.shaoyue.weizhegou.base.dialog.NewOkCancelFragment;
import com.shaoyue.weizhegou.base.dialog.OkCancelFragment;
import com.shaoyue.weizhegou.entity.ActivityBean;
import com.shaoyue.weizhegou.entity.PayBean;
import com.shaoyue.weizhegou.entity.VersionBean;
import com.shaoyue.weizhegou.entity.WebEntity;
import com.shaoyue.weizhegou.entity.cedit.FamilyBean;
import com.shaoyue.weizhegou.entity.cedit.GoAllSelect;
import com.shaoyue.weizhegou.entity.cedit.QianziBean;
import com.shaoyue.weizhegou.entity.cedit.TiJiaoBean;
import com.shaoyue.weizhegou.entity.cedit.VideoMaterialBean;
import com.shaoyue.weizhegou.entity.diaocha.StartDiaochaBean;
import com.shaoyue.weizhegou.entity.goods.GoodsDetialBean;
import com.shaoyue.weizhegou.entity.goods.SettlementCoupon;
import com.shaoyue.weizhegou.entity.home.HomeInitBean;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.entity.order.OrderDetailsBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.AppMgr;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.account.activity.GoodsDetailsActivity;
import com.shaoyue.weizhegou.module.account.activity.OrderDetailsActivity;
import com.shaoyue.weizhegou.module.account.activity.SettlementCenterActivity;
import com.shaoyue.weizhegou.module.account.dialog.ActvityDialogFragment;
import com.shaoyue.weizhegou.module.account.dialog.BindPhoneDialogFragment;
import com.shaoyue.weizhegou.module.account.dialog.IntelligentHelpFilelFragment;
import com.shaoyue.weizhegou.module.account.dialog.JumpInterfaceDialogFragment;
import com.shaoyue.weizhegou.module.account.dialog.LineHelpFilelFragment;
import com.shaoyue.weizhegou.module.account.dialog.RuleFragment;
import com.shaoyue.weizhegou.module.account.dialog.VerifiedDialogFragment;
import com.shaoyue.weizhegou.module.credit.activity.ApplyInfoActivity;
import com.shaoyue.weizhegou.module.credit.activity.DcInfoActivity;
import com.shaoyue.weizhegou.module.credit.activity.DyFaceActivity;
import com.shaoyue.weizhegou.module.credit.dialog.ApplicationProgressDialogFragment;
import com.shaoyue.weizhegou.module.credit.dialog.DiYaDialogFragment;
import com.shaoyue.weizhegou.module.credit.dialog.FamilyInfoDialogFragment;
import com.shaoyue.weizhegou.module.credit.dialog.NewQianziDialogFragment;
import com.shaoyue.weizhegou.module.credit.dialog.ProvincialIdentificationDialogFragment;
import com.shaoyue.weizhegou.module.credit.dialog.QianziDialogFragment;
import com.shaoyue.weizhegou.module.credit.dialog.StartDcDialogFragment;
import com.shaoyue.weizhegou.module.credit.dialog.VideoPicDialogFragment;
import com.shaoyue.weizhegou.module.credit.dialog.XcjyProgressDialogFragment;
import com.shaoyue.weizhegou.module.credit.dialog.ZhuChaFragment;
import com.shaoyue.weizhegou.module.dhgl.activity.XcjyActivity;
import com.shaoyue.weizhegou.module.general.activity.ProfileCommonActivity;
import com.shaoyue.weizhegou.module.goods.dialog.NewCarGoodsSpecificationFragment;
import com.shaoyue.weizhegou.module.goods.dialog.NewGoodsSpecificationFragment;
import com.shaoyue.weizhegou.module.goods.dialog.ShareFragment;
import com.shaoyue.weizhegou.module.goods.dialog.ShareQcCodeFragment;
import com.shaoyue.weizhegou.module.main.activity.CreditNavActivity;
import com.shaoyue.weizhegou.module.main.activity.DhglNavActivity;
import com.shaoyue.weizhegou.module.main.activity.MainActivity;
import com.shaoyue.weizhegou.module.owner.dialog.ShopQcCodeFragment;
import com.shaoyue.weizhegou.module.pay.dialog.NewPayPasswordFragment;
import com.shaoyue.weizhegou.module.pay.dialog.PayDialog;
import com.shaoyue.weizhegou.module.pay.dialog.ShopPromotionsFragment;
import com.shaoyue.weizhegou.module.pay.fragment.PayDialogFragment;
import com.shaoyue.weizhegou.module.pay.fragment.PayFialDialogFragment;
import com.shaoyue.weizhegou.module.start.LauncherActivity;
import com.shaoyue.weizhegou.module.update.UpdateActivity;
import com.shaoyue.weizhegou.module.user.dialog.AccountNotesFragment;
import com.shaoyue.weizhegou.module.user.dialog.AccountPasswordFragment;
import com.shaoyue.weizhegou.module.user.dialog.GuojiHyFragment;
import com.shaoyue.weizhegou.module.user.dialog.UserInfoDialogFragment;
import com.shaoyue.weizhegou.module.web.WebActivity;
import com.shaoyue.weizhegou.module.web.WebShareActivity;
import com.shaoyue.weizhegou.module.web.WxWebViewActivity;


/**
 * 作者：HQY on 17/7/8 16:22
 * 邮箱：hqy_xz@126.com
 */

public class UIHelper {


    public static final String CONTENT_TYPE = "content_type";

    public static final String PROFILE_CONTENT = "search_content";


    public static final String HOME_DATA = "home_data";

    public static final String HELP_FILE = "help_file";

    public static final String VERIFIED = "verified";

    public static final String PERFEXT_INDIVIDUAL = "";

    public static final String JUMP = "jump";

    public static final String INELLIGENT = "inelligent_file";

    public static final String RULE_FILE = "rule_file";

    public static final String PAY_PACKAGE = "pay_package";

    public static final String CONTENT = "content";

    public static final String WEBVIEW = "webview";

    public static final String TYPE = "type";

    public static final String FIAL = "fail";

    public static final String Ok_CANCEL = "ok_cancel";
    public static final String Ok_CANCEL_NEW = "ok_cancel";

    public static final String EDIT_NUM = "edit_num";

    public static final String SHARE_GOODS = "share_goods";

    public static final String MY_SUPERIOR = "my_superior";


    public static final String SHARE_QC_CODE = "share_qc_code";

    public static final String SHARE_SHOP_QC_CODE = "share_shop_qc_code";

    public static final String USER_INFO = "user_info";

    public static final String USER_PIC = "user_pic";

    public static final String USER_INFO_PASSWORD = "user_info_password";

    public static final String USER_INFO_NOTES = "user_info_notes";

    public static final String APPLICATION_PROGRESS = "application_progress";

    public static final String ID_CARD = "dialog_id_card";

    public static final String FAMILY_INFO = "dialog_family_info";


    public static final String FAMILY_DIYA = "dialog_diya";

    public static final String START_DC = "dialog_start_dc";


    public static final String GOODS_SPECIFICATION = "goods_specification";

    public static final String CAR_GOODS_SPECIFICATION = "car_goods_specification";

    public static final String PAY_PASSWORD_EDIT = "pay_password_edit";

    public static final String SHOP_PROMOTIONS = "shop_promotions";

    public static final String GOODS_ID = "goods_id";

    public static final String HOME_INIT_BEAN = "homeInitBean";

    public static final String XIEZIBAN = "xieziban";


    public static final String XIEZIBAN_NEW = "xieziban_new";

    public static final String GUOJIHANGYE = "gjhy";

    public static final String TI_JIAO = "tijiao";


    public static void showPayPage(Context context) {
        String packageUrl = AppMgr.getInstance().getH5PayUrl() + "?session=" +
                UserMgr.getInstance().getSessionId() + "&account=" + UserMgr.getInstance().getUsername();
        WebActivity.showWebPage(context, "套餐购买", packageUrl);
    }

    /**
     * webview界面
     *
     * @param context
     * @param title
     * @param url
     */
    public static void showWebPage(Context context, String title, String url) {
        String packageUrl = url + "?session=" +
                UserMgr.getInstance().getSessionId() + "&account=" + UserMgr.getInstance().getUsername();
        WebActivity.showWebPage(context, title, packageUrl);
    }


    public static void showWxWebPage(Activity activity, WebEntity web) {
        Intent intent = WxWebViewActivity.newInstance(activity, web);
        activity.startActivity(intent);
    }

    /**
     * webview界面
     *
     * @param context
     * @param title
     * @param url
     */
    public static void showShareWebPage(Context context, String title, String url, String iconUrl) {
        String packageUrl = url + "?session=" +
                UserMgr.getInstance().getSessionId() + "&account=" + UserMgr.getInstance().getUsername();
        WebShareActivity.showShareWebPage(context, title, packageUrl, iconUrl);
    }


    /**
     * 国际行业
     *
     * @param activity
     */
    public static void showGjhyDialog(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(GUOJIHANGYE);
        if (fragment == null) {
            DialogFragment dialog = GuojiHyFragment.newInstance();
            dialog.show(fm, GUOJIHANGYE);
        }
    }

    /**
     * share界面
     *
     * @param activity
     */
    public static void showShareDialog(FragmentActivity activity, String title) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(SHARE_GOODS);
        if (fragment == null) {
            DialogFragment dialog = ShareFragment.newInstance();
            dialog.show(fm, SHARE_GOODS);
        }
    }


    /**
     * 现场检验申请进度界面
     *
     * @param activity
     */
    public static void showXcjyProgressDialog(FragmentActivity activity, String id) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(APPLICATION_PROGRESS);
        if (fragment == null) {
            DialogFragment dialog = XcjyProgressDialogFragment.newInstance(id);
            dialog.show(fm, APPLICATION_PROGRESS);
        }
    }
    /**
     * 申请进度界面
     *
     * @param activity
     */
    public static void showApplicationProgressDialog(FragmentActivity activity, String id) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(APPLICATION_PROGRESS);
        if (fragment == null) {
            DialogFragment dialog = ApplicationProgressDialogFragment.newInstance(id);
            dialog.show(fm, APPLICATION_PROGRESS);
        }
    }


    /**
     * 写字板界面
     *
     * @param activity
     */
    public static void showXezibanDialog(FragmentActivity activity, QianziBean qianziBean) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(XIEZIBAN);
        if (fragment == null) {
            DialogFragment dialog = QianziDialogFragment.newInstance(qianziBean);
            dialog.show(fm, XIEZIBAN);
        }
    }

    /**
     * 写字板统一界面
     *
     * @param activity
     */
    public static void showXezibanDialog(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(XIEZIBAN_NEW);
        if (fragment == null) {
            DialogFragment dialog = NewQianziDialogFragment.newInstance();
            dialog.show(fm, XIEZIBAN_NEW);
        }
    }

    /**
     * 提交申请
     *
     * @param activity
     */
    public static void showTijiaoDialog(FragmentActivity activity, TiJiaoBean tiJiaoBean) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(TI_JIAO);
        if (fragment == null) {
            DialogFragment dialog = ZhuChaFragment.newInstance(tiJiaoBean);
            dialog.show(fm, TI_JIAO);
        }
    }


    /**
     * share 二维码界面
     *
     * @param activity
     */
    public static void showGoodsShareQcDialog(FragmentActivity activity, String id) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(SHARE_QC_CODE);
        if (fragment == null) {
            DialogFragment dialog = ShareQcCodeFragment.newInstance(id);
            dialog.show(fm, SHARE_QC_CODE);
        }
    }


    /**
     * 展示图片集合
     *
     * @param activity
     */
    public static void showPicDialog(FragmentActivity activity, VideoMaterialBean data) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(USER_PIC);
        if (fragment == null) {
            DialogFragment dialog = VideoPicDialogFragment.newInstance(data);
            dialog.show(fm, USER_PIC);
        }
    }

    /**
     * 展示个人信息
     *
     * @param activity
     */
    public static void showUserInfoDialog(BaseAppActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(USER_INFO);
        if (fragment == null) {
            DialogFragment dialog = UserInfoDialogFragment.newInstance();
            dialog.show(fm, USER_INFO);
        }
    }

    /**
     * 展示账号密码
     *
     * @param activity
     */
    public static void showUserPasswordDialog(BaseAppActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(USER_INFO_PASSWORD);
        if (fragment == null) {
            DialogFragment dialog = AccountPasswordFragment.newInstance();
            dialog.show(fm, USER_INFO_PASSWORD);
        }
    }

    /**
     * 开始调查
     *
     * @param activity
     */
    public static void showStartDiaoCha(FragmentActivity activity, StartDiaochaBean goAllSelect) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(START_DC);
        if (fragment == null) {
            DialogFragment dialog = StartDcDialogFragment.newInstance(goAllSelect);
            dialog.show(fm, START_DC);
        }
    }


    /**
     * 新增抵押担保
     *
     * @param activity
     */
    public static void showDiyaFragment(FragmentActivity activity, GoAllSelect goAllSelect) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(FAMILY_DIYA);
        if (fragment == null) {
            DialogFragment dialog = DiYaDialogFragment.newInstance(goAllSelect);
            dialog.show(fm, FAMILY_DIYA);
        }
    }

    /**
     * 新增或者修改家庭信息
     *
     * @param activity
     */
    public static void showAddFamilyInfoDialog(FragmentActivity activity, FamilyBean familyBean) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(FAMILY_INFO);
        if (fragment == null) {
            DialogFragment dialog = FamilyInfoDialogFragment.newInstance(familyBean);
            dialog.show(fm, FAMILY_INFO);
        }
    }


    /**
     * 展示账号密码
     *
     * @param activity
     */
    public static void showIDCardDialog(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(ID_CARD);
        if (fragment == null) {
            DialogFragment dialog = ProvincialIdentificationDialogFragment.newInstance();
            dialog.show(fm, ID_CARD);
        }
    }

    /**
     * 展示账号密码
     *
     * @param activity
     */
    public static void showNotesDialog(BaseAppActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(USER_INFO_NOTES);
        if (fragment == null) {
            DialogFragment dialog = AccountNotesFragment.newInstance();
            dialog.show(fm, USER_INFO_NOTES);
        }
    }


    /**
     * 二维码界面
     *
     * @param activity
     */
    public static void showShareQcDialog(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(SHARE_SHOP_QC_CODE);
        if (fragment == null) {
            DialogFragment dialog = ShopQcCodeFragment.newInstance();
            dialog.show(fm, SHARE_SHOP_QC_CODE);
        }
    }

    /**
     * 跳转登录界面
     *
     * @param activity
     */
    public static void goLoginPage(Context activity) {
        UserMgr.getInstance().clearUserInfo();
        Intent loginIntent = new Intent(activity, LauncherActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(loginIntent);
    }

    /**
     * 主界面
     *
     * @param activity
     */
    public static void showMainPage(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * 信贷平台
     *
     * @param activity
     */
    public static void showCreditActivity(Activity activity) {
        Intent intent = new Intent(activity, CreditNavActivity.class);
        activity.startActivity(intent);
    }


    /**
     * 贷后平台
     *
     * @param activity
     */
    public static void showDhActivity(Activity activity) {
        Intent intent = new Intent(activity, DhglNavActivity.class);
        activity.startActivity(intent);
    }


    /**
     * 主界面
     *
     * @param activity
     * @param homeInitBean
     */
    public static void showMainPage(Activity activity, HomeInitBean homeInitBean) {
        Intent intent = new Intent(activity, MainActivity.class);
        if (ObjectUtils.isNotEmpty(homeInitBean)) {
            intent.putExtra(HOME_DATA, homeInitBean);
        }
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * 各种跳转界面
     */

    public static void showProfileCommonActivity(Activity activity, ContentType contentType) {
        Intent intent = ProfileCommonActivity.newInstance(activity, contentType);
        activity.startActivity(intent);
    }

    /**
     * 跳转授信申请activity
     */

    public static void showApplyCommonActivity(Activity activity, String contentType) {
        Intent intent = ApplyInfoActivity.newInstance(activity, contentType);
        activity.startActivity(intent);
    }

    /**
     * 跳转授信申请抵押activity
     */

    public static void showDyApplyCommonActivity(Activity activity, String contentType) {
        Intent intent = DyFaceActivity.newInstance(activity, contentType);
        activity.startActivity(intent);
    }


    /**
     * 跳转授信调查activity
     */

    public static void showDcCommonActivity(String type, Activity activity, String contentType) {
        if ("调查".equals(type)) {
            Intent intent = DcInfoActivity.newInstance(activity, contentType);
            activity.startActivity(intent);
        } else if ("现场检验".equals(type)) {
            Intent intent = XcjyActivity.newInstance(activity, contentType);
            activity.startActivity(intent);
        }
    }


    /**
     * 跳转夹带内容
     *
     * @param activity
     * @param contentType
     * @param content
     */
    public static void showProfileCommonActivity(Activity activity, ContentType contentType, profileBean content) {
        Intent intent = ProfileCommonActivity.newInstance(activity, contentType, content);
        activity.startActivity(intent);
    }

    /**
     * 订单详情
     *
     * @param activity
     * @param contentType
     * @param content
     */
    public static void showOrderDetailsActicity(Activity activity, ContentType contentType, OrderDetailsBean content) {
        Intent intent = OrderDetailsActivity.newInstance(activity, contentType, content);
        activity.startActivity(intent);
    }

    /**
     * 結算中心
     *
     * @param activity
     * @param contentType
     * @param content
     */
    public static void showSettlementCenterActivity(Activity activity, ContentType contentType, profileBean content) {
        Intent intent = SettlementCenterActivity.newInstance(activity, contentType, content);
        activity.startActivity(intent);
    }

    /**
     * 商品详情页
     *
     * @param activity
     * @param contentType
     * @param content
     */
    public static void showGoodsDetailsActivity(Activity activity, ContentType contentType, profileBean content) {
        Intent intent = GoodsDetailsActivity.newInstance(activity, contentType, content);
        activity.startActivity(intent);
    }

    /**
     * 需要确定取消的dialog
     *
     * @param activity
     */
    public static void showOkClearDialog(FragmentActivity activity, String title) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(Ok_CANCEL);
        if (fragment == null) {
            DialogFragment dialog = OkCancelFragment.newInstance(title);
            dialog.show(fm, Ok_CANCEL);
        }
    }

    /**
     * 需要确定取消的dialog
     *
     * @param activity
     */
    public static void showOkNewClearDialog(FragmentActivity activity, OkOrCancelEvent title) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(Ok_CANCEL_NEW);
        if (fragment == null) {
            DialogFragment dialog = NewOkCancelFragment.newInstance(title);
            dialog.show(fm, Ok_CANCEL_NEW);
        }
    }




    /**
     * 商品规格的dialog
     *
     * @param activity
     */
    public static void showGoodsSpecificationDialog(FragmentActivity activity, GoodsDetialBean goodsDetialBean) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(GOODS_SPECIFICATION);
        if (fragment == null) {
            DialogFragment dialog = NewGoodsSpecificationFragment.newInstance(goodsDetialBean);
            dialog.show(fm, GOODS_SPECIFICATION);
        }
    }

    /**
     * 购物车商品规格的dialog
     *
     * @param activity
     */
    public static void showCarGoodsSpecificationDialog(FragmentActivity activity, GoodsDetialBean goodsDetialBean) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(CAR_GOODS_SPECIFICATION);
        if (fragment == null) {
            DialogFragment dialog = NewCarGoodsSpecificationFragment.newInstance(goodsDetialBean);
            dialog.show(fm, CAR_GOODS_SPECIFICATION);
        }
    }


    /**
     * 支付dialog
     *
     * @param activity
     */
    public static void showPayPasswordDialog(FragmentActivity activity, String mBalance) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(PAY_PASSWORD_EDIT);
        if (fragment == null) {
            DialogFragment dialog = NewPayPasswordFragment.newInstance(mBalance);
            dialog.show(fm, PAY_PASSWORD_EDIT);
        }
    }

    /**
     * 优惠券列表dialog
     *
     * @param activity
     */
    public static void showCouponListDialog(FragmentActivity activity, SettlementCoupon mCouponList) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(SHOP_PROMOTIONS);
        if (fragment == null) {
            DialogFragment dialog = ShopPromotionsFragment.newInstance(mCouponList);
            dialog.show(fm, SHOP_PROMOTIONS);
        }
    }


    public static void showUpdateDialog(Context context, VersionBean version) {
        Intent intent = new Intent(context, UpdateActivity.class);
        intent.putExtra(UpdateActivity.EXTRA_VERSION, version);
        context.startActivity(intent);
    }

    /**
     * 说明Dialog
     *
     * @param activity
     */
    public static void showVerifiedFile(FragmentActivity activity, String title, String content) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(VERIFIED);
        if (fragment == null) {
            DialogFragment dialog = VerifiedDialogFragment.newInstance(title, content);
            dialog.show(fm, VERIFIED);
        }
    }


    /**
     * 跳转Dialog
     *
     * @param activity
     */
    public static void showjumpDialog(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(JUMP);
        if (fragment == null) {
            DialogFragment dialog = JumpInterfaceDialogFragment.newInstance();
            dialog.show(fm, JUMP);
        }
    }

    /**
     * 跳转Dialog
     *
     * @param activity
     */
    public static void showPecrfectIndividualDialog(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(PERFEXT_INDIVIDUAL);
        if (fragment == null) {
            DialogFragment dialog = BindPhoneDialogFragment.newInstance();
            dialog.show(fm, PERFEXT_INDIVIDUAL);
        }
    }


    /**
     * 线路选择说明
     *
     * @param activity
     */
    public static void showlineSelectHelpFile(FragmentActivity activity, String title) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(HELP_FILE);
        if (fragment == null) {
            DialogFragment dialog = LineHelpFilelFragment.newInstance(title);
            dialog.show(fm, HELP_FILE);
        }
    }


    /**
     * 智能模式选择说明
     *
     * @param activity
     */
    public static void showIntelligentSelectHelpFile(FragmentActivity activity, String title) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(INELLIGENT);
        if (fragment == null) {
            DialogFragment dialog = IntelligentHelpFilelFragment.newInstance(title);
            dialog.show(fm, INELLIGENT);
        }
    }


    /**
     * 规则说明
     *
     * @param activity
     */
    public static void showRuleFile(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(RULE_FILE);
        if (fragment == null) {
            DialogFragment dialog = RuleFragment.newInstance();
            dialog.show(fm, RULE_FILE);
        }
    }

    /**
     * @param activity
     */
    public static void showpayPackage(FragmentActivity activity, PayBean paybean) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(PAY_PACKAGE);
        if (fragment == null) {
            DialogFragment dialog = PayDialog.newInstance(paybean);
            dialog.show(fm, PAY_PACKAGE);
        }
    }


    /**
     * 完成支付
     *
     * @param activity
     */
    public static void showPayDialog(FragmentActivity activity, WebEntity web) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(TYPE);
        if (fragment == null) {
            DialogFragment dialog = PayDialogFragment.newInstance(web);
            dialog.show(fm, TYPE);
        }
    }

    /**
     * 支付失败
     *
     * @param activity
     */
    public static void showPayFialDialog(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(FIAL);
        if (fragment == null) {
            DialogFragment dialog = PayFialDialogFragment.newInstance();
            dialog.show(fm, FIAL);
        }
    }


    /**
     * 会员到期对话框
     */
    public static void showActivityDialog(FragmentActivity activity, ActivityBean activityBean) {
        String tag = "DIALOG_ACTIVITY_NEW";
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment == null) {
            DialogFragment dialog = ActvityDialogFragment.newInstance(activityBean);
            dialog.show(fm, tag);
        }
    }


}
