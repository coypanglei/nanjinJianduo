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
import com.shaoyue.weizhegou.api.remote.TyApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.BasicTitle;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.TimeSelect;
import com.shaoyue.weizhegou.entity.diaocha.AddressSelectBean;
import com.shaoyue.weizhegou.module.sxdc.adapter.BasicInformationNewAdapter;
import com.shaoyue.weizhegou.util.ObjectToMapUtils;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.datepicker.CustomDatePicker;
import com.shaoyue.weizhegou.widget.datepicker.DateFormatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


public class BasicInformationTyDbFragment extends BaseAppFragment {


    @BindView(R.id.ry_info)
    RecyclerView mIdJiben1;
    BasicInformationNewAdapter mAdapter;

    String title;
    @BindView(R.id.ll_all)
    LinearLayout llAll;

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

    public static BasicInformationTyDbFragment newInstance(String type) {

        Bundle args = new Bundle();
        args.putString("type", type);
        BasicInformationTyDbFragment fragment = new BasicInformationTyDbFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            title = getArguments().getString("title");
            type = getArguments().getString("type");
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


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            sbZancun.setVisibility(View.GONE);
            sbEdit.setVisibility(View.GONE);
        }

        switch (type) {
            case "首贷结论":
                titles.add(new BasicTitle("结论", new ArrayList<BasicInformationBean.RecordsBean>()));
                sbZancun.setVisibility(View.GONE);
                break;

            case "贷后基本信息":
                titles.add(new BasicTitle("基本信息", new ArrayList<BasicInformationBean.RecordsBean>()));
                sbZancun.setVisibility(View.GONE);
                break;

            case "个贷基本信息":
                titles.add(new BasicTitle("基本信息", new ArrayList<BasicInformationBean.RecordsBean>()));
                sbZancun.setVisibility(View.GONE);
                break;

            case "个贷检查结果":
                titles.add(new BasicTitle("检查结果", new ArrayList<BasicInformationBean.RecordsBean>(), 1));
                sbZancun.setVisibility(View.GONE);
                break;
            case "个贷汇法网查询":
                titles.add(new BasicTitle("汇法网查询", new ArrayList<BasicInformationBean.RecordsBean>(), 1));
                sbZancun.setVisibility(View.GONE);
                break;
            case "对公贷款汇法网查询":
                titles.add(new BasicTitle("汇法网查询", new ArrayList<BasicInformationBean.RecordsBean>(), 1));
                sbZancun.setVisibility(View.GONE);
                break;
            case "对公基本信息":
                titles.add(new BasicTitle("基本信息", new ArrayList<BasicInformationBean.RecordsBean>()));
                sbZancun.setVisibility(View.GONE);
                break;

            case "对公贷款检查结果":
                titles.add(new BasicTitle("检查结论", new ArrayList<BasicInformationBean.RecordsBean>(), 1));
                sbZancun.setVisibility(View.GONE);
                break;
            case "对公非财务分析":
                titles.add(new BasicTitle("履约及信用状况", new ArrayList<BasicInformationBean.RecordsBean>(), 1));
                titles.add(new BasicTitle("管理情况", new ArrayList<BasicInformationBean.RecordsBean>(), 1));
                titles.add(new BasicTitle("担保抵押状况", new ArrayList<BasicInformationBean.RecordsBean>(), 1));
                titles.add(new BasicTitle("行业及市场风险分析", new ArrayList<BasicInformationBean.RecordsBean>(), 1));
                sbZancun.setVisibility(View.GONE);
                break;
            case "抵质(押)分析":
                titles.add(new BasicTitle("抵押物分析",new ArrayList<BasicInformationBean.RecordsBean>()));
                titles.add(new BasicTitle("土地使用权",new ArrayList<BasicInformationBean.RecordsBean>()));
                titles.add(new BasicTitle("房屋价值",new ArrayList<BasicInformationBean.RecordsBean>()));
                titles.add(new BasicTitle("评估信息",new ArrayList<BasicInformationBean.RecordsBean>()));
                sbZancun.setVisibility(View.GONE);
                break;
        }


        mAdapter = new BasicInformationNewAdapter();
        mAdapter.setActivity(getActivity());
        mIdJiben1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIdJiben1.setAdapter(mAdapter);

