package com.shaoyue.weizhegou.module.search.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.View;


import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.base.BaseTitleFragment;

import com.shaoyue.weizhegou.entity.home.profileBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.module.search.view.Search_View;
import com.shaoyue.weizhegou.router.UIHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;


public class SearchContentFragment extends BaseTitleFragment {


    @BindView(R.id.search_layout)
    Search_View mSearchLayout;

    private String mSearchContnet = "";

    private int type;

    public static SearchContentFragment newInstance(profileBean profileBean) {

        Bundle args = new Bundle();
        args.putString("key_name", profileBean.getContent());
        args.putInt("type", profileBean.getType());
        SearchContentFragment fragment = new SearchContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_content;
    }

    @Override
    protected void initBundle(Bundle bundle) {
        super.initBundle(bundle);
        if (getArguments() != null) {
            mSearchContnet = getArguments().getString("key_name");
            type = getArguments().getInt("type");
        }

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
        setCommonTitle("搜索");
        mSearchLayout.setContext(getActivity());
        mSearchLayout.setType(type);
        mSearchLayout.setEtText(mSearchContnet);
    }

    /**
     * 清除历史记录
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("确定清空记录")) {
            if (ObjectUtils.isNotEmpty(mSearchLayout)) {
                mSearchLayout.clearAll();
            }
        }
    }

}
