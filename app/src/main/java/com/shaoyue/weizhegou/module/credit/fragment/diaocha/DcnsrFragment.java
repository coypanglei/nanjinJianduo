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
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
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
import com.shaoyue.weizhegou.entity.diaocha.AddressSelectBean;
import com.shaoyue.weizhegou.entity.sxdc.DbBean;
import com.shaoyue.weizhegou.module.sxdc.adapter.NsrInformationAdapter;
import com.shaoyue.weizhegou.util.GsonUtil;
import com.shaoyue.weizhegou.util.ObjectToMapUtils;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.datepicker.CustomDatePicker;
import com.shaoyue.weizhegou.widget.datepicker.DateFormatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class DcnsrFragment extends BaseAppFragment {
    @BindView(R.id.ry_info)
    RecyclerView mIdJiben1;
    NsrInformationAdapter mAdapter;

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

    private int jtrk = 1;

    public static DcnsrFragment newInstance() {

        Bundle args = new Bundle();
        DcnsrFragment fragment = new DcnsrFragment();
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
                mEtStartTime.setText(DateFormatUtils.long2Str(timestamp, false));
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final RefreshBean refreshBean) {

        final List<BasicTitle> list = mAdapter.getData();
        Double zsr = 0.0;
        Double qnzsr = 0.0;

        Double zzc = 0.0;
        Double qnzzc = 0.0;


        Double zysr = 0.0;
        Double zyzc = 0.0;
        Double qnzysr = 0.0;
        Double qnzyzc = 0.0;

        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean1 : title.getMlist()) {
                    if (ObjectUtils.isNotEmpty(bean1.getList())) {
                        for (BasicInformationBean.RecordsBean bean : bean1.getList()) {

                            if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {

                                LogUtils.e(title.getTitle());
                                if ("收入情况".equals(title.getTitle())) {

                                    if ("去年收入证明显示余额(万元)".equals(bean.getTitile())) {

                                        if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {

                                            if ("主营业务收入".equals(bean1.getTitile())) {
                                                zysr = Double.valueOf(bean.getDefaultvalue());
                                            }

                                            zsr = zsr + Double.valueOf(bean.getDefaultvalue());
                                        }
                                    } else if ("工资流水金额(万元)".equals(bean.getTitile())) {

                                        if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {


                                            qnzsr = qnzsr + Double.valueOf(bean.getDefaultvalue());
                                        }
                                    }
                                } else if ("支出情况".equals(title.getTitle())) {

                                    if ("去年(万元)".equals(bean.getTitile())) {

                                        if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {

                                            if ("主营业务支出".equals(bean1.getTitile())) {
                                                zyzc = Double.valueOf(bean.getDefaultvalue());
                                            }

                                            zzc = zzc + Double.valueOf(bean.getDefaultvalue());
                                        }
                                    } else if ("今年预测(万元)".equals(bean.getTitile())) {

                                        if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {

                                            if ("主营业务支出".equals(bean1.getTitile())) {
                                                qnzyzc = Double.valueOf(bean.getDefaultvalue());
                                            }

                                            qnzzc = qnzzc + Double.valueOf(bean.getDefaultvalue());
                                        }
                                    }
                                }

                            }

                        }
                    }

                }

            }

        }

        for (BasicTitle title : list) {
            if ("数据统计".equals(title.getTitle())) {
                //总收入
                title.getMlist().get(0).getList().get(0).setDefaultvalue(String.format("%.2f", zsr));
                title.getMlist().get(0).getList().get(1).setDefaultvalue(String.format("%.2f", qnzsr));

                //总支出
                title.getMlist().get(1).getList().get(0).setDefaultvalue(String.format("%.2f", zzc));
                title.getMlist().get(1).getList().get(1).setDefaultvalue(String.format("%.2f", qnzzc));


                //净利润
                title.getMlist().get(2).getList().get(0).setDefaultvalue(String.format("%.2f", (zsr - zzc)));
                title.getMlist().get(2).getList().get(1).setDefaultvalue(String.format("%.2f", (qnzsr - qnzzc)));

                //主营净利润
                title.getMlist().get(3).getList().get(0).setDefaultvalue(String.format("%.2f", (zsr - zzc) / jtrk));
                title.getMlist().get(3).getList().get(1).setDefaultvalue(String.format("%.2f", (qnzsr - qnzzc) / jtrk));


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
        sbZancun.setVisibility(View.GONE);
        titles.add(new BasicTitle("收入情况", new ArrayList<BasicInformationBean.RecordsBean>()));
        titles.add(new BasicTitle("支出情况", new ArrayList<BasicInformationBean.RecordsBean>()));
        titles.add(new BasicTitle("数据统计", new ArrayList<BasicInformationBean.RecordsBean>()));


        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            sbEdit.setVisibility(View.GONE);
        }


        mAdapter = new NsrInformationAdapter();
        mAdapter.setActivity(getActivity());
        mIdJiben1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIdJiben1.setAdapter(mAdapter);


        startProgressDialog(true);
        initview();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dc_info;
    }


    /**
     * 刷新数据
     */

    private void initview() {
        CeditApi.getListAll("年收入情况", null, new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                llAll.setVisibility(View.VISIBLE);
                stopProgressDialog();
                for (BasicTitle title : titles) {
                    if ("支出情况".equals(title.getTitle()) || "收入情况".equals(title.getTitle())) {
                        title.getMlist().add(new BasicInformationBean.RecordsBean("false", "top", "去年收入证明显示余额(万元),工资流水金额(万元),备注"));
                    }
                    for (BasicInformationBean.RecordsBean bean : result.data.getRecords()) {
                        if ("数据统计".equals(title.getTitle())) {
                            LogUtils.e(bean.getCategory());
                            LogUtils.e(title.getTitle());
                        }
                        if (bean.getCategory().equals(title.getTitle())) {
                            List<BasicInformationBean.RecordsBean> stringList = GsonUtil.fromJson(bean.getDateformat(), new TypeToken<List<BasicInformationBean.RecordsBean>>() {
                            }.getType());
                            bean.setList(stringList);
                            title.getMlist().add(bean);
                        }
                    }
//                    if ("数据统计".equals(title.getTitle())) {
//                        title.getMlist().add(new BasicInformationBean.RecordsBean());
//                        title.getMlist().add(new BasicInformationBean.RecordsBean());
//                    }
                }
                mAdapter.setNewData(titles);
//
//
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
        DcApi.getNsrInfo(new BaseCallback<BaseResponse<JsonObject>>() {
            @Override
            public void onSucc(BaseResponse<JsonObject> result) {

                if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {

                    sbEdit.setVisibility(View.GONE);


                }
//                }
                List<DbBean> stringList = new ArrayList<>();
                JsonObject jsonObject = result.data;
                try {
                    id = jsonObject.get("id").getAsString();
                } catch (UnsupportedOperationException e) {
                    LogUtils.e(e);
                }

                //担保
                try {
                    jtrk = jsonObject.get("jtrk").getAsInt();
                } catch (UnsupportedOperationException e) {
                    LogUtils.e(e);
                }

                try {

                    JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                    Map<String, String> map = ObjectToMapUtils.JsonToMap(jsonObject1);

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
                            if ("担保".equals(title.getTitle())) {
                                title.setMdbBeanList(stringList);
                            }
                        }
                        mAdapter.setNewData(list);
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    LogUtils.e(e);
                }


            }

            //家庭人口
            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                if (apiError.getErrMsg().contains("家庭人口数")) {

                }

            }
        }, this);
    }

    @OnClick({R.id.sb_edit, R.id.et_start_time})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.sb_edit:

                Map<String, String> map = getMap();
                if (ObjectUtils.isNotEmpty(id)) {
                    List<BasicTitle> list = mAdapter.getData();
                    if (ObjectUtils.isNotEmpty(list)) {
                        for (BasicTitle title : list) {
                            for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                                if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {

                                } else {
//                                    if (bean.getRequire().equals("true")) {
//                                        ToastUtil.showBlackToastSucess(bean.getTitile() + "不能为空");
//                                        return;
//                                    }
                                }

                            }

                        }
                    }
                    String mStartTime = mEtStartTime.getText().toString().trim();
                    map.put("jzrq", mStartTime);
                    map.put("id", id);
                    DcApi.editnsrINfo(map, new BaseCallback<BaseResponse<Void>>() {
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

        DcApi.addnsrINfo(map, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showBlackToastSucess("保存成功");
                getListById();
            }
        }, this);

    }

}
