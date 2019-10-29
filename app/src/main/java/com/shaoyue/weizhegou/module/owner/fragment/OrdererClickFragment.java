package com.shaoyue.weizhegou.module.owner.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.event.RefreshNumEvent;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.TextWidthColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 作者：PangLei on 2019/5/23 0023 14:09
 * <p>
 * 邮箱：434604925@qq.com
 */
public class OrdererClickFragment extends BaseTitleFragment {

    @BindView(R.id.moretab_indicator)
    ScrollIndicatorView scrollIndicatorView;
    @BindView(R.id.moretab_viewPager)
    ViewPager viewPager;


    private final Fragment[] fragmentArray = {};

    String[] mTextviewArray = {"下级订货商", "下级会员", "团队订单"};
    @BindView(R.id.tv_commission)
    TextView mTvCommission;
    Unbinder unbinder;
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private String click;
    private MyAdapter myAdapter;

    public static OrdererClickFragment newInstance(profileBean mProfileContent) {

        Bundle args = new Bundle();
        args.putString("click", mProfileContent.getContent());
        args.putStringArray("titles", mProfileContent.getmString());
        OrdererClickFragment fragment = new OrdererClickFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            click = getArguments().getString("click");
            mTextviewArray = getArguments().getStringArray("titles");
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
    protected int getContentLayoutId() {
        return R.layout.fragment_orderer;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("订货商");
        initView();
    }

    private void initView() {
        scrollIndicatorView.setScrollBar(new TextWidthColorBar(getActivity(), scrollIndicatorView, getResources().getColor(R.color.color_cd0946), 4));
        float unSelectSize = 15;
//        float selectSize = unSelectSize * 1.1f;
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(getResources().getColor(R.color.color_cd0946), getResources().getColor(R.color.color_4a4a4a)).setSize(unSelectSize, unSelectSize));
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        indicatorViewPager.setPageOffscreenLimit(1);
        inflate = LayoutInflater.from(mActivity.getApplicationContext());
        myAdapter = new MyAdapter(getActivity().getSupportFragmentManager());
        indicatorViewPager.setAdapter(myAdapter);
        indicatorViewPager.setCurrentItem(Integer.parseInt(click), false);
        mTvCommission.setText("我的佣金：￥" + UserMgr.getInstance().getSP_COMMISSION_BAlANCE());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshNum(RefreshNumEvent event) {
        if (event.getType() == 1) {
            mTextviewArray[0] = "下级订货商(" + event.getNum() + ")";
            myAdapter.getIndicatorAdapter().notifyDataSetChanged();
        } else if (event.getType() == 2) {
            mTextviewArray[1] = "下级会员(" + event.getNum() + ")";
            myAdapter.getIndicatorAdapter().notifyDataSetChanged();
        } else if (event.getType() == 3) {
            mTextviewArray[2] = "团队订单(" + event.getNum() + ")";
            myAdapter.getIndicatorAdapter().notifyDataSetChanged();
        }
    }


    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return mTextviewArray.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(mTextviewArray[position]);
            int padding = 10;
            textView.setPadding(padding, 0, padding, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            return fragmentArray[position];
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_NONE;
        }

    }
}
