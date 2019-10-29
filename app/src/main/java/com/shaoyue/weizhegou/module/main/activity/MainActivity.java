package com.shaoyue.weizhegou.module.main.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseAppActivity;
import com.shaoyue.weizhegou.entity.user.MainClickBean;
import com.shaoyue.weizhegou.module.main.adapter.NavigationAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by USER on 2018/2/5.
 */

public class MainActivity extends BaseAppActivity {


    @BindView(R.id.rv_features)
    RecyclerView mRvFeatures;
    @BindView(R.id.view_dismiss)
    View mViewDismiss;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    private long exitTime = 0;
    private NavigationAdapter mNavigationAdapter;
    private List<MainClickBean> mlist = new ArrayList<>();

    PopupWindow popupBigPhoto;
    View popupBigPhotoview;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showSuccToast(getResources().getString(R.string.tips_exit_out));
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_navigation;
    }


    @Override
    protected void initView() {
        super.initView();
        mlist.add(new MainClickBean("智能营销", R.drawable.main_icon_1));
        mlist.add(new MainClickBean("信贷运营", R.drawable.main_icon_2));
        mlist.add(new MainClickBean("贷后管理", R.drawable.mian_icon_3));
        mlist.add(new MainClickBean("信贷风控", R.drawable.main_icon_4));
        mlist.add(new MainClickBean("合规平台", R.drawable.main_icon_5));
        mlist.add(new MainClickBean("智能绩效", R.drawable.main_icon_6));
        mlist.add(new MainClickBean("人力资源管理", R.drawable.main_icon_7));
        mlist.add(new MainClickBean("系统设置", R.drawable.main_icon_8));


        mNavigationAdapter = new NavigationAdapter();
        mNavigationAdapter.setNewData(mlist);
        mRvFeatures.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        mRvFeatures.setAdapter(mNavigationAdapter);
        mNavigationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MainClickBean data = (MainClickBean) adapter.getData().get(position);
                switch (view.getId()) {
                    //消息管理
                    case R.id.tv_msg:

                        if (data.getTitle().equals("信贷运营")) {
                            UIHelper.showNotesDialog(MainActivity.this);
                        }
                        break;
                    //去各种系统
                    case R.id.iv_icon:
                        if (data.getTitle().equals("信贷运营")) {
                            UIHelper.showCreditActivity(MainActivity.this);
                        }else if(data.getTitle().equals("贷后管理")){
                            UIHelper.showDhActivity(MainActivity.this);
                        }
                        break;
                }
            }
        });
        popupBigPhotoview = getLayoutInflater().inflate(R.layout.navigation_popwindow, null);
        popupBigPhotoview.findViewById(R.id.tv_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupBigPhoto.isShowing()) {
                    popupBigPhoto.dismiss();
                }
                UIHelper.showUserPasswordDialog(MainActivity.this);
            }
        });
        popupBigPhotoview.findViewById(R.id.tv_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupBigPhoto.isShowing()) {
                    popupBigPhoto.dismiss();
                }
                UIHelper.showUserInfoDialog(MainActivity.this);
            }
        });


    }


    @OnClick({R.id.tv_logout, R.id.tv_title_center, R.id.view_dismiss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_logout:
                UIHelper.goLoginPage(this);
                break;
            case R.id.tv_title_center:
                if (popupBigPhoto == null) {
                    popupBigPhoto = new PopupWindow(popupBigPhotoview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    popupBigPhoto.setOutsideTouchable(true);
                    popupBigPhoto.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            mViewDismiss.setVisibility(View.GONE);
                        }
                    });

                }
                if (popupBigPhoto.isShowing()) {
                    popupBigPhoto.dismiss();
                } else {
                    popupBigPhoto.showAsDropDown(mTvTitleCenter, -120, 15);
                    mViewDismiss.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.view_dismiss:

                break;
        }
    }


}
