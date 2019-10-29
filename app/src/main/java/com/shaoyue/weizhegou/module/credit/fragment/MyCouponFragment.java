package com.shaoyue.weizhegou.module.credit.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseTitleFragment;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.TextWidthColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import butterknife.BindView;

public class MyCouponFragment extends BaseTitleFragment {
    @BindView(R.id.moretab_indicator)
    ScrollIndicatorView scrollIndicatorView;
    @BindView(R.id.moretab_viewPager)
    ViewPager viewPager;

    private final Fragment[] fragmentArray = {};

    String[] mTextviewArray;
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;

    public static MyCouponFragment newInstance() {

        Bundle args = new Bundle();

        MyCouponFragment fragment = new MyCouponFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setCommonTitle("红包礼券");
        initView();
    }

    private void initView() {
        mTextviewArray = getResources().getStringArray(R.array.main_tab_coupon);

        scrollIndicatorView.setScrollBar(new TextWidthColorBar(getActivity(), scrollIndicatorView, getResources().getColor(R.color.color_cd0946), 4));
        float unSelectSize = 15;
        float selectSize = unSelectSize * 1.1f;
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(getResources().getColor(R.color.color_cd0946), getResources().getColor(R.color.color_4a4a4a)).setSize(selectSize, unSelectSize));

        viewPager.setOffscreenPageLimit(3);
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        inflate = LayoutInflater.from(mActivity.getApplicationContext());
        indicatorViewPager.setAdapter(new MyAdapter(getActivity().getSupportFragmentManager()));
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
                convertView = inflate.inflate(com.shaoyue.weizhegou.R.layout.tab_top, container, false);
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
