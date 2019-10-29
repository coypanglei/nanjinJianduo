package com.shaoyue.weizhegou.module.dhgl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.TimeSelect;
import com.shaoyue.weizhegou.entity.dhgl.XcjyBBean;
import com.shaoyue.weizhegou.module.dhgl.adapter.DhglXCJYBAdapter;
import com.shaoyue.weizhegou.util.ObjectToMapUtils;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.datepicker.CustomDatePicker;
import com.shaoyue.weizhegou.widget.datepicker.DateFormatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


public class XcjyBDhglFragment extends BaseAppFragment {


    @BindView(R.id.id_jiben_1)
    RecyclerView mIdJiben1;
    DhglXCJYBAdapter mAdapter;

    private CustomDatePicker mDatePicker;
    @BindView(R.id.ll_all)
    LinearLayout llAll;

    @BindView(R.id.sb_edit)
    StateButton sbEdit;
    private String timeTitle;

    private String id;


    public static XcjyBDhglFragment newInstance() {

        Bundle args = new Bundle();

        XcjyBDhglFragment fragment = new XcjyBDhglFragment();
        fragment.setArguments(args);
        return fragment;
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
        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            sbEdit.setVisibility(View.GONE);
        }
        initDatePicker();
        mAdapter = new DhglXCJYBAdapter();
        mAdapter.setActivity(getActivity());
        mIdJiben1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIdJiben1.setAdapter(mAdapter);
        mIdJiben1.setNestedScrollingEnabled(false);//禁止滑动


        startProgressDialog(true);
        initview();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dhgl_xcjyb_basic_information;
    }


    /**
     * 刷新数据
     */

    private void initview() {
        CeditApi.getListAll("现场检验", "现场检验表", new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                llAll.setVisibility(View.VISIBLE);
                stopProgressDialog();
                List<BasicInformationBean.RecordsBean> mlist = result.data.getRecords();
                mlist.add(0, new BasicInformationBean.RecordsBean("false","top", "借款人非财务分析"));
                mlist.add(15, new BasicInformationBean.RecordsBean("false","top", "担保状况总体评价"));
                mlist.add(19, new BasicInformationBean.RecordsBean("false","top", "预约新增业务"));
                mlist.add(21, new BasicInformationBean.RecordsBean("false","top", "现场验证情况"));
                mlist.add(25, new BasicInformationBean.RecordsBean("false","top", "风险评价及控制"));
                mlist.add(28, new BasicInformationBean.RecordsBean("false","top", "检验人"));
                mAdapter.setNewData(mlist);
                getListById();
            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                stopProgressDialog();
            }
        }, this);
    }


    @Override
    public void onResume() {
        super.onResume();
        getListById();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final TimeSelect timeSelect) {

        if (ObjectUtils.isNotEmpty(timeSelect.getTime())) {
            mDatePicker.show(timeSelect.getTime());
            timeTitle = timeSelect.getTitle();
        }

    }

    /**
     * 时间滑轮
     */
    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2009-05-01", false);
        long endTimestamp = DateFormatUtils.str2Long("2119-05-01", false);
        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                List<BasicInformationBean.RecordsBean> list = mAdapter.getData();
                for (BasicInformationBean.RecordsBean recordsBean : list) {
                    if (recordsBean.getTitile().equals(timeTitle)) {
                        recordsBean.setDefaultvalue(DateFormatUtils.long2Str(timestamp, false));
                    }
                }
                mAdapter.setNewData(list);
                mAdapter.notifyDataSetChanged();
            }
        }, beginTimestamp, endTimestamp);

        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(false);
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
    }

    /**
     * 获取界面数据byid
     */
    private void getListById() {
        DhApi.searchXcjyBByZjhm(new BaseCallback<BaseResponse<XcjyBBean>>() {
            @Override
            public void onSucc(BaseResponse<XcjyBBean> result) {

                if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {

                    sbEdit.setVisibility(View.GONE);
                }

                id = result.data.getId();
                Map<String, String> map = ObjectToMapUtils.str2Map(result.data);
                List<BasicInformationBean.RecordsBean> list = mAdapter.getData();
                if (ObjectUtils.isNotEmpty(list)) {
                    for (BasicInformationBean.RecordsBean bean : list) {
                        if (map.containsKey(bean.getName())) {
                            bean.setEdit(true);
                            bean.setDefaultvalue(map.get(bean.getName()));
                        }
                    }
                    mAdapter.setNewData(list);
                    mAdapter.notifyDataSetChanged();
                }


            }
        }, this);
    }

    @OnClick({R.id.sb_edit})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.sb_edit:

                Map<String, String> map = getMap();
                if (ObjectUtils.isNotEmpty(id)) {
                    map.put("id", id);
                    DhApi.editXCJYB(map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            ToastUtil.showBlackToastSucess("保存成功");
                            getListById();
                        }
                    }, this);
                } else {
                    firstAdd();
                }

                break;
        }
    }

    /**
     * 把所有参数转换 成map
     *
     * @return
     */
    private Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        List<BasicInformationBean.RecordsBean> list = mAdapter.getData();
        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicInformationBean.RecordsBean bean : list) {
                if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                    map.put(bean.getName(), bean.getDefaultvalue());
                }
            }

        }


        return map;
    }

    /**
     * 第一次添加
     */
    private void firstAdd() {
        Map<String, String> map = new HashMap<>();
        List<BasicInformationBean.RecordsBean> list = mAdapter.getData();
        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicInformationBean.RecordsBean bean : list) {
                if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                    map.put(bean.getName(), bean.getDefaultvalue());
                } else {
                    if (bean.getRequire().equals("true")) {
                        ToastUtil.showBlackToastSucess(bean.getTitile() + "不能为空");
                        return;
                    }
                }

            }

        }


        DhApi.addXCJYB(map, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showBlackToastSucess("保存成功");
                getListById();
            }
        }, this);

    }


}
