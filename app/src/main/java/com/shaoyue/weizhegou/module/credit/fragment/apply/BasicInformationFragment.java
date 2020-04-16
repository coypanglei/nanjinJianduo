package com.shaoyue.weizhegou.module.credit.fragment.apply;

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
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.BasicTitle;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.InfoChangeBean;
import com.shaoyue.weizhegou.entity.cedit.TimeSelect;
import com.shaoyue.weizhegou.entity.diaocha.AddressSelectBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
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
import butterknife.Unbinder;


public class BasicInformationFragment extends BaseAppFragment {


    @BindView(R.id.id_jiben_1)
    RecyclerView mIdJiben1;


    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.sb_zancun)
    StateButton sbZancun;
    @BindView(R.id.sb_edit)
    StateButton sbEdit;
    Unbinder unbinder;

    BasicInformationAdapter mAdapter;
    //标题集合
    private List<BasicTitle> titles = new ArrayList<>();

    private CustomDatePicker mDatePicker;

    private String timeTitle;

    private String id;
    private String gbhyflmc;
    private String gbhyfl;


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

        titles.add(new BasicTitle("基本信息", new ArrayList<BasicInformationBean.RecordsBean>()));
        titles.add(new BasicTitle("导入信息", new ArrayList<BasicInformationBean.RecordsBean>()));
        titles.add(new BasicTitle("其他信息", new ArrayList<BasicInformationBean.RecordsBean>()));
        initDatePicker();
        mAdapter = new BasicInformationAdapter();
        mAdapter.setActivity(getActivity());
        mIdJiben1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIdJiben1.setAdapter(mAdapter);


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
        List<BasicTitle> list = mAdapter.getData();
        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                    if (bean.getName().equals("gjhyfl")) {
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
    public void onMessageEvent(final TimeSelect timeSelect) {
        if (ObjectUtils.isNotEmpty(timeSelect.getTime())) {
            mDatePicker.show(timeSelect.getTime());
            timeTitle = timeSelect.getTitle();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final BasicInformationBean.RecordsBean item) {
        if (ObjectUtils.isNotEmpty(item.getDefaultvalue())) {
            if (item.getDefaultvalue().equals("阳光信贷")) {
                CeditApi.infoChange(new BaseCallback<BaseResponse<InfoChangeBean>>() {
                    @Override
                    public void onSucc(BaseResponse<InfoChangeBean> result) {
                        LogUtils.e(result);
                        if (ObjectUtils.isNotEmpty(result.data)) {
                            List<BasicTitle> list = mAdapter.getData();

                            if (ObjectUtils.isNotEmpty(list)) {
                                for (BasicTitle title : list) {
                                    for (BasicInformationBean.RecordsBean bean : title.getMlist()) {

                                        if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                                            if (bean.getName().equals("fsfs")) {
                                                if ("true".equals(result.data.getClsx())) {
                                                    bean.setDefaultvalue("存量授信");
                                                } else {
                                                    bean.setDefaultvalue("增量授信");
                                                }

                                            }
                                        }
                                    }

                                }
                            }
                            mAdapter.setNewData(list);
                            mAdapter.notifyDataSetChanged();
                            UIHelper.infoShowDialog(getActivity(), new OkOrCancelEvent(result.data.getTsxx()));
                        }

                    }
                }, this);

            }
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

                if (ObjectUtils.isNotEmpty(list)) {
                    for (BasicTitle title : list) {
                        for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                            if (bean.getTitile().equals(timeTitle)) {
                                bean.setDefaultvalue(DateFormatUtils.long2Str(timestamp, false));
                            }
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

                    List<BasicTitle> list = mAdapter.getData();

                    if (ObjectUtils.isNotEmpty(list)) {
                        for (BasicTitle title : list) {
                            for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                                if (map.containsKey(bean.getName())) {
                                    bean.setEdit(true);
                                    if (bean.getName().equals("gjhyfl")) {
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
        List<BasicTitle> list = mAdapter.getData();

        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                    if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                        if (bean.getName().equals("gjhyfl")) {
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
                        if (bean.getName().equals("gjhyfl")) {
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
        List<BasicTitle> list = mAdapter.getData();

        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
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
            }
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                    if ("申请金额(万元)".equals(bean.getTitile())) {
                        bean.setDefaultvalue(sqje + "");
                    }
                }
            }
        }
        mAdapter.setNewData(list);
    }

}
