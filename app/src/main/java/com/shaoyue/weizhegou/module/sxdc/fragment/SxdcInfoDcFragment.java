package com.shaoyue.weizhegou.module.sxdc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
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
import com.shaoyue.weizhegou.api.remote.DcApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.BasicTitle;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.entity.cedit.TiJiaoBean;
import com.shaoyue.weizhegou.entity.cedit.TimeSelect;
import com.shaoyue.weizhegou.entity.diaocha.AddressSelectBean;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.sxdc.adapter.BasicInformationAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
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


public class SxdcInfoDcFragment extends BaseAppFragment {


    @BindView(R.id.ry_info)
    RecyclerView mIdJiben1;
    BasicInformationAdapter mAdapter;

    String title;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    private CustomDatePicker mDatePicker;
    @BindView(R.id.sb_zancun)
    StateButton sbZancun;
    @BindView(R.id.sb_edit)
    StateButton sbEdit;

    private String id;

    private String gbhyflmc;
    private String gbhyfl;
    String type;
    //标题集合
    private List<BasicTitle> titles = new ArrayList<>();
    private String timeTitle;

    public static SxdcInfoDcFragment newInstance() {

        Bundle args = new Bundle();
        SxdcInfoDcFragment fragment = new SxdcInfoDcFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            title = getArguments().getString("title");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final AddressSelectBean addressSelectBean) {

        List<BasicTitle> list = mAdapter.getData();

        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                    if (bean.getName().equals("gbhyfl")) {
                        bean.setDefaultvalue(addressSelectBean.getTitle());
                        gbhyfl = addressSelectBean.getKey();

                    }

                }
            }
            mAdapter.setNewData(list);
            mAdapter.notifyDataSetChanged();
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final RefreshBean refreshBean) {

        final List<BasicTitle> list = mAdapter.getData();
        Double sxed = 0.0;

        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean1 : title.getMlist()) {
                    if ("xy".equals(bean1.getName()) || "dy".equals(bean1.getName()) || "zy".equals(bean1.getName()) || "db".equals(bean1.getName())) {
                        sxed = sxed + Double.valueOf(bean1.getDefaultvalue());
                    }
                }
            }
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean1 : title.getMlist()) {
                    if ("sxed".equals(bean1.getName())) {
                        bean1.setDefaultvalue(String.format("%.2f", sxed));
                    }
                }
            }

        }

        if (mIdJiben1.isComputingLayout()) {
            mIdJiben1.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.setNewData(list);
                    mAdapter.notifyDataSetChanged();
                }
            });
        } else {
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
     * 时间滑轮
     */
    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2009-05-01", false);
        long endTimestamp = DateFormatUtils.str2Long("2119-05-01", false);
        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                List<BasicTitle> list = mAdapter.getData();
                for (BasicTitle title : list) {
                    for (BasicInformationBean.RecordsBean recordsBean : title.getMlist()) {
                        if (recordsBean.getTitile().equals(timeTitle)) {
                            recordsBean.setDefaultvalue(DateFormatUtils.long2Str(timestamp, false));
                        }
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


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        initDatePicker();
        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            sbZancun.setVisibility(View.GONE);
            sbEdit.setVisibility(View.GONE);
        }
        type = SPUtils.getInstance().getString(UserMgr.SP_DC_TYPE);
        switch (type) {
            case "简化经营":
                titles.add(new BasicTitle("授信结论", new ArrayList<BasicInformationBean.RecordsBean>()));

                break;
            case "工薪类":
                titles.add(new BasicTitle("授信结论", new ArrayList<BasicInformationBean.RecordsBean>()));
                break;
            case "经营":
                titles.add(new BasicTitle("授信结论", new ArrayList<BasicInformationBean.RecordsBean>()));
                break;
            case "农户":
                titles.add(new BasicTitle("授信结论", new ArrayList<BasicInformationBean.RecordsBean>()));
                break;
        }


        mAdapter = new BasicInformationAdapter();
        mAdapter.setActivity(getActivity());
        mIdJiben1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIdJiben1.setAdapter(mAdapter);


        startProgressDialog(true);
        initview();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dc_sxjl_info;
    }


    /**
     * 刷新数据
     */

    private void initview() {
        CeditApi.getListAll("授信调查", null, new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                llAll.setVisibility(View.VISIBLE);
                stopProgressDialog();
                for (BasicTitle title : titles) {
                    for (BasicInformationBean.RecordsBean bean : result.data.getRecords()) {
                        if (bean.getCategory().equals(title.getTitle())) {
                            title.getMlist().add(bean);
                        }
                    }
                }
                mAdapter.setNewData(titles);


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
     * 获取界面数据byid
     */
    private void getListById() {
        DcApi.getSxjlInfo(new BaseCallback<BaseResponse<JsonObject>>() {
            @Override
            public void onSucc(BaseResponse<JsonObject> result) {


                JsonObject jsonObject = result.data;
                try {
                    id = jsonObject.get("id").getAsString();
                } catch (UnsupportedOperationException e) {
                    LogUtils.e(e);
                }
                if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
                    sbZancun.setVisibility(View.GONE);
                    sbEdit.setVisibility(View.GONE);
                } else {
                    if (ObjectUtils.isNotEmpty(id)) {
                        sbEdit.setVisibility(View.VISIBLE);

                    } else {
                        sbEdit.setVisibility(View.VISIBLE);


                    }
                }


                try {

                    JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                    Map<String, String> map = ObjectToMapUtils.JsonToMap(jsonObject1);

                    List<BasicTitle> list = mAdapter.getData();

                    if (ObjectUtils.isNotEmpty(list)) {
                        for (BasicTitle title : list) {
                            for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                                if (map.containsKey(bean.getName())) {
                                    bean.setEdit(true);
                                    if (bean.getName().equals("gbhyfl")) {
                                        LogUtils.e(gbhyflmc);
                                        bean.setDefaultvalue(gbhyflmc);
                                        gbhyfl = map.get(bean.getName());

                                    } else {
                                        bean.setDefaultvalue(map.get(bean.getName()));
                                    }
                                }
                            }
                        }
                        mAdapter.setNewData(list);
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    LogUtils.e(e);
                }


//
//


            }
        }, this);
    }

    @OnClick({R.id.sb_zancun, R.id.sb_edit})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.sb_zancun:
                String taskid = SPUtils.getInstance().getString(UserMgr.SP_DC_TASKID);
                String instid = SPUtils.getInstance().getString(UserMgr.SP_DC_INSTID);
                if (ObjectUtils.isNotEmpty(taskid)) {
                    CeditApi.putDanbaoInfo(taskid, instid, new BaseCallback<BaseResponse<TiJiaoBean>>() {
                        @Override
                        public void onSucc(BaseResponse<TiJiaoBean> result) {
                            UIHelper.showSxSpDialog(getActivity(), result.data);
                        }

                        @Override
                        public void onFail(ApiException apiError) {
                            super.onFail(apiError);

                        }
                    }, this);


                }
                break;
            case R.id.sb_edit:

                Map<String, String> map = getMap();
                if (ObjectUtils.isNotEmpty(id)) {
                    List<BasicTitle> list = mAdapter.getData();
                    if (ObjectUtils.isNotEmpty(list)) {
                        for (BasicTitle title : list) {
                            for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                                if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {

                                } else {
                                    if (bean.getRequire().equals("true")) {
                                        ToastUtil.showBlackToastSucess(bean.getTitile() + "不能为空");
                                        return;
                                    }
                                }

                            }

                        }
                    }
                    map.put("id", id);
                    DcApi.editSxjlInfo(map, new BaseCallback<BaseResponse<Void>>() {
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
        List<BasicTitle> list = mAdapter.getData();

        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                    if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                        if (bean.getName().equals("gbhyfl")) {
                            LogUtils.e(gbhyfl);
                            map.put(bean.getName(), gbhyfl);
                        } else {
                            map.put(bean.getName(), bean.getDefaultvalue());
                        }
                    }
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
        List<BasicTitle> list = mAdapter.getData();

        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                    if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                        if (bean.getName().equals("gbhyfl")) {
                            map.put(bean.getName(), gbhyfl);
                        } else {
                            map.put(bean.getName(), bean.getDefaultvalue());
                        }
                    } else {
                        if (bean.getRequire().equals("true")) {
                            ToastUtil.showBlackToastSucess(bean.getTitile() + "不能为空");
                            return;
                        }


                    }

                }

            }
        }

        DcApi.addSxjlInfo(map, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showBlackToastSucess("保存成功");
                getListById();
            }
        }, this);

    }


}
