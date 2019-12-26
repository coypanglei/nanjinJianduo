package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.JsonObject;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.TimeSelect;
import com.shaoyue.weizhegou.entity.diaocha.AddressSelectBean;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.BasicInformationAdapter;
import com.shaoyue.weizhegou.util.ObjectToMapUtils;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.datepicker.CustomDatePicker;
import com.shaoyue.weizhegou.widget.datepicker.DateFormatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


public class BasicInformationFragment extends BaseAppFragment {


    @BindView(R.id.id_jiben_1)
    RecyclerView mIdJiben1;
    BasicInformationAdapter mAdapter;
    BasicInformationAdapter mAdapter2;
    BasicInformationAdapter mAdapter3;
    @BindView(R.id.id_jiben_2)
    RecyclerView mIdJiben2;
    @BindView(R.id.id_jiben_3)
    RecyclerView mIdJiben3;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.sb_zancun)
    StateButton sbZancun;
    @BindView(R.id.sb_edit)
    StateButton sbEdit;
    Unbinder unbinder;


    private CustomDatePicker mDatePicker;

    private String timeTitle;

    private String id;
    private String gbhyflmc;
    private String gbhyfl;

    private List<BasicInformationBean.RecordsBean> mlist1 = new ArrayList<>();

    private List<BasicInformationBean.RecordsBean> mlist2 = new ArrayList<>();

    private List<BasicInformationBean.RecordsBean> mlist3 = new ArrayList<>();


    public static BasicInformationFragment newInstance() {

        Bundle args = new Bundle();

        BasicInformationFragment fragment = new BasicInformationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            sbZancun.setVisibility(View.GONE);
            sbEdit.setVisibility(View.GONE);
        }


        initDatePicker();
        mAdapter = new BasicInformationAdapter();
        mAdapter.setActivity(getActivity());
        mIdJiben1.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mIdJiben1.setAdapter(mAdapter);
        mIdJiben1.setNestedScrollingEnabled(false);//禁止滑动

        mAdapter2 = new BasicInformationAdapter();
        mIdJiben2.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mIdJiben2.setAdapter(mAdapter2);
        mIdJiben2.setNestedScrollingEnabled(false);//禁止滑动
        mAdapter3 = new BasicInformationAdapter();
        mIdJiben3.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mIdJiben3.setAdapter(mAdapter3);
        mIdJiben3.setNestedScrollingEnabled(false);//禁止滑动
        startProgressDialog(true);
        initview();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_basic_information;
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final AddressSelectBean addressSelectBean) {
        List<BasicInformationBean.RecordsBean> list = mAdapter.getData();
        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicInformationBean.RecordsBean bean : list) {
                if (bean.getName().equals("gjhyfl")) {
                    bean.setDefaultvalue(addressSelectBean.getTitle());
                    gbhyfl = addressSelectBean.getKey();

                }
            }


            mAdapter.setNewData(list);
            mAdapter.notifyDataSetChanged();
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final TimeSelect timeSelect) {

        if (ObjectUtils.isNotEmpty(timeSelect.getTime())) {
            mDatePicker.show(timeSelect.getTime());
            timeTitle = timeSelect.getTitle();
        }

    }

    /**
     * 刷新数据
     */

    private void initview() {
        CeditApi.getListAll("基本信息", null, new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                llAll.setVisibility(View.VISIBLE);
                stopProgressDialog();
                mlist1.clear();
                mlist2.clear();
                mlist3.clear();
                for (BasicInformationBean.RecordsBean bean : result.data.getRecords()) {
                    if (bean.getCategory().equals("基本信息")) {
                        mlist1.add(bean);
                    } else if (bean.getCategory().equals("导入信息")) {
                        mlist2.add(bean);
                    } else if (bean.getCategory().equals("其他信息")) {
                        mlist3.add(bean);
                    }
                }
                mAdapter.setNewData(mlist1);
                mAdapter2.setNewData(mlist2);
                mAdapter3.setNewData(mlist3);

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
        CeditApi.searchById(new BaseCallback<BaseResponse<JsonObject>>() {
            @Override
            public void onSucc(BaseResponse<JsonObject> result) {
                JsonObject jsonObject = result.data;
                try {
                    id = jsonObject.get("id").getAsString();
                } catch (java.lang.UnsupportedOperationException e) {
                    LogUtils.e(e);
                }

                if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
                    sbZancun.setVisibility(View.GONE);
                    sbEdit.setVisibility(View.GONE);
                } else {
                    if (ObjectUtils.isNotEmpty(id)) {
                        sbEdit.setVisibility(View.VISIBLE);
                        sbZancun.setVisibility(View.GONE);
                    } else {
                        LogUtils.e(id);
                        sbEdit.setVisibility(View.VISIBLE);
                        sbZancun.setVisibility(View.VISIBLE);

                    }
                }
                try {
                    gbhyflmc = jsonObject.get("gjhyflmc").getAsString();
                } catch (java.lang.UnsupportedOperationException e) {
                    LogUtils.e(e);
                }
                try {

                    JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                    Map<String, String> map = ObjectToMapUtils.JsonToMap(jsonObject1);

                    List<BasicInformationBean.RecordsBean> list = mAdapter.getData();
                    if (ObjectUtils.isNotEmpty(list)) {
                        for (BasicInformationBean.RecordsBean bean : list) {
                            if (bean.getName().equals("gjhyfl")) {


                                bean.setDefaultvalue(gbhyflmc);
                                gbhyfl = map.get(bean.getName());

                            } else {
                                bean.setDefaultvalue(map.get(bean.getName()));
                            }
                        }
                        mAdapter.setNewData(list);
                        mAdapter.notifyDataSetChanged();
                    }
                    List<BasicInformationBean.RecordsBean> list2 = mAdapter2.getData();
                    if (ObjectUtils.isNotEmpty(list2)) {
                        for (BasicInformationBean.RecordsBean bean : list2) {
                            if (map.containsKey(bean.getName())) {
                                bean.setEdit(true);
                                bean.setDefaultvalue(map.get(bean.getName()));
                            }
                        }
                        mAdapter2.setNewData(list2);
                        mAdapter2.notifyDataSetChanged();
                    }
                    List<BasicInformationBean.RecordsBean> list3 = mAdapter3.getData();
                    if (ObjectUtils.isNotEmpty(list3)) {
                        for (BasicInformationBean.RecordsBean bean : list3) {
                            if (map.containsKey(bean.getName())) {
                                bean.setEdit(true);
                                bean.setDefaultvalue(map.get(bean.getName()));
                            }
                        }
                        mAdapter3.setNewData(list3);
                        mAdapter3.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    LogUtils.e(e);
                }
            }
        }, this);
    }

    @OnClick({R.id.sb_zancun, R.id.sb_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_zancun:
                setSqJe();
                Map<String, String> mapZancun = getMap();
                CeditApi.zancunInfo(mapZancun, new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        ToastUtil.showBlackToastSucess("暂存成功");
                        getListById();
                    }
                }, this);
                break;
            case R.id.sb_edit:
                setSqJe();
                Map<String, String> map = getMap();
                if (ObjectUtils.isNotEmpty(id)) {
                    map.put("id", id);
                    CeditApi.editINfo(map, new BaseCallback<BaseResponse<Void>>() {
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
                    if (bean.getName().equals("gjhyfl")) {

                        map.put(bean.getName(), gbhyfl);
                    } else {
                        map.put(bean.getName(), bean.getDefaultvalue());
                    }
                }
            }

        }
        List<BasicInformationBean.RecordsBean> list2 = mAdapter2.getData();
        if (ObjectUtils.isNotEmpty(list2)) {
            for (BasicInformationBean.RecordsBean bean : list2) {
                if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                    map.put(bean.getName(), bean.getDefaultvalue());
                }
            }

        }
        List<BasicInformationBean.RecordsBean> list3 = mAdapter3.getData();
        if (ObjectUtils.isNotEmpty(list3)) {
            for (BasicInformationBean.RecordsBean bean : list3) {
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
        List<BasicInformationBean.RecordsBean> list2 = mAdapter2.getData();
        if (ObjectUtils.isNotEmpty(list2)) {
            for (BasicInformationBean.RecordsBean bean : list2) {
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
        List<BasicInformationBean.RecordsBean> list3 = mAdapter3.getData();
        if (ObjectUtils.isNotEmpty(list3)) {
            for (BasicInformationBean.RecordsBean bean : list3) {
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
        CeditApi.addInfo(map, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showBlackToastSucess("保存成功");
                getListById();
            }
        }, this);

    }


    /**
     * 计算申请金额
     */
    private void setSqJe() {
        int sqje = 0;
        List<BasicInformationBean.RecordsBean> list = mAdapter.getData();
        for (BasicInformationBean.RecordsBean bean : list) {
            if ("抵押(万元)".equals(bean.getTitile())) {
                if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                    sqje = sqje + Integer.parseInt(bean.getDefaultvalue());
                }
            } else if ("质押(万元)".equals(bean.getTitile())) {
                if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                    sqje = sqje + Integer.parseInt(bean.getDefaultvalue());
                }
            } else if ("保证(万元)".equals(bean.getTitile())) {
                if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                    sqje = sqje + Integer.parseInt(bean.getDefaultvalue());
                }
            } else if ("信用(万元)".equals(bean.getTitile())) {
                if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                    sqje = sqje + Integer.parseInt(bean.getDefaultvalue());
                }
            }
        }
        for (BasicInformationBean.RecordsBean bean : list) {
            if ("申请金额(万元)".equals(bean.getTitile())) {
                bean.setDefaultvalue(sqje + "");
            }
        }
        mAdapter.setNewData(list);
    }

}
