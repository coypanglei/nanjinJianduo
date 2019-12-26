package com.shaoyue.weizhegou.module.credit.fragment.diaocha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.JsonArray;
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
import com.shaoyue.weizhegou.module.sxdc.adapter.ZcfzInformationAdapter;
import com.shaoyue.weizhegou.util.GsonUtil;
import com.shaoyue.weizhegou.util.ObjectToMapUtils;
import com.shaoyue.weizhegou.util.ToastUtil;

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

public class DcZCFZFragment extends BaseAppFragment {
    @BindView(R.id.ry_info)
    RecyclerView mIdJiben1;
    ZcfzInformationAdapter mAdapter;

    String title;
    @BindView(R.id.ll_all)
    LinearLayout llAll;

    @BindView(R.id.sb_edit)
    StateButton sbEdit;

    @BindView(R.id.sb_zancun)
    StateButton sbZancun;
    private String id;


    private String gbhyfl;

    //标题集合
    private List<BasicTitle> titles = new ArrayList<>();

    public static DcZCFZFragment newInstance() {

        Bundle args = new Bundle();
        DcZCFZFragment fragment = new DcZCFZFragment();
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
    public void onMessageEvent(final RefreshBean refreshBean) {

        List<BasicTitle> list = mAdapter.getData();
        Double zongzican = 0.0;
        Double zfz = 0.0;
        Double dfdbze = 0.0;
        Double chedai = 0.0;
        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean1 : title.getMlist()) {
                    if (ObjectUtils.isNotEmpty(bean1.getList())) {
                        for (BasicInformationBean.RecordsBean bean : bean1.getList()) {

                            if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {


                                if ("资产".equals(title.getTitle())) {
                                    LogUtils.e(bean.getTitile());
                                    if ("价值（万元）".equals(bean.getTitile())) {

                                        if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {

                                            if ("车辆".equals(bean1.getTitile())) {
                                                chedai = Double.valueOf(bean.getDefaultvalue());
                                            }
                                            zongzican = zongzican + Double.valueOf(bean.getDefaultvalue());
                                        }
                                    }
                                } else if ("负债".equals(title.getTitle())) {
                                    if ("价值（万元）".equals(bean.getTitile())) {
                                        if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                                            zfz = zfz + Double.valueOf(bean.getDefaultvalue());
                                        }
                                    }
                                }

                            }

                        }
                    }

                }
                //担保集合 获取数据
                if ("担保".equals(title.getTitle())) {
                    for (int i = 1; i < title.getMdbBeanList().size(); i++) {
                        if (ObjectUtils.isNotEmpty(title.getMdbBeanList().get(i).getJkje())) {
                            dfdbze = dfdbze + Double.valueOf(title.getMdbBeanList().get(i).getJkje());
                        }
                    }


                }
            }

        }
        for (BasicTitle title : list) {
            if ("数据统计".equals(title.getTitle())) {
                for (int i = 0; i < title.getMlist().size(); i++) {
                    title.getMlist().get(0).setDefaultvalue(zongzican + "");
                    title.getMlist().get(1).setDefaultvalue(zfz + "");
                    title.getMlist().get(2).setDefaultvalue(dfdbze + "");
                    title.getMlist().get(3).setDefaultvalue((zongzican - chedai - zfz) + "");
                    title.getMlist().get(4).setDefaultvalue((zfz / zongzican * 100) + "");
                }

            }
        }
        mAdapter.setNewData(list);
        mAdapter.notifyDataSetChanged();


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

        titles.add(new BasicTitle("资产", new ArrayList<BasicInformationBean.RecordsBean>()));
        titles.add(new BasicTitle("负债", new ArrayList<BasicInformationBean.RecordsBean>()));
        titles.add(new BasicTitle("担保", new ArrayList<BasicInformationBean.RecordsBean>()));
        titles.add(new BasicTitle("数据统计", new ArrayList<BasicInformationBean.RecordsBean>()));


        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            sbEdit.setVisibility(View.GONE);
        }


        mAdapter = new ZcfzInformationAdapter();
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
        CeditApi.getListAll("资产负债", null, new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                llAll.setVisibility(View.VISIBLE);
                stopProgressDialog();


                for (BasicTitle title : titles) {
                    if (!"数据统计".equals(title.getTitle())) {
                        title.getMlist().add(new BasicInformationBean.RecordsBean("false", "top", "价值(万元),备注"));
                    }
                    for (BasicInformationBean.RecordsBean bean : result.data.getRecords()) {
                        if (bean.getCategory().equals(title.getTitle())) {
                            List<BasicInformationBean.RecordsBean> stringList = GsonUtil.fromJson(bean.getDateformat(), new TypeToken<List<BasicInformationBean.RecordsBean>>() {
                            }.getType());
                            bean.setList(stringList);
                            title.getMlist().add(bean);
                        }
                    }
                    if ("数据统计".equals(title.getTitle())) {
                        title.getMlist().add(new BasicInformationBean.RecordsBean());
                        title.getMlist().add(new BasicInformationBean.RecordsBean());
                        title.getMlist().add(new BasicInformationBean.RecordsBean());

                    }
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
        DcApi.getZcfzInfo(new BaseCallback<BaseResponse<JsonObject>>() {
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
                    JsonArray jsonArray = jsonObject.getAsJsonArray("dbrList");
                    stringList = GsonUtil.fromJson(jsonArray.toString(), new TypeToken<List<DbBean>>() {
                    }.getType());
                    stringList.add(0, new DbBean("担保人姓名", "备注", "价值(万元)", "五级分类"));

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


//
//


            }
        }, this);
    }

    @OnClick({R.id.sb_edit})
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
                    map.put("id", id);
                    DcApi.editZcfzInfo(map, new BaseCallback<BaseResponse<Void>>() {
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
                    if ("数据统计".equals(title.getTitle())) {
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
                    if ("数据统计".equals(title.getTitle())) {
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

        DcApi.addZcfz(map, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showBlackToastSucess("保存成功");
                getListById();
            }
        }, this);

    }

}
