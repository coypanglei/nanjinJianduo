package com.shaoyue.weizhegou.module.credit.fragment.diaocha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.reflect.TypeToken;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.DcApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.BasicTitle;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.GoAllSelect;
import com.shaoyue.weizhegou.entity.cedit.TimeSelect;
import com.shaoyue.weizhegou.entity.sxdc.DbBean;
import com.shaoyue.weizhegou.module.sxdc.adapter.DydbQyInformationAdapter;
import com.shaoyue.weizhegou.util.GsonUtil;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.datepicker.CustomDatePicker;
import com.shaoyue.weizhegou.widget.datepicker.DateFormatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class DcDbQyfxFragment extends BaseTitleFragment {
    @BindView(R.id.ry_info)
    RecyclerView mIdJiben1;
    DydbQyInformationAdapter mAdapter;

    String title;
    @BindView(R.id.ll_all)
    LinearLayout llAll;

    @BindView(R.id.sb_edit)
    StateButton sbEdit;

    @BindView(R.id.sb_zancun)
    StateButton sbZancun;
    private String id;
    @BindView(R.id.et_start_time)
    TextView mEtStartTime;
    @BindView(R.id.rl_time)
    RelativeLayout rlTime;
    private String gbhyfl;
    private CustomDatePicker mDatePicker;
    //标题集合
    private List<BasicTitle> titles = new ArrayList<>();
    GoAllSelect goAllSelect;

    public static DcDbQyfxFragment newInstance(GoAllSelect goAllSelect) {

        Bundle args = new Bundle();
        args.putSerializable("goAllSelect", goAllSelect);
        DcDbQyfxFragment fragment = new DcDbQyfxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            goAllSelect = (GoAllSelect) getArguments().getSerializable("goAllSelect");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private String timeTitle;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final TimeSelect timeSelect) {

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

    /**
     * 时间滑轮
     */
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

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        sbZancun.setVisibility(View.GONE);
        rlTime.setVisibility(View.VISIBLE);
        initDatePicker();
        if (goAllSelect.isAdd()) {
            setCommonTitle("新增" + goAllSelect.getTitle().replace("新增", ""));
        } else {
            setCommonTitle("修改" + goAllSelect.getTitle().replace("新增", ""));
        }

        titles.add(new BasicTitle("企业信息", new ArrayList<BasicInformationBean.RecordsBean>()));
        titles.add(new BasicTitle("财务分析", new ArrayList<BasicInformationBean.RecordsBean>()));
        titles.add(new BasicTitle("情况说明",new ArrayList<BasicInformationBean.RecordsBean>()));


        mEtStartTime.setVisibility(View.GONE);

        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            sbEdit.setVisibility(View.GONE);
            mEtStartTime.setVisibility(View.GONE);
        }


        mAdapter = new DydbQyInformationAdapter();
        mAdapter.setActivity(getActivity());
        mIdJiben1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIdJiben1.setAdapter(mAdapter);


        startProgressDialog(true);
        initview();

    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_dc_info;
    }


    /**
     * 刷新数据
     */

    private void initview() {
        CeditApi.getListAll("授信调查-企业担保分析", null, new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                llAll.setVisibility(View.VISIBLE);
                stopProgressDialog();


                for (BasicTitle title : titles) {
                    if ("财务分析".equals(title.getTitle())) {
                        title.getMlist().add(new BasicInformationBean.RecordsBean("false", "top", "前年12月(万元),上年12(万元),本期(万元),重要变动说明"));
                    }
                    for (BasicInformationBean.RecordsBean bean : result.data.getRecords()) {

                        if (bean.getCategory().equals(title.getTitle())) {
                            if (ObjectUtils.isNotEmpty(bean.getDateformat())) {
                                String str = bean.getDateformat().replace("\"\"", "null");
                                LogUtils.e(str);
                                try {
                                    List<BasicInformationBean.RecordsBean> stringList = GsonUtil.fromJson(str, new TypeToken<List<BasicInformationBean.RecordsBean>>() {
                                    }.getType());

                                    bean.setList(stringList);
                                    title.getMlist().add(bean);
                                } catch (Exception e) {
                                    LogUtils.e(e);
                                }
                            } else {
                                title.getMlist().add(bean);
                            }


                        }
                    }
//                    if ("数据统计".equals(title.getTitle())) {
//                        title.getMlist().add(new BasicInformationBean.RecordsBean());
//                        title.getMlist().add(new BasicInformationBean.RecordsBean());
//                    }
                }
                mAdapter.setNewData(titles);
//
                //修改时添加数据
                if (!goAllSelect.isAdd()) {
                    getListById();
                }

            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                stopProgressDialog();
            }
        }, this);
    }


    /**
     * 获取界面数据byid
     */
    private void getListById() {
        LogUtils.e("asasd");
//        DcApi.getSyjbInfo(new BaseCallback<BaseResponse<JsonObject>>() {
//            @Override
//            public void onSucc(BaseResponse<JsonObject> result) {

        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {

            sbEdit.setVisibility(View.GONE);


        }

//                //担保
//                try {
//                    JsonArray jsonArray = jsonObject.getAsJsonArray("dbrList");
//                    stringList = GsonUtil.fromJson(jsonArray.toString(), new TypeToken<List<DbBean>>() {
//                    }.getType());
//                    stringList.add(0, new DbBean("担保人姓名", "备注", "价值(万元)", "五级分类"));
//
//                } catch (UnsupportedOperationException e) {
//                    LogUtils.e(e);
//                }

        try {
            Map<String, String> map = goAllSelect.getJsonObjectmap();
            LogUtils.e(map);
            List<BasicTitle> list = mAdapter.getData();

            if (ObjectUtils.isNotEmpty(list)) {
                for (BasicTitle title : list) {
                    for (BasicInformationBean.RecordsBean bean1 : title.getMlist()) {

                        if (ObjectUtils.isNotEmpty(bean1.getList())) {
                            for (BasicInformationBean.RecordsBean bean : bean1.getList()) {

                                if (map.containsKey(bean.getName())) {
                                    bean.setEdit(true);

                                    bean.setDefaultvalue(map.get(bean.getName()));
                                }
                            }

                        }
                        if (map.containsKey(bean1.getName())) {
                            bean1.setEdit(true);

                            bean1.setDefaultvalue(map.get(bean1.getName()));
                        }
                    }
//                            if ("担保".equals(title.getTitle())) {
//                                title.setMdbBeanList(stringList);
//                            }
                }
                mAdapter.setNewData(list);
                mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }



    }

    @OnClick({R.id.sb_edit, R.id.et_start_time})
    public void onViewClicked(View view) {

        switch (view.getId()) {

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
                map.put("id", goAllSelect.getJsonObjectmap().get("id").toString());
                if (goAllSelect.isAdd()) {

                    String add = CeditApi.DANBAOREN_ADD;
                    if ("授信调查(自然人)担保分析".equals(goAllSelect.getTitle())) {
                        add = CeditApi.DANBAOREN_ADD + "All";
                    } else if ("新增授信调查-抵(质)押分析".equals(goAllSelect.getTitle())) {
                        add = CeditApi.DANBAODIYA_ADD + "All";
                    } else if ("公司担保分析".equals(goAllSelect.getTitle())) {
                        add = CeditApi.DANBAOREN_ADD_GONGSI + "All";
                    } else if ("企业担保分析".equals(goAllSelect.getTitle())) {
                        add = CeditApi.DANBAOREN_ADD_QIYE + "All";
                    }
                    CeditApi.addDanbaoInfo(add, map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            removeFragment();

                            ToastUtil.showBlackToastSucess("保存成功");
                        }
                    }, this);
                } else {
                    String edit = CeditApi.DANBAOREN_EDIT;
                    if ("新增授信调查-抵(质)押分析".equals(goAllSelect.getTitle())) {
                        edit = CeditApi.DANBAOZHIYA_EDIT + "All";

                    } else if ("公司担保分析".equals(goAllSelect.getTitle())) {
                        edit = CeditApi.DANBAOREN_EDIT_GONGSI + "All";

                    } else if ("授信调查-企业担保分析".equals(goAllSelect.getTitle())) {
                        edit = CeditApi.DANBAOREN_EDIT_QIYE + "All";

                    }

                    CeditApi.editDanbaoInfo(edit, map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            removeFragment();
                            ToastUtil.showBlackToastSucess("修改成功");
                        }
                    }, this);
                }
                break;


            case R.id.et_start_time:

                mDatePicker.show(mEtStartTime.getText().toString());
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
                for (BasicInformationBean.RecordsBean bean1 : title.getMlist()) {
                    if (ObjectUtils.isNotEmpty(bean1.getList())) {
                        for (BasicInformationBean.RecordsBean bean : bean1.getList()) {

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
                    if ("企业信息".equals(title.getTitle()) || "情况说明".equals(title.getTitle())) {
                        if (ObjectUtils.isNotEmpty(bean1.getName())) {
                            map.put(bean1.getName(), bean1.getDefaultvalue());
                        }

                    }
                }

                //担保集合 获取数据
                if ("担保".equals(title.getTitle())) {
                    Field[] fields = DbBean.class.getDeclaredFields();
                    for (int t = 0; t < fields.length; t++) {
                        for (int i = 0; i < title.getMdbBeanList().size(); i++) {

                            //设置是否允许访问，不是修改原来的访问权限修饰词。
                            fields[t].setAccessible(true);

                            try {
                                LogUtils.e(fields[t].getName() + (i) + ":" + fields[t].get(title.getMdbBeanList().get(i)));
                                map.put(fields[t].getName() + (i), fields[t].get(title.getMdbBeanList().get(i)) + "");
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }


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
                for (BasicInformationBean.RecordsBean bean1 : title.getMlist()) {
                    if (ObjectUtils.isNotEmpty(bean1.getList())) {
                        for (BasicInformationBean.RecordsBean bean : bean1.getList()) {

                            if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                                if ("gbhyfl".equals(bean.getName())) {
                                    map.put(bean.getName(), gbhyfl);
                                } else {
                                    map.put(bean.getName(), bean.getDefaultvalue());
                                }
                            } else {
                                if ("true".equals(bean.getRequire())) {
                                    ToastUtil.showBlackToastSucess(bean.getTitile() + "不能为空");
                                    return;
                                }


                            }
                        }
                    }
                    if ("主营业务收入检验".equals(title.getTitle()) || "年净利润检验".equals(title.getTitle())) {
                        if (ObjectUtils.isNotEmpty(bean1.getName())) {
                            map.put(bean1.getName(), bean1.getDefaultvalue());
                        }

                    }
                }
                //担保集合 获取数据
                if ("担保".equals(title.getTitle())) {
                    Field[] fields = DbBean.class.getDeclaredFields();
                    for (int t = 0; t < fields.length; t++) {
                        for (int i = 0; i < title.getMdbBeanList().size(); i++) {

                            //设置是否允许访问，不是修改原来的访问权限修饰词。
                            fields[t].setAccessible(true);

                            try {
                                LogUtils.e(fields[t].getName() + (i) + ":" + fields[t].get(title.getMdbBeanList().get(i)));
                                map.put(fields[t].getName() + (i), fields[t].get(title.getMdbBeanList().get(i)) + "");
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }


                        }

                    }

                }

            }
        }

        DcApi.addSyjbINfo(map, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showBlackToastSucess("保存成功");
                removeFragment();
            }
        }, this);

    }

}