        //加载慢时的圈
        startProgressDialog(true);
        initview();
        //时间view
        initDatePicker();


    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ty_info;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final TimeSelect timeSelect) {
        LogUtils.e(type);
        if (ObjectUtils.isNotEmpty(timeSelect.getTime())) {
            List<BasicTitle> list = mAdapter.getData();
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                    if (bean.getTitile().equals(timeSelect.getTitle())) {
                        mDatePicker.show(timeSelect.getTime());
                        timeTitle = timeSelect.getTitle();
                    }
                }
            }

        }

    }

    private CustomDatePicker mDatePicker;
    private String timeTitle;

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
                    for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                        if (bean.getTitile().equals(timeTitle)) {
                            bean.setDefaultvalue(DateFormatUtils.long2Str(timestamp, false));
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
     * 刷新数据
     */

    private void initview() {
        CeditApi.getListAll(type, null, new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                llAll.setVisibility(View.VISIBLE);
                stopProgressDialog();
                if (ObjectUtils.isNotEmpty(result.data.getRecords())) {
                    for (BasicTitle title : titles) {
                        for (BasicInformationBean.RecordsBean bean : result.data.getRecords()) {
                            if (bean.getCategory().equals(title.getTitle())) {
                                title.getMlist().add(bean);
                            }
                        }
                    }
                    mAdapter.setNewData(titles);

                }


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
        TyApi.getTyJbinfo(type, new BaseCallback<BaseResponse<JsonObject>>() {
            @Override
            public void onSucc(BaseResponse<JsonObject> result) {


                JsonObject jsonObject = result.data;
                try {
                    id = jsonObject.get("id").getAsString();
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
                    sbZancun.setVisibility(View.GONE);
                    sbEdit.setVisibility(View.GONE);
                } else {
//                    if (ObjectUtils.isNotEmpty(id)) {
//                        sbEdit.setVisibility(View.VISIBLE);
//                        sbZancun.setVisibility(View.GONE);
//                    } else {
//                        sbEdit.setVisibility(View.VISIBLE);
//                        sbZancun.setVisibility(View.VISIBLE);
//
//                    }
                }
                try {
                    gbhyflmc = jsonObject.get("gbhyflmc").getAsString();
                } catch (Exception e) {
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
                } catch (Exception e) {
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
                Map<String, String> mapZancun = getMap();
                DcApi.zancunInfo(mapZancun, new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        ToastUtil.showBlackToastSucess("暂存成功");
                        getListById();
                    }
                }, this);
                break;
            case R.id.sb_edit:

                Map<String, String> map = getMap();

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
//                if (goAllSelect.isAdd()) {
//
//                    String add = CeditApi.DANBAOREN_ADD;
//                    if ("自然人担保分析".equals(goAllSelect.getTitle())) {
//                        add = CeditApi.DANBAOREN_ADD;
//                    } else if ("抵(质)押分析".equals(goAllSelect.getTitle())) {
//                        add = CeditApi.DANBAODIYA_ADD;
//                    } else if ("公司担保分析".equals(goAllSelect.getTitle())) {
//                        add = CeditApi.DANBAOREN_ADD_GONGSI;
//                    } else if ("企业担保分析".equals(goAllSelect.getTitle())) {
//                        add = CeditApi.DANBAOREN_ADD_QIYE;
//                    }
//                    CeditApi.addDanbaoInfo(add, map, new BaseCallback<BaseResponse<Void>>() {
//                        @Override
//                        public void onSucc(BaseResponse<Void> result) {
//
//                            ToastUtil.showBlackToastSucess("保存成功");
//                            getListById();
//                        }
//                    }, this);
//                } else {
//                    String edit = CeditApi.DANBAOREN_EDIT;
//                    if ("自然人担保分析".equals(goAllSelect.getTitle())) {
//                        edit = CeditApi.DANBAOREN_EDIT;
//                        map.put("id", goAllSelect.getmZiRanDanBao().getId());
//                    } else if ("抵(质)押分析".equals(goAllSelect.getTitle())) {
//                        edit = CeditApi.DANBAOZHIYA_EDIT+"All";
//                        map.put("id", goAllSelect.getmDiyaDanBao().getId());
//                    } else if ("公司担保分析".equals(goAllSelect.getTitle())) {
//                        edit = CeditApi.DANBAOREN_EDIT_GONGSI;
//                        map.put("id", goAllSelect.getmGongsiDanBao().getId());
//                    } else if ("企业担保分析".equals(goAllSelect.getTitle())) {
//                        edit = CeditApi.DANBAOREN_EDIT_QIYE;
//                        map.put("id", goAllSelect.getmDiyaDanBao().getId());
//                    }
//
//                    CeditApi.editDanbaoInfo(edit, map, new BaseCallback<BaseResponse<Void>>() {
//                        @Override
//                        public void onSucc(BaseResponse<Void> result) {
//
//                            ToastUtil.showBlackToastSucess("修改成功");
//                            getListById();
//                        }
//                    }, this);
//                }



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




}
